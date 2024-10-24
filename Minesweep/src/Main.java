import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to MineSweeper!");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Print your name: ");
        String userName = scanner.nextLine();
        Player player = new Player(userName);
        System.out.println("Nice to meet you, " + player.getName());
        int winTimes = 0;
        int lostTimes = 0;
        Menu menu = new Menu();
        menu.menu(winTimes, lostTimes);
    }
}