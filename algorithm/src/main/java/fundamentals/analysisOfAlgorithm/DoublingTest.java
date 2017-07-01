package fundamentals.analysisOfAlgorithm;

import util.api.StdOut;
import util.api.StdRandom;
import util.api.Stopwatch;

/**
 * @auther Bruce Jiang
 */
public class DoublingTest {

    public static void main(String[] args){
        for(int i = 250; true; i += i){
            double time = timeTrial(i);
            StdOut.printf("%7d %5.1f\n", i, time);
        }
    }

    public static double timeTrial(int N){
        int MAX = 100000;
        int[] a = new int[N];
        for(int i = 0; i < N; i ++){
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        Stopwatch timer = new Stopwatch();
        int cnt = TreeSum.count(a);
        return timer.elapsedTiem();

    }
}
