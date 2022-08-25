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

    public ArrayList<BoardCell> getMoves(ChessBoard board, BoardCell currCell){
        ArrayList<BoardCell> result = new ArrayList<>();
        BoardCell newCell;
        for (Direction dir : this.directions){
            for (int i = 1; i <= this.nSteps; i++){
                newCell = new BoardCell(currCell.row + i*dir.getRowOffset(team)
                                        ,currCell.col + i*dir.getColOffset(team));
                if (board.isValid(newCell, team)){
                    result.add(newCell);
                }
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

class Pawn extends ChessPiece{
    Pawn(Team team){
        super(team, "P", new Direction []{Direction.FORWARD}, 2);
    }
}