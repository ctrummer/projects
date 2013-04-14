package at.edu.hti.st.pathfinder;

abstract public class AbstractPathFinder implements IPathFinder {

    protected int[][] matrix;

    @Override
    public void setWeigthMatrix(int[][] weigthMatrix) {
        this.matrix = weigthMatrix;
        printMatrix();
    }

    private void printMatrix() {
        int m = matrix.length;
        int n = matrix[0].length;
        System.out.println("Matrix is a m*n matrix:" + m + "*" + n);
        for (int i = 0; i < m; i++) {
            printRow(i);
        }
    }

    private void printRow(int rowNumb) {
        for (int i = 0; i < matrix[rowNumb].length; i++) {
            System.out.print(matrix[rowNumb][i] + ", ");
        }
        System.out.println();
    }

}
