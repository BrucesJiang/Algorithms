package fundamentals.dataAbstraction.api;
/***************************************************************
 *  Compilation: javac Date.java
 *  Execution:   java Date
 *  Dependencies: StdOut.java
 *
 *  An immutable data type for dates
 *
 ***************************************************************/


import util.api.StdOut;

/**
 *  The {@code Date} class is an immutable data type to encapsulate a
 *  date (day, month, and year).
 *  <p>
 *   For additional documentation.
 *   see <a href="http://algs4.cs.princeton.edu/12oop">Section 1.2</a> of
 *   <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @auther Bruce Jiang
 */
public class Date  implements Comparable<Date>{

    private static final int[] DAYS = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private final int month; //month between 1 and 12
    private final int day; // day between 1 and DAYS[month]
    private final int year; //year

    /**
     * Initializes a new date from the month, day and year
     * @param month the month (between 1 and 12)
     * @param day the day (between 1 and 28-31, depending on the month)
     * @param year the year
     */
    public Date(int month, int day, int year){
        if(!isValid(month, day, year)) throw new IllegalArgumentException("Invalid date");
        this.month = month;
        this.day = day;
        this.year = year;
    }

    /**
     * Initializes a new data specified as a string in from MM/DD/YYYY.
     * @param date  the string representation of this date
     * @throws IllegalArgumentException if this data is invalid
     */
    public Date(String date){
        String[] fields = date.split("/");
        if(fields.length != 3){
            throw new IllegalArgumentException("Invalid date");
        }
        month = Integer.parseInt(fields[0]);
        day = Integer.parseInt(fields[1]);
        year = Integer.parseInt(fields[2]);
    }


    /**
     * Returns the month
     * @return the month (an integer between 1 and 12)
     */
    public int month(){
        return month;
    }

    /**
     * Returns the day
     * @return the day (an integer between 1 and 31)
     */
    public int day(){
        return day;
    }

    /**
     * Returns the year
     * @return the year
     */
    public int year(){
        return year;
    }

    /**
     * Returns the next data in the calendar
     * @return a data that represents the next day after this day
     */
    public Date next(){
        if(isValid(month, day + 1, year))             return new Date(month, day + 1, year);
        else if(isValid(month + 1, 1, year)) return new Date(month + 1, 1, year);
        else                                                return new Date(1,1, year + 1);
    }

    /**
     *  Compares two dates chronologically
     * @param that the other date
     * @return {@code true} if this date is after that data; {@code false} otherwise
     */
    public boolean isAfter(Date that){
        return compareTo(that) > 0;
    }

    /**
     *  Compares two dates chronologically
     * @param that the other date
     * @return {@code true} if this date is before that date; {@code false} otherwise
     */
    public boolean isBefore(Date that){
        return compareTo(that) < 0;
    }

    /**
     *  Compares two dates chronologically
     * @param o the other date
     * @return the value {@code 0} if the argument date is equal to this date;
     *          a negative integer if this data is chronologically less than
     *          the argument data; and a positive integer if this date is chronologically
     *          after the argument date
     */
    public int compareTo(Date o) {
        if(this.year < o.year) return -1;
        if(this.year > o.year) return +1;
        if(this.month < o.month) return -1;
        if(this.month > o.month) return +1;
        if(this.day < o.day) return -1;
        if(this.day > o.day) return +1;
        return 0;

    }

    /**
     * Returns a string representation of this date
     * @return the string representation in the format MM/DD/YYYY
     */
    public String toString(){
        return month + "/" + day + "/" + year;
    }

    /**
     * Compares this date to the specified data.
     * @param other the other date
     * @return {@code true} if this date equals {@code other} ; {@code false} otherwise
     */
    public boolean equals(Object other){
        if(other == this) return true;
        if(other == null) return false;
        if(other.getClass() != this.getClass()) return false;
        Date that = (Date) other;
        return (this.month == that.month) && (this.day == that.day) && (this.year == that.year);
    }

    /**
     * Returns an integer hash code for this date.
     *
     * @return an integer hash code for this date
     */
    public int hashCode(){
        int hash = 17;
        hash = 31 * hash + month;
        hash = 31 * hash + day;
        hash = 31 * hash + year;
        return hash;
    }

    // is the given date valid ?
    private static boolean isValid(int month, int day, int year){
        if(month < 1 || month > 12) return false;
        if(day < 1 || day > DAYS[month]) return false;
        if(month == 2 && day == 29 && !isLeapYear(year)) return false;
        return true;
    }

    // is year a leap year ?
    private static boolean isLeapYear(int year){
        if(year % 400 == 0) return true;
        if(year % 100 == 0) return false;
        return year % 4 == 0;
    }


    /**
     * Unit test the {@code Date} data type
     * @param args the command-line arguments
     */
    public static void main(String[] args){
        Date today = new Date(2, 25, 2004);
        StdOut.println(today);
        for (int i = 0; i < 10; i++) {
            today = today.next();
            StdOut.println(today);
        }

        StdOut.println(today.isAfter(today.next()));
        StdOut.println(today.isAfter(today));
        StdOut.println(today.next().isAfter(today));


        Date birthday = new Date(10, 16, 1971);
        StdOut.println(birthday);
        for (int i = 0; i < 10; i++) {
            birthday = birthday.next();
            StdOut.println(birthday);
        }
    }
}
