/******************************************************************************
 *  Compilation:  javac Quick.java
 *  Execution:    java Quick < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   http://algs4.cs.princeton.edu/23quicksort/tiny.txt
 *                http://algs4.cs.princeton.edu/23quicksort/words3.txt
 *
 *  Sorts a sequence of strings from standard input using quicksort.
 *
 ***************************************************/

package sorting.quickSorting;

import util.api.In;
import util.api.StdOut;
import util.api.StdRandom;

/**
 *  The {@code Quick} class provides static methods for sorting an
 *  array and selecting the ithe smallest element in an array using qiucksort
 *
 * @auther Bruce Jiang
 */
public class Quick {

    //This class cannot be instantiated
    private Quick(){}

    public static void sort(Comparable[] a){
        StdRandom.shuffle(a); // shuffle the array
        sort(a, 0, a.length -1); // rearranges the array
    }

    //sort subarray a[lo .. hi]
    private static void sort(Comparable[] a, int lo, int hi){
        if(hi <= lo) return ;
        int cur = partition(a, lo, hi);
        sort(a, lo, cur-1);
        sort(a, cur+1, hi);
    }

    // part subarray a[lo .. hi]
    private static int partition(Comparable[] a, int lo, int hi){
        int i = lo, j = hi+1; // left scanner-point and right scanner-point
        Comparable v = a[lo]; // the scanner-item

        while(i < j){
            while(--j > i && less(v, a[j]));
            if(j > i) a[i] = a[j];
            while(++i < j && less(a[i], v));
            if(j > i) a[j] = a[i];
        }
        a[i] = v;
        return i;
    }

    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }


    private static void show(String[] strs){
        for(String str : strs){
            StdOut.print(str + "\t");
        }
    }

    /**
     * The {@code Quick} data type test.Reads strings from file, rearranges them and
     * Output them into standard output
     *
     * @param args the command-line args
     */
    public static void main(String[] args){
        String fileName = args[0].trim();

        In in = new In(fileName);

        String[] strs = in.readAllStrings();
        Quick.sort(strs); // rearranges them
        show(strs); // show sorted result
    }
}
