package com.bikers.paradise;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

class myLocationListener implements LocationListener{

	public static  String Lat = "";
	public static String Long = "";
	public static String getLat() {
		return Lat;
	}

	public static void setLat(String lat) {
		Lat = lat;
	}

	public static String getLong() {
		return Long;
	}

	public static void setLong(String l) {
		Long = l;
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		if(location !=  null){
			double pLong = location.getLongitude();
			double pLat = location.getLatitude();
			
			//Lat = Double.toString(pLat);
			//Long = Double.toString(pLong);
			myLocationListener.setLat(Double.toString(pLat));
			myLocationListener.setLong(Double.toString(pLong));
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
