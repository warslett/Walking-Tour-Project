	package com.wtc.grp5.model;

public class WTCKeyLocation extends WTCLocation {

	private list <String> photos = new ArrayList<String>();
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
		photos.add(path);
	}

	/**
	 * Removes the file path of a photo from this hey location. 
	 * 
	 * @param path the file path.
	 */
	public void removePhoto(String path){
		photos.remove(path);
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
		for(int i=0;i<photos.size()-1;i++){
			out += "\""+"Photo"+i+"\":\""+photo[i]+"\",";
		}
			out +="\""+"Photo"+photos.size()+"\"]}";
                return out;
        }

}


		return out;
	}

}
