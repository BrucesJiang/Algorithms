package sorting.mergeSorting;

import util.api.In;
import util.api.StdOut;

/**
 * The {@code Merge} class provides static methods for sorting an array using bottom-up mergesort.
 *
 * @auther Bruce Jiang
 */
public class MergeBU {


    //This class cannot be instantiated
    private MergeBU(){}


    // stably merge a[lo..mid] with a [mid+1, hi] using aux[lo..hi]
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
        //copy to aux[]
        for(int k = lo; k <= hi; k ++){
            aux[k] = a[k];
        }

        //merge back to a[]
        int i = lo, j = mid+1;
        for(int k = lo; k <= hi; k ++){
            if        (i > mid)                a[k] = aux[j++];
            else if   (j > hi)                 a[k] = aux[i++];
            else if   (less(aux[j], aux[i]))   a[k] = aux[j++];
            else                               a[k] = aux[i++];
        }
    }

    public static void sort(Comparable[] a){
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        for(int len = 1; len < N; len *= 2){
            for(int lo = 0; lo < N - len; lo += len*2){
                int mid = lo + len -1;
                int hi = Math.min(lo+len+len-1, N-1);
                merge(a, aux, lo, mid, hi);
            }
        }
        assert isSorted(a);
    }


    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int x, int y){
        Comparable tmp = a[x]; a[x] = a[y]; a[y] = tmp;
    }

    // Print array to standard output
    private static void show(Comparable[] a){
        for(Comparable c : a){
            StdOut.print(c + "\t");
        }
    }

    /***************************************************************************
     *  Check if array is sorted - useful for debugging.
     ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    /**
     * Reads in a sequence of strings from file ; bottom-up mergesorts them.
     * and prints them to standard output in ascending order.
     * @param args the command-line arguments
     */
    public static void main(String[] args){
        In in = new In(args[0].trim());

        String[] a = in.readAllStrings();
        MergeBU.sort(a);
        show(a);
    }
}
