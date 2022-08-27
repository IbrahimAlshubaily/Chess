import java.util.ArrayList;

class BoardCell {
    int row;
    int col;
    ChessPiece occupant;
    public BoardCell(int row, int col){
        this.row = row;
        this.col = col;
    }
    public boolean isEmpty() {
        return this.occupant == null;
    }
    public boolean isOpponent(Team team) {
        return !this.isEmpty() && this.occupant.getTeam() != team;
    }
    public boolean isValid(Team team) {
        return this.isEmpty() || this.isOpponent(team);
    }
    public String getRepr() {
        String result = " | ";
        if (isEmpty()){
            result += this.row + "  " + this.col;
        }else{
            result += " " + this.occupant.getRepr() + " ";
        }
        return result;
    }
}
public class ChessBoard {
    private static final int BOARD_SIZE = 8;
    private final BoardCell [][] board = new BoardCell[BOARD_SIZE][BOARD_SIZE];
    public ChessBoard(){
        initBoard();
        initPieces();
    }
    private void initBoard() {
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                board[i][j] = new BoardCell(i, j);
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
            this.board[row][col].occupant = new Pawn(team);
        }
    }
    private void initFirstRow(Team team, int row) {
        this.board[row][0].occupant = new Rook(team);
        this.board[row][1].occupant = new Bishop(team);
        this.board[row][2].occupant = new Knight(team);
        this.board[row][3].occupant = new Queen(team);
        this.board[row][4].occupant = new King(team);
        this.board[row][5].occupant = new Knight(team);
        this.board[row][6].occupant = new Bishop(team);
        this.board[row][7].occupant = new Rook(team);
    }

    public boolean isInBounds(int row, int col) {
        return 0 <= row && row < BOARD_SIZE && 0 <= col && col < BOARD_SIZE;
    }
    public BoardCell getCell(int row, int col) {
        if (!isInBounds(row, col)){
            throw new ArrayIndexOutOfBoundsException();
        }
        return board[row][col];
    }
    public ArrayList<BoardCell> getMoves(int row, int col){
        ArrayList<BoardCell> result = new ArrayList<>();
        BoardCell cell = getCell(row, col);
        if (!cell.isEmpty()){
            result = cell.occupant.getMoves(this, cell);
        }
        return result;
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

