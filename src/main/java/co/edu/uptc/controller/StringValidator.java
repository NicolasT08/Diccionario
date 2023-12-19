package co.edu.uptc.controller;

import co.edu.uptc.exceptions.ERROR_REASON;
import co.edu.uptc.exceptions.InvalidWord;

/**
 * This class is for validate
 * strings that will be components
 * of a Word
 * @author Nicolas Sarmiento
 */
public class StringValidator {
    public StringValidator() {}

    /**
     * This method validates if
     * a string is null
     * @param field String of the word's field
     *
     */
    void isNullField( String field ){
        if ( field == null ) throw new InvalidWord( field, ERROR_REASON.IS_EMPTY);
    }

    /**
     * This method validates info of the word itself.
     * A word cannot contain spaces,special characters
     * or numbers. The method throws InvalidWord exception
     * if the word is not allowed
     * @param word word to validate
     */
    void validateWord( String word ){
       this.isNullField( word );
       if ( word.isBlank() ) throw new InvalidWord( word, ERROR_REASON.IS_EMPTY);
       for( int i = 0; i < word.length(); i++ ){
            if ( !Character.isAlphabetic(word.charAt(i))) throw new InvalidWord( word, ERROR_REASON.INVALID_CHARACTERS);
       }
    }

    /**
     * This method validates a word's meaning.
     * The meaning can contains a certain type of
     * characters. The method will throw InvalidWord
     * exception if the word is invalid.
     * @param content meaning to validate.
     */
    void validateMeaning( String content ){
        this.isNullField( content );
        if ( content.isBlank() ) throw new InvalidWord( content, ERROR_REASON.IS_EMPTY);
        if ( content.startsWith(" ")) throw new InvalidWord( content, ERROR_REASON.STARTS_SPACE);

        String aux = validateAllowCharacters( content );
        if ( aux.isBlank() ) throw new InvalidWord( content, ERROR_REASON.INVALID_CHARACTERS);
        for( int i = 0; i < aux.length(); i++ ){
            if ( !Character.isAlphabetic(aux.charAt(i))) throw new InvalidWord( content, ERROR_REASON.INVALID_CHARACTERS);
        }
    }

    /**
     * Clean the allow characters. Those are
     * comma, colon, semicolon,dots, exclamation
     * and question marks. The method clean the valid
     * characters.
     * @param content String to be validate
     * @return a clean String without the allowed characters.
     */
    private String validateAllowCharacters( String content ){
        content = content.replaceAll("[0-9]", "");
        String[] allowed = { "\\.",",",":",";","\\?","¿","!","¡","\\(","\\)","\\'","\""," ","\n"};
        for ( String allow : allowed ){
            content = content.replaceAll( allow, "");
        }
        return content;
    }


    /**
     * This method validates a translation word.
     * A translation only can contain alpha
     * characters and spaces.
     * @param translation translation to validate.
     */
    void validateTranslation( String translation ){
        this.isNullField( translation );
        if ( translation.isBlank() ) throw new InvalidWord( translation, ERROR_REASON.IS_EMPTY);
        if ( translation.startsWith(" ")) throw new InvalidWord( translation, ERROR_REASON.STARTS_SPACE);
        for( int i = 0; i < translation.length(); i++ ){
            if ( translation.charAt(i) == ' ') continue;
            if ( !Character.isAlphabetic(translation.charAt(i))) throw new InvalidWord( translation, ERROR_REASON.INVALID_CHARACTERS);
        }
    }

    /**
     * This method removes accent vowels from
     * words.
     * @param str string to remove accent vowels
     * @return a string without accent vowels
     */
    public String cleanAccent( String str ){
        return str.replaceAll("á","a").replaceAll("é","e").replaceAll("í","i").replaceAll("ó","o").replaceAll("ú","u");
    }
}
