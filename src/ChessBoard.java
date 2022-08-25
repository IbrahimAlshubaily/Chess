import java.util.ArrayList;

class BoardCell {
    int row;
    int col;
    ChessPiece occupant;//coupling alert
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

    public boolean isOpponent(String team) {
        return !this.isEmpty() && !this.occupant.getTeam().equalsIgnoreCase(team);
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
    private BoardCell [][] board;
    public ChessBoard(){
        this.board = initBoard();
        initPieces();

    }

    private BoardCell[][] initBoard() {
        BoardCell [][] board = new BoardCell[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                board[i][j] = new BoardCell(i, j);
            }
        }
        return board;
    }

    private void initPieces() {
        initPawns("black", BOARD_SIZE - 2);
        initPawns("white", 1);
    }
    private void initPawns(String team, int row) {
        for (int col = 0; col < BOARD_SIZE; col++){
            this.board[row][col].occupant = new Pawn(team);
        }
    }

    public boolean isValid(BoardCell newCell, String team) {
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

    private ChessPiece getPiece(int row, int col) {
        return board[row][col].occupant;
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

