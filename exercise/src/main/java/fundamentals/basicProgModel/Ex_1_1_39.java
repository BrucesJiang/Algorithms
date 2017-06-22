package fundamentals.basicProgModel;

/**************************************
 *  % java Ex_1_1_39 100
 *
 *     1000:      998.93
 *    10000:     9999.14
 *   100000:    99999.10
 *  1000000:   999999.49
 *
 *************************************/

import util.api.StdOut;
import util.api.StdRandom;

import java.util.Arrays;

/**
 * @auther Bruce Jiang
 */
public class Ex_1_1_39 {

    public static int findCommon(int N){
        int[] a = new int[N],
               b = new int[N];

        for(int i = 0 ; i < N; i ++){
            a[i] = StdRandom.uniform(100000,1000000);
            b[i] = StdRandom.uniform(100000,1000000);
        }
        Arrays.sort(b);

        int count = 0;
        for(int i = 0; i < N; i ++){
            if(Arrays.binarySearch(b, a[i]) != -1) count ++;
        }
        return count;
    }

    public static void main(String[] args){
        int T = Integer.parseInt(args[0]);

        int[] N = {1000, 10000, 100000, 1000000};


        for(int i = 0; i < N.length; i ++){
            int sum = 0;
            for(int j = 0; j < T; j ++){
                sum  += findCommon(N[i]);
            }
            StdOut.printf("%8d:%12.2f\n", N[i], 1.0*sum/T);
        }
    }
}
