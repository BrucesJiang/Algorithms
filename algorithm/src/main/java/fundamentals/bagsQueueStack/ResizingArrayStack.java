/******************************************************************************
 *  Compilation:  javac ResizingArrayStack.java
 *  Execution:    java ResizingArrayStack (< input.txt)
 *  Dependencies: StdIn.java StdOut.java
 ******************************************************************************/
package fundamentals.bagsQueueStack;

import util.api.StdIn;
import util.api.StdOut;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code ResizingArrayStack} class represents a last-in-fist-out {LIFO} stack of
 * generic items
 *
 * It supports the usual <em>push</em> and <em>pop</em> operations , along with methods
 * for peeking at the top item, testing if the stack is empty, and iterating through the items
 * in LIFO order.
 * <p>
 * This implementation uses a resizing array, which double the underlying array when it is full and halves
 * the underlying array when it is one-quarter full. The <em>push </em> and <em>pop</em> operations take constant amortized
 * time. The <em>size</em>, <em>peek</em>, and <em>is-empty</em> operations takes constant time in the wrost case.
 *<p>
 * For additional documention
 * see <a href="http://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of <i>Algorithms, 4th Editio</i>
 *
 * @auther Bruce Jiang
 */
public class ResizingArrayStack<I> implements Iterable<I>{
    private final static int INIT_CAPACITY = 1000;
    private I[] a; // array of items
    private int size; // number of items in the stack

    /**
     * Initializes an empty stack
     */
    public ResizingArrayStack(){
        a = (I[])new Object[INIT_CAPACITY];
        size = 0;
    }

    /**
     * Returns whether the stack is empty or not
     * @return {@code true} if the stack is empty
     *          {@code false} other number
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Returns the number of items in the stack
     *
     * @return the number of items in the stack
     */
    public int size(){
        return size;
    }

    /**
     * Add an item into stack
     * @param item the item
     */
    public void push(I item){
        if(size() >= a.length) ensureCapacity(2*a.length );
        a[size ++] = item;
    }

    /**
     * Removes and returns the item most recently added to this stack
     * @return the item most recently added to this stack
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    public I pop(){
        if(isEmpty()) throw new NoSuchElementException("Stack underflow");
        I item = a[--size];
        a[size] = null; // to avoid loitering
        if(size()> 0 && size() >= a.length/4) ensureCapacity(a.length/2);
        return item;
    }

    /**
     * Returns (but not remove) the item most recently added to this stack.
     * @return the item most recently added to this stack
     * @throws NoSuchElementException if this stack is empty
     */
    public I peek(){
        if(isEmpty()) throw new NoSuchElementException("Stack underflow");
        return a[size - 1];
    }
    @Override
    public Iterator<I> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<I>{
        private int n;
        public ReverseArrayIterator(){
            n = size - 1;
        }
        @Override
        public boolean hasNext() {
            return  n >= 0;
        }

        @Override
        public I next() {
            if(!hasNext()) throw new NoSuchElementException("The stack is Empty");
            return a[n--];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Unsupport this operation");
        }
    }

    private void ensureCapacity(int newCapacity){
        assert newCapacity >= size;
        a = Arrays.copyOf(a, newCapacity);
    }


    /**
     * Unit tests the {@code Stack} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        ResizingArrayStack<String> stack = new ResizingArrayStack<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) stack.push(item);
            else if (!stack.isEmpty()) StdOut.print(stack.pop() + " ");
        }
        StdOut.println("(" + stack.size() + " left on stack)");
    }

}
