package com.bikers.paradise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DisplayClubDetail extends Activity {

	TextView searchresponse;
	InputStream is;
	String result = "";
	String UserQuery;
	String searchKeyword;
	StringBuilder sb = new StringBuilder();
	String line = null;
	StringBuffer stringBuffer = new StringBuffer();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dispclubdetail);
		searchresponse = (TextView) findViewById(R.id.searchResult);
		Bundle getPrevBundleData = getIntent().getExtras();
		UserQuery = getPrevBundleData.getString("key");
		//searchresponse.setText(UserQuery);
		StringTokenizer st = new StringTokenizer(UserQuery," ",false);
		  String trimmedQuery="";
		  while (st.hasMoreElements())
			  {
			  trimmedQuery += st.nextElement()+"%20";
			  
			  }
		  int queryLangth = trimmedQuery.length();
		  if(trimmedQuery.endsWith("%20"))
		  {
			  trimmedQuery = trimmedQuery.substring(0, queryLangth-3);
		  }
		 
		  Log.v(trimmedQuery, "User Query");
		searchKeyword = "http://128.196.27.201/biker.php?searchtext=" +trimmedQuery;
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
		JSONArray jsonInput = new JSONArray(result);
		String Name_of_Club = "";
		String State = "";
		String Club_Image = "";
		String Club_URL = "";
		for(int i = 0 ; i < jsonInput.length(); i ++)
		{
			Name_of_Club = jsonInput.getJSONObject(i).getString("Name_of_Club").toString();
			State = jsonInput.getJSONObject(i).getString("State").toString();
			Club_Image = jsonInput.getJSONObject(i).getString("Club_Image").toString();
			Club_URL = jsonInput.getJSONObject(i).getString("Club_URL").toString();
			if(!Name_of_Club.equalsIgnoreCase("") || !Name_of_Club.equals(null) || !(Name_of_Club == null))
			{
				stringBuffer.append("Club Name :"+Name_of_Club+"  \n ");
			}
			if(!State.equalsIgnoreCase("") || !State.equals(null) || !(State == null))
			{
				stringBuffer.append("State :"+State+"\n");
			}
			if(!Club_Image.equalsIgnoreCase("") || !Club_Image.equals(null) || !(Club_Image == null))
			{
				stringBuffer.append(" Club Image :"+ Club_Image+"\n");
			}
			if(!Club_URL.equalsIgnoreCase("") || !Club_URL.equals(null) || !(Club_URL == null))
			{
				stringBuffer.append("Club URL :"+Club_URL + "\n" + "\n");
			}
			
		}
		searchresponse.setText(stringBuffer);
		
		//searchresponse.setText(result);
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
