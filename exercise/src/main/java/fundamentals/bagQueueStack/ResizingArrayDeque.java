package fundamentals.bagQueueStack;

import util.api.StdIn;
import util.api.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @auther Bruce Jiang
 */
public class ResizingArrayDeque<I> implements Iterable<I>{
    private static final int INIT_CAPACITY = 100;
    private I[] array; // the queue
    private int head; // the head of the queue
    private int tail; // the tail of the queue
    private int size; // the size of the queue

    public ResizingArrayDeque(){
        array = (I[])new Object[INIT_CAPACITY];
        head = INIT_CAPACITY/2;
        tail = INIT_CAPACITY/2;
        size = 0;
    }


    public boolean isEmpty(){
        return size == 0;
    }


    public int size(){
        return size;
    }

    public void pushLeft(I item){
        if(size == array.length) ensureCapacity(array.length * 3);
        array[head] = item;
        size ++ ;
        head = (head + array.length - 1) % array.length;
    }

    public void pushRight(I item){
        if(size == array.length) ensureCapacity(array.length * 3);
        array[tail] = item;
        size ++;
        tail = (tail + 1 + array.length) % array.length;
    }

    public I popLeft(){
        if(isEmpty()) throw new NoSuchElementException("Dequeue Underflow");
        head = (head + 1 + array.length) % array.length;
        I item = array[head];
        array[head] = null;
        size --;
        if(size > 0 && size == array.length/9) ensureCapacity(array.length/3);
        return item;
    }

    public I popRight(){
        if(isEmpty()) throw new NoSuchElementException("Dequeue Underflow");
        tail = (tail - 1 + array.length) % array.length;
        I item = array[tail];
        array[tail] = null;
        size --;
        return item;
    }


    public Iterator<I> iterator() {
        return new ListIterator<>();
    }

    private class ListIterator<I> implements Iterator<I>{

        private int current = 0;
        public boolean hasNext() {
            return current < size;
        }

        public I next() {
            if(!hasNext()) throw new NoSuchElementException("Dequeue Underflow");
            I item = (I) array[(current + head) % array.length];
            current ++;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("Unsupported this operation");
        }
    }

    private void ensureCapacity(int newCapacity){
        assert newCapacity >= size;
        I[] tmp = (I[])new Object[newCapacity];
        for(int i = 0; i < size; i ++){
            tmp[array.length + i] = array[(head + i) % array.length];
        }
        array = tmp;
        this.head = array.length;
        this.tail = array.length + size;
    }

    /**
     * Unit test {@code ResizingArrayDeque} data type
     *
     * @param args the command-line data type
     */
    public static void main(String[] args){
        ResizingArrayDeque<String> deque = new ResizingArrayDeque<>();
        while(!StdIn.isEmpty()){
            String str = StdIn.readString().trim();
            if(str.equals("-")){
                StdOut.println(deque.popLeft());
            }else if(str.equals("+")){
                StdOut.println(deque.popRight());
            }else if(str.matches("[1-9]")){
                deque.pushLeft(str);
            }else if(str.matches("[a-z]")){
                deque.pushRight(str);
            }
        }
    }
}
