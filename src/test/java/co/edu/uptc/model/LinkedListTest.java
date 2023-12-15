package co.edu.uptc.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {
    private LinkedList<Integer> listInts;
    private LinkedList<String> words;


    @BeforeEach
    void setup(){
        this.listInts = new LinkedList<>();
        this.listInts.add(0);
        this.listInts.add(1);
        this.listInts.add(2);
        this.listInts.add(3);
        this.listInts.add(4);
        this.listInts.add(5);
        this.listInts.add(6);
        this.listInts.add(7);
        this.listInts.add(8);

        this.words = new LinkedList<>();
        this.words.add("Hola");
        this.words.add("Hu Tao");
        this.words.add("Ganyu");
        this.words.add("Yae Miko");
        this.words.add(" ");
        this.words.add("Nada");
        this.words.add("Hada");
    }

    @Test
    void isEmptyTest(){
        assertFalse( this.listInts.isEmpty());
        LinkedList<String> strings = new LinkedList<>();
        assertTrue( strings.isEmpty());
    }

    @Test
    void sizeTest(){
        assertEquals(9, this.listInts.size());

        LinkedList<Integer> empty = new LinkedList<>();
        assertEquals(0, empty.size());

        LinkedList<Integer> oneHundredNums = new LinkedList<>();
        for ( int i = 0; i < 100; i++ ){
            oneHundredNums.add(i);
        }
        assertEquals(100, oneHundredNums.size());

    }

    @Test
    void getTest(){
        for ( int i = 0; i < this.listInts.size(); i++ ){
            assertEquals(i, this.listInts.get(i));
        }

        String[] expectedWords = {"Hola", "Hu Tao", "Ganyu","Yae Miko"," ","Nada", "Hada" };
        for ( int i = 0; i < expectedWords.length; i++ ){
            assertEquals(expectedWords[i] , this.words.get(i));
        }

        assertEquals(4, this.listInts.get(this.listInts.size()/2));

        assertDoesNotThrow( () -> this.listInts.get(4));
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> this.listInts.get(10)
        );
        assertThrows(
                IndexOutOfBoundsException.class,
                ()-> this.words.get(-2)
        );

    }

    @Test
    void addTest(){
        LinkedList<Integer> list = new LinkedList<>();
        assertEquals(0, list.size());
        list.add(3);
        assertEquals(1, list.size());
        list.add(30);
        assertEquals(2, list.size());

        assertDoesNotThrow( ()-> list.add(2000));

        assertThrows(
                NullPointerException.class,
                () -> list.add(null)
        );
    }

    @Test
    void getIndexOfTest(){
        for ( int i = 0; i < this.listInts.size(); i++ ){
            assertEquals(i, this.listInts.getIndexOf(i));
        }

        String[] expectedWords = {"Hola", "Hu Tao", "Ganyu","Yae Miko"," ","Nada", "Hada" };
        for ( int i = 0; i < expectedWords.length; i++ ){
            assertEquals(i  , this.words.getIndexOf(expectedWords[i]));
        }

        assertEquals(-1, this.words.getIndexOf("Amber"));
        assertEquals(-1, this.words.getIndexOf("Kokomi"));

        assertThrows(
                NullPointerException.class,
                () -> this.words.getIndexOf(null)
        );

        assertThrows(
                NullPointerException.class,
                () -> this.listInts.getIndexOf(null)
        );

    }




}
