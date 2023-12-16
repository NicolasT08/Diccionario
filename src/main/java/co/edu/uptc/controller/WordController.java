package co.edu.uptc.controller;

import co.edu.uptc.exceptions.ERROR_REASON;
import co.edu.uptc.exceptions.InvalidWord;
import co.edu.uptc.model.BinaryTree;
import co.edu.uptc.model.LinkedList;

import co.edu.uptc.model.Word;

public class WordController {
    private BinaryTree<Word>[] dictionary;
    static final int LETTER_NUM = 27;

    public WordController() {
        this.dictionary = new BinaryTree[LETTER_NUM];
        for( int i = 0; i < LETTER_NUM; i++ ){
            this.dictionary[i] = new BinaryTree<>(((o1, o2) -> o1.getId().compareTo(o2.getId())));
        }
    }

    public int findIndex( String word ) throws IllegalArgumentException{
        char firstChar = Character.toLowerCase(word.charAt(0));

        if ( firstChar >= 97 && firstChar <= 122 ){
            return firstChar - 97 + ( firstChar > 110 ? 1: 0 );
        }
        char [] especialCases = {'á','é','í','ñ','ó','ú'};
        int [] especialValues = {0,4,8,14,15,21};

        for ( int i = 0; i < especialCases.length; i++ ){
            if ( firstChar == especialCases[i] ) return especialValues[i];
        }

        throw new InvalidWord(word, ERROR_REASON.IMPOSSIBLE);
    }

    public String addWord(String word, String meaning, String translation) {
        String returnMessage = "";
        try {
            validateWord( word );
            validateMeaning( meaning );
            validateTranslation( translation );

            meaning = meaning.replaceAll("\\s+", " ");
            translation = translation.replaceAll("\\s+", " ");

            int root = this.findIndex(word);
            Word newWord = new Word( word.toLowerCase(), meaning,translation);
            if ( this.dictionary[root].findNode(newWord) != null) throw new InvalidWord( word, ERROR_REASON.EXIST);

            this.dictionary[root].addNode( newWord );
            returnMessage = "La palabra " + word + " ha sido añadida satisfactoriamente";

        }catch (IllegalArgumentException e){
            returnMessage = e.getMessage();
        }
        return returnMessage;
    }

    private void validateWord( String word ){
        if ( word == null ) throw  new InvalidWord( word, ERROR_REASON.IS_EMPTY);
        for( int i = 0; i < word.length(); i++ ){
            if ( !Character.isAlphabetic(word.charAt(i))) throw new InvalidWord( word, ERROR_REASON.INVALID_CHARACTERS);
        }
    }

    private void validateMeaning( String content ){
        if ( content == null ) throw  new InvalidWord( content, ERROR_REASON.IS_EMPTY);
        if ( content.isBlank() ) throw new InvalidWord( content, ERROR_REASON.IS_EMPTY);
        if ( content.startsWith(" ")) throw new InvalidWord( content, ERROR_REASON.STARTS_SPACE);
    }

    private void validateTranslation( String translation ){
        if ( translation == null ) throw new InvalidWord(translation, ERROR_REASON.IS_EMPTY);
        if ( translation.startsWith(" ")) throw new InvalidWord( translation, ERROR_REASON.STARTS_SPACE);
        for( int i = 0; i < translation.length(); i++ ){
            if ( translation.charAt(i) == ' ') continue;
            if ( !Character.isAlphabetic(translation.charAt(i))) throw new InvalidWord( translation, ERROR_REASON.INVALID_CHARACTERS);
        }
    }

    public String[] findWord(String word) {

        word = word.toLowerCase();

        int pos = this.findIndex( word );
        Word auxWord = new Word( word,"","" );
        String[] response = new String[3];

        try{
            response[0] = dictionary[pos].findNode( auxWord ).getInfo().getId();
            response[1] = dictionary[pos].findNode( auxWord ).getInfo().getMeaning();
            response[2] = dictionary[pos].findNode( auxWord ).getInfo().getTranslation();
        }catch (NullPointerException e){
            return null;
        }

        return response;
    }

    public String[][] listByFirstChar( char firstCharacter ){
        try {
            int root = this.findIndex(String.valueOf(firstCharacter));
            if ( this.dictionary[root].isEmpty() ) return null;
            LinkedList<Word> treeWords = this.dictionary[root].listInsort();
            String[][] result = new String[treeWords.size()][3];
            for ( int i = 0; i < treeWords.size(); i++ ){
                result[i][0] = treeWords.get(i).getId();
                result[i][1] = treeWords.get(i).getMeaning();
                result[i][2] = treeWords.get(i).getTranslation();
            }
            return result;
        }catch ( InvalidWord e){}
        return null;
    }

    public String[][] showAllWords(){
        int count = 0;
        for ( int i = 0; i < LETTER_NUM ; i++){
            if( !dictionary[i].isEmpty() ) count+= dictionary[i].weightTree();
        }

        if( count == 0 ) return null;

        String[][] response = new String[count][3];

        count = 0;

        for (int i = 0; i < LETTER_NUM; i++) {
            if (!dictionary[i].isEmpty()) {
                LinkedList<Word> words = dictionary[i].listInsort();

                for ( int t = 0; t < words.size(); t++ ) {
                    response[count][0] = words.get(t).getId();
                    response[count][1] = words.get(t).getMeaning();
                    response[count][2] = words.get(t).getTranslation();
                    count++;
                }
            }
        }


        return response;
    }

    public String[] updateWord(String word, String newValue, ATT_TYPE att){
        String[] response = new String[3];
        try {
            word = word.toLowerCase();
            newValue = newValue.toLowerCase();
            if ( this.dictionary[this.findIndex(word)].findNode(new Word(word, "", "")) == null ) throw new InvalidWord(word, ERROR_REASON.NOT_FOUND);
            switch ( att ){
                case WORD -> updateID( word, newValue);
                case MEANING -> updateMeaning( word,newValue);
                case TRANSLATE -> updateTranslation( word, newValue);
            }
            response = att == ATT_TYPE.WORD ? this.findWord(newValue) : this.findWord(word);
        }catch ( InvalidWord e){
            return new String[]{ e.getMessage() };
        }

        return response;
    }

    private void updateID( String word, String newValue ){
        String[] actual = this.findWord(word);
        if ( actual == null ) throw new InvalidWord( word, ERROR_REASON.NOT_FOUND);
        this.addWord( newValue, actual[1], actual[2] );
        if ( this.findWord( newValue ) == null || word.compareToIgnoreCase(newValue) == 0) throw new InvalidWord(newValue, ERROR_REASON.EXIST);
        this.deleteWord( word );
    }
    private void updateMeaning(String word, String newValue ){
        validateMeaning( newValue );
        newValue = newValue.replaceAll("\\s+", " ");
        this.dictionary[this.findIndex(word)].findNode(new Word(word, "", "")).getInfo().setMeaning(newValue);
    }
    private void updateTranslation( String word, String newValue ){
        validateTranslation( newValue );
        newValue = newValue.replaceAll("\\s+", " ");
        this.dictionary[this.findIndex(word)].findNode(new Word(word, "", "")).getInfo().setTranslation(newValue);
    }

    public String[] deleteWord(String word){
        int index = this.findIndex( word );
        String[] response = this.findWord( word );

        if( response == null ) return null;

        Word delete = new Word(word.toLowerCase()," "," ");

        dictionary[ index ].deleteNode(dictionary[ index ].findNode(delete));


        return response;
    }
}
