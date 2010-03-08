package org.spellwind.model;

import java.util.ArrayList;
import java.util.List;

import org.spellwind.message.Message;
import org.spellwind.message.impl.RoomEntryMessage;
import org.spellwind.message.impl.RoomExitMessage;

/**
 * Represents a room in the world. This is not necessarily physical room,
 * but a single 'location', in which other objects are placed.
 * @author Graham Edgecombe
 */
public final class Room extends MudObject {
	
	/**
	 * A list of objects in this room.
	 */
	private final List<MudObject> children = new ArrayList<MudObject>();
	
	/**
	 * Creates the room.
	 * @param name The name of this room.
	 * @param description The description of this room.
	 */
	public Room(String name, String description) {
		super(name, description);
	}
	
	/**
	 * Places a child object into this room.
	 * @param child The child object.
	 */
	public void enter(MudObject child) {
		if(children.contains(child)) {
			return;
		}
		children.add(child);
		publish(new RoomEntryMessage(this, child));
		child.subscribeTo(this);
	}
	
	/**
	 * Removes a child object from this room.
	 * @param child The child object.
	 */
	public void leave(MudObject child) {
		if(!children.contains(child)) {
			return;
		}
		children.remove(child);
		publish(new RoomExitMessage(this, child));
		child.unsubscribeFrom(this);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.spellwind.model.MudObject#handle(org.spellwind.message.Message)
	 */
	@Override
	public void handle(Message message) {
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.spellwind.model.MudObject#destroy()
	 */
	@Override
	public void destroy() {
		try {
			List<MudObject> children = new ArrayList<MudObject>(this.children);
			for(MudObject child : children) {
				leave(child);
			}
		} finally {
			super.destroy();
		}
	}

}
