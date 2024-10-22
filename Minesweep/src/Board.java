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


    public void addBombs() {

    }

}
