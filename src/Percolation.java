/**
 * Created by Vera_Sidarovich on 2/3/2015.
 */
public class Percolation {
    private int[][] sites;

    private QuickFindUF uf;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        sites = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sites[i][j] = 0;
            }
        }
        uf = new QuickFindUF(N * N);
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        System.out.println("i = " + i + "  j = " + j);
        int currentIndex = (sites.length - 1) * j + i;
        sites[i][j] = 1;

        int upShift = i - 1;
        int upIndex = (sites.length - 1) * j + upShift;

        if (upIndex >= 0 && upIndex < uf.count() && isOpen(upShift, j)) {
            uf.union(currentIndex, upIndex);
        }

        int downShift = i + 1;
        int downIndex = (sites.length - 1) * j + downShift;

        if (downIndex >= 0 && downIndex < uf.count() && isOpen(downIndex, j)) {
            uf.union(currentIndex, downIndex);
        }

        int rightShift = j + 1;
        int rightIndex = (sites.length - 1) * rightShift + i;

        if (rightIndex >= 0 && rightIndex < uf.count() && isOpen(i, rightShift)) {
            uf.union(currentIndex, rightIndex);
        }

        int leftShift = j - 1;
        int leftIndex = (sites.length - 1) * leftShift + i;

        if (leftIndex >= 0 && leftIndex < uf.count() && isOpen(i, leftShift)) {
            uf.union(currentIndex, leftIndex);
        }
    }

    // open site (row i, column j) if it is not open already
    public boolean isOpen(int i, int j) {
        return sites[i][j] == 1;
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        return false;
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        int randomArraySize = 5;
        Percolation percolation = new Percolation(randomArraySize);

        while (!percolation.percolates()) {
            int randomRow = (int) (Math.random() * randomArraySize);
            int randomColumn = (int) (Math.random() * randomArraySize);
            try {
                if (!percolation.isOpen(randomRow, randomColumn)) {
                    percolation.open(randomRow, randomColumn);
                }
            } catch (Exception e) {
                System.out.println("Error!");
            }
        }
    }
}