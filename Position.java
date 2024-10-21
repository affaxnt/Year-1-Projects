public class Position {
    protected char piece;

    public Position() {
        this.piece = Pieces.EMPTY;
    }

    public boolean canPlay() {
        return this.piece == Pieces.EMPTY;
    }

    public char getPiece() {
        return piece;
    }

    public void setPiece(char piece) {
        this.piece = piece;
    }
}
