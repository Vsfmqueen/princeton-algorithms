/**
 * Created by Vera_Sidarovich on 2/3/2015.
 */
public class Percolation {
    private int[][] array;

    private QuickFindUF uf;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        array = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                array[i][j] = -1;
            }
        }
        uf = new QuickFindUF(N * N);
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        int currentIndex = (array.length - 1) * j + i;

        int leftShift = i - 1;
        int leftIndex = (array.length - 1) * j + leftShift;

        if (leftIndex > 0 && leftIndex < uf.count() && isOpen(leftIndex, j)) {
            uf.union(currentIndex, leftIndex);
        }

        int rightShift = i + 1;
        int rightIndex = (array.length - 1) * j + rightShift;

        if (rightIndex > 0 && rightIndex < uf.count() && isOpen(rightIndex, j)) {
            uf.union(currentIndex, rightIndex);
        }

        int downShift = j + 1;
        int downIndex = (array.length - 1) * downShift + i;

        if (downIndex > 0 && downIndex < uf.count() && isOpen(downIndex, j)) {
            uf.union(currentIndex, downIndex);
        }

        int upShift = j - 1;
        int upIndex = (array.length - 1) * upShift + i;

        if (upIndex > 0 && upIndex < uf.count() && isOpen(upIndex, j)) {
            uf.union(currentIndex, upIndex);
        }
    }

    // open site (row i, column j) if it is not open already
    public boolean isOpen(int i, int j) {
        return false;
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
            int randomRow = (int) Math.random() * randomArraySize;
            int randomColumn = (int) Math.random() * randomArraySize;
            if (!percolation.isOpen(randomRow, randomColumn)) {
                percolation.open(randomRow, randomColumn);
            }
        }
    }
}