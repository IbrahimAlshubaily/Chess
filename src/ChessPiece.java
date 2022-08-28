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
                    if (newCell.isValid(team)) {
                        result.add(newCell);
                    }else break;
                }else break;
            }
        }
        return result;
    }
    public Team getTeam() {
        return this.team;
    }
    public String getRepr() {
        return this.team.getRepr() + this.repr.toUpperCase();
    }
}

class Queen extends ChessPiece{
    Queen(Team team){
        super(team, "Q", Direction.getDirections(), 8);
    }
}
class King extends ChessPiece{
    King(Team team){
        super(team, "K", Direction.getDirections(), 1);
    }
}
class Knight extends ChessPiece{
    Knight(Team team){
        super(team, "N", Direction.getKnightDirections(), 1);
    }
}
class Bishop extends ChessPiece{
    Bishop(Team team){
        super(team, "B", new Direction []{Direction.FORWARD_LEFT, Direction.FORWARD_RIGHT,
                Direction.BACKWARD_LEFT, Direction.BACKWARD_RIGHT}, 8);
    }
}
class Rook extends ChessPiece{
    Rook(Team team){
        super(team, "R", new Direction []{Direction.FORWARD, Direction.BACKWARD,
                Direction.LEFT, Direction.RIGHT}, 8);
    }
}

class Pawn extends ChessPiece{
    Pawn(Team team){
        super(team, "P", new Direction []{Direction.FORWARD}, 2);
    }
}
