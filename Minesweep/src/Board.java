public class Board {

    char [][] board;
    int row;
    int column;
    int mines;

    public Board(int row, int column, int mines) {
        this.board = new char[row][column];
        this.row = row;
        this.column = column;
        this.mines = mines;
    }

    Cell cell = new Cell();

    public void initializeBoard() {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                board[r][c] = ' ';
            }
        }
    }


    public void printBoard() {

        for (char letter = 'A'; letter < 'H'; letter++) {
            System.out.print("     " + letter);
        }
        System.out.println();
        System.out.println("---+-----+-----+-----+-----+-----+-----+-----+");


        for (int r = 0; r <= row - 1; r++) {
            System.out.print(r + 1);
            for (int c = 0; c < column; c++) {

                    System.out.print("  |  " + board[r][c]);
                if (c == 6) {
                    System.out.println("  | ");
                }
            }
            System.out.println("---+-----+-----+-----+-----+-----+-----+-----+");
        }
    }

   public void addMines() {
        int minesCount = 0;

        while (mines > minesCount) {

            int x = (int) (Math.random() * row);
            int y = (int) (Math.random() * column);

            if (board[x][y] == ' ') {
            board[x][y] = 'X';
            minesCount++;
            } else {
                continue;
            }

        }
    }
}
