public class Pieces {
    public static final char EMPTY = ' ';
    public static final char BLACK = 'B';
    public static final char WHITE = 'W';
    public static final char UNPLAYABLE = '*';

    public static char getOpponent(char piece) {
        if (piece == BLACK) {
            return WHITE; // If the piece is black, the opponent is white
        } else if (piece == WHITE) {
            return BLACK; // If the piece is white, the opponent is black
        } else {
            return EMPTY; 
        }
    }
}
