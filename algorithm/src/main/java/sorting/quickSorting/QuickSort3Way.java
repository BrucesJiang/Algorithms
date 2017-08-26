/*******************************************************
 *  Compilation: javac QuickSort3Way.java
 *  Execution:   java QuickSort3Way < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   http://algs4.cs.princeton.edu/23quicksort/tiny.txt
 *                http://algs4.cs.princeton.edu/23quicksort/words3.txt
 *
 *  Sorts a sequence of strings from standard input using 3-way quicksort.
 ******************************************************/

package sorting.quickSorting;

import util.api.In;
import util.api.Out;
import util.api.StdRandom;

/**
 *  The {@code QuickSort3Way} class provides static methods for sorting an array
 *  using quicksort with 3-ways partitioning
 *
 * @auther Bruce Jiang
 */
public class QuickSort3Way
{
    //This class can not be instantiated
    private QuickSort3Way(){}

    public static void sort(Comparable[] a){
        StdRandom.shuffle(a); // shuffle this array
        sort(a, 0, a.length - 1);
        assert isSort(a);
    }

    // quicksort the subarray a[lo ... hi] using 3-way partitioning
    private static void sort(Comparable[] a, int lo, int hi){
        if(lo >= hi) return ;
        int lt = lo, gt = hi;
        Comparable tmp = a[lt];
        int idx = lo;
        // build 3-way partitioning
        while(idx <= gt){
            int cpm = a[idx].compareTo(tmp);
            if            (cpm < 0) exch(a, lt++, idx++);
            else if       (cpm > 0) exch(a, gt--, idx++);
            else           idx ++;
        }
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
        assert isSort(a, lo, hi);
    }

    /****************************************************************
    * Helper sorting function
     *******************************************************************/

    private static boolean less(Comparable v,Comparable w){
        return v.compareTo(w) < 0;
    }

    private static void exch(Object[] a, int i, int j){
        Object tmp = a[i]; a[i] = a[j]; a[j] = tmp;
    }

    /****************************************************************
     *  Check if array is sorted - usefule for debugging
     *******************************************************************/

    private static boolean isSort(Comparable[] a){
        return isSort(a, 0, a.length - 1);
    }
    private static boolean isSort(Comparable[] a, int lo, int hi){
        for(int i = lo+1; i <= hi; i ++){
            if(less(a[i], a[i-1])) return false;
        }
        return true;
    }


    /**
     * Reads in a sequence of strings from input file; 3-way quicksort
     * them.; and prints the sorted-result to standard output in ascending order.
     *
     * @param args the command-line args
     */
    public static void main(String[] args){
        String filePath = args[0].trim();
        In in = new In(filePath);
        Out out = new Out();
        String[] strs = in.readAllStrings();
        QuickSort3Way.sort(strs);
        for(String str : strs){
            out.printf("%s\t", str);
        }
        out.println();
    }
}
