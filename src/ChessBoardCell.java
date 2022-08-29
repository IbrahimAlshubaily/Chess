import java.util.ArrayList;

class ChessBoardCell {
    final int row;
    final int col;
    private ChessPiece occupant;
    public ChessBoardCell(int row, int col){
        this.row = row;
        this.col = col;
    }
    public boolean isEmpty() {
        return this.occupant == null;
    }
    public boolean isOpponent(Team team) {
        return !this.isEmpty() && this.occupant.getTeam() != team;
    }
    public boolean isValid(Team team) {
        return this.isEmpty() || this.isOpponent(team);
    }
    public String getRepr() {
        String result = " | ";
        if (isEmpty()){
            result += this.row + "  " + this.col;
        } else {
            result += " " + this.occupant.getRepr() + " ";
        }
        return result;
    }

    public void setOccupant(ChessPiece piece) {
        this.occupant = piece;
    }
    ChessPiece getOccupant(){
        return this.occupant;
    }

    public ArrayList<ChessBoardCell> getMoves(ChessBoard chessBoard) {
        ArrayList<ChessBoardCell> result = new ArrayList<>();
        if (!this.isEmpty()){
            result = this.occupant.getMoves(chessBoard, this);
        }
        return result;
    }
}