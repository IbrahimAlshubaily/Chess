import java.util.ArrayList;

class ChessBoardCell {
    private final int row;
    private final int col;
    private ChessPiece occupant;
    public ChessBoardCell(int row, int col){
        this.row = row;
        this.col = col;
    }
    public boolean isEmpty() {
        return occupant == null;
    }
    public boolean isOpponent(Team team) {
        return !isEmpty() && occupant.getTeam() != team;
    }
    int getRow(){ return row; }
    int getCol(){ return col; }
    ChessPiece getOccupant(){ return occupant; }
    public void setOccupant(ChessPiece piece) { occupant = piece; }

    public ArrayList<ChessBoardCell> getMoves(ChessBoard chessBoard) {
        if (this.isEmpty()) return new ArrayList<>();
        return occupant.getMoves(chessBoard, this);
    }
    public String getRepr() {
        String result = " | " + row + "  " + col;
        if (!isEmpty()){
            result = " |  " + occupant.getRepr() + " ";
        }
        return result;
    }
}