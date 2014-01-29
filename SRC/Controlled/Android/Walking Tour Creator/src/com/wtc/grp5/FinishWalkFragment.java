package com.wtc.grp5;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class FinishWalkFragment extends EndWalkFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Are you sure?")
				.setMessage("Your walk will saved online.")
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
