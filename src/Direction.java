enum Directions{
    FORWARD,
    BACKWARD,
    LEFT,
    RIGHT,

    FORWARD_LEFT,
    FORWARD_RIGHT,
    BACKWARD_LEFT,
    BACKWARD_RIGHT,

    KNIGHT_A,
    KNIGHT_B,
    KNIGHT_C,
    KNIGHT_D,
    KNIGHT_E,
    KNIGHT_F,
    KNIGHT_G,
    KNIGHT_H,
}
class Direction {
    int rowOffset;
    int colOffset;
    public Direction(Directions direction, String team) {
        switch (direction) {
            case FORWARD:
                this.rowOffset = 1;
                this.colOffset = 0;
                break;
            case BACKWARD:
                this.rowOffset = -1;
                this.colOffset = 0;
                break;
            case LEFT:
                this.rowOffset = 0;
                this.colOffset = 1;
                break;
            case RIGHT:
                this.rowOffset = 0;
                this.colOffset = -1;
                break;
            case FORWARD_LEFT:
                this.rowOffset = 1;
                this.colOffset = 1;
                break;
            case FORWARD_RIGHT:
                this.rowOffset = 1;
                this.colOffset = -1;
                break;
            case BACKWARD_LEFT:
                this.rowOffset = -1;
                this.colOffset = 1;
                break;
            case BACKWARD_RIGHT:
                this.rowOffset = -1;
                this.colOffset = -1;
                break;
            case KNIGHT_A:
                this.rowOffset = 2;
                this.colOffset = 1;
                break;
            case KNIGHT_B:
                this.rowOffset = 2;
                this.colOffset = -1;
                break;
            case KNIGHT_C:
                this.rowOffset = -2;
                this.colOffset = 1;
                break;
            case KNIGHT_D:
                this.rowOffset = -2;
                this.colOffset = -1;
                break;
            case KNIGHT_E:
                this.rowOffset = 1;
                this.colOffset = 2;
                break;
            case KNIGHT_F:
                this.rowOffset = 1;
                this.colOffset = -2;
                break;
            case KNIGHT_G:
                this.rowOffset = -1;
                this.colOffset = 2;
                break;
            case KNIGHT_H:
                this.rowOffset = -1;
                this.colOffset = -2;
                break;
            default:
                break;
        }
        if (team.equalsIgnoreCase("black")) {
            this.rowOffset = -this.rowOffset;
            this.colOffset = -this.colOffset;
        }
    }
}
