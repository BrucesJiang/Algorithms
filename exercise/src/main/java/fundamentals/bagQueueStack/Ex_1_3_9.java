/**
 *  Compilation: javac Ex_1_3_9.java
 *  Execution: java Ex_1_3_9
 *
 *  1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
 *  ( ( ( 6 - 5 ) * ( 4 - 3 ) ) * ( 2 + 1 ) )
 */

package fundamentals.bagQueueStack;

import fundamentals.bagsQueueStack.api.Stack;
import util.api.StdIn;
import util.api.StdOut;

/**
 * @auther Bruce Jiang
 */
/**
 * @auther Bruce Jiang
 */
public class Ex_1_3_9 {
    /**
     *  算法思想： 使用双栈
     *   遇到 操作符，入栈 opt
     *   遇到 操作数  入栈 val
     *   遇到 ')' : 出栈两个操作数和一个操作符 构成 (val1 opt val2), 压栈 val
     *   直至结束
     */
    public static String matcher(String[] strs){
        Stack<String> opt = new Stack<String>();
        Stack<String> val = new Stack<String>();
        int len = strs.length;
        for(int i = 0; i < len; i ++){
            if(strs[i].equals("*") || strs[i].equals("+") ||
                    strs[i].equals("-") || strs[i].equals("/") || strs[i].equals("sqrt")){
                opt.push(strs[i]);
            }else if(strs[i].equals(")")){
                String o = opt.pop();
                String str = null;
                if(o.equals("*") || o.equals("-") || o.equals("+") || o.equals("/"))
                    str = String.format("( %s %s %s )", val.pop(), o, val.pop());
                else if( o.equals("sqrt"))
                    str = String.format("( %s %s )", o, val.pop());
                val.push(str);
            }else{
                val.push(strs[i]);
            }
        }
        return val.toString();
    }


    public static void main(String[] args){
        String[] strs = StdIn.readAllStrings();
        StdOut.println(matcher(strs));
    }
}