package co.edu.uptc.controller;

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

        throw  new IllegalArgumentException("La palabra " + word + " no pertenece a la lengua castellana.");
    }

    public String addWord(String word, String meaning, String translation) {
        return "";
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

    public String updateWord(String newValue, ATT_TYPE att){
        return "";
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
