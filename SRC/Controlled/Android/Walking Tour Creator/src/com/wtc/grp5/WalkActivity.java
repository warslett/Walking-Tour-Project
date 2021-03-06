/*
 * @(#) WalkActivity.java Version 1.0
 * Copyright(c) Group 5 @Aberystwyth University Computer Science Dept: Yr 2 (2014)
 * All Rights Reserved
 */

package com.wtc.grp5;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.wtc.grp5.model.WTCKeyLocation;
import com.wtc.grp5.model.WTCLocation;
import com.wtc.grp5.model.WTCTour;

import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;

public class WalkActivity extends Activity implements ConnectionCallbacks, OnConnectionFailedListener,
	LocationListener, OnMarkerClickListener, WTCDialogCallbacks{

	private WTCTour tour;
	private TourSave tourSave;
	private long sampleRate = 2000; // 2000 millis (2 seconds)
	
	private GoogleMap map;
	private LocationClient locClient;
	private Location currentLoc;
	private LocationRequest locRequest;
	private Marker selectedMarker; // The marker the user clicked on
	
	String mCurrentPhotoPath;
	Uri fileUri;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		Intent intent = getIntent();
		setTitle(intent.getStringExtra("TourTitle"));
		tour = new WTCTour(intent.getStringExtra("TourTitle"), intent.getStringExtra("TourShortDesc"), intent.getStringExtra("TourLongDesc"));
		
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.g_map)).getMap();
		map.setMyLocationEnabled(true);
		map.setOnMarkerClickListener(this);
		
        locClient = new LocationClient(this, this, this);
	}
	
	/**
	 * Called when the activity starts.
	 * 
	 * Used to setup the temp storage for the tour and connect to the location client.
	 */
	@Override
	protected void onStart() {
		super.onStart();
		File fileDir = new File(this.getExternalFilesDir("TourCache").getAbsolutePath());
        if(!fileDir.exists()){
        	fileDir.mkdir();
        }
        tourSave = new TourSave(fileDir.getAbsolutePath() + "tour.ser");
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		// If Google Play services is available
		if (ConnectionResult.SUCCESS == resultCode) {
			locClient.connect();
		}
	}
	
	/**
	 * Called when the activity is paused.
	 * 
	 * Used to stop the location client from recording the user's location.
	 */
	@Override
	protected void onPause() {
		locClient.removeLocationUpdates(this);
		super.onPause();
	}
	
	/**
	 * Called when the activity resumes.
	 * 
	 * Used to start location updating and re-load the tour from file.
	 */
	@Override
	protected void onResume() {
		super.onResume();
		if(tour == null){
			tour = tourSave.loadTour();
		}
		locRequest = LocationRequest.create();
        locRequest.setInterval(sampleRate);
        locRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        
        // Prevent the screen from going off and pausing / stopping this activity
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	
	/**
	 * Called when the activity is stopped.
	 * 
	 * Saves the tour to its temp file and disconnects the location client.
	 */
	@Override
	protected void onStop() {
		tourSave.saveTour(tour);
		tour = null;
		locClient.disconnect();
		super.onStop();
	}
	/**
	 * Addss the photo' file path the selected key location after the photo has been taken.
	 */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Get the tour back
        tour = tourSave.loadTour();
    	if(requestCode == 200){
            if (resultCode == RESULT_OK) {
            	for(int i = 0; i < tour.getLocations().size(); i++){
            		if((float) tour.getLocations().get(i).getLatitude() == (float) selectedMarker.getPosition().latitude){
            			Log.d("WILLIAM", "latitudes equal");
            			if((float) tour.getLocations().get(i).getLongitude() == (float) selectedMarker.getPosition().longitude){
            				if(tour.getLocations().get(i).getClass() == WTCKeyLocation.class){
            					Log.d("WILLIAM", "longitudes equal");
            					WTCKeyLocation temp = (WTCKeyLocation) tour.getLocations().get(i);
            					temp.addPhoto(mCurrentPhotoPath);
            					break;
            					}
            				}
            		}
            	}
            	// De-select the marker
        		selectedMarker = null;
                Toast.makeText(this, "Picture saved", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
            	//Toast.makeText(this, "Cancelled save image", Toast.LENGTH_SHORT).show();
            } else {
                // Image capture failed, advise user
            	Toast.makeText(this, "Failed to save image", Toast.LENGTH_SHORT).show();
            }
        }
    }
	/**
	 * Keeps track of the user's location.
	 */
	@Override
	public void onLocationChanged(Location location) {
		currentLoc = location;
		addLocation();
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		
	}
	
	/**
	 * When the phone detects the Google Play Services SDK to interact with the map,
	 * this method add the start location to the map and the tour.
	 */
	@Override
	public void onConnected(Bundle bundle) {
		locClient.requestLocationUpdates(locRequest, this);
		currentLoc = locClient.getLastLocation();
		LatLng loc = new LatLng(currentLoc.getLatitude(), currentLoc.getLongitude());
		if(tour.getLocations().isEmpty()){
			WTCKeyLocation walkStart = new WTCKeyLocation(currentLoc.getLongitude(), currentLoc.getLatitude());
			walkStart.setLocName("Walk Start");
			addKeyLocation(walkStart);
		}
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 15));
	}

	@Override
	public void onDisconnected() {
		
	}
	
	/**
	 * Sets the seleced marker.
	 * 
	 * Called when the user clicks on a map marker.
	 */
	@Override
	public boolean onMarkerClick(Marker marker) {
		selectedMarker = marker;
		return false;
	}
	
	/**
	 * Creates the action bar where all the buttons are.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}
	
	/**
	 * Dictates what happens when the user clicks a button on the options menu.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.action_addLoc:
			// Bring up the dialog to add a new KeyLocation to the tour.
			LocationDetailsFragment locDetailsFrag = new LocationDetailsFragment();
			locDetailsFrag.show(getFragmentManager(), "LocDetails");
			return true;
		case R.id.action_remLoc:
			if(selectedMarker == null){
				Toast.makeText(this, "You must select a location to remove.", Toast.LENGTH_LONG).show();
			}else{
				removeLocation(selectedMarker);
			}
			return true;
		case R.id.action_addPhoto:
			if(selectedMarker == null){
				Toast.makeText(this, "You must select a location to add a photo for.", Toast.LENGTH_LONG).show();
			}else{
				addPhoto();
			}
			return true;
		case R.id.action_remPhoto:
			if(selectedMarker == null){
				Toast.makeText(this, "You must select a location to remove a photo from.", Toast.LENGTH_LONG).show();
			}else{
				// Will be implemented in future
			}
			return true;
		case R.id.action_cancel:
			EndWalkFragment endWalkFrag = new EndWalkFragment();
			endWalkFrag.show(getFragmentManager(), "EndWalk");
			return true;
		case R.id.action_finish:
			FinishWalkFragment finishWalkFrag = new FinishWalkFragment();
			finishWalkFrag.show(getFragmentManager(), "FinishWalk");
			return true;
		case R.id.action_sample_two:
			this.setSameRate(2000);
			locRequest.setInterval(sampleRate);
			return true;
		case R.id.action_sample_three:
			this.setSameRate(3000);
			locRequest.setInterval(sampleRate);
			return true;
		case R.id.action_sample_four:
			this.setSameRate(4000);
			locRequest.setInterval(sampleRate);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	/**
	 * Used to add a new key location to the tour.
	 */
	@Override
	public void onPositiveSelection(DialogFragment fragment) {
		LocationDetailsFragment frag = (LocationDetailsFragment) fragment;
		WTCKeyLocation keyLoc = new WTCKeyLocation(currentLoc.getLongitude(), currentLoc.getLatitude());
		keyLoc.setLocName(frag.getTfLocName().getText().toString());
		keyLoc.setLocDesc(frag.getTfLocDesc().getText().toString());
		addKeyLocation(keyLoc);
	}

	/**
	 * Used to cancel the tour if the user selects 'OK' on the dialog.
	 */
	@Override
	public void onCancelWalkSelection(DialogFragment fragment) {
		cancelWalk();	
	}

	/**
	 * Used to finish the walk and save it to the server if the user selects 'OK on the dialog.
	 */
	@Override
	public void onFinishWalkSelection(DialogFragment fragment) {
		finishWalk();
	}

	/**
	* Adds a location to the tour.
	*/
	public void addLocation() {
		tour.addLocation(new WTCLocation(currentLoc.getLongitude(), currentLoc.getLatitude()));
	}
	
	/**
	* Adds a key location to the walk.
	*/
	public void addKeyLocation(WTCKeyLocation keyLoc) {
		// Add Location pin to the map
		LatLng loc = new LatLng(keyLoc.getLatitude(), keyLoc.getLongitude());
		map.addMarker(new MarkerOptions()
			.title(keyLoc.getLocName())
			.snippet(keyLoc.getLocDesc())
			.position(loc));
		// Add location to the tour object
		tour.addLocation(keyLoc);
	}
	
	/**
	* Removes a location from the tour.
	*
	* @param marker the marker being removed from the map; also used to identify which location to remove.
	*/
	public void removeLocation(Marker marker) {
		for(int i = 0; i < tour.getLocations().size(); i++){
			if((float) marker.getPosition().latitude == (float)tour.getLocations().get(i).getLatitude() &&
					(float) marker.getPosition().longitude == (float) tour.getLocations().get(i).getLongitude() &&
					!tour.getLocations().isEmpty()){
				tour.removeLocation(i);
				Toast.makeText(this, "Location Removed", Toast.LENGTH_SHORT).show();
				break;
			}
		}
		marker.remove();
		selectedMarker = null;
		//Toast.makeText(this, "Location Removed", Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * Adds photo to the tour.
	 */
	public void addPhoto() {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    	// Ensure that there's a camera activity to handle the intent
    	if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
        	// Create the File where the photo should go
        	File photoFile = null;
        	try {
            	photoFile = createImageFile();
            	
        	} catch (IOException ex) {
            	// Error occurred while creating the File
        	}
        	// Continue only if the File was successfully created
        	if (photoFile != null) {
            	takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
            	startActivityForResult(takePictureIntent, 200);
        	}
    	}
	}
	
	/**
	 * Creates the image file where the photo will be saved to.
	 * 
	 * e following method was taken from the Android Developers Documents
	 * 
	 * URL: http://developer.android.com/training/camera/photobasics.html
	 */
	private File createImageFile() throws IOException {
    	// Create an image file name
    	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    	String imageFileName = "JPEG_" + timeStamp + "_";
    	File storageDir = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    	File image = File.createTempFile(
    		imageFileName,  /* prefix */
        	".jpg",         /* suffix */
        	storageDir);    /* directory */

    	// Save a file: path for use with ACTION_VIEW intents
    	mCurrentPhotoPath = image.getAbsolutePath();
    	return image;
	}
	
	public void removePhoto() {
		
	}
	
	/**
	* Saves the tour to the server.
	*/
	private void saveToServer() {
		String[] data = {"http://users.aber.ac.uk/wia2/WTC/upload.php"};
		new SendData(this, tour).execute(data);
	}
	
	/**
	* Stops the recording of the and deletes the data.
	*/
	public void cancelWalk() {
		tour.getLocations().clear();
		tour = null;
		Intent homeScreen = new Intent(this, MainActivity.class);
		startActivity(homeScreen);
		finish();
	}
	
	/**
	* Adds a stopping location to the tour and stops the recording.
	*/
	public void finishWalk() {
		WTCKeyLocation walkEnd = new WTCKeyLocation(currentLoc.getLongitude(), currentLoc.getLatitude());
		walkEnd.setLocName("Walk End");
		addKeyLocation(walkEnd);
		
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if(networkInfo != null && networkInfo.isConnected()){
			Toast.makeText(this, "Saving walk...", Toast.LENGTH_SHORT).show();
			saveToServer();
		}else{
			Toast.makeText(this, "Couldn't connect to server", Toast.LENGTH_LONG).show();
		}
	}
	
	/**
	* @param tour the new value for this.tour.
	*/
	public void setTour(WTCTour tour) {
		this.tour = tour;
	}
	
	/**
	* @return the tour.
	*/
	public WTCTour getTour() {
		return tour;
	}
	
	/**
	 * Sets a new value for this.sampleRate.
	 * 
	 * @param sampleRate the new value for this.sampleRate.
	 */
	public void setSameRate(long sampleRate) {
		this.sampleRate = sampleRate;
	}
	
	/**
	 * @return the sample rate.
	 */
	public long getSampleRate() {
		return sampleRate;
	}
}
