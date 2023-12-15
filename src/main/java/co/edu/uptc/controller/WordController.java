package co.edu.uptc.controller;

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

        throw  new IllegalArgumentException("La palabra " + word + " no pertenece a la lengua castellana.");
    }

    public String addWord(String word, String meaning, String translation) {
        return "";
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
}
