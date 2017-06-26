package fundamentals.dataAbstraction;

import fundamentals.dataAbstraction.api.Interval1D;
import util.api.StdIn;

import java.util.ArrayList;

/**
 * @auther Bruce Jiang
 */
public class Ex_1_2_2 {
    public static void main(String[] args){
        if(args.length == 0) throw new IllegalArgumentException("We Need Command Line Argument");
        int N = Integer.parseInt(args[0]);
        ArrayList<Interval1D> lists = new ArrayList<Interval1D>();
        for(int i = 0; i < N; i ++){
            double min = StdIn.readDouble();
            double max = StdIn.readDouble();
            lists.add(new Interval1D(min, max));
        }

        for(int i = 0; i < N - 1; i ++){
            for(int j = i + 1; j < N; j ++){
                boolean flag = lists.get(i).intersects(lists.get(j));
                if(flag){
                    System.out.println(lists.get(i) + " <-> " + lists.get(j));
                }
            }
        }
    }
}
