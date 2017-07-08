package sorting;

import util.api.StdOut;

import java.util.Comparator;

/**
 *  the template of all Sorting Algorithms
 *
 * @auther Bruce Jiang
 */
public class SortTools {

    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // v < w ?
    public static boolean less(Comparable v, Comparable o){
        return v.compareTo(o) < 0;
    }
    // v < w ?
    public static boolean less(Object v, Object w, Comparator comparator){
        return comparator.compare(v, w) < 0;
    }
    // exchange a[i] and a[j]
    public static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }

    // exchange a[i] and a[j]
    public static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void show(Comparable[] a){
        for(Comparable tmp : a)
            StdOut.print(tmp + " ");
        StdOut.println();
    }

    /***************************************************************************
     *  Check if array is sorted - useful for debugging.
     ***************************************************************************/
    public static boolean isSorted(Comparable[] a){
        for(int i = 1; i < a.length; i ++){
            if(less(a[i], a[i-1])) return false;
        }
        return true;
    }
    // is the array a[lo, hi) sorted
    public static boolean isSorted(Comparable[] a, int lo, int hi){
        for(int i = lo+1; i < hi; i ++){
            if(less(a[i], a[i-1])) return false;
        }
        return true;
    }

    public static boolean isSorted(Object[] a, Comparator comparator){
        return isSorted(a, 0, a.length, comparator);
    }

    // is the array a[lo, hi) sorted
    public static boolean isSorted(Object[] a, int lo, int hi, Comparator comparator){
        for(int i = lo+1; i < hi; i ++)
            if(less(a[i], a[i-1], comparator)) return false;
        return true;
    }
}
