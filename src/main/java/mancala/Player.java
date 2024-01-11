package mancala;
import java.util.ArrayList;
import java.io.Serializable;
 
/**
 * Represents a player in the Mancala game. Each player has a user profile, name, and an associated store.
 */

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    private UserProfile userProfile ;
    private String name;
    private Store store;
    private int id;

    /**
     * Constructs a new Player with the specified name.
     *
     * @param name The name of the player.
     */

    public Player(String nae) {
               // The name should come from the UserProfile
        this.name = nae; // Assuming UserProfile has a getName() method
    }

    /**
     * Retrieves the user profile associated with this player.
     *
     * @return The UserProfile of the player.
     */    

    public UserProfile getUserProfile() {
        return userProfile;
    }

    /**
     * Retrieves the name of the player.
     *
     * @return The name of the player.
     */   

    public String getName() {
        return name;
    }

    /**
     * Sets the store associated with this player.
     *
     * @param store The store to associate with this player.
     */

    public void setStore(Store store) {
        this.store = store;
    }


    /**
     * Retrieves the number of stones in the player's store.
     *
     * @return The count of stones in the player's store.
     */

    public int getStoreCount() {
        return store.getStoneCount();
    }

    /**
     * Provides a string representation of the player including their name and ID.
     *
     * @return A string representation of the player.
     */
    
    @Override
    public String toString() {
        return "Player: " + name + " (ID: " + id + ")";
    }
}
