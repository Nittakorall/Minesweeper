import java.sql.SQLOutput;
import java.util.Scanner;

public class Game {

    int winn = 0;
    int lost = 0;

    public void game() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to MineSweeper!");
        System.out.println("Press 1 for rules \n Press 2 to start the game");
        int rulesOrGame = scanner.nextInt();
        scanner.nextLine();
        if (rulesOrGame == 2) {
            System.out.println("Print your name: ");
            String userName = scanner.nextLine();
            Player player = new Player(userName);
            System.out.println(player.getName());
            System.out.println("Please choose difficulty level: ");
            System.out.println("1. Easy");
            System.out.println("2. Medium");
            System.out.println("3. Hard");
            int a = scanner.nextInt();
            switch (a) {
                case 1:
                    Board board = new Board(7, 7, 6);
                    board.printBoard();
                    break;
                case 2:
//                Board board = new Board(7, 7, 6);
//                board.printBoard();
//                break;
                case 3:
                    // Board board = new Board(7, 7, 6);
//                board.printBoard();
//                break;
                default:
                    System.out.println("Invalid choice");
            }
        }else if (rulesOrGame == 1) {
            System.out.println("rules");
        }
    }

    public Game() {
    }

    public void totalScore() {
        // if(user winns) winn ++
        winn = getWinn() + 1;
        // else if (user lost) lost ++
    }


    public int getWinn() {
        return winn;
    }

    public int getLost() {
        return lost;
    }
}
