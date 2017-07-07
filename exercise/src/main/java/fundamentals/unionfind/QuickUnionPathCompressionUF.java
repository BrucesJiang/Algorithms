/******************************************************************************
 *  Compilation:  javac QuickUnionPathCompressionUF.java
 *  Execution:  java QuickUnionPathCompressionUF < input.txt
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
 *  The {@code QuickUnionPathCompressionUF} class represents a
 *  union–find data structure.
 *  It supports the <em>union</em> and <em>find</em> operations, along with
 *  methods for determining whether two sites are in the same component
 *  and the total number of components.
 *  <p>
 *  This implementation uses quick union (no weighting) with full path compression.
 *  Initializing a data structure with <em>n</em> sites takes linear time.
 *  Afterwards, <em>union</em>, <em>find</em>, and <em>connected</em> take
 *  logarithmic amortized time <em>count</em> takes constant time.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/15uf">Section 1.5</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @auther Bruce Jiang
 */
public class QuickUnionPathCompressionUF {
    private int[] parent; // parent[i] = parent of i
    private int count; // number of component


    /**
     * Initializes an empty union-find data structure with {@code N} isolated components {@code 0} through {@code N-1}
     *
     * @param N the number of sites
     * @throws IllegalArgumentException if N < 0
     */
    public QuickUnionPathCompressionUF(int N){
        this.count = N;
        this.parent = new int[N];
        for(int i = 0; i < this.count; i ++){
            this.parent[i] = i;
        }
    }

    /**
     * Returns the number of component
     *
     * @return the number of component
     */
    public int count(){
        return this.count;
    }

    /**
     *  Returns whether the two site are the same component
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @return {@code true} if the two site {@code p} and {@code q} are in the same component
     *          {@code false} otherwise
     */
    public boolean connected(int p, int q){
        return find(p) == find(p);
    }

    /**
     * Returns the component identifier for the component containing the site {@code p}.
     *
     * @param idx the integer representing one object
     * @return the component identifier for the component
     * @throws IndexOutOfBoundsException unless {@code 0 <= p < N}
     */
    public int find(int idx){
        validate(idx);
        int root = idx;
        while(root != parent[root]){
            root = parent[root];
        }
        while(idx != root){ //路径折叠法
            int tmp = parent[idx];
            parent[idx] = root;
            idx = tmp;
        }
        return root;
    }


    /**
     *  Merges the component containing site {@code p} with the
     *  component containing site {@code q}
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @throws IndexOutOfBoundsException unless both {@code 0 <= p < N} and {@code 0 <= q < N}
     */
    public void union(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);

        if(rootP == rootQ) return ;
        parent[rootP] = rootQ;
        count --;
    }

    private void validate(int num){
        if(num < 0 || num > parent.length)
            throw new IllegalArgumentException("Argument is not valid");
    }

    /**
     *  Unit test the {@code QuickUnionPathCompressionUF} data type
     *
     * @param args the command-line  args
     */
    public static void main(String[] args){
        String fileName = args[0];
        In in = new In(fileName);
        Out out = new Out();
        int N = in.readInt();
        QuickUnionPathCompressionUF qupc = new QuickUnionPathCompressionUF(N);
        while(!in.isEmpty()){
            int p = in.readInt();
            int q = in.readInt();

            if(!qupc.connected(p, q)) continue;
            qupc.union(p,q);
            out.println(p + " " + q);
        }
        out.println(qupc.count() + " components");
    }
}
