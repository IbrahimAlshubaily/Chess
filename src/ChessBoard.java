class BoardCell {
    int row;
    int col;
    ChessPiece occupant;//coupling alert
    public BoardCell(int row, int col){
        this.row = row;
        this.col = col;
    }
    public boolean isInBounds(int boardSize) {
        return this.row < boardSize && this.col < boardSize;
    }
    public boolean isOpponent(String team) {
        if (!this.isEmpty()){
            return !this.occupant.getTeam().equalsIgnoreCase(team);
        }
        return false;
    }
    public boolean isEmpty() {
        return this.occupant == null;
    }
}
public class ChessBoard {
    private static final int BOARD_SIZE = 8;
    private BoardCell [][] board = new BoardCell[BOARD_SIZE][BOARD_SIZE];
    public ChessBoard(){
        initBoard();
    }

    private void initBoard() {
    }

    public boolean isValid(BoardCell newCell, String team) {
        return newCell.isInBounds(BOARD_SIZE) && (newCell.isEmpty() || newCell.isOpponent(team));
    }

}

