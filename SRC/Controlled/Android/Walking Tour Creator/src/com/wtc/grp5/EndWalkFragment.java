/*
 * @(#) EndWalkFragment.java Version 1.0
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

public class EndWalkFragment extends DialogFragment implements OnClickListener{
	
	protected WTCDialogCallbacks listener;
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		listener = (WTCDialogCallbacks) activity;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Are you sure?") //Talk to the user tell them what will happen
				.setMessage("You will lose the locations you stored!")
				.setPositiveButton("Yes", this)
				.setNegativeButton("No", this);
		return builder.create();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if(which == DialogInterface.BUTTON_POSITIVE){
			listener.onCancelWalkSelection(this);
		}
		
	}

}
