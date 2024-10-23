import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public void menu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please press 1 for rules or press 2 to start the game");
        int rulesOrGame;

        while (true) {//checks is input is either 1 or 2, crashes if input is anything but number, need try catch
            try {
                rulesOrGame = scanner.nextInt();
                if (rulesOrGame == 2) {
                    scanner.nextLine();
                    System.out.println("Please choose difficulty level: ");
                    System.out.println("1. Easy");
                    System.out.println("2. Medium");
                    System.out.println("3. Hard");
                    int a;
                    while (true) { // loops until user choose 1, 2 or 3, crashes if input is not a number, need try catch
                        try {
                            a = scanner.nextInt();
                            if (a == 1 || a == 2 || a == 3) {
                                Game game = new Game();
                                switch (a) {
                                    case 1:
                                        Board easyBoard = new Board(7, 7, 6);
                                        game.game(easyBoard);

                                        break;
                                    case 2:
                                        Board mediumBoard = new Board(12, 12, 10);
                                        game.game(mediumBoard);
                                        break;
                                    case 3:
                                        Board hardBoard = new Board(15, 15, 12);
                                        game.game(hardBoard);
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
                    System.out.println("To win a game of Minesweeper, all non-mine cells must be opened without opening a mine.\n" +
                            " Difficulty can be increased by adding mines or starting with a larger grid.\n\n");
                    menu();
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

}