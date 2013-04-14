package at.edu.hti.st.pathfinder;

public class SimplePathFinder extends AbstractPathFinder {

    private boolean[][] visited;

    @Override
    public void wrritePathToConsole() {
        if (matrix == null) {
            System.err.println("No input available!");
        }
        initVisited();
        calculate(new IndexPair(0, 0), null, Directions.START);
    }

    private void initVisited() {
        int maxM = matrix.length;
        int maxN = matrix[0].length;
        visited = new boolean[maxM][maxN]; // implicit initialized with false
    }

    private int calculate(IndexPair index, IndexPair preIndex, Directions direction) {
        visited[index.getI()][index.getJ()] = true;
        int rigth, left, down, up;

        // right
        if ((index.j + 1 < matrix[0].length) & !(visited[index.i][index.j + 1])
                & !(index.i == preIndex.i & index.j + 1 == preIndex.j)) {
            rigth = calculate(new IndexPair(index.i, index.j + 1), index, Directions.FROM_LEFT);
        }

        // left

        // down

        // up

        IndexPair up = new IndexPair(index.i - 1, index.j);
        IndexPair down = new IndexPair(index.i + 1, index.j);

        visited[index.getI()][index.getJ()] = false;
        return -1; // TODO has to be defined
    }

    private class IndexPair {
        private int i;
        private int j;

        public IndexPair(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public void set(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }
    }

}
