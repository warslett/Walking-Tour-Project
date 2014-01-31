/*
 *  @(#) WTCTour.java Version 1.0
 *
 *  Copyright(c) Group 5 @Aberystwyth University
 *  All rights reserved
 */
package com.wtc.grp5.model;

import java.util.Calendar;
import java.util.LinkedList;
import java.io.Serializable;


public class WTCTour implements Serializable{

	private String tourName;
	private String shortDesc;
	private String longDesc;
	private LinkedList<WTCLocation> locations;
	
	/**
	* Constructs a blank tour.
	*/
	public WTCTour(){
		tourName = new String();
		shortDesc = new String();
		longDesc = new String();
		locations = new LinkedList<WTCLocation>();
	}
	
	/**
	* Constructs a new tour with a specified name.
	*
	* @param tourName the name of the tour.
	*/
	public WTCTour(String tourName){
		this.tourName = tourName;
		shortDesc = new String();
		longDesc = new String();
		locations = new LinkedList<WTCLocation>();
	}

	/**
	* Constructs a new tour with a specified name and short description.
	*
	* @param tourName the name of the tour.
	* @param shortDesc a short description for the tour.
	*/
	public WTCTour(String tourName, String shortDesc){
		this.tourName = tourName;
		this.shortDesc = shortDesc;
		longDesc = new String();
		locations = new LinkedList<WTCLocation>();
	}

	/**
	* Constructs a new tour with all necessary details.
	*
	* @param tourName the name of the tour.
	* @param shortDesc a short description for the tour.
	* @param longDesc a long description for the tour.
	*/
	public WTCTour(String tourName, String shortDesc, String longDesc){
		this.tourName = tourName;
		this.shortDesc = shortDesc;
		this.longDesc = longDesc;
		locations = new LinkedList<WTCLocation>();
	}
	
	/**
	* Adds a location to the tour.
	*
	* @param location the location being added to the tour.
	*/
	public void addLocation(WTCLocation location){
		locations.add(location);
	}
	
	/**
	* Removes a location from the tour.
	*
	* @param index the index of the location being removed.
	*/
	public void removeLocation(int index){
		locations.remove(index);
	}
	
	/**
	* Takes the data stored this object and converts it to a JSON String.
	*
	* @return the JSON String.
	*/
	public String toJSON(){
		String out;
		out = "{\"title\": \""+tourName+"\",\"longDesc\": \""+longDesc+"\",\"shortDesc\": \""+shortDesc+"\",\"distance\":\""+Double.toString(calcDist())+"\",\"locations\": [";
		if(locations.size()==){
			out+="]}";
		}
		else{
			for(int i=0;i<locations.size()-1;i++){
				out += locations.get(i).toJSON()+",";	
			}
			out+=locations.get(locations.size()-1).toJSON()+"]}";
		}
		return out;
	}

	/**
	* Sets a new value for this.tourName.
	*
	* @param tourName the new value for this.tourName.
	*/
	public void setTourName(String tourName){
		this.tourName = tourName.substring(0,50);//Hard redundent limit on Tour name length
	}
	
	/**
	* @return the name of the tour.
	*/
	public String getTourName(){
		return tourName;
	}

	/**
	* Sets a new value for this.shortDesc.
	*
	* @param shortDesc the new value for this.shortDesc.
	*/
	public void setShortDesc(String shortDesc){
		this.shortDesc = shortDesc.substring(0,100);//hard redundent limit on short description
	}

	/**
	* @return the short description for the tour.
	*/
	public String getShortDesc(){
		return shortDesc;
	}

	/**
	* Sets a new value for this.longDesc.
	*
	* @param longDesc the new value for this.longDesc.
	*/
	public void setLongDesc(String longDesc){
		this.longDesc = longDesc.substring(0,1000);//hard redundant limit on long description
	}

	/**
	* @return the long description for the tour.
	*/
	public String getLongDesc(){
		return longDesc;
	}

	/**
	* Sets the list of locations in the tour.
	*
	* @param locations the new value for this.locations.
	*/
	public void setLocations(LinkedList<WTCLocation> locations){
		this.locations = locations;
	}

	/**
	* @return the list of locations in the tour.
	*/
	public LinkedList<WTCLocation> getLocations(){
		return locations;
	}
	
	public void fixTime(){
		WTCLocation start;
		Calendar startTime;
		start = locations.get(0);
		startTime = start.getOldTime();
		for(int i=0;i<locations.size();i++){
			try{
				long diff = locations.get(i).oldTime.getTimeInMillis() - startTime.getTimeInMillis();
				long sec = diff/1000;
				locations.get(i).setTimeStamp(sec); 
			}catch (Exception e){
				//do stuff
			}
		}		
	}
	
	/** 
	 * Calculate a rough estimate for the distance 
	 */
	public double calcDist(){
		double dist = 0;
		if(locations.size()==0){
			return dist;
		}
		for(int i=1;i<locations.size();i++){
			dist+=GreatCircle(locations.get(i-1),locations.get(i));
		}
		return dist;
	}

	private static double GreatCircle(WTCLocation loc1, WTCLocation loc2){
		double out;
		double radius = 6371000;//The Radius in Meters of the rock we are on
		double dLat = Math.toRadians(loc1.getLatitude() - loc2.getLatitude());
		double dLng = Math.toRadians(loc1.getLongitude() - loc2.getLongitude());
		double dSinLat = Math.sin(dLat/2);
		double dSinLng = Math.sin(dLng/2);
		double temp = Math.pow(dSinLat,2) + Math.pow(dSinLng,2)*Math.cos(Math.toRadians(loc1.getLatitude()))*Math.cos(Math.toRadians(loc2.getLatitude()));
		double rawResult = 2*Math.atan2(Math.sqrt(temp),Math.sqrt(1-temp));
		out = rawResult * radius;
		return out;
	}
}
