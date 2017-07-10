/******************************************************************************
 *  Compilation:  javac SelectionBars.java
 *  Execution:    java SelectionBars N
 *  Dependencies: StdDraw.java
 *
 *  Selection sort n random real numbers between 0 and 1, visualizing
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
public class SelectionBars {
    public static void sort(double[] a){
        int N = a.length;
        for(int i = 0; i < N; i ++){
            int min = i;
            for(int j = i+1; j < N; j ++){
                if(less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
            show(a, i, min);
        }
    }

    private static void show(double[] a, int i, int min){
        StdDraw.setYscale(-a.length + i + 1,   i + 1 );
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for (int k = 0; k < i; k++)
            StdDraw.line(k, 0, k, a[k]*0.6);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int k = i; k < a.length; k++)
            StdDraw.line(k, 0, k, a[k]*0.6);
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.line(min, 0, min, a[min]*0.6);
    }
    private static boolean less(double v, double w){
        return v < w;
    }
    private static void exch(double a[], int l, int r){
        double tmp = a[l]; a[l] = a[r]; a[r] = tmp;
    }

    public static void main(String[] args){
        int N = Integer.parseInt(args[0]);

        StdDraw.setCanvasSize(160, 640);
        StdDraw.setXscale(-1, N + 1);
        StdDraw.setPenRadius(0.006);
        double[] a = new double[N];
        for(int i = 0; i < N; i ++){
           a[i]  = StdRandom.uniform(0.0, 1.0);
        }
        sort(a);
    }
}
