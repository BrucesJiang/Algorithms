/**************************************************************************
 *  Compilation:    javac Stopwatch.java
 *  Execution:      java Stopwatch n
 *
 *  A utility class to measure the running time (wall clock) of a program.
 *
 *  % java Stopwatch 100000000
 *  6.666667e+11 (0.66 seconds)
 *  6.666667e+11 (7.43 seconds)
 *
 ***************************************************************************/

package util.api;

/**
 *  The {@code Stopwatch} data type is for measuring the time that elapses between the
 *  start and end of a programming task (wall-clock time).
 *
 *  See {@link StopwatchCPU} for a version that measure CPU time.
 *
 * @auther Bruce Jiang
 */
public class Stopwatch {
    private final long start;

    /**
     * Initializes a new stopwatch
     */
    public Stopwatch(){
        start = System.currentTimeMillis();
    }

    public double elapsedTiem(){
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }

    /**
     * Unit tests the {@code Stopwatch} data type.
     * Takes a command-line argument {@code n} and computes the
     * sum of the square roots of the first {@code n} positive integers,
     * first using {@code Math.sqrt()}, then using {@code Math.pow()}.
     * It prints to standard output the sum and the amount of time to
     * compute the sum. Note that the discrete sum can be approximated by
     * an integral - the sum should be approximately 2/3 * (n^(3/2) - 1).
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);

        //sum of squre roots of integers from 1 to n using Math.sqrt(x).
        Stopwatch timer1 = new Stopwatch();
        double sum1 = 0.0;
        for(int i = 1; i <= n; i ++){
            sum1 += Math.sqrt(i);
        }

        double time1 = timer1.elapsedTiem();
        StdOut.printf("%e (%.2f seconds)\n", sum1, time1);

        //sum of square roots of integers from 1 to n using Math.pow(x, 0.5)
        Stopwatch timer2 = new Stopwatch();
        double sum2 = 0.0;
        for(int i = 0; i <= n; i ++){
            sum2 += Math.pow(i, 0.5);
        }

        double time2 = timer2.elapsedTiem();

        StdOut.printf("%e (%.2f seconds)\n", sum2, time2);
    }
}
