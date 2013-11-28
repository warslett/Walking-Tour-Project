import java.net.Socket;

public class Communication implements Link {

	private String username;
	private String password;
	private Socket socket;
	private String hostName;
	private int portNum;
	private Tour tour;

	public Communication(){
	
	}

	public Communication(Tour tour){

	}

	public void connect(String hostName, int portNum){
		
	}

	public void send(){
		
	}

	public void receive(){

	}

	public void disconnect(){

	}

	public void pauseConnection(){

	}

	public void reconnect(){

	}

	public void login(String username, String password){

	}

	public void logout(){

	}

}
