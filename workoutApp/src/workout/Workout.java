package workout;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class Workout implements Serializable {

	private int difficulty;
	
	private int rating;
	
	private int weight;
	
	private int time;
	
	private int reps;
	
	private LocalDateTime dateAndTime;

	public Workout(int diff, int time, int weight, int rating) {
		this.difficulty = diff;
		this.time = time;
		this.rating = rating;
		this.dateAndTime = LocalDateTime.now();
	}

	public Workout(int difficulty, int time, LocalDateTime dateAndTime, int weight, int rating) {
		this.difficulty = difficulty;
		this.rating = rating;
		this.time = time;
		this.dateAndTime = dateAndTime;
	}

	public void addRating(int rating) {
		this.rating = rating;
	}

	public void addReps(int reps) {
		this.reps = reps;
	}

	public String getInfo() {
		// Format Difficulty
		String newDiff = "Easy";
		if (difficulty == 2) {
			newDiff = "Medium";
		}
		else if (difficulty == 3) {
			newDiff = "Hard";
		}
		
		// Format Date Time Object
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d HH:mm");
		String formattedDateTime = this.dateAndTime.format(formatter);
		
		return formattedDateTime + " - Difficulty: " + newDiff + " - Rating: " + rating;
	}

	public static Comparator<Workout> WorkoutRatingComparator = new Comparator<Workout>() {
		public int compare(Workout workout1, Workout workout2) {
			if (workout1.getRating() == workout2.getRating()) {
				return 0;
			} else if (workout1.getRating() < workout2.getRating()) {
				return 1;
			} else {
				return -1;
			}
		}
	};

	public static Comparator<Workout> WorkoutDateComparator = new Comparator<Workout>() {
		public int compare(Workout workout1, Workout workout2) {
			if (workout1.getDateAndTime().isEqual(workout2.getDateAndTime())) {
				return 0;
			} else if (workout1.getDateAndTime().isBefore(workout2.getDateAndTime())) {
				return 1;
			} else {
				return -1;
			}
		}
	};
	
	public static Comparator<Workout> WorkoutDifficultyComparator = new Comparator<Workout>() {
		public int compare(Workout workout1, Workout workout2) {
			if (workout1.getDifficulty() == workout2.getDifficulty()) {
				return 0;
			} else if (workout1.getDifficulty() > workout2.getDifficulty()) {
				return 1;
			} else {
				return -1;
			}
		}
	};
	
	public int getRating() {
		return this.rating;
	}
	
	public int getDifficulty() {
		return this.difficulty;
	}
	
	public LocalDateTime getDateAndTime() {
		return this.dateAndTime;
	}
}
