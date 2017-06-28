/******************************************************************************
 *  Compilation:  javac FixedCapacityStack.java
 *  Execution:    java FixedCapacityStack
 *  Dependencies: StdIn.java StdOut.java
 *
 *  Generic stack implementation with a fixed-size array.
 *
 *  Remark:  bare-bones implementation. Does not do repeated
 *  doubling or null out empty array entries to avoid loitering.
 *
 ******************************************************************************/

package fundamentals.bagsQueueStack;

import util.api.StdIn;
import util.api.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @auther Bruce Jiang
 */
public class FixedCapacityStack <I> implements Iterable<I>{
    private I[] a; //holds the items
    private int N; // number of items in stack

    //create an empty stack with given capicity
    public FixedCapacityStack(int capacity){
        a = (I[]) new Object[capacity];
        N = 0;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public void push(I item){
        a[N++] = item;
    }

    public I pop(){
        return isEmpty()?null:a[--N];
    }
    @Override
    public Iterator<I> iterator(){ return new ReverseArrayIterator();}

    private class ReverseArrayIterator implements Iterator{
        private int n = N - 1;
        @Override
        public boolean hasNext() {
            return n >= 0;
        }

        @Override
        public I next() {
            if(!hasNext()) throw new NoSuchElementException("The stack is empty");
            return  a[n--];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Unsupport this operation");
        }
    }

    public static void main(String[] args) {
        int max = Integer.parseInt(args[0]);
        FixedCapacityStack<String> stack = new FixedCapacityStack<String>(max);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) stack.push(item);
            else if (stack.isEmpty())  StdOut.println("BAD INPUT");
            else                       StdOut.print(stack.pop() + " ");
        }
        StdOut.println();

        // print what's left on the stack
        StdOut.print("Left on stack: ");
        for (String s : stack) {
            StdOut.print(s + " ");
        }
        StdOut.println();
    }
}
