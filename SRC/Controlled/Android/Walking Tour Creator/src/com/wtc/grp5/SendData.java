package com.wtc.grp5;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;

public class SendData extends AsyncTask<String, Void, Void> {

	// Yes that is meant to be a capital V in Void.
	@Override
	protected Void doInBackground(String... params) {
		try{
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(params[0]);
			ArrayList<NameValuePair> data = new ArrayList<NameValuePair>();
			
			post.setEntity(new UrlEncodedFormEntity(data));
			HttpResponse response = client.execute(post);
		}catch(ClientProtocolException e){
			
		}catch(IOException e){
			
		}
		return null;
	}

}
