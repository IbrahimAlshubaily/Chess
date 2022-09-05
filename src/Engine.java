import java.io.IOException;

public class Engine {
    ChessBoard board = new ChessBoard();
    private final Player playerOne = new Player(Team.BLACK);
    private final Player playerTwo = new Player(Team.WHITE);
    void rollOut() throws IOException {
        while(!board.isGameOver()){
            board.display();
            playerOne.play(board);
            board.display();
            if (!board.isGameOver()){
                playerTwo.play(board);
            }
        }
    }
}
