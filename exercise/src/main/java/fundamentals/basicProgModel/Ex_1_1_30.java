package fundamentals.basicProgModel;

/**************************************************
 *
 *
 *
 *************************************************/

import util.api.StdArrayIO;
import util.api.StdIn;

/**
 * @auther Bruce Jiang
 */
public class Ex_1_1_30 {
    public static void setGCD(boolean[][] a){
        for(int i = 0; i < a.length; i ++ ){
            for(int j =0; j < a[i].length; j ++){
                if(gcd(i+1, j+1) == 1){
                    a[i][j] = true;
                }else{
                    a[i][j] = false;
                }
            }
        }
    }

    /**
     *  Euclidean algorithm to get the greatest common divisor about a and b
     * @param a
     * @param b
     * @return
     */
    public static int gcd(int a, int b){
        if(a < b){ int tmp = a; a = b; b = tmp;}
        while(b!=0){
            int c = a % b;
            a = b;
            b = c;
        }
        return a;
    }
    public static void main(String[] args){
        int x = StdIn.readInt();
        int y = StdIn.readInt();
        boolean[][] a = new boolean[x][y];
        setGCD(a);
        StdArrayIO.print(a);
    }
}
