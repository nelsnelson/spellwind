package org.spellwind.task.impl;

import org.spellwind.net.MudSession;
import org.spellwind.task.Task;

/**
 * A task that is executed when a session receives a message.
 * @author Graham Edgecombe
 */
public final class SessionMessageTask extends Task {
	
	/**
	 * The session.
	 */
	private final MudSession session;
	
	/**
	 * The message.
	 */
	private final String message;
	
	/**
	 * Creates the session message task.
	 * @param session The session.
	 * @param message The message.
	 */
	public SessionMessageTask(MudSession session, String message) {
		this.session = session;
		this.message = message;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.spellwind.task.Task#execute()
	 */
	@Override
	public void execute() {
		session.messageReceived(message);
	}
	
}
