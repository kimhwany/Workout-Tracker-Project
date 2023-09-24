package workout;

import java.util.ArrayList;
import java.util.Collections;

public class Library {
	
	private ArrayList<Workout> workouts;
	
	private ArrayList<String> equipment;
	
	private int recommended;
	
	private boolean[][] schedule;
	
	private ReadWrite files;
	
	
	public Library() {
		files = new ReadWrite(this);
		workouts = new ArrayList<Workout>();
		equipment = new ArrayList<String>();
		files.loadObjectFile();
		files.loadEquipmentFile();
		recommended = 0;
		schedule = new boolean[24][7];
	}
	
	public boolean getScheduleState(int row, int col) {
		return schedule[row][col];
	}
	
	public void updateScheduleState(int row, int col, boolean value) {
		schedule[row][col] = value;
	}
	
	public int getRecAsInt() {
		return recommended;
	}
	
	public String getRecommendation() {
		if (recommended == 0) {
			return "This is your first workout! Complete your first to start recommendations.";
		}
		else {
			return "Your recommended weight is: " + recommended + ".";
		}
	}
	
	public void setRecommendation(int reps, int diff, int weight, int time) {
		System.out.println("Reps: " + reps + " Diff: " + diff + " Weight: " + weight + " Time: " + time);
		
		// Calculate rep goal from difficulty
		int repgoal = 6;
		if (diff == 2) repgoal = 8;
		if (diff == 3) repgoal = 12;
		repgoal = repgoal * (time/60);
		
		// Derive percentage of rep goal reached
		double percent = (double) reps/ repgoal;
		if (percent < 0.8) percent = 0.8;
		if (percent > 1.2) percent = 1.2;
		System.out.println("percentage:" + percent);

		// Determine recommended weight
		if (recommended == 0) { 
			recommended = weight;
		} 
		else {
			double newRec = recommended * percent;
			recommended = (int) newRec;
			if (recommended < 1) recommended = 1;
		}	
		System.out.println("Rec after:" + recommended);
	}
	
	public void addWorkout(Workout in) {
		workouts.add(in);
		files.saveObjectFile();
	}
	
	public ArrayList<Workout> getWorkoutList() {
		return workouts;
	}
	
	public void setWorkoutList(ArrayList<Workout> workouts) {
		this.workouts = workouts;
	}
	
	public ArrayList<String> getWorkoutListAsString() {
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i<workouts.size(); i++) {
			list.add(workouts.get(i).getInfo());
		}	
		return list;
	}
	
	public void setEquipmentList(ArrayList<String> in){
		equipment = in;
	}
	
	public ArrayList<String> getEquipmentList() { 
		return equipment;
	}
	
	public void addEquipment(String in) {
		equipment.add(in);
		files.saveEquipmentFile();
	}
	
	public String toString() {
		String out = "";
		for (int i = 0; i<workouts.size(); i++) {
			out += workouts.get(i).toString() + "\n";
		}	
		return out;
	}

	
	public void sortByRating() {
		Collections.sort(workouts, Workout.WorkoutRatingComparator);		
	}
	
	public void sortByDifficulty() {
		Collections.sort(workouts, Workout.WorkoutDifficultyComparator);		
	}
	
	public void sortByDate() {
		Collections.sort(workouts, Workout.WorkoutDateComparator);		
	}
}
