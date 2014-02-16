/*
 * @(#) TourSave.java Version 1.0
 * Copyright(c) Group 5 @Aberystwyth University Computer Science Dept: Yr 2 (2014)
 * All Rights Reserved
 */

package com.wtc.grp5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.wtc.grp5.model.WTCTour;

public class TourSave {
	
	private String filePath;
	
	/**
	 * Constructs a TourSave object with a file path to where the tour data will be saved.
	 * 
	 * @param filePath the file path for where the tour will saved on the device.
	 */
	public TourSave(String filePath){
		this.filePath = filePath;
	}
	
	/**
	 * Serializes the tour data out to file.
	 * 
	 * @param tour the tour being saved.
	 * @return a message to say whether the save was successful or not
	 */
	public String saveTour(WTCTour tour) {
		try{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
			oos.writeObject(tour);
			oos.close();
		}catch(IOException ex){
			return "Error saving tour";
		}
		return "Tour saved";
	}
	
	/**
	 * Deserializes a tour data file.
	 * 
	 * @return the tour loaded in from file
	 */
	public WTCTour loadTour(){
		WTCTour result = null;
		try{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
			try{
				result = (WTCTour) ois.readObject();
			}catch(ClassNotFoundException classEx){
				ois.close();
				return null;
			}
			ois.close();
		}catch(IOException ioEx){
			return null;
		}
		return result;
		
	}

}
