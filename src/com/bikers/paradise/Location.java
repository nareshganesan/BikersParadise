package com.bikers.paradise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Location extends Activity {
	EditText etLocation;
	Button bsearchLocation;
	String locSearchParameter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location);
		etLocation = (EditText) findViewById(R.id.etLocation);
		bsearchLocation = (Button) findViewById(R.id.bsearchLocation);
		bsearchLocation.setOnClickListener(new View.OnClickListener() {
			
			 @Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 locSearchParameter = etLocation.getText().toString();
				Bundle displayClubData = new Bundle();
				displayClubData.putString("key", locSearchParameter);
				Intent callDispSearchLocation = new Intent(Location.this,GoogleLocations.class);
				callDispSearchLocation.putExtras(displayClubData);
				startActivity(callDispSearchLocation);
				
			}
		});
	}
	

}
