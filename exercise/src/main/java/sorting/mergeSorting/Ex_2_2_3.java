package sorting.mergeSorting;

import util.api.StdDraw;

/**
 * @auther Bruce Jiang
 */
public class Ex_2_2_3 {
    private final static int VERTICAL = 20;


    private static int numberOfRows;
    private static int row = 0;

    public static void sort(char[] a){
        int N = a.length;
        char[] aux = a.clone();
        for(int len = 1; len < N; len *= 2){
            for(int lo = 0;  lo < N - len; lo += len*2){
                int mid = lo + len -1;
                int hi = Math.min(lo + len + len -1, N-1);
                merge(a, aux, lo, mid, hi);
                show(a, lo, hi);
            }
        }
    }


    private static void merge(char[] a, char[] aux, int lo, int mid, int hi){
        int i = lo, j = mid+1;
        for(int k = lo; k <= hi; k ++){
            if       (i > mid)              a[k] = aux[j++];
            else if (j > hi)                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))  a[k] = aux[j++];
            else                            a[k] = aux[i++];
        }
    }

    private static boolean less(char a, char b){
        return a < b;
    }
    private static void exch(char[] a, int x, int y){
        char tmp = a[x]; a[x] = a[y]; a[y] = tmp;
    }

    private static void show(char[] a, int lo, int hi){
        int y = numberOfRows - row - 1;
        for (int k = 0; k < a.length; k++) {
            if      (k < lo)             StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            else if (k > hi)             StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            else                         StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(k, y, a[k] + "");
        }
        row++;
    }

    //TDD
    public static void main(String[] args){
        char[] a = {'E', 'A', 'S', 'Y', 'Q', 'U', 'E', 'S', 'T', 'O', 'N'};
        int N = a.length;
        numberOfRows = N;
        row = 0;
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
