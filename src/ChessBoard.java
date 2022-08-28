import java.util.ArrayList;


public class ChessBoard {
    private static final int BOARD_SIZE = 8;
    private final ChessBoardCell[][] board = new ChessBoardCell[BOARD_SIZE][BOARD_SIZE];
    public ChessBoard(){
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
        for (int col = 1; col < BOARD_SIZE; col++){
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
    void addPiece(ChessPiece piece, int row, int col){
        this.board[row][col].setOccupant(piece);
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
    public ArrayList<ChessBoardCell> getMoves(int row, int col){
        return getCell(row, col).getMoves(this);
    }
    public void display() {
        System.out.println("_".repeat(59));
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j].getRepr());
            }
            System.out.println(" |");
            //System.out.println("-".repeat(25));
        }
        System.out.println("_".repeat(59));
    }
}

