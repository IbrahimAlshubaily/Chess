enum Direction{
    FORWARD(1, 0),
    BACKWARD(-1, 0),
    LEFT(0, 1),
    RIGHT(0, -1),

    FORWARD_LEFT(1, 1),
    FORWARD_RIGHT(1, -1),
    BACKWARD_LEFT(-1, 1),
    BACKWARD_RIGHT(-1, -1),

    KNIGHT_A(2, 1),
    KNIGHT_B(2, -1),
    KNIGHT_C(-2, 1),
    KNIGHT_D(-2, -1),
    KNIGHT_E(1, 2),
    KNIGHT_F(1, -2),
    KNIGHT_G(-1, 2),
    KNIGHT_H(-1, -2),
    ;

    private final int rowOffset;
    private final int colOffset;
    Direction(int rowOffset, int colOffset) {
        this.rowOffset = rowOffset;
        this.colOffset = colOffset;
    }
    int getRowOffset(Team team){
        if (team == Team.BLACK){
            return - this.rowOffset;
        }
        return this.rowOffset;
    }

    int getColOffset(Team team){
        if (team == Team.BLACK){
            return - this.colOffset;
        }
        return this.colOffset;
    }
}

