package com.wtc.grp5.model;

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
		
	}

	@Override
	public void send(){
		
	}

	@Override
	public void disconnect(){

	}

	@Override
	public void reconnect(){

	}

}
