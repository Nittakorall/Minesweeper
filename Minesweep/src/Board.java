public class Board {

    char [][] board;
    int row;
    int column;
    int mines;

    public Board(int row, int column, int mines) {
        this.board = new char[20][20];
        this.row = row;
        this.column = column;
        this.mines = mines;
    }

    public void printEasyBoard() {

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
    public void printMediumBoard() {

        for (char letter = 'A'; letter < 'L'; letter++) {
            System.out.print("     " + letter);
        }
        System.out.println();
        System.out.println("----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+");


        for (int r = 1; r < 12; r++) {
            System.out.print(r);
            for (int c = 0; c < 11; c++) {

                System.out.print("  |  " + board[r][c]);
                if (c == 10) {
                    System.out.println("  | ");
                }
            }
            System.out.println("----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+");
        }
    }
    public void printHardBoard() {

        for (char letter = 'A'; letter < 'O'; letter++) {
            System.out.print("     " + letter);
        }
        System.out.println();
        System.out.println("----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+");


        for (int r = 1; r < 15; r++) {
            System.out.print(r);
            for (int c = 0; c < 14; c++) {

                System.out.print("  |  " + board[r][c]);
                if (c == 13) {
                    System.out.println("  | ");
                }
            }
            System.out.println("----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+");
        }
    }

}
