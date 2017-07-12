package sorting.elementarySorting;

import sorting.SortTools;
import util.api.StdDraw;
import util.api.StdIn;
import util.api.StdRandom;

/**
 * @auther Bruce Jiang
 */
public class Ex_2_1_24 {
    public static void sort(double[] a){
        int N = a.length;
        for(int i = 0; i < N - 1; i ++){
            for(int j = i + 1; j < N; j ++){
                if(SortTools.less(a[j], a[i])) SortTools.exch(a, i, j);
            }
            assert SortTools.isSorted(a, 0, i);
            show(a, i);
        }
        assert SortTools.isSorted(a): "It's not sorted";
    }

    private static void show(double[] a, int min){
        StdDraw.setYscale(-a.length + min + 1, min + 1);
        StdDraw.setPenColor(StdDraw.BLACK);
        for(int i = 0 ; i < min; i ++){
            StdDraw.line(i, 0, i, a[i]*0.6);
        }
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.line(min, 0, min, a[min]*0.6);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for(int i = min + 1; i < a.length; i ++){
            StdDraw.line(i, 0, i, a[i]*0.6);
        }
    }
    public static void main(String[] args){
        int N = Integer.parseInt(args[0].trim());

        double[] a = new double[N];
        for(int i = 0; i < N; i ++){
            a[i] = StdRandom.uniform(0.0, 1.0);
        }

        StdDraw.setCanvasSize(160, 600);
        StdDraw.setPenRadius(0.006);
        StdDraw.setXscale(-1, N + 1);
        sort(a);
    }
}
