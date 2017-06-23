/****************************************************
 *  Compilation:  javac Accumulator.java
 *  Execution:    javac Accumulator
 *  Dependencies: StdOut.java StdIn.java
 *
 *  Mutable data type that calculates the mean, sample standard
 *  deviation, and sample variance of a stream of real numbers
 *  use a stable, one-pass algorithm.
 *  使用稳定的一次算法计算实数流的平均值，样本标准偏差和样本方差的可变数据类型。
 *
 *  测试：
 *  0.6 6 7 8 9 3 4 6 78 5 3 6 8 5 3
 *  n      = 15
 *  mean   = 10.10667
 *  stddev = 18.91936
 *  var    = 357.94210
 ****************************************************/

package fundamentals.dataAbstraction.api;

import util.api.StdIn;
import util.api.StdOut;

/**
 *  The {@code Accumulator} class is a data type for computing the running
 *  mean, sample standard deviation, and sample variance of a stream of real
 *  numbers. It provides an example of a mutable data type and a streaming
 *  algorithm.
 *  <p>
 *  This implementation uses a one-pass algorithm that is less susceptible
 *  to floating-point roundoff error than the more straightforward
 *  implementation based on saving the sum of the squares of the numbers.
 *  This technique is due to
 *  <a href = "https://en.wikipedia.org/wiki/Algorithms_for_calculating_variance#Online_algorithm">B. P. Welford</a>.
 *  Each operation takes constant time in the worst case.
 *  The amount of memory is constant - the data values are not stored.
 *  <p>
 *  For additional documentation,
 *  see <a href="http://algs4.cs.princeton.edu/12oop">Section 1.2</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @auther Bruce Jiang
 */
public class Accumulator {
    private int n = 0; // number of data values
    private double sum = 0.0; // sample variance * (n -1)
    private double mu = 0.0; // sample mean

    /**
     * Initializes an Accumulator
     */
    public Accumulator(){}

    /**
     * Adds the specified data value to the accumulator
     *
     * @param val the data value
     */
    public void addDataValue(double val){
        this.n ++;
        double delta =  val  - mu;
        mu += delta/n;
        sum += (double)(n-1)/n * delta*delta;
    }

    /**
     * Returns the mean of the data values
     *
     * @return the mean of the data values
     */
    public double mean(){
        return mu;
    }

    /**
     * Returns the sample variance of the data values
     *
     * @return the spample variance of the data values
     */
    public double var(){
        if(n <= 1) return Double.NaN;
        return sum/(n-1);
    }

    /**
     * Returns the sample standard deviation of the data values
     *
     * @return the sample standard deviation of the data values
     */
    public double stddev(){
        return Math.sqrt(this.var());
    }


    /**
     * Returns the number of the data values
     *
     * @return the number of the data values
     */
    public int count(){
        return n;
    }


    /**
     * Returns a string representation of the accumulator
     * @return a string representation of the accumulator
     */
    public String toString(){
        return n + ", " + sum/(n-1) + ", " + mu;
    }

    /**
     * Unit tests the {@code Accumulator} data type
     * Reads in a stream of real number from standard input;
     * adds them to the accumulator; and prints the mean,
     * sample standard deviation, and sample variance to standard output
     * @param args the command-line arguments
     */
    public static void main(String[] args){
        Accumulator ac = new Accumulator();

        while(!StdIn.isEmpty()){
            double x = StdIn.readDouble();
            ac.addDataValue(x);
        }

        StdOut.printf("n      = %d\n", ac.count());
        StdOut.printf("mean   = %.5f\n", ac.mean());
        StdOut.printf("stddev = %.5f\n", ac.stddev());
        StdOut.printf("var    = %.5f\n", ac.var());
    }
}
