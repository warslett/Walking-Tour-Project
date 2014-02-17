/*
 * @(#) FinishWalkFragment.java Version 1.0
 * Copyright(c) Group 5 @Aberystwyth University Computer Science Dept: Yr 2 (2014)
 * All Rights Reserved
 */

package com.wtc.grp5;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class FinishWalkFragment extends EndWalkFragment {
	
	/**
	 * Creates the dialog and sets its layout.
	 */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Are you sure?")
				.setMessage("Your walk will be saved online.")
				.setPositiveButton("Yes", this)
				.setNegativeButton("No", this);
		return builder.create();
	}
	
	/**
	 * Dictates what happens when you click a button on the dialog.
	 */
	@Override
	public void onClick(DialogInterface dialog, int which) {
		if(which == DialogInterface.BUTTON_POSITIVE){
			listener.onFinishWalkSelection(this);
		}
		
	}
}
