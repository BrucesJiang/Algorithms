package fundamentals.bagQueueStack;

import util.api.StdOut;

import java.util.Iterator;

/**
 * 线程安全的环形缓冲区
 * @auther Bruce Jiang
 */
public class RingBuffer<I>{
    private final static int DEFAULT_CAPACITY = 1 * 2014 * 1024;

    private I[] buff; // the buffer;
    private int capacity; // the capacity of the buffer
    private int head; // the head of the buffer
    private int tail; // the tail of the buffer

    enum ThreadState{
        BLOCK, /* 暂时阻塞*/
        RUNNING, /* 运行*/
        OVER, /* 线程顺利完成*/
        INTERRUPT /* 中断 */
    }

    private ThreadState putThreadState = ThreadState.RUNNING;
    private ThreadState getThreadState = ThreadState.RUNNING;



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

        while(null != this.buff[tail]){
            try{
                this.setPutThreadState(ThreadState.BLOCK);
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
                System.out.println("线程停止");
            }
        }
        this.setPutThreadState(ThreadState.RUNNING);
        this.buff[this.tail] = item;
        this.tail = (this.tail + 1) % this.capacity;

//        if(this.buff[tail] == null){ // buffer is not full
//            this.buff[tail] = item;
//            tail = (tail + 1) % this.capacity;
//        }else{
//            assert this.buff[tail] == null;
//            StdOut.println("Buffer is full");
//        }

    }

    public I get(){
        while(null == this.buff[this.head]){
            try{
                this.setGetThreadState(ThreadState.BLOCK);
                if(this.isPutedOver() && this.isEmpty()){
                    this.setGetThreadState(ThreadState.OVER);
                    return null;
                }
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
                System.out.println("例外发生");
            }
        }

        this.setGetThreadState(ThreadState.RUNNING);

        I item = this.buff[this.head];
        this.buff[this.head] = null;

        this.head = (this.head + 1) % this.capacity;

        return item;

//        I item = null;
//        if(this.buff[head] == null){
//            assert this.buff[head] == null;
//            StdOut.println("Buffer is empty");
//        }else{
//            item = this.buff[head];
//            this.buff[head] = null;
//            head = (head + 1) % this.capacity;
//        }
    }

    public boolean isEmpty() {
        // 新元素是以 索引0 向 索引length 的顺序 put入
        // 有鉴于此，这里倒过来枚举，防止出现“同向追赶”导致落空的的囧事；
        for (int i = this.buff.length - 1; i > 0; i--) {
            if (this.buff[i] != null) {
                return false;
            }
        }
        return true;
    }

    public boolean isPutedOver() {
        return this.putThreadState == ThreadState.OVER;
    }

    public void setPutThreadState(ThreadState putThreadState) {
        this.putThreadState = putThreadState;
    }

    private void setGetThreadState(ThreadState getThreadState) {
        this.getThreadState = getThreadState;
    }

    public static void main(String[] args){
        final int LENGTH = 1024;
        RingBuffer<Integer> rb = new RingBuffer<Integer>(20);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i <  LENGTH; i ++){
                    rb.put(i);
                    StdOut.println(this.getClass().getName());
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i <  LENGTH; i ++){
                    rb.put(i);
                    StdOut.println(this.getClass().getName());
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i <  LENGTH; i ++){
                    rb.put(i);
                    StdOut.println(this.getClass().getName());
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i <  LENGTH; i ++){
                    rb.put(i);
                    StdOut.println(this.getClass().getName());
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < LENGTH; i ++){
                    StdOut.println(this.getClass().getName() + ":" + rb.get());
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < LENGTH; i ++){
                    StdOut.println(this.getClass().getName() + ":" + rb.get());
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < LENGTH; i ++){
                    StdOut.println(this.getClass().getName() + ":" + rb.get());
                }
            }
        }).start();
    }

}
