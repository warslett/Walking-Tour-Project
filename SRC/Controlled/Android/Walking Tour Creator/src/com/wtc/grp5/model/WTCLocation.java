/*
 * @(#)WTCLocation.java Version 1.0
 * Copyright(c) Group 5 @Aberystwyth University Computer Science Dept: Yr 2 (2014)
 * All Rights Reserved
 */
package com.wtc.grp5.model;

import java.io.Serializable;
import java.util.Calendar;

public class WTCLocation implements Serializable{

	protected double longitude;
	protected double latitude;
	protected Calendar oldTime;
	protected long timeStamp; //number of minuets since tour start
	
	/**
	 * Constructs a blank location.
	 */
	public WTCLocation(){
		longitude = 0;
		latitude = 0;
		oldTime = Calendar.getInstance();
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
		oldTime = Calendar.getInstance();
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
		return Long.toString(timeStamp); //this might be broken now hac22, oops
	}
	//Part of some magic code to convert a TIME into a number of seconds since the first time
	public Calendar getOldTime(){

		return oldTime;
	}
	
	/**
	* Converts the data stored in this object to a JSON string.
	* 
	* @return the JSON String.
	*/
	public String toJSON(){
		String out;// construct a string "out" based on the contents of the WTCLocation object
		out = "{\"Longitude\": \""+Double.toString(longitude)+"\",\"Latitude\": \""+Double.toString(latitude)+"\",\"TimeStamp\": \""+Long.toString(timeStamp)+"\",\"Place\":null}";
		return out;
	}

}
