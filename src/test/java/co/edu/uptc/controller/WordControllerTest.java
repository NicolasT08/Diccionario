package co.edu.uptc.controller;

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
}
