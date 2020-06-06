package Database;

import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 * Class to represent a single linked-list of Database.Game objects.
 * Created for Data Structures, SP2 2017
 * @author James Baumeister
 * @version 1.0
 */
public class GameList implements Iterable<Game> {

    public Game head;

    /**
     * Constructor
     * @param head Head game for the list
     */
	public GameList(Game head) {
        this.head = head;
    }

    /**
     * Adds a game to the list
     * @param game Game to add
     */
    public void addGame(Game game) throws IllegalArgumentException {
        if (game == null)
            throw new IllegalArgumentException("New game cannot be null");

	    Game g;
	    Game next = head;

	    // If head is null set head to game
	    if (next == null) {
	        head = game;
        } else {
            do {
                g = next;

                // If equals current game, return as already added
                if (g.equals(game)) return;
                next = g.getNext();
            } while (next != null);

            g.setNext(game);
        }
    }

    /**
     * Gets a game within this list with a given name
     * @param name Name to check for
     * @return Game object
     */
	public Game getGame(String name) {
        if (name == null)
            throw new IllegalArgumentException("New game cannot be null");

        for (Game g : this) {
            if (g.getName().equals(name)) return g;
        }

        return null;
	}

    /**
     * Remove a game with a given name
     * @param name Name to check for to remove
     */
    public void removeGame(String name) {
        if (name == null)
            throw new IllegalArgumentException("New game cannot be null");

        Game g = head;
        Game prev = null;

        while (g != null) {
            if (g.getName().equals(name)) {
                if (prev != null) {
                    prev.setNext(g.getNext());
                } else { // If is null then this game was the head
                    head = g.getNext();
                }

                return;
            }

            prev = g;
            g = g.getNext();
        }
    }

    /**
     * Remove a game with a given name
     * @param game Name to check for to remove
     */
    public void removeGame(Game game) {
        if (game == null)
            throw new IllegalArgumentException("New game cannot be null");

        Game g = head;
        Game prev = null;

        while (g != null) {
            if (g.equals(game)) {
                if (prev != null) {
                    prev.setNext(g.getNext());
                } else { // If is null then this game was the head
                    head = g.getNext();
                }

                return;
            }

            prev = g;
            g = g.getNext();
        }
    }

    /**
     * Gets the length of this game list
     * @return The list length
     */
    public int length() {
        int l = 0;
        for (Game g : this) l++;
        return l;
    }

    public Game getHead() {
        return head;
    }

    /**
     * Gets the string representation of this object
     * @return The string representation
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int l = 0;

        for (Game g : this) {
            sb.append(String.format("%s\n", g));
            l++;
        }

        // Check for length requirement
        return (l == 0) ? "Empty game list" : sb.substring(0, sb.length() - 1);
    }

    /**
     * Iterator implementation for the game list
     * @return An iterable list
     */
    @Override
    public Iterator<Game> iterator() {
        return new Iterator<>() {
            private Game currentGame = head;

            @Override
            public boolean hasNext() {
                return currentGame != null;
            }

            @Override
            public Game next() {
                Game tmp = currentGame;
                currentGame = currentGame.getNext();
                return tmp;
            }
        };
    }
}
