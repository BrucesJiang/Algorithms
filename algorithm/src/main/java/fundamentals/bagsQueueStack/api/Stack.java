/**
 * Compilation:     javac -d . Stack.java
 * Execution:       java Stack
 *
 * % java Stack
 * Input: to be or not to - be - - that - - - is
 *
 * Output : to be not that or be (2 left in the stack)
 */

package fundamentals.bagsQueueStack.api;

import util.api.StdIn;
import util.api.StdOut;

import java.util.Iterator;
/**
 * The {@code Stack} class represents a last-in-first-out (LIFO) stack of generic items.
 * It supports the usual <em>push</em>， <em>pop</em> operations, along with methods for peek
 * at top item, testing if the stack is empty, and iterating through the items in LIFO order.
 *<p>
 *  This implementation uses a singly-linked list with a static nested class for
 *  linked-list nodes.
 *  The <em>push</em>, <em>pop</em>, <em>peek</em>, <em>size</em>, and <em>is-empty</em>
 *  operations all take constant time in the worst case.
 *
 *
 * @auther Bruce Jiang
 */
public class Stack<I> implements Iterable<I>{
    private Node first; //top of the stack
    private int size; // number of items in the stack

    private static class Node<I>{
        Node(I item, Node next){
            this.item = item;
            this.next = next;
        }
        private I item;
        private Node<I> next;
    }

    /**
     * Initializes an empty stack
     */
    public Stack(){
       this.first = null;
       size = 0;
    }

    /**
     * Return whether the stack is empty or not
     *
     * @return {@code true} if the stack is empty
     *          {@code false} otherwise
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Returns the size of the stack
     *
     * @return the size of the stack
     */
    public int size(){
        return size;
    }

    /**
     * Add an item on the top of the stack
     *
     * @param item the item
     */
    public void push(I item){
        Node node = new Node(item, first);
        first = node;
        size ++;
    }

    /**
     * Returns and removes the item on the top of stack
     * or {@code null} if the stack is empty
     * @return the item on the top of stack
     *          return {@code null} if the stack is empty
     */
    public I pop(){
        Node node = first;
        first = node.next;
        size --;
        return (I) node.item;
    }

    /**
     * Returns the item on the top of the stack
     * or return {@code null} if the stack is empty
     *
     * @return the item on the top of the stack
     *    {@code null} if the stack is empty
     */
    public I peek(){
        if(isEmpty()) return null;
        else return (I)first.item;
    }

    @Override
    public Iterator<I> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<I>{
        private Node current = first;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public I next() {
            I item = (I)current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Unsupport this operation");
        }
    }

    /**
     * Unit tests the {@code Stack} data type
     *
     * @param args the command-line args
     */
    public static void main(String[] args){
        Stack<String> stack = new Stack<String>();

        while(!StdIn.isEmpty()){
            String item = StdIn.readString();
            if(!item.equals("-")) stack.push(item);
            else if(!stack.isEmpty()) StdOut.print(stack.pop() + " ");
        }
        StdOut.println("(" + stack.size() + " left in the stack)");
    }
}
