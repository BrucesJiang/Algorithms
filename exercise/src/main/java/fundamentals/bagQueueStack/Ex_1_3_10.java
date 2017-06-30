/**
 *  Compilation: javac Ex_1_3_10.java
 *  Execution: java Ex_1_3_10
 *
 *  Input:
 *  ( ( ( 6 - 5 ) * ( 4 - 3 ) ) * ( 2 + 1 ) )
 *
 *  Output:
 *   65-43-*21+*
 */
package fundamentals.bagQueueStack;

import fundamentals.bagsQueueStack.api.Stack;
import util.api.StdIn;

/**
 * @auther Bruce Jiang
 */
public class Ex_1_3_10 {

    public static void infixToPostfix(String[] strs){
        Stack<String> stack = new Stack<String>();

        int len = strs.length;

        for(int i = 0; i < len; i ++){
            if(strs[i].equals("+") || strs[i].equals("-") || strs[i].equals("/") || strs[i].equals("*")){

            }
        }
    }

    public static void main(String[] args){
        String[] strs = StdIn.readAllStrings();
        infixToPostfix(strs);
    }

}
