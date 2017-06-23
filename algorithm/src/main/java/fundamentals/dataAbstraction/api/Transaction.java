/*******************************************
 *  Compilation: javac Transaction.java
 *  Execution : java Transaction
 *  Dependencies: StdOut.java
 *
 *  Data type for commercial transactions
 *
 *  Unsorted
 *  Turing      6/17/1990   644.08
 *  Tarjan      3/26/2002  4121.85
 *  Knuth       6/14/1999   288.34
 *  Dijkstra    8/22/2007  2678.40
 *
 *  Sort by date
 *  Turing      6/17/1990   644.08
 *  Knuth       6/14/1999   288.34
 *  Tarjan      3/26/2002  4121.85
 *  Dijkstra    8/22/2007  2678.40
 *
 *  Sort by customer
 *  Dijkstra    8/22/2007  2678.40
 *  Knuth       6/14/1999   288.34
 *  Tarjan      3/26/2002  4121.85
 *  Turing      6/17/1990   644.08
 *
 *  Sort by amount
 *  Knuth       6/14/1999   288.34
 *  Turing      6/17/1990   644.08
 *  Dijkstra    8/22/2007  2678.40
 *  Tarjan      3/26/2002  4121.85
 ******************************************/

package fundamentals.dataAbstraction.api;

import util.api.StdOut;

import java.util.Arrays;
import java.util.Comparator;

/**
 *  The {@code Transaction} class is an immutable data type to encapsulate a
 *  commercial transaction with a customer name, date, and amount.
 *  <p>
 *  For additional documentation,
 *  see <a href="http://algs4.cs.princeton.edu/12oop">Section 1.2</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @auther Bruce Jiang
 */
public class Transaction implements Comparable<Transaction>{

    private final String who; //cunstomer
    private final Date when; //date
    private final double amount; //amount

    /**
     * Initializes a new transaction form the given arguments
     * @param who the person involved in this transaction
     * @param when the date of this transaction
     * @param amount the amount of this tranasction
     * @throws IllegalArgumentException if {@code amount} is {@code Double.NaN}, {@code Double.POSITIVE_INFINITY}
     *          or {@code Double.NEGATIVE_INFINITY}
     */
    public Transaction(String who, Date when, double amount){
        if(Double.isNaN(amount) || Double.isInfinite(amount)) throw new IllegalArgumentException("Amount connot be NaN or infinite");
        this.who = who;
        this.when = when;
        this.amount = amount;
    }

    /**
     *  Initializes a new transaction by parsing a string of the form NAME DATE AMOUNT
     *
     * @param transaction the string to parse
     * @throws IllegalArgumentException if {@code amount} is {@code Double.NaN}, {@code Double.POSITIVE_INFINITY}
     *          or {@code Double.NEGATIVE_INFINITY}
     */
    public Transaction(String transaction){
        String[] a = transaction.split("\\s+");
        this.who = a[0];
        this.when = new Date(a[1]);
        this.amount = Double.parseDouble(a[2]);
        if(Double.isNaN(amount) || Double.isInfinite(amount)) throw new IllegalArgumentException("Amount connot be NaN or infinite");
    }


    /**
     *  Returns the name of the customer involved in this transaction
     *
     * @return the name of the customer involved in this transaction
     */
    public String who(){
        return who;
    }

    /**
     * Returns the date of this transaction
     *
     * @return the date of this transaction
     */
    public Date when(){
        return when;
    }

    /**
     *  Returns the amount of this transaction
     *
     * @return the amount of this transaction
     */
    public double amount(){
        return  amount;
    }

    /**
     *  Returns a string representation of this transaction
     * @return a string representation of this transaction
     */
    public String toString(){
        return String.format("%-10s %10s %8.2f",who, when, amount);
    }

    /**
     * Compares this transaction to the specified object
     * @param that the other transaction
     * @return if this transaction is equal to {@code other}; false otherwise
     */
    public boolean equals(Object that){
        if(that == this) return true;
        if(that == null) return false;
        if(that.getClass() != this.getClass()) return false;
        Transaction tx = (Transaction)that;
        return (this.amount == tx.amount ) && (this.who.equals(tx.who)) && (this.when.equals(tx.when));
    }
    /**
     * Returns a hash code for this transaction.
     *
     * @return a hash code for this transaction
     */
    public int hashCode() {
        int hash = 1;
        hash = 31*hash + who.hashCode();
        hash = 31*hash + when.hashCode();
        hash = 31*hash + ((Double) amount).hashCode();
        return hash;
        // return Objects.hash(who, when, amount);
    }



    /**
     * /**
     * Compares this transaction by amount
     * @param that the other transaction
     * @return {a negative integer, zero, a positive integer}, depending on whether the amount of
     * this transaction is { less than, equal to , or greater than} the amount of that transaction
     */
    @Override
    public int compareTo(Transaction that) {
        return Double.compare(this.amount, that.amount);
    }

    /**
     * Compares two transactions by customer name.
     */
    public static class WhoOrder implements Comparator<Transaction> {

        @Override
        public int compare(Transaction v, Transaction w) {
            return v.who.compareTo(w.who);
        }
    }

    /**
     * Compares two transactions by date.
     */
    public static class WhenOrder implements Comparator<Transaction> {

        @Override
        public int compare(Transaction v, Transaction w) {
            return v.when.compareTo(w.when);
        }
    }

    /**
     * Compares two transactions by amount.
     */
    public static class HowMuchOrder implements Comparator<Transaction> {

        @Override
        public int compare(Transaction v, Transaction w) {
            return Double.compare(v.amount, w.amount);
        }
    }


    /**
     * Unit tests the {@code Transaction} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Transaction[] a = new Transaction[4];
        a[0] = new Transaction("Turing   6/17/1990  644.08");
        a[1] = new Transaction("Tarjan   3/26/2002 4121.85");
        a[2] = new Transaction("Knuth    6/14/1999  288.34");
        a[3] = new Transaction("Dijkstra 8/22/2007 2678.40");

        StdOut.println("Unsorted");
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();

        StdOut.println("Sort by date");
        Arrays.sort(a, new Transaction.WhenOrder());
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();

        StdOut.println("Sort by customer");
        Arrays.sort(a, new Transaction.WhoOrder());
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();

        StdOut.println("Sort by amount");
        Arrays.sort(a, new Transaction.HowMuchOrder());
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();
    }
}
