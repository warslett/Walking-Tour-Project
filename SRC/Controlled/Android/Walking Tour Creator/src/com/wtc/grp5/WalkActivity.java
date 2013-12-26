package com.wtc.grp5;

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
import android.util.Log;
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
			Log.d("WILLIAM", "Google Play services is available.");
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
	public void onLocationChanged(Location location) {
		currentLoc = location;
		Log.d("WILLIAM", Double.toString(location.getLatitude()) + " " + Double.toString(location.getLongitude()));
		addLocation();
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		
	}

	@Override
	public void onConnected(Bundle bundle) {
		Log.d("WILLIAM", "location client connected");
		currentLoc = locClient.getLastLocation();
		// LatLng loc = new LatLng(52.4140, -4.0810);
		LatLng loc = new LatLng(currentLoc.getLatitude(),
		currentLoc.getLongitude());
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 15));
		map.addMarker(new MarkerOptions()
			.title(tour.getTourName())
			.snippet(tour.getShortDesc())
			.position(loc));
		locClient.requestLocationUpdates(locRequest, this);
	}

	@Override
	public void onDisconnected() {
		
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.map, menu);
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
	* @param l the location being removed from the tour.
	*/
	public void removeLocation() {
		// The argument will be re-added when Will L is resolves type conflict
	}
	
	/**
	* Saves the tour to the server.
	*/
	private void saveToServer() {
		String[] data = {"http://users.aber.ac.uk/wia2/WTC/upload.php"};
		new SendData(this).execute(data);
	}
	
	/**
	* Stops the recording of the and deletes the data.
	*/
	public void cancelWalk() {
	
	}
	
	/**
	* Adds a stopping location to the tour and stops the recording.
	*/
	public void finishWalk() {
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
