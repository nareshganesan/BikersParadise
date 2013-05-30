package com.bikers.paradise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BikersParadiseActivity extends Activity {
	/** Called when the activity is first created. */
	Button bClubDetail;
	Button bTripDetails;
	Button bGoogleLocations;
	Button bYouTube;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bClubDetail = (Button) findViewById(R.id.bClubDetail);
		bTripDetails = (Button) findViewById(R.id.bTripDetail);
		bGoogleLocations = (Button) findViewById(R.id.bGoogleLocations);
		//bYouTube = (Button) findViewById(R.id.bYouTube);

		bClubDetail.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent callClubDetail = new Intent(BikersParadiseActivity.this,ClubDetail.class);
				startActivity(callClubDetail);

			}
		});
		
		bTripDetails.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent callTripDetail = new Intent(BikersParadiseActivity.this,TripDetail.class);
				startActivity(callTripDetail);
				
			}
		});
		
		bGoogleLocations.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent callGoogleLocation = new Intent (BikersParadiseActivity.this, Location.class);
				startActivity(callGoogleLocation);
				
			}
		});
		
//		bYouTube.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent callYoutubeSearch = new Intent (BikersParadiseActivity.this, YouTubeSearch.class);
//				startActivity(callYoutubeSearch);
//				
//			}
//		});

	}
}