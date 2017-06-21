package fundamentals.basicProgModel;

/**************************************************
 *
 *
 *
 *************************************************/

import util.api.StdIn;
import util.api.StdOut;

import java.util.Arrays;

/**
 * @auther Bruce Jiang
 */
public class Ex_1_1_29 {
    /**
     * Returns the {@code c} of the elements which equals {@code key} in the array {@code a}
     * @param a the array
     * @param key the key
     * @return the {@code c} of elements which equals {@code key} in the array {@code a}
     *            return -1 if no such value in the array
     */
    public static int count(int[] a, int key){
        int r = rank(a, key);
        if(r == -1){
            return -1;
        }
        int c = 1;
        while(r+1 < a.length && a[r] == a[r+1]){ c++; r++; }
        return c;
    }

    /**
     * Returns the very left rank of the {@code key} in the array. returns -1 if no such value in the array
     * @param a the array
     * @param key the {@code key}
     * @return returns the very left rank of the {@code key} in the array.
     *          Return -1 if no such value in the array
     */
    public static int rank(int[] a, int key){
        int hi = a.length -1;
        int lo = 0;
        while(lo <= hi){
            int mid = lo + (hi - lo)/2;

            if      (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else{
                while(--mid >= 0  && a[mid] == key);
                return mid + 1;
            }
        }
        return -1;
    }

    public static boolean verify(int r, int c , int key, int[] a){
        for(int i = 0; i < a.length; i ++){
            if((i < r || i > r+c-1) && a[i] == key || (i >= r && i <= r+c-1) && a[i] != key) return false;
        }
        return true;
    }

    public static void main(String[] args){
        int[] a= {2,3,10,10,1,4,5,67,3,2,6,8,4};
        Arrays.sort(a);
        String indices = "", values = "";
        for(int i = 0; i < a.length; i ++){
            indices += String.format("%4d", i);
            values += String.format("%4d", a[i]);
        }
        StdOut.println(indices + "\n" + values);
        while(!StdIn.isEmpty()){
            int key = StdIn.readInt();
            int r = rank(a, key);
            int c = count(a, key);
            StdOut.printf("(%d, %d) -> %s\n", r, c, verify(r,c,key,a));
        }
    }
}
