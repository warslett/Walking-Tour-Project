import static org.junit.Assert.*;
 
import org.junit.Before;
import org.junit.Test;

public class WTCTourTest {

	@Before
	public void setUp() throws Exception {
		WTCTour test = new WTCTour("name","short desc","long desc");
 	}
 
 	@Test
 	public void testToJSON() {
		if(assertEquals("{\"title\": \"name\",\"longDesc\": \"long desc\",\"shortDesc\": \"short desc\",\"distance\":\"0\",\"locations\": []}",WTCTour.toJSON())){
			System.out.println("The Strings are the same toJSON Passed");
		}	
		else{
 			fail("Strings are different FAIL");
 		}
	}
	@Test
	public void testAddLocation{
		WTCLocation blank = new WTCLocation();
		test.addLocation(WTCLocation);
	if(assertEquals(1,WTCLocation.getLocationsSize());
		System.out.println("Pass");
	}
	else{
		fail(); 
	}
	@Test
	public void testGetters(){
		if(assertEquals(test.getLongDesc(),"long desc")){
			System.out.println("Long Description getter works");
		}

		else if(assertEquals(test.getTourName(),"name"){
			System.out.println("getTourName Works");
			
		}
		else if(assertEquals(test.getShortDesc(),"short desc")){
			System.out.println("getShortDesc Works");
		}
		else{
			fail();
		}







	}
}
