package com.wtc.grp5;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.wtc.grp5.model.WTCTour;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

public class SendData extends AsyncTask<String, Void, Void> {
	
	private Activity activity;
    private WTCTour tour;
    private final String boundary = "part"
	
	public SendData(Activity activity, WTCTour tour){
		this.activity = activity;
        this.tour = tour;
	}
	
	// Yes that is meant to be a capital V in Void.
	@Override
	protected Void doInBackground(String... params) {
		try{

			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(params[0]);
            post.addHeader("Content-Type", "multipart/mixed; boundary=" + boundary);

			ArrayList<NameValuePair> data = new ArrayList<NameValuePair>();
			data.add(new BasicNameValuePair("message", tour.toJSON()));

            for(int i = 0; i < tour.getLocations.size(); i++){
                if(tour.getLocations.get(i).getClass() == WTCKeyLocation.class){
                    WTCKeyLocation keyLoc = (WTCKeyLocation) tour.getLocations.get(i);
                    if(!keyLoc.getPhotos().isEmpty()){}
                }
            }

			post.setEntity(new UrlEncodedFormEntity(data));
			client.execute(post);
		}catch(ClientProtocolException e){
			
		}catch(IOException e){
			
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		Toast.makeText(activity, "Tour sent to server", Toast.LENGTH_LONG).show();
		tour.getLocations().clear();
		tour = null;
		Intent homeScreen = new Intent(activity, MainActivity.class);
		activity.startActivity(homeScreen);
		activity.finish();
	}
	
	

}
