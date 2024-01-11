package mancala;
import java.util.ArrayList;
import java.io.Serializable;


/** 
 * The UserProfile class keeps track of a player's profile information, including their name 
 * and the number of games played and won in both Kalah and Ayo variants of the Mancala game.
 */
public class UserProfile implements Serializable {
    private static final long serialVersionUID = 1L;
    private UserProfile userProfile;
    private String name;
    private Store store;
    private int id;
 
    private int kalahGamesPlayed;
    private int ayoGamesPlayed;
    private int kalahGamesWon;
    private int ayoGamesWon;

    /**
     * Constructs a new UserProfile with the given name.
     * Initially, all game counts are set to zero.
     *
     * @param name The name of the user.
     */

    public UserProfile(String name) {
        this.name = name;
        this.kalahGamesPlayed = 0;
        this.ayoGamesPlayed = 0;
        this.kalahGamesWon = 0;
        this.ayoGamesWon = 0;
    }

    /**
     * Returns the name of the user.
     *
     * @return The name of the user.
     */

    public String getName() {
        return name;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public int getStoreCount() {
        return store.getStoneCount();
    }
    
    /**
     * Increments the count of games played based on the game type.
     *
     * @param gameType The type of game played, either "Kalah" or "Ayo".
     */
    public void incrementGamesPlayed(String gameType) {
        if ("Kalah".equals(gameType)) {
            kalahGamesPlayed++;
        } else if ("Ayo".equals(gameType)) {
            ayoGamesPlayed++;
        }
    }

    /**
     * Increments the count of games won based on the game type.
     *
     * @param gameType The type of game won, either "Kalah" or "Ayo".
     */
    public void incrementGamesWon(String gameType) {
        if ("Kalah".equals(gameType)) {
            kalahGamesWon++;
        } else if ("Ayo".equals(gameType)) {
            ayoGamesWon++;
        }
    }

    /**
     * Returns a string representation of the user profile, including the name and game statistics.
     *
     * @return A string representation of the user profile.
     */

    @Override
    public String toString() {
        return "UserProfile{" +
               "name='" + name + '\'' +
               ", kalahGamesPlayed=" + kalahGamesPlayed +
               ", ayoGamesPlayed=" + ayoGamesPlayed +
               ", kalahGamesWon=" + kalahGamesWon +
               ", ayoGamesWon=" + ayoGamesWon +
               '}';
    }
}
