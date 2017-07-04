package fundamentals.bagQueueStack;

import fundamentals.bagsQueueStack.api.Stack;

/**
 *  The {@code Buffer} is a data type that represents the buffer of text editor.
 *
 *  It has usual <em>delete</em>, <em>left</em>, <em>right</em> method.
 *
 *  implemented with two stacks // 暂时没有好的实现思路
 *
 * @auther Bruce Jiang
 */
public class Buffer {
    private Stack<Character> leftStack;
    private Stack<Character> rightStack;
    public Buffer(){
        leftStack = new Stack<>();
        rightStack = new Stack<>();
    }

    /**
     * Returns whether the buffer is empty or not
     *
     * @return {@code true} if the buffer is empty
     */
    public boolean isEmpty(){
        return size() == 0;
    }

    /**
     *  Returns the size of the buffer
     *
     * @return the size of the buffer
     */
    public int size(){
        return leftStack.size() + rightStack.size();
    }

    /**
     *  Insert a character at the current cursor position
     *
     * @param c the character to be inserted
     */
    public void insert(char c){
        leftStack.push(c);
    }

    /**
     *  Returns and removes a character at the current cursor position
     *
     * @return the character bo be deleted
     */
    public char delete(){
        //TODO
        return 'a';
    }

    /**
     *  Move the cursor to the left by k positions
     *
     * @param k positions the cursor will be moved
     */
    public void left(int k){

    }

    /**
     *  Move the cursor to the right by k positions
     *
     * @param k positions the cursor will be moved
     */
    public void right(int k){

    }

    /**
     * Unit test {@code Buffer} data type
     *
     * @param args the command-line args
     */
    public static void main(String[] args){
        //TODO
    }
}
