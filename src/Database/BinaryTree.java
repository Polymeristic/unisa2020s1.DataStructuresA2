package Database;

import java.security.InvalidKeyException;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Uses a binary search tree to store a database of
 * PlayStation users. Nodes are ordered by user unique key (see the
 * User class for more detail).
 * Created for Data Structures, SP2 2017
 * @author James Baumeister
 * @version 1.0
 */
public class BinaryTree {

	/** Root node of the binary tree **/
	public User root;

	/** Used in recursive functions, is true if the friend was found **/
	private boolean r_didFindFriend = false;

	/**
	 * Making new friends is great. This method should add your new
	 * bestie to your database (tree). Remember that they should be
	 * added according to their key.
	 * @param friend The friend to be added
	 * @return true if  successfully added, false for all error cases
	 * @throws IllegalArgumentException if friend is null
	 */
	public boolean beFriend(User friend) throws IllegalArgumentException {
		if (friend == null)
			throw new IllegalArgumentException("Friend object cannot be null");

		if (root == null) {
			root = friend;
			return true;
		}

		try {
			beFriend(friend, root);
			return true;
		} catch (InvalidKeyException e) {
			return false;
		}
	}

	/**
	 * Helper method to insert a friend into the BST
	 * @param friend User to insert
	 * @return The inserted node
	 * @throws InvalidKeyException if a duplicate key is found
	 */
	private User beFriend(User friend, User root) throws InvalidKeyException {
		if (root == null) {
			return friend;
		}

		if (root.getKey() > friend.getKey()) {
			root.setLeft(beFriend(friend, root.getLeft()));
		} else if (root.getKey() < friend.getKey()) {
			root.setRight(beFriend(friend, root.getRight()));
		} else {
			throw new InvalidKeyException();
		}

		return root;
	}

	/**
	 * Sometimes friendships don't work out. In those cases it's best
	 * to remove that "friend" altogether. This method should remove
	 * all trace of that "friend" in the database (tree).
	 * @param friend the "friend" to remove
	 * @return true if successfully removed, false for all error cases
	 * @throws IllegalArgumentException if "friend" is null
	 */
	public boolean deFriend(User friend) throws IllegalArgumentException {
		if (friend == null)
			throw new IllegalArgumentException("Friend object cannot be null");

		if (root == null) {
			return false;
		}

		r_didFindFriend = false;

		root = deFriend(friend, root);

		return r_didFindFriend;
	}

	/**
	 * Helper method to remove users from the BST
	 * @param friend User to remove
	 * @param root Root node
	 * @return The
	 */
	private User deFriend(User friend, User root) {
		if (root == null || friend == null) return null;

		// Navigate tree
		if (root.getKey() > friend.getKey()) {
			root.setLeft(deFriend(friend, root.getLeft()));
		} else
		if (root.getKey() < friend.getKey()) {
			root.setRight(deFriend(friend, root.getRight()));
		} else {
			r_didFindFriend = true;

			// Check for single children
			if (root.getLeft() == null) return root.getRight();
			if (root.getRight() == null) return root.getLeft();

			// Set root as replacement child from left subtree
			User tMax = new User(maxKeyNode(root.getLeft()));
			tMax.setLeft(root.getLeft());
			tMax.setRight(root.getRight());

			root = tMax;

			// Remove the replacement child from it's own subtree
			root.setLeft(deFriend(root, root.getLeft()));
		}

		return root;
	}

	/**
	 * Gets the node with the minimum key value on a tree
	 * @param root Root node
	 * @return Node with minimum key value
	 */
	private User minKeyNode(User root) {
		// Traverse to the left most node
		while (root.getLeft() != null) {
			root = root.getLeft();
		}

		return root;
	}

	/**
	 * Gets the node with the maximum key value on a tree
	 * @param root Root node
	 * @return Node with maximum key value
	 */
	private User maxKeyNode(User root) {
		// Traverse to the right most node
		while (root.getRight() != null) {
			root = root.getRight();
		}

		return root;
	}

	/**
	 * In your quest to be the very best you need to know how many
	 * of your friends are ranked higher than you. This method should
	 * return the number of higher ranked users that the provided reference
	 * user, or zero if there are none (woot!).
	 * @param reference The starting point in the search
	 * @return Number of higher ranked users or -1 if user not found
	 * @throws IllegalArgumentException if reference is null
	 */
	public int countBetterPlayers(User reference) throws IllegalArgumentException {
		if (reference == null)
			throw new IllegalArgumentException("Friend object cannot be null");

		r_didFindFriend = false;

		int c = countBetterPlayers(reference, root);
		return (r_didFindFriend) ? c : -1;
	}

	/**
	 * Helper function for the number of users who have scores greater than the reference user relative to the root node
	 * @param reference Reference user
	 * @param root Root node
	 * @return Number of players better than the reference player
	 */
	private int countBetterPlayers(User reference, User root) {
		if (root == null) return 0;

		if (root.getKey() == reference.getKey())
			r_didFindFriend = true;

		if (root.getLevel() >= reference.getLevel()) {
			// If is greater than, look for more people at both left
			// and right of tree - and +1 for this node
			return  countBetterPlayers(reference, root.getLeft()) +
					countBetterPlayers(reference, root.getRight()) +
					(root.getLevel() == reference.getLevel() ? 0 : 1);
		}

		// If not greater than, try the right of the tree to find somebody better
		return countBetterPlayers(reference, root.getRight());
	}

	/**
	 * Bragging rights are well earned, but it's good to be sure that you're actually
	 * better than those over whom you're lording your achievements. This method
	 * should find all those friends who have a lower level than you, or zero if
	 * there are none (you suck).
	 * @param reference The starting point in the search
	 * @return Number of lower ranked users
	 * @throws IllegalArgumentException if reference is null
	 */
	public int countWorsePlayers(User reference) throws IllegalArgumentException {
		if (reference == null)
			throw new IllegalArgumentException("Friend object cannot be null");

		r_didFindFriend = false;

		int c = countWorsePlayers(reference, root);
		return (r_didFindFriend) ? c : -1;
	}

	/**
	 * Helper function for the number of users who have scores greater than the reference user relative to the root node
	 * @param reference Reference user
	 * @param root Root node
	 * @return Number of players better than the reference player
	 */
	private int countWorsePlayers(User reference, User root) {
		if (root == null) return 0;

		if (root.getKey() == reference.getKey())
			r_didFindFriend = true;

		if (root.getLevel() <= reference.getLevel()) {
			// If is less than, look for more people at both left
			// and right of tree - and +1 for this node
			return  countWorsePlayers(reference, root.getLeft()) +
					countWorsePlayers(reference, root.getRight()) +
					(root.getLevel() == reference.getLevel() ? 0 : 1);
		}

		// If not less than, try the left of the tree to find somebody worse
		return countWorsePlayers(reference, root.getLeft());
	}

	/**
	 * The best player may not necessarily be measured by who has the highest level.
	 * Platinum trophies are the holy grail, so it would be good to know who has the
	 * most. This method should return the user with the highest number of platinum trophies.
	 * @return the user with the most platinum trophies, or null if there are none
	 */
	public User mostPlatinums() {
		if (root == null) return null;

		User top = mostPlatinums(root);

		return (top.countTrophiesOfRank(Trophy.Rank.PLATINUM) == 0) ? null : top;
	}

	/**
	 * Gets the user with the most platinum awards referenced from a root node
	 * @param root Root node
	 * @return user with the most platinums
	 */
	private User mostPlatinums(User root) {
		User tmp;
		User top = root;

		if (root.getLeft() != null) {
			tmp = mostPlatinums(root.getLeft());
			if (tmp.countTrophiesOfRank(Trophy.Rank.PLATINUM) > top.countTrophiesOfRank(Trophy.Rank.PLATINUM)) {
				top = tmp;
			}
		}

		if (root.getRight() != null) {
			tmp = mostPlatinums(root.getRight());
			if (tmp.countTrophiesOfRank(Trophy.Rank.PLATINUM) > top.countTrophiesOfRank(Trophy.Rank.PLATINUM)) {
				top = tmp;
			}
		}

		return top;
	}

	/**
	 * You or one of your friends bought a new game! This method should add it to their
	 * GameList.
	 * @param username The user who has bought the game
	 * @param game The game to be added
	 */
	public void addGame(String username, Game game) throws IllegalArgumentException {
		if (username == null)
			throw new IllegalArgumentException("Username cannot be null");

		if (game == null)
			throw new IllegalArgumentException("Game cannot be null");

		User user = getFriend(username);

		if (user == null)
			throw new IllegalArgumentException("User does not exist");

		user.getGames().addGame(game);
	}

	/**
	 * You or one of your friends achieved a new trophy! This method should add it to
	 * their trophies.
	 * @param username The user who has earned a new trophy
	 * @param trophy The trophy to be added   
	 */
	public void addTrophy(String username, Trophy trophy) throws IllegalArgumentException {
		if (username == null)
			throw new IllegalArgumentException("Username cannot be null");

		User user = getFriend(username);

		if (user == null)
			throw new IllegalArgumentException("User does not exist");

		if (!user.addTrophy(trophy))
			throw new IllegalArgumentException(String.format("User already has the trophy: %s", trophy.getName()));
	}

	/**
	 * You or one of your friends has gained one level! This is great news, except that
	 * it may have ruined your tree structure! A node move may be in order.
	 * @param username The user whose level has increased
	 */
	public void levelUp(String username) throws IllegalArgumentException {
		if (username == null)
			throw new IllegalArgumentException("Username cannot be null");

		// Get a copy of the user
		User user = getFriend(username);
		User tmp = new User(user);

		// Remove the user from the tree structure
		deFriend(user);

		// Level up and re-add
		tmp.setLevel(tmp.getLevel() + 1);
		tmp.setLeft(null);
		tmp.setRight(null);

		beFriend(tmp);
	}

	/**
	 * As your friends list grows, adding with regular binary tree rules will
	 * result in an unbalanced and inefficient tree. One approach to fix this
	 * is to implement an add method that uses AVL balancing. This method should
	 * work in the same way as beFriend, but maintain a balanced tree according to
	 * AVL rules.
	 * @param friend The friend to be added
	 * @return true if  successfully added, false for all error cases
	 * @throws IllegalArgumentException if friend is null
	 */
	public boolean addAVL(User friend) throws IllegalArgumentException {
		if (friend == null)
			throw new IllegalArgumentException("Friend cannot be null");

		root = addAVL(friend, root);

		return true;
	}

	/**
	 * Adds a friend to the tree using AVL rules
	 * @param friend Friend to add
	 * @param root Root node
	 * @return if user was added
	 */
	private User addAVL(User friend, User root) {
		if (root == null) return friend;

		if (root.getKey() > friend.getKey()) {
			root.setLeft(addAVL(friend, root.getLeft()));
		} else
		if (root.getKey() < friend.getKey()) {
			root.setRight(addAVL(friend, root.getRight()));
		} else {
			throw new IllegalArgumentException("Friend cannot be a duplicate : " + friend.getKey());
		}

		root.setHeight(getHeight(root));
		int b = getBalance(root);

		if (b > 1) {
			if (friend.getKey() > root.getLeft().getKey()) {
				root.setLeft(rotateLeft(root.getLeft()));
			}

			return rotateRight(root);
		} else
		if (b < -1) {
			if (friend.getKey() < root.getRight().getKey()) {
				root.setRight(rotateRight(root.getRight()));
				return rotateLeft(root);
			}

			return rotateLeft(root);
		}

		return root;
	}

	/**
	 * Rotates a root node once left
	 * @param root Root node
	 */
	private User rotateLeft(User root) {
		User userA = root.getRight();
		User userB = userA.getLeft();

		root.setRight(userB);
		userA.setLeft(root);

		root.setHeight(getHeight(root));
		userA.setHeight(getHeight(userA));

		return userA;
	}

	/**
	 * Rotates a root node once right
	 * @param root Root node
	 */
	private User rotateRight(User root) {
		User userA = root.getLeft();
		User userB = userA.getRight();

		root.setLeft(userB);
		userA.setRight(root);

		root.setHeight(getHeight(root));
		userA.setHeight(getHeight(userA));

		return userA;
	}

	/**
	 * Gets the height of a subtree
	 * @param root Root node
	 * @return Height of subtree
	 */
	private int getHeight(User root) {
		if (root == null) return 0;

		return 1 + Math.max(
				getHeight(root.getRight()),
				getHeight(root.getLeft()));
	}

	/**
	 * Gets the balance of a subtree
	 * @param root Root node
	 * @return Balance of tree
	 */
	private int getBalance(User root) {
		if (root == null) return 0;
		return getHeight(root.getLeft()) - getHeight(root.getRight());
	}

	/**
	 * Gets a friend from a given username
	 * @param username Username to search for
	 * @return The user (null if none)
	 */
	public User getFriend(String username) {
		if (username == null)
			throw new IllegalArgumentException("Username cannot be null");

		return getFriend(username, root);
	}

	/**
	 * Recursively searches the database for a user with a matching username from a given root node
	 * @param username Username to search for
	 * @param root Root node
	 * @return Matched user (null if not found)
	 */
	private User getFriend(String username, User root) {
		if (root == null) return null;
		if (root.getUsername().equals(username)) return root;

		User search = getFriend(username, root.getLeft());
		if (search != null) return search;

		search = getFriend(username, root.getRight());
		return search;
	}

	/**
	 * Checks to see if a user is in the friend list
	 * @param checkUser User to check
	 * @return True if in friend list
	 */
	public User getFriend(User checkUser) {
		return getFriend(checkUser.getKey(), root);
	}

	/**
	 * Gets a node with a given node ID
	 * @param ID Check ID
	 * @return True if is contained
	 */
	private User getFriend(double ID, User root) {
		if (root == null) return null;

		if (root.getKey() > ID) {
			return getFriend(ID, root.getLeft());
		} else
		if (root.getKey() < ID) {
			return getFriend(ID, root.getRight());
		} else {
			return root;
		}
	}

	/**
	 * A nice, neat print-out of your friends would look great in the secret scrap-book
	 * that you keep hidden underneath your pillow. This method should print out the
	 * details of each user, traversing the tree in order.
	 * @return A string version of the tree
	 */
	public String toString() {
		if (root == null)
			return "No friends";

		return toString(root);
	}

	/**
	 * Recursive to string, traversing in order
	 * @param root Root node
	 * @return String
	 */
	private String toString(User root) {
		if (root == null) return "";

		StringBuilder sb = new StringBuilder();

		if (root.getLeft() == null) {
			sb.append(String.format("%s\n", root));
		} else {
			sb.append(String.format("%s\n%s\n", toString(root.getLeft()), root));
		}

		if (root.getRight() != null) {
			sb.append(String.format("%s\n", toString(root.getRight())));
		}

		return sb.substring(0, sb.length() - 1);
	}
}
