package Database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Class to represent a PlayStation user.
 * Created for Data Structures, SP2 2017
 * @author James Baumeister
 * @version 1.0
 */
public class User {

	/** Unique key of this user **/
	private double key;

	/** Username of the user **/
	private String username;

	/** Current level of this user **/
	private int level;

	/** List of trophies this user has achieved **/
	private ArrayList<Trophy> trophies;

	/** Games this user has **/
	private GameList games;

	/** Date of birth of this user **/
	private Calendar dob;

	/** Root user on the left subtree **/
	private User left;

	/** Root user on the right subtree **/
	private User right;


	/**
	 * Create a new defined instance of user
	 * @param username Username of the user
	 * @param dob Date of birth of the user
	 * @param level Level of the user
	 */
	public User(String username, Calendar dob, int level) {
		this.username = username;
		this.dob = dob;
		this.level = level;
    }

    /**
     * DO NOT MODIFY THIS METHOD
     * This method uses the username and level to create a unique key.
     * As we don't want the username's hash to increase the level, it's first converted
     * to a floating point, then added to the level.
     * @return the unique key
     */
    public double calculateKey() {
        int hash = Math.abs(username.hashCode());
        // Calculate number of zeros we need
        int length = (int)(Math.log10(hash) + 1);
        // Make a divisor 10^x
        double divisor = Math.pow(10, length);
        // Return level.hash

        return level + hash / divisor;
    }

    /** Some tests use a method called 'getKey'? Creating this to fix those tests as we cant modify the above method **/
    public double getKey() {
    	return calculateKey();
	}

	/**
	 * Convert this user to a string representation
	 * @return A string representation of this object
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("User: %s\n\nTrophies:\\n", username));

		for (Trophy trophy : trophies) {
			sb.append(String.format("%s\n", trophy));
		}

		sb.append("\nGames:\n");

		for (Game game : games) {
			sb.append(String.format("%s\n", game));
		}

		SimpleDateFormat fmt = new SimpleDateFormat("MMM dd, YYYY");
		sb.append(String.format("\nBirth Date: %s\n", fmt.format(dob.getTime())));

		return sb.toString();
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public ArrayList<Trophy> getTrophies() {
		return trophies;
	}

	public void setTrophies(ArrayList<Trophy> trophies) {
		this.trophies = trophies;
	}

	public GameList getGames() {
		return games;
	}

	public void setGames(GameList games) {
		this.games = games;
	}

	public Calendar getDob() {
		return dob;
	}

	public void setDob(Calendar dob) {
		this.dob = dob;
	}

	public User getLeft() {
		return left;
	}

	public void setLeft(User left) {
		this.left = left;
	}

	public User getRight() {
		return right;
	}

	public void setRight(User right) {
		this.right = right;
	}
}
