import java.io.IOException;

public class ChessBoard {
    private static final int BOARD_SIZE = 8;
    private final ChessBoardCell[][] board = new ChessBoardCell[BOARD_SIZE][BOARD_SIZE];
    private final Player playerOne;
    private final Player playerTwo;
    private boolean gameOver = false;

    public ChessBoard(){
        playerOne = new Player(Team.BLACK);
        playerTwo = new Player(Team.WHITE);
        initBoard();
        initPieces();
    }
    private void initBoard() {
        for (int row = 0; row < BOARD_SIZE; row++){
            for (int col = 0; col < BOARD_SIZE; col++){
                board[row][col] = new ChessBoardCell(row, col);
            }
        }
    }
    private void initPieces() {
        initFirstRow(Team.WHITE, 0);
        initPawns(Team.WHITE, 1);

        initPawns(Team.BLACK, 6);
        initFirstRow(Team.BLACK, 7);
    }
    private void initPawns(Team team, int row) {
        for (int col = 0; col < BOARD_SIZE; col++){
            addPiece(new Pawn(team), row, col);
        }
    }
    private void initFirstRow(Team team, int row) {
        ChessPiece[] orderedPieces = { new Rook(team), new Bishop(team), new Knight(team), new Queen(team),
                                        new King(team), new Knight(team), new Bishop(team), new Rook(team) };
        for (int col = 0; col < orderedPieces.length; col++){
            addPiece(orderedPieces[col], row, col);
        }
    }
    public boolean isInBounds(int row, int col) {
        return 0 <= row && row < BOARD_SIZE && 0 <= col && col < BOARD_SIZE;
    }
    public ChessBoardCell getCell(int row, int col) {
        if (!isInBounds(row, col)){
            throw new ArrayIndexOutOfBoundsException();
        }
        return board[row][col];
    }
    void addPiece(ChessPiece piece, int row, int col){
        if (isInBounds(row, col)){
            this.board[row][col].setOccupant(piece);
        }
    }
    public void movePiece(ChessBoardCell source, ChessBoardCell destination) {
        if (isValidMove(source, destination)){
            if (!destination.isEmpty() && destination.getOccupant().isKing()){
                System.out.println("Game Over: "+source.getOccupant().getTeam() + " Won!");
                this.gameOver = true;
            }
            destination.setOccupant(source.getOccupant());
            source.setOccupant(null);
        }
    }
    public boolean isValidMove(ChessBoardCell source, ChessBoardCell destination) {
        if (source.isEmpty()) return false;
        return destination.isEmpty() || destination.isOpponent(source.getOccupant().getTeam());
    }

    void rollOut() throws IOException {
        while(!gameOver){
            display();
            playerOne.play(this);
            display();
            if (!gameOver){
                playerTwo.play(this);
            }
        }
    }
    public void display(){
        System.out.println(this);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("_".repeat(59));
        sb.append("\n");
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                sb.append(board[i][j].getRepr());
            }
            sb.append(" |\n");
        }
        sb.append("_".repeat(59));
        sb.append("\n");
        return sb.toString();
    }
}