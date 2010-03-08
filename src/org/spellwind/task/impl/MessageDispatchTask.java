package org.spellwind.task.impl;

import org.spellwind.message.Message;
import org.spellwind.model.MudObject;
import org.spellwind.task.Task;

/**
 * A task which dispatches a message.
 * @author Graham Edgecombe
 */
public final class MessageDispatchTask extends Task {
	
	/**
	 * The message.
	 */
	private final Message message;
	
	/**
	 * The targets of the message.
	 */
	private final MudObject[] targets;
	
	/**
	 * Creates the message dispatch task.
	 * @param message The message.
	 * @param targets The targets.
	 */
	public MessageDispatchTask(Message message, MudObject[] targets) {
		this.message = message;
		this.targets = targets;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.spellwind.task.Task#execute()
	 */
	@Override
	public void execute() {
		for(MudObject target : targets) {
			target.handle(message);
		}
	}
	
}
