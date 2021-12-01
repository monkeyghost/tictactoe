import java.util.*;

public class TicTacToe {
    static Scanner in;
    static char [][] gameBoard;
    static char player;
    static boolean gameEnded = false;

    public static void main(String[] args) {
        //2D array represents play area which resembles a #
        gameBoard= new char[3][3];
        in = new Scanner(System.in);
        player = 'X';

        initGame();
        while (!gameEnded) {
            showBoard();
            makeMove();
        }
    }

    private static void initGame(){
        for (char[] row : gameBoard) {
            Arrays.fill(row, '-');
        }
        System.out.println("Lets play!");
    }

    private static void showBoard(){
        System.out.println(" 012");
        for (int row = 0; row < gameBoard.length; row++) {
            showRow(row);
        }
    }

    private static void showRow(int row){
        System.out.print(row);
        for (int col = 0; col < gameBoard[row].length; col++) {
            System.out.print(gameBoard[row][col]);
        }
        System.out.println();
    }

    private static void makeMove(){
        System.out.printf("Player %s: Make your move. Select a free slot. Enter row number (value 0, 1 or 2): ", player);
        int row = getPlayerInput();
        showRow(row);
        System.out.printf("Now player %s, enter column number (value 0, 1 or 2): ", player);
        int col = getPlayerInput();

        if (gameBoard[row][col] != '-') {
            System.out.println("Invalid move, try another move: ");
        } else {
            gameBoard[row][col] = player;
            checkWin(row,col);
            player = switchPlayer();
        }
    }

    private static Integer getPlayerInput(){
        while (!gameEnded){
            try {
                int inputNumber = in.nextInt();
                if (inputNumber >= 0 && inputNumber < 3) {
                    return inputNumber;
                }
                System.out.println("Number out of range. Select a valid number: ");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter valid number: ");
                in.nextLine();
            }
        }
        return -1;
    }

    private static void checkWin(int row, int col){
        checkRowForWin(row);
        checkColForWin(col);
        /*check win diagonal only if playInput is added to a corner or the middle
        * determine if the slot is a corner or the middle by modulo */
        if ((row+col) % 2 == 0) {
            checkDiagonalForWin(row,col);
        }
        if (isDraw()) {
            endGame();
        }
    }

    private static void checkRowForWin(int row){
        if (gameBoard[row][0] == gameBoard[row][1] && gameBoard[row][1] == gameBoard[row][2]){
            endGame();
        }
    }

    private static void checkColForWin(int col){
        if (gameBoard[0][col] == gameBoard[1][col] && gameBoard[1][col] == gameBoard[2][col]){
            endGame();
        }
    }

    private static void checkDiagonalForWin(int row, int col){
         if (row != 1 && gameBoard[1][1] == gameBoard[row][col]){
            if (row == col){
                if (gameBoard[0][0] == gameBoard[2][2]){
                    endGame();
                }
            } else {
                if (gameBoard[0][2] == gameBoard[2][0]){
                    endGame();
                }
            }
        }
    }

    private static char switchPlayer(){
        if (player == 'X') {
            return 'O';
        } else {
            return 'X';
        }
    }

    private static boolean isDraw(){
        for (char[] row : gameBoard) {
            for (char col : row) {
                if (col == '-') return false;
            }
        }
        return true;
    }

    private static void endGame(){
        showBoard();
        gameEnded = true;
        if ((isDraw())){
            System.out.println("It's a draw!");
        } else {
            System.out.printf("Player %s wins!", player);
        }
    }
}
