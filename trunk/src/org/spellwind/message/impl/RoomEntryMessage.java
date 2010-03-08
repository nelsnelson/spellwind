package org.spellwind.message.impl;

import org.spellwind.message.Message;
import org.spellwind.model.MudObject;
import org.spellwind.model.Room;

/**
 * A <code>Message</code> that is published when an object enters a room.
 * @author Graham Edgecombe
 */
public final class RoomEntryMessage extends Message {
	
	/**
	 * The object that entered the room.
	 */
	private final MudObject object;
	
	/**
	 * Creates the room entry message.
	 * @param publisher The publisher (room that the object entered).
	 * @param object The object that entered the room.
	 */
	public RoomEntryMessage(Room publisher, MudObject object) {
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
	 * Gets the object that entered the room.
	 * @return The object that entered the room.
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
		return object.getName() + " enters the room.";
	}
	
}
