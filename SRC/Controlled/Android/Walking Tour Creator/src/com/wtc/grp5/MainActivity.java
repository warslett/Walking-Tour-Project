/*
 * @(#) MainActivity.java Version 1.0
 * Copyright(c) Group 5 @Aberystwyth University Computer Science Dept: Yr 2 (2014)
 * All Rights Reserved
 */

package com.wtc.grp5;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener, LocationListener {
 	
	private Button btnNewWalk; 
	private Button btnAbout;
	
	LocationManager locationManager;
	
	/**
	* Creates this activity and sets its layout. It also initializes the buttons and sets
	* their listeners.
	*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnNewWalk = (Button) findViewById(R.id.newWalk_btn);
		btnAbout = (Button) findViewById(R.id.about_btn);
		btnNewWalk.setOnClickListener(this);
		btnAbout.setOnClickListener(this);
		
		locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
	}
	
	@Override
	protected void onStop(){
		locationManager.removeUpdates(this);
		super.onStop();
	}

	/**
	* Dictates what what happens when the user clicks a button.
	*/
	@Override
	public void onClick(View view) {
		switch(view.getId()){
		case R.id.newWalk_btn:
			ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
			if(networkInfo != null && networkInfo.isConnected()){
				NewWalkFragment newFrag = new NewWalkFragment();
				newFrag.show(getFragmentManager(), "NewWalk");
			}else{
				NoNetworkFragment noNetFrag = new NoNetworkFragment();
				noNetFrag.show(getFragmentManager(), "NoNetwork");
			}
			break;
		case R.id.about_btn:
			Intent about = new Intent(this, AboutActivity.class);
			startActivity(about);
			break;
		}
		
	}

	@Override
	public void onLocationChanged(Location location) {
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		if(provider.equals(LocationManager.GPS_PROVIDER)){
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		}else if(provider.equals(LocationManager.NETWORK_PROVIDER)){
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
		}
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		
	}

}