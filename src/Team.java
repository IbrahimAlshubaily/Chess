enum Team{
    WHITE("W"),
    BLACK("B"),
    ;
    private final String repr;
    Team(String repr) {
        this.repr = repr;
    }
    public String getRepr() {
        return this.repr;
    }
}