package sorting.elementarySorting;

import util.api.StdOut;
import util.api.StdRandom;
import util.api.Stopwatch;

/**
 * @auther Bruce Jiang
 */
public class SortCompare {

    public static double time(String alg, Double[] a){
        Stopwatch timer = new Stopwatch();
        if(alg.equals("Insertion")) Insertion.sort(a);
        if(alg.equals("Selection")) Selection.sort(a);
        return timer.elapsedTiem();
    }

    public static double timeRandomInput(String alg, int N, int T){
        double total = 0.0;
        Double[] a = new Double[N];
        //生成T个数组，并排序计时
        for(int t = 0; t < T; t ++){
            //生成数组并排序
            for(int i = 0; i < N; i ++){
                a[i] = StdRandom.uniform();
            }
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args){
        String alg1 = args[0];
        String alg2 = args[1];
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        StdOut.printf("For %d random Doubles\n %s is", N, alg1);
        StdOut.printf(" %.1f times faster than %s\n", t2/t1, alg2);
    }
}
