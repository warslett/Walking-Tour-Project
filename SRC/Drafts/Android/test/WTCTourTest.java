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
		test.setTourName("A_Long_And_Loving_Walk");
		test.setLongDesc("A rather long description of a rather meandingless tour that I am developing purly for a test hahahah test stuff");
		test.setShortDesc("ape");
		if(assertEquals("A rather long description of a rather meaningless tour that I am developing purly for a test hahahah test stuff",test.getLongDesc())){	
			System.out.println("Long Description getter/setter works");
		}

		else if(assertEquals("A_Long_And_Loving_Walk",test.getTourName()){
			System.out.println("get/set TourName Works");
			
		}
		else if(assertEquals("ape",test.getShortDesc()));
			System.out.println("get/setShortDesc Works");
		}
		else{
			fail();
		}







	}
}
