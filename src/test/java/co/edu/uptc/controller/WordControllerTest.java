package co.edu.uptc.controller;

import co.edu.uptc.exceptions.InvalidWord;
import co.edu.uptc.model.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WordControllerTest {
    private WordController controller;
    @BeforeEach
    void setup(){
        this.controller = new WordController();
    }
    @Test
    void findWordIndex(){
        try{
            String[] words = {"Amor", "Brillante", "Cielo", "Delfín", "Estrella", "Felicidad", "Gracia", "Harmonía", "Ilusión", "Jardín", "Kilo", "Luna", "Mariposa", "Naturaleza","Ñoño", "Océano", "Paz", "Quimera", "Resplandor", "Serenidad", "Tesoro", "Universo", "Viaje", "Waffles", "Xilófono", "Yunque", "Zafiro"};
            for( int i = 0; i < words.length; i++ ){
                assertEquals(i, this.controller.findIndex(words[i]));
            }

            String[] lowerWords = {"a", "brillante", "cielo", "delfín", "ensueño", "feli", "gomelo", "Hu Tao", "imaginario", "jardín", "karma", "luna", "mariposa", "niñez","ñandú", "océano", "paz", "quietud", "resplandor", "serenidad", "tesoro", "universo", "viaje", "waffles", "xenón", "yoga", "zafiro"};
            for ( int i = 0; i < lowerWords.length; i++ ){
                assertEquals(i, this.controller.findIndex(lowerWords[i]));
            }

            String[] accent = {"época", "íntimo", "óleo", "último", "árbol", "éxito", "óvalo", "última", "índigo", "ámbar"};
            int[] values = {4,8,15,21,0,4,15,21,8,0};
            for( int i = 0; i < accent.length; i++ ){
                assertEquals(values[i], this.controller.findIndex(accent[i]));
            }

            accent = new String[]{"Época", "Íntimo", "Óleo", "Último", "Árbol", "Éxito", "Óvalo", "Última", "Índigo", "Ámbar"};
            values = new int[]{4,8,15,21,0,4,15,21,8,0};
            for( int i = 0; i < accent.length; i++ ){
                assertEquals(values[i], this.controller.findIndex(accent[i]));
            }
        }catch ( Exception e){

        }


         assertThrows(
                IllegalArgumentException.class,
                () -> this.controller.findIndex("%#!@")
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> this.controller.findIndex("  ")
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> this.controller.findIndex("ütao")
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> this.controller.findIndex("[]")
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> this.controller.findIndex("%#!@")
        );
        assertDoesNotThrow(() -> this.controller.findIndex("Hola"));
        assertDoesNotThrow(() -> this.controller.findIndex("euler"));
    }

    @Test
    void addWordTest(){
        Word[] words ={
                new Word("uwu", "emoticono tierno", "uwu"),
                new Word("Árbol", "planta", "tree"),
                new Word("agua", "líquido", "wa ter"),
                new Word("Madera", "material de árboles", "wood"),
                new Word("Salir", "Ir a fuera", "Go Out")

        };
        for ( Word w : words ){
            assertEquals( "La palabra " + w.getId() + " ha sido añadida satisfactoriamente", this.controller.addWord(w.getId(), w.getMeaning(), w.getTranslation()));
        }

        for ( Word w : words ){
            assertEquals( "La palabra \"" + w.getId() + "\" " + " ya existe!", this.controller.addWord(w.getId(), w.getMeaning(), w.getTranslation()));
        }

        Word[] invalidWords = {
                new Word("1", "uno", "one"),
                new Word("$tos", "palabra grosera", "rude"),
                new Word("µvaca", "vaquita", "cow"),
                new Word("µvaca", "vaquita", "cow"),
                new Word("pera", "     ", " "),
                new Word("uva", "fruta morada.", " "),
                new Word(null, "fruta morada.", "grape"),
                new Word(" paz", "fruta morada.", "grape"),
                new Word("paz", "  fruta morada.", "grape"),
                new Word("paz", "Tranquilidad", "  peace"),
                new Word("manzana",  null, "apple"),
                new Word(null,  null, null),
                new Word("martinez",  "", ""),
                new Word("estadística",  "", "stadistics"),
        };

        for ( Word w : invalidWords ){
            assertNotEquals("La palabra " + w.getId() + " ha sido añadida satisfactoriamente", this.controller.addWord(w.getId(), w.getMeaning(), w.getTranslation()));
        }
    }
}

