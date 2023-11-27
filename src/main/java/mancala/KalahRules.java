package mancala;
import mancala.MancalaGame;
import mancala.InvalidMoveException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
public class KalahRules extends GameRules {

/**
     * Constructs a new KalahRules object, initializing the game board using the superclass constructor.
*/
    public KalahRules() {
        super(); // Calls the constructor of the superclass to initialize the game board.
    }

/**
     * Executes a move in the Kalah game, distributing stones from the starting pit and possibly capturing stones.
     *
     * @param startPit  The pit from which to start distributing stones.
     * @param playerNum The player number making the move.
     * @return The difference in the number of stones in the store before and after the move, or -1 for a free turn.
     * @throws InvalidMoveException If the starting pit number is invalid or the pit is empty.
*/

    @Override      
    public int moveStones(int startPit, int playerNum) throws InvalidMoveException {
     if (startPit < 0 || startPit > 12) {
        throw new InvalidMoveException();
    }

    int stonesInitiallyInStore = getDataStructure().getStoreCount(playerNum);
    int stonesDistributed = distributeStones(startPit);
    int lastPit = (startPit + stonesDistributed) % 12;

    // Check if the last stone lands in the player's store for a free turn
    if (lastPit == getDataStructure().getStorePosition(playerNum)) {
        return -1; // Indicate a free turn
    }

    // Check for capture condition
    if (getDataStructure().getNumStones(lastPit) == 1 && isOwnPit(lastPit, playerNum)) {
        int capturedStones = captureStones(lastPit);
        getDataStructure().addToStore(playerNum, capturedStones);
    }

    int stonesFinallyInStore = getDataStructure().getStoreCount(playerNum);
    return stonesFinallyInStore - stonesInitiallyInStore; // Return the number of stones added to the player's store

}

/**
     * Distributes stones from a specified starting pit across the board.
     *
     * @param startPit The pit from which to start distributing stones.
     * @return The number of stones distributed.
*/      

    @Override
    int distributeStones(int startPit) {
        
    int stonesToMove = getDataStructure().removeStones(startPit);
    int currentPlayer = getCurrentPlayer();
    getDataStructure().setIterator(startPit, currentPlayer + 1 , true); // Skip the starting pit

    for (int i = 0; i < stonesToMove; i++) {
        Countable nextPit = getDataStructure().next();
        nextPit.addStone();
    }

    return stonesToMove; // Return the number of stones distributed
        
    }

/**
     * Captures stones from the opponent's pit and the current pit and returns the number of stones captured.
     *
     * @param stoppingPoint The pit from which stones are to be captured.
     * @return The number of stones captured.
*/


    @Override
    int captureStones(int stoppingPoint) {

   int oppositePit = 11 - stoppingPoint; // Calculate the opposite pit index
    int capturedStones = getDataStructure().removeStones(oppositePit); // Capture stones from the opposite pit
    capturedStones += getDataStructure().removeStones(stoppingPoint); // Also take the last stone
    return capturedStones; // Return the total number of captured stones
}

/**
     * Checks if a pit belongs to the current player.
     *
     * @param pit       The pit to check.
     * @param playerNum The player number.
     * @return True if the pit belongs to the current player, false otherwise.
*/

 private boolean isOwnPit(int pit, int playerNum) {
    if (playerNum == 1) {
        // Assuming player 1's pits are 0-5
        return pit >= 0 && pit < 6;
    } else if (playerNum == 2) {
        // Assuming player 2's pits are 6-11
        return pit >= 6 && pit < 12;
    }
    return false;
 }
}
