/*
 * @(#) WTCDialogCallbacks.java Version 1.0
 * Copyright(c) Group 5 @Aberystwyth University Computer Science Dept: Yr 2 (2014)
 * All Rights Reserved
 */

package com.wtc.grp5;

import android.app.DialogFragment;

public interface WTCDialogCallbacks {

	/**
	 * This method is used when the user clicks the positive button on a dialog.
	 * 
	 * @param fragment the DialogFragment that made this callback.
	 */
	public void onPositiveSelection(DialogFragment fragment);
	
	/**
	 * This method is to be called when the user clicks the positive button
	 * on the CancelWalkFragment.
	 * 
	 * @param fragment the DialogFragment that made this callback.
	 */
	public void onCancelWalkSelection(DialogFragment fragment);
	
	/**
	 * This method is to be called when the user clicks the positvite button
	 * on the FinishWalkFragment.
	 * 
	 * @param fragment the DialogFragment that made this callback.
	 */
	public void onFinishWalkSelection(DialogFragment fragment);
}
