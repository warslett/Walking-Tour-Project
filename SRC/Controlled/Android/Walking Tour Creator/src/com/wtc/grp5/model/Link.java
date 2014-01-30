/*
 * @(#) Link.java Version 1.0
 * Copyright(c) Group 5 @Aberystwyth University: Computer Science Dept yr 2 (2014)
 * All Rights reserverd
 */
package com.wtc.grp5.model;

import java.io.IOException;

public interface Link{

/**
* Connects to a server with a given host name.
*
* @param hostName The host name of the server you wish to connect to.
*/
public void connect(String hostName) throws IOException;

/**
* Sends data to the server.
*/
public void send() throws IOException;

/**
* Destroys the connection to the server.
*/
public void disconnect() throws IOException;

/**
 * Re-opens a connection to the server.
 */
public void reconnect() throws IOException;

}
