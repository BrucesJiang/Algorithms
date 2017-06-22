package fundamentals.basicProgModel;
/**********************************************
 * Empirical results match exact ones to 3 decimal places when
 * n >= 10,000,000  (< 1 sec.)
 * % java Ex_1_1_35 10000000
 *       2      3      4      5      6      7      8      9     10     11     12
 *   0.028  0.056  0.083  0.111  0.139  0.167  0.139  0.111  0.083  0.056  0.028
 *   0.028  0.055  0.083  0.111  0.139  0.167  0.139  0.111  0.083  0.056  0.028
 **********************************************/

import util.api.StdOut;
import util.api.StdRandom;

/**
 * @auther Bruce Jiang
 */
public class Ex_1_1_35 {
    public static int SIDES = 6;

    /**
     * 精确概率
     * @return
     */
     public static double[] getExact(){
        double[] dist = new double[2*SIDES + 1];

        for(int i = 1; i <= SIDES; i ++){
            for(int j = 1; j <= SIDES; j ++){
                dist[i + j] += 1.0;
            }
        }

        for(int i = 2; i <= 2*SIDES; i ++){
            dist[i] /= SIDES*SIDES;
        }

        return dist;
     }

     public static double[] getExper(int n){
         double[] dist = new double[2*SIDES + 1];

         for(int j = 1; j <= n; j ++){
             dist[getUniform()] ++;
         }

         for(int i = 2; i <= 2*SIDES; i ++){
             dist[i] /= n;
         }

         return dist;
     }

    /**
     * 试验，随机产生两个1~6的数字之和
     * @return
     */
     public static int getUniform(){
         return StdRandom.uniform(1, SIDES + 1) + StdRandom.uniform(1, SIDES + 1);
     }

     public static void main(String[] args){
         int n = Integer.parseInt(args[0]);

         double[] exact = getExact();

         for (int i = 2; i <= 2*SIDES; i++)
             StdOut.printf("%7d", i);
         StdOut.println();

         for (int i = 2; i <= 2*SIDES; i++)
             StdOut.printf("%7.3f", exact[i]);
         StdOut.println();

         double[] experim = getExper(n);

         for (int i = 2; i <= 2*SIDES; i++)
             StdOut.printf("%7.3f", experim[i]);
         StdOut.println();


     }
}
