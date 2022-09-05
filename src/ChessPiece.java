import java.util.ArrayList;

public class ChessPiece {
    private final Team team;
    private final Direction[] directions;
    private final int nSteps;
    private final String repr;
    public ChessPiece(Team team, String repr, Direction[] directions, int nSteps) {
        this.team = team;
        this.repr = repr;
        this.nSteps = nSteps;
        this.directions = directions;
    }
    public ArrayList<ChessBoardCell> getMoves(ChessBoard board, ChessBoardCell currCell){
        ArrayList<ChessBoardCell> result = new ArrayList<>();
        ChessBoardCell newCell;
        int row, col;
        for (Direction dir : this.directions){
            for (int i = 1; i <= this.nSteps; i++){
                row = currCell.getRow() + i*dir.getRowOffset(team);
                col = currCell.getCol() + i*dir.getColOffset(team);
                if (board.isInBounds(row, col)){
                    newCell = board.getCell(row, col);
                    if (board.isValidMove(currCell, newCell)) {
                        result.add(newCell);
                        if (!newCell.isEmpty()) break;
                    }else break;
                }else break;
            }
        }
        return result;
    }
    public Team getTeam() { return team; }
    public String getRepr() { return team.getRepr() + repr.toUpperCase(); }
    public boolean isKing() { return repr.equalsIgnoreCase("K"); }
}

class Queen extends ChessPiece{
    Queen(Team team){ super(team, "Q", Direction.getDirections(), 8); }
}
class King extends ChessPiece{
    King(Team team){ super(team, "K", Direction.getDirections(), 1); }
}
class Knight extends ChessPiece{
    Knight(Team team){ super(team, "N", Direction.getKnightDirections(), 1); }
}
class Bishop extends ChessPiece{

    Bishop(Team team){ super(team, "B", new Direction []{Direction.FORWARD_LEFT, Direction.FORWARD_RIGHT,
                                                        Direction.BACKWARD_LEFT, Direction.BACKWARD_RIGHT}, 8); }

}
class Rook extends ChessPiece{
    Rook(Team team){ super(team, "R", new Direction []{Direction.FORWARD, Direction.BACKWARD,
                                                            Direction.LEFT, Direction.RIGHT}, 8); }
}
class Pawn extends ChessPiece{
    Pawn(Team team){ super(team, "P", new Direction []{Direction.FORWARD}, 2); }
    public ArrayList<ChessBoardCell> getMoves(ChessBoard board, ChessBoardCell currCell){
        ArrayList<ChessBoardCell> result = new ArrayList<>();
        int rowOffset = getTeam() == Team.WHITE? 1:-1;
        int row,col;
        for (int i = 1; i < 3; i++){
            row = currCell.getRow() + i*rowOffset;
            col = currCell.getCol();
            if (isValidForwardMove(board, row, col)) {
                result.add(board.getCell(row, col));
            }else break;
        }
        for (int colOffset : new int[]{1, -1}) {
            row = currCell.getRow() + rowOffset;
            col = currCell.getCol() + colOffset;
            if (isValidDiagonalMove(board, row, col)){
                result.add(board.getCell(row, col));
            }
        }
        return result;
    }
    private boolean isValidForwardMove(ChessBoard board, int row, int col){
        return board.isInBounds(row, col) && board.getCell(row, col).isEmpty();
    }
    private boolean isValidDiagonalMove(ChessBoard board, int row, int col){
        return board.isInBounds(row, col) && board.getCell(row,col).isOpponent(getTeam());
    }

}
