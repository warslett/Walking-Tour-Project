package com.wtc.grp5;

import android.app.DialogFragment;

public interface WTCDialogCallbacks {

	public void onPositiveSelection(DialogFragment fragment);
	
	public void onCancelWalkSelection(DialogFragment fragment);
	
	public void onFinishWalkSelection(DialogFragment fragment);
}
