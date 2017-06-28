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

import util.api.StdOut;

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
        num = numerator / g;
        den = denominator / g;

        // only needed for negative numbers,
        // n % p 得到结果的正负由被除数n决定,与p无关,
        if (den < 0) {
            den = -den;
            num = -num;
        }
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
     * Create and return a new rational (left.num + right.num)/(left.den + right.den)
     * @param left the left rational number
     * @param right the right rational number
     * @return (left.num + right.num)/(left.den + right.den)
     */

    public static Rational mediant(Rational left, Rational right){
        assert left.num+right.num > Long.MAX_VALUE || left.den+right.den > Long.MAX_VALUE : "Long value overflow";
        assert left.num+right.num < Long.MIN_VALUE || left.den+right.den < Long.MIN_VALUE : "Long value underflow";
        return new Rational(left.num + right.num, left.den + right.den);
    }

    /**
     * Returns this rational pluses that rational, staving off overflow
     * @param that the other rational
     * @return the result of two rationals
     */
    public Rational plus(Rational that){

        if(this.compareTo(zero) == 0) return that;
        if(that.compareTo(zero) == 0) return this;


        //Find gcd of numerators and denominators
        long f = gcd(this.num, that.num);
        long g = gcd(this.den, that.den);

        //add across-product terms for numerator
        Rational r = new Rational((this.num / f)*(that.den / g) + (that.num / f)*(this.den / g), (this.den)*(that.den) / g);

        //multiply back in
        r.num *= f;
        return r;
    }

    /**
     * Return this - that
     * @param that the other rational
     * @return this - that
     */
    public Rational minus(Rational that){
        return this.plus(that.negate());
    }

    /**
     * Return that * this, staving off overflow as much as possible by across-cancellation
     * @param that the other rational number
     * @return that*this
     */
    public Rational times(Rational that){
        //reduce p1/q2 and p2/q1, then multiply, where a = p1/q2 and b = p2/q1
        Rational a = new Rational(this.num, that.den);
        Rational b = new Rational(that.num, this.den);
        return new Rational(a.num * b.num, a.den * b.den);
    }

    /**
     * Returns this/that
     * @param that the other rational
     * @return this/that
     */
    public Rational divide(Rational that){
        return this.times(this.reciprocal());
    }

    /**
     * Returns 1/this
     * @return 1/this
     */
    public Rational reciprocal(){
        return new Rational(den, num);
    }
    /**
     * Return -this
     * @return -this
     */
    public Rational negate(){
        return new Rational(-num, den);
    }

    /**
     * Return /this/
     * @return /this/
     */
    public Rational abs(){
        if(this.num > 0) return this;
        else              return this.negate();
    }

    /**
     * Return double precision representation of this rational number
     * @return double precision representation of this rational number
     */
    public double toDouble(){
        return (double)num/den;
    }

    /**
     * Returns a string representation of this rational number
     * @return a string representation of this rational number
     */
    public String toString(){
        return "[ " + this.num + ", " + this.den + "]";
    }

    /**
     * Compares this rational to that rational
     * @param that the other rational number
     * @return  {@code true} if this rational number is equal to that rational number
     *           {@code false} otherwise
     */
    public boolean equals(Object that){
        if(this == that) return true;
        if(that == null) return false;
        if(this.getClass() != that.getClass()) return false;
        return this.compareTo((Rational)that) == 0;
    }

    /**
     * Returns the integer hash code which consistents with {@code #equals()} and {@code #compareTo()}
     * @return the integer hash code
     */
    public int hashCode(){
        return this.toString().hashCode();
    }

    /**
     * return {-1, 0, +1} if this < that, this = that, this > that
     * @param that the other rational
     * @return {@code -1} this < that
     *         {@code 0} this = that
     *         {@code +1} this > that
     */
    @Override
    public int compareTo(Rational that) {
        assert this.num*that.den > Long.MAX_VALUE : "Long value overflow";
       long left = this.num * that.den;
        assert this.num*that.den > Long.MAX_VALUE : "Long value overflow";
       long right = that.num * this.den;

       if(left < right) return -1;
       if(left > right) return +1;
       return 0;
    }

    // return gcd(/n/, /d/)
    private long gcd(long n, long d){
        if(n < 0) n = -n;
        if(d < 0) d = -d;
        if(0 == d) return n;
        else       return gcd(d, n % d);
    }

    // return lcm(|m|, |n|)
    private long lcm(long m, long n) {
        if (m < 0) m = -m;
        if (n < 0) n = -n;
        return m * (n / gcd(m, n));    // parentheses important to avoid overflow
    }
    /**
     * Unit tests
     * @param args the command-line argument
     */
    public static void main(String[] args){
        Rational x, y, z;

        // 1/2 + 1/3 = 5/6
        x = new Rational(1, 2);
        y = new Rational(1, 3);
        z = x.plus(y);
        StdOut.println(z);

        // 8/9 + 1/9 = 1
        x = new Rational(8, 9);
        y = new Rational(1, 9);
        z = x.plus(y);
        StdOut.println(z);

        // 0/1 + 1/2
        x = new Rational(0, 1);
        y = new Rational(1,2);
        z = x.plus(y);
        StdOut.println(z);

        x = new Rational(9, 8);
        y = new Rational(15, 4);
        z = x.plus(y);
        StdOut.println(x + ",\n " + y + ",\n" + z);

        // 1/200000000 + 1/300000000 = 1/120000000
        x = new Rational(1, 200000000);
        y = new Rational(1, 300000000);
        z = x.plus(y);
        StdOut.println(z);

        // 1073741789/20 + 1073741789/30 = 1073741789/12
        x = new Rational(1073741789, 20);
        y = new Rational(1073741789, 30);
        z = x.plus(y);
        StdOut.println(z);

        //  4/17 * 17/4 = 1
        x = new Rational(4, 17);
        y = new Rational(17, 4);
        z = x.times(y);
        StdOut.println(z);

        // 3037141/3247033 * 3037547/3246599 = 841/961
        x = new Rational(3037141, 3247033);
        y = new Rational(3037547, 3246599);
        z = x.times(y);
        StdOut.println(z);

        // 1/6 - -4/-8 = -1/3
        x = new Rational(1, 6);
        y = new Rational(-4, -8);
        z = x.minus(y);
        StdOut.println(z);

    }
}
