package fundamentals.basicProgModel;

import org.junit.Assert;
import org.junit.Test;

/**
 * User： Bruce Jiang
 * Date: 2017/6/14 9:23
 * Description:
 */
public class GCDTest {
    private GCD gcd = new GCD();

    @Test
    public void gcdRecursive() throws Exception {
        Assert.assertEquals(10, gcd.gcdRecursive(10, 0));
        Assert.assertEquals(10, gcd.gcdRecursive(0, 10));
        Assert.assertEquals(10, gcd.gcdRecursive(10, 10));
        Assert.assertEquals(5, gcd.gcdRecursive(10, 5));
        Assert.assertEquals(5, gcd.gcdRecursive(5, 10));
        Assert.assertEquals(5, gcd.gcdRecursive(5, 5));
    }

    @Test
    public void gcdIteration() throws Exception {
        Assert.assertEquals(10, gcd.gcdIteration(10, 0));
        Assert.assertEquals(10, gcd.gcdIteration(0, 10));
        Assert.assertEquals(10, gcd.gcdIteration(10, 10));
        Assert.assertEquals(5, gcd.gcdIteration(10, 5));
        Assert.assertEquals(5, gcd.gcdIteration(5, 10));
    }

}