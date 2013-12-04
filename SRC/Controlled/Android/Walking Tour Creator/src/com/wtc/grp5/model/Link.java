package com.wtc.grp5.model;

public interface Link{

/**
* Connects to a server with a given host name.
*
* @param hostName The host name of the server you wish to connect to.
*/
public void connect(String hostName);

/**
* Sends data to the server.
*/
public void send();

/**
* Destroys the connection to the server.
*/
public void disconnect();

/**
 * Re-opens a connection to the server.
 */
public void reconnect();

}
