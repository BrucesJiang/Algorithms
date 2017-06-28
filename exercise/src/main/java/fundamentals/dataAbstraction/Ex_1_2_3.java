package fundamentals.dataAbstraction;

import fundamentals.dataAbstraction.api.Interval1D;
import fundamentals.dataAbstraction.api.Interval2D;
import util.api.StdDraw;
import util.api.StdOut;
import util.api.StdRandom;

import java.util.ArrayList;

/**
 * @auther Bruce Jiang
 */
public class Ex_1_2_3 {
    public static void main(String[] args){
        if(args.length < 3) throw new IllegalArgumentException("Invalid Command Line Argument");
        int N = Integer.parseInt(args[0]);
        int min = Integer.parseInt(args[1]);
        int max = Integer.parseInt(args[2]);

        ArrayList<Interval2D> lists = new ArrayList<Interval2D>();
        for(int i = 0; i < N; i ++){
            double x0 = StdRandom.uniform(min);
            double y0 = StdRandom.uniform(min,max);

            Interval1D a = new Interval1D(x0, y0);
            StdDraw.setPenColor(StdDraw.RED);
            double x1 = StdRandom.uniform(min);
            double y1 = StdRandom.uniform(min,max);
            Interval1D b = new Interval1D(x1 , y1);
            Interval2D i2 = new Interval2D(a,b);
            StdOut.println(i2);
            i2.draw();
        }
    }
}
