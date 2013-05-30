package com.bikers.paradise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TripDetail extends Activity {

	EditText etTripDetails;
	Button bTripSearch;
	String tripSearchParameter = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tripdetails);
		etTripDetails = (EditText) findViewById(R.id.etTripSearch);
		bTripSearch = (Button) findViewById(R.id.bTripSearch);
		bTripSearch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tripSearchParameter = etTripDetails.getText().toString();
				Bundle displayTripData = new Bundle();
				displayTripData.putString("key", tripSearchParameter);
				Intent callDispTripDetail = new Intent(TripDetail.this,DisplayTripDetails.class);
				callDispTripDetail.putExtras(displayTripData);
				startActivity(callDispTripDetail);
				
			}
		});
	}

}
