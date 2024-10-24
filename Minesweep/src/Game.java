import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

    int win = 0;
    int lost = 0;


    public Game() {

    }

    public void game(Board board, int winTimes, int lostTimes) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Print your name: ");
            String userName = scanner.nextLine();
            Player player = new Player(userName);
            System.out.println("Nice to meet you, " + player.getName());
            board.initializeBoard();
            board.addMines();
            board.printBoard(board.board);
            board.makeMove(winTimes, lostTimes);


    }


    public void totalScore() {
        // if(user wins) win ++
        win = getWin() + 1;
        // else if (user lost) lost ++
    }


    public int getWin() {
        return win;
    }

    public int getLost() {
        return lost;
    }
}