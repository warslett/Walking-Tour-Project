public interface Link{

/**
* Connects to a server with a given IP address and port number.
*
*
*@param ipAddr The IP address of the server you wish to connect to.
*@param portNum The port number of the application that you are using.
*/
public void connect(String ipAddr,int portNum);
/**
* Sends data to the server.
*
*
*/
public void send();

/**
* Receives data from the server.
*
*/
public void receive();
/**
* Destroys the connection to the server.
*
*
*/
public void disconnect();
/**
 * Pauses the current connection to the server.
 *
 *
 */
public void pauseConnection();

/**
 * Re-opens a paused connection the server.
 *
 *
 */
public void openConnection();

}
