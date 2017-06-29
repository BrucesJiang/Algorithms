 package fundamentals.bagsQueueStack;

import util.api.StdIn;
import util.api.StdOut;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
        return size;
    }

    /**
     * Returns the the first item in the queue
     *
     * @return the first item
     * @throws NoSuchElementException if the queue is empty
     */
    public I peek(){
        if(isEmpty()) throw new NoSuchElementException("Queue is empty");
        return array[first];
    }

    /**
     * Returns and remove the first item in the queue
     *
     * @return the first item in the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public I pop(){
        if(isEmpty()) throw new NoSuchElementException("Queue is empty");
        I item = array[first];
        array[first++] = null;
        --size;
        return item;
    }

     /**
      * Add an item into the queue
      *
      * @param item the item
      */
    public void enqueue(I item){
        if(size == array.length) ensureCapacity(array.length * 2);
        array[last++] = item;
        if(last == array.length) last = 0; //wrap-around
        size ++;
    }

     /**
      * Removes and returns the item on this queue that was least recently added.
      * @return the item on this queue that was least recently added
      * @throws NoSuchElementException if this queue is empty
      */
     public I dequeue() {
         if (isEmpty()) throw new NoSuchElementException("Queue underflow");
         I item = array[first];
         array[first] = null;                            // to avoid loitering
         size--;
         first++;
         if (first == array.length) first = 0;           // wrap-around
         // shrink size of array if necessary
         if (size > 0 && size == array.length/4) ensureCapacity(array.length/2);
         return item;
     }

    @Override
    public Iterator<I> iterator() {
        return new ReverseArrayQueue();
    }

    //resize the underlying array
    private void ensureCapacity(int newCapacity){
        assert newCapacity >= size;
        Arrays.copyOf(array, newCapacity);
        first = 0;
        last = size;
    }

    private class ReverseArrayQueue implements Iterator<I>{

        private int n = 0;
        @Override
        public boolean hasNext() {
            return n < size;
        }

        @Override
        public I next() {
            if(!hasNext()) throw new NoSuchElementException("The Queue is empty");
            I item = array[(n + first) % array.length];
            n ++;
            return item;
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
        ResizingArrayQueue<String> queue = new ResizingArrayQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) queue.enqueue(item);
            else if (!queue.isEmpty()) StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("(" + queue.size() + " left on queue)");
    }
}
