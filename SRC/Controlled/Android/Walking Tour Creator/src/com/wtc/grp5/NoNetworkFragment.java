package com.wtc.grp5;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

public class NoNetworkFragment extends DialogFragment implements OnClickListener {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("No Network!") 
				.setMessage("You need to be connected to the internet to use this app")
				.setNegativeButton("OK...", this);
		return builder.create();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		
	}
	
}
