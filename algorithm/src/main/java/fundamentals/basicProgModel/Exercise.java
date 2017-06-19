package fundamentals.basicProgModel;

import java.util.HashMap;
import java.util.Map;

/**
 * User： Bruce Jiang
 * Date: 2017/6/14 15:04
 * Description:
 */
public class Exercise {
    /**
     * 三个数的重排序
     *
     * @param a
     * @param b
     * @param c
     */
    public static void tripleReordering(int a, int b, int c) {
        if (a > b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        if (a > c) {
            int tmp = a;
            a = c;
            c = tmp;
        }
        if (b > c) {
            int tmp = b;
            b = c;
            c = tmp;
        }
        System.out.printf("%d\t%d\t%d\t", a, b, c);
    }

    /**
     * 二项分布(Binomial distribution) : B(x,n,p) : 在n次实验中有x次成功，其中成功的概率为p
     * B(x, n , p) = Cn^x p^x q^(n-x) : 描述为掷n次出现x次的组合数，p的x次方， q的n-x次方，其中q = 1 - p
     * Cn^x = n!/(x!(n-x)!) n的阶乘除以 x的阶乘与n-x阶乘的乘积
     *
     * @param N 实验次数 test times
     * @param k 成功的次数  success times
     * @param p 成功的概率 success probability
     * @return 返回k次成功的概率  return the probability of k times success
     */
    public static double binomial(int N, int k, double p) {
        System.out.println("(" + N + ", " + k + ")");
        if (N == 0 && k == 0) return 1.0;
        if (N < 0 || k < 0) return 0.0;
        return (1.0 - p) * binomial(N - 1, k, p) + p * binomial(N - 1, k - 1, p);
    }

    private static Map<NumMap, Double> nk = new HashMap<NumMap, Double>();

    private static class NumMap {
        protected int n;
        protected int k;

        public NumMap(int n, int k) {
            this.n = n;
            this.k = k;
        }
    }

    //改进版
    public static double binomialImprove(int N, int k, double p) {
        if (N == 0 && k == 0) return 1.0;
        if (N < 0 || k < 0) return 0.0;
        NumMap nm = new NumMap(N, k);
        if (nk.containsKey(nm)) {
            return nk.get(nm);
        } else {
            double re = (1.0 - p) * binomialImprove(N - 1, k, p) + p * binomialImprove(N - 1, k - 1, p);
            nk.put(new NumMap(N, k), re);
            return re;
        }
    }
}
