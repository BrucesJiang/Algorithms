package fundamentals.dataAbstraction;

import util.api.StdOut;

/**
 *  A Counter API
 *
 * @auther Bruce Jiang
 *
 */
public class Counter {
    private final String name;
    private int count;
    /**
     * Constructor , the object named id
     * @param id  the counter's name
     */
    public Counter(String id){
        this.name = id;
    }

    /**
     *  the counter is incremented by one
     */
    public void increment(){
        count++;
    }

    /**
     *  The number of times the counter is incremented after the object is created
     * @return the number of times
     */
    public int tally(){
        return count;
    }

    @Override
    public String toString(){
        return count + " " + name;
    }

    public static void main(String[] args){
        Counter heads = new Counter("heads");
        Counter tails = new Counter("tails");

        heads.increment();
        heads.increment();
        tails.increment();

        StdOut.println(heads + " " + tails); //自动调用toString()方法
        StdOut.println(heads.tally() + tails.tally());
    }
}
