package com.wtc.grp5;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.app.Activity;

public class WalkActivity extends Activity {

	private Tour tour;
	private Communication communication;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		GoogleMap map = ((MapFragment) getFragmentManager()
                .findFragmentById(R.id.g_map)).getMap();

        LatLng london = new LatLng(51.5072, 0.1275);
        
        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(london, 7));

        map.addMarker(new MarkerOptions()
                .title("London")
                .snippet("The Capital city of England")
                .position(london));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}
	
	/**
	* Adds a location to the tour.
	*/
	public void addLocation(){
	}
	
	/**
	* Adds a key location to the walk.
	*/
	public void addKeyLocation(){
	}
	
	/**
	* Removes a location from the tour.
	*
	* @param l the location being removed from the tour.
	*/
	public void removeLocation(Location l){
	}
	
	/**
	* Saves the tour to the server.
	*/
	public void saveToServer(){
	}
	
	/**
	* Stops the recording of the and deletes the data.
	*/
	public void cancelWalk(){
	}
	
	/**
	* Adds a stopping location to the tour and stops the recording.
	*/
	public void finishWalk(){
	}
	
	/**
	* @param tour the new value for this.tour.
	*/
	public void setTour(Tour tour){
		this.tour = tour;
	}
	
	/**
	* @return the tour.
	*/
	public Tour getTour(){
		return tour;
	}
	
	/**
	* @param communication the new value for this.communication.
	*/
	public void setCommunication(Communication communication){
		this.communication = communication;	
	}
	
	/**
	* @return the communication.
	*/
	public Communication getCommunication(){
		return communication;
	}
	
}
