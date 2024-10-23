import java.sql.SQLOutput;
import java.util.Scanner;

public class Game {

    int winn = 0;
    int lost = 0;

    public void game() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Print your name: ");
        String userName = scanner.nextLine();
        Player player = new Player(userName);
       System.out.println("Nice to meet you, " + player.getName());
        System.out.println("\n Please choose difficulty level: ");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        int a;
        while (true) { // loops until user choose 1, 2 or 3, crashes if input is not a number, need try catch
            a = scanner.nextInt();
            if (a == 1 || a == 2 || a == 3) {
                switch (a) {
                    case 1:
                        Board easyBoard = new Board(7, 7, 6);
                        easyBoard.initializeBoard();
                        easyBoard.printBoard();
                        easyBoard.addMines();
                        break;
                    case 2:
                        Board mediumBoard = new Board(12, 12, 10);
                        mediumBoard.initializeBoard();
                        mediumBoard.printBoard();
                        mediumBoard.addMines();
                        break;
                    case 3:
                        Board hardBoard = new Board(15, 15, 12);
                        hardBoard.initializeBoard();
                        hardBoard.printBoard();
                        hardBoard.addMines();
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
                break;
            } else {
                System.out.println("Invalid input, try again");
            }

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
