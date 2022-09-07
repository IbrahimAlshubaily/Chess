import java.util.ArrayList;

public class MinMax {


    public static BoardMove minMax(ChessBoard board, Player playerOne, Player playerTwo){
        int searchDepth = 5;
        BoardMove bestMove = null;
        int minVal = Integer.MAX_VALUE;
        for (BoardMove move : getPossibleMoves(board, playerOne)) {
            ChessPiece prevOccupant = board.reversibleMove(move);
            int moveVal = max(board, playerTwo, playerOne, searchDepth-1);
            board.reverseMove(move, prevOccupant);
            if (moveVal < minVal){
                minVal = moveVal;
                bestMove = move;
            }
        }
        board.display();
        return bestMove;
    }

    private static int min(ChessBoard board, Player playerOne, Player playerTwo, int depth) {
        if (depth == 0){
            return score(board, playerTwo, playerTwo);
        }
        int minVal = Integer.MAX_VALUE;
        for (BoardMove move : getPossibleMoves(board, playerOne)) {
            if (depth == 5) board.display();
            ChessPiece prevOccupant = board.reversibleMove(move);
            minVal = Math.min(minVal, max(board, playerTwo, playerOne, depth - 1));
            board.reverseMove(move, prevOccupant);
        }
        return minVal;
    }

    private static int max(ChessBoard board, Player playerOne, Player playerTwo, int depth){
        if (depth == 0){
            return score(board, playerOne, playerTwo);
        }
        int maxVal = Integer.MIN_VALUE;
        for (BoardMove move : getPossibleMoves(board, playerOne)) {
            ChessPiece prevOccupant = board.reversibleMove(move);
            maxVal = Math.max(maxVal, min(board, playerTwo, playerOne, depth - 1));
            board.reverseMove(move, prevOccupant);
        }
        return maxVal;
    }

    public static BoardMove randomMove(ChessBoard board, Player playerOne){
        ArrayList<BoardMove> possibleMoves = getPossibleMoves(board, playerOne);
        int randInd = (int) Math.round(Math.random() * possibleMoves.size());
        return possibleMoves.get(randInd);
    }

    private static ArrayList<BoardMove> getPossibleMoves(ChessBoard board, Player player) {
        ArrayList<BoardMove> possibleMoves = new ArrayList<>();
        for (ChessBoardCell source : getPlayerCells(board, player)){
            for (ChessBoardCell destination : source.getOccupant().getMoves(board, source)){
                possibleMoves.add(new BoardMove(source, destination));
            }
        }
        return possibleMoves;
    }

    private static ArrayList<ChessBoardCell> getPlayerCells(ChessBoard board, Player player) {
        ChessBoardCell currCell;
        ArrayList<ChessBoardCell> result = new ArrayList<>(16);
        for (int row = 0; row < board.getSize(); row++){
            for (int col = 0; col < board.getSize(); col++){
                currCell = board.getCell(row, col);
                if (!currCell.isEmpty() && !currCell.isOpponent(player.team)){
                    result.add(currCell);
                }
            }
        }
        return result;
    }

    private static int score(ChessBoard board, Player playerOne, Player playerTwo) {
        return getPlayerCells(board, playerOne).size() - getPlayerCells(board, playerTwo).size();
    }
}
