package fundamentals.basicProgModel;


/**
 * User： Bruce Jiang
 * Date: 2017/6/14 9:11
 * Description:
 * 计算两个非负整数p和q的最大公约数：
 * 如果 q = 0, 则最大公约数为 p
 * 将 p 除以 q 得到余数 r，
 * 则 p 和 q 的最大公约数与 q 和 r 的最大公约数相等
 */
public class GCD {
    /**
     * 递归求解
     *
     * @param p * @param q
     * @return
     */
    public int gcdRecursive(int p, int q) {
        if (q == 0) {
            return p;
        }
        int r = p % q;
        return gcdRecursive(q, r);
    }

    /**
     * 迭代求解
     *
     * @param p
     * @param q
     * @return
     */
    public int gcdIteration(int p, int q) {
        while (q != 0) {
            int r = p % q;
            p = q;
            q = r;
        }
        return p;
    }
}
