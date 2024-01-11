package mancala;

/**
 * Abstract class representing the rules of a Mancala game.
 * KalahRules and AyoRules will subclass this class.
 */
public abstract class GameRules {
    private MancalaDataStructure gameBoard;
    private int currentPlayer = 1; // Player number (1 or 2)

    /**
     * Constructor to initialize the game board.
     */
    public GameRules() {
        gameBoard = new MancalaDataStructure();
       // gameBoard.setUpPits();
        resetBoard();  
    }
 

    /**
     * Get the number of stones in a pit.
     *
     * @param pitNum The number of the pit.
     * @return The number of stones in the pit.
     */
    public int getNumStones(int pitNum) {
        return gameBoard.getNumStones(pitNum);
    }

    /**
     * Get the game data structure.
     *
     * @return The MancalaDataStructure.
     */
    MancalaDataStructure getDataStructure() {
        return gameBoard;
    }

    /**
     * Check if a side (player's pits) is empty.
     *
     * @param pitNum The number of a pit in the side.
     * @return True if the side is empty, false otherwise.
     */
    boolean isSideEmpty(int pitNum) {
    int startPit, endPit;
    if (pitNum <= 6) { // Assuming pits 1-6 belong to player 1
        startPit = 1;
        endPit = 6;
    } else { // Assuming pits 7-12 belong to player 2
        startPit = 7;
        endPit = 12;
    }

    for (int i = startPit; i <= endPit; i++) {
        if (getDataStructure().getNumStones(i) > 0) {
            return false; // Found a pit that is not empty
        }
    }
    return true; // All pits on the side are empty
        
    }

    /**
     * Set the current player.
     *
     * @param playerNum The player number (1 or 2).
     */
    public void setPlayer(int playerNum) {
        currentPlayer = playerNum;
    }

     /**
     * Public getter method for the current player.
     *
     * @return the current player number.
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Perform a move and return the number of stones added to the player's store.
     *
     * @param startPit  The starting pit for the move.
     * @param playerNum The player making the move.
     * @return The number of stones added to the player's store.
     * @throws InvalidMoveException If the move is invalid.
     */
    public abstract int moveStones(int startPit, int playerNum) throws InvalidMoveException;

    /**
     * Distribute stones from a pit and return the number distributed.
     *
     * @param startPit The starting pit for distribution.
     * @return The number of stones distributed.
     */
    abstract int distributeStones(int startPit);

    /**
     * Capture stones from the opponent's pit and return the number captured.
     *
     * @param stoppingPoint The stopping point for capturing stones.
     * @return The number of stones captured.
     */
    abstract int captureStones(int stoppingPoint);

    /**
     * Register two players and set their stores on the board.
     *
     * @param one The first player.
     * @param two The second player.
     */
    public void registerPlayers(Player one, Player two) {

    Store storeOne = new Store();
    Store storeTwo = new Store();

    // Set the owner of each store
    storeOne.setOwner(one);
    storeTwo.setOwner(two);

    // Assign the stores to the players
    one.setStore(storeOne);
    two.setStore(storeTwo);

    // Integrate the stores into the MancalaDataStructure
    getDataStructure().setStore(storeOne, 1); // Assigning the store of player one
    getDataStructure().setStore(storeTwo, 2); // Assigning the store of player two

    }

    /**
     * Reset the game board by setting up pits and emptying stores.
     */
    public void resetBoard() {
        gameBoard.setUpPits();
        gameBoard.emptyStores();
    }

    @Override
    public String toString() {
        // Implement toString() method logic here.
        return "Board";
    }
}
