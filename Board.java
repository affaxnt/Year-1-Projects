public class Board {
    private Position[][] board; // 2D array representing the board

    // Constructor 
    public Board() {
        board = new Position[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Position();
            }
        }
        setupStandardBoard();
    }

    private void setupStandardBoard() {
        // Set up for standard starting positions
        board[3][3].setPiece(Pieces.WHITE);
        board[3][4].setPiece(Pieces.BLACK);
        board[4][3].setPiece(Pieces.BLACK);
        board[4][4].setPiece(Pieces.WHITE);

        // Set unplayable positions
        board[7][3] = new UnplayablePosition();
        board[7][4] = new UnplayablePosition();
    }

    public void setupFourByFourStartingPosition() {
        // Reset the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Position();
            }
        }

        // Set up for the Four-by-Four starting positions
        board[2][2].setPiece(Pieces.WHITE);
        board[2][3].setPiece(Pieces.WHITE);
        board[2][4].setPiece(Pieces.BLACK);
        board[2][5].setPiece(Pieces.BLACK);

        board[3][2].setPiece(Pieces.WHITE);
        board[3][3].setPiece(Pieces.WHITE);
        board[3][4].setPiece(Pieces.BLACK);
        board[3][5].setPiece(Pieces.BLACK);

        board[4][2].setPiece(Pieces.BLACK);
        board[4][3].setPiece(Pieces.BLACK);
        board[4][4].setPiece(Pieces.WHITE);
        board[4][5].setPiece(Pieces.WHITE);

        board[5][2].setPiece(Pieces.BLACK);
        board[5][3].setPiece(Pieces.BLACK);
        board[5][4].setPiece(Pieces.WHITE);
        board[5][5].setPiece(Pieces.WHITE);

        // Set unplayable positions
        board[7][3] = new UnplayablePosition();
        board[7][4] = new UnplayablePosition();
    }

    public void drawBoard() {
        System.out.println("  A B C D E F G H");
        for (int i = 0; i < 8; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j].getPiece() + " ");
            }
            System.out.println();
        }
    }

    public Position[][] getBoard() {
        return board;
    }

    public void setBoard(Position[][] board) {
        this.board = board;
    }

    public boolean isValidMove(int row, int col, char playerPiece) {
        // Check if the position is empty and within bounds
        // This ensures that the row and column indices are valid and that the position is not already occupied
        if (row < 0 || row >= 8 || col < 0 || col >= 8 || !board[row][col].canPlay()) {
            return false;  // If any of these conditions are met, the move is not valid
        }

        // Get the character representing the opponent's piece
        char opponentPiece = Pieces.getOpponent(playerPiece);
        // Initialize a flag to indicate if the move is valid
        boolean valid = false;

        //  Eight possible directions to check (up, down, left, right, and diagonals)
        int[][] directions = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1},     // Up, Down, Left, Right
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}    // Diagonals: Up-Left, Up-Right, Down-Left, Down-Right
        };

        // Iterate over each direction
        for (int[] dir : directions) {
            // Get the row and column increments for the current direction
            int dRow = dir[0], dCol = dir[1];
            // Start at the next position in the current direction
            int r = row + dRow, c = col + dCol;
            // Initialize a flag to indicate if any opponent pieces are found in this direction
            boolean foundOpponent = false;

            // Traverse in the current direction as long as the position is within bounds
            // and contains opponent's pieces
            while (r >= 0 && r < 8 && c >= 0 && c < 8 && board[r][c].getPiece() == opponentPiece) {
                foundOpponent = true;  // Set the flag since an opponent's piece is found
                // Move to the next position in the current direction
                r += dRow;
                c += dCol;
            }

            // Check if opponent pieces were found and the next position contains the player's piece
            if (foundOpponent && r >= 0 && r < 8 && c >= 0 && c < 8 && board[r][c].getPiece() == playerPiece) {
                // If both conditions are met, the move is valid
                valid = true;
                break;  // Exit the loop early since we found a valid move
            }
        }

        // Return whether the move is valid
        return valid;
    }

    public void makeMove(int row, int col, char playerPiece) {
        // Check if the move is valid
        if (!isValidMove(row, col, playerPiece)) {
            // If not valid, exit the method
            return;
        }

        // Place the player's piece at the specified location
        board[row][col].setPiece(playerPiece);
        // Get the opponent's piece character
        char opponentPiece = Pieces.getOpponent(playerPiece);

        // Check all eight possible directions (up, down, left, right, diagonals)
        int[][] directions = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1},    // Up, Down, Left, Right
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}   // Diagonals: Up-Left, Up-Right, Down-Left, Down-Right
        };

        // Iterate over each direction
        for (int[] dir : directions) {
            int directionRow = dir[0], directionCol = dir[1];  // Direction row and column increments
            int r = row + directionRow, c = col + directionCol;  // Start at the next position in the direction
            boolean foundOpponent = false;  // Flag to indicate if opponent pieces are found

            // Travel in the current direction as long as the position is within bounds
            // and contains opponent's pieces
            while (r >= 0 && r < 8 && c >= 0 && c < 8 && board[r][c].getPiece() == opponentPiece) {
                foundOpponent = true;  // Set the flag since an opponent's piece is found
                r += directionRow;  // Move to the next row in the direction
                c += directionCol;  // Move to the next column in the direction
            }

            // Check if opponent pieces were found and the next position contains the player's piece
            if (foundOpponent && r >= 0 && r < 8 && c >= 0 && c < 8 && board[r][c].getPiece() == playerPiece) {
                // Flip the opponent's pieces in the current direction
                r = row + directionRow;  // Reset to the initial position in the direction
                c = col + directionCol;
                while (board[r][c].getPiece() == opponentPiece) {
                    board[r][c].setPiece(playerPiece);  // Flip the piece to the player's piece
                    r += directionRow;  // Move to the next row in the direction
                    c += directionCol;  // Move to the next column in the direction
                }
            }
        }
    }

    public boolean hasValidMoves(char playerPiece) {
        // Iterate over each position on the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // Check if placing a piece at the current position is a valid move
                if (isValidMove(i, j, playerPiece)) {
                    // If a valid move is found, return true
                    return true;
                }
            }
        }
        // If no valid moves are found, return false
        return false;
    }

    public void displayValidMoves(char playerPiece) {
        System.out.println("Valid moves for player (" + playerPiece + "):");
        boolean hasValidMove = false;  // Flag to indicate if there are any valid moves

        // Iterate over each position on the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // Check if placing a piece at the current position is a valid move
                if (isValidMove(i, j, playerPiece)) {
                    hasValidMove = true;  // Set the flag since a valid move is found
                    char col = (char) ('A' + j);  // Convert column index to letter
                    int row = i + 1;  // Convert row index to 1-based
                    System.out.print("(" + row + " " + col + ") ");  // Print the valid move
                }
            }
        }

        // If no valid moves are found, print a message indicating that
        if (!hasValidMove) {
            System.out.print("No valid moves available.");
        }
        System.out.println();
    }
}
