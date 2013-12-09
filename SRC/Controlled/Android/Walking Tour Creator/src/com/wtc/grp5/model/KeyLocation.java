package com.wtc.grp5.model;

public class KeyLocation extends Location {

	private String[] photos; // Might be changed to ArrayList
	private String locName;
	private String locDesc;

	/**
	 * Constructs a blank key location.
	 */
	public KeyLocation(){
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
	public KeyLocation(long longitude, long latitude){
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
	
	public String toJSON(){
		String out;
		out = "{\"Location name\":\""+locName+"\",\"Location Description\":\""+locDesc+"\",\"Photos\"[";
		for(int i=0;i<4;i++){
			out+="{\"Photo filename\":\""+photos[i]",\"";

		}
		out+="\""//TODO finish


		return out;
	}

}
