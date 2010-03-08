package org.spellwind.task.impl;

import org.spellwind.net.MudSession;
import org.spellwind.task.Task;

/**
 * A task that is executed when a session is opened.
 * @author Graham Edgecombe
 */
public final class SessionOpenTask extends Task {
	
	/**
	 * The session that was opened.
	 */
	private final MudSession session;
	
	/**
	 * Creates the session opened task.
	 * @param session The session.
	 */
	public SessionOpenTask(MudSession session) {
		this.session = session;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.spellwind.task.Task#execute()
	 */
	@Override
	public void execute() {
		session.init();
	}
	
}
