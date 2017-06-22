package fundamentals.basicProgModel;

import util.api.StdRandom;

/**
 * @auther Bruce Jiang
 */
public class Ex_1_1_37 {

    public static void shuffle(int[] a){
        int N = a.length;
        for(int i = 0; i < N; i ++){
            int r = StdRandom.uniform(N);
            int tmp = a[r];
            a[r] = a[i];
            a[i] = tmp;
        }
    }
    public static void main(String[] args){
         int M = Integer.parseInt(args[0]);
         int N = Integer.parseInt(args[1]);
         Ex_1_1_36.Shuffle sh = new Ex_1_1_36.Shuffle() {
             public void shuffle(int[] a) {
                 Ex_1_1_37.shuffle(a);
             }
         };
         Ex_1_1_36.shuffleTest(sh, M, N);
    }
}
