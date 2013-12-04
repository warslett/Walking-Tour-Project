package com.wtc.grp5;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.os.Bundle;

public class NewWalkFragment extends DialogFragment implements OnClickListener{

	public static final String WALK_TITLE = "com.wtc.grp5.WALK_TITLE";
	
	private EditText tfTourName;
	private EditText tfShortDesc;
	private EditText tfLongDesc;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		
		builder.setView(inflater.inflate(R.layout.dialog_new_walk, null));
		
		// Set the button that will continue to WalkActivity
		builder.setPositiveButton("Begin...", this);
		
		// Set the cancel button
		builder.setNegativeButton("Cancel", this);
		
		tfTourName = (EditText) getActivity().findViewById(R.id.walkTitle_tf);
		tfShortDesc = (EditText) getActivity().findViewById(R.id.walkShortDesc_tf);
		tfLongDesc = (EditText) getActivity().findViewById(R.id.walkLongDesc_tf);
		return builder.create();
	}


	@Override
	public void onClick(DialogInterface dialog, int which) {
		if(which == DialogInterface.BUTTON_POSITIVE){
			Intent newWalk = new Intent(this.getActivity(), WalkActivity.class);
			startActivity(newWalk);
			dismiss();
		}
		
	}



	
	
}
