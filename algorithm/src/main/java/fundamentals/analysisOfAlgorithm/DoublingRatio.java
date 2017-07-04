package fundamentals.analysisOfAlgorithm;

import util.api.StdOut;
import util.api.StdRandom;

/*************************************************************************
 *  Compilation:  javac DoublingRatio.java
 *  Execution:    java DoublingRatio
 *  Dependencies: ThreeSum.java Stopwatch.java StdRandom.java StdOut.java
 *
 *
 *  % java DoublingTest 
 *        250     0.0   2.0
 *        500     0.0   1.5
 *       1000     0.1   9.2
 *       2000     0.5   9.0
 *       4000     3.3   6.6
 *       8000    25.6   7.8
 *   ... 
 *
 *************************************************************************/

public class DoublingRatio {

    public static double timeTrial(int N) {
        int MAX = 1000000;
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        Stopwatch s = new Stopwatch();
        int cnt = ThreeSum.count(a);
        return s.elapsedTime();
    }


    public static void main(String[] args) { 
        double prev = timeTrial(125);
        for (int N = 250; true; N += N) {
            double time = timeTrial(N);
            StdOut.printf("%6d %7.1f %5.1f\n", N, time, time/prev);
            prev = time;
        } 
    } 
} 

