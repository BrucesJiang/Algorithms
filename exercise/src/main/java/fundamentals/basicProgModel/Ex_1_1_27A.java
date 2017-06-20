package fundamentals.basicProgModel;

/**
 *  % java Ex_1_1_27A
 * (10,5,0.5,0.24609375) = 1233 calls
 * (20,15,0.5,0.0147857666015625) = 2074100 calls
 * (30,15,0.5,0.14446444809436798) = 1219164498 calls
 */

import util.api.StdOut;



/**
 * @auther Bruce Jiang
 */
public class Ex_1_1_27A {
    public static double binomial(int N, int k, double p, Counter c){
        if(N == 0 && k == 0) return 1.0;
        if(N < 0 || k < 0) return 0.0;
        c.increment();
        //将第一个N-1置为 N 抛出异常StackOverFlow ：堆栈溢出，陷入无限调用
        return (1.0 - p) * binomial(N-1, k, p, c) + p * binomial(N-1, k-1, p, c);
    }


    public static void main(String[] args){
        int N1 = 10, N2 = 20, N3 = 30;
        int K1 = 5, K2 = 15, K3 = 15;
        double p = 0.5;
        Counter counter = new Counter("calls");
        StdOut.println("("+N1+ "," + K1 + "," + p + "," + binomial(N1,K1, p, counter)+ ")" + " = " + counter.out());
        counter = new Counter("calls");
        StdOut.println("("+N2+ "," + K2 + "," + p + "," + binomial(N2,K2, p, counter)+ ")" + " = " + counter.out());
        counter = new Counter("calls");
        StdOut.println("("+N3+ "," + K3 + "," + p + "," + binomial(N3,K3, p, counter)+ ")" + " = " + counter.out());
    }

    private static class Counter{
        protected String message = "calls";
        protected volatile int counter = 0;
        Counter(String msg){
            this.message = msg;
            this.counter = 0;
        }
        void increment(){
            this.counter = this.counter + 1;
        }
        String out(){
            return this.counter + " " + this.message;
        }
    }
}
