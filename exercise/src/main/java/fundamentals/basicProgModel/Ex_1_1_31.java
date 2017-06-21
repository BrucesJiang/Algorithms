package fundamentals.basicProgModel;

/**************************************************
 *
 *
 *
 *************************************************/

import util.api.StdDraw;
import util.api.StdIn;
import util.api.StdRandom;

/**
 * @auther Bruce Jiang
 */
public class Ex_1_1_31 {
    public static void drawRandomConn(int N, double p){
        StdDraw.setCanvasSize(512,512);
        StdDraw.setScale(-1.0, 1.0);
        StdDraw.setPenRadius(.015);
        double[][] d = new double[N][2];
        for(int i = 0; i < N; i ++){
            d[i][0] = Math.cos(2 * Math.PI * i / N);
            d[i][1] = Math.sin(2 * Math.PI * i / N);
            StdDraw.point(d[i][0], d[i][1]);
        }
        StdDraw.setPenColor();
        for (int i = 0; i < N - 1; i++)
            for (int j = i + 1; j < N; j++)
                if (StdRandom.bernoulli(p))
                    StdDraw.line(d[i][0], d[i][1], d[j][0], d[j][1]);
    }

    public static void main(String[] args){
        int N = StdIn.readInt();
        // between 0.0 and 1.0
        double p = StdIn.readDouble();
        p = Math.max(0, Math.min(1, p));

        drawRandomConn(N, p);
    }
}
