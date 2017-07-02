package fundamentals.bagQueueStack;

import util.api.StdArrayIO;
import util.api.StdOut;
import util.api.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @auther Bruce Jiang
 */
public class RandomBag<I> implements Iterable<I>{
    private static final int INIT_CAPACITY = 100;

    private I[] bag; // the bag
    private int size; // the size of the bag

    //resize the bag
    private void ensureCapacity(int newCapacity){
        assert newCapacity >= size;
        bag = Arrays.copyOf(bag, newCapacity);
    }


    /**
     * Initializes an empty bag
     */
    public RandomBag(){
        bag = (I[])new Object[INIT_CAPACITY];
        size = 0;
    }

    /**
     * Returns whether the bag is empty or not
     *
     * @return whether the bag is empty or not
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Returns the size of the random bag
     *
     * @return the size of the random bag
     */
    public int size(){
        return size;
    }

    /**
     * Add an item into the bag
     *
     * @param item the item to be added
     */
    public void add(I item){
        if(size == bag.length) ensureCapacity(bag.length * 2);
        bag[size++] = item;
    }

    /**
     * Random access every item in the bag
     *
     * @return the list of items in the bag
     */
    @Override
    public Iterator<I> iterator() {
        return new ListIterator<I>(this.bag, size());
    }

    private class ListIterator<I> implements Iterator<I>{
        private I[] array;
        private int N;
        public ListIterator(I[] a, int N){
            this.array = Arrays.copyOf(a, N);
            this.N = N;
            StdRandom.shuffle(this.array);
        }

        @Override
        public boolean hasNext() {
            return N > 0;
        }

        @Override
        public I next() {
            if(!hasNext()) throw new NoSuchElementException("RandomBag underflow");
            return array[--N];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Unsupport this Operation");
        }
    }


    /**
     * Unit test the {@code RandomBag} data type
     *
     * @param args the command-line args
     */
    public static void main(String[] args){
        int[] a = {1, 2, 3, 8, 7, 5, 1, 0, 3, 6, 8, 4, 9, 5, 3};
        RandomBag<Integer> rb = new RandomBag<Integer>();
        for(int item : a){
            rb.add(item);
        }
        Iterator<Integer> iter = rb.iterator();
        while(iter.hasNext()){
            StdOut.print(iter.next() + " ");
        }
        StdOut.println();
    }
}
