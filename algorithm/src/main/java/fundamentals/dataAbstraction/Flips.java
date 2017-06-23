package fundamentals.dataAbstraction;

import util.api.StdOut;
import util.api.StdRandom;

/**
 * @auther Bruce Jiang
 */
public class Flips {

    public static Counter max(Counter x, Counter y){
        if(x.tally() > y.tally()) return x;
        else                      return y;
    }
    public static void main(String[] args){
        int T = Integer.parseInt(args[0]);

        Counter heads = new Counter("heads");
        Counter tails = new Counter("tails");
        for(int t = 0; t < T; t ++){
            if(StdRandom.bernoulli(0.5))
                  heads.increment();
            else tails.increment();
        }


        StdOut.println(heads);
        StdOut.println(tails);
        int d = heads.tally() - tails.tally();
        StdOut.println("delta: " + Math.abs(d));
    }
}
