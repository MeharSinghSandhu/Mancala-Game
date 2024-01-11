package mancala;


public class AyoRules extends GameRules {
/**
 * The AyoRules class defines the rules specific to the Ayo variant of the Mancala game.
 */
    public AyoRules() {
        super(); // Calls the constructor of the superclass to initialize the game board.
    }

/**
     * Executes a move in the Ayo game, distributing stones from the starting pit and possibly capturing stones.
     *
     * @param startPit  The pit from which to start distributing stones.
     * @param playerNum The player number making the move.
     * @return The difference in the number of stones in the store before and after the move.
     * @throws InvalidMoveException If the starting pit number is invalid or the pit is empty.
*/

    @Override
    public int moveStones(int startPit, int playerNum) throws InvalidMoveException {
        if (startPit < 1 || startPit > 12) {
        throw new InvalidMoveException();
    }

    int stonesInitiallyInStore = getDataStructure().getStoreCount(playerNum);
    int lastPit = startPit;
    int stonesInLastPit;

    do {
        int stonesDistributed = distributeStones(lastPit);
        lastPit = (lastPit + stonesDistributed - 1) % 12 + 1;
        stonesInLastPit = getDataStructure().getNumStones(lastPit);
        
        // If last stone ends in an empty pit on player's side and opposite pit has stones, capture them
        if (stonesInLastPit == 1 && isOwnPit(lastPit, playerNum) && getDataStructure().getNumStones(oppositePit(lastPit)) > 0) {
            int capturedStones = captureStones(oppositePit(lastPit));
            getDataStructure().addToStore(playerNum, capturedStones);
        }
    } while (stonesInLastPit > 1); // Continue if last pit is not empty

    return getDataStructure().getStoreCount(playerNum) - stonesInitiallyInStore;
}
/**
     * Distributes stones from a specified starting pit.
     *
     * @param startPit The pit from which to start distributing stones.
     * @return The number of stones distributed.
*/
    

    @Override
    int distributeStones(int startPit) {

    int stonesToMove = getDataStructure().removeStones(startPit);
    getDataStructure().setIterator(startPit, getCurrentPlayer(), true); // Skip the starting pit

    for (int i = 0; i < stonesToMove; i++) {
        getDataStructure().next().addStone();
    }

    return stonesToMove; // Return the number of stones distributed
    }

/** 
     * Captures stones from a specified pit and returns the number of stones captured.
     *
     * @param stoppingPoint The pit from which stones are to be captured.
     * @return The number of stones captured.
*/

    @Override
    int captureStones(int stoppingPoint) {

    int capturedStones = getDataStructure().removeStones(stoppingPoint);
    return capturedStones; 
    }

    private boolean isOwnPit(int pit, int playerNum) {
    // Assuming player 1's pits are 1-6 and player 2's pits are 7-12
    return (playerNum == 1 && pit >= 1 && pit <= 6) || (playerNum == 2 && pit >= 7 && pit <= 12);
    }

    private int oppositePit(int pit) {
     // Calculate and return the opposite pit index
    return 12-pit;
    }
}
