import java.net.Socket;

import java.net.HttpURLConnection;

public class Communication implements Link {

	private HttpUrlConnection socket;
	private String hostName;
	private Tour tour;
	
	/**
	 * Constructs a blank Communication object.
	 */
	public Communication(){
	
	}
	
	/**
	 * Constructs a Communication object with a specified Tour object.
	 * 
	 * @param tour the object to sent to the server.
	 */
	public Communication(Tour tour){

	}
	
	public void connect(String hostName){
		
	}

	public void send(){
		
	}

	public void disconnect(){

	}

	public void reconnect(){

	}

}
