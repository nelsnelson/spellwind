package org.spellwind.task;

/**
 * Represents a game logic task that must be executed in a single thread for
 * safety reasons.
 * @author Graham Edgecombe
 */
public abstract class Task {
	
	/**
	 * Executes this task.
	 */
	public abstract void execute();

}
