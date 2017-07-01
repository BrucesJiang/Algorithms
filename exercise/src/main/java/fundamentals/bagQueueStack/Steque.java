/***************************************
 *  Compilation:   javac Steque.java
 *  Execution:     java Steque < input.txt
 *  Dependencies:   StdOut.java StdIn.java
 *
 *  % java Steque
 *   a b c 1 3 - b d 3 4 5
 *   Output:
 *   3
 *   5 4 3 1 a b c b d
 *
 ******************************************/

package fundamentals.bagQueueStack;

import util.api.StdIn;
import util.api.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @auther Bruce Jiang
 */
public class Steque<I> implements Iterable<I> {

    private Node<I> first; //the first item
    private Node<I> last; // the last item
    private int size; // number of items in the queue

    private static class Node<I>{
        private I item;
        private Node<I> next;
        public Node(I item, Node<I> next){
            this.item = item;
            this.next = next;
        }
    }

    /**
     * Initializes an empty stack queue
     */
    public Steque(){
        Node<I> node = new Node<I>(null, null);
        first = last = node;
        size = 0;
    }

    /**
     * Return the size of the stack queue
     *
     * @return the size of the stack queue
     */
    public int size(){
        return size;
    }

    /**
     * Returns the stack queue whether empty or not
     *
     * @return the stack queue whether empty or not
     */
    public boolean isEmpty(){
        return size == 0;
    }


    /**
     * Add an item into the stack at the first
     * @param item the item to be added
     */
    public void push(I item){
        Node<I> node = new Node<I>(item, first.next);
        first.next = node;
        size ++;
    }

    /**
     * Add an item into the queue at last
     * @param item the item to be added
     */
    public void enqueue(I item){
        Node<I> node = new Node<I>(item, null);
        last.next = node;
        last = node;
        size ++;
    }

    /**
     * Returns and removes an item at the top of the stack
     *
     * @return an item at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    public I pop(){
        if(isEmpty()) throw new NoSuchElementException("Container underflow");
        I item = first.next.item;
        first = first.next;
        size--;
        if(isEmpty()) last = null;
        return item;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(I item : this){
            sb.append(item + " ");
        }
        return sb.toString();
    }


    public Iterator<I> iterator() {
        return new ListIterator<I>(first.next);
    }

    private class ListIterator<I> implements Iterator<I>{
        private Node<I> current;
        public ListIterator(Node<I> node){
            this.current = node;
        }

        public boolean hasNext() {
            return current == null;
        }

        public I next() {
            if(!hasNext()) throw new NoSuchElementException("Container underflow");
            I item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("Unsupport Operation");
        }
    }


    /**
     * Unit test the {@code Steque} data type
     *
     * @param args command-line args
     */
    public static void main(String[] args){
        Steque<String> sq = new Steque<String>();

        while(!StdIn.isEmpty()){
            String str = StdIn.readString();
            if(str.equals("-")){
                StdOut.println(sq.pop());
            }else if(str.matches("\\d")){
                sq.push(str);
            }else{
                sq.enqueue(str);
            }
        }
        StdOut.println(sq.toString());
    }
}
