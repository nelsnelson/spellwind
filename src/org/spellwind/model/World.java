package org.spellwind.model;

/**
 * The world class deals with the ingame world, such as the keep record of
 * active players and managing the default spawn room.
 * @author Graham Edgecombe
 */
public final class World {
	
	/**
	 * The world instance.
	 */
	private static final World world = new World();
	
	/**
	 * Gets the world.
	 * @return The world.
	 */
	public static World getWorld() {
		return world;
	}
	
	/**
	 * Creates the world.
	 */
	private World() {
		
	}
	
	/**
	 * Initialises the world.
	 */
	public void init() {
		
	}
	
}
