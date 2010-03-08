package org.spellwind.task.impl;

import org.spellwind.net.MudSession;
import org.spellwind.task.Task;

/**
 * A task which is executed when a session is closed.
 * @author Graham Edgecombe
 */
public final class SessionCloseTask extends Task {
	
	/**
	 * The session that was closed.
	 */
	private final MudSession session;
	
	/**
	 * Creates the session closed task.
	 * @param session The session that was closed.
	 */
	public SessionCloseTask(MudSession session) {
		this.session = session;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.spellwind.task.Task#execute()
	 */
	@Override
	public void execute() {
		session.destroy();
	}
	
}
