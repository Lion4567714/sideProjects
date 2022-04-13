public class Life implements Runnable {

    public static int gridSize = 32;
    public static boolean grid[][];
    public static int turnNumber = 0;
    public static int population = 0;

    public static Display display = new Display();
    public static StatisticsDisplay stats = new StatisticsDisplay();

    public void run() {
        grid = initializeGrid();
        
        display.run();
        stats.initialize(display.frame);

        Stopwatch stopwatch1 = new Stopwatch();
        Stopwatch stopwatch2 = new Stopwatch();

        while (true) {
            while (!Display.START_SIMULATION) {
                Thread.yield();
            }

            stopwatch1.start();

            stopwatch2.start();
            grid = nextTurn(grid);
            stopwatch2.stop();
            System.out.println("Logic time: " + stopwatch2.getDurationInMilliseconds());

            display.updateDisplay(grid, turnNumber);
            turnNumber++;
            stats.updateStatistics(turnNumber, population);

            stopwatch1.stop();
            System.out.println("Turn time: " + stopwatch1.getDurationInMilliseconds());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static boolean[][] initializeGrid() {
        boolean newGrid[][] = new boolean[gridSize][gridSize];

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                newGrid[i][j] = false;
            }
        }

        return newGrid;
    }

    static boolean[][] nextTurn(boolean grid[][]) {
        boolean newGrid[][] = new boolean[gridSize][gridSize];
        newGrid = initializeGrid();

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
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

                    if (j < gridSize - 1) {
                        if (grid[i - 1][j + 1]) {   // Not top right
                            neighborsCount++;
                        }
                    }
                }

                if (j < gridSize - 1) {
                    if (grid[i][j + 1]) {           // Not right
                        neighborsCount++;
                    }
                }

                if (i < gridSize - 1) {
                    if (j < gridSize - 1) {
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
                    } else {
                        newGrid[i][j] = false;
                        population--;
                    }
                } else {
                    if (neighborsCount == 3) {
                        newGrid[i][j] = true;
                        population++;
                    }
                }
            }
        }

        return newGrid;
    }
}
