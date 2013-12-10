package com.wtc.grp5;

import android.os.Bundle;
import android.widget.Toast;
import android.app.Activity;

public class AboutActivity extends Activity {
	
	/**
	* Creates this activity its layout.
	*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show();
	}

}
