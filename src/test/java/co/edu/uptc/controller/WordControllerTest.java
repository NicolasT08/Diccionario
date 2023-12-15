package co.edu.uptc.controller;

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

    @Test
    void findWord(){
        setup();

        assertNull(controller.findWord("Paranguaricutirimicuaro"));

        controller.addWord("hu tao","La asesina de bosses","the boss killer");

        String[] huTao = {"hu tao","la asesina de bosses","the boss killer"};

        assertArrayEquals(huTao, controller.findWord("hu tao"));

        assertArrayEquals(huTao, controller.findWord("Hu tao"));

        controller.addWord("habitar","Residir o vivir en un lugar","to inhabit");

        String[] inhabit = {"habitar","Residir o vivir en un lugar","to inhabit"};

        assertArrayEquals(inhabit, controller.findWord("habitar"));

        assertArrayEquals(inhabit, controller.findWord("habitar"));

        assertNull(controller.findWord("alfredo"));

    }

    @Test
    void showAllWords(){
        setup();

        controller.addWord("habitar","Residir o vivir en un lugar","to inhabit");

        String[] inhabit = {"habitar","Residir o vivir en un lugar","to inhabit"};

        assertArrayEquals(inhabit, controller.showAllWords()[0]);

        controller.addWord("hu tao","la asesina de bosses","the boss killer");

        String[] huTao = {"hu tao","la asesina de bosses","the boss killer"};

        assertArrayEquals(huTao, controller.showAllWords()[1]);

        controller.addWord("ígneo","Relativo al fuego o que tiene origen en el fuego","igneous");

        String[] igneous = {"ígneo","Relativo al fuego o que tiene origen en el fuego","igneous"};

        assertArrayEquals(igneous, controller.showAllWords()[2]);
    }

    @Test
    void deleteWord(){
        setup();

        assertEquals(1 , controller.showAllWords().length);

        controller.addWord("Hu tao","la asesina de bosses","the boss killer");

        String[] huTao = {"hu tao","la asesina de bosses","the boss killer"};

        assertArrayEquals(huTao, controller.deleteWord("Hu tao"));

        assertNull(controller.deleteWord("goku"));

        assertEquals(0 , controller.showAllWords().length);

    }
}
