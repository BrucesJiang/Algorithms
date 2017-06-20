package fundamentals.basicProgModel;

/**
 * (10,5,0.5,1.0) = 50 calls
 * (20,15,0.5,1.0) = 215 calls
 * (30,15,0.5,1.0) = 375 calls
 */

import util.api.StdOut;

/**
 * @auther Bruce Jiang
 */
public class Ex_1_1_27B {
//    private static class KeyValue implements Comparable<KeyValue>{
//        protected int n;
//        protected int k;
//        KeyValue(int n, int k){
//            this.n = n;
//            this.k = k;
//        }
//        public int compareTo(KeyValue o) {
//            if(n == o.n && k == o.k){
//                return 0;
//            }
//            return 1;
//        }
//    }

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
    public static double[][] matrix(int N, int k){
        return  new  double[N+1][k+1];
    }


    public double binomial(int N, int k, double p,double[][] m,  Counter c){
        if(N == 0 && k == 0) return 1.0;
        if(N < 0 || k < 0) return 1.0;
        if(m[N][k] == 0.0){
            c.increment();
            m[N][k] = (1.0 - p)*binomial(N-1, k, p,m, c) + p * binomial(N-1, k-1, p, m, c);

        }
        return m[N][k];
    }

    public static void main(String[] args){
        int N1 = 10, N2 = 20, N3 = 30;
        int K1 = 5, K2 = 15, K3 = 15;
        double p = 0.5;
        Ex_1_1_27B ex = new Ex_1_1_27B();
        Counter counter = new Counter("calls");

        StdOut.println("("+N1+ "," + K1 + "," + p + "," + ex.binomial(N1,K1, p, Ex_1_1_27B.matrix(N1, K1), counter)+ ")" + " = " + counter.out());
        counter = new Counter("calls");
        StdOut.println("("+N2+ "," + K2 + "," + p + "," + ex.binomial(N2,K2, p, Ex_1_1_27B.matrix(N2, K2), counter)+ ")" + " = " + counter.out());
        counter = new Counter("calls");
        StdOut.println("("+N3+ "," + K3 + "," + p + "," + ex.binomial(N3,K3, p, Ex_1_1_27B.matrix(N3, K3), counter)+ ")" + " = " + counter.out());
    }
}
