package co.edu.uptc.exceptions;

public class InvalidWord extends IllegalArgumentException {
    private static final String EXITS = " ya existe!";
    private static final String IS_EMPTY = "Las palabras y definiciones no pueden estar vacías!!";
    private static final String INVALID_CHARACTERS = " debe contener solamente caracteres alfabéticos!";
    private static final String STARTS_SPACE = " empieza con espacios!";
    private static final String IMPOSSIBLE = " no pertenece a la lengua castellana";

    private static String getMessage( String word, ERROR_REASON reason ){
        String message = "";
        switch ( reason ){
            case EXIST -> message = "La palabra \"" + word + "\" " + EXITS;
            case IS_EMPTY -> message = IS_EMPTY;
            case STARTS_SPACE -> message = "La palabra \"" + word + "\" " +  STARTS_SPACE;
            case INVALID_CHARACTERS ->  message = "La palabra \"" + word + "\" " + INVALID_CHARACTERS;
            case IMPOSSIBLE -> message =  "La palabra \"" + word + "\" " + IMPOSSIBLE;
        }
        return message;
    }
    public InvalidWord( String word , ERROR_REASON reason){
        super( getMessage(word, reason) );
    }

}
