package fundamentals.unionfind;

import util.api.In;
import util.api.Out;
import util.api.StdDraw;

/**
 * @auther Bruce Jiang
 */
public class Ex_1_5_16 {

    public static void main(String[] args){
        String fileName = args[0];
        In in = new In(fileName);
        int n = in.readInt();
        Out out = new Out();
        QuickFindUF uf = new QuickFindUF(n);
        int x = 0;
        StdDraw.setXscale(0, 800);
        StdDraw.setYscale(0, 1300);
        StdDraw.setPenRadius(0.0001);
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.point(++x, uf.unionSum);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.point(x, uf.unionSum/x);
            out.println("Sum  = " + uf.unionSum + ", Ave = " + uf.unionSum/x);
            out.println(p + " " + q);
        }
        out.println(uf.count() + " components");
    }
}
