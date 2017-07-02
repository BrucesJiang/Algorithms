package fundamentals.bagQueueStack;

import util.api.StdIn;
import util.api.StdOut;
import util.api.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @auther Bruce Jiang
 */
public class RandomQueue<I> implements Iterable<I> {

    private final static int INI_CAPACITY = 100;

    private I[] que; // the queue
    private int head; // the head of the queue
    private int tail; // the tail of the queue
    private int size; // the size of the queue

    private void ensureCapacity(int newCapacity){
        assert newCapacity > size;
        I[] curQue = (I[])new Object[newCapacity];
        for(int i = 0; i < size; i ++){
            curQue[i] = que[(head + i)% que.length];
        }
        que = curQue;
        head = 0;
        tail = size;
    }
    /**
     * Initializes an empty RandomQueue
     */
    public RandomQueue(){
        que = (I[])new Object[INI_CAPACITY];
        head = tail = size = 0;
    }

    /**
     * Returns whether the queue is empty or not
     * @return {@code true} the queue is empty
     *          {@code false} otherwise
     */
    public boolean isEmpty(){
        return size() == 0;
    }

    /**
     * Returns the size of the queue
     *
     * @return the size of the queue
     */
    public int size(){
        return  size;
    }

    /**
     * Add an item at the end of the queue
     *
     * @param item the item to be added
     */
    public void enqueue(I item){
        if(size == que.length) ensureCapacity(que.length * 2);
        que[tail ++] = item;
    }

    /**
     *  Returns and remove a random {@code 0 <= num <= #size()} in the queue
     *
     * @return a item in the queue
     */
    public I dequeue(){
        int idx = StdRandom.uniform(0, size());
        idx = (idx + head) % que.length;
        I tmp = que[idx];
        que[idx] = que[tail];
        tail --;
        size --;
        if(size >0 && size < que.length/4) ensureCapacity(que.length/2);
        return tmp;
    }

    /**
     *  Returns (not remove) a random {@code 0 <= num <= #size()} in the queue
     *
     * @return a item in the queue
     */
    public I sample(){
        int idx = StdRandom.uniform(0, size());
        idx = (idx + head) % que.length;
        return que[idx];
    }

    @Override
    public Iterator<I> iterator() {
        return new ListIterator<I>(que, head, size);
    }

    private class ListIterator<I> implements Iterator<I>{
        private I[] q;
        private int N;
        private int current;
        public ListIterator(I[] q, int cur, int N){
            this.q = q;
            this.current = cur;
            this.N = N;
        }
        @Override
        public boolean hasNext() {
            return N > 0;
        }

        @Override
        public I next() {
            if(!hasNext()) throw new NoSuchElementException("Queue underflow");
            I item = q[current];
            current = (current + 1 + que.length) % que.length;
            return item;
        }
    }

    /**
     * Unit test {@code RandomQueue} data type
     * @param args the command-line args
     */
    public static void main(String[] args){
        RandomQueue<String> que = new RandomQueue<>();
        while(!StdIn.isEmpty()){
            String str = StdIn.readString();
            if(str.matches("[0-9]")){
                que.enqueue(str);
            }else if(str.matches("[a-z]")){
                StdOut.print(que.dequeue() + " ");
            }else if(str.matches("[+ - * /]")){
                StdOut.print(que.sample());
            }
        }
    }
}
