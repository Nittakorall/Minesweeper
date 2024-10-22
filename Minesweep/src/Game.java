import java.sql.SQLOutput;
import java.util.Scanner;

public class Game {

    int winn = 0;
    int lost = 0;

    public void game(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to MineSweeper!");

        System.out.println("Print your name: ");
        String userName = scanner.nextLine();
        Player player = new Player(userName);
        System.out.println(player.getName());

    }

    public Game() {
    }

    public void totalScore(){
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
