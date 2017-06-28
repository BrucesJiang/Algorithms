package fundamentals.dataAbstraction;

import util.api.StdOut;

/**
 * @auther Bruce Jiang
 */
public class Ex_1_2_6 {
    public static int circularRotation(String a, String b){
        return (a+a).indexOf(b);
    }
    public static void main(String[] args){
        String a = "ACTGACG";
        String b0 = "TGACGAC";
        String b1 = "TBA";
        StdOut.println(circularRotation(a, b0));
        StdOut.println(circularRotation(a,b1));
    }
}
