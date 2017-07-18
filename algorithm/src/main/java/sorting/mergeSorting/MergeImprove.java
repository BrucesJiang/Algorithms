package sorting.mergeSorting;

import sorting.SortTools;
import util.api.In;
import util.api.StdOut;

import java.util.Comparator;

/**
 *  The {@code MergeImprove} provides static methods for sorting an
 *  array using an optimized version of mergesort.
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
        sort(aux, a, 0, a.length);
        assert SortTools.isSorted(a): "Array is not sorted";
    }


    private static void sort(Comparable[] src, Comparable[] dist, int lo, int hi){
        // if ( hi <= lo) return ;
        if(hi <= lo + CUTOFF){
            insertionSort(dist, lo, hi);
            return ;
        }


    }

    // sort a[lo .. hi] using insertion sort
    private static void insertionSort(Comparable[] a, int lo, int hi){
        for(int i = lo; i <= hi; i ++){
            for(int j = i; j > lo && SortTools.less(a[j], a[j-1]); j --){
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
            else if   (SortTools.less(src[i], src[j])) dist[k] = src[i++];
            else                                        dist[k] = src[j++];
        }
        //postcondition: dist[lo .. hi] is sorted subarray
        SortTools.isSorted(dist, lo, hi);
    }


    /************************************************
     * Check if array is sorted - useful for debugging
     ************************************************/


    // print array to standard output
    private static void show(Object[] a){
        for(Object o : a){
            StdOut.println(a);
        }
    }

    /**
     * Reads in a sequence of strings from standard input: mergesorts them
     * (using an optimized version of mergesort):
     * and prints them to standard output in ascending order
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args){
        String fileName = args[0].trim();

        In in = new In(fileName);
        String[] a = in.readAllStrings();

        show(a);
    }
}
