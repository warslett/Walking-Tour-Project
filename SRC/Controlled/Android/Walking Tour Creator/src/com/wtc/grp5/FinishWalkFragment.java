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

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Are you sure?") //Get conformation
				.setMessage("Your walk will be saved online.")//Tell user what will happen
				.setPositiveButton("Yes", this)
				.setNegativeButton("No", this);
		return builder.create();
	}
	
	@Override
	public void onClick(DialogInterface dialog, int which) {
		if(which == DialogInterface.BUTTON_POSITIVE){
			listener.onFinishWalkSelection(this);
		}
		
	}
}
