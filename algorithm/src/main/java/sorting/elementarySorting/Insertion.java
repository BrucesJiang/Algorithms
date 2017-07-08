/******************************************************************************
 *  Compilation:  javac Insertion.java
 *  Execution:    java  Insertion < input.txt
 *  Dependencies: In.java SortTools.java
 *  Data files:   http://algs4.cs.princeton.edu/21elementary/tiny.txt
 *                http://algs4.cs.princeton.edu/21elementary/words3.txt
 *
 *  Sorts a sequence of strings from file input using insertion sort.
 */

package sorting.elementarySorting;

import sorting.SortTools;
import util.api.In;

import java.util.Comparator;

/**
 * The {@code Insertion} class provides static methods for sorting an array
 * with insertion sort
 *
 * @auther Bruce Jiang
 */
public class Insertion {

    //this class cannot be instantiated
    private Insertion(){}

    /**
     * Rearranges the array in ascending order, using the nature order
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a){
        int N = a.length;
        for(int i = 1 ; i < N; i ++){
            for(int j = i; j>0 && SortTools.less(a[j], a[j-1]); j --){
                SortTools.exch(a, j, j-1);
            }
            assert SortTools.isSorted(a, 0, i): "It's not sorted";
        }
        assert SortTools.isSorted(a): "It's not sorted";
    }

    /**
     * Rearranges the array in ascending order, using the comparator
     *
     * @param a the arrray to be sorted
     * @param comparator the specified comparator
     */
    public static void sort(Object[] a, Comparator comparator){
        int N = a.length;
        for(int i = 1; i < N; i ++){
            for(int j = i; j>0 && SortTools.less(a[j], a[j-1], comparator); j --){
                SortTools.exch(a, j, j-1);
            }
            assert SortTools.isSorted(a, 0, i, comparator): "It's not sorted";
        }
        assert SortTools.isSorted(a, comparator): "It's not sorted";
    }

    /**
     * Reads in a sequence of strings from file input; selection sorts them;
     * and prints them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args){
        String fileName = args[0].trim();
        In in = new In(fileName);
        String[] strs = in.readAllStrings();
        sort(strs);
        SortTools.show(strs);
    }
}
