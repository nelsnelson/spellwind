package org.spellwind.task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.spellwind.task.impl.PoisonTask;

/**
 * Manages a queue of pending tasks.
 * @author Graham Edgecombe
 */
public final class TaskQueue {
	
	/**
	 * A flag indicating if the server is still running.
	 */
	private static boolean running = true;
	
	/**
	 * A queue of pending tasks.
	 */
	private static final BlockingQueue<Task> taskQueue = new LinkedBlockingQueue<Task>();
	
	/**
	 * Checks if the server is still running.
	 * @return <code>true</code> if so, <code>false</code> if not.
	 */
	public static boolean isRunning() {
		return running;
	}
	
	/**
	 * Enqueus the specified task.
	 * @param task The task to enqueue.
	 */
	public static void enqueue(Task task) {
		taskQueue.add(task);
	}
	
	/**
	 * Processes the next task, if possible.
	 * @return <code>false</code> if an <code>InterruptedException</code>
	 * occurred and no task was processed, <code>true</code> otherwise.
	 */
	public static boolean processNextTask() {
		Task task;
		try {
			task = taskQueue.take();
		} catch (InterruptedException e) {
			return false;
		}
		task.execute();
		return true;
	}
	
	/**
	 * Stops the task queue.
	 */
	public static void stop() {
		running = false;
		taskQueue.offer(new PoisonTask());
	}
	
	/**
	 * Creates the task queue.
	 */
	private TaskQueue() {
		
	}
	
}
