/***************************************
 *  Compilation:   javac Josephus.java
 *  Execution:     java Josephus 10 5
 *  Dependencies:  StdOut.java Queue.java
 *
 *  % java Josephus 10 5
 *  1 3 6 10 8 9 5 2 4 7
 ***************************************/


package fundamentals.bagQueueStack;

import fundamentals.bagsQueueStack.api.Queue;
import util.api.StdOut;

/**
 * @auther Bruce Jiang
 */
public class Josephus {
    public static void main(String[] args){
        int N = Integer.parseInt(args[0]);
        int step = Integer.parseInt(args[0]);

        Queue<Integer> queue = new Queue<Integer>();
        for(int i = 1; i <= N; i ++){
            queue.enqueue(i);
        }

        while(!queue.isEmpty()){
            for(int i = 0; i < step; i ++){
                queue.enqueue(queue.dequeue());
            }
            StdOut.print(queue.dequeue() + " ");
        }
    }
}
