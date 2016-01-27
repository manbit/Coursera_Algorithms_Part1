import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    // this private field stores the dimension of the grid
    private int N;

    // we use a weighted quick union-find data structure to model the connection amongst sites in the composite system
    private WeightedQuickUnionUF wquf;

    // we use this private integer array to maintain which sites are open and which are closed closed = -1, open = 0
    private int[] sites;

    // this private method helps us retrieve the index of a site in the grid as represented in the WeightedQuickUnion object
    private int getIndex(int i, int j) {
        return ((i-1)*N + j);
    }

    // this private method checks if a cell is a valid cell or not
    private boolean validate(int i, int j) {
        return (i >= 1 && i <= N && j >= 1 && j <= N);
    }

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        if(N <= 0) {
            throw new IllegalArgumentException();
        }

        // assign the dimension of the grid and also the sites array
        this.N = N;
        this.sites = new int[N*N + 2];

        // bot the top and the bottom sites are open initially rest all are closed sites
        this.sites[0] = this.sites[N*N + 1] = 0;
        for(int i = 1; i < N*N + 1; ++i) {
            this.sites[i] = -1;
        }
        // we allocate additional two sites which act as the top and bottom sites in the Percolation grid
        this.wquf = new WeightedQuickUnionUF(N*N + 2);
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        if(!validate(i, j)) {
            throw new IndexOutOfBoundsException();
        }

        if(!isOpen(i, j)) {
            int index = getIndex(i, j);
            sites[index] = 0;

            // if the site is a top site
            if(i == 1) {
                wquf.union(index, 0);
            }

            // if the site is a bottom site
            if(i == N) {
                wquf.union(index, N*N + 1);
            }

            // if the site above is open
            if(validate(i-1, j) && isOpen(i-1, j)) {
                wquf.union(index, getIndex(i-1, j));
            }

            // if the site below is open
            if(validate(i+1, j) && isOpen(i+1, j)) {
                wquf.union(index, getIndex(i+1, j));
            }

            // if the site towards left is open
            if(validate(i, j-1) && isOpen(i, j-1)) {
                wquf.union(index, getIndex(i, j-1));
            }

            // if the site towards right is open
            if(validate(i, j+1) && isOpen(i, j+1)) {
                wquf.union(index, getIndex(i, j+1));
            }
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        if(!validate(i, j)) {
            throw new IndexOutOfBoundsException();
        }

        return sites[getIndex(i,j)] == 0;
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        if(!validate(i, j)) {
            throw new IndexOutOfBoundsException();
        }

        return this.wquf.connected(0, getIndex(i,j));
    }

    // does the system percolate?
    public boolean percolates() {
        return this.wquf.connected(0, N*N + 1);
    }

    public static void main(String[] args) {

    }
}
