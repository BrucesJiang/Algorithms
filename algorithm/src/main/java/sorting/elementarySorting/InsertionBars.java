/******************************************************************************
 *  Compilation:  javac InsertionBars.java
 *  Execution:    java InsertionBars N
 *  Dependencies: StdDraw.java
 *
 *  Insertion sort n random real numbers between 0 and 1, visualizing
 *  the results by ploting bars with heights proportional to the values.
 *
 *  % java InsertionBars 20
 *
 ******************************************************************************/
package sorting.elementarySorting;

import util.api.StdDraw;
import util.api.StdRandom;

/**
 * @auther Bruce Jiang
 */
public class InsertionBars {

    public static void sort(double[] a){
        int N = a.length;
        for(int i = 1; i < N; i ++){
            double cur = a[i];
            int j = i;
            while(j>0 && less(cur, a[j-1])){ a[j] = a[j-1]; j --; };
            a[j]=cur;
            show(a, i, j);
        }
    }

    private static boolean less(double w, double v){
        return w < v;
    }
    private static void exch(double[] a, int l, int r){
        double tmp = a[l]; a[l] = a[r]; a[r] = tmp;
    }
    private static void show(double[] a, int i, int cur){
        StdDraw.setYscale(-a.length + i + 1, i + 1);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for(int k = 0; k < cur; k ++){
            StdDraw.line(k, 0, k, a[k]*0.6);
        }
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.line(cur, 0, cur, a[cur]*0.6);
        StdDraw.setPenColor(StdDraw.BLACK);
        for(int k = cur+1; k <= i; k ++){
            StdDraw.line(k, 0, k, a[k]*0.6);
        }
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for(int k = i+1; k < a.length; k ++){
            StdDraw.line(k, 0, k, a[k]*0.6);
        }

    }

    public static void main(String[] args){
        int N = Integer.parseInt(args[0].trim());
        double[] a = new double[N];
        for(int i = 0; i < N; i ++){
            a[i] = StdRandom.uniform();
        }
        StdDraw.setCanvasSize(160, 640);
        StdDraw.setXscale(-1, N + 1);
        StdDraw.setPenRadius(0.006);
        sort(a);
    }
}
