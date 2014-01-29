package com.wtc.grp5;

import android.content.DialogInterface;

public class FinishWalkFragment extends EndWalkFragment {

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if(which == DialogInterface.BUTTON_POSITIVE){
			listener.onFinishWalkSelection(this);
		}
		
	}
}
