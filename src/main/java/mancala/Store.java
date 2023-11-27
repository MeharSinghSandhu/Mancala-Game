package mancala;
import java.io.Serializable;

/**
 * The Store class represents the store in the Mancala game, 
 * which is a place where each player collects stones during the game.
 */

public class Store implements Countable, Serializable {
    private static final long serialVersionUID = 1L;
    
    private Player owner;
    private int stoneCount;

    /**
     * Constructs a new Store with no owner and an initial stone count of zero.
     */
    public Store() {
        // Initially, a store has no owner and no stones.
        this.owner = null;
        this.stoneCount = 0;
    }

    /**
     * Returns the owner of this store.
     *
     * @return The Player who owns the store.
     */

    // Returns the owner of the store
    public Player getOwner() {
        return owner;
    }

    /**
     * Sets the owner of this store to the specified player.
     *
     * @param player The Player to set as the owner of the store.
     */
    public void setOwner(Player player) {
        this.owner = player;
    }

    /**
     * Returns the count of stones in this store.
     *
     * @return The total number of stones in the store.
     */
    public int getStoneCount() {
        return stoneCount;
    }

    /**
     * Adds a single stone to this store.
     */
    public void addStone() {
        this.stoneCount++;
    }

    /**
     * Adds the specified number of stones to this store.
     *
     * @param amount The number of stones to add.
     * @throws IllegalArgumentException if the amount is negative.
     */
    public void addStones(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot add a negative amount of stones.");
        }
        this.stoneCount += amount;
    }

    /**
     * Removes all stones from this store and returns the count of stones that were removed.
     *
     * @return The number of stones removed from the store.
     */
    public int removeStones() {
        int stonesRemoved = this.stoneCount;
        this.stoneCount = 0;
        return stonesRemoved;
    }
}
