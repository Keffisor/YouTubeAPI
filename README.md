# YouTubeAPI
Get the suscribers, total views and more of a YouTube channel in a very easy way

# Example of usage
```
YouTubeAPI youTubeAPI = new YouTubeAPI(channel_id, YouTubeChannelType.CHANNEL_ID);
System.out.println("Channel name: " + youTubeAPI.getChannelName());
System.out.println("Channel description: " + youTubeAPI.getChannelDescription());
System.out.println("Suscribers: " + youTubeAPI.getSuscriberCount());
System.out.println("Videos: " + youTubeAPI.getVideoCount());
System.out.println("Total views: " + youTubeAPI.getTotalViews());
```
