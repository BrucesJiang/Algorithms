package sorting.elementarySorting;

import sorting.SortTools;
import util.api.StdDraw;
import util.api.StdRandom;

import java.awt.*;

/**
 * @auther Bruce Jiang
 */
public class ShellBars {
    private final static int FF = 4;


    public static void sort(double[] a){
        int N = a.length;
        int h = 1;
        int k = 1;
        while(h < N/3){
            h = 3*h + 1;
            k ++;
        }
        show(a, k, "input");
        while(h >= 1){
            for(int i = h; i < N; i ++){
                for(int j = i; j >= h && SortTools.less(a[j], a[j - h]); j -= h){
                   exch(a, j, j-h);
                }
            }
            if(h < N) show(a, --k, h + "-sorted");
            h /= 3;
        }
    }

    private static void show(double[] a, int k, String message){
        for(int i = 0; i < a.length; i ++){
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.line(i, k*FF, i, FF*k + a[i]*(FF-1));
        }
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.text(0, FF*k - 0.3, message);
    }

    private static void exch(double[] a, int x, int y){
        double tmp = a[x]; a[x] = a[y]; a[y] = tmp;
    }


    public static void main(String[] args){
        int N = Integer.parseInt(args[0].trim());
        double[] a = new double[N];
        for(int i = 0; i < N; i ++){
            a[i] = StdRandom.uniform(0.0, 1.0);
        }
        int k = (int)Math.round(Math.log(N)/Math.log(3));// number of h-increment values

        StdDraw.disableDoubleBuffering();

        StdDraw.setCanvasSize(800, 900);
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 9));
        StdDraw.setXscale(-5, N + 1);
        StdDraw.setYscale(-1, FF*(k+1));
        StdDraw.setPenRadius(0.005);
        sort(a);
        StdDraw.show();
    }
}
