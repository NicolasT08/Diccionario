package co.edu.uptc.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {
    private BinaryTree<Integer> bts;

    private BinaryTree<Word> words;

    void setup(){
        bts = new BinaryTree<>(((o1, o2) -> Integer.compare(o1,o2)));
        bts.addNode(50);
        bts.addNode(10);
        bts.addNode(69);
        bts.addNode(5);
        bts.addNode(34);
        bts.addNode(57);
        bts.addNode(18);
        bts.addNode(40);
        bts.addNode(63);
    }

    void setupOne(){
        words = new BinaryTree<>(((o1, o2) -> o1.getId().compareTo(o2.getId())));
        words.addNode( new Word("uwu", "Emoticono de ternura", "uwu"));
    }



    @Test
    void listInsort(){
        setup();
        LinkedList<Integer> list = bts.listInsort();
        assertEquals(5,list.get(0));
        assertEquals(69,list.get(8));
        assertEquals(50,list.get(5));
    }

    @Test
    void findNode(){
        setup();
        assertNotNull( bts.findNode(50));
        assertNotNull( bts.findNode(10));
        assertNotNull( bts.findNode(69));
        assertNotNull( bts.findNode(5));
        assertNotNull( bts.findNode(34));
        assertNotNull( bts.findNode(57));
        assertNotNull( bts.findNode(18));
        assertNotNull( bts.findNode(40));
        assertNotNull( bts.findNode(63));
        assertNull(bts.findNode(456));
        assertNull(bts.findNode(89));
        assertNull(bts.findNode(132));

        setupOne();
        //assertNotNull( dishes.findNode( new Dish(null,"Carne en Bisteck",0.0,0,false)));
        //assertNull( dishes.findNode( new Dish(null,"Carnex",0.0,0,false)));
    }

    @Test
    void findFather(){
        setup();
        assertNull(bts.findFather( bts.findNode( 50 ) ) );
        assertNotNull( bts.findFather( bts.findNode(10)));
        assertNotNull( bts.findFather( bts.findNode(69)));
        assertEquals(50, bts.findFather(bts.findNode(10)).getInfo());
        assertEquals(50, bts.findFather(bts.findNode(69)).getInfo());
        assertEquals(57, bts.findFather(bts.findNode(63)).getInfo());
    }

    @Test
    void deleteNode(){
        setup();
        //Eliminar Hojas
        //assertEquals(9, bts.weightTree());
        assertEquals(2, bts.gradeNode( bts.findNode( 10 ) ) ) ;
        assertEquals(5, bts.deleteNode( bts.findNode( 5 ) ) );
        assertEquals(1, bts.gradeNode( bts.findNode( 10 ) ) ) ;
        assertNull( bts.findNode(5));
        //assertEquals(8, bts.weightTree());

        //Eliminar Nodos con un hijo
        assertEquals(3, bts.levelNode( bts.findNode(63)));
        assertEquals( 57, bts.findFather( bts.findNode( 63)).getInfo());
        assertEquals( 57,bts.deleteNode( bts.findNode( 57 ) ) );
        assertEquals( 69, bts.findFather( bts.findNode( 63 ) ).getInfo() );
        assertEquals(2, bts.levelNode( bts.findNode(63)));

        //Eliminat Nodos con dos hijos
        //Eliminar el nodo 10

        setup();
        assertEquals(34, bts.findFather( bts.findNode(18)).getInfo());
        assertEquals(2,bts.gradeNode( bts.findNode( 34) ) );
        assertEquals(10,bts.deleteNode( bts.findNode(10)));
        assertNull(this.bts.findNode(10));
        assertEquals(50, bts.findFather( bts.findNode(18)).getInfo());
        assertEquals(1,bts.gradeNode( bts.findNode( 34) ) );


        setupOne();
//        Dish dish = dishes.deleteNode( dishes.findNode( new Dish( "345",null,0.0,0,false ) ) );
//
//        assertEquals("345", dish.getId());
//        assertEquals("Carne en Bisteck", dish.getName());
    }
}
