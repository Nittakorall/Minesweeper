import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import java.util.concurrent.TimeUnit;


public class Board {
    Scanner scanner = new Scanner(System.in);
    StringBuilder stringBuilder = new StringBuilder(); //when creating a board, add all column letters to a new string

    // 2d arrays for visible board and hidden board (contains mines later).

    char[][] board;
    char[][] hiddenBoard;
    int row;
    int column;
    int mines;
    int flagsAvailable;

    // Timer
    private boolean gameTimer;
    private long displayMinutes = 0;
    private long startTime;

    public Board(int row, int column, int mines) {
        this.board = new char[row][column];
        this.row = row;
        this.column = column;
        this.mines = mines;
        this.hiddenBoard = new char[row][column];
    }

    // Timer-method
    public void startTimer() {
        gameTimer = true;
        displayMinutes = 0;
        startTime = System.currentTimeMillis();

        System.out.println("Timer: ");

        new Thread(() -> {
            try {
                while (gameTimer) {
                    TimeUnit.SECONDS.sleep(1);
                    long timePassed = System.currentTimeMillis() - startTime;
                    long secondsPassed = (timePassed / 1000) % 60;

                    if (secondsPassed == 0 && timePassed > 0) {
                        displayMinutes++;
                    }
                    System.out.print("\rTimer: " + displayMinutes + ":" + secondsPassed);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void stopTimer() {
        gameTimer = false;
        long timePassed = System.currentTimeMillis() - startTime;
        long secondsPassed = (timePassed / 1000) % 60;
        System.out.println("\nFinal time: " + displayMinutes + ":" + secondsPassed + " minutes::seconds");
    }

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

    /**
     * Prints visible board in terminal for user.
     */
    public void printBoard(char[][] chooseBoard) {
        String blueColor = "\033[34m";
        String magentaColor = "\u001B[35m";
        String yellowColor = "\033[33m";// ANSI escape code for green text
        String greenColor = "\033[32m";
        // ANSI escape code for green background
        // ANSI escape code for red text
        String redColor = "\033[31m";
        // ANSI escape code for green background
        String greenBackground = "\033[42m";
        // ANSI escape code för röd bakgrund
        String redBackground = "\033[41m";
        String orangeColor = "\u001B[38;5;214m";
        // ANSI escape code to reset color
        String resetColor = "\033[0m";

        System.out.print("    ");
        for (int c = 0; c < column; c++) {
            char letter = (char) ('A' + c);
            stringBuilder.append(letter); // works weird because it adds letters several times, but works
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
                    // prints background color red if symbol is 'X'
                    System.out.print("  |  " + redBackground + redColor + currentChar + resetColor);


                } else if (currentChar == '0') {
                    System.out.print("  |  " + blueColor + currentChar + resetColor);
                } else if (currentChar == '1') {
                    System.out.print("  |  " + greenColor + currentChar + resetColor);
                } else if (currentChar == '2') {
                    System.out.print("  |  " + yellowColor + currentChar + resetColor);
                } else if (currentChar == '3') {
                    System.out.print("  |  " + redColor + currentChar + resetColor);
                } else if (currentChar == '4' || currentChar == '5' || currentChar == '6' || currentChar == '7' || currentChar == '8') {
                    System.out.print("  |  " + magentaColor + currentChar + resetColor);

                } else if (currentChar == 'ꚰ') {
                    System.out.print("  |  " + orangeColor + currentChar + resetColor);
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

    public void makeMove(int winTimes, int lostTimes, int flagsAvailable) {
      //  startTimer();

        checkWin(winTimes, lostTimes);
        printBoard(hiddenBoard); // better to remove later
        // System.out.println(flagsAvailable);
        while (true) {


            System.out.println("\nWhat do you want to do?");
            if (flagsAvailable > 0) {
                System.out.println("1. Open cell.\n" +
                        "2. Add flag (" + flagsAvailable + " left)\n");
            } else if (flagsAvailable == 0) {
                System.out.println("1. Open cell.\n" +
                        "2. Add flag.\nBy the way, you've placed as many flags as there are mines.\n");
            } else if (flagsAvailable < 0) {
                System.out.println("1. Open cell.\n" +
                        "2. Add flag.\nThere aren't that many mines, actually, you've already placed " + (flagsAvailable - (flagsAvailable * 2)) + " too many");
            }

            String openOrFlag;
            while (true) {
                openOrFlag = scanner.nextLine();
                if (openOrFlag.equals("1") || openOrFlag.equals("2")) {
                    break;
                } else {
                    System.out.println("Please pick 1 or 2");
                }
            }

            System.out.println("Choose row: ");
            String inputRow;
            int inputRowNumber;

            while (true) {

                inputRow = scanner.nextLine(); //made string to avoid exception to nextInt
                try {
                    inputRowNumber = Integer.parseInt(inputRow); // trying making string to int
                    if (inputRowNumber <= row && inputRowNumber > 0) {

                        System.out.println("Choose column: ");
                        break;
                    } else {
                        System.out.println("There's no row " + inputRowNumber + ", try again");

                    }
                } catch (Exception e) {
                    System.out.println("That doesn't look like a row number, try again");

                }
            }
            String inputColumn;
            String inputColumnUpperCase;
            int rowOfACell;
            int columnOfACell;

            int columnIndex;
            while (true) {

                inputColumn = scanner.nextLine();
                inputColumnUpperCase = inputColumn.toUpperCase();
                if (inputColumnUpperCase.length() == 1) { //checks if input has more than 1 letter to make a char of it
                    char columnLetter = inputColumnUpperCase.charAt(0);
                    if (stringBuilder.indexOf(String.valueOf(columnLetter)) != -1) {
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

            if (openOrFlag.equals("1")) {

                if (hiddenBoard[inputRowNumber - 1][columnIndex] == 'X' && board[inputRowNumber - 1][columnIndex] != 'ꚰ') { // checks if there are a bomb in choosen space
                    System.out.println("Boom. There was a mine on " + inputColumnUpperCase + inputRowNumber);
                    board[inputRowNumber - 1][columnIndex] = 'X';

                    //printBoard(hiddenBoard); mine shows in real board too
                    printBoard(board);
                    lostTimes++;
                    System.out.println("You won " + winTimes + " times"); // prints if you win or lose
                    System.out.println("You lost " + lostTimes + " times");
                    playAgainQuestion(winTimes, lostTimes);


                } else if (board[inputRowNumber - 1][columnIndex] == 'ꚰ') {
                    System.out.println("Do you want to remove flag? yes or no: ");
                    String yesOrNo = scanner.nextLine();


                    if (yesOrNo.equalsIgnoreCase("yes")) {  // if yes flag removes
                        board[inputRowNumber - 1][columnIndex] = ' ';
                        flagsAvailable++;
                        printBoard(board);
                        makeMove(winTimes, lostTimes, flagsAvailable);
                    } else {
                        printBoard(board);
                        makeMove(winTimes, lostTimes, flagsAvailable);
                    }

                } else if (board[inputRowNumber - 1][columnIndex] != ' ') { //if a cell user picks isn't ' ' and has some other symbol
                    System.out.println("You've already opened this cell, please pick another one");
                    printBoard(board);
                    makeMove(winTimes, lostTimes, flagsAvailable);


                } else {
                    rowOfACell = inputRowNumber - 1;//need for minesAround
                    columnOfACell = columnIndex; //need for minesAround
                    board[inputRowNumber - 1][columnIndex] = minesAround(board, rowOfACell, columnOfACell);
                    System.out.println("There was no bomb on " + inputColumnUpperCase + inputRowNumber + ". You can make next move:");
                    printBoard(board);
                    makeMove(winTimes, lostTimes, flagsAvailable);
                }


            } else if (openOrFlag.equals("2")) {  // checks if opened, if not adds flag

                {
                    if (board[inputRowNumber - 1][columnIndex] == ' ' ) {
                        board[inputRowNumber - 1][columnIndex] = 'ꚰ';
                        flagsAvailable--;
                       // System.out.println(flagsAvailable);
                        printBoard(board);
                        makeMove(winTimes, lostTimes, flagsAvailable);
                    } else if (board[inputRowNumber - 1][columnIndex] == 'ꚰ') {
                        System.out.println("There's already a flag there");
                        printBoard(board);
                        makeMove(winTimes, lostTimes, flagsAvailable);
                    } else if (board[inputRowNumber - 1][columnIndex] != ' ' && board[inputRowNumber - 1][columnIndex] != 'ꚰ') {//if a cell user picks isn't ' ' or a flag and has some other symbol
                        System.out.println("You've already opened this cell, please pick another one");
                        makeMove(winTimes, lostTimes, flagsAvailable);

                    }


                }
            }
            //printBoard(hiddenBoard); mine shows in real board too


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

    /**
     * Asks if you want to play again or not
     */
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


    public char minesAround(char[][] board, int rowOfACell, int columnOfACell) {
        ArrayList<Character> allCellsAround = new ArrayList<>();
        ArrayList<Character> listWithMinesAround = new ArrayList<>();
        int lastColumnIndex = board[0].length - 1;

        if (rowOfACell - 1 >= 0) {
            allCellsAround.add(hiddenBoard[rowOfACell - 1][columnOfACell]);
        }
        if (columnOfACell - 1 >= 0) {
            allCellsAround.add(hiddenBoard[rowOfACell][columnOfACell - 1]);
        }
        if (rowOfACell + 1 < board.length) {
            allCellsAround.add(hiddenBoard[rowOfACell + 1][columnOfACell]);
        }
        if (columnOfACell + 1 <= lastColumnIndex) {
            allCellsAround.add(hiddenBoard[rowOfACell][columnOfACell + 1]);
        }
        if (rowOfACell - 1 >= 0 && columnOfACell - 1 >= 0) {
            allCellsAround.add(hiddenBoard[rowOfACell - 1][columnOfACell - 1]);
        }
        if (rowOfACell + 1 < board.length && columnOfACell - 1 >= 0) {
            allCellsAround.add(hiddenBoard[rowOfACell + 1][columnOfACell - 1]);
        }
        if (rowOfACell - 1 >= 0 && columnOfACell + 1 <= lastColumnIndex) {
            allCellsAround.add(hiddenBoard[rowOfACell - 1][columnOfACell + 1]);
        }
        if (rowOfACell + 1 < board.length && columnOfACell + 1 <= lastColumnIndex) {
            allCellsAround.add(hiddenBoard[rowOfACell + 1][columnOfACell + 1]);
        }


        for (Character character : allCellsAround) {
            if (character == 'X') {
                listWithMinesAround.add(character);
            }
        }
        int amountMinesAround = listWithMinesAround.size();
        char amountMinesAroundChar = (char) ('0' + amountMinesAround);
        return amountMinesAroundChar;
    }
}



