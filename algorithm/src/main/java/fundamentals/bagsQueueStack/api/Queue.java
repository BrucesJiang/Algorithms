package fundamentals.bagsQueueStack.api;

import util.api.StdIn;
import util.api.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code Queue } class represents a first-in-first-out (FIFO) queue of generic items
 * Its supports the usual <em>enqueue</em> and <em>dequeue</em> operations, along with methods
 * for peeking at the first item, testing if the queue is empty, and iterating through the item
 * in FIFO order.
 *
 * @auther Bruce Jiang
 */
public class Queue<I> implements Iterable<I> {
    private Node<I> first;
    private Node<I> last;
    private int size;

    //inner class
    private static class Node<I>{
        private I item;
        private Node<I> next;
        public Node(I item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    /**
     * Initializes an empty queue
     */
    public Queue(){
        first = last = null;
        size = 0;
    }

    /**
     * Returns whether the queue is empty or not
     *
     * @return {@code true} if the queue is empty
     *          {@code false} otherwise
     */
    public boolean isEmpty(){
        return first == null;
    }

    /**
     * Returns the size of the queue
     *
     * @return the size of the queue
     */
    public int size(){
        return size;
    }

    /**
     * Add an item into the queue at the last of the queue
     *
     * @param item the item to add
     */
    public void enqueue(I item){
        Node<I> oldlast = last;
        last = new Node<I>(item, null);
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        size++;
    }

    /**
     * Returns and remove the fist item in the queue
     *
     * @return the first item
     * @throws NoSuchElementException if this queue is empty
     */
    public I dequeue(){
        if(isEmpty()) throw new NoSuchElementException("Queue underflow");
        I item = first.item;
        first = first.next;
        size--;
        if(isEmpty()) last = null; // to avoid loitering
        return item;
    }

    /**
     * Returns the first item in the queue
     *
     * @return the first item in the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public I peek(){
        if(isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

    @Override
    public Iterator<I> iterator() {
        return new ListIterator(first);
    }

    private class ListIterator<I> implements Iterator<I>{
        private Node<I> current;
        public ListIterator(Node first){
            current = first;
        }
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public I next() {
            if(!hasNext()) throw new NoSuchElementException("Queue underflow");
            I item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove(){
            throw new UnsupportedOperationException("Unsupport this operation");
        }
    }

    /**
     * Unit test this queue type
     *
     * @param args the command-line args
     */
    public static void main(String[] args){
        Queue<String> queue = new Queue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                queue.enqueue(item);
            else if (!queue.isEmpty())
                StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("(" + queue.size() + " left on queue)");
    }

}
