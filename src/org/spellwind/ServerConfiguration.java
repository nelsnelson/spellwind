package org.spellwind;

/**
 * The server configuration.
 * @author Graham Edgecombe
 */
public final class ServerConfiguration {
	
	/**
	 * The port to bind the server to.
	 */
	private int port;
	
	/**
	 * Creates the server configuration object.
	 */
	public ServerConfiguration() {
		
	}
	
	/**
	 * Gets the port.
	 * @return The port.
	 */
	public int getPort() {
		return port;
	}
	
}
