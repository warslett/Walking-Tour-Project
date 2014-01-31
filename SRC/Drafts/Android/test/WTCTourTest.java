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
			//test that the gets work for each location
			//test that the toJSON works
			// test that get distance works
 			fail("Strings are different FAIL");
 		}
	}
	public void testAddLocation{
	WTCLocation blank = new WTCLocation();
	test.addLocation(WTCLocation);
	if(assertEquals(1,WTCLocation.getLocationsSize());
		System.out.println("Pass");
	}
	else{
		fail(); 
	}
}
