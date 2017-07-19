/******************************************************************************
 *  Compilation:  javac MergeImprove.java
 *  Execution:    java MergeImprove
 *  Dependencies: StdOut.java In.java SortTools.java
 *  Data files:   http://algs4.cs.princeton.edu/22mergesort/tiny.txt
 *                http://algs4.cs.princeton.edu/22mergesort/words3.txt
 *
 *  Sorts a sequence of strings from standard input using an
 *  optimized version of mergeSorting.
 *
 ******************************************************************************/

package sorting.mergeSorting;

import sorting.SortTools;
import util.api.In;
import util.api.StdOut;

import java.util.Comparator;

/**
 *  The {@code MergeImprove} provides static methods for sorting an
 *  array using an optimized version of mergeSorting.
 *
 * @auther Bruce Jiang
 */
public class MergeImprove {

    private final static int CUTOFF = 7; // cutoff to insertion sort

    //This class cannot be instantiated
    private MergeImprove(){}

    /**
     * Rearranges the array in ascending order, using the natural order.
     *
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a){
        Comparable[] aux = a.clone();
        sort(aux, a, 0, a.length -1);
        assert SortTools.isSorted(a): "Array is not sorted";
    }


    private static void sort(Comparable[] src, Comparable[] dist, int lo, int hi){
        // if ( hi <= lo) return ;
        if(hi <= lo + CUTOFF){
            insertionSort(dist, lo, hi);
            return ;
        }

        int mid = lo + (hi - lo)/2;
        sort(dist, src, lo, mid);
        sort(dist, src, mid+1, hi);

        if(!less(src[mid+1], src[mid])){
            System.arraycopy(src, lo, dist, lo, hi - lo +1);
            return ;
        }
        merge(src, dist, lo, mid, hi);
    }

    // sort a[lo .. hi] using insertion sort
    private static void insertionSort(Comparable[] a, int lo, int hi){
        for(int i = lo; i <= hi; i ++){
            for(int j = i; j > lo && less(a[j], a[j-1]); j --){
                SortTools.exch(a, j, j-1);
            }
        }
    }

    private static void merge(Comparable[] src, Comparable[] dist, int lo, int mid, int hi){
        //precondition: src[lo .. mid] and src[mid+1, hi] is sorted subarray
        assert SortTools.isSorted(src, lo, mid) : "Left part is not sorted";
        assert SortTools.isSorted(src, mid+1, hi) : "Right part is not sorted";

        int i = lo, j = mid + 1;

        for(int k = lo; k <= hi; k ++){
            if        (i > mid)  dist[k] = src[j++];
            else if   (j > hi)  dist[k] = src[i++];
            else if   (less(src[i], src[j])) dist[k] = src[i++];
            else                              dist[k] = src[j++];
        }
        //postcondition: dist[lo .. hi] is sorted subarray
        SortTools.isSorted(dist, lo, hi);
    }


    /**********************************************************************
     *  Version that takes Comparator as argument
     **********************************************************************/

    /**
     * Rearranges the array in ascending order, using the provider order.
     *
     * @param a the array to be sorted
     * @param comparator the comparator that defines the total order
     */
    public static void sort(Object[] a, Comparator comparator){
        Object[] aux = a.clone();
        sort(aux, a, 0, a.length - 1, comparator);
        assert SortTools.isSorted(a, comparator) : "Array is not sorted";
    }

    private static void merge(Object[] src, Object[] dist, int lo, int mid, int hi, Comparator comparator){
        assert SortTools.isSorted(dist, lo, mid, comparator): "Right part is not sorted";
        assert SortTools.isSorted(dist, mid+1, hi, comparator) : "Left part is not sorted";

        int i = lo, j = mid + 1;
        for(int k = lo; k <= hi; k ++){
            if          (i > mid)                            dist[k] = src[j++];
            else  if   (j > hi)                              dist[k] = src[i++];
            else  if   (less(src[i], src[j], comparator))   dist[k] = src[i++];
            else                                             dist[k] = src[j++];
        }

        //postcondition dist[lo .. hi] is sorted
        assert SortTools.isSorted(dist,comparator);
    }

    private static void sort(Object[] src, Object[] dist, int lo, int hi, Comparator comparator){
        if (hi <= lo + CUTOFF){
            insertionSort(dist, lo, hi, comparator);
            return ;
        }

        int mid = lo + (hi - lo)/2;
        sort(dist, src, lo, mid, comparator);
        sort(dist, src, mid+1, hi, comparator);

        /***
         * Add a judgement condition, if a[mid] <= a[mid+1], the array is sorted and skips merge method
         * otherwise, do merge method. We find it don't influence the recursive call but the executable
         * time of the subarray become a linear.
         */
        if(!less(src[mid+1], src[mid], comparator)){
            //native method
            System.arraycopy(src, lo, dist, lo, hi - lo +1);
            return ;
        }
        merge(src, dist, lo, mid, hi, comparator);
    }

    //sort a[lo .. hi] using insertion sort
    private static void insertionSort(Object[] a, int lo, int hi, Comparator comparator){
        for(int i = lo; i <= hi; i ++){
            for(int j = i; j > lo && less(a[j], a[j-1], comparator); j--){
                exch(a, j, j-1);
            }
        }
    }

    /*******************************************************************
     *  Utility methods.
     *******************************************************************/

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // is a[i] < a[j]?
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    // is a[i] < a[j]?
    private static boolean less(Object a, Object b, Comparator comparator) {
        return comparator.compare(a, b) < 0;
    }



    // print array to standard output
    private static void show(Object[] a){
        for(Object o : a){
            StdOut.printf("%s\t", o);
        }
    }

    /**
     * Reads in a sequence of strings from standard input: mergesorts them
     * (using an optimized version of mergeSorting):
     * and prints them to standard output in ascending order
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args){
        String fileName = args[0].trim();

        In in = new In(fileName);
        String[] a = in.readAllStrings();
        MergeImprove.sort(a);
        show(a);
    }
}
