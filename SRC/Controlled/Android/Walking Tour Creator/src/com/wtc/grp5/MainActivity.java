package com.wtc.grp5;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;

import android.content.Intent;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	private Button btnNewWalk; 
	private Button btnLogin;
	private Button btnSettings;
	private Button btnAbout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar ab = getActionBar();
		ab.hide();
		btnNewWalk = (Button) findViewById(R.id.newWalk_btn);
		btnLogin = (Button) findViewById(R.id.logIn_btn);
		btnSettings = (Button) findViewById(R.id.settings_btn);
		btnAbout = (Button) findViewById(R.id.about_btn);
		
		// Make buttons respond to clicks
		btnNewWalk.setOnClickListener(this);
		btnLogin.setOnClickListener(this);
		btnSettings.setOnClickListener(this);
		btnAbout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.newWalk_btn:
			NewWalkFragment newFrag = new NewWalkFragment();
			newFrag.show(getFragmentManager(), "NewWalk");
			//Intent newWalk = new Intent(this, MapActivity.class);
			//startActivity(newWalk);
			break;
		case R.id.about_btn:
			Intent about = new Intent(this, AboutActivity.class);
			startActivity(about);
			break;
		}
		
	}

	

}
