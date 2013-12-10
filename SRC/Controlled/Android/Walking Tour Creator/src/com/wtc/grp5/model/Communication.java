package com.wtc.grp5.model;

import java.net.URL;
import java.net.HttpURLConnection;

public class Communication implements Link {

	private HttpURLConnection connection;
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
	public void connect(String hostName){
		try{
			URL url = new URL(hostName);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.SetDoOutput(true);
		}catch(IOException e){
			// TODO display error message
		}
	}

	@Override
	public void send(){
		OutputStream out = new BufferedOutputStream(connection.getOutputStream());
		out.close();
	}

	@Override
	public void disconnect(){
		connection.disconnect();
	}

	@Override
	public void reconnect(){
		URL url = new URL(hostName);
		connection = (HttpURLConnection) url.openConnection();
	}

}
