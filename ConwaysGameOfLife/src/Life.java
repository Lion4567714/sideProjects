public class Life {

    public static final int GRID_SIZE = 32;

    static Display display = new Display();

    public static void main(String[] args) {
        Boolean grid[][] = new Boolean[GRID_SIZE][GRID_SIZE];
        grid = initializeBooleanArray(grid);

        display.initialize(GRID_SIZE);
        
        // Customize initial grid here
        grid[2][3] = true;
        grid[3][3] = true;
        grid[3][4] = true;
        grid[5][5] = true;
        grid[4][3] = true;

        for (int i = 0; i < 100; i++) {
            grid = nextTurn(grid);

            display.updateDisplay(grid);
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //printGrid(grid);
        }
    }

    static Boolean[][] initializeBooleanArray(Boolean grid[][]) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = false;
            }
        }

        return grid;
    }

    static Boolean[][] nextTurn(Boolean grid[][]) {
        Boolean newGrid[][] = new Boolean[GRID_SIZE][GRID_SIZE];
        newGrid = initializeBooleanArray(newGrid);

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

    static void printGrid(Boolean grid[][]) {
        for (Boolean row[] : grid) {
            for (Boolean cell : row) {
                if (cell) {
                    System.out.print("X");
                } else {
                    System.out.print("O");
                }
            }

            System.out.println();
        }

        System.out.println("-------");
    }
}
