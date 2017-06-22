package fundamentals.basicProgModel;

import util.api.StdArrayIO;
import util.api.StdRandom;

/**
 * @auther Bruce Jiang
 */
public class Ex_1_1_36 {


//    /**
//     * 随机将double数组中的元素排序
//     * @param a
//     */
//    public static void shuffle(int[] a){
//        int N = a.length; //打乱N次
//        for(int i = 0; i < N; i ++){
//            //将 a[i] 和 a[i..N-1]中任意一个元素交换
//            int r = i + StdRandom.uniform(N-i);
//            int tmp = a[r];
//            a[r] = a[i];
//            a[i] = tmp;
//        }
//    }
    //Proxy Pattern
    interface Shuffle{
        void shuffle(int[] a);
    }
    public static void init(int[] a){
        for(int i = 0; i < a.length; i ++){
            a[i] = i;
        }
    }
    public static void shuffleTest(Shuffle sh, int M, int N){
        int[] a = new int[M];
        int[][] c = new int[M][M];
        for(int i = 0; i < N; i ++){
            init(a);
            sh.shuffle(a);
            for(int j = 0; j < M; j ++){
                c[j][a[j]]++;
            }
        }
        StdArrayIO.print(c);
    }
    public static void main(String[] args){
        int M  = Integer.parseInt(args[0]);
        int N = Integer.parseInt(args[1]);
        Shuffle sh = new Shuffle() {
            public void shuffle(int[] a) {
                StdRandom.shuffle(a);
            }
        };
        shuffleTest(sh,M,N);
    }
}
