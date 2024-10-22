public class Board {

    char [][] board;
    int row;
    int column;
    int mines;

    public Board(int row, int column, int mines) {
        this.board = new char[7][7];
        this.row = row;
        this.column = column;
        this.mines = mines;
    }

    public void printBoard() {

        for (char letter = 'A'; letter < 'G'; letter++) {
            System.out.print("     " + letter);
        }
        System.out.println();
        System.out.println("---+-----+-----+-----+-----+-----+-----+");


        for (int r = 1; r < 7; r++) {
            System.out.print(r);
            for (int c = 0; c < 6; c++) {

                    System.out.print("  |  " + board[r][c]);
                if (c == 5) {
                    System.out.println("  | ");
                }
            }
            System.out.println("---+-----+-----+-----+-----+-----+-----+");
        }
    }

}
