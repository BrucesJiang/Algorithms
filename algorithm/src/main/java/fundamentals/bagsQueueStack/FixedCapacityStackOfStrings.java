package fundamentals.bagsQueueStack;

import util.api.StdIn;
import util.api.StdOut;

import java.io.FileNotFoundException;

/**
 * @auther Bruce Jiang
 */
public class FixedCapacityStackOfStrings {
    private String[] a; //stack of entries
    private int N; // size

    public FixedCapacityStackOfStrings(int capacity){
            a = new String[capacity];
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void push(String str){
        a[N++] = str;
    }

    public String pop(){
        return a[--N];
    }


    public static void main(String[] args){
        FixedCapacityStackOfStrings fcsos = new FixedCapacityStackOfStrings(100);
        while(!StdIn.isEmpty()){
            String item = StdIn.readString();
            if(!item.equals("-")) fcsos.push(item);
            else if(!fcsos.isEmpty()) StdOut.print(fcsos.pop() + " ");
        }
        StdOut.println("(" + fcsos.size() + " left on stack )");
    }
}
