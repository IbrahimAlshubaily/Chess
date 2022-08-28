import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Player{
    Team team;
    Player(Team team){
        this.team = team;
    }
    void play(ChessBoard board) throws IOException {
        board.display();
        ChessBoardCell source = selectCell(board);
        if (!source.isEmpty() && source.getOccupant().getTeam() == this.team) {
            for (ChessBoardCell cell : source.getMoves(board)) {
                System.out.println(cell.getRepr());
            }
            ChessBoardCell destination = selectCell(board);
            board.movePiece(source, destination);
            board.display();
        }
    }
    private ChessBoardCell selectCell(ChessBoard board) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String [] rowCol = reader.readLine().split(",");
        int row = Integer.parseInt(rowCol[0]);
        int col = Integer.parseInt(rowCol[1]);
        if (!board.isInBounds(row, col)){
            throw new IOException("Invalid input: out of bounds");
        }
        return board.getCell(row, col);
    }
}