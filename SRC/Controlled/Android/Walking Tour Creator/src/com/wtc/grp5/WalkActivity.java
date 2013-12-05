package com.wtc.grp5;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.wtc.grp5.model.Communication;
import com.wtc.grp5.model.Location;
import com.wtc.grp5.model.Tour;

import android.os.Bundle;
import android.view.Menu;
import android.app.Activity;

public class WalkActivity extends Activity {

	private Tour tour;
	private Communication communication;
	private double sampleRate;

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
	public void removeLocation(Location location){
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
	
	/**
	 * Sets a new value for this.sampleRate.
	 * 
	 * @param sampleRate the new value for this.sampleRate.
	 */
	public void setSameRate(double sampleRate){
		this.sampleRate = sampleRate;
	}
	
	/**
	 * @return the sample rate.
	 */
	public double getSampleRate(){
		return sampleRate;
	}
	
}
