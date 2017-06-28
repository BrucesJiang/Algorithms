 package fundamentals.bagsQueueStack;

import java.util.Iterator;

/**
 * The {@code ResizingArrayQueue} class represents a first-in-first-out (FIFO) queue of generic items.
 *
 * It supports the usual <em>enqueue</em> and <em>dequeue</em> operations, along with methods for peeking
 * at first item, testing if the queue is empty, and iterating through the items in FIFO order.
 * <p>
 * This implementation uses a resizing array, which double the underlying array when it is full and halves the underlying
 * array when it is one-quarter full. The <em>enqueue</em> and <em>dequeue</em> operations take constant amortized time.
 * The <em>size</em>, <em>peak</em>, and <em>is-empty</em> operations take constant time in the wrost case
 *
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @auther Bruce Jiang
 */
public class ResizingArrayQueue<I> implements Iterable<I> {
    private final static int INITCAPACITY = 1000;
    private I[] array; // the queue arrays
    private int size; // number of element in the queue
    private int first; // index of first element of queue
    private int last; // index of last element of queue

    /**
     * Initializes a empty queue with initializable capacity
     */
    public ResizingArrayQueue(){
        array = (I[])new Object[INITCAPACITY];
        size = first = last = 0;
    }

    /**
     * Returns whether is empty or not
     * @return {@code true} if not empty
     *          {@code false} otherwise
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Returns the number of items in the queue
     * @return the number of items in the queue
     */
    public int size(){
        //TODO
        return -1;
    }

    public I peek(){
        //TODO
        return null;
    }

    public I pop(){
        //TODO
        return null;
    }

    public void enqueue(I item){
        //TODO
    }

    public I dequeue(){
        //TODO
        return null;
    }

    @Override
    public Iterator<I> iterator() {
        return new ReverseArrayQueue<>();
    }

    private void ensureCapacity(int newCapacity){
        //TODO
    }

    private class ReverseArrayQueue<I> implements Iterator<I>{

        @Override
        public boolean hasNext() {
            //TODO
            return false;
        }

        @Override
        public I next() {
            //TODO
            return null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Unsupport this Operation");
        }
    }

    /**
     * Unit tests the {@code ResizingArrayQueue} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args){
        //TODO
    }
}
