package com.wtc.grp5;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;

public class RecordedActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recorded);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recorded, menu);
		return true;
	}

}
