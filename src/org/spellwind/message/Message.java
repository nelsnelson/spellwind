package org.spellwind.message;

import org.spellwind.model.MudObject;

/**
 * Represents a message published by a <code>MudObject</code> and sent to its
 * subscribers.
 * @author Graham Edgecombe
 */
public abstract class Message {
	
	/**
	 * The object that published this message.
	 */
	private final MudObject publisher;
	
	/**
	 * Creates a message.
	 * @param publisher The object which published the message.
	 */
	public Message(MudObject publisher) {
		this.publisher = publisher;
	}
	
	/**
	 * Gets the publisher of this message.
	 * @return The object that published this message.
	 */
	public final MudObject getPublisher() {
		return publisher;
	}
	
	/**
	 * Gets a formatted version of this message, for sending to game clients,
	 * logging, etc.
	 * @return A formatted version of this message.
	 */
	public abstract String getFormattedMessage();

}
