import java.util.ArrayList;

class BoardCell {
    int row;
    int col;
    ChessPiece occupant;
    public BoardCell(int row, int col){
        this.row = row;
        this.col = col;
    }
    public boolean isInBounds(int boardSize) {
        return this.row < boardSize && this.col < boardSize;
    }
    public boolean isEmpty() {
        return this.occupant == null;
    }
    public boolean isOpponent(Team team) {
        return !this.isEmpty() && this.occupant.getTeam() != team;
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
        initPawns(Team.WHITE, 1);
    }
    private void initPawns(Team team, int row) {
        for (int col = 0; col < BOARD_SIZE; col++){
            this.board[row][col].occupant = new Pawn(team);
        }
    }
    public boolean isValid(BoardCell newCell, Team team) {
        return newCell.isInBounds(BOARD_SIZE) && (newCell.isEmpty() || newCell.isOpponent(team));
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
    public ArrayList<BoardCell> getMoves(int row, int col){
        ArrayList<BoardCell> result = new ArrayList<>();
        BoardCell cell = board[row][col];
        if (cell.isInBounds(BOARD_SIZE) && !cell.isEmpty()){
            result = cell.occupant.getMoves(this, cell);
        }
        return result;
    }
}

