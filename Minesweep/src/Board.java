public class Board {
    /**
     * 2d arrays for visible board and hidden board (contains mines later).
     */
    char [][] board;
    char [][] hiddenBoard;
    int row;
    int column;
    int mines;

    public Board(int row, int column, int mines) {
        this.board = new char[row][column];
        this.row = row;
        this.column = column;
        this.mines = mines;
        this.hiddenBoard = new char[row][column];
    }

    Cell cell = new Cell();


    /**
     * Fills board and hiddenBoard with empty places.
     */
    public void initializeBoard() {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                board[r][c] = ' ';
                hiddenBoard[r][c]= ' ';
            }
        }
    }

    public Board() { //created to try to fix main manu  problem. Can be removed later if not needed
    }

    /**
     * Prints visible board in terminal for user.
     */

    public void printBoard() {

        System.out.print("    ");
        for (int c = 0; c < column; c++) {
            char letter = (char)('A' + c);
            System.out.printf("  %2s  " , letter);
        }
        System.out.println();

        System.out.print("----");
        for (int c = 0; c < column; c++) {
            System.out.print("+-----");
        }
        System.out.println();

        for (int r = 0; r <= row - 1; r++) {
            System.out.printf("%2d", r + 1);
            for (int c = 0; c < column; c++) {

                    System.out.print("  |  " + board[r][c]);
                if (c == column - 1) {
                    System.out.println("  | ");
                }
            }
            System.out.print("----");
            for (int c = 0; c < column; c++) {
                System.out.print("+-----");
            }
            System.out.println();
        }
    }

    /**
     * adds mines to hiddenBoard, randomly according to board size.
     */
   public void addMines() {
        int minesCount = 0;


        while ( minesCount < mines) {

            int x = (int) (Math.random() * hiddenBoard.length);
            int y = (int) (Math.random() * hiddenBoard[0].length);

            if (hiddenBoard[x][y] == ' ') {
            hiddenBoard[x][y] = 'X';
            minesCount++;
            }

        }
     
       /* for (int r = 1; r < 7; r++) {
            for ( r = 1; r < row; r++) {
                System.out.print(r);
                for (int c = 0; c < 6; c++) {
                    for ( c = 0; c < column - 1; c++) {

                        System.out.print("  |  " + board[r][c]);
                        if (c == 5) {
                            System.out.println("  | ");
                        }
                    }
                    System.out.println("---+-----+-----+-----+-----+-----+-----+");
                }
            }

          }
*/
    }
}
