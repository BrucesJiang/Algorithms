package fundamentals.dataAbstraction;

import fundamentals.dataAbstraction.api.Point2D;
import util.api.StdOut;
import util.api.StdRandom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @auther Bruce Jiang
 */
public class Ex_1_2_1 {

    public static void main(String[] args){
        int T = Integer.parseInt(args[0]);
        ArrayList<Point2D> arrayLists = new ArrayList<Point2D>();
        for(int i = 0; i < T; i ++){
            double x = StdRandom.uniform(0.0, 1.0);
            double y = StdRandom.uniform(0.0, 1.0);
            arrayLists.add(new Point2D(x, y));
        }
        Collections.sort(arrayLists);

        int x =0, y = 0;
        double min = Double.MAX_VALUE;
        for(int i = 0; i < arrayLists.size() - 1; i ++){
            for(int j = i + 1; j < arrayLists.size(); j ++){
                double dist = arrayLists.get(i).distanceTo(arrayLists.get(j));
                if(dist < min){
                    x = i;
                    y = j;
                    min = dist;
                }
            }
        }

        StdOut.println("x = " + arrayLists.get(x) + ", y = " + arrayLists.get(y) + ", Dist = " + min);
    }
}
