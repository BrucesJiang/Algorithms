package fundamentals.bagQueueStack;


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
        Node<I> node = new Node<I>(null, null, null);
        head = tail = node;
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
        head.next = node;
        size++;
    }

    /**
     * Add an item from the right point
     *
     * @param item the item to be added
     */
    public void pushRight(I item){
        Node<I> node = new Node<I>(item, tail, null);
        tail.next = node;
        tail = node;
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
        I item = tail.item;
        tail.prev.next = null;
        tail = tail.prev;
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
        return new ListInterator<I>(head.next);
    }

    private class ListInterator<I> implements Iterator<I>{
        private Node<I> currrent;
        public ListInterator(Node<I> current){
            this.currrent = current;
        }

        public boolean hasNext() {
            return currrent == null;
        }

        public I next() {
            if(!hasNext()) throw new NoSuchElementException("Deque underflow");
            I item = currrent.item;
            currrent = currrent.next;
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

    }
}
