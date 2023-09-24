package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;
import workout.Workout;

class workoutTests {
    ArrayList<Workout> workoutList;
    LocalDateTime currentTime;

    @BeforeEach
    public void generateTestData() {
        // Populate Test Data
        currentTime = LocalDateTime.now();

        workoutList = new ArrayList();
        workoutList.add(new Workout(1, 1, currentTime.minusDays(1), 120, 1));
        workoutList.add(new Workout(2, 1, currentTime.minusDays(2), 120, 10));
        workoutList.add(new Workout(1, 1, currentTime.minusDays(3), 120, 6));
        workoutList.add(new Workout(3, 1, currentTime.minusDays(4), 120, 4));
        workoutList.add(new Workout(2, 1, currentTime.minusDays(5), 120, 3));
    }

	@Test
	public void testWorkoutRatingComparator() {
        Collections.sort(workoutList, Workout.WorkoutRatingComparator);	
        
        // Expected Result
        ArrayList<Workout> expectedList = new ArrayList();
        expectedList.add(new Workout(2, 1, currentTime.minusDays(2), 120, 10));
        expectedList.add(new Workout(1, 1, currentTime.minusDays(3), 120, 6));
        expectedList.add(new Workout(3, 1, currentTime.minusDays(4), 120, 4));
        expectedList.add(new Workout(2, 1, currentTime.minusDays(5), 120, 3));
        expectedList.add(new Workout(1, 1, currentTime.minusDays(1), 120, 1));
        
        for (int i = 0; i < expectedList.size(); i++) {
            String currentWorkout = workoutList.get(i).getInfo();
            String expectedWorkout = expectedList.get(i).getInfo();
            assertEquals(currentWorkout, expectedWorkout);
        }
	}

	@Test
	public void testWorkoutDateComparator() {
        Collections.sort(workoutList, Workout.WorkoutDateComparator);	
        
        // Expected Result
        ArrayList<Workout> expectedList = new ArrayList();
        expectedList.add(new Workout(1, 1, currentTime.minusDays(1), 120, 1));
        expectedList.add(new Workout(2, 1, currentTime.minusDays(2), 120, 10));
        expectedList.add(new Workout(1, 1, currentTime.minusDays(3), 120, 6));
        expectedList.add(new Workout(3, 1, currentTime.minusDays(4), 120, 4));
        expectedList.add(new Workout(2, 1, currentTime.minusDays(5), 120, 3));
        
        for (int i = 0; i < expectedList.size(); i++) {
            String currentWorkout = workoutList.get(i).getInfo();
            String expectedWorkout = expectedList.get(i).getInfo();
            assertEquals(currentWorkout, expectedWorkout);
        }
	}

	@Test
	public void testWorkoutDifficultyComparator() {
        Collections.sort(workoutList, Workout.WorkoutDifficultyComparator);	
        
        // Expected Result
        ArrayList<Workout> expectedList = new ArrayList();
        expectedList.add(new Workout(1, 1, currentTime.minusDays(1), 120, 1));
        expectedList.add(new Workout(1, 1, currentTime.minusDays(3), 120, 6));
        expectedList.add(new Workout(2, 1, currentTime.minusDays(2), 120, 10));
        expectedList.add(new Workout(2, 1, currentTime.minusDays(5), 120, 3));
        expectedList.add(new Workout(3, 1, currentTime.minusDays(4), 120, 4));
        
        for (int i = 0; i < expectedList.size(); i++) {
            String currentWorkout = workoutList.get(i).getInfo();
            String expectedWorkout = expectedList.get(i).getInfo();
            assertEquals(currentWorkout, expectedWorkout);
        }
	}
}