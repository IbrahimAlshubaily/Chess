public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ChessBoard board = new ChessBoard();
        board.display();
        for (BoardCell cell : board.getMoves(0, 0)){
            System.out.println(cell.row + "  "+ cell.col);
        }
    }
}