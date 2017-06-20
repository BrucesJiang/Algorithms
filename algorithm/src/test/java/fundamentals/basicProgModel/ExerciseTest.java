package fundamentals.basicProgModel;

import fundamentals.basicProgModel.Exercise;
import org.junit.Test;

/**
 * User： Bruce Jiang
 * Date: 2017/6/14 15:21
 * Description:
 */
public class ExerciseTest {
    @Test
    public void binomial() throws Exception {
        System.out.println(Exercise.binomial(6, 4, 0.25));
    }

    private Exercise reordering = new Exercise();

    @Test
    public void tripleReordering() throws Exception {
        Exercise.tripleReordering(1, 3, 2);
    }

}