package bstmap;

import bstmap.Map61B;

import java.util.*;
import java.util.function.Consumer;

public class BSTMap<K extends Comparable<K> , V> implements Map61B<K, V> {

    private Node root;

    /** Required data for each Node */
    private class Node{
        public Node left , right;
        private K key;
        private V val;
        private int sz;
        public Node(K key,V val, int sz){
            this.key = key;
            this.val = val;
            this.sz = sz;
        }
    }

    /** Clear the BSTMap */
    @Override
    public void clear(){
        clear(this.root);
        root = null;
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
    public boolean containsKey(K key){
        return get(key) != null;
    }

    /** Return the mapped value of a certain key */
    @Override
    public V get(K key){
        if (key == null)
            return null;
        return get(this.root, key);
    }

    private V get(Node T, K key){
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
        return getSz(root);
    }

    /** Map key with a certian value val */
    @Override
    public void put (K key, V val){
        if (key == null || val == null)  return;
        root = put(this.root, key, val);
    }

    private Node put(Node T, K key, V val){
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

    /** Get the size of the subtree of a certain node in the BST */
    private int getSz(Node T){
        if (T == null) return 0;
        return T.sz;
    }

    /** Return a set of keys included in the BST */
    @Override
    public Set<K> keySet(){
        Set<K> keys = new TreeSet<>();
        addToSet(root, keys);
        return keys;
    }

    private void addToSet(Node T, Set<K> keys){
        if (T == null) return;
        addToSet(T.left, keys);
        keys.add(T.key);
        addToSet(T.right, keys);
    }

    /** Remove the node with a certain key if found in the tree and corresponding methods */

    @Override
    public V remove(K key){
        V ret = get(key);
        root = remove(root, key);
        return ret;
    }

    private Node remove(Node T, K key){
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
    public V remove(K key, V value){
        if (get(key) == value)
            return remove(key);
        return null;
    }


    /** Return an iterator class to make iterable */
    @Override
    public Iterator<K> iterator() {
        return new getIterator();
    }

    private class getIterator implements Iterator<K>{
        Queue<K> queue;
        public getIterator(){
            queue = this.getQueue();
        }

        private Queue<K> getQueue(){
            if (root.sz == 0)
                return new LinkedList<>();
            Queue<K> queue = new LinkedList<>();
            getQueue(root, queue);
            return queue;
        }

        private void getQueue(Node T, Queue<K> queue){
            if (T == null)
                return;
            getQueue(T.left, queue);
            queue.add(T.key);
            getQueue(T.right, queue);
        }

        public boolean hasNext(){
            return !queue.isEmpty();
        }

        public K next(){
            return queue.remove();
        }


    }

    private void getQueue(Node T, Queue<K> queue){
        if (T == null) return;
        getQueue(T.left, queue);
        queue.add(T.key);
        getQueue(T.right, queue);
    }

    private boolean isEmpty(){
        return root.sz == 0;
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
    
}