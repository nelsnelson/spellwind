package org.spellwind.message.impl;

import org.spellwind.message.Message;
import org.spellwind.model.MudObject;
import org.spellwind.model.Room;

/**
 * A <code>Message</code> that is published when an object leaves a room.
 * @author Graham Edgecombe
 */
public final class RoomExitMessage extends Message {
	
	/**
	 * The object that left the room.
	 */
	private final MudObject object;
	
	/**
	 * Creates the room exit message.
	 * @param publisher The publisher (the room that the object left).
	 * @param object The object that left the room.
	 */
	public RoomExitMessage(Room publisher, MudObject object) {
		super(publisher);
		this.object = object;
	}
	
	/**
	 * Gets the room.
	 * @return The room.
	 */
	public Room getRoom() {
		return (Room) getPublisher();
	}
	
	/**
	 * Gets the object that left the room.
	 * @return The object that left the room.
	 */
	public MudObject getObject() {
		return object;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.spellwind.message.Message#getFormattedMessage()
	 */
	@Override
	public String getFormattedMessage() {
		return object.getName() + " leaves the room.";
	}
	
}
