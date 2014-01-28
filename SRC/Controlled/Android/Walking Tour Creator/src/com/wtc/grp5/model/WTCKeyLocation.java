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
	public String toJSON(){
		String out;
		out = "{\"Longitude\": \""+Double.toString(longitude)+"\",\"Latitude\": \""+Double.toString(latitude)+"\"Time Stamp\": \""+timeStamp.toString()+"\",\"Images:[\"";
		for(int i=0;i<photos.getLength()-1;i++){
			out += "\"";
		}
                return out;
        }

}


		return out;
	}

}
