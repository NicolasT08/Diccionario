package co.edu.uptc.model;

import co.edu.uptc.model.TreeNode;
import java.util.*;

/**
 * Data Structure for handling words
 * The binary tree is able to order Objects
 * by a comparator
 * @author Nicolas Tinjaca
 * @param <T> Object Type
 */
public class BinaryTree <T>{
    private Comparator<T> comparator;

    private TreeNode<T> root;

    private LinkedList<T> list;

    /**
     * Create a new Binary Tree with using a
     * comparator
     * @param comparator used for order Objects
     */
    public BinaryTree(Comparator<T> comparator) {
        this.comparator = comparator;
        root = null;
    }

    /**
     * Checks if the Binary Tree is empty
     * @return true if the root is not null
     */
    public boolean isEmpty(){
        return root == null;
    }

    /**
     * Add a node according to the comparator
     * @param info Info to be added
     */
    public void addNode(T info){
        if( isEmpty() ){
            root = new TreeNode<>(info);
        } else {
            TreeNode<T> newNode = new TreeNode<>(info);
            TreeNode<T> actually =root;
            TreeNode<T> previous = root;
            while ( actually != null ){
                previous = actually;
                actually = comparator.compare(actually.getInfo(), info) > 0 ? actually.getLeft() : actually.getRight();
            }
            if ( comparator.compare(info, previous.getInfo()) < 0 ){
                previous.setLeft( newNode );
            }else {
                previous.setRight( newNode );
            }
        }
    }

    /**
     * Creates a list with all items in order
     * @return A proper LinkedList with the items in order
     */
    public LinkedList<T> listInsort(){
        list = new LinkedList<>();
        insort(root);
        return list;
    }

    /**
     * Collect the items and push in the
     * own LinkedList
     * @param root Beginning node
     */
    private void insort( TreeNode<T> root){
        if ( root != null ){
            insort(root.getLeft());
            list.add( root.getInfo() );

            insort(root.getRight());
        }
    }

    /**
     *Find the node that contains the info
     * @param info info to find
     * @return a node if some node has the info or null if this info doesn't exist
     */
    public TreeNode<T> findNode( T info ){
        TreeNode<T> aux = root;

        while ( aux != null && comparator.compare(info, aux.getInfo()) != 0 ){
            aux = comparator.compare(info, aux.getInfo()) < 0 ? aux.getLeft() : aux.getRight();
        }

        return aux;
    }

    /**
     * Find for the father of the node
     * @param node node to find its father
     * @return the father node or null if the node is the root
     */
    public TreeNode<T> findFather(TreeNode<T> node) {
        if ( node != root ){
            TreeNode<T> aux = root;
            while ( aux.getLeft() != node  && aux.getRight() != node ){
                aux = comparator.compare(node.getInfo(), aux.getInfo()) < 0 ? aux.getLeft() : aux.getRight();
            }
            return aux;
        }
        return null;
    }

    /**
     * Return the grade of a node. It means. the number of children
     * @param node node to count the children
     * @return the number of children of the node.
     */
    public byte gradeNode( TreeNode<T> node ){
        int grade = 0;
        if ( node.getLeft() != null ) grade++;
        if( node.getRight() != null ) grade++;
        return (byte)grade;
    }


    /**
     * Count how much levels the node is far from the root.
     * @param node node to count its levels
     * @return the levels far from the root node.
     */
    public int levelNode(TreeNode<T> node){
        return node == root ? 0 : levelNode( findFather(node) ) + 1;
    }

    /**
     * Remove and retrives the info from the tree.
     * @param node node to delete.
     * @return The contained info in the node.
     */
    public T deleteNode( TreeNode<T> node ){
        switch ( gradeNode(node) ){
            case 0:
                return deleteLeaf(node);
            case 1:
                return deleteWithSon(node);
            default:
                return deleteWithSons(node);
        }
    }

    /**
     * Delete and retrieves the info of a node with
     * two children
     * @param node node to be deleted
     * @return Contained info in the deleted node.
     */
    public T deleteWithSons(TreeNode<T> node) {
        T out = node.getInfo();

        if( node == root ){
            root = node.getLeft() != null ? node.getLeft() : node.getRight();
        }else {
            TreeNode<T> father = findFather( node );

            LinkedList<T> ordererlist = this.listInsort();

            T replaceinfo = ordererlist.get(ordererlist.getIndexOf(node.getInfo()) + 1);
            
            TreeNode<T> replace = this.findNode(replaceinfo);
            TreeNode<T> replaceFather = this.findFather(replace);

            if (replaceFather != node) {
                replaceFather.setLeft(replace.getRight());
                replace.setRight(node.getRight());
            }

            replace.setLeft(node.getLeft());

            if (father.getLeft() == node) {
                father.setLeft(replace);
            } else {
                father.setRight(replace);
            }

        }

        return out;
    }

    /**
     * Delete and retrieves the info of a node with
     * one child
     * @param node node to be deleted
     * @return Contained info in the deleted node.
     */
    public T deleteWithSon(TreeNode<T> node) {
        T out = node.getInfo();

        if( node == root ){
            root = node.getLeft() != null ? node.getLeft() : node.getRight();
        }else {
            TreeNode<T> father = findFather( node );

            if( father.getRight() == node ){
                father.setRight( node.getLeft() != null ?  node.getLeft() : node.getRight() );
            } else {
                father.setLeft( node.getLeft() != null ?  node.getLeft() : node.getRight() );
            }
        }
        return out;
    }

    /**
     * Delete and retrieves the info of a node with
     * no children
     * @param node node to be deleted
     * @return Contained info in the deleted node.
     */
    public T deleteLeaf(TreeNode<T> node) {
        T out = node.getInfo();

        if( node == root ){
            root = null;
        } else {
            TreeNode<T> father = findFather( node );
            if( node == father.getLeft() ) father.setLeft( null );
            if ( node == father.getRight() ) father.setRight( null );
        }
        return out;
    }

    /**
     *
     * @return Number of the nodes in the tree
     */
    public int weightTree(){
        return weight( root );
    }

    /**
     * @param node
     * @return Number of the nodes under the node.
     */
    private int weight( TreeNode<T> node){

        if( node == null ){
            return 0;
        } else {
            return weight( node.getLeft() ) + weight( node.getRight() ) + 1;
        }
    }
}
