import java.sql.SQLOutput;

public class Board {

    Cell [][] board;
    int row;
    int column;
    int mines;

    public Board(int row, int column, int mines) {
        this.board = new Cell[20][20];
        this.row = row;
        this.column = column;
        this.mines = mines;
    }


    public void initializeBoard() {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column - 1; c++) {
                board[r][c] = new Cell();
            }
        }
    }


    public void printBoard() {

        for (char letter = 'A'; letter < 'G'; letter++) {
            System.out.print("     " + letter);
        }
        System.out.println();
        System.out.println("---+-----+-----+-----+-----+-----+-----+");


        for (int r = 1; r < row; r++) {
            System.out.print(r);
            for (int c = 0; c < column - 1; c++) {

                    System.out.print("  |  " + board[r][c]);
                if (c == 5) {
                    System.out.println("  | ");
                }
            }
            System.out.println("---+-----+-----+-----+-----+-----+-----+");
        }
    }

    public void addBombs() {

    }

}
