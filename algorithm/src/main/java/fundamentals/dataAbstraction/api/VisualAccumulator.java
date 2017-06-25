/***********************************************
 *  Compilation:   javac VisualAccumulation.java
 *  Execution:     java VisualAccumulation
 *  Dependencies:  StdDraw.java
 *
 *
 *  Visual accumulator mutable data type
 **********************************************/

package fundamentals.dataAbstraction.api;

import util.api.StdDraw;
import util.api.StdOut;
import util.api.StdRandom;

/**
 * @auther Bruce Jiang
 */
public class VisualAccumulator {
    private double total;
    private int n;

    public VisualAccumulator(int trials, double max){
        StdDraw.setXscale(0, trials);
        StdDraw.setYscale(0, max);
        StdDraw.setPenRadius(0.005);
    }

    public void addDataValue(double value){
        n++;
        total += value;
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(n, value);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(n, mean());
    }


    public double mean(){
        return total/n;
    }

    public String toString(){
        return "Mean ( " + n + " values): " + String.format("%8.5f", mean());
    }


    public static void main(String[] args){
        int T = Integer.parseInt(args[0]);
        VisualAccumulator va = new VisualAccumulator(T, 1.0);
        for(int t = 0; t < T; t ++){
            va.addDataValue(StdRandom.uniform());
        }
        StdOut.println(va);
    }
}
