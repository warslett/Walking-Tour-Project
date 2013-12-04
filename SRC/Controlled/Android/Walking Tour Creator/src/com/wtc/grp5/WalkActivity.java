package com.wtc.grp5;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.app.Activity;

public class WalkActivity extends Activity {

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


}
