import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Player{
    Team team;
    Player(Team team){
        this.team = team;
    }
    void play(ChessBoard board) throws IOException {
        System.out.println(this.team + " turn");
        System.out.println("Enter: row,col");
        ChessBoardCell source = selectCell(board);
        if (!source.isEmpty() && source.getOccupant().getTeam() == this.team) {

            System.out.println("Selected "+ source.getRepr());
            System.out.println("Select a destination among the following options");
            for (ChessBoardCell cell : source.getMoves(board)) {
                System.out.println(cell.getRepr());
            }
            ChessBoardCell destination = selectCell(board);
            board.movePiece(source, destination);
        } else{
            System.out.println("Invlid Entry");
            this.play(board);
        }
    }
    private ChessBoardCell selectCell(ChessBoard board) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String [] rowCol = reader.readLine().split(",");
        int row = Integer.parseInt(rowCol[0]);
        int col = Integer.parseInt(rowCol[1]);
        if (!board.isInBounds(row, col)){
            System.out.println("Out of bounds, row and col must be more than 0 and less than 8. Try again:");
            return this.selectCell(board);
        }
        return board.getCell(row, col);
    }
}