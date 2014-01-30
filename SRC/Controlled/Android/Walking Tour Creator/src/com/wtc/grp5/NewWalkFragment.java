/*
 * @(#) NewWalkFragment.java Version 1.0
 * Copyright(c) Group 5 @Aberystwyth University Computer Science Dept: Yr 2 (2014)
 * All Rights Reserved
 */

package com.wtc.grp5;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;

public class NewWalkFragment extends DialogFragment implements OnClickListener{
		
	private EditText tfTourName;
	private EditText tfShortDesc;
	private EditText tfLongDesc;

	/**
	* Creates the dialog box that collects the details for the walk.
	*/
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.dialog_new_walk, null);
		tfTourName = (EditText) view.findViewById(R.id.walkTitle_tf);
		tfShortDesc = (EditText) view.findViewById(R.id.walkShortDesc_tf);
		tfLongDesc = (EditText) view.findViewById(R.id.walkLongDesc_tf);
		builder.setView(view);
		// Set the button that will continue to WalkActivity
		builder.setPositiveButton("Begin...", this);
		// Set the cancel button
		builder.setNegativeButton("Cancel", this);
		
		return builder.create();
	}

	/**
	* Dictates what happens when the user clicks the positive or negative button on the
	* dialog.
	*/
	@Override
	public void onClick(DialogInterface dialog, int which) {
		if(which == DialogInterface.BUTTON_POSITIVE){
			if(tfTourName.getText().toString().equals("")){
				Toast.makeText(getActivity(), "Enter a Walk Name", Toast.LENGTH_SHORT).show();
			}else if(tfTourName.getText().toString().contains(" ")){
				Toast.makeText(getActivity(), "Walk Name may not contain spaces", Toast.LENGTH_LONG).show();
			}else{
				Intent intent = new Intent(this.getActivity(), WalkActivity.class);
				intent.putExtra("TourTitle", tfTourName.getText().toString());
				intent.putExtra("TourShortDesc", tfShortDesc.getText().toString());
				intent.putExtra("TourLongDesc", tfLongDesc.getText().toString());
				startActivity(intent);
				dismiss();
			}
		}else if(which == DialogInterface.BUTTON_NEGATIVE){
			dismiss();
		}
		
	}
	
}
