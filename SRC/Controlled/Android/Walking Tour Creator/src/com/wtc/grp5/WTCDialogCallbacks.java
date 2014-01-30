/*
 * @(#) WTCDialogCallbacks.java Version 1.0
 * Copyright(c) Group 5 @Aberystwyth University Computer Science Dept: Yr 2 (2014)
 * All Rights Reserved
 */

package com.wtc.grp5;

import android.app.DialogFragment;

public interface WTCDialogCallbacks {

	public void onPositiveSelection(DialogFragment fragment);
	
	public void onCancelWalkSelection(DialogFragment fragment);
	
	public void onFinishWalkSelection(DialogFragment fragment);
}
