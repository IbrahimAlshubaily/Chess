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
        initPawns(Team.BLACK, BOARD_SIZE - 2);
        initFirstRow(Team.BLACK, BOARD_SIZE - 1);

        initPawns(Team.WHITE, 1);
        initFirstRow(Team.WHITE, 0);
    }
    private void initPawns(Team team, int row) {
        for (int col = 0; col < BOARD_SIZE; col++){
            addPiece(new Pawn(team), row, col);
        }
    }
    private void initFirstRow(Team team, int row) {
        addPiece(new Rook(team), row, 0);
        addPiece(new Bishop(team), row, 1);
        addPiece(new Knight(team), row, 2);
        addPiece(new Queen(team), row, 3);
        addPiece(new King(team), row, 4);
        addPiece(new Knight(team), row, 5);
        addPiece(new Bishop(team), row, 6);
        addPiece(new Rook(team), row, 7);
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
        if (!source.isEmpty() || !destination.isValid(source.getOccupant().getTeam())){
            if (!destination.isEmpty() && destination.getOccupant().isKing()){
                System.out.println("Game Over: "+source.getOccupant().getTeam() + " Won!");
                this.gameOver = true;
            }
            destination.setOccupant(source.getOccupant());
            source.setOccupant(null);
        }
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
    public void display() {
        System.out.println("_".repeat(59));
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j].getRepr());
            }
            System.out.println(" |");
        }
        System.out.println("_".repeat(59));
    }
}