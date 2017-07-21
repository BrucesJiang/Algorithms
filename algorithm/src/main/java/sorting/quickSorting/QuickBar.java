package sorting.quickSorting;

import util.api.StdDraw;
import util.api.StdIn;
import util.api.StdRandom;

/**
 *
 *
 * @auther Bruce Jiang
 */
public class QuickBar {
    private static int rows;
    private static int row = 0;
    private static final int VERTICAL = 50;

    //This class cannot be instantiated
    private QuickBar(){}

    public static void sort(double[] a){
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(double[] a, int lo, int hi){
        if(hi <= lo) return;
        int partition = partition(a, lo, hi);
        show(a, lo, partition, hi);
        sort(a, lo, partition-1);
        sort(a, partition+1, hi);

    }

    private static int partition(double[] a, int lo, int hi){
        int i = lo;
        int j = hi + 1;
        double v = a[lo];
        while (true) {

            // find item on lo to swap
            while (less(a[++i], v))
                if (i == hi) break;

            // find item on hi to swap
            while (less(v, a[--j]))
                if (j == lo) break;      // redundant since a[lo] acts as sentinel

            // check if pointers cross
            if (i >= j) break;

            exch(a, i, j);
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    private static boolean less(double a, double b){
        return a - b < 0;
    }
    private static void exch(double[] a, int x, int y){
        double tmp = a[x]; a[x] = a[y]; a[y] = tmp;
    }

    private static void show(double[] a, int lo, int partition, int hi){
        double y = rows - row - 1;
        for (int k = 0; k < a.length; k++) {
            if      (k < lo)             StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            else if (k > hi)             StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            else if (k == partition) StdDraw.setPenColor(StdDraw.BOOK_RED);
            else                         StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledRectangle(k, y + a[k] * 0.25, 0.25, a[k] * 0.25);
        }
        row++;
    }
    /**
     * Reads {@code M} and {@code N} representing of range of random number and the size of the array,
     * generate {@code N} randomly number, rearranges the array and show on panel.
     *
     * @param args the command-line args
     */
    public static void main(String[] args){
        int M = Integer.parseInt(args[0].trim());
        int N = Integer.parseInt(args[1].trim());

        double[] a = new double[N];
        double[] b = new double[N];
        for(int i = 0; i < N; i ++){
            a[i] = StdRandom.uniform(M)/(double)M;
            b[i] = a[i];
        }
        StdDraw.enableDoubleBuffering();

        // precompute the number of rows
        rows = 0;
        sort(b);
        rows = row;
        row = 0;
        StdDraw.clear();

        StdDraw.setCanvasSize(800, rows*VERTICAL);
        StdDraw.show();
        StdDraw.square(0.5, 0.5, 0.5);
        StdDraw.setXscale(-1, N);
        StdDraw.setYscale(-0.5, rows);
        StdDraw.show();
        sort(a);
        StdDraw.show();
    }

}
