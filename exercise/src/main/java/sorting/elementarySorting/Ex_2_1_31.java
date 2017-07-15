package sorting.elementarySorting;

import fundamentals.analysisOfAlgorithm.Stopwatch;
import util.api.StdRandom;

/**
 * @auther Bruce Jiang
 */
public class Ex_2_1_31 {


    public static void main(String[] args){
        int M = Integer.parseInt(args[1]); // 问题规模的数量
        int[][] num = new int[M][];
        double[] timer = new double[M];
        for(int i = 0; i < M; i ++){
            int N = (int)StdRandom.uniform(); // 生成一个规模为N的问题
            num[i] = new int[N];
            for(int j =0 ; j < N; j ++){
                num[i][j] = StdRandom.uniform(N);
            }
        }
    }

    public static double[][] timer(int[][] nums){
        return null;
    }


    public static void draw(int[] num, double[] time){

    }
}
