/******************************************************************************
 *  Compilation:  javac WeightedQuickUnionPathCompressionUF.java
 *  Execution:  java WeightedQuickUnionPathCompressionUF < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/15uf/tinyUF.txt
 *                http://algs4.cs.princeton.edu/15uf/mediumUF.txt
 *                http://algs4.cs.princeton.edu/15uf/largeUF.txt
 *
 *  Quick-union with path compression (but no weighting by size or rank).
 *
 ******************************************************************************/
package fundamentals.unionfind;

import util.api.In;
import util.api.Out;

/**
 *   The {@code WeightedQuickUnionPathCompressionUF} class represents a union-find data structure
 *   It supports the <em>union</em> and <em>find</em> operations, along with operations for determining
 *   whether two site are in the same component or not and the total number of components
 *  <p>
 *  This implementation uses weighted quick union (by size) with full path compression
 *  Initializes a data structure with <em>N</em> sites take a linear time.
 *  Afterwards, <em>union</em>, <em>find</em>, and <em>connected</em> take logarithmic time (in the worst case) and
 *  <em>count</em> takes constant time. Moreover, the amortized tiem per <em>union</em>, <em>find</em>,
 *  and <em>connected</em> operation has inverse Ackermann complexity
 *
 * @auther Bruce Jiang
 */
public class WeightedQuickUnionPathCompressionUF {
    private int[] parent; // parent[i] = parent of i
    private int[] size; // size[i] = number of sites in the tree rooted at i
                            //Note: not necessarily correct if i is not a root node
    private int count; // number of components

    /**
     * Initializes an empty union-find data structure with {@code N} sites
     *
     * @param N the number of sites
     * @throws IllegalArgumentException unless {@code  0 <= N < Integer.MAX_VALUE }
     */
    public WeightedQuickUnionPathCompressionUF(int N){
        this.count = N;
        this.parent = new int[N];
        this.size = new int[N];
        for(int i = 0; i < N; i ++){
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * Returns the number of the components
     *
     * @return the number of the components
     */
    public int count(){
        return count;
    }

    /**
     *  Returns the component identifier of the component containing site {@code idx}
     *
     * @param idx the integer representing one site
     * @return the component identifier of the component containing site {@code idx}
     * @throws IllegalArgumentException unless {@code 0 <= idx < N }
     */
    public int find(int idx){
        validate(idx);
        int root = idx;
        while(root != parent[root]) root = parent[root];
        while(root != idx){
            int tmp = parent[idx];
            parent[idx] = root;
            idx = tmp;
        }
        return root;
    }

    /**
     *  Returns whether the two sites are in the same component or not
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @return {@code true} if {@code p} and {@code q} are in the same component
     *          {@code false} otherwise
     */
    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    /**
     * Merges the component containing site {@code p} with the
     * the component containing site {@code q}.
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @throws IndexOutOfBoundsException unless
     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public void union(int p, int q){
        validate(p);
        validate(q);
        int rootP = find(p);
        int rootQ = find(q);

        if(rootP == rootQ) return;

        // make smaller root point to larger one
        if(rootP < rootQ){
            this.parent[rootP] = rootQ;
            this.size[rootQ] += this.size[rootP];

        }else{
            this.parent[rootQ] = rootP;
            this.size[rootP] += this.size[rootQ];
        }
        count --;
    }


    private void validate(int idx){
        if(idx < 0 || idx > parent.length)
            throw new IllegalArgumentException("Argument is not valid");
    }
    /**
     * Unit test the {@code WeightedQuickUnionPathCompressionUF} data structure
     *
     * @param args the command-line args
     */
    public static void main(String[] args){
        String fileName = args[0];
        In in = new In(fileName);
        Out out = new Out();
        int N = in.readInt();
        WeightedQuickUnionPathCompressionUF wq = new WeightedQuickUnionPathCompressionUF(N);
        while(!in.isEmpty()){
            int p = in.readInt();
            int q = in.readInt();

            if(wq.connected(p, q)) continue;
            wq.union(p, q);
            out.println(p + " " + q);
        }
        out.println(wq.count() + " components");
    }
}
