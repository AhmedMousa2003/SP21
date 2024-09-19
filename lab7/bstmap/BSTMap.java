package bstmap;

import bstmap.Map61B;

import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;

public class BSTMap<k extends Comparable<k> , v> implements Map61B<k, v> {

    private Node root;

    private class Node{
        public Node left , right;
        private k key;
        private v val;
        private int sz;
        public Node(k key, v val, int sz){
            this.key = key;
            this.val = val;
            this.sz = sz;
        }
    }

    /** Clear the BSTMap */
    @Override
    public void clear(){
        clear(this.root);
    }

    private void clear(Node T){
        if (T == null) return;
        clear(T.left);
        T.left = null;
        clear(T.right);
        T.right = null;
    }

    /** Return true if the current key can be found in the BSTMap */
    @Override
    public boolean containsKey(k key){
        return get(key) != null;
    }

    /** Return the mapped value of a certain key */
    @Override
    public v get(k key){
        if (key == null)
            return null;
        return get(this.root, key);
    }

    private v get(Node T, k key){
        if (T == null) return null;
        int comp = key.compareTo(T.key);
        if (comp == 0){
            return T.val;
        }else if (comp < 0){
            return get(T.left, key);
        }else {
            return get(T.right, key);
        }
    }

    /** Return the size of the BSTMap */
    @Override
    public int size(){
        return this.root.sz;
    }

    /** Map key with a certian value val */
    @Override
    public void put (k key, v val){
        if (key == null || val == null)  return;
        root = put(this.root, key, val);
    }

    private Node put(Node T, k key, v val){
        if (T == null) return new Node(key, val, 1);
        int comp = key.compareTo(T.key);
        if (comp == 0){
            T.val = val;
        }else if (comp < 0){
            T.left = put(T.left, key, val);
        }else {
            T.right = put(T.right, key, val);
        }
        T.sz = getSz(T.left) + getSz(T.right) + 1;
        return T;
    }

    private int getSz(Node T){
        if (T == null) return 0;
        return T.sz;
    }

    @Override
    public Set<k> keySet(){
        throw new UnsupportedOperationException("Not supported yet.");
    };

    @Override
    public v remove(k key){
        v ret = get(key);
        root = remove(root, key);
        return ret;
    }


    /** Remove the node with a certain key if found in the tree */

    private Node remove(Node T, k key){
        if (T == null) return null;
        int comp = key.compareTo(T.key);
        if (comp < 0) {
            T.left = remove(T.left, key);
        }else if (comp > 0)
            T.right = remove(T.right, key);
        else {
            if (T.left == null)
                return T.right;
            if (T.right == null)
                return T.left;
            Node cur = T;
            T = getMin(cur.right);
            T.right = removeMin(cur.right);
            T.left = cur.left;
        }
        T.sz = getSz(T.left) + getSz(T.right) + 1;
        return T;
    }

    private Node removeMin(Node T){
        if (T.left == null) return T.right;
        T.left = removeMin(T.left);
        T.sz = getSz(T.left) + getSz(T.right) + 1;
        return T;
    }

    private Node getMin(Node T){
        if (T.left == null) return T;
        return getMin(T.left);
    }

    private Node getSuccessor(Node T){
        if (T.left == null) return T;
        return getSuccessor(T.left);
    }
    @Override
    public v remove(k key, v value){
        if (get(key) == value)
            return remove(key);
        return null;
    }


    @Override
    public Iterator<k> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /** print the key and value in order */
    public void printInOrder(){
        printInOrder(root);
    }

    private void printInOrder(Node T){
        if (T == null) return ;
        printInOrder(T.left);
        System.out.println(T.key + " " + T.val);
        printInOrder(T.right);
    }

    public static void main(String[] args){
        BSTMap<String, Integer> cur = new BSTMap<>();

        cur.put("Ahmed", 50);
        cur.put("Mahmoud", 200);
        cur.printInOrder();
        cur.remove("Mahmoud");
        cur.printInOrder();
    }

}