/******************************************************************************
 *  Compilation:  javac Selection.java
 *  Execution:    java  Selection < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   http://algs4.cs.princeton.edu/21elementary/tiny.txt
 *                http://algs4.cs.princeton.edu/21elementary/words3.txt
 *
 *  Sorts a sequence of strings from standard input using selection sort.
 */
package sorting.elementarySorting;


import sorting.SortTools;
import util.api.In;

import java.util.Comparator;

/**
 * The {@code Selection} class provides static methods for sorting an array using
 * selection sort
 *
 * @auther Bruce Jiang
 */
public final class Selection{

    //This class should not be instantiated
    private Selection(){}

    /**
     * Rearranges the array in ascending order, using the natural order.
     *
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        int N = a.length;
        for(int i = 0; i < N; i ++){
            int cur = i;
            for(int j = cur+1; j < N; j ++){
                if(SortTools.less(a[j], a[cur])) cur = j;
            }
            SortTools.exch(a, i, cur);
            assert SortTools.isSorted(a, 0, i): "It's not sorted";
        }
        assert SortTools.isSorted(a): "It's not sorted";
    }

    /**
     * Rearranges the array in ascending order, using a comparator
     *
     * @param a the array to be sorted
     * @param comparator the comparator specifying the order
     */
    public void sort(Object[] a, Comparator comparator){
        int N = a.length;
        for(int i = 0; i < N; i ++){
            int min = i;
            for(int j = min+1; j < N; j ++){
                if(SortTools.less(a[j], a[min], comparator)) min = j;
            }
            SortTools.exch(a, i, min);
            assert SortTools.isSorted(a, 0, i, comparator): "It's not sorted"; // when return false, print the comment
        }
        assert SortTools.isSorted(a, comparator): "It's not sorted";
    }

    /**
     * Reads in a sequence of strings from standard input; selection sorts them;
     * and prints them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String fileName = args[0].trim();
        In in = new In(fileName);
        String[] a = in.readAllStrings();
        Selection.sort(a);
        SortTools.show(a);
    }
}
