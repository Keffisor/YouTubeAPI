package com.keffisor21.youtubeapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Util {
	public static String get(String part, String channel_id, String api_key, YouTubeChannelType type) {
		try {
		    URL u = new URL("https://www.googleapis.com/youtube/v3/channels?part=" + part + (type.equals(YouTubeChannelType.CHANNEL_ID) ? "&id=" : "&forUsername=") + channel_id + "&fields=items&key=" + api_key);
		    URLConnection conn = u.openConnection();
		    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    StringBuffer buffer = new StringBuffer();
		    String inputLine;
		    while ((inputLine = in.readLine()) != null)
		      buffer.append(inputLine); 
		    in.close();
		    buffer.toString();
		    String suscribers = buffer.toString();
		    return suscribers;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
