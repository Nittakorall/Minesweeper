import org.w3c.dom.ls.LSOutput;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Menu {
    /**
     * function that presents a menu.
     * First with options 1. Rules, 2. Start game.
     * Second with options for difficulty.
     * Error handling added.
     */
    public void menu(int winTimes, int lostTimes) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to do: \n" +
                "1. Read the rules.\n" +
                "2. Start the game!\n");
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
                    System.out.println("4. Choose your own size and mine amount\n");
                    int a;
                    while (true) { // loops until user choose 1, 2 or 3, crashes if input is not a number, need try catch
                        try {
                            a = scanner.nextInt();
                            if (a == 1 || a == 2 || a == 3 || a == 4) {
                                Game game = new Game();
                                switch (a) {
                                    case 1:
                                        Board easyBoard = new Board(2, 2, 1);
                                        game.game(easyBoard, winTimes, lostTimes);

                                        break;
                                    case 2:
                                        Board mediumBoard = new Board(12, 12, 10);
                                        game.game(mediumBoard, winTimes, lostTimes);
                                        break;
                                    case 3:
                                        Board hardBoard = new Board(15, 15, 12);
                                        game.game(hardBoard, winTimes, lostTimes);
                                        break;
                                    case 4:
                                        int inputChooseRows = -1;
                                        int inputChooseColumns = -1;
                                        int inputChooseMines = -1;

                                        while (true) {

                                            try {
                                                System.out.println("Minimum size is 2x2 and maximum is 50x25.\n");
                                                System.out.println("How many rows? Choose a number between 2-50!\n");

                                                inputChooseRows = scanner.nextInt();

                                                if (inputChooseRows > 50 || inputChooseRows < 2) {
                                                    System.out.println("Not a number between 2-50. Choose again.\n");
                                                    scanner.nextLine();
                                                    continue;
                                                }
                                                break;
                                            } catch (InputMismatchException e) {
                                                System.out.println("Invalid choice. Please choose again!\n");
                                                scanner.nextLine();
                                            }
                                        }
                                        while (true) {

                                            try {

                                                System.out.println("How many columns? Choose a number between 2-25!\n");
                                                inputChooseColumns = scanner.nextInt();
                                                if (inputChooseColumns < 2 || inputChooseColumns > 25) {
                                                    System.out.println("Not a number between 2-25. Choose again!\n");
                                                    scanner.nextLine();
                                                    continue;
                                                }
                                                break;
                                            } catch (InputMismatchException e) {
                                                System.out.println("Invalid choice. Please choose again!\n");
                                                scanner.nextLine();
                                            }
                                        }
                                        while (true) {

                                            int rowsTimesColumns = (inputChooseColumns * inputChooseRows) - 1;
                                            try {
                                                System.out.println("How many mines? Choose a number between 1-" + rowsTimesColumns + "!\n");
                                                inputChooseMines = scanner.nextInt();

                                                if (inputChooseMines < 1 || inputChooseMines > rowsTimesColumns) {
                                                    System.out.println("Not a number between 1-" + rowsTimesColumns + ". Choose again!\n");
                                                    scanner.nextLine();
                                                    continue;
                                                }
                                                System.out.println("You have chosen a " + inputChooseRows + "x" + inputChooseColumns + " with " + inputChooseMines + " mines!\n");
                                                Board userBoard = new Board(inputChooseRows, inputChooseColumns, inputChooseMines);
                                                game.game(userBoard, winTimes, lostTimes);
                                                break;

                                            } catch (InputMismatchException e) {
                                                System.out.println("Invalid choice. Please choose again!\n");
                                                scanner.nextLine();
                                            }
                                        }
                                        break;
                                }
                                break;
                            } else {
                                System.out.println("Invalid input. Choose again!\n " +
                                        "1. Easy\n" +
                                        "2. Medium\n" +
                                        "3. Hard\n" +
                                        "4. Choose your own size and mine amount");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Choose again!\n " +
                                    "1. Easy\n" +
                                    "2. Medium\n" +
                                    "3. Hard\n" +
                                    "4. Choose your own size and mine amount");
                            scanner.nextLine();
                        }
                    }
                } else if (rulesOrGame == 1) {
                    System.out.println("To win a game of Minesweeper, all non-mine cells must be opened without opening a mine.\n" +
                            "Put flags where you think there might be a mine.\n" +
                            "Difficulty can be increased by adding mines or starting with a larger grid.\n" +
                            "Good luck!\n");
                    menu(winTimes, lostTimes);
                    break;
                } else {
                    System.out.println("Invalid input. Choose:\n" +
                            " 1. Read rules!\n" +
                            " 2. Start the game!\n");

                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Choose 1 for rules or 2 for game.\n");
                scanner.nextLine();
            }
        }
    }
}