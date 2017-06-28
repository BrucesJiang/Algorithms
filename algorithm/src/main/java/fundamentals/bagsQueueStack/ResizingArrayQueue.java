package fundamentals.bagsQueueStack;

/**
 * The {@code ResizingArrayQueue} class represents a first-in-first-out (FIFO) queue of generic items.
 *
 * It supports the usual <em>enqueue</em> and <em>dequeue</em> operations, along with methods for peeking
 * at first item, testing if the queue is empty, and iterating through the items in FIFO order.
 * <p>
 * This implementation uses a resizing array, which double the underlying array when it is full and halves the underlying
 * array when it is one-quarter full. The <em>equeue</em> and <em>dequeue</em> operations take constant amortized time.
 * The <em>size</em>, <em>peak</em>, and <em>is-empty</em> operations take constant time in the wrost case
 *
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @auther Bruce Jiang
 */
public class ResizingArrayQueue {


}
