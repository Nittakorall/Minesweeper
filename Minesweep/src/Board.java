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
    //  Cell cell = new Cell();


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

    StringBuilder sb = new StringBuilder(); //when creating a board, add all column letters to a new string

    /**
     * Prints visible board in terminal for user.
     */
    public void printBoard(char[][] chooseBoard) {
        // ANSI escape code för grön text
        String greenColor = "\033[32m";
        // ANSI escape code för grön bakgrund
        // ANSI escape code för röd text
        String redColor = "\033[31m";
        // ANSI escape code för grön bakgrund
        String greenBackground = "\033[42m";
        // ANSI escape code för röd bakgrund
        String redBackground = "\033[41m";
        // ANSI escape code för att återställa färgen
        String resetColor = "\033[0m";

        System.out.print("    ");
        for (int c = 0; c < column; c++) {
            char letter = (char) ('A' + c);
            sb.append(letter); // works weird because it adds letters several times, but works
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
                char currentChar = chooseBoard[r][c];
                if (currentChar == 'X') {
                    // Skriv ut med röd bakgrund om tecknet är 'X'
                    System.out.print("  |  " + redBackground + redColor + currentChar + resetColor);
                } else if (currentChar == 'O') {
                    System.out.print("  |  " + greenBackground + greenColor + currentChar + resetColor);
                } else {
                    System.out.print("  |  " + currentChar);
                }
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

    /**
     * starts checkMove in the beginning to check if user won previous time
     * if not, asks user to open new cell
     *
     * @param winTimes  add +1 if user wins
     * @param lostTimes add +1 if user lose
     */
    public void makeMove(int winTimes, int lostTimes) {
        checkWin(winTimes, lostTimes);
        printBoard(hiddenBoard); // better to remove later

        while (true) {

            System.out.println("1. Open cell \n 2. Add flag");
            int openOrFlag = scanner.nextInt();
            scanner.nextLine();

            if (openOrFlag == 1) {

                System.out.println("Choose row: ");
                String inputRow;
                int inputRowNumber;

                inputRow = scanner.nextLine(); //made string to avoid exception to nextInt
                try {
                    inputRowNumber = Integer.parseInt(inputRow); // trying making string to int
                    if (inputRowNumber <= row && inputRowNumber > 0) {

                        System.out.println("Chosen row: " + inputRowNumber);
                        System.out.println("Choose column: ");
                        //break;
                    } else {
                        System.out.println("There's no row " + inputRowNumber + ", try again");
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("That doesn't look like a row number, try again");
                    continue;
                }


                String inputColumn;
                String inputColumnUpperCase;
                int columnIndex;
                while (true) {
                    inputColumn = scanner.nextLine();
                    inputColumnUpperCase = inputColumn.toUpperCase();
                    if (inputColumnUpperCase.length() == 1) { //checks if input has more than 1 letter to make a char of it
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
                } //hidwdjo
                //while (true) {

                if (hiddenBoard[inputRowNumber - 1][columnIndex] == 'X') {
                    System.out.println("Boom. There was a mine on " + inputColumnUpperCase + inputRowNumber);
                    board[inputRowNumber - 1][columnIndex] = 'X';
                    //printBoard(hiddenBoard); mine shows in real board too
                    printBoard(board);
                    lostTimes++;
                    System.out.println("You won " + winTimes + " times");
                    System.out.println("You lost " + lostTimes + " times");
                    playAgainQuestion(winTimes, lostTimes);

                } else if (board[inputRowNumber - 1][columnIndex] != ' ') { //if a cell user picks isn't ' ' and has some other symbol
                    System.out.println("You've already opened this cell, please pick another one");
                    makeMove(winTimes, lostTimes);
                } else {
                    board[inputRowNumber - 1][columnIndex] = 'O';
                    System.out.println("There was no bomb on " + inputColumnUpperCase + inputRowNumber + ". You can make next move:");

                    printBoard(board);
                    makeMove(winTimes, lostTimes);
                }

            } else if (openOrFlag == 2) {
                System.out.println("Choose row: ");
                String inputRow;
                int inputRowNumber;
                //while (true) {
                inputRow = scanner.nextLine(); //made string to avoid exception to nextInt
                try {
                    inputRowNumber = Integer.parseInt(inputRow); // trying making string to int
                    if (inputRowNumber <= row && inputRowNumber > 0) {

                        System.out.println("Chosen row: " + inputRowNumber);
                        System.out.println("Choose column: ");
                        //break;
                    } else {
                        System.out.println("There's no row " + inputRowNumber + ", try again");
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("That doesn't look like a row number, try again");
                    continue;
                }

                String inputColumn;
                String inputColumnUpperCase;
                int columnIndex;
                while (true) {
                    inputColumn = scanner.nextLine();
                    inputColumnUpperCase = inputColumn.toUpperCase();
                    if (inputColumnUpperCase.length() == 1) { //checks if input has more than 1 letter to make a char of it
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

                if (hiddenBoard[inputRowNumber - 1][columnIndex] == ' ') {
                    board[inputRowNumber - 1][columnIndex] = 'ꚰ';
                    //printBoard(hiddenBoard); mine shows in real board too
                    printBoard(board);
                    makeMove(winTimes, lostTimes);

                } else {//if a cell user picks isn't ' ' and has some other symbol
                    System.out.println("You've already opened this cell, please pick another one");
                    makeMove(winTimes, lostTimes);
                }
            }
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

    /**
     * sets win and shows winning text unless there are some unopened cells that doesn't have mines according to hiddenboard
     *
     * @param winTimes
     * @param lostTimes
     */
    public void checkWin(int winTimes, int lostTimes) {
        boolean win = true;
        for (int x = 0; x < board.length; x++) { //check for all cells in board
            for (int y = 0; y < board[x].length; y++) {
                if (board[x][y] == ' ' && hiddenBoard[x][y] != 'X') { //if there's at least one cell ' ' on board that isn't X on hiddenboard
                    win = false;
                    break; // go back to move() and continue game
                }

            }
        }
        if (win) {
            System.out.println("You win!");
            winTimes++;
            System.out.println("You won " + winTimes + " times");
            System.out.println("You lost " + lostTimes + " times");
            playAgainQuestion(winTimes, lostTimes);
        }
    }

    public void playAgainQuestion(int winTimes, int lostTimes) {
        System.out.println("Would you like to play again? yes or no");
        String answer;
        String answerLowerCase;

        while (true) {
            answer = scanner.nextLine();
            answerLowerCase = answer.toLowerCase();
            if (answerLowerCase.equals("yes")) {
                Menu menu = new Menu();
                menu.menu(winTimes, lostTimes);
                break;
            } else if (answerLowerCase.equals("no")) {
                System.out.println("Thank you for coming!");
                System.exit(0);
            } else {
                System.out.println("Yes or no?");
            }
        }
    }
}



