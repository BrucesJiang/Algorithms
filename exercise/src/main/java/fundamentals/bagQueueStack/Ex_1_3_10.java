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
import util.api.StdOut;

/**
 * @auther Bruce Jiang
 */
public class Ex_1_3_10 {

    public static String infixToPostfix(String[] strs){
        Stack<String> vals = new Stack<String>();
        Stack<String> opts = new Stack<String>();
        int len = strs.length;

        for(int i = 0; i < len; i ++){
            if(strs[i].equals("+") || strs[i].equals("-") || strs[i].equals("/") || strs[i].equals("*") || strs[i].equals("sqrt")){
                opts.push(strs[i]);
            }else if(strs[i].equals("(")){
                continue;
            }else if(strs[i].equals(")")){
                String opt = opts.pop();
                String val = vals.pop();
                if(opt.equals("+") || opt.equals("-") || opt.equals("/") || opt.equals("*")){
                    vals.push(String.format("%s%s%s", vals.pop(), val, opt));
                }else if(opt.equals("sqrt")){
                    vals.push(String.format("%s%s", val, opt));
                }
            }else{
                vals.push(strs[i]);
            }
        }
        return vals.toString();
    }

    public static void main(String[] args){
        String[] strs = StdIn.readAllStrings();
        StdOut.println(infixToPostfix(strs));
    }

}
