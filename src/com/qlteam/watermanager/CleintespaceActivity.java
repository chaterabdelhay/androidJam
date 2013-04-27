package com.qlteam.watermanager;

import greendroid.app.GDActivity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qlteam.watermanager.lib.JSONParser;

public class CleintespaceActivity extends GDActivity {
	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_USER = "user";
	private static final String TAG_ID = "id";
	private static final String TAG_IDENTF = "identifient";
	private static final String TAG_PASS = "password";
	private ProgressDialog pDialog;
	TextView txtName;
	EditText txtpassword;
	Button btnlogin;
	String identifient,password;
	// JSON parser class
	JSONParser jsonParser = new JSONParser();

	// single product url
	private static final String url_usrs = "http://annonceapp.hebergratuit.com/androidJAM/get_users.php";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setActionBarContentView(R.layout.cleintespace_layout);
		txtName =(TextView)findViewById(R.id.logintest);
		Intent i = getIntent();

		// getting product id (pid) from intent
		identifient = i.getStringExtra("identifient");
		password = i.getStringExtra("identifient");
		setActionBarContentView(R.layout.cleintespace_layout);
		new GetUsers().execute();
	}

	/**
	 * Background Async Task to Get complete product details
	 * */
	class GetUsers extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(CleintespaceActivity.this);
			pDialog.setMessage("Loading product details. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Getting product details in background thread
		 * */
		protected String doInBackground(String... params) {

			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					// Check for success tag
					int success;
					try {
						// Building Parameters
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						params.add(new BasicNameValuePair("clientid",
								identifient));
						params.add(new BasicNameValuePair("password",
								password));


						// getting product details by making HTTP request
						// Note that product details url will use GET request
						JSONObject json = jsonParser.makeHttpRequest(url_usrs,
								"GET", params);

						// check your log for json response
						Log.d("Single Product Details", json.toString());

						// json success tag
						success = json.getInt(TAG_SUCCESS);
						if (success == 1) {
							// successfully received product details
							JSONArray productObj = json.getJSONArray(TAG_USER); // JSON
																				// Array

							// get first product object from JSON Array
							JSONObject product = productObj.getJSONObject(0);

							
							
							// display product data in EditText
							txtName.setText(product.getString(TAG_IDENTF));
						

						} else {
							// product with pid not found
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once got all details
			pDialog.dismiss();
		}
	}

}
