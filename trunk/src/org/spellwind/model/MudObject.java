package org.spellwind.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.spellwind.message.Message;
import org.spellwind.task.Task;
import org.spellwind.task.TaskQueue;
import org.spellwind.task.impl.MessageDispatchTask;

/**
 * The root of every object in game, the <code>MudObject</code> class contains
 * methods and fields related to every object. For example, it keeps track of
 * subscriptions/subscribers, object name and description, and it can publish
 * messages.
 * @author Graham Edgecombe
 */
public abstract class MudObject {
	
	/**
	 * The name of this object.
	 */
	private String name;
	
	/**
	 * The description of this object.
	 */
	private String description;
	
	/**
	 * A list of subscriptions (the objects this object is subscribed to).
	 */
	private final List<MudObject> subscriptions = new ArrayList<MudObject>();
	
	/**
	 * A list of subscribers (the object that subscribe to this object).
	 */
	private final List<MudObject> subscribers = new ArrayList<MudObject>();
	
	/**
	 * Creates the object with the specified name and description.
	 * @param name The name of this object.
	 * @param description The description of this object.
	 */
	public MudObject(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	/**
	 * Sets the name of this object.
	 * @param name The new name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the description of this object.
	 * @param description The new description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the name of this object.
	 * @return The name of this object.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the description of this object.
	 * @return The description of this object.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Subscribes to an object.
	 * @param subscription The object to subscribe to.
	 */
	public final void subscribeTo(MudObject subscription) {
		if(subscriptions.contains(subscription)) {
			return;
		}
		subscription.subscribers.add(this);
		subscriptions.add(subscription);
	}
	
	/**
	 * Unsubscribes from an object.
	 * @param subscription The object to unsubscribe from.
	 */
	public final void unsubscribeFrom(MudObject subscription) {
		if(!subscriptions.contains(subscription)) {
			return;
		}
		subscription.subscribers.remove(this);
		subscriptions.remove(subscription);
	}
	
	/**
	 * Gets the subscribers of this object.
	 * @return The objects this object subscribes to.
	 */
	public final Collection<MudObject> getSubscribers() {
		return Collections.unmodifiableCollection(subscribers);
	}
	
	/**
	 * Publishes a message created by this object.
	 * @param message The message to publish.
	 * @throws IllegalArgumentException if the object is not the publisher.
	 */
	public final void publish(Message message) {
		if(message.getPublisher() != this) {
			throw new IllegalArgumentException("This object is not the message publisher.");
		}
		MudObject[] targets = subscribers.toArray(new MudObject[subscribers.size()]);
		Task dispatchTask = new MessageDispatchTask(message, targets);
		TaskQueue.enqueue(dispatchTask);
	}
	
	/**
	 * Handles a message published by an object this object subscribers to.
	 * @param message The message.
	 */
	public abstract void handle(Message message);
	
	/**
	 * Destroys this object - performs tasks such as unsubscribing to prevent
	 * memory leaks.
	 */
	public void destroy() {
		for(MudObject subscription : subscriptions) {
			unsubscribeFrom(subscription);
		}
		for(MudObject subscriber : subscribers) {
			subscriber.unsubscribeFrom(this);
		}
	}
	
}
