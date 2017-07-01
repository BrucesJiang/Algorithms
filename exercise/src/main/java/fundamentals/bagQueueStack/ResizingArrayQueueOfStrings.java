package fundamentals.bagQueueStack;

import java.util.*;

/**
 * @auther Bruce Jiang
 */
public class ResizingArrayQueueOfStrings implements Iterable{
    private final static int INIT_CAPACITY = 1000;
    private String[] strs; // item array
    private int first; // the first item in the queue
    private int last; // the last item in the queue
    private int size; // number of items in the queue

    /**
     * Initializes an mepty queue
     */
    public ResizingArrayQueueOfStrings(){
        first = last = size = 0;
        ensureCapacity(INIT_CAPACITY);
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public String dequeue(){
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        String str = strs[first];
        strs[first] = null;                            // to avoid loitering
        size--;
        first++;
        if (first == strs.length) first = 0;           // wrap-around
        // shrink size of array if necessary
        if (size > 0 && size == strs.length/4) ensureCapacity(strs.length/2);
        return str;
    }

    public void enqueue(String str){
        if(size == strs.length) ensureCapacity(strs.length * 2);
        strs[last++] = str;
        if(last == strs.length) last = 0; //wrap-around
        size ++;
    }

    public Iterator iterator() {
        return null;
    }

    private class ListIterator implements Iterator{
        private int n = size;
        public boolean hasNext() {
            return n < size;
        }

        public Object next() {
            if(!hasNext()) throw new NoSuchElementException("The Queue is empty");
            String str = strs[(n + first) % strs.length];
            n ++;
            return str;
        }

        public void remove() {

        }
    }
    private void ensureCapacity(int max){
        assert max < size;
        String[] strsN = new String[max];
        for(int i =0 ; i < strs.length; i ++){
            strsN[i] = strs[i];
        }
        first = 0;
        last = size;
    }
}
