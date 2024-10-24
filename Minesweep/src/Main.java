//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to MineSweeper!");
        int winTimes=0;
        int lostTimes=0;
        Menu menu = new Menu();
        menu.menu(winTimes, lostTimes);
    }
}