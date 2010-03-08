package org.spellwind.persist;

/**
 * A class which manages persistence of the in-game word.
 * @author Graham Edgecombe
 */
public final class PersistenceManager {
	
	/**
	 * The singleton instance of the persistence manager.
	 */
	private static final PersistenceManager manager = new PersistenceManager();
	
	/**
	 * Gets the persistence manager.
	 * @return The persistence manager.
	 */
	public static PersistenceManager getManager() {
		return manager;
	}
	
	/**
	 * Creates the persistence manager.
	 */
	private PersistenceManager() {
		
	}

}
