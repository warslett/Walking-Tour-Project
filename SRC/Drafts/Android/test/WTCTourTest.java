import static org.junit.Assert.*;
 
import org.junit.Before;
import org.junit.Test;

public class WTCTourTest {

	@Before
	public void setUp() throws Exception {
		WTCTour test = new WTCTour("name","short desc","long desc");
		WTCLocation loc1 = new WTCLocation();
		WTCLocation loc2 = new WTCLocation();
		WTCKeyLocation keyLoc1 = new WTCKeyLocation();
		WTCTour.add(loc1);
		WTCTour.add(loc2);
		WTCTour.add(keyLoc1);
 	}
 
 	@Test
 	public void test() {
		//test that the gets work for each location
		//test that the toJSON works
		// test that get distance works
 		fail("Not yet implemented");
 	}
 
 }
