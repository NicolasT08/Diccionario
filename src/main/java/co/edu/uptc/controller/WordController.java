package co.edu.uptc.controller;

import co.edu.uptc.exceptions.ERROR_REASON;
import co.edu.uptc.exceptions.InvalidWord;
import co.edu.uptc.model.BinaryTree;
import co.edu.uptc.model.LinkedList;
import co.edu.uptc.model.Word;

/**
 * This class is the handling for the Binary Trees that contains
 * the words. It has a static array to manage the spanish alphabet.
 * The main actions on this class are: Create, update, read and delete
 * words, also, it verifies the information before do some action.
 * @author Nicolas Tinjaca
 * @author Nicolas Sarmiento
 */
public class WordController {
    private BinaryTree<Word>[] dictionary;
    static final int LETTER_NUM = 27;
    private StringValidator validateWord;


    public WordController() {
        this.dictionary = new BinaryTree[LETTER_NUM];
        this.validateWord = new StringValidator();
        for( int i = 0; i < LETTER_NUM; i++ ){
            this.dictionary[i] = new BinaryTree<>(((o1, o2) -> o1.getId().compareTo(o2.getId())));
        }
    }

    /**
     * Find the index in the static array. This method
     * support and return a key for the dictionary. Based
     * on the first letter.
     * @param word word to be process.
     * @return an int that represents the position in the array.
     * @throws IllegalArgumentException when the parameter contains non spanish alphabet character.
     */
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

    /**
     * This method adds words to the array based on the first letter.
     * In this method all components of a word are verified.
     * @param word Word to add
     * @param meaning Meaning of the word to add.
     * @param translation word's English translation.
     * @return A String that contains a informative message. That indicate if
     * the word was successfully added or something went wrong.
     */
    public String addWord(String word, String meaning, String translation) {
        String returnMessage = "";
        try {
            this.validateWord.validateWord( word );
            this.validateWord.validateMeaning( meaning );
            this.validateWord.validateTranslation( translation );


            meaning = meaning.replaceAll("\\s+", " ");
            translation = translation.replaceAll("\\s+", " ");

            int root = this.findIndex(word);
            translation = translation.isEmpty() ? word.toLowerCase() : translation;
            Word newWord = new Word(word.toLowerCase(), meaning,translation);
            if ( this.dictionary[root].findNode(newWord) != null) throw new InvalidWord( word, ERROR_REASON.EXIST);
            if ( hasSimilarWord( newWord )) throw new InvalidWord( word, ERROR_REASON.EXIST);
            this.dictionary[root].addNode( newWord );
            returnMessage = "La palabra " + word + " ha sido añadida satisfactoriamente";

        }catch (IllegalArgumentException e){
            returnMessage = e.getMessage();
        }
        return returnMessage;
    }

    /**
     * Find a word in the dictionary array. Only needs the word itself. No
     * need of definition or translate.
     * @param word The word to find.
     * @return a String array that contains the word, meaning and translate of
     * the word. If the word isn't in the array a message will be returned.
     */
    public String[] findWord(String word) {

        word = word.toLowerCase();

        try {
            this.validateWord.validateWord( word );
        } catch ( IllegalArgumentException e ){
            String[] response = new String[3];
            response[0] = ""; response[1] = e.getMessage(); response[2] = "";
            return response;
        }

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

    /**
     * This method is for get all the words that begins with
     * the same letter.
     * @param firstCharacter is the character used to find the words.
     * @return a String matrix with all the words that begins with the
     * param firstCharacter. Null if there are no words that starts with
     * the specific letter.
     */
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

    /**
     * This method return all the words in
     * the dictionary.
     * @return a String matrix that contains all the
     * words of the dictionary. Null if there are no
     * words in the dictionary.
     */
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

    /**
     * This method update a word field. Also verified
     * if the word exists and the new value is allowed.
     * @param word word to be updated.
     * @param newValue new value to be set.
     * @param att the type of value. It can be Word, Meaning or Translation
     * @return a String array with the new components of the word. It the
     * operation went wrong, the return value will be a single element array.
     * This element is the information message.
     */
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

    /**
     * This method update the word specifically of a word.
     * with a verified word id that can't be the same or a
     * word id that exists. the method will throw an InvalidWord
     * exception if the operation can't be possible.
     * @param word word to be updated.
     * @param newValue the new word id.
     *
     */
    private void updateID( String word, String newValue ){
        String[] actual = this.findWord(word);
        if ( actual == null ) throw new InvalidWord( word, ERROR_REASON.NOT_FOUND);
        if ( this.findWord( newValue ) != null )throw new InvalidWord(newValue, ERROR_REASON.EXIST);
        this.addWord( newValue, actual[1], actual[2] );
        if ( this.findWord( newValue ) == null || word.compareToIgnoreCase(newValue) == 0) throw new InvalidWord(newValue, ERROR_REASON.EXIST);
        this.deleteWord( word );
    }

    /**
     * This method update the meaning of a word.
     * with a verified meaning that can't contain special characters.
     * The method will throw an InvalidWord
     * exception if the operation can't be possible.
     * @param word word to be updated.
     * @param newValue the new meaning for the word.
     */
    private void updateMeaning(String word, String newValue ){
        this.validateWord.validateMeaning( newValue );
        newValue = newValue.replaceAll("\\s+", " ");
        this.dictionary[this.findIndex(word)].findNode(new Word(word, "", "")).getInfo().setMeaning(newValue);
    }

    /**
     * This method update the translation of a word.
     * The method verifies the meaning, it can't contain
     * numbers or  special characters except spaces. If
     * the operation can't be possible, It will throw
     * InvalidWord exception
     * @param word word to be updated.
     * @param newValue the new translation of the word.
     */
    private void updateTranslation( String word, String newValue ){
        this.validateWord.validateTranslation( newValue );
        newValue = newValue.replaceAll("\\s+", " ");
        this.dictionary[this.findIndex(word)].findNode(new Word(word, "", "")).getInfo().setTranslation(newValue);
    }

    /**
     *This method delete a word from the dictionary.
     * @param word word to be deleted.
     * @return a String array with the components of
     * the word or null if the word doesn't exist.
     */
    public String[] deleteWord(String word){

        try {
            this.validateWord.validateWord( word );
        } catch ( IllegalArgumentException e ){
            String[] response = new String[3];
            response[0] = ""; response[1] = e.getMessage(); response[2] = "";
            return response;
        }

        int index = this.findIndex( word );
        String[] response = this.findWord( word );

        if( response == null ) return null;

        Word delete = new Word(word.toLowerCase()," "," ");

        dictionary[ index ].deleteNode(dictionary[ index ].findNode(delete));


        return response;
    }

    /**
     * This is a method for find words that writes similar but
     * has an accent of difference. So, the user must be able to create
     * similiar words only if the meaning is different.
     * @param word word to validate
     * @return true if there are a word that differs in an accent but keep a similar meaning.
     * False if there are no words similar.
     */
    private boolean hasSimilarWord( Word word ){
        String[][] words = this.listByFirstChar( word.getId().charAt(0));
        if ( words == null ) return false;
        for ( int i = 0; i < words.length; i++ ){
            if ( this.validateWord.cleanAccent(words[i][0]).compareTo(this.validateWord.cleanAccent(word.getId())) != 0) continue;
            if ( words[i][1].replaceAll(" ", "").replaceAll("\n", "").compareTo(word.getMeaning().replaceAll(" ", "").replaceAll("\n", "")) == 0) return true;
        }
        return false;
    }


}
