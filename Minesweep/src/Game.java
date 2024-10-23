import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

    int winn = 0;
    int lost = 0;

    public void game() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to MineSweeper!");
        System.out.println("Press 1 for rules \n Press 2 to start the game");
        int rulesOrGame;


        while (true) {//checks is input is either 1 or 2, crashes if input is anythinig but number, need try catch
            try {
                rulesOrGame = scanner.nextInt();
                if (rulesOrGame == 2) {
                    scanner.nextLine();
                    System.out.println("Print your name: ");
                    String userName = scanner.nextLine();
                    Player player = new Player(userName);
                    System.out.println(player.getName());
                    System.out.println("Please choose difficulty level: ");
                    System.out.println("1. Easy");
                    System.out.println("2. Medium");
                    System.out.println("3. Hard");
                    int a;
                    while (true) { // loops until user choose 1, 2 or 3, crashes if input is not a number, need try catch
                        try {
                            a = scanner.nextInt();
                            if (a == 1 || a == 2 || a == 3) {
                                switch (a) {
                                    case 1:
                                        Board easyBoard = new Board(7, 7, 6);
                                        easyBoard.printBoard();
                                        break;
                                    case 2:
                                        Board mediumBoard = new Board(12, 12, 10);
                                        mediumBoard.printBoard();
                                        break;
                                    case 3:
                                        Board hardBoard = new Board(15, 15, 12);
                                        hardBoard.printBoard();
                                        break;
                                }
                                break;
                            } else {
                                System.out.println("Invalid input, try again");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid choice");
                            scanner.nextLine();
                        }
                    }
                } else if (rulesOrGame == 1) {
                    System.out.println("rules");
                    break;
                } else {
                    System.out.println("Invalid input");

                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                scanner.nextLine();
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
