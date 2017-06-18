package chapter_1_section_1;

/**
 * User: Bruce Jiang
 * Date: 2017/6/18 9:02
 * Description:
 */
public class BinarySearch {
    /**
     * Binary Search : Returns the element which equals to the given {@code key} in the array
     * Returns -1 if no such value {@code key}
     * @param key the given key
     * @param a the array
     * @return the element equals to the {@code key}
     *           returns -1 if no such value {@code key}
     */
    public static int rank(int key, int[] a){
        int lo = 0;
        int hi = a.length - 1;
        while(lo <= hi){
            int mid = (lo + hi)/2;
            if(key < a[mid]) hi = mid;
            else if(key > a[mid]) lo = mid;
            else return mid;
        }
        return -1;
    }

    /**
     *  Recursive Binary Search
     * @param key the key value
     * @param lo the left endpoint(inclusive)
     * @param hi the right endpoint (inclusive)
     * @param a the array
     * @return the value equals to {@code key} or -1 if no such value
     */
    public static int rank(int key, int lo, int hi, int[] a){
        if(lo > hi) return -1;
        int mid = (lo + hi)/2;
        if(key < a[mid]) return rank(key, lo, mid, a);
        else if(key > a[mid]) return rank(key, mid, hi, a);
        else return mid;
    }




}
