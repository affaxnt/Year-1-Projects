import java.util.Scanner;  
import java.io.File;  // For file handling
import java.io.FileWriter;  // For writing to files
import java.io.IOException;  // For handling input-output exceptions

public class Game {
    private Board board;  // The game board
    private Player[] players;  // Array to hold two players
    private Player currentPlayer;  // The current player

    // Constructor to initialize the board and players array
    public Game() {
        board = new Board();  // Initialize the board
        players = new Player[2];  // Initialize the players array
    }

    // Method to start a new game
    public void start() {
        Scanner scanner = new Scanner(System.in);  

        // Reset the board
        board = new Board();

        // Get player names
        System.out.print("Enter name for Player 1 (Black): ");
        players[0] = new Player(scanner.nextLine(), Pieces.BLACK);  // Initialize Player 1 with black pieces
        System.out.print("Enter name for Player 2 (White): ");
        players[1] = new Player(scanner.nextLine(), Pieces.WHITE);  // Initialize Player 2 with white pieces
        currentPlayer = players[0];  // Set the current player to Player 1

        // Choose starting position
        System.out.println("Choose starting position:");
        System.out.println("1. Standard Starting Positions");
        System.out.println("2. Four-by-Four Starting Position");
        int choice = scanner.nextInt();  // Get the starting position choice from the user
        if (choice == 2) {
            board.setupFourByFourStartingPosition();  // Setup the board for the Four-by-Four starting position
        }

        // Start game loop
        play();  // Call the play method to start the game
    }

    // Static method to load a game from a file
    public static Game load(String filename) {
        Game game = new Game();  // Create a new game instance
        try {
            Scanner fileScanner = new Scanner(new File(filename));  // Open the file to load the game
            game.players[0] = new Player(fileScanner.nextLine(), Pieces.BLACK);  // Load Player 1's name and piece colour
            game.players[1] = new Player(fileScanner.nextLine(), Pieces.WHITE);  // Load Player 2's name and piece colour
            
            // Load the current player 
            String currentPlayerIndex = fileScanner.nextLine();
            if (currentPlayerIndex.equals("0")) {
                game.currentPlayer = game.players[0];  // Set current player to Player 1
            } else {
                game.currentPlayer = game.players[1];  // Set current player to Player 2
            }

            Position[][] loadedBoard = new Position[8][8];  // Initialize the board array
            String boardState = fileScanner.nextLine();  // Read the board state from the file
            int index = 0;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    char piece = boardState.charAt(index++);  // Get each piece from the board state string
                    if (piece == Pieces.UNPLAYABLE) {
                        loadedBoard[i][j] = new UnplayablePosition();  // Set unplayable positions
                    } else {
                        loadedBoard[i][j] = new Position();  // Set playable positions
                        loadedBoard[i][j].setPiece(piece);  // Set the piece on the board
                    }
                }
            }
            game.board.setBoard(loadedBoard);  // Set the board with the loaded positions
        } catch (IOException error) {
            System.out.println("Error loading game: " + error.getMessage());  // Handle file loading errors
        }
        return game;  // Return the loaded game instance
    }

    // Main game loop method
    public void play() {
        Scanner scanner = new Scanner(System.in);  

        while (true) {
            board.drawBoard();  // Draw the current board state
            board.displayValidMoves(currentPlayer.getColour());  // Display valid moves for the current player
            System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getColour() + ")");
            if (!board.hasValidMoves(currentPlayer.getColour())) {
                System.out.println("No valid moves available for " + currentPlayer.getName() + ".");
                System.out.println("1. Save game");
                System.out.println("2. Concede");
                System.out.println("3. Forfeit turn");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // newline
                if (choice == 1) {
                    System.out.print("Enter filename to save: ");
                    String filename = scanner.nextLine();
                    save(filename);  // Save the game state to a file
                } else if (choice == 2) {
                    System.out.println(currentPlayer.getName() + " concedes!");
                    break;  // End the game if a player concedes
                } else if (choice == 3) {
                    System.out.println(currentPlayer.getName() + " forfeits the turn.");
                    switchPlayer();  // Switch to the other player
                } else {
                    System.out.println("Invalid choice, try again.");
                }
            } else {
                System.out.println("1. Make a move");
                System.out.println("2. Save game");
                System.out.println("3. Concede");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // newline

                if (choice == 1) {
                    System.out.print("Enter move (row col, e.g., 4 D): ");
                    String moveInput = scanner.nextLine();
                    String[] moveParts = moveInput.split(" ");
                    if (moveParts.length == 2) {
                        int row = Integer.parseInt(moveParts[0]) - 1;  // Parse row input
                        char colChar = moveParts[1].charAt(0);  // Parse column input
                        int col = colChar - 'A';  // Convert column to index
                        if (board.isValidMove(row, col, currentPlayer.getColour())) {
                            makeMove(row, col);  // Make the move if it's valid
                            if (isGameOver()) {
                                checkWinner();  // Check for a winner if the game is over
                                break;
                            }
                        } else {
                            System.out.println("Invalid move, try again.");
                        }
                    } else {
                        System.out.println("Invalid input format, try again.");
                    }
                } else if (choice == 2) {
                    System.out.print("Enter filename to save: ");
                    String filename = scanner.nextLine();
                    save(filename);  // Save the game state to a file
                } else if (choice == 3) {
                    System.out.println(currentPlayer.getName() + " concedes!");
                    break;  // End the game if a player concedes
                } else {
                    System.out.println("Invalid choice, try again.");
                }
            }
        }
    }

    // Method to make a move
    public void makeMove(int row, int col) {
        if (board.isValidMove(row, col, currentPlayer.getColour())) {
            board.makeMove(row, col, currentPlayer.getColour());  // Make the move on the board
            switchPlayer();  // Switch to the other player
        }
    }

    // Method to check and display the winner
    public void checkWinner() {
        int blackCount = 0;
        int whiteCount = 0;
        Position[][] positions = board.getBoard();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (positions[i][j].getPiece() == Pieces.BLACK) {
                    blackCount++;
                } else if (positions[i][j].getPiece() == Pieces.WHITE) {
                    whiteCount++;
                }
            }
        }

        System.out.println("Game Over!");
        board.drawBoard();  // Draw the final board state
        System.out.println(players[0].getName() + " (Black): " + blackCount);
        System.out.println(players[1].getName() + " (White): " + whiteCount);

        if (blackCount > whiteCount) {
            System.out.println(players[0].getName() + " wins!");
        } else if (whiteCount > blackCount) {
            System.out.println(players[1].getName() + " wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    // Method to check if the game is over
    public boolean isGameOver() {
        return !board.hasValidMoves(players[0].getColour()) && !board.hasValidMoves(players[1].getColour());  // Check if there are no valid moves for both players
    }

    // Method to switch the current player
    private void switchPlayer() {
        if (currentPlayer == players[0]) {
            currentPlayer = players[1];
        } else {
            currentPlayer = players[0];
        }
    }

    // Method to save the game state to a file
    public void save(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);  // Open the file to save the game state
            writer.write(players[0].getName() + "\n");
            writer.write(players[1].getName() + "\n");

            if (currentPlayer == players[0]) {
                writer.write("0\n");
            } else {
                writer.write("1\n");
            }

            StringBuilder boardState = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    boardState.append(board.getBoard()[i][j].getPiece());
                }
            }
            writer.write(boardState.toString());
            writer.close();
            System.out.println("Game saved successfully.");
        } catch (IOException error) {
            System.out.println("Error saving game: " + error.getMessage());
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Start a New Game");
            System.out.println("2. Load a Game");
            System.out.println("3. Quit");

            System.out.print("Enter your choice: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input, please enter a number.");
                scanner.next();  // clear invalid input
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume the newline

            if (choice == 1) {
                game.start();
            } else if (choice == 2) {
                System.out.print("Enter filename to load: ");
                String filename = scanner.nextLine();
                game = Game.load(filename);
                game.play();
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice, try again.");
            }
        }
    }
}
