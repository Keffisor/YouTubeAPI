package com.keffisor21.youtubeapi;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class YouTubeAPI {
	private Integer suscriberCount = null;
	private Integer totalViews = null;
	private Integer videoCount = null;
	private Boolean hiddenSubscriberCount = null;
	
	private String channelName = null;
	private String channelDescription = null;
	private String customUrl = null;
	private String dateCreation = null;
	private String country = null;
	private String profile_pic = null;
	
	private String channel_id;
	private YouTubeChannelType type;
	
	public YouTubeAPI(String channel_id, YouTubeChannelType type) {
		this.channel_id = channel_id;
		this.type = type;
		if(getAPIKey().equals("empty")) {
			System.out.println("YouTube api key not provided in the function getAPIKey in YouTubeAPI class. For make the library work you should create one!\nTutorial: https://www.youtube.com/watch?v=jykW3AX8pEE");
			return;
		}
	}
	
	public String getChannelName() {
		if(channelName == null) setupSnippet();
		return channelName;
	}
	
	public String getChannelDescription() {
		if(channelDescription == null) setupSnippet();
		return channelDescription;
	}
	
	public String getProfilePic() {
		if(profile_pic == null) setupSnippet();
		return profile_pic;
	}
	
	public String getCustomUrl() {
		if(customUrl == null) setupSnippet();
		return customUrl;
	}
	
	public String getDateCreation() {
		if(dateCreation == null) setupSnippet();
		return dateCreation;
	}
	
	public String getCountry() {
		if(country == null) setupSnippet();
		return country;
	}
	
	public int getTotalViews() {
		if(totalViews == null) setupStatistics();
		return totalViews;
	}
	
	public int getSuscriberCount() {
		if(suscriberCount == null) setupStatistics();
		return suscriberCount;
	}
	
	public int getVideoCount() {
		if(videoCount == null) setupStatistics();
		return videoCount;
	}
	
	public boolean hasHiddenSubscriberCount() {
		if(hiddenSubscriberCount == null) setupStatistics();
		return hiddenSubscriberCount;
	}
	
	public String getAPIKey() {
		return "empty";
	}
	
	private void setupStatistics() {
		try {
			String response = Util.get("statistics", channel_id, getAPIKey(), type);
	    	JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
	    	JsonObject data = ((JsonObject)jsonObject.getAsJsonArray("items").get(0)).getAsJsonObject("statistics");
	    	suscriberCount = data.get("subscriberCount").getAsInt();
	    	videoCount = data.get("videoCount").getAsInt();
	    	totalViews = data.get("viewCount").getAsInt();
	    	hiddenSubscriberCount = data.get("hiddenSubscriberCount").getAsBoolean();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setupSnippet() {
		try {
			String response = Util.get("snippet", channel_id, getAPIKey(), type);
	    	JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
	    	JsonObject data = ((JsonObject)jsonObject.getAsJsonArray("items").get(0)).getAsJsonObject("snippet");
	    	channelName = data.get("title").getAsString();
	    	channelDescription = data.get("description").getAsString();
	    	customUrl = data.get("customUrl").getAsString();
	    	dateCreation = data.get("publishedAt").getAsString();
	    	profile_pic = data.getAsJsonObject("thumbnails").getAsJsonObject("high").get("url").getAsString();
	    	country = data.get("country").getAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
