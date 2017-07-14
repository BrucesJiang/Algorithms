/******************************************************************************
 *  Compilation:  javac Merge.java
 *  Execution:    java Merge < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   http://algs4.cs.princeton.edu/22mergesort/tiny.txt
 *                http://algs4.cs.princeton.edu/22mergesort/words3.txt
 *
 *  Sorts a sequence of strings from standard input using mergesort.
 *
 *  % more tiny.txt
 *  S O R T E X A M P L E
 *
 *  % java Merge  tiny.txt
 *  A E E L M O P R S T X                 [ one string per line ]
 *
 *  % more words3.txt
 *  bed bug dad yes zoo ... all bad yet
 *
 *  % java Merge  words3.txt
 *  all bad bed bug dad ... yes yet zoo    [ one string per line ]
 *******************************************************************************/

 package sorting.mergeSorting;

import sorting.SortTools;
import util.api.In;
import util.api.StdOut;

/**
 * The {@code Merge} class provides static methods for sorting an array using mergesort.
 *
 * @auther Bruce Jiang
 */
public class Merge {
    // This class cannot be instantiated
    private Merge(){}

    //stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo ..hi]
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
        //precondition : a[lo .. mid] and a[mid .. hi] are sorted subarrays
        assert SortTools.isSorted(a, lo, mid);
        assert SortTools.isSorted(a, mid+1, hi);

        //copy to aux[]
        for(int k = lo; k <= hi; k ++){
            aux[k] = a[k];
        }

        //merge back to a[]
        int i = lo, j = mid + 1;
        for(int k = lo; k <= hi; k ++){
            if       (i > mid)                        a[k] = aux[j++];
            else if  (j > hi)                         a[k] = aux[i++];
            else if  (SortTools.less(aux[j], aux[i])) a[k] = aux[j++];
            else                                      a[k] = aux[i++];
        }
        // postcondition: a[lo .. hi] using auxiliary array aux[lo .. ho]
        assert SortTools.isSorted(a, lo, hi);
    }


    //mergesort a[lo, hi] using auxiliary array aux[lo ..hi]
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi){
        if( hi <= lo) return ;
        int mid = lo + (hi - lo)/2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length-1);
        assert SortTools.isSorted(a);
    }

    /***************************************************************************
     *  Index mergesort.
     ***************************************************************************/
    // stably merge a[lo .. mid] with a[mid+1 .. hi] using aux[lo .. hi]
    private static void merge(Comparable[] a, int[] index, int[] aux, int lo, int mid, int hi) {

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = index[k];
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)                              index[k] = aux[j++];
            else if (j > hi)                               index[k] = aux[i++];
            else if (SortTools.less(a[aux[j]], a[aux[i]])) index[k] = aux[j++];
            else                                           index[k] = aux[i++];
        }
    }

    /**
     * Returns a permutation that gives the elements in the array in ascending order.
     * @param a the array
     * @return a permutation {@code p[]} such that {@code a[p[0]]}, {@code a[p[1]]},
     *    ..., {@code a[p[N-1]]} are in ascending order
     */
    public static int[] indexSort(Comparable[] a) {
        int n = a.length;
        int[] index = new int[n];
        for (int i = 0; i < n; i++)
            index[i] = i;

        int[] aux = new int[n];
        sort(a, index, aux, 0, n-1);
        return index;
    }

    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static void sort(Comparable[] a, int[] index, int[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, index, aux, lo, mid);
        sort(a, index, aux, mid + 1, hi);
        merge(a, index, aux, lo, mid, hi);
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from file input; mergesorts them;
     * and prints them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String fileName = args[0].trim();
        In in = new In(fileName);
        String[] a = in.readAllStrings();
        Merge.sort(a);
        show(a);
    }
}
