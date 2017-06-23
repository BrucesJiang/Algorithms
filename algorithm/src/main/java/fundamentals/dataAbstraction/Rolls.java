package fundamentals.dataAbstraction;

import util.api.StdOut;
import util.api.StdRandom;

/**
 *  模拟T次掷色子
 * @auther Bruce Jiang
 */
public class Rolls {
    public static void main(String[] args){
        long start = System.currentTimeMillis();
        int T = Integer.parseInt(args[0]);
        final int SIDES = 6;
        Counter[] rolls = new Counter[SIDES + 1];
        for(int i = 1; i <= SIDES; i ++){
            rolls[i] = new Counter(i +"'s");
        }

        for(int i = 0; i < T; i ++){
            int result = StdRandom.uniform(1, SIDES +1);
            rolls[result].increment();
        }

        for(int i = 1; i <= SIDES; i ++){
            StdOut.println(rolls[i]);
        }
        StdOut.println(System.currentTimeMillis() - start);
    }
}
