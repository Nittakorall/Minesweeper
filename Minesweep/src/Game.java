import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {


    public Game() {
    }

    public void game(Board board, int winTimes, int lostTimes) {

      /*  Scanner scanner = new Scanner(System.in);
        System.out.println("Print your name: ");
        String userName = scanner.nextLine();
        Player player = new Player(userName);
        System.out.println("Nice to meet you, " + player.getName());

       */
        board.initializeBoard();
        board.addMines();

        board.printBoard(board.board);
        int flagsAvailable = board.mines;
        board.makeMove(winTimes, lostTimes, flagsAvailable);


    }


}