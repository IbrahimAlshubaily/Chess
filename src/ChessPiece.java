import java.util.ArrayList;

public class ChessPiece {
    private Direction[] directions;
    private int nSteps;
    private String team;
    private String repr;
    public ChessPiece(String team, String repr, String[] directions, int nSteps) {
        this.team = team;
        this.repr = repr;
        this.nSteps = nSteps;
        this.directions = this.getDirections(directions);
    }

    private Direction[] getDirections(String[] directions) {
        Direction[] result = new Direction[directions.length];
        for(int i = 0; i < directions.length; ++i) {
            result[i] = new Direction(directions[i], this.team);
        }
        return result;
    }

    public ArrayList<BoardCell> getMoves(ChessBoard board, BoardCell currCell){
        ArrayList<BoardCell> result = new ArrayList<>();
        BoardCell newCell = new BoardCell(-1,-1);
        for (Direction dir : this.directions){
            for (int i = 1; i <= this.nSteps; i++){
                newCell.row = currCell.row + (i*dir.rowOffset);
                newCell.col = currCell.col + (i*dir.colOffset);
                if (board.isValid(newCell, this.team)){
                    result.add(newCell);
                }
            }
        }
        return result;
    }

    public String getTeam() {
        return this.team;
    }
}