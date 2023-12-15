package co.edu.uptc.model;

public class LinkedList <T>{
    private ListNode<T> head;

    public LinkedList() {
        this.head = null;
    }

    public boolean isEmpty(){
        return head == null;
    }
    public void add(T info){
        if( this.isEmpty() ){
            this.head = new ListNode<>( info );
            return;
        }

        ListNode<T> node = new ListNode<>( info );
        ListNode<T> aux = head;
        while ( aux.getNext() != null ){
            aux = aux.getNext();
        }

        aux.setNext(node);

    }
    public T get( int index ) throws IndexOutOfBoundsException{
        if ( index < 0 || index >= this.size() ) throw new IndexOutOfBoundsException();
        int i = 0;
        ListNode<T> aux = head;
        while ( aux != null){
            if( i == index) return aux.getInfo();
            aux = aux.getNext();
            i++;
        }
        return null;
    }

    public int size(){
        int size = 0;
        ListNode<T> aux = head;
        while ( aux != null ){
            size++;
            aux = aux.getNext();
        }
        return size;
    }

}
