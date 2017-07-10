package sorting.elementarySorting;

import sorting.SortTools;

/**
 * @auther Bruce Jiang
 */
public class Shell {
    public static void sort(Comparable[] a){
        int len = a.length;
        int h = 1;
        while(h < len/3) h = 3*h + 1; // 1, 4, 13, 40, 121, 364, ...
        while(h >= 1){
            for(int i = h; i < len; i ++){
                for(int j = i; j >= h && SortTools.less(a[j], a[j - h]); j -= h)
                    SortTools.exch(a, j, j-h);
            }
            h /= 3;
        }
    }
}
