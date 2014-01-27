package com.wtc.grp5.model;

public class WTCKeyLocation extends WTCLocation {

	private String[] photos; // Might be changed to ArrayList
	private String locName;
	private String locDesc;

	/**
	 * Constructs a blank key location.
	 */
	public WTCKeyLocation(){
		super();
		photos = new String[5];
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
		photos = new String[5];
		locName = new String();
		locDesc = new String();
	}

	/**
	 * Adds the file path of a photo to this key location.
	 * 
	 * @param path the file path.
	 */
	public void addPhoto(String path){
		//TODO needs implementing
	}

	/**
	 * Removes the file path of a photo from this hey location. 
	 * 
	 * @param path the file path.
	 */
	public void removePhoto(String path){
		//TODO needs implementing
	}

	/**
	 * @return the locName
	 */
	public String getLocName() {
		return locName.substring(0,50);//Even if a 50+ char String sneaks through, cut off any extra characters
	}

	/**
	 * @param locName the locName to set
	 */
	public void setLocName(String locName) {
		this.locName = locName.substring(0,50);//Forces 50 char limit on location names
	}

	/**
	 * @return the locDesc
	 */
	public String getLocDesc() {
		return locDesc.substring(0,100); //even if a 100+ char string gets through cut it up
	}

	/**
	 * @param locDesc the locDesc to set
	 */
	public void setLocDesc(String locDesc) {
		this.locDesc = locDesc.substring(0,100); // forces description to be of limit 100 chars
	}

}
