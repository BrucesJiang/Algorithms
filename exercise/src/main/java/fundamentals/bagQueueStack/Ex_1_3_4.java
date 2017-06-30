package fundamentals.bagQueueStack;

import fundamentals.bagsQueueStack.api.Stack;
import util.api.StdIn;
import util.api.StdOut;

/**
 * @auther Bruce Jiang
 */
public class Ex_1_3_4 {

    public static boolean parentheses(String str){
        int len = str.length();
        Stack<Character> stack = new Stack<Character>();
        for(int i = 0; i < len; i ++){
            char ch = str.charAt(i);
            if(ch == '[' || ch == '{' || ch == '(')
                stack.push(ch);
            else if(ch == ']' && stack.pop() != '[' || ch == '}' && stack.pop() != '{' || ch == ')' && stack.pop() != '('){
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args){
        String str = StdIn.readAll().trim();
        StdOut.println(parentheses(str));
    }
}
