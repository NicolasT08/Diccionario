package co.edu.uptc.model;

/**
 * This Object contains info
 * and a reference to a next object in
 * a list
 * @author Nicolas Sarmiento
 * @param <T> Object Type
 */
public class ListNode <T> {
    private ListNode<T> next;
    private T info;

    public ListNode(T info) {
        this.info = info;
    }

    public ListNode<T> getNext() {
        return next;
    }

    public void setNext(ListNode<T> next) {
        this.next = next;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }
}
