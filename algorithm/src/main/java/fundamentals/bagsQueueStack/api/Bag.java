package fundamentals.bagsQueueStack.api;

import util.api.StdIn;
import util.api.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code Bag} class represents a bag (or multiset) of generic items. It supports insertion and iterating
 * over the items in arbitrary order.
 *
 * @auther Bruce Jiang
 */
public class Bag<I> implements Iterable<I>{
    private Node<I> first;
    private int size; // number of elements in the bag

    private static class Node<I>{
        private I item;
        private Node<I> next;
        public Node(I item, Node<I> next){
            this.item = item;
            this.next = next;
        }
    }

    /**
     * Initializes an empty bag
     */
    public Bag(){
        this.first = null;
        size = 0;
    }

    /**
     * Return whether the bag is empty or not
     *
     * @return {@code true} if the bag is empty
     *          {@code false} otherwise
     */
    public boolean isEmpty(){
        return first == null;
    }

    /**
     * Returns the size of the bag
     *
     * @return the size of the bag
     */
    public int size(){
        return size;
    }

    /**
     * Add an item into the bag
     *
     * @param item the bag to added
     */
    public void add(I item){
        Node<I> node = new Node<I>(item, first);
        first = node;
        size ++;
    }

    /**
     * Returns an iterator taht iterates over the items in this bag in arbitrary order
     *
     * @return an iterator that iterates over the items in this bag in the arbitrary order
     */
    @Override
    public Iterator<I> iterator() {
        return null;
    }

    private class ListIterator<I> implements Iterator<I>{
        private Node<I> current;
        public ListIterator(Node first){
            this.current = first;
        }

        @Override
        public boolean hasNext() {
            return first == null;
        }

        @Override
        public I next() {
            if(!hasNext()) throw new NoSuchElementException("Bag Underflow");
            I item = current.item;
            first = first.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Unsupport this operation");
        }
    }


    /**
     * Unit test the {@code Bag} data type
     *
     * @param args the command-line args
     */
    public static void main(String[] args){
        Bag<String> bag = new Bag<String>();
        while(!StdIn.isEmpty()){
            String str = StdIn.readString();
            bag.add(str);
        }
        StdOut.println("size of bag = " + bag.size());
        for (String s : bag) {
            StdOut.println(s);
        }
    }
}
