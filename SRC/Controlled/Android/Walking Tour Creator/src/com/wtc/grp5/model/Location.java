package com.wtc.grp5.model;

public class Location {

	private double longitude;
	private double latitude;
	private String timeStamp;
	
	/**
	 * Constructs a blank location.
	 */
	public Location(){
		longitude = 0;
		latitude = 0;
		// timeStamp will implemented later...
	}
	
	/**
	 * Constructs a location object a specified longitude and latitude.
	 * 
	 * @param longitude the longitude of the location.
	 * @param latitude the latitude of the location.
	 */
	public Location(double longitude, double latitude){
		this.longitude = longitude;
		this.latitude = latitude;
		// timeStamp will implemented later...
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
		out = "{\"Longitude\": \""+Double.toString(longitude)+"\",\"Latitude\": \""+Double.toString(latitude)+"\"Time Stamp\": \""+timeStamp+"\"}";
		return out;
	}

}
