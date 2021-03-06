/*
 * @(#) SendData.java Version 1.0
 * Copyright(c) Group 5 @Aberystwyth University Computer Science Dept: Yr 2 (2014)
 * All Rights Reserved
 */

package com.wtc.grp5;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.wtc.grp5.model.WTCKeyLocation;
import com.wtc.grp5.model.WTCTour;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class SendData extends AsyncTask<String, Void, Void> {
	
	private Activity activity;
    	private WTCTour tour;
	
	/**
	 * Constructs a SendData object.
	 * 
	 * @param activity a link back to the activity that made this object.
	 * @param tour the tour being saved to the server
	 */
	public SendData(Activity activity, WTCTour tour){
		this.activity = activity;
		this.tour = tour;
	}
	
	/**
	 * Sends the tour data to the specified server.
	 * <p>The tour data and the accompanying photos are placed into a
	 * Multi-Part MIME and sent to the server using HTTP POST.</p>
	 * 
	 * @param params the list of URLs to send the tour to.
	 */
	@Override
	protected Void doInBackground(String... params) {
		try{
			tour.fixTime();
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(params[0]);
			
			MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
			
			ArrayList<NameValuePair> data = new ArrayList<NameValuePair>();
			data.add(new BasicNameValuePair("message", tour.toJSON()));
			
			for(int i = 0; i < tour.getLocations().size(); i++){
				if(tour.getLocations().get(i).getClass() == WTCKeyLocation.class){
					WTCKeyLocation keyLoc = (WTCKeyLocation) tour.getLocations().get(i);
					for(int j = 0; j < keyLoc.getPhotos().size(); j++){
						data.add(new BasicNameValuePair(keyLoc.getPhotos().get(j), keyLoc.getPhotos().get(j)));
					}
				}
			}
			
			/*
			 * This is not my code. I got this from:
			 * 
			 * URL: http://stackoverflow.com/questions/2935946/sending-images-using-http-post
			 * Credit: Piro
			 */
			for(int index=0; index < data.size(); index++) {
	            if(data.get(index).getName().equalsIgnoreCase("image")) {
	                // If the key equals to "image", we use FileBody to transfer the data
	                entity.addPart(data.get(index).getName(), new FileBody(new File(data.get(index).getValue())));
	            } else {
	                // Normal string data
	                entity.addPart(data.get(index).getName(), new StringBody(data.get(index).getValue()));
	            }
	        }

			
			post.setEntity(entity);
			client.execute(post);
		}catch(ClientProtocolException e){
			
		}catch(IOException e){
			
		}
		return null;
	}

	/**
	 * Notifies the user that the tour was saved and takes them back to the home screen.
	 */
	@Override
	protected void onPostExecute(Void result) {
		Toast.makeText(activity, "Walk saved to server", Toast.LENGTH_LONG).show();
		tour.getLocations().clear();
		tour = null;
		Intent homeScreen = new Intent(activity, MainActivity.class);
		activity.startActivity(homeScreen);
		activity.finish();
	}
	
	

}
