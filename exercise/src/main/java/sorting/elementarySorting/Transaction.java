package sorting.elementarySorting;

import util.api.StdOut;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @auther Bruce Jiang
 */
public class Transaction implements Comparable<Transaction>{
    private final String who; // customer
    private final Date when; // date
    private final double amount; // amount

    /**
     * Initializes a new transaction from the given arguments.
     *
     * @param  who the person involved in this transaction
     * @param  when the date of this transaction
     * @param  amount the amount of this transaction
     * @throws IllegalArgumentException if {@code amount}
     *         is {@code Double.NaN}, {@code Double.POSITIVE_INFINITY},
     *         or {@code Double.NEGATIVE_INFINITY}
     */
    public Transaction(String who, Date when, double amount){
        this.who=  who;
        this.when = when;
        this.amount = amount;
    }

    /**
     * Initializes a new transaction by parsing a string of the form NAME DATE AMOUNT.
     *
     * @param  transaction the string to parse
     * @throws IllegalArgumentException if {@code amount}
     *         is {@code Double.NaN}, {@code Double.POSITIVE_INFINITY},
     *         or {@code Double.NEGATIVE_INFINITY}
     */
    public Transaction(String transaction) {
        String[] a = transaction.split("\\s+");
        this.who = a[0].trim();
        this.when = new Date(a[1].trim());
        this.amount = Double.parseDouble(a[2]);
        if(Double.isNaN(amount) || Double.isInfinite(amount))
            throw new IllegalArgumentException("Amount cannot be NaN or infinite");
    }

    /**
     * Returns the name of the customer involved in this transaction.
     *
     * @return the name of the customer involved in this transaction
     */
    public String who(){
        return this.who;
    }

    /**
     * Returns the date of this transaction.
     *
     * @return the date of this transaction
     */
    public Date when() {
        return this.when;
    }

    /**
     * Returns a string representation of this transaction.
     *
     * @return a string representation of this transaction
     */
    @Override
    public String toString() {
        return String.format("%-10s %10s %8.2f", who, when, amount);
    }


    /**
     * Compares two transactions by amount.
     *
     * @param  that the other transaction
     * @return { a negative integer, zero, a positive integer}, depending
     *         on whether the amount of this transaction is { less than,
     *         equal to, or greater than } the amount of that transaction
     */
    @Override
    public int compareTo(Transaction that) {
        return Double.compare(this.amount, that.amount);
    }

    @Override
    public boolean equals(Object other){
        if(other == this) return true;
        if(null == other) return false;
        if(other.getClass() != this.getClass()) return false;
        Transaction tx = (Transaction)other;
        return (this.amount == tx.amount) && this.when.equals(tx.when) && this.who.equals(tx.who);
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
