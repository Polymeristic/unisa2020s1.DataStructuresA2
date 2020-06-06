package Database;

import java.util.Iterator;

/**
 * Class to represent a single linked-list of Database.Game objects.
 * Created for Data Structures, SP2 2017
 * @author James Baumeister
 * @version 1.0
 */
public class GameList implements Iterable<Game> {

    private Game head;

	public GameList(Game head) {
    }

    public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Game g : this) {
		    sb.append(String.format("%s\n", g));
        }

		return sb.toString();
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
     * @param name Name to check for to remove
     */
    public void removeGame(Game name) {
        Game g = head;
        Game prev = null;

        while (g != null) {
            if (g.equals(name)) {
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

    public Game getHead() {
        return head;
    }

    /**
     * Iterator implementation for the game list
     * @return An iterable list
     */
    @Override
    public Iterator<Game> iterator() {
        return new Iterator<Game>() {
            private Game currentGame = head;

            @Override
            public boolean hasNext() {
                return  currentGame != null &&
                        currentGame.getNext() != null;
            }

            @Override
            public Game next() {
                currentGame = currentGame.getNext();
                return currentGame;
            }
        };
    }
}
