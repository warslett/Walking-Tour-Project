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

	public interface LocationDetailsListener{
		public void onPositiveSelection(DialogFragment fragment);
	}
	
	private EditText tfLocName;
	private EditText tfLocDesc;
	private LocationDetailsListener listener;
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		listener = (LocationDetailsListener) activity;
	}
	
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

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if(which == DialogInterface.BUTTON_POSITIVE){
			listener.onPositiveSelection(this);
		}
	}

	public EditText getTfLocName() {
		return tfLocName;
	}

	public EditText getTfLocDesc() {
		return tfLocDesc;
	}
	
}
