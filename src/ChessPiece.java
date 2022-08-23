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
}