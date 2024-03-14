import java.util.*;

class SudokuSolver {

    private static final int SIZE = 9;

    public static void main(String[] args) {
        int[][] board = generateBoard();
        printGrid(board);

    }

    public static boolean solver(int[][] board) {
        Random r = new Random();
        int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        for (int i = array.length - 1; i > 0; i--) {
            int j = r.nextInt(i + 1);
            // Swap characters at indices i and j
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int num = 0; num <= 8; num++) {
                        // n = r.nextInt(SIZE);
                        if (isValidMove(board, row, col, array[num])) {
                            board[row][col] = array[num];

                            if (solver(board)) {
                                return true; // Successfully solved
                            }

                            // If placing the current number does not lead to a solution, backtrack
                            board[row][col] = 0;
                        }
                    }
                    return false; // No valid number for this cell
                }
            }
        }
        return true; // All cells filled, Sudoku solved
    }

    /*
     * public static boolean solve(int[][] board) {
     * Random r = new Random();
     * for (int i = 0; i < SIZE; i++) {
     * for (int j = 0; j < SIZE; j++) {
     * int n = r.nextInt(SIZE);
     * if (isValidMove(board, i, j, n)) {
     * board[i][j] = n;
     * } else {
     * board[i][j] = 0;
     * System.out.println("InValid ");
     * return false;
     * }
     * }
     * }
     * System.out.println("Valid ");
     * return true;
     * }
     */

    public static int[][] generateBoard() {
        boolean flag = true;
        int[][] temp1 = new int[SIZE][SIZE];
        while (flag) {
            int[][] temp = new int[SIZE][SIZE];
            if (solver(temp)) {
                temp1 = temp;
                flag = false;
            }
        }
        return temp1;
    }

    private static boolean isValidMove(int[][] board, int row, int col, int num) {
        // Check if the number is not in the current row and column
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        // Check if the number is not in the current 3x3 box
        int boxStartRow = row - row % 3;
        int boxStartCol = col - col % 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[boxStartRow + i][boxStartCol + j] == num) {
                    return false;
                }
            }
        }

        return true; // The move is valid
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