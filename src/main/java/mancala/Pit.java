package mancala;

import java.io.Serializable;
/**
 * Represents a single pit in the Mancala game board.
 * Each pit can hold a number of stones, which can be added or removed during gameplay.
 */

public class Pit implements Countable, Serializable {
    private static final long serialVersionUID = 1L;
    private int stoneCount;
 
    /**
     * Constructs a new Pit with an initial stone count of zero.
     */

    // Constructor
    public Pit() {
        this.stoneCount = 0; // initially, pits have no stones
    }

    /**
     * Adds a single stone to this pit.
     */

    // Adds a single stone to this pit
    public void addStone() {
        stoneCount++;
    }

    /**
     * Adds a specified number of stones to this pit.
     *
     * @param amount The number of stones to add.
     * @throws IllegalArgumentException if the amount is negative.
     */

    public void addStones(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot add a negative number of stones.");
        }
        stoneCount += amount;
    }
    /**
     * Retrieves the current number of stones in this pit.
     *
     * @return The stone count.
     */

    public int getStoneCount() {
        return stoneCount;
    }

    /**
     * Removes all stones from this pit and returns the number of stones that were in it.
     *
     * @return The number of stones removed.
     */

    // Removes all stones from this pit and returns the number of stones that were in it
    public int removeStones() {
        int stonesRemoved = stoneCount;
        stoneCount = 0; // remove all stones
        return stonesRemoved;
    }

    /**
     * Returns a string representation of the pit and its current stone count.
     *
     * @return A string detailing the number of stones in the pit.
     */
    @Override
    public String toString() {
        return "Pit{" +
               "stoneCount=" + stoneCount +
               '}';
    }
}
