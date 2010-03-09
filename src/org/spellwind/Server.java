package org.spellwind;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.spellwind.model.World;
import org.spellwind.net.MudPipelineFactory;
import org.spellwind.persist.PersistenceManager;
import org.spellwind.task.TaskQueue;

/**
 * The core class of the MUD server.
 * @author Graham Edgecombe
 */
public final class Server {
	
	/**
	 * The logger instance.
	 */
	private static final Logger logger = Logger.getLogger(Server.class.getName());
	
	/**
	 * The entry point of the server. This method will simply create a new
	 * <code>Server</code> object, call the {@link #start()} method and log any
	 * exceptions that occur.
	 * @param args The command-line arguments.
	 */
	public static void main(String[] args) {
		try {
			logger.info("Loading configuration...");
			ServerConfiguration configuration = PersistenceManager.getManager().loadConfiguration();
			Server server = new Server(configuration);
			server.start();
		} catch(Throwable t) {
			logger.log(Level.SEVERE, "Error starting server.", t);
		}
	}
	
	/**
	 * The server configuration.
	 */
	private final ServerConfiguration configuration;
	
	/**
	 * The <code>ServerBootstrap</code> object.
	 */
	private final ServerBootstrap bootstrap = new ServerBootstrap();
	
	/**
	 * Creates the server, initialising Netty and the game world.
	 * @param port The configuration.
	 */
	public Server(ServerConfiguration configuration) {
		logger.info("Starting Spellwind...");
		this.configuration = configuration;
		initNetty();
		initWorld();
	}
	
	/**
	 * Initialises Netty by:
	 * <ul>
	 * <li>Creating the thread pools.</li>
	 * <li>Creating the channel factory.</li>
	 * <li>Creating the pipeline factory.</li>
	 * </ul>
	 */
	private void initNetty() {
		logger.info("Bootstrapping Netty...");
		ExecutorService bossExecutor = Executors.newCachedThreadPool();
		ExecutorService workerExecutor = Executors.newCachedThreadPool();
		bootstrap.setFactory(new NioServerSocketChannelFactory(bossExecutor, workerExecutor));
		bootstrap.setPipelineFactory(new MudPipelineFactory());
	}
	
	/**
	 * Initialises the game world.
	 */
	private void initWorld() {
		logger.info("Creating game world...");
		World.getWorld().init();
	}
	
	/**
	 * Starts the server and continues processing <code>Task</code>s until the
	 * server is shut down.
	 */
	public void start() {
		logger.info("Binding to port: " + configuration.getPort() + "...");
		bootstrap.bind(new InetSocketAddress(configuration.getPort()));
		logger.info("Ready for connections.");
		processTasks();
	}
	
	/**
	 * Processes <code>Task</code>s in the <code>TaskQueue</code> until the
	 * server is shut down.
	 */
	private void processTasks() {
		while(TaskQueue.isRunning()) {
			boolean interrupted = TaskQueue.processNextTask();
			if(interrupted) {
				continue;
			}
		}
	}

}
