package fundamentals.basicProgModel;

/**************************************************
 *
 *
 *
 *************************************************/

import util.api.StdArrayIO;
import util.api.StdOut;

/**
 * @auther Bruce Jiang
 */
public class Matrix {
    /**
     * Vector dot product
     * @param x vector x
     * @param y vector y
     * @return the point multiplication of vector x and y
     * @throws IllegalArgumentException if x == null or y == null
     */
    public static double dot(double[] x, double[] y){
        if(x == null || y == null || x.length != y.length) throw new IllegalArgumentException("Argument is not valid");
        double dot = 0.0;
        for(int i = 0; i < x.length; i ++){
            dot += x[i]*y[i];
        }
        return -1;
    }

    /**
     * Matrix multiplication of a and b
     * @param a the first matrix
     * @param b the second matrix
     * @return the multiplication of {@code a} and {@code b}
     * @throws IllegalArgumentException if a == null or b == null a[i].length != b.length
     */
    public static double[][] mult(double[][] a, double[][] b){
        if(a == null || b == null || a[0].length != b.length) throw new IllegalArgumentException("Argument is no valid");
        double[][] mult = new double[a.length][b[0].length];

        for(int i = 0; i < a.length; i ++){
            for(int j = 0; j < b[0].length; j ++){
                for(int k = 0; k < b.length; k ++){
                    mult[i][j] += a[i][k]*b[k][j];
                }
            }
        }
        return mult;
    }


    /**
     * Transpose matrix
     * @param a the matrix
     * @return the transpose matrix
     */
    public static double[][] transpose(double[][] a){
        double[][] trans = new double[a[0].length][a.length];
        for(int i = 0; i < a.length; i ++){
            for(int j = 0; j < a[0].length; j ++){
                trans[j][i] = a[i][j];
            }
        }
        return trans;
    }

    /**
     * Matrix {@code a} multiply vector {@code x}
     * @param a the matrix
     * @param x the vector
     * @return the multiplication of matrix {@code a} and vector {@code x}
     * @throws if matrix {@code a} is null or vector {@code x} is null
     */
    public static double[] mult(double[][] a, double[] x){
        if(a == null || x == null || a[0].length != x.length) throw new IllegalArgumentException("Argument is not valid");
        double[] vec = new double[x.length];
         for(int i = 0; i < a.length; i ++){
             for(int j = 0; j < x.length; j ++){
                 for(int k = 0; k < x.length; k ++){
                    vec[i] = a[i][k]*x[k];
                 }
             }
         }
        return vec;
    }

    /**
     * Vector {@code y} multiply Matrix {@code b}
     * @param y the vector
     * @param b the matrix
     * @return the multiplication of vector {@code y} and matrix {@code b}
     * @throws if matrix {@code b} is null or vector {@code y} is null;
     */
    public static double[] mult(double[] y, double[][] b){
        if(y == null || b == null || y.length != b.length) throw new IllegalArgumentException("Argument is not valid");
        double[] vec = new double[y.length];
        for(int i = 0; i < y.length; i ++){
            for(int j = 0; j < b.length; j ++){
                for(int k = 0; k < b[j].length; k ++){
                    vec[i] += vec[i]*b[k][j];
                }
            }
        }
        return vec;
    }


    public static void main(String[] args){
        double[][] a = {{1,2,3},{4,5,6}};
        double[][] b = {{1,4},{2,5},{3,6}};

        double[][] c = mult(a,b);
        StdArrayIO.print(c);
        StdArrayIO.print(b);
        c = transpose(b);
        StdArrayIO.print(c);

        //测试
    }
}
