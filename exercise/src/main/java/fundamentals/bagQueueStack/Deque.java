/**************************************************
 * Compilation:   javac Deque.java
 * Execution:     java Deque
 * Dependencies:  StdOut.java StdIn.java
 *
 * % java Deque < input.txt
 * Input:
 *  a * b - c + a 1 2 3 4 5 f v f s * d
 * Output:
 *  a**null-+ba+c1254fs**
 **************************************************/

package fundamentals.bagQueueStack;


import util.api.StdIn;
import util.api.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @auther Bruce Jiang
 */
public class Deque<I> implements Iterable<I>{
    public Node<I> head;
    public Node<I> tail;
    public int size;

    private static class Node<I>{
        private I item;
        private Node<I> prev;
        private Node<I> next;
        public Node(I item, Node<I> prev, Node<I> next){
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * Initializes an empty Deque
     */
    public Deque(){
        head = new Node<I>(null, null, null);
        tail = new Node<I>(null, null, null);
        tail.prev = head;
        head.next = tail;
        size = 0;
    }

    /**
     * Returns the size of the Deque
     *
     * @return the size of the Deque
     */
    public int size(){
        return size;
    }

    /**
     * Returns whether the Deque is empty or not
     *
     * @return {@code true} if the Deque is empty
     *          {@code false} otherwise
     */
    public boolean isEmpty(){
        return size() == 0;
    }

    /**
     * Add an item from the left point
     *
     * @param item the item to be added
     */
    public void pushLeft(I item){
        Node<I> node = new Node<I>(item, head, head.next);
        head.next.prev = node;
        head.next = node;
        size++;
    }

    /**
     * Add an item from the right point
     *
     * @param item the item to be added
     */
    public void pushRight(I item){
        Node<I> node = new Node<I>(item, tail.prev, tail);
        tail.prev.next = node;
        tail.prev = node;
        size ++;
    }

    /**
     * Returns and removes an item from the left point
     *
     * @return an item from left point
     * @throws NoSuchElementException if the Deque is empty
     */
    public I popLeft(){
        if(isEmpty()) throw new NoSuchElementException("Deque underflow");
        I item = head.next.item;
        head.next = head.next.next;
        head.next.next.prev = head;
        size --;
        return item;
    }

    /**
     * Returns an item (but not remove) from the left point
     *
     * @return an item from the left point
     * @throws NoSuchElementException if the Deque is empty
     */
    public I peekLeft(){
        if(isEmpty()) throw new NoSuchElementException("Deque underflow");
        return head.next.item;
    }

    /**
     * Returns and remove an item from the right point
     *
     * @return an item from right point
     * @throws NoSuchElementException if the Deque is empty
     */
    public I popRight(){
        if(isEmpty()) throw new NoSuchElementException("Deque underflow");
        I item = tail.prev.item;
        tail.prev = tail.prev.prev;
        tail.prev.next = tail;
        return item;
    }

    /**
     * Return (but not removes) an item from the right point
     *
     * @return an item from right point
     * @throws NoSuchElementException if the Deque is empty
     */
    public I peekRight(){
        if(isEmpty()) throw new NoSuchElementException("Deque underflow");
        return tail.item;
    }

    public Iterator<I> iterator() {
        return new ListInterator<I>(head.next, size);
    }

    private class ListInterator<I> implements Iterator<I>{
        private Node<I> currrent;
        private int N;
        public ListInterator(Node<I> current, int N){
            this.currrent = current;
            this.N = N;
        }

        public boolean hasNext() {
            return N >= 0;
        }

        public I next() {
            if(!hasNext()) throw new NoSuchElementException("Deque underflow");
            I item = currrent.item;
            currrent = currrent.next;
            N--;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("Unsupport this Operation");
        }
    }

    /**
     * Unit test the {@code  Deque} data type
     *
     * @param args the command-line argss
     */
    public static void main(String[] args){
        Deque<String> dq = new Deque<String>();


        while(!StdIn.isEmpty()){
            String str = StdIn.readString();
            if(str.matches("[+ - * /]")){
                dq.pushLeft(str);
                StdOut.print(dq.peekLeft());
            }else if(str.equals("-")){
                dq.pushRight(str);
                StdOut.print(dq.peekRight());
            }else if(str.matches("[1-9]")){
                dq.pushRight(str);
                StdOut.print(dq.popLeft());
            }else{
                /**
                 * 这里有个Bug: 如果首先执行这里，182行会报错，最根本的原因是 115行，继续追溯
                 *  会发现自己在数据结构的处理失误， 新增的额外空间点初始加入的时候，head和 tail
                 *  指针有问题
                 */
                dq.pushLeft(str);
                StdOut.print(dq.popRight());
            }
        }
    }
}
