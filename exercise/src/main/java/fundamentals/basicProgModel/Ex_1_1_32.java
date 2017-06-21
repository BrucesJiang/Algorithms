package fundamentals.basicProgModel;

/**************************************************
 *
 *
 *
 *************************************************/

import util.api.StdDraw;
import util.api.StdIn;

/**
 * @auther Bruce Jiang
 */
public class Ex_1_1_32 {
    /**
     * 画直方图
     */
    public static void drawHistogram(double[] c, int N, double l, double r){
        StdDraw.setCanvasSize();
        StdDraw.setScale();
        StdDraw.setPenRadius(.015);

    }

    /**
     * 获取间隔
     * @return
     */
    public static double getInterval(){

        return 0.1;
    }
    public static void main(String[] args){
        int N = Integer.parseInt(args[0]);
        double l = Double.parseDouble(args[1]);
        double r = Double.parseDouble(args[2]);


        double[] c = StdIn.readAllDoubles();

        drawHistogram(c, N, l, r);
    }


}
