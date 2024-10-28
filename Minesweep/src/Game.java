import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

    /**
     * Starts game and allows degree of difficulty
     */
    public Game() {
    }

    /**
     * Prints board, adds mines, sets flagsAvailible possible, prints wins and lost times.
     * @param board
     * @param winTimes
     * @param lostTimes
     */
    public void game(Board board, int winTimes, int lostTimes) {

        board.initializeBoard();
        board.addMines();

        board.printBoard(board.board);
        int flagsAvailable = board.mines;
        board.makeMove(winTimes, lostTimes, flagsAvailable);


    }

}