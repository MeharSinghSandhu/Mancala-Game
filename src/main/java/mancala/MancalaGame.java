package mancala;
import java.io.Serializable;
import java.util.ArrayList;
public class MancalaGame implements Serializable {
    private static final long serialVersionUID = 1L; // Recommended for explicit serialization control
    private GameRules gameRules; // Make transient if GameRules isn't Serializable
    private Player currentPlayer;
    private ArrayList<Player> players;
 
    // Constructor
        public MancalaGame() {
        // Create UserProfiles for both players
        players = new ArrayList<>();
    }
    /**
     * Returns the game rules.
     * 
     * @return The current game rules.
     */
    public GameRules getBoard() {
        return gameRules;
    }

    /**
     * Returns the current player.
     * 
     * @return The player whose turn it is currently.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Retrieves the number of stones in a specified pit.
     * 
     * @param pitNum The pit number to check.
     * @return The number of stones in the pit.
     */
    public int getNumStones(int pitNum) {
        // Now utilizes the MancalaDataStructure's method directly
        return gameRules.getDataStructure().getNumStones(pitNum);
    }
    /**
     * Gets the store count for a specific player.
     * 
     * @param player The player whose store count is to be retrieved.
     * @return The number of stones in the player's store.
     */

    public int getStoreCount(Player player) {
        // This would likely involve getting the player number and using it to fetch the store count
        int playerNum = player.equals(players.get(0)) ? 1 : 2;
        return gameRules.getDataStructure().getStoreCount(playerNum);
    }

    /**
     * Determines the winner of the game based on the number of stones in each player's store.
     * 
     * @return The winning player, or null if there is a tie.
     */
    public Player getWinner() {
        int stonesPlayerOne = players.get(0).getStoreCount();
        int stonesPlayerTwo = players.get(1).getStoreCount();
        
        if (stonesPlayerOne > stonesPlayerTwo) {
            return players.get(0);
        } else if (stonesPlayerOne < stonesPlayerTwo) {
            return players.get(1);
        } else {
            return null; // A tie, could be handled differently based on game rules
        }
    }
    /**
     * Checks whether the game is over by determining if all pits on one side are empty.
     * 
     * @return True if the game is over, false otherwise.
     */
public boolean isGameOver() {
    // Assuming pits 1-6 belong to Player 1 and pits 7-12 belong to Player 2
    boolean playerOnePitsEmpty = true;
    boolean playerTwoPitsEmpty = true;

    // Check if all pits for Player 1 are empty
    for (int i = 1; i <= 6; i++) {
        if (gameRules.getDataStructure().getNumStones(i) > 0) {
            playerOnePitsEmpty = false;
            break;
        }
    }

    // Check if all pits for Player 2 are empty
    for (int i = 7; i <= 12; i++) {
        if (gameRules.getDataStructure().getNumStones(i) > 0) {
            playerTwoPitsEmpty = false;
            break;
        }
    }

    // The game is over if all pits on one side are empty
    return playerOnePitsEmpty || playerTwoPitsEmpty;
}
  
    /**
     * Attempts to make a move starting from a specified pit.
     * 
     * @param startPit The pit from which to begin the move.
     * @return The result of the move as per the rules of the game.
     * @throws InvalidMoveException If the move is not possible as per the game rules.
     */
public int move(int startPit) throws InvalidMoveException {
    try {
        int playerNum;
        if (currentPlayer == players.get(0)) {
            playerNum = 1;
        } else {
            playerNum = 2;
        }
        // Utilize the moveStones method which would contain the logic for making a move
        return gameRules.moveStones(startPit, playerNum);
    } catch (InvalidMoveException e) {
        // Handle the exception, possibly by logging it or notifying the user
        System.err.println("Invalid move: " + e.getMessage());
        return 0;
    }
}

    /**
     * Sets the game rules.
     * 
     * @param theBoard The GameRules to set.
     */
    public void setBoard(GameRules theBoard) {
        this.gameRules = theBoard;
    }

    /**
     * Sets the current player for the turn.
     * 
     * @param player The player to set as the current player.
     */

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    /**
     * Initializes the players for the game and assigns them to the game rules.
     * 
     * @param onePlayer The first player.
     * @param twoPlayer The second player.
     */
    public void setPlayers(Player onePlayer, Player twoPlayer) {
        players.clear();
        players.add(onePlayer);
        players.add(onePlayer);
        gameRules.registerPlayers(onePlayer,twoPlayer);
        setCurrentPlayer(onePlayer);
    }

    /**
     * Captures the remaining stones on the board when the game ends.
     */
    public void captureRemainingStones() {
    // Call this method for each player separately, and capture remaining stones to their store.
    for (int playerNum = 1; playerNum <= 2; playerNum++) {
        int startPit;
        int endPit;
        int stonesToCapture = 0;

        // Determine the start and end pit based on the player number
        if (playerNum == 1) {
            startPit = 1; // The first pit for player 1
            endPit = 6;   // The last pit for player 1
        } else {
            startPit = 8; // The first pit for player 2
            endPit = 13;  // The last pit for player 2
        }

        // Iterate over each pit for the current player and sum up the stones
        for (int pitIndex = startPit; pitIndex <= endPit; pitIndex++) {
            // stonesToCapture += gameBoard.removeStones(pitIndex); // Collect stones from each pit
            stonesToCapture += gameRules.getDataStructure().removeStones(pitIndex);
        }

        // Add the captured stones to the player's store
        // gameBoard.addToStore(playerNum, stonesToCapture);
        gameRules.getDataStructure().addToStore(playerNum, stonesToCapture);
    }
}

    /**
     * Retrieves the list of players.
     * 
     * @return The list of players in the game.
     */
    public ArrayList<Player> getPlayers(){
        return players;
    }
    /**
     * Ends the game, determining the winner and performing any necessary cleanup.
     * 
     * @param gameType The type of game that has ended.
     */
    public void endGame(String gameType) {
        if (isGameOver()) {
            captureRemainingStones();
            Player winner = getWinner();

            // Increment games played for both players
            players.get(0).getUserProfile().incrementGamesPlayed(gameType);
            players.get(1).getUserProfile().incrementGamesPlayed(gameType);

            // Increment games won for the winner
            if (winner != null) {
                winner.getUserProfile().incrementGamesWon(gameType);
            }

            // Reset the game board
            gameRules.resetBoard();
            // Additional cleanup if necessary
        }
    }
    /**
     * Starts a new game, resetting the board and determining the starting player.
     */
    public void startNewGame() {
        // Reset the board for a new game
        gameRules.resetBoard();
        // Determine who starts the new game, could be alternated or chosen by some logic
      
        this.currentPlayer = players.get(0); // Set to first player only if the list is not empty
    

    }

    @Override
    public String toString() {
        // Return a string representation of the game's current state
        return gameRules.toString(); // Assuming GameRules has a meaningful toString implementation
    }
}
