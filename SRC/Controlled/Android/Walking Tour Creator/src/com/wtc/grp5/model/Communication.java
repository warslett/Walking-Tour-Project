package com.wtc.grp5.model;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.HttpURLConnection;

public class Communication implements Link {

	private HttpURLConnection connection;
	private URL url;
	private String hostName;
	private Tour tour;
	
	/**
	 * Constructs a blank Communication object.
	 */
	public Communication(){
		// Does nothing
	}
	
	/**
	 * Constructs a Communication object with a specified Tour object.
	 * 
	 * @param tour the object to sent to the server.
	 */
	public Communication(Tour tour){
		this.tour = tour;
	}
	
	@Override
	public void connect(String hostName) throws IOException{
		this.hostName = hostName;
		url = new URL(hostName);
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.connect();
	}

	@Override
	public void send() throws IOException{
		OutputStream out = new BufferedOutputStream(connection.getOutputStream());
		// Send the data
		out.close();
	}

	@Override
	public void disconnect() throws IOException{
		connection.disconnect();
	}

	@Override
	public void reconnect() throws IOException{
		url = new URL(this.hostName);
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.connect();
	}

}
