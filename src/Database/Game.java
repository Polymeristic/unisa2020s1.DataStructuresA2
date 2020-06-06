package Database;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Class to represent a PlayStation game.
 * Created for Data Structures, SP2 2017
 * @author James Baumeister
 * @version 1.0
 */
public class Game {

	/** Next game in the single linked list **/
	private Game nextGame;

	/** Release date of the game **/
	private Calendar released;

	/** Name of the game **/
	private String name;

	/** Total trophies that this game has **/
	private int totalTrophies;

	/**
	 * Create an empty game object
	 */
    public Game() { }

	/**
	 * Create a defined game object
	 * @param name Name of the game
	 * @param released Release date of the game
	 * @param totalTrophies Total trophies the game has
	 */
    public Game(String name, Calendar released, int totalTrophies) {
    	this.name = name;
    	this.released = released;
    	this.totalTrophies = totalTrophies;
    }

    public String toString() {
    	SimpleDateFormat fmt = new SimpleDateFormat("MMM dd, YYYY");

		return String.format(
				"\"%s\", released on: %s", name, fmt.format(released.getTime())
		);
    }

	/**
	 * Checks if this game is equal to another object
	 * @param o Other object
	 * @return True if equal
	 */
	@Override
    public boolean equals(Object o) {
		if (o == null) return false;
		if (!(o instanceof Game)) return false;
		if (o == this) return true;

		Game g = (Game) o;

		return  name.equals(g.getName()) &&
				released.equals(g.getReleased()) &&
				totalTrophies == g.getTotalTrophies();
    }

	public Game getNext() {
		return nextGame;
	}

	public void setNext(Game nextGame) {
		this.nextGame = nextGame;
	}

	public Calendar getReleased() {
		return released;
	}

	public void setReleased(Calendar released) {
		this.released = released;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalTrophies() {
		return totalTrophies;
	}

	public void setTotalTrophies(int totalTrophies) {
		this.totalTrophies = totalTrophies;
	}
}
