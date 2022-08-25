public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ChessBoard board = new ChessBoard();
        board.display();
        for (BoardCell cell : board.getMoves(1, 1)){
            System.out.println(cell.row + " "+ cell.col);
        }
    }
}