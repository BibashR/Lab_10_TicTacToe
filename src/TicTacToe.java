import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do {
            // Step 1: Clear the board and initialize variables
            clearBoard();
            String currentPlayer = "X";
            boolean gameWon = false;
            boolean gameTie = false;
            int moveCount = 0;

            // Step 2: Game loop
            while (!gameWon && !gameTie) {
                display(); // Show the current board
                int row, col;

                // Step 3: Get a valid move from the player
                do {
                    row = SafeInput.getRangedInt(scanner, "Enter row (1-3): ", 1, 3) - 1;
                    col = SafeInput.getRangedInt(scanner, "Enter column (1-3): ", 1, 3) - 1;
                } while (!isValidMove(row, col));

                // Step 4: Record the move on the board
                board[row][col] = currentPlayer;
                moveCount++;

                // Step 5: Check for a win or tie
                if (moveCount >= 5) {
                    gameWon = isWin(currentPlayer);
                    if (!gameWon && moveCount == 9) {
                        gameTie = isTie();
                    }
                }

                // Step 6: Switch player if game is not over
                if (!gameWon && !gameTie) {
                    currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                }
            }

            // Step 7: Display final board and announce results
            display();
            if (gameWon) {
                System.out.println("Player " + currentPlayer + " wins!");
            } else {
                System.out.println("It's a tie!");
            }

            // Step 8: Ask if players want to play again
            playAgain = SafeInput.getYNConfirm(scanner, "Do you want to play again? (Y/N): ");
        } while (playAgain);
    }

    // Step 1: Clear the board
    private static void clearBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = " ";
            }
        }
    }

    // Step 2: Display the board
    private static void display() {
        System.out.println("\n  1 2 3");
        for (int i = 0; i < ROWS; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j]);
                if (j < COLS - 1) System.out.print("|");
            }
            System.out.println();
            if (i < ROWS - 1) System.out.println("  -----");
        }
    }

    // Step 3: Validate if the chosen move is valid
    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    // Step 5: Check if there is a win
    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    // Step 5: Check for a row win
    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROWS; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    // Step 5: Check for a column win
    private static boolean isColWin(String player) {
        for (int j = 0; j < COLS; j++) {
            if (board[0][j].equals(player) && board[1][j].equals(player) && board[2][j].equals(player)) {
                return true;
            }
        }
        return false;
    }

    // Step 5: Check for a diagonal win
    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    // Step 5: Check if the game is a tie
    private static boolean isTie() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}
