package org.spellwind.persist;

import java.io.FileInputStream;
import java.io.IOException;

import org.ho.yaml.YamlDecoder;
import org.spellwind.ServerConfiguration;

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
	
	/**
	 * Loads the configuration from the <code>./data/config.yml</code> file.
	 * @return The configuration.
	 * @throws IOException if an I/O error occurs.
	 */
	public ServerConfiguration loadConfiguration() throws IOException {
		YamlDecoder decoder = new YamlDecoder(new FileInputStream("./data/config.yml"));
		try {
			return decoder.readObjectOfType(ServerConfiguration.class);
		} finally {
			decoder.close();
		}
	}
	
}
