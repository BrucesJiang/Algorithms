/******************************************************************************
 *  Compilation:  javac FixedCapacityStackOfStrings.java
 *  Execution:    java FixedCapacityStackOfStrings
 *  Dependencies: StdIn.java StdOut.java
 *
 *  Stack of strings implementation with a fixed-size array.
 *
 *  % more tobe.txt
 *  to be or not to - be - - that - - - is
 *
 *  % java FixedCapacityStackOfStrings 5 < tobe.txt
 *  to be not that or be
 *
 *  Remark:  bare-bones implementation. Does not do repeated
 *  doubling or null out empty array entries to avoid loitering.
 *
 ******************************************************************************/
package fundamentals.bagsQueueStack;

import util.api.StdIn;
import util.api.StdOut;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @auther Bruce Jiang
 */
public class FixedCapacityStackOfStrings implements Iterable<String>{
    private String[] a; //stack of entries
    private int N; // size

    public FixedCapacityStackOfStrings(int capacity){
            a = new String[capacity];
            N = 0;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void push(String str){
        a[N++] = str;
    }

    public String pop(){
        return a[--N];
    }
    @Override
    public Iterator<String> iterator() {
        return new ReverseArrayIterator();
    }

    public class ReverseArrayIterator implements Iterator<String>{
        private int  i = N - 1;

        @Override
        public boolean hasNext() {
            return i >= 0;
        }

        @Override
        public String next() {
            if(!hasNext()) throw new NoSuchElementException("The Stack is empty");
            return a[i--];
        }

        @Override
        public void remove(){
            throw new UnsupportedOperationException("Unsupported Operation");
        }
    }

    public static void main(String[] args){
        FixedCapacityStackOfStrings fcsos = new FixedCapacityStackOfStrings(100);
        while(!StdIn.isEmpty()){
            String item = StdIn.readString();
            if(!item.equals("-")) fcsos.push(item);
            else if(!fcsos.isEmpty()) StdOut.print(fcsos.pop() + " ");
        }
        StdOut.println("(" + fcsos.size() + " left on stack )");
        Iterator<String> iter = fcsos.iterator();
        while(iter.hasNext()){
            StdOut.println(iter.next());
        }
    }


}
