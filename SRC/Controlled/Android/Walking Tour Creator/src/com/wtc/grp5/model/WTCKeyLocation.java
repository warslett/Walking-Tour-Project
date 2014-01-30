/*
 * @(#) WTCKeyLocation.java Version 1.0
 * Copyright(c) Group 5 @Aberystwyth University Computer Science Dept: Yr 2 (2014)
 * All Rights Reserved
 */
package com.wtc.grp5.model;

import java.util.ArrayList;
import java.util.List;

public class WTCKeyLocation extends WTCLocation {

	private List<String> photos;
	private String locName;
	private String locDesc;

	/**
	 * Constructs a blank key location.
	 */
	public WTCKeyLocation(){
		super();
		photos = new ArrayList<String>(5);
		locName = new String();
		locDesc = new String();
	}

	/**
	 * Constructs a key location with a given longitude and latitude.
	 * 
	 * @param longitude the longitude of the location.
	 * @param latitude the latitude of the location.
	 */
	public WTCKeyLocation(double longitude, double latitude){
		super(longitude, latitude);
		photos = new ArrayList<String>(5);
		locName = new String();
		locDesc = new String();
	}

	/**
	 * Adds the file path of a photo to this key location.
	 * 
	 * @param path the file path.
	 */
	public void addPhoto(String path){
		if(photos.size() < 5){
			photos.add(path);
		}
	}

	/**
	 * Removes the file path of a photo from this hey location. 
	 * 
	 * @param path the file path.
	 */
	public void removePhoto(String path){
		if(!photos.isEmpty()){
			photos.remove(path);
		}
	}
	
	public List<String> getPhotos(){
		return photos;
	}

	/**
	 * @return the locName
	 */
	public String getLocName() {
		return locName;
	}

	/**
	 * @param locName the locName to set
	 */
	public void setLocName(String locName) {
		this.locName = locName;
	}

	/**
	 * @return the locDesc
	 */
	public String getLocDesc() {
		return locDesc;
	}

	/**
	 * @param locDesc the locDesc to set
	 */
	public void setLocDesc(String locDesc) {
		this.locDesc = locDesc;
	}
	
	@Override
	public String toJSON(){
		String out;
		out = "{\"Longitude\": \""+Double.toString(longitude)+"\",\"Latitude\": \""+Double.toString(latitude)+"\",\"TimeStamp\": \""+Long.toString(timeStamp)+"\",\"Place\":\""+locName+"\",\"Photos\":[";
		if(!photos.isEmpty()){ //incase the photographs are empty simply close the JSON array of photos
			for(int i=0;i<photos.size()-1;i++){// step though all but the last of the photographs appending the filepath to the JSON string seperating them with commas
					out+="\""+photos.get(i)+"\",";
			}
				out+="\""+photos.get(photos.size())+"\"]}";//append the last photograph file path and close the array
			}
		else{
			out+="]}";
		}
		return out;
	}

}
