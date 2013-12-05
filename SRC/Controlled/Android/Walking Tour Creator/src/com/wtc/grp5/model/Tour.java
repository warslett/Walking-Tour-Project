package com.wtc.grp5.model;

import java.util.LinkedList;

public class Tour {

	private String tourName;
	private String shortDesc;
	private String longDesc;
	private LinkedList<Location> locations;
	
	/**
	* Constructs a blank tour.
	*/
	public Tour(){
		tourName = new String();
		shortDesc = new String();
		longDesc = new String();
		locations = new LinkedList<Location>();
	}
	
	/**
	* Constructs a new tour with a specified name.
	*
	* @param tourName the name of the tour.
	*/
	public Tour(String tourName){
		this.tourName = tourName;
		shortDesc = new String();
		longDesc = new String();
		locations = new LinkedList<Location>();
	}

	/**
	* Constructs a new tour with a specified name and short description.
	*
	* @param tourName the name of the tour.
	* @param shortDesc a short description for the tour.
	*/
	public Tour(String tourName, String shortDesc){
		this.tourName = tourName;
		this.shortDesc = shortDesc;
		longDesc = new String();
		locations = new LinkedList<Location>();
	}

	/**
	* Constructs a new tour with all necessary details.
	*
	* @param tourName the name of the tour.
	* @param shortDesc a short description for the tour.
	* @param longDesc a long description for the tour.
	*/
	public Tour(String tourName, String shortDesc, String longDesc){
		this.tourName = tourName;
		this.shortDesc = shortDesc;
		this.longDesc = longDesc;
		locations = new LinkedList<Location>();
	}
	
	/**
	* Adds a location to the tour.
	*
	* @param l the location being added to the tour.
	*/
	public void addLocation(Location l){
		locations.add(l);
	}
	
	/**
	* Removes a location from the tour.
	*
	* @param l the location being removed.
	*/
	public void removeLocation(Location l){
		locations.remove(l);
	}
	
	/**
	* Takes the data stored this object and converts it to a JSON String.
	*
	* @return the JSON String.
	*/
	public String toJSON(){
		String out;
		out = "{\"Tour Name\": \""+tourName+"\",\"Long Description\": \""+longDesc+"\",\"Short Description\": \""+shortDesc+"\",\"Locations\": [";
		for(int i=0;i<locations.size()-1;i++){
			out += locations.get(i).toJSON()+",";		
		}
		out+=locations.get(locations.size()-1).toJSON()+"]}";

		return out;
	}

	/**
	* Sets a new value for this.tourName.
	*
	* @param tourName the new value for this.tourName.
	*/
	public void setTourName(String tourName){
		this.tourName = tourName;
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
		this.shortDesc = shortDesc;
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
		this.longDesc = longDesc;
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
	public void setLocations(LinkedList<Location> locations){
		this.locations = locations;
	}

	/**
	* @return the list of locations in the tour.
	*/
	public LinkedList<Location> getLocations(){
		return locations;
	}

}