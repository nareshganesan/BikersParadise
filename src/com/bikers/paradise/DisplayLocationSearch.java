package com.bikers.paradise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class DisplayLocationSearch extends MapActivity {

	MapView searchresponse;
	InputStream is;
	String result = "";
	String UserQuery;
	String searchKeyword;
	StringBuilder sb = new StringBuilder();
	String line = null;
	StringBuffer stringBuffer = new StringBuffer();
	String APIKEY;
	String Lat = "";
	String Long = "";
	TextView textLat;
	TextView textLong;
	HelloItemizedOverlay itemizedoverlay;
	List<Overlay> locationOverlay;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.displaylocations);
		searchresponse = (MapView) findViewById(R.id.mvLocations);
		searchresponse.setBuiltInZoomControls(true);
		
		locationOverlay = searchresponse.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.pin);
	    itemizedoverlay = new HelloItemizedOverlay(drawable, this);
	    
		Bundle getPrevBundleData = getIntent().getExtras();
		UserQuery = getPrevBundleData.getString("key");
		//searchresponse.setText(UserQuery);
		APIKEY = "AIzaSyBO6rY2AK0yGlCSNGqNa0L0VvpVmGzMbhY";
       LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
       LocationListener ll = new myLocationListener();
       lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 1, ll);

	  
	}
	 class myLocationListener implements LocationListener 
	    {

			@Override
			public void onLocationChanged(Location location) 
			{
				// TODO Auto-generated method stub
				if(location !=  null){
					double pLong = location.getLongitude();
					double pLat = location.getLatitude();
					Lat = Double.toString(pLat);
					Long = Double.toString(pLong);
					callGooglePlaces();
				
			  
					
				}
				
			}

			@Override
			public void onProviderDisabled(String arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onProviderEnabled(String arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
				// TODO Auto-generated method stub
				
			}
	    	
	    }
	 public  void callGooglePlaces()
	 {
	    	if((Lat != null || Lat != "") && (Long != null || Long != "" ))
	        {
	        	Log.v(Lat,"Latitude");
				Log.v(Long,"Longitude");
				Log.v(UserQuery,"UserQuery");
				StringTokenizer st = new StringTokenizer(UserQuery," ",false);
				  String trimmedQuery="";
				  while (st.hasMoreElements())
					  {
					  trimmedQuery += st.nextElement()+"%20";
					  
					  }
	        	searchKeyword =  "https://maps.googleapis.com/maps/api/place/search/json?location="+Lat+","+Long+"&rankby=distance&sensor=false&key=AIzaSyBO6rY2AK0yGlCSNGqNa0L0VvpVmGzMbhY&keyword="+trimmedQuery;
                System.out.println(Lat+"Lat and Long Value"+Long);
	        	Log.e("LAT & LONG Null","location passed");
	        	Log.v(Lat,"Latitude Value");
	        	Log.v(Long,"Longitude Value UserQuery.trim()");
	        	Log.v(UserQuery.trim(),"Trimmed Value ");
	        }
	        else{
	        	searchKeyword = "https://maps.googleapis.com/maps/api/place/search/json?location=-33.8670522,151.1957362&rankby=distance&sensor=false&key=AIzaSyBO6rY2AK0yGlCSNGqNa0L0VvpVmGzMbhY&keyword=subway";
	        	Log.e("LAT & LONG not Null","location not passed");
	        }
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(searchKeyword);
		try {
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
			
			while((line = reader.readLine()) != null) 
			{
				sb.append(line + "\n");
			}
			is.close();
			result=sb.toString();
			
			JSONObject jsonInput = new JSONObject(result);
			JSONArray locationResults = jsonInput.getJSONArray("results");
			String placeLat = "";
			String placeLong = "";
			String placeName = "";
			String placeAddress = "";
			for(int i = 0 ; i < locationResults.length(); i ++)
			{
				placeLat = locationResults.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getString("lat");
				placeLong = locationResults.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getString("lng");
				//stringBuffer.append(locationResults.getJSONObject(i).getString("name").toString() +" \n  ");
				placeName = locationResults.getJSONObject(i).getString("name").toString();
				placeAddress = locationResults.getJSONObject(i).getString("vicinity").toString();
				
				GeoPoint point = new GeoPoint((int)(Double.valueOf(placeLat) *1e6), (int)(Double.valueOf(placeLong) *1e6));
			    OverlayItem overlayitem = new OverlayItem(point, placeName,placeAddress);
			    itemizedoverlay.addOverlay(overlayitem);
//				stringBuffer.append(locationResults.getJSONObject(i).getString("name").toString() +" \n  ");
//				stringBuffer.append(locationResults.getJSONObject(i).getString("vicinity").toString()+"\n");
//				stringBuffer.append(placeLat+"    "+placeLong+"\n");
//				stringBuffer.append(locationResults.getJSONObject(i).getString("icon").toString()+"\n"+" \n");
			}
			locationOverlay.add(itemizedoverlay);
			//searchresponse.setText(stringBuffer);
			//textLong.setText(result);
			
			//searchresponse.setText(result);
			} catch(Exception e){
				e.printStackTrace();
			}
	    }
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
