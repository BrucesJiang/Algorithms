/********************************************
 *  Compilation:   javac Point2D.java
 *  Execution:     java Point2D x0 y0 n
 *  Dependencies:  StdDraw.java StdRandom.java
 *
 ********************************************/

package fundamentals.dataAbstraction.api;

import util.api.StdDraw;
import util.api.StdRandom;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;

/**
 *  The {@code Point} class is an immutable data type to encapsulate a
 *  two-dimensional point with real-value coordinates.
 *  <p>
 *  Note: in order to deal with the difference behavior of double and
 *  Double with respect to -0.0 and +0.0, the Point2D constructor converts
 *  any coordinates that are -0.0 to +0.0.
 *  <p>
 *  For additional documentation,
 *  see <a href="http://algs4.cs.princeton.edu/12oop">Section 1.2</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @auther Bruce Jiang
 */
public final class Point2D implements Comparable<Point2D>{

    /**
     * Compares two points by x-coordinate
     */
    public static final Comparator<Point2D> X_ORDER = new XOrder();

    /**
     *  Compares two points by y-coordinate
     */
    public static final Comparator<Point2D> Y_ORDER = new YOrder();

    /**
     *  Compares two points by polar radius
     */
    private static final Comparator<Point2D> R_ORDER = new ROrder();

    private final double x; //x coordinate
    private final double y; //y coordinate

    /**
     * Initializes a new point (x,y)
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @throws IllegalArgumentException if ether {@code x} or {@code y}
     *    is {@code Double.NaN} , {@code Double.POSITIVE_INFINITY} or
     *     {@code Double.NEGATIVE_INFINITY}
     */
    public Point2D(double x, double y){
        if(Double.isInfinite(x) || Double.isInfinite(y))
            throw new IllegalArgumentException("Coordinate must be finite");
        if(Double.isNaN(x) || Double.isNaN(y))
            throw new IllegalArgumentException("Coordinate cannot be NaN");
        if(x == 0.0) this.x = 0.0; // convert -0.0 to +0.0
        else         this.x = x;
        if(y == 0.0) this.y = 0.0; // convert -0.0 to +0.0
        else         this.y = y;
    }

    /**
     * Returns the x-coordinate
     *
     * @return the x-coordinate
     */
    public double x(){
        return x;
    }

    /**
     *  Returns the y-coordinate
     *
     * @return the y-coordinate
     */
    public double y(){
        return y;
    }

    /**
     *  Returns the polar radius of this point （返回该点的极半径）
     *
     * @return the polar radius of this point in polar coordinates : sqrt(x*x + y*y)
     */
    public double r(){
        return Math.sqrt(x*x + y*y);
    }

    /**
     * Returns the angle of this point in polar coordinates.（返回极坐标中的角度）
     *
     * @return the angle (in radians) of this point in polar coordiantes (between –&pi;/2 and &pi;/2)
     */
    public double theta(){
        return Math.atan2(y,x);
    }

    /**
     * Returns the angle between this point and that point （返回角度）
     *
     * @param that another point
     * @return the angle in radius (between -&pi; and &pi;) between this point and that point (0 if equal)
     */
    private double angleTo(Point2D that){
        double dx = that.x - this.x;
        double dy = that.y - this.y;
        return Math.atan2(dy, dx);
    }

    /**
     * Returns true if a->b->c is a counterclockwise turn. (逆时针)
     * @param a first point
     * @param b second point
     * @param c third point
     * @return {-1, 0, +1} if a->b->c is a{clockwise, collinear,: counterclockwise} turn
     */
    public static int ccw(Point2D a, Point2D b, Point2D c){
        double area2 = (b.x - a.x)*(c.y - a.y) - (b.y - a.y)*(c.x - a.x);
        if       (area2 < 0) return -1;
        else if (area2 > 0) return +1;
        else                 return 0;
    }

    /**
     * Returns twice the signed area of the triangle a-b-c
     * @param a first point
     * @param b second point
     * @param c third point
     * @return twice the signed area of the triangle a-b-c
     */
    public static double area2(Point2D a, Point2D b, Point2D c){
        return (b.x - a.x)*(c.y - a.y) - (b.y - a.y)*(c.x - a.x);
    }

    /**
     * Returns the Euclidean distance between this point and that point
     * @param that the other point
     * @return the Eucliean distance between this point and that point
     */
    public double distanceTo(Point2D that){
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        return Math.sqrt(dx*dx + dy*dy);
    }


    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point (x1, y1)
     * if and only if ether {@code y0 < y1} or if {@code y0 == y1} and {@code x0 < x1}
     *
     * @param that the other point
     * @return the value {@code 0} if this string is equal to the argument string
     *           (positively when {@code equals()} returns {@code true})
     *           a negative integer if this point is less than the argument point;
     *           and a positive integer if this point is greater than the argument
     */
    public double distanceSquareTo(Point2D that){
        if(this.y < that.y) return -1;
        if(this.y > that.y) return +1;
        if(this.x < that.x) return -1;
        if(this.x > that.x) return +1;
        return 0;
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point (x1, y1)
     * if and only if either {@code y0 < y1} or if {@code y0 == y1} and {@code x0 < x1}.
     *
     * @param that the other point
     * @return the value {@code 0} if this string is equal to the argument
     *          string (precisely when {@code equals()} returns {@code true});
     *          a negative integer if this point is less than the argument point;
     *          and a positive integer if this point is greater than the argument
     *          point
     */
    @Override
    public int compareTo(Point2D that) {
        if(this.y < that.y) return -1;
        if(this.y > that.y) return +1;
        if(this.x < that.x) return -1;
        if(this.x > that.x) return +1;
        return 0;
    }




    public Comparator<Point2D> polarOrder(){
        return new PolarOrder();
    }

    public Comparator<Point2D> atan2Order(){
        return new Atan2Order();
    }

    public Comparator<Point2D> distanceToOrder(){
        return new DistanceToOrder();
    }
    // compare other points relative to polar angle (between 0 and 2PI) they make with this Point
    private class PolarOrder implements Comparator<Point2D>{
        @Override
        public int compare(Point2D o1, Point2D o2) {
            double dx1 = o1.x - x;
            double dy1 = o1.y - y;
            double dx2 = o2.x - x;
            double dy2 = o2.y - y;

            if       (dy1 >= 0 && dy2 < 0)  return -1; //q1 above ; q2 below
            else if  (dy2 >= 0 && dy1 < 0)  return +1; //q1 below; q2 above
            else if  (dy1 == 0 && dy2 == 0){ // 3-collinear and horizontal
                if      (dx1 >= 0 && dx2 < 0)  return -1;
                else if (dy1 >= 0 && dy2 < 0) return +1;
                else                           return 0;
            }else
                return -ccw(Point2D.this, o1, o2); // both above or below
            //Note : cce() recomputes dx1 , dy1, dx2 and dy2
        }
    }

    // compare other points relative to atan2 angle (between -PI/1 and PI/2) they make with this Point
    private class Atan2Order implements Comparator<Point2D>{
        @Override
        public int compare(Point2D o1, Point2D o2) {
            double angle1 = angleTo(o1);
            double angle2 = angleTo(o2);

            if       (angle1 < angle2)  return -1;
            else  if (angle1 > angle2)  return +1;
            else                        return 0;
        }
    }

    // compare points according to their distance to this point
    private class DistanceToOrder implements Comparator<Point2D>{
        @Override
        public int compare(Point2D p, Point2D q){
            double dist1 = distanceSquareTo(p);
            double dist2 = distanceSquareTo(q);
            if         (dist1 < dist2) return -1;
            else if    (dist1 > dist2) return +1;
            else                       return 0;
        }
    }

    // compare points according to their x-coordinate
    private static class XOrder implements Comparator<Point2D>{

        @Override
        public int compare(Point2D o1, Point2D o2) {
            if(o1.x < o2.x) return -1;
            if(o1.x > o2.x) return +1;
            return 0;
        }
    }

    // compare points according to their y-coordinate
    private static class YOrder implements Comparator<Point2D>{
        @Override
        public int compare(Point2D o1, Point2D o2) {
            if(o1.y < o2.y) return -1;
            if(o1.y > o2.y) return +1;
            return 0;
        }
    }

    // compare points according to their polar radius
    private static class ROrder implements Comparator<Point2D>{
        @Override
        public int compare(Point2D o1, Point2D o2) {
            double delta = (o1.x*o1.x + o1.y*o1.y) - (o2.x*o2.x + o2.y*o2.y);
            if(delta < 0) return -1;
            if(delta > 0) return +1;
            return 0;
        }
    }


    /**
     *  Compares this point to the specified point
     *
     * @param other the other point
     * @return {@code true} if this point equals {@code other}
     *           {@code false} otherwise
     */
    public boolean equals(Object other){
        if(this == other) return true;
        if(other == null) return false;
        if(this.getClass() != other.getClass()) return false;
        Point2D that = (Point2D)other;
        return (this.x == that.x) && (this.y == that.y);
    }

    /**
     * Return an integer hash code for this point
     *
     * @return an integer hash code for this point
     */
    public int hashCode(){
        int hashX = ((Double)x).hashCode();
        int hashY = ((Double)y).hashCode();
        return 31*hashX + hashY;
    }

    /**
     * Returns a string representation of the point
     * @return a string representation of the point
     */
    public String toString(){
        return "Point2D = [" + x + ", " + y + "]";
    }

    /**
     * Plot this point using standard draw.
     */
    public void draw(){
        StdDraw.point(x, y);
    }

    /**
     * Plot a line from this point to that point using standard draw
     * @param that the other point
     */
    public void drawTo(Point2D that){
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Unit tesgt the point date type
     *
     * @param args args the command-line arguments
     */
    public static void main(String[] args){
        int x0 = Integer.parseInt(args[0]);
        int y0 = Integer.parseInt(args[1]);
        int n = Integer.parseInt(args[2]);


        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
        StdDraw.setPenRadius(0.005);
        StdDraw.enableDoubleBuffering();

        Point2D[] points = new Point2D[n];

        for(int i = 0; i < n; i ++){
            int x = StdRandom.uniform(100);
            int y = StdRandom.uniform(100);
            points[i] = new Point2D(x, y);
            points[i].draw();
        }

        //draw p (x0, y1） in red
        Point2D p = new Point2D(x0, y0);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.02);
        p.draw();

        //draw line segments from p to each point, one at a time, in polar order
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.BLUE);
        Arrays.sort(points, p.polarOrder());
        for(int i = 0; i < n; i ++){
            p.drawTo(points[i]);
            StdDraw.show();
            StdDraw.pause(100);
        }
    }
}
