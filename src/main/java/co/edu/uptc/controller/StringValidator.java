package co.edu.uptc.controller;

import co.edu.uptc.exceptions.ERROR_REASON;
import co.edu.uptc.exceptions.InvalidWord;

public class StringValidator {
    public StringValidator() {
    }

    void isNullField( String field ){
        if ( field == null ) throw new InvalidWord( field, ERROR_REASON.IS_EMPTY);
    }
    void validateWord( String word ){
       this.isNullField( word );
        for( int i = 0; i < word.length(); i++ ){
            if ( !Character.isAlphabetic(word.charAt(i))) throw new InvalidWord( word, ERROR_REASON.INVALID_CHARACTERS);
        }
    }
    void validateMeaning( String content ){
        this.isNullField( content );
        if ( content.isBlank() ) throw new InvalidWord( content, ERROR_REASON.IS_EMPTY);
        if ( content.startsWith(" ")) throw new InvalidWord( content, ERROR_REASON.STARTS_SPACE);
    }

    void validateTranslation( String translation ){
        this.isNullField( translation );
        if ( translation.startsWith(" ")) throw new InvalidWord( translation, ERROR_REASON.STARTS_SPACE);
        for( int i = 0; i < translation.length(); i++ ){
            if ( translation.charAt(i) == ' ') continue;
            if ( !Character.isAlphabetic(translation.charAt(i))) throw new InvalidWord( translation, ERROR_REASON.INVALID_CHARACTERS);
        }
    }

    String cleanAccent( String str ){
        return str.replaceAll("á","a").replaceAll("é","e").replaceAll("í","i").replaceAll("ó","o").replaceAll("ú","u");
    }
}
