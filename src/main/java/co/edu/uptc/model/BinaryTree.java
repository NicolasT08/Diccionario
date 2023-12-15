package co.edu.uptc.model;

import co.edu.uptc.model.TreeNode;
import java.util.*;

public class BinaryTree <T>{
    private Comparator<T> comparator;

    private TreeNode<T> root;

    private LinkedList<T> list;

    public BinaryTree(Comparator<T> comparator) {
        this.comparator = comparator;
        root = null;
    }

    public boolean isEmpty(){
        return root == null;
    }

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


    public LinkedList<T> listInsort(){
        list = new LinkedList<>();
        insort(root);
        return list;
    }
    private void insort( TreeNode<T> root){
        if ( root != null ){
            insort(root.getLeft());
            list.add( root.getInfo() );

            insort(root.getRight());
        }
    }
    public TreeNode<T> findNode( T info ){
        TreeNode<T> aux = root;

        while ( aux != null && comparator.compare(info, aux.getInfo()) != 0 ){
            aux = comparator.compare(info, aux.getInfo()) < 0 ? aux.getLeft() : aux.getRight();
        }

        return aux;
    }

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

    public byte gradeNode( TreeNode<T> node ){
        int grade = 0;
        if ( node.getLeft() != null ) grade++;
        if( node.getRight() != null ) grade++;
        return (byte)grade;
    }


    public int levelNode(TreeNode<T> node){
        return node == root ? 0 : levelNode( findFather(node) ) + 1;
    }

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
    public int weightTree(){
        return weight( root );
    }
    private int weight( TreeNode<T> node){

        if( node == null ){
            return 0;
        } else {
            return weight( node.getLeft() ) + weight( node.getRight() ) + 1;
        }
    }
}
