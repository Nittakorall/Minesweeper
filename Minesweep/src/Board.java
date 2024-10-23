import java.util.Arrays;
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
        printBoard(hiddenBoard);
        System.out.println("Choose row: ");
        int inputRow;
        while (true) {
            inputRow = scanner.nextInt();
            if (inputRow < row) {
                scanner.nextLine();
                System.out.println("Chosen row: " + inputRow);
                System.out.println("Choose column: ");
                break;
            } else {
                System.out.println("There's no such row, try again");
            }
        }
        String inputColumn;
        String inputColumnUpperCase;
        int columnIndex;
        while (true) {
            inputColumn = scanner.nextLine();
            inputColumnUpperCase = inputColumn.toUpperCase();
            System.out.println(inputRow + inputColumn + inputColumnUpperCase);
            char columnLetter = inputColumnUpperCase.charAt(0);
            if (sb.indexOf(String.valueOf(columnLetter)) != -1) {
                columnIndex = inputColumnUpperCase.charAt(0) - 'A';
                break;
            } else {
                System.out.println("That's not a column");
            }
        }


        if (hiddenBoard[inputRow - 1][columnIndex] == 'X') {
            System.out.println("Boom");
            printBoard(hiddenBoard);
            // isRunning = false;
            Menu menu = new Menu();
            menu.menu();
            //break;
        } else {
            board[inputRow - 1][columnIndex] = 'O';
            System.out.println("make next move");
            printBoard(board);

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

     
       /* for (int r = 1; r < 7; r++) {
            for ( r = 1; r < row; r++) {
                System.out.print(r);
                for (int c = 0; c < 6; c++) {
                    for ( c = 0; c < column - 1; c++) {

                        System.out.print("  |  " + board[r][c]);
                        if (c == 5) {
                            System.out.println("  | ");
                        }
                    }
                    System.out.println("---+-----+-----+-----+-----+-----+-----+");
                }
            }

          }
*/
    }
}
