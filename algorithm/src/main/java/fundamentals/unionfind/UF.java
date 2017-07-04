/******************************************************************************
 *  Compilation:  javac UF.java
 *  Execution:    java UF < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/15uf/tinyUF.txt
 *                http://algs4.cs.princeton.edu/15uf/mediumUF.txt
 *                http://algs4.cs.princeton.edu/15uf/largeUF.txt
 *
 *  Weighted quick-union by rank with path compression by halving.
 *
 *  % java tinyUF.txt
 *   4 3
 *   3 8
 *   6 5
 *   9 4
 *   2 1
 *   5 0
 *   7 2
 *   6 1
 *   2 components !
 *
 * *****************************************************************************/

package fundamentals.unionfind;

import util.api.In;
import util.api.Out;


/**
 *
 *
 * @auther Bruce Jiang
 */
public class UF {
    private int[] id; // id
    private int count; // number of components

    /**
     * Initializes N sites with integer names (0 to N-1)
     *
     * @param N number of sites
     */
    public UF(int N){
        count = N;
        id = new int[N];
        for(int i = 0; i < N; i ++){
            id[i] = i;
        }
    }

    /**
     * Add connection between endpoint q and endpoint p
     *
     * @param p the endpoint
     * @param q the other endpoint
     */
    public void union(int p, int q){
        int pId = find(p);
        int qId = find(q);

        if(pId == qId) return;
        for(int i = 0; i < id.length; i ++){
            if(id[i] == pId) id[i] = qId;
        }
        count --;
    }

    /**
     *  Component identifier for p (0 to N-1)
     *
     * @param p the endpoint
     * @return component identifier for p
     */
    public int find(int p){
        return id[p];
    }

    /**
     *  Returns whether endpoint p and endpoint q are in the same component or not
     *
     * @param p the endpoint
     * @param q the endpoint
     * @return {@code true} if endpoint {@code p} and endpoint {@code q} in the same component
     *          {@code false} otherwise
     */
    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    /**
     * Returns number of components
     *
     * @return number of components
     */
    public int count(){
        return count;
    }


    /**
     * Unit test the {@code UF} data type
     *
     * @param args the command-line args
     */
    public static void main(String[] args){
        //StdOut.println(args[0]);
        String fileName = args[0].trim();
        In in = new In(fileName);
        Out out = new Out();
        int N = in.readInt(); // the number of contact
        UF uf = new UF(N); // Initializes N sites
        while(!in.isEmpty()){
            int p = in.readInt();
            int q = in.readInt();
            if(uf.connected(p, q)) continue; // if connected
            uf.union(p,q);
            out.println(p + " " + q);
        }
        out.println(uf.count() + " components !");
    }
}
