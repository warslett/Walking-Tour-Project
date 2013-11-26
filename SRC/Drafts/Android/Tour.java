import java.util.LinkedList;

public class Tour {

	private String tourName;
	private String shortDesc;
	private String longDesc;
	private LinkedList<Location> locations;

	public Tour(){
		tourName = new String();
		shortDesc = new String();
		longDesc = new String();
		locations = new LinkedList<String>();
	}

	public Tour(String tourName){
		this.tourName = tourName;
		shortDesc = new String();
		longDesc = new String();
		locations = new LinkedList<String>();
	}

	public Tour(String tourName, String shortDesc){
		this.tourName = tourName;
		this.shortDesc = shortDesc;
		longDesc = new String();
		locations = new LinkedList<String>();
	}

	public Tour(String tourName, String shortDesc, String longDesc){
		this.tourName = tourName;
		this.shortDesc = shortDesc;
		this.longDesc = longDesc;
		locations = new LinkedList<String>();
	}

	public void addLocation(Location l){
		locations.add(l);
	}

	public void removeLocation(Location l){
		locations.remove(l);
	}

	public String toJSON(){
		String out;
		//going to have to do some testing on Linked Lists and JSON generation
		return out;
	}

	public void setTourName(String tourName){
		this.tourName = tourName;
	}

	public String getTourName(){
		return tourName;
	}

	public void setShortDesc(String shortDesc){
		this.shortDesc = shortDesc;
	}

	public String getShortDesc(){
		return shortDesc;
	}

	public void setLongDesc(String longDesc){
		this.longDesc = longDesc;
	}

	public String getLongDesc(){
		return longDesc;
	}

	public void setLocations(LinkedList locations){
		this.locations = locations;
	}

	public LinkedList<Location> getLocations(){
		return locations;
	}

}
