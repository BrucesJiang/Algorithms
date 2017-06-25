/**********************************
 *  Compilation:  javac Rational.java
 *  Execution:    java Rational
 *  Dependencies: StdOut.java
 *
 *  Immutable of ADT for Rational numbers.
 *
 *  Invariants:
 *  -------------------
 *
 *  - gcd(num, den) = 1 i.e, the rational number is in reduced form
 *  - den >= 1 , the denominator is always a positive integer
 *  - 0/1 is the unique representation of 0
 *
 *  We employ some tricks to stave off overflow, but if you
 *  need arbitrary precision rationals, use BigRational.java.
 *
 *
 **********************************/

package fundamentals.dataAbstraction;

import java.util.Comparator;

/**
 * @auther Bruce Jiang
 */
public class Rational implements Comparable<Rational> {
    private static Rational zero = new Rational(0, 1);

    private long num; // the numerator
    private long den; // the denominator

    /**
     * Create and initializes a new Rational object
     * @param numerator the numerator
     * @param denominator the denominator
     */
    public Rational(long numerator, long denominator){
        // deal with x/0
        if(denominator == 0){
            throw new ArithmeticException("Denominator is zero");
        }

        // reduce fraction
        long g = gcd(numerator, denominator);
        num = numerator/g;
        den = denominator/g;

//        // only needed for negative numbers
//        if (den < 0) {
//            den = -den;
//            num = -num;
//        }
    }


    /**
     * Returns the numerator
     * @return  the numerator
     */
    public long numerator(){ return num; }

    /**
     * Returns the denominator
     * @return the denominator
     */
    public long denominator(){ return den; }

    /**
     * return {-1, 0, +1} if this < that, this = that, this > that
     * @param that the other rational
     * @return {@code -1} this < that
     *         {@code 0} this = that
     *         {@code +1} this > that
     */
    @Override
    public int compareTo(Rational that) {
        return 0;
    }



    // return gcd(/n/, /d/)
    private long gcd(long n, long d){
        if(n < 0) n = -n;
        if(d < 0) d = -d;
        if(0 == d) return n;
        return gcd(d, n%d);
    }


}
