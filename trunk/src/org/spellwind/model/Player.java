package org.spellwind.model;

import org.spellwind.message.Message;
import org.spellwind.net.MudSession;

/**
 * Represents a player-controlled character.
 * @author Graham Edgecombe
 */
public final class Player extends Character {
	
	/**
	 * The player's password.
	 */
	private String password;
	
	/**
	 * The current session.
	 */
	private MudSession session;
	
	/**
	 * Creates the player.
	 * @param username The player's username.
	 * @param password The player's password.
	 */
	public Player(String username, String password) {
		super(username, "A person named " + username + " stands here.");
		this.password = password;
	}
	
	/**
	 * Sets this player's password.
	 * @param password The new password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Gets this player's password.
	 * @return The player's password.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the current session attached to this player.
	 * @param session The current session.
	 */
	public void setSession(MudSession session) {
		this.session = session;
	}
	
	/**
	 * Removes the session attached to this player.
	 */
	public void removeSession() {
		setSession(null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.spellwind.model.Character#handle(org.spellwind.message.Message)
	 */
	@Override
	public void handle(Message message) {
		try {
			if(session != null) {
				session.send(message.getFormattedMessage());
			}
		} finally {
			super.handle(message);
		}
	}
	
}
