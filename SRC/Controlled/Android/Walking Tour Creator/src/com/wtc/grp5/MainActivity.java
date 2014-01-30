/*
 * @(#) MainActivity.java Version 1.0
 * Copyright(c) Group 5 @Aberystwyth University Computer Science Dept: Yr 2 (2014)
 * All Rights Reserved
 */

package com.wtc.grp5;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
 	
	private Button btnNewWalk; 
	private Button btnAbout;
	
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
		
		// Make buttons respond to clicks
		btnNewWalk.setOnClickListener(this);
		btnAbout.setOnClickListener(this);
	}

	/**
	* Dictates what what happens when the user clicks a button.
	*/
	@Override
	public void onClick(View view) {
		switch(view.getId()){
		case R.id.newWalk_btn:
			NewWalkFragment newFrag = new NewWalkFragment();
			newFrag.show(getFragmentManager(), "NewWalk");
			break;
		case R.id.about_btn:
			Intent about = new Intent(this, AboutActivity.class);
			startActivity(about);
			break;
		}
		
	}

}
