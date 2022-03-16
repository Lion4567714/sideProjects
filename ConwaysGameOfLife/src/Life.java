public class Life {

    public static final int GRID_SIZE = 48;
    public static boolean grid[][];
    public static int turnNumber = 0;

    static Display display = new Display();

    public static void main(String[] args) {
        grid = initializeGrid();

        display.run();

        for (turnNumber = 0; turnNumber < 10000; turnNumber++) {
            while (!Display.START_SIMULATION) {
                Thread.yield();
            }

            grid = nextTurn(grid);

            display.updateDisplay(grid, turnNumber);
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static boolean[][] initializeGrid() {
        boolean newGrid[][] = new boolean[GRID_SIZE][GRID_SIZE];

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                newGrid[i][j] = false;
            }
        }

        return newGrid;
    }

    static boolean[][] nextTurn(boolean grid[][]) {
        boolean newGrid[][] = new boolean[GRID_SIZE][GRID_SIZE];
        newGrid = initializeGrid();

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                int neighborsCount = 0;

                // Check for neighbors

                if (i > 0) {
                    if (j > 0) {
                        if (grid[i - 1][j - 1]) {   // Not top left
                            neighborsCount++;
                        }
                    }

                    if (grid[i - 1][j]) {           // Not top
                        neighborsCount++;
                    }

                    if (j < GRID_SIZE - 1) {
                        if (grid[i - 1][j + 1]) {   // Not top right
                            neighborsCount++;
                        }
                    }
                }

                if (j < GRID_SIZE - 1) {
                    if (grid[i][j + 1]) {           // Not right
                        neighborsCount++;
                    }
                }

                if (i < GRID_SIZE - 1) {
                    if (j < GRID_SIZE - 1) {
                        if (grid[i + 1][j + 1]) {   // Not bottom right
                            neighborsCount++;
                        }
                    }

                    if (grid[i + 1][j]) {           // Not bottom
                        neighborsCount++;
                    }

                    if (j > 0) {
                        if (grid[i + 1][j - 1]) {   // Not bottom left
                            neighborsCount++;
                        }
                    }
                }

                if (j > 0) {
                    if (grid[i][j - 1]) {           // Not left
                        neighborsCount++;
                    }
                }

                // Change state

                if (grid[i][j]) {
                    if (neighborsCount == 2 || neighborsCount == 3) {
                        newGrid[i][j] = true;
                    }
                } else {
                    if (neighborsCount == 3) {
                        newGrid[i][j] = true;
                    }
                }
            }
        }

        return newGrid;
    }
}
