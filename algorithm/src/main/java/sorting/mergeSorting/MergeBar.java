package sorting.mergeSorting;

import sorting.SortTools;
import util.api.StdDraw;
import util.api.StdRandom;

/**
 * @auther Bruce Jiang
 */
public class MergeBar {
    private final static int CUTOFF = 7;
    private final static int VERTICAL = 70;


    private static int numberOfRows;
    private static int row = 0;

    private MergeBar(){}

    public static void sort(double[] dest){
        double[] src = dest.clone(); // native method , faster, in Object class
        sort(src, dest, 0, dest.length - 1);
        assert SortTools.isSorted(dest) : "Array is not sorted";
    }

    private static void sort(double[] src, double[] dest, int lo, int hi){
        if(hi <= lo + CUTOFF){
            insertionSort(dest, lo, hi);
            show(dest, lo, hi);
            return ;
        }

        int mid = lo + (hi - lo)/2;
        sort(dest, src, lo, mid);
        sort(dest, src, mid+1, hi);

//        if(!less(src[mid], src[mid+1])){
//            System.arraycopy(src, lo, dest, lo, hi - lo + 1);
//            return ;
//        }
        merge(src, dest, lo, mid, hi);
        show(dest, lo, hi);
    }

    private static void merge(double[] src, double[] dest, int lo, int mid, int hi){
        int i = lo, j = mid + 1;
        for(int k = lo; k <= hi; k ++){
            if        (i > mid)                dest[k] = src[j++];
            else if   (j > hi)                 dest[k] = src[i++];
            else if   (less(src[j], src[i]))   dest[k] = src[j++];
            else                                dest[k] = src[i++];
        }
    }

    private static void insertionSort(double[] dest, int lo, int hi){
        for(int i = lo; i <= hi; i ++){
            for(int j = i; j > lo && less(dest[j], dest[j-1]); j --){
                exch(dest, j, j-1);
            }
        }
    }

    private static boolean less(double v, double w){
        return v < w;
    }

    private static void exch(double[] dest, int v, int w){
        double tmp = dest[v]; dest[v] = dest[w]; dest[w] = tmp;
    }

//    private static void show(double[] a){
//        for(double d : a){
//            StdOut.printf("%f\t", d);
//        }
//    }
// draw one row of trace
    private static void show(double[] a, int lo, int hi) {
        double y = numberOfRows - row - 1;
        for (int k = 0; k < a.length; k++) {
            if      (k < lo)             StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            else if (k > hi)             StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            else                         StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledRectangle(k, y + a[k] * 0.25, 0.25, a[k] * 0.25);
        }
        row++;
    }

    public static void main(String[] args){
        int M = Integer.parseInt(args[0].trim());
        int N = Integer.parseInt(args[1].trim());
        if(args.length == 3){
            long seed = Long.parseLong(args[2]); // if the seed exists
            StdRandom.setSeed(seed);
        }

        //generate random number
        double[] a = new double[N];
        double[] b = new double[N];
        for(int i = 0; i < N; i ++){
            a[i] = (1 + StdRandom.uniform(M))/(double)M; // generate a random number between 0 and 1
            b[i] = a[i];
        }

        StdDraw.enableDoubleBuffering();

        // precompute the number of rows
        numberOfRows = 0;
        sort(b);
        numberOfRows = row;
        row = 0;
        StdDraw.clear();

        StdDraw.setCanvasSize(800, numberOfRows*VERTICAL);
        StdDraw.show();
        StdDraw.square(0.5, 0.5, 0.5);
        StdDraw.setXscale(-1, N);
        StdDraw.setYscale(-0.5, numberOfRows);
        StdDraw.show();
        sort(a);
        StdDraw.show();
    }
}
