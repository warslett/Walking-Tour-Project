package com.wtc.grp5;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class SendData extends AsyncTask<String, Void, Void> {
	
	private Context context;
	
	public SendData(Context context){
		this.context = context;
	}
	
	// Yes that is meant to be a capital V in Void.
	@Override
	protected Void doInBackground(String... params) {
		try{
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(params[0]);
			Log.d("WILLIAM", "Client and POST setup");
			ArrayList<NameValuePair> data = new ArrayList<NameValuePair>();
			data.add(new BasicNameValuePair("message", "Hello World"));
			Log.d("WILLIAM", "Data specified");
			post.setEntity(new UrlEncodedFormEntity(data));
			Log.d("WILLIAM", "Entity set");
			client.execute(post);
			Log.d("WILLIAM", "Data sent");
		}catch(ClientProtocolException e){
			
		}catch(IOException e){
			
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		Toast.makeText(context, "Tour sent to server", Toast.LENGTH_LONG).show();
	}
	
	

}
