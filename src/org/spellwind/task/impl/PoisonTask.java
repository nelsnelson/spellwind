package org.spellwind.task.impl;

import org.spellwind.task.Task;

/**
 * A task which "poisons" the task queue, so it will stop running if requested.
 * @author Graham Edgecombe
 */
public final class PoisonTask extends Task {
	
	/*
	 * (non-Javadoc)
	 * @see org.spellwind.task.Task#execute()
	 */
	@Override
	public void execute() {
		/* empty */
	}
	
}
