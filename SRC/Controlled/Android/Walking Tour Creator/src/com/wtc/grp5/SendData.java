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
			URL url = new URL(params[0]);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.connect();
			String s = "Hello World!";
			OutputStream out = new BufferedOutputStream(conn.getOutputStream());
			out.write(s.getBytes());
			out.close();
		}catch(IOException e){
			
		}
		return null;
	}

}
