class Direction {
    int rowOffset;
    int colOffset;
    public Direction(String direction, String team) {
        switch (direction.toLowerCase()) {
            case "forward":
                this.rowOffset = 1;
                this.colOffset = 0;
            case "backward":
                this.rowOffset = -1;
                this.colOffset = 0;
            case "left":
                this.rowOffset = 0;
                this.colOffset = 1;
            case "right":
                this.rowOffset = 0;
                this.colOffset = -1;
            case "forwardLeft":
                this.rowOffset = 1;
                this.colOffset = 1;
            case "forwardRight":
                this.rowOffset = 1;
                this.colOffset = -1;
            case "backwardLeft":
                this.rowOffset = -1;
                this.colOffset = 1;
            case "backwardRight":
                this.rowOffset = -1;
                this.colOffset = -1;
            case "knightA":
                this.rowOffset = 2;
                this.colOffset = 1;
            case "knightB":
                this.rowOffset = 2;
                this.colOffset = -1;
            case "knightC":
                this.rowOffset = -2;
                this.colOffset = 1;
            case "knightD":
                this.rowOffset = -2;
                this.colOffset = -1;
            case "knightE":
                this.rowOffset = 1;
                this.colOffset = 2;
            case "knightF":
                this.rowOffset = 1;
                this.colOffset = -2;
            case "knightG":
                this.rowOffset = -1;
                this.colOffset = 2;
            case "knightH":
                this.rowOffset = -1;
                this.colOffset = -2;
            default:
                if (team.equalsIgnoreCase("a")) {
                    this.rowOffset = -this.rowOffset;
                    this.colOffset = -this.colOffset;
                }
        }
    }
}
