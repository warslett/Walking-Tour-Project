/*
 * @(#) LocationDetailsFragment.java Version 1.0
 * Copyright(c) Group 5 @Aberystwyth University Computer Science Dept: Yr 2 (2014)
 * All Rights Reserved
 */

package com.wtc.grp5;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class LocationDetailsFragment extends DialogFragment implements OnClickListener{
	
	private EditText tfLocName;
	private EditText tfLocDesc;
	private WTCDialogCallbacks listener;
	
	/**
	 * Links this dialog back to the activity that created it.
	 */
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		listener = (WTCDialogCallbacks) activity;
	}
	
	/**
	 * Creates the dialog and sets its layout.
	 */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.dialog_loc_details, null);
		tfLocName = (EditText) view.findViewById(R.id.locName_tf);
		tfLocDesc = (EditText) view.findViewById(R.id.locDesc_tf);
		builder.setView(view);
		builder.setPositiveButton("OK", this);
		builder.setNegativeButton("Cancel", this);
		return builder.create();
	}
	
	/**
	 * Dictates what happens when you click a button on the dialog.
	 */
	@Override
	public void onClick(DialogInterface dialog, int which) {
		if(which == DialogInterface.BUTTON_POSITIVE){
			listener.onPositiveSelection(this);
		}
	}
	
	/**
	 * @return the text field for the location name.
	 */
	public EditText getTfLocName() {
		return tfLocName;
	}
	
	/**
	 * @return the text field for the location description.
	 */
	public EditText getTfLocDesc() {
		return tfLocDesc;
	}
	
}
