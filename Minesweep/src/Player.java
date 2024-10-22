import java.util.Scanner;

public class Player {
    String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /*
    Move doesn't work rn, because there's no cellList yet, but it should start working as soon as we have cells
    (if they have some way to be found by their place like a1, c5 etc)
    and a list of them.
     */
    /*
    public void move(Scanner scanner) {
        System.out.println("Which cell do you want to check?");
        System.out.println();
        String userCell;
        boolean validInput = false; // checks if input is some cell
        while (true) {
            userCell = scanner.nextLine(); // input from user, which cell he wants to check
            for (Cell cell : ArrayList <Cell> NameOfTheArray) { // for all cells in a list och cells
                if ( userCell.equals(placeOfTheCell)) { //check if users input looks like some of the cells
                    validInput = true;
                    checkBomb(userCell); //if so, start checkBomb
                    break; // and leave the loop

                }
                if (!validInput){ //if doesn't look like a cell name
                    System.out.println("Invalid input"); //loop igen
                }

            }
        }
    }


     */
}
