package fundamentals.bagQueueStack;

import util.api.StdOut;

import java.util.Iterator;

/**
 * @auther Bruce Jiang
 */
public class RingBuffer<I>{
    private final static int DEFAULT_CAPACITY = 1 * 2014 * 1024;

    private I[] buff; // the buffer;
    private int capacity; // the capacity of the buffer
    private int head; // the head of the buffer
    private int tail; // the tail of the buffer

    public RingBuffer(){
        this.buff = (I[])new Object[DEFAULT_CAPACITY];
        this.head = 0;
        this.tail = 0;
        this.capacity = DEFAULT_CAPACITY;
    }

    public RingBuffer(int capacity){
        this.buff = (I[])new Object[capacity];
        this.head = 0;
        this.tail = 0;
        this.capacity = capacity;
    }

    public void put(I item){
        if(this.buff[tail] == null){ // buffer is not full
            this.buff[tail] = item;
            tail = (tail + 1) % this.capacity;
        }else{
            assert this.buff[tail] == null;
            StdOut.println("Buffer is full");
        }

    }

    public I get(){
        I item = null;
        if(this.buff[head] == null){
            assert this.buff[head] == null;
            StdOut.println("Buffer is empty");
        }else{
            item = this.buff[head];
            this.buff[head] = null;
            head = (head + 1) % this.capacity;
        }
        return item;
    }

    public static void main(String[] args){
        int[] a = {1,2,3,4,5,6,7,8,9,0};
        RingBuffer<Integer> rb = new RingBuffer<Integer>(5);
        for(int i : a){
            rb.put(i);
        }

        for(int i = 0; i < a.length; i ++){
            StdOut.print(rb.get() + " ");
        }
    }

}
