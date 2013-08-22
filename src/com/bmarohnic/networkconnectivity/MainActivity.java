package com.bmarohnic.networkconnectivity;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.bmarohnic.lib.FileStuff;
import com.bmarohnic.lib.WebStuff;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	Context _context;
	LinearLayout _appLayout;
	SearchForm _search;
	DealDisplay _deal;
	FavDisplay _favorites;
	Boolean _connected = false;
	HashMap<String, String> _history;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		_context = this;
		_appLayout = new LinearLayout(this);
		_history = new HashMap<String, String>();
		
		_search = new SearchForm(_context, "Enter Your Zip Code", "Search");
		
		
		// Add search handler
		
		Button searchButton = _search.getButton();
		
		searchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("Click Handler", _search.getField().getText().toString());
				getDeal(_search.getField().getText().toString());
			}
		});
		
		// Detect network connection
		_connected = WebStuff.getConnectionStatus(_context);
		if(_connected){
			Log.i("Newwork Connection", WebStuff.getConnectionType(_context));
		}
		
		
		// Add deal display
		_deal = new DealDisplay(_context);
		
		// Add favorites display
		_favorites = new FavDisplay(_context);
		
		// Add views to main layout
		_appLayout.addView(_search);
		_appLayout.addView(_deal);
		_appLayout.addView(_favorites);
		
		_appLayout.setOrientation(LinearLayout.VERTICAL);
		
		setContentView(_appLayout);
		
		
	}

	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void getDeal(String zipCode){
		Log.i("Click", zipCode);
		String baseURL = "http://api.8coupons.com/v1/getdeals?key=67714ceb7f857482a7f3e890ae52a8730c7d60663de10661e527d93a9236c547a1c5c3d15f1cb29e6aa3430a54a2091b&zip=" + zipCode + "&categoryid=1";
		String yql = "";
		@SuppressWarnings("unused")
		String qs;
		try{
			qs = URLEncoder.encode(yql, "UTF-8");
		} catch(Exception e){
			Log.e("Bad URL", "Encoding Problem");
			qs = "";
		}
		URL finalURL;
		try{
			finalURL = new URL(baseURL + "&format=json");
			DealRequest dr = new DealRequest();
			dr.execute(finalURL);
			
		} catch (MalformedURLException e){
			Log.e("Bad URL", "Malformed URL");
			finalURL = null;
		}
	}
	
	private class DealRequest extends AsyncTask<URL, Void, String>{
		
		@Override
		protected String doInBackground(URL... urls){
			String response = "";
			for(URL url: urls){
				response = WebStuff.getURLStringResponse(url);
			}
			return response;
		}
		
		@Override
		protected void onPostExecute(String result){
			Log.i("URL Response", result);
			
			try{
				JSONArray inputArray = new JSONArray(result);
				JSONObject jo = inputArray.getJSONObject(0);
				Log.i("Value for key affiliate:", jo.getString("affiliate"));
				
				_history.put(jo.getString("name"), jo.toString());
				FileStuff.storeObjectFile(_context, "history", _history, false);
				FileStuff.storeStringFile(_context, "temp", jo.toString(), true);
				
				_deal._name.setText(jo.getString("name"));
				_deal._dealSource.setText(jo.getString("dealSource"));
				_deal._address.setText(jo.getString("address"));
				_deal._address2.setText(jo.getString("address2"));
				_deal._city.setText(jo.getString("city"));
				_deal._state.setText(jo.getString("state"));
				_deal._ZIP.setText(jo.getString("ZIP"));
				_deal._dealTitle.setText(jo.getString("dealTitle"));
				_deal._dealinfo.setText(jo.getString("dealinfo"));
				_deal._postDate.setText(jo.getString("postDate"));
				_deal._expirationDate.setText(jo.getString("expirationDate"));
				
			} catch (JSONException e){
				Log.e("JSON","My JSON Object Exception");
			}
			
			/*
			try{
				JSONObject json = new JSONObject(result);
				JSONObject results = json.getJSONObject("query").getJSONObject("results").getJSONObject("row");
				
				if(results.getString("col1").compareTo("N/A") == 0) {
					Toast toast = Toast.makeText(_context, "Invalid ZIP", Toast.LENGTH_SHORT);
						toast.show();
						
					} else {
						Toast toast = Toast.makeText(_context, "Valid ZIP" + results.getString("ZIP"), Toast.LENGTH_SHORT);
							toast.show();
				}
			} catch (JSONException e){
				Log.e("JSON", "JSON Object Exception");
			}*/
		}
	}
}
