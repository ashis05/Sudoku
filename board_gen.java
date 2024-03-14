import java.util.*;

public class board_gen {

    private static final int SIZE = 9;
    private static double REMOVAL_PERCENTAGE = 0.3; // Adjust to change difficulty
    static int Level = 1;

    public static void main(String[] args) {
        //int[][] solvedGrid = generateSolvedGrid();
        //int[][] randomizedGrid = createRandomizedGrid(solvedGrid);
        //printGrid(solvedGrid);
        //printGrid(randomizedGrid);
        System.out.println();
        System.out.println();
        System.out.println();
        // printGrid(solvedGrid);
    }

    public static int[][] generateSolvedGrid() {
        SudokuSolver solver = new SudokuSolver();
        int[][] solvedGrid = new int[SIZE][SIZE];
        solver.solver(solvedGrid);
        return solvedGrid;
    }

    public static int[][] createRandomizedGrid(int[][] solvedGrid, String username, String password) {
        int[][] randomizedGrid = new int[SIZE][SIZE];
        Random random = new Random();

        // Copy the solved grid to the randomized grid
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(solvedGrid[i], 0, randomizedGrid[i], 0, SIZE);
            // System.arraycopy(solvedGrid[i],randomizedGrid[i]);
        }
        MainSudoku ms = new MainSudoku();
        Level = ms.get_level(username, password);

        // Determine the number of cells to remove based on the removal percentage
        int cellsToRemove;
        if(Level <= 5)
        {cellsToRemove = (int) (SIZE * Level);}
        else
        {cellsToRemove = (int) (SIZE * 5);}

        // Randomly remove cells
        for (int i = 0; i < cellsToRemove; i++) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);

            // Skip cells that are already empty
            if (randomizedGrid[row][col] != 0) {
                randomizedGrid[row][col] = 0;
            } else {
                // Try again with a different cell
                i--;
            }
        }

        return randomizedGrid;
    }

    private static void printGrid(int[][] grid) {
        System.out.println("-----------------------------");
        for (int i = 0; i < SIZE; i++) {
            System.out.print("|");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(grid[i][j] + "  ");
                if ((j + 1) % 3 == 0) {
                    System.out.print("|");
                }

            }
            System.out.println();
            if ((i + 1) % 3 == 0) {
                System.out.println("-----------------------------");
            }
        }
    }

}