package org.shovelgame.engine.io;

import java.util.List;

public interface ServerDelegate {
	/**
	 * Returns only fully authenticated clients
	 * @return
	 */
	List<ClientConnection> getQueue();
	ClientConnection getClient();
}