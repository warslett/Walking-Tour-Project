package com.wtc.grp5.model;
import android.text.format.Time;
import java.util.Date;

public class WTCLocation {

	protected double longitude;
	protected double latitude;
	//private String timeStamp; // Easier if we use times as below
	protected Date oldTime;
	protected long timeStamp; //number of seconds since tour start
	
	/**
	 * Constructs a blank location.
	 */
	public WTCLocation(){
		longitude = 0;
		latitude = 0;
		// timeStamp will implemented later...
		oldTime = new Date();
	}
	
	/**
	 * Constructs a location object a specified longitude and latitude.
	 * 
	 * @param longitude the longitude of the location.
	 * @param latitude the latitude of the location.
	 */
	public WTCLocation(double longitude, double latitude){
		this.longitude = longitude;
		this.latitude = latitude;
		// timeStamp will implemented later...
		oldTime = new Date();
	}
	
	/**
	 * Sets a new value for this.longitude.
	 * 
	 * @param longitude the new value for this.longitude.
	 */
	public void setLongitude(double longitude){
		this.longitude = longitude;
	}

	/**
	 * @return the longitude of the location.
	 */
	public double getLongitude(){
		return longitude;
	}
	
	/**
	 * Sets a new value for this.latitude.
	 * 
	 * @param latitude the new value for this.latitude.
	 */
	public void setLatitude(double latitude){
		this.latitude = latitude;
	}

	/**
	 * @return the latitude for the location.
	 */
	public double getLatitude(){
		return latitude;
	}
	
	/**
	 * Sets a new value fort this.timeStamp.
	 * 
	 * The time stamp is set completely by the method and therefore takes no parameters.
	 */
	public void setTimeStamp(long newTime){

		timeStamp = newTime;
	}

	/**
	 * @return the time stamp for when this location was recorded
	 */
	public String getTimeStamp(){
		return Long.toString(TimeStamp); //this might be broken now hac22, oops
	}
	//Part of some magic code to convert a TIME into a number of seconds since the first time
	public Date getOldTime(){


		return oldTime;
	}
	
	/**
	* Converts the data stored in this object to a JSON string.
	* 
	* @return the JSON String.
	*/
	public String toJSON(){
		String out;
		out = "{\"longitude\": \""+Double.toString(longitude)+"\",\"latitude\": \""+Double.toString(latitude)+"\",\"timestamp\": \""+Long.toString(timeStamp)+"\"}";
		return out;
	}

}
