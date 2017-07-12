package sorting.elementarySorting;

import sorting.SortTools;
import util.api.In;
import util.api.StdOut;

import java.util.Comparator;

/**
 * @auther Bruce Jiang
 */
public class Shell {
    public static void sort(Comparable[] a){
        int len = a.length;
        int h = 1;
        while(h < len/3) h = 3*h + 1; // 1, 4, 13, 40, 121, 364, ...
        while(h >= 1){
            for(int i = h; i < len; i ++){
                for(int j = i; j >= h && SortTools.less(a[j], a[j - h]); j -= h)
                    SortTools.exch(a, j, j-h);
            }
            assert isHsorted(a, h): "It's not sorted";
            h /= 3;
        }
        assert isSorted(a): "It's not sorted";
    }

    public static void sort(Object[] a, Comparator comparator){
        int N = a.length;
        int h = 1;
        while(h < N/3) h =3*h + 1; // 1, 4, 13, 40, 121, 364, ...
        while(h >= 1){
            for(int i = h; i < N; i ++){
                for(int j = i; j >= h && SortTools.less(a[i], a[j - h], comparator); j -= h){
                    SortTools.exch(a, j, j-h);
                }
            }
            assert SortTools.isSorted(a, comparator): "It's not sorted";
            h /= 3;
        }
        assert SortTools.isSorted(a, comparator): "It's not sorted";
    }


    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


    /***************************************************************************
     *  Check if array is sorted - useful for debugging.
     ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // is the array h-sorted?
    private static boolean isHsorted(Comparable[] a, int h) {
        for (int i = h; i < a.length; i++)
            if (less(a[i], a[i-h])) return false;
        return true;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; Shellsorts them;
     * and prints them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String fileName = args[0].trim();
        In in = new In(fileName);
        String[] strs = in.readAllStrings();
        sort(strs);
        SortTools.show(strs);
    }
}
