package org.spellwind.model;

import org.spellwind.message.Message;

/**
 * Represents a character (living entity) in the MUD.
 * @author Graham Edgecombe
 */
public abstract class Character extends MudObject {
	
	/**
	 * Creates the character.
	 * @param name The name of the character.
	 * @param description The description of the character.
	 */
	public Character(String name, String description) {
		super(name, description);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.spellwind.model.MudObject#handle(org.spellwind.message.Message)
	 */
	@Override
	public void handle(Message message) {
		
	}
	
}
