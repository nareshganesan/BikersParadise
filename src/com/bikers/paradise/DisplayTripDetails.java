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

public class DisplayTripDetails extends Activity {

	TextView tvDisplayTripDetail;
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
		setContentView(R.layout.displaytripdetails);
		tvDisplayTripDetail = (TextView) findViewById(R.id.tvDisplayTripDetail);
		Bundle getPrevBundleData = getIntent().getExtras();
		UserQuery = getPrevBundleData.getString("key");
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
		searchKeyword = "http://128.196.27.201/biketour.php?searchtext=" +trimmedQuery;
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
			
			//SELECT Name_of_Tour, Tour_Description, Type_of_Tour, Trip_Difficulty, Price, Event_Image
			//FROM biking_information 
		result=sb.toString();
		JSONArray jsonInput = new JSONArray(result);
		String Name_of_Tour = "";
		String Start_Point = "";
		String Type_of_Tour = "";
		String Trip_Difficulty = "";
		String Price = "";
		String Event_Image  = "";
		String State = "";
		String Name_of_Club = "";
		for(int i = 0 ; i < jsonInput.length(); i ++)
		{
			Name_of_Club = jsonInput.getJSONObject(i).getString("Name_of_Club").toString();
			State = jsonInput.getJSONObject(i).getString("State").toString();
			Name_of_Tour = jsonInput.getJSONObject(i).getString("Name_of_Tour").toString();
			Start_Point = jsonInput.getJSONObject(i).getString("Start_Point").toString();
			Type_of_Tour = jsonInput.getJSONObject(i).getString("Type_of_Tour").toString();
			Trip_Difficulty = jsonInput.getJSONObject(i).getString("Trip_Difficulty").toString();
			Price = jsonInput.getJSONObject(i).getString("Price").toString();
			Event_Image = jsonInput.getJSONObject(i).getString("Event_Image").toString();
			if(!Name_of_Club.equalsIgnoreCase("") || Name_of_Club.equals(null) || Name_of_Club == null)
			{
				stringBuffer.append("Name of Club :"+Name_of_Club +"  \n ");
			}
			if(!State.equalsIgnoreCase("") || State.equals(null) || State == null)
			{
				stringBuffer.append("State :"+State +"  \n ");
			}
			if(!Name_of_Tour.equalsIgnoreCase("") || Name_of_Tour.equals(null) || Name_of_Tour == null)
			{
				stringBuffer.append("Name of Tour :"+Name_of_Tour +"  \n ");
			}
			if(!Start_Point.equalsIgnoreCase("") || Start_Point.equals(null) || Start_Point == null)
			{
				stringBuffer.append("Tour Description :"+Start_Point+"\n");
			}
			if(!Type_of_Tour.equalsIgnoreCase("") || Type_of_Tour.equals(null) || Type_of_Tour == null)
			{
				stringBuffer.append(" Type of Tour :"+ Type_of_Tour+"\n");
			}
			if(!Trip_Difficulty.equalsIgnoreCase("") || Trip_Difficulty.equals(null) || Trip_Difficulty == null)
			{
				stringBuffer.append("Trip Difficulty :"+Trip_Difficulty + "\n" + "\n");
			}
			if(!Price.equalsIgnoreCase("") || Price.equals(null) || Price == null)
			{
				stringBuffer.append("Price :"+Price + "\n" + "\n");
			}
//			if(!Event_Image.equalsIgnoreCase("") || Event_Image.equals(null) || Event_Image == null)
//			{
//				stringBuffer.append("Event Image :"+Event_Image + "\n" + "\n");
//			}
			
		}
		tvDisplayTripDetail.setText(stringBuffer);
		
		//searchresponse.setText(result);
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
