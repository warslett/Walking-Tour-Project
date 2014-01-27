package com.wtc.grp5;

import java.io.File;

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
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;

public class WalkActivity extends Activity implements ConnectionCallbacks, OnConnectionFailedListener,
	LocationListener, OnMarkerClickListener, LocationDetailsFragment.LocationDetailsListener{

	private WTCTour tour;
	private long sampleRate = 5000; // 5000 millis (5 seconds)
	
	private GoogleMap map;
	private LocationClient locClient;
	private Location currentLoc;
	private LocationRequest locRequest;
	private Marker selectedMarker; // The marker the user clicked on
	private Menu tourMenu; // A handle to the options menu for the walk
	
	
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
        
        locRequest = LocationRequest.create();
        locRequest.setInterval(sampleRate);
	}

	@Override
	protected void onStart() {
		super.onStart();
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		// If Google Play services is available
		if (ConnectionResult.SUCCESS == resultCode) {
			locClient.connect();
		}
	}

	@Override
	protected void onStop() {
		locClient.removeLocationUpdates(this);
		locClient.disconnect();
		super.onStop();
	}
        
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 100){
            if (resultCode == RESULT_OK) {
                // Image captured and saved to fileUri specified in the Intent
                Toast.makeText(this, "Image saved to:\n" + data.getData(), Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
            } else {
                // Image capture failed, advise user
            }
        }
    }
	
	@Override
	public void onLocationChanged(Location location) {
		currentLoc = location;
		addLocation();
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		
	}

	@Override
	public void onConnected(Bundle bundle) {
		currentLoc = locClient.getLastLocation();
		LatLng loc = new LatLng(currentLoc.getLatitude(), currentLoc.getLongitude());
		
		WTCKeyLocation walkStart = new WTCKeyLocation(currentLoc.getLongitude(), currentLoc.getLatitude());
		walkStart.setLocName("Walk Start");
		addKeyLocation(walkStart);
		
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 15));
		locClient.requestLocationUpdates(locRequest, this);
	}

	@Override
	public void onDisconnected() {
		
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		// Make sure the user can only delete a location when they select a marker to remove.
		MenuItem removeLocItem = tourMenu.findItem(R.id.action_remLoc);
		removeLocItem.setEnabled(true);
		selectedMarker = marker;
		
		// Enable add / remove photo buttons
		MenuItem addPhoto = tourMenu.findItem(R.id.action_addPhoto);
		addPhoto.setEnabled(true);
		MenuItem removePhoto = tourMenu.findItem(R.id.action_remPhoto);
		removePhoto.setEnabled(true);
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.map, menu);
		tourMenu = menu;
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.action_addLoc:
			// Bring up the dialog to add a new KeyLocation to the tour.
			LocationDetailsFragment locDetailsFrag = new LocationDetailsFragment();
			locDetailsFrag.show(getFragmentManager(), "LocDetails");
			return true;
		case R.id.action_remLoc:
			removeLocation(selectedMarker);
			return true;
		case R.id.action_addPhoto:
			addPhoto();
			return true;
		case R.id.action_remPhoto:
			
			return true;
		case R.id.action_cancel:
			cancelWalk();
			return true;
		case R.id.action_finish:
			finishWalk();
			return true;
		case R.id.action_sample_five:
			this.setSameRate(5000);
			locRequest.setInterval(sampleRate);
			return true;
		case R.id.action_sample_ten:
			this.setSameRate(10000);
			locRequest.setInterval(sampleRate);
			return true;
		case R.id.action_sample_fifteen:
			this.setSameRate(15000);
			locRequest.setInterval(sampleRate);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public void onPositiveSelection(DialogFragment fragment) {
		LocationDetailsFragment frag = (LocationDetailsFragment) fragment;
		WTCKeyLocation keyLoc = new WTCKeyLocation(currentLoc.getLongitude(), currentLoc.getLatitude());
		keyLoc.setLocName(frag.getTfLocName().getText().toString());
		keyLoc.setLocDesc(frag.getTfLocDesc().getText().toString());
		addKeyLocation(keyLoc);
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
			if(marker.getPosition().latitude == tour.getLocations().get(i).getLatitude() &&
					marker.getPosition().longitude == tour.getLocations().get(i).getLongitude() &&
					!tour.getLocations().isEmpty()){
				tour.removeLocation(i);
				break;
			}
		}
		marker.remove();
		MenuItem removeLocItem = tourMenu.findItem(R.id.action_remLoc);
		removeLocItem.setEnabled(false);
		Toast.makeText(this, "Location Removed", Toast.LENGTH_SHORT).show();
	}
	
	public void addPhoto() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File storageDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "WTC");
        if(!storageDir.exists()){
			storageDir.mkdirs();
		}
		File photoPath = new File(storageDir.getPath() + File.separator + "test.jpg");
		
        
        startActivityForResult(intent, 100);
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
		if (networkInfo != null && networkInfo.isConnected()) {
			saveToServer();
		} else {
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
