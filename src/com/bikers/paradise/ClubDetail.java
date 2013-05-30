package com.bikers.paradise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ClubDetail extends Activity {

	EditText etClubDetailUserData;
	Button bclubsearch;
	String SearchParameter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clubdetail);
		etClubDetailUserData = (EditText) findViewById(R.id.etClubDetailUserData);
		bclubsearch = (Button) findViewById(R.id.bClubSearch);
		bclubsearch.setOnClickListener(new View.OnClickListener() {
			
			 @Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SearchParameter = etClubDetailUserData.getText().toString();
				Bundle displayClubData = new Bundle();
				displayClubData.putString("key", SearchParameter);
				Intent callDispClubDetail = new Intent(ClubDetail.this,DisplayClubDetail.class);
				callDispClubDetail.putExtras(displayClubData);
				startActivity(callDispClubDetail);
				
			}
		});
		
	}

}
