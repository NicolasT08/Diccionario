package co.edu.uptc.controller;

import co.edu.uptc.exceptions.ERROR_REASON;
import co.edu.uptc.exceptions.InvalidWord;
import co.edu.uptc.model.BinaryTree;
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
            Word newWord = new Word( word, meaning,translation);
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
        return null;
    }

    public String[][] listByFirstChar( char firstCharacter ){
        return null;
    }

    public String[][] showAllWords(){
        return null;
    }

    public String updateWord(String newValue, ATT_TYPE att){
        return "";
    }
    public String deleteWord( String word ){
        return "";
    }
}
