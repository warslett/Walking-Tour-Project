public class KeyLocation extends Location {

	private String[] photos; // Might be changed ArrayList
	private String locName;
	private String locDesc;

	public KeyLocation(){
		super();
		photos = new String[5];
		locName = new String();
		locDesc = new String();
	}

	public KeyLocation(long longitude, long latitude){
		super(longitude, latitude);
		photos = new String[5];
		locName = new String();
		locDesc = new String();
	}

	public void addPhoto(String p){
		//TODO needs implementing
	}

	public void removePhoto(String p){
		//TODO needs implementing
	}

}
