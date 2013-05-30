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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DisplayYoutubeSearch extends Activity {

	TextView tvDisplayYoutubeSearch;
	String result = "";
	String UserQuery = "";
	String searchKeyword = "";
	StringBuilder sb = new StringBuilder();
	String line = null;
	StringBuffer stringBuffer = new StringBuffer();
	InputStream is;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.displayyoutubesearch);
		tvDisplayYoutubeSearch = (TextView) findViewById(R.id.tvDisplayYoutubeSearch);
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
		  
		  searchKeyword = "http://gdata.youtube.com/feeds/videos?alt=json&key=AI39si5MvoasEAaZcs_0GhWTqxdqIdG1gS64_07k_Z_Zj3x13jWg11YUO9yP1EoPk6VYu9yXcnsichrHtLHYsIsKo8sTvYQp9A&q="+trimmedQuery;
		 // http://gdata.youtube.com/feeds/api/videos?safeSearch=none&orderby=viewCount&key=AI39si5MvoasEAaZcs_0GhWTqxdqIdG1gS64_07k_Z_Zj3x13jWg11YUO9yP1EoPk6VYu9yXcnsichrHtLHYsIsKo8sTvYQp9A&q=keyword
		  //https://gdata.youtube.com/feeds/api/standardfeeds/top_rated/videos?alt=json&key=AI39si5MvoasEAaZcs_0GhWTqxdqIdG1gS64_07k_Z_Zj3x13jWg11YUO9yP1EoPk6VYu9yXcnsichrHtLHYsIsKo8sTvYQp9A&q="+trimmedQuery;
		 
			//HttpPost httppost = new HttpPost(searchKeyword);
			try {
				HttpClient httpclient = new DefaultHttpClient();
				 HttpResponse response = httpclient.execute(new HttpGet(searchKeyword));
				//HttpResponse response = httpclient.execute(httppost);
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
			JSONObject feed = jsonInput.getJSONObject("feed");
			JSONArray entry = feed.getJSONArray("entry");
			String title = "";
			String url = "";
			String thumbnail = "";
			for(int i = 0 ; i < entry.length(); i ++)
			{
				
				try{
					title = entry.getJSONObject(i).getJSONObject("title").getString("$t");
					url = entry.getJSONObject(i).getJSONObject("media$group").getJSONArray("media$content").getJSONObject(0).getString("url");
					thumbnail = entry.getJSONObject(i).getJSONObject("media$group").getJSONArray("media$thumbnail").getJSONObject(1).getString("url");
				}finally{
					
				
				
				if(!url.startsWith("http") || url.equals(""))
				{
					url = "";
					title = "";
				}
				Log.v(title,"title");
				Log.v(url,"url");
				stringBuffer.append("Title :"+title+ "\n  "+"URL :"+url+"\n"+"\n");				
			}
			tvDisplayYoutubeSearch.setText(stringBuffer);
			}
			//searchresponse.setText(result);
			} catch(Exception e){
				e.printStackTrace();
			}
	}

}
