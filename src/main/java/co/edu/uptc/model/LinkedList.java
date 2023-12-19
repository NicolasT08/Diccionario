package co.edu.uptc.model;

/**
 * Collection for only store info and get
 * by a index position
 * @author Nicolas Sarmiento
 * @param <T> Object Type
 */
public class LinkedList <T>{
    private ListNode<T> head;

    /**
     * Create a empty LinkedList
     */
    public LinkedList() {
        this.head = null;
    }

    /**
     *
     * @return true if the List is empty
     */
    public boolean isEmpty(){
        return head == null;
    }

    /**
     * Add a element at the end of the list
     * @param info info to be added
     */
    public void add(T info){
        if ( info == null ) throw new NullPointerException();
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

    /**
     * Returns the object at the specified position
     * @param index position
     * @return Object at the position
     * @throws IndexOutOfBoundsException when the index is greater or equal
     * to the LinkedList's size or if the index is negative.
     */
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

    /**
     * Return the position of a specified info
     * @param info info to be found
     * @return the index position of the info
     */
    public int getIndexOf( T info ){
        if ( info == null ) throw new NullPointerException();
        int index = 0;
        ListNode<T> aux = head;
        while ( aux != null ){
            if ( aux.getInfo() == info ) return index;
            aux = aux.getNext();
            index++;
        }
        return -1;
    }


    /**
     * Return the size of the LinkedList
     * @return the size of the LinkedList
     */
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
