public class UnplayablePosition extends Position {

    public UnplayablePosition() {
        this.piece = Pieces.UNPLAYABLE;
    }

    @Override
    public boolean canPlay() {
        return false;
    }
}
