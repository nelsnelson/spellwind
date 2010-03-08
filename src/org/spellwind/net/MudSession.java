package org.spellwind.net;

import org.jboss.netty.channel.Channel;
import org.spellwind.model.Player;

/**
 * Represents a single session on the MUD. A session is a connection - not just
 * someone who is in-game.
 * @author Graham Edgecombe
 */
public final class MudSession {
	
	/**
	 * The state of this session.
	 * @author Graham Edgecombe
	 */
	private enum State {
		
		/**
		 * In this state, the player is entering their username to login.
		 */
		LOGIN_USERNAME,
		
		/**
		 * In this state, the player is entering their password to login.
		 */
		LOGIN_PASSWORD,
		
		/**
		 * In this state, the player is in-game.
		 */
		GAME;
		
	}
	
	/**
	 * The channel associated with this session.
	 */
	private final Channel channel;
	
	/**
	 * The player associated with this session.
	 */
	private Player player;
	
	/**
	 * The current state.
	 */
	private State state = State.LOGIN_USERNAME;
	
	/**
	 * The current username.
	 */
	private String username;
	
	/**
	 * Creates the MUD session.
	 * @param channel The channel associated with this session.
	 */
	public MudSession(Channel channel) {
		this.channel = channel;
	}
	
	/**
	 * Initialises the session.
	 */
	public void init() {
		sendRaw("login: ");
	}
	
	/**
	 * Called when a message is received.
	 * @param message The message.
	 */
	public void messageReceived(String message) {
		switch(state) {
		case LOGIN_USERNAME:
			username = message;
			sendRaw(username + "'s password: ");
			state = State.LOGIN_PASSWORD;
			break;
		case LOGIN_PASSWORD:
			login(username, message);
			break;
		case GAME:
			
			break;
		}
	}
	
	/**
	 * Called when a player logs in.
	 * @param username The username.
	 * @param password The password.
	 */
	private void login(String username, String password) {
		player = new Player(username, password);
		player.setSession(this);
		state = State.GAME;
	}
	
	/**
	 * Sends a raw message (no newline is appended).
	 * @param message The message.
	 */
	public void sendRaw(String message) {
		channel.write(message);
	}
	
	/**
	 * Sends some messages. Each message in the array is terminated with
	 * <code>\r\n</code>.
	 * @param messages An array of messages.
	 */
	public void send(String... messages) {
		StringBuilder bldr = new StringBuilder();
		for(String message : messages) {
			bldr.append(message).append("\r\n");
		}
		channel.write(bldr.toString());
	}
	
	/**
	 * Destroys this session.
	 */
	public void destroy() {
		if(player != null) {
			player.removeSession();
			player.destroy();
		}
	}

}
