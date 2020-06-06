package Database;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Class to represent a PlayStation game trophy. A trophy comes in
 * four ranks: bronze, silver, gold and platinum. The date the trophy was
 * earned and its respective game is also stored.
 * Created for Data Structures, SP2 2017
 * @author James Baumeister
 * @version 1.0
 */
public class Trophy {
	/** Types of ranks a trophy can be **/
    public enum Rank {
		BRONZE, GOLD, SILVER, PLATINUM

	}

	/** Rarity of a trophy **/
	public enum Rarity {
		RARE, VERY_RARE, ULTRA_RARE, UNCOMMON, COMMON

	}

	/** Name of the trophy **/
	private String name;

	/** Rank of the trophy **/
	private Rank rank;

	/** Rarity of the trophy **/
	private Rarity rarity;

	/** Date the trophy was obtained **/
	private Calendar obtained;

	/** Game in which the trophy was obtained **/
	private Game game;

	/**
	 * Create a new empty trophy
	 */
	public Trophy() { }

	/**
	 * Create a new defined trophy
	 * @param name Name of the trophy
	 * @param rank Rank of the trophy
	 * @param rarity Rarity of the trophy
	 * @param obtained Date the trophy was obtained
	 * @param game Game in which the trophy was obtained
	 */
    public Trophy(String name, Rank rank, Rarity rarity, Calendar obtained, Game game) {
		this.name = name;
		this.rank = rank;
		this.rarity = rarity;
		this.obtained = obtained;
		this.game = game;
    }

	/**
	 * Get the string representation of the trophy
	 * @return A string representing a trophy
	 */
	public String toString() {
		SimpleDateFormat fmt = new SimpleDateFormat("MMM dd, YYYY");

		return String.format("\"%s\", rank: %s, rarity: %s, obtained on: %s", name, rank, rarity,
				fmt.format(obtained.getTime()));
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public void setRarity(Rarity rarity) {
		this.rarity = rarity;
	}

	public Calendar getObtained() {
		return obtained;
	}

	public void setObtained(Calendar obtained) {
		this.obtained = obtained;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
