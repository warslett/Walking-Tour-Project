public class Location {

	private long longitude;
	private long latitude;
	private String timeStamp;

	public Location(){
		longitude = 0;
		latitude = 0;
		// timeStamp will implemented later...
	}

	public Location(long longitude, long latitude){
		this.longitude = longitude;
		this.latitude = latitude;
		// timeStamp will implemented later...
	}

	public void setLongitude(long longitude){
		this.longitude = longitude;
	}

	public long getLongitude(){
		return longitude;
	}

	public void setLatitude(long latitude){
		this.latitude = latitude;
	}

	public long getLatitude(){
		return latitude;
	}

	public void setTimeStamp(){
		// Will be implemented later...
	}

	public String getTimeStamp(){
		return timeStamp;
	}

}
