package com.wtc.grp5.model;
//Include Time class java
import android.text.format.Time;

public class WTCLocation {

	private double longitude;
	private double latitude;
	//private String timeStamp; // Easier if we use times as below
	private Time timeStamp;
	
	/**
	 * Constructs a blank location.
	 */
	public WTCLocation(){
		longitude = 0;
		latitude = 0;
		// timeStamp will implemented later...
		timeStamp = new Time();
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
		timeStamp.setToNow();
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
	public void setTimeStamp(){
		// Will be implemented later...
	}

	/**
	 * @return the time stamp for when this location was recorded
	 */
	public String getTimeStamp(){
		return timeStamp;
	}
	
	/**
	* Converts the data stored in this object to a JSON string.
	* 
	* @return the JSON String.
	*/
	public String toJSON(){
		String out;
		out = "{\"Longitude\": \""+Double.toString(longitude)+"\",\"Latitude\": \""+Double.toString(latitude)+"\"Time Stamp\": \""+timeStamp.toString()+"\"}";
		return out;
	}

}
