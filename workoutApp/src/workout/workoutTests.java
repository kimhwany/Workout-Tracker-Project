package workout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class workoutTests {

	//make sure the recommended weight cannot go below 1 after being set.
	@Test
	void testWeightLowerLimit() {
		Library library = new Library();
		library.setRecommendation(10, 10, 10, 10);
		library.setRecommendation(0, 0, 0, 0);
		int rec = library.getRecAsInt();
		if (rec <=0) {  fail("Recommendation set back to 0");}
	}
	
	@Test
	void checkPrintout() {
		Workout workout = new Workout(10,60, 100, 1);
		String out = workout.getInfo();
		if (!out.contains("Difficulty")) {  fail("No difficulty");}
		if (!out.contains("Rating")) {  fail("No rating");}
		
	}

}
