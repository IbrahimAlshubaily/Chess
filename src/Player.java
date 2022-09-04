import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Player{
    Team team;
    Player(Team team){
        this.team = team;
    }
    void play(ChessBoard board) throws IOException {
        System.out.println(this.team + " turn");
        System.out.println("Enter: row col");
        ChessBoardCell source = selectCell(board);
        if (!source.isEmpty() && source.getOccupant().getTeam() == this.team) {
            System.out.println(getSelectionPrettyPrint(source));
            ArrayList<ChessBoardCell> validMoves = source.getMoves(board);
            if (validMoves.size() == 0){
                System.out.println("No valid moves, try again.");
                play(board);
                return;
            }
            System.out.println("Select a destination among the following options");
            for (ChessBoardCell cell : validMoves) {
                System.out.println(getSelectionPrettyPrint(cell));
            }
            ChessBoardCell destination = selectCell(board);
            board.movePiece(source, destination);
        } else{
            System.out.println("Invalid Entry");
            this.play(board);
        }
    }
    private ChessBoardCell selectCell(ChessBoard board) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String [] rowCol = reader.readLine().split(" ");
        int row = Integer.parseInt(rowCol[0]);
        int col = Integer.parseInt(rowCol[1]);
        if (!board.isInBounds(row, col)){
            System.out.println("Out of bounds, row and col must be more than 0 and less than 8. Try again:");
            return this.selectCell(board);
        }
        return board.getCell(row, col);
    }
    private String getSelectionPrettyPrint(ChessBoardCell cell){
        return cell.getRepr() + " @ "+ cell.getRow() + " "+ cell.getCol();
    }
}