import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Board {
    /**
     * 2d arrays for visible board and hidden board (contains mines later).
     */
    char[][] board;
    char[][] hiddenBoard;
    int row;
    int column;
    int mines;

    public Board(int row, int column, int mines) {
        this.board = new char[row][column];
        this.row = row;
        this.column = column;
        this.mines = mines;
        this.hiddenBoard = new char[row][column];
    }

    Scanner scanner = new Scanner(System.in);
    Cell cell = new Cell();


    /**
     * Fills board and hiddenBoard with empty places.
     */
    public void initializeBoard() {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                board[r][c] = ' ';
                hiddenBoard[r][c] = ' ';
            }
        }
    }

    public Board() { //created to try to fix main manu  problem. Can be removed later if not needed
    }

    /**
     * Prints visible board in terminal for user.
     */
    StringBuilder sb = new StringBuilder();

    public void printBoard(char[][] chooseBoard) {

        System.out.print("    ");
        for (int c = 0; c < column; c++) {
            char letter = (char) ('A' + c);
            sb.append(letter);
            System.out.printf("  %2s  ", letter);
        }
        System.out.println();

        System.out.print("----");
        for (int c = 0; c < column; c++) {
            System.out.print("+-----");
        }
        System.out.println();

        for (int r = 0; r <= row - 1; r++) {
            System.out.printf("%2d", r + 1);
            for (int c = 0; c < column; c++) {

                System.out.print("  |  " + chooseBoard[r][c]);
                if (c == column - 1) {
                    System.out.println("  | ");
                }
            }
            System.out.print("----");
            for (int c = 0; c < column; c++) {
                System.out.print("+-----");
            }
            System.out.println();
        }
    }

    public void makeMove() {
        checkWin();
        printBoard(hiddenBoard);
        System.out.println("Choose row: ");
        String inputRow;
        int inputRowNumber;
        while (true) {
            inputRow = scanner.nextLine();
            try {
                inputRowNumber = Integer.parseInt(inputRow);
                if (inputRowNumber <= row) {

                    System.out.println("Chosen row: " + inputRowNumber);
                    System.out.println("Choose column: ");
                    break;
                } else {
                    System.out.println("There aren't that many rows, try again"); // it excludes last row!!!
                }
            } catch (Exception e) {
                System.out.println("That doesn't look like a row number, try again");
            }
        }

        String inputColumn;
        String inputColumnUpperCase;
        int columnIndex;
        while (true) {
            inputColumn = scanner.nextLine();
            inputColumnUpperCase = inputColumn.toUpperCase();
            if (inputColumnUpperCase.length() == 1) {
                char columnLetter = inputColumnUpperCase.charAt(0);
                if (sb.indexOf(String.valueOf(columnLetter)) != -1) {
                    System.out.println("Chosen column: " + inputColumnUpperCase);
                    columnIndex = inputColumnUpperCase.charAt(0) - 'A';
                    break;
                } else {
                    System.out.println("Looks like the column you want to check doesn't exist, try again");
                }
            } else {
                System.out.println("Hm. Can a column be called " + inputColumnUpperCase + "? Try again");
            }
        }

        if (hiddenBoard[inputRowNumber - 1][columnIndex] == 'X') {
            System.out.println("Boom. There was a mine on " + inputColumnUpperCase + inputRowNumber);
            printBoard(hiddenBoard);
            playAgainQuestion();

        } else {
            board[inputRowNumber - 1][columnIndex] = 'O';
            System.out.println("There was no bomb on " + inputColumnUpperCase + inputRowNumber + ". You can make next move:");

            printBoard(board);
            makeMove();
        }

    }


    /**
     * adds mines to hiddenBoard, randomly according to board size.
     */
    public void addMines() {
        int minesCount = 0;


        while (minesCount < mines) {

            int x = (int) (Math.random() * hiddenBoard.length);
            int y = (int) (Math.random() * hiddenBoard[0].length);

            if (hiddenBoard[x][y] == ' ') {
                hiddenBoard[x][y] = 'X';
                minesCount++;
            }

        }

    }

    public void checkWin() {
        boolean win = true;
        for (int x = 0; x < board.length; x++) { //check for all cells in board
            for (int y = 0; y < board[x].length; y++) { //
                if (board[x][y] == ' ' && hiddenBoard[x][y] != 'X') {
                    win = false;
                    break;
                }

            }
        }
        if (win) {
            System.out.println("You win!");
            playAgainQuestion();
        }
    }

    public void playAgainQuestion() {
        System.out.println("Would you like to play again? yes or no");
        String answer;
        String answerLowerCase;

        while (true) {
            answer = scanner.nextLine();
            answerLowerCase = answer.toLowerCase();
            if (answerLowerCase.equals("yes")) {
                Menu menu = new Menu();
                menu.menu();
                break;
            } else if (answerLowerCase.equals("no")) {
                System.out.println("Thank you for coming!");// program doesn't end if you press np
                System.exit(0);
            } else {
                System.out.println("Yes or no?");
            }
        }
    }
}



