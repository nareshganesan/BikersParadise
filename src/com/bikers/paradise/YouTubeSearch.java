package com.bikers.paradise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class YouTubeSearch extends Activity {

	EditText etYoutubeSearch;
	Button bYouSearch;
	String youtubeSearchParameter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.youtube);
		etYoutubeSearch = (EditText) findViewById(R.id.etYoutubeSearch);
		bYouSearch = (Button) findViewById(R.id.bYouSearch);
		bYouSearch.setOnClickListener(new View.OnClickListener() {
			
			 @Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 youtubeSearchParameter = etYoutubeSearch.getText().toString();
				Bundle displayYoutubeData = new Bundle();
				displayYoutubeData.putString("key", youtubeSearchParameter);
				Intent callDispSearchLocation = new Intent(YouTubeSearch.this,DisplayYoutubeSearch.class);
				callDispSearchLocation.putExtras(displayYoutubeData);
				startActivity(callDispSearchLocation);
				
			}
		});
		
	}

}
