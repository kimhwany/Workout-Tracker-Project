package workout;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class workoutApp extends Application {

    private Button add;
    private Button save;
	private static Library library;
	private ReadWrite rw;
    private TextField temp;
    private VBox subpage3Layout;
    private Stage window;
    private Scene homeScene, subpage1Scene, subpage11Scene, subpage2Scene, subpage3Scene, subpage4Scene;
    private int diff;
    private int time;
    private int weight;
    private ListView<Workout> listView = new ListView<>();
    private ArrayList<Workout> data = new ArrayList<>();
    private Label recomm;
    private static final String[] DAYS_OF_WEEK = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
    	rw = new ReadWrite(library);
    	
        Library library = new Library();
        
        window = primaryStage;

        window.setTitle("Workout App");

        // Create home screen
        Button button1 = new Button("Workout");
        button1.setOnAction(e -> window.setScene(subpage1Scene));

        Button button2 = new Button("Past Workouts");
        button2.setOnAction(e -> window.setScene(subpage2Scene));

        Button button3 = new Button("Equipment");
        button3.setOnAction(e -> window.setScene(subpage3Scene));

        Button button4 = new Button("Schedule");
        button4.setOnAction(e -> window.setScene(subpage4Scene));

        VBox layout = new VBox(20);
        layout.getChildren().addAll(button1, button2, button3, button4);
        layout.setAlignment(Pos.CENTER);

        homeScene = new Scene(layout, 400, 400);
        

        // Create Workout Page 1
        VBox subpage1Layout = new VBox();
        Button backOne = new Button("Cancel Workout");
        backOne.setOnAction(e -> window.setScene(homeScene));
        
        Label weightLabel = new Label("Weight");
        Label blankOne = new Label("");
        Label blankTwo = new Label("");
        Label blankThree = new Label("");
        Label blankFour = new Label("");
        Label difficulty = new Label("Difficulty");
        Label timeLabel = new Label("Time (in seconds)");
        recomm = new Label(library.getRecommendation());
        
        TextField weightBox = new TextField();
        TextField timeBox = new TextField();
        
        RadioButton radioButton1 = new RadioButton("Easy (6 reps per minute)");
        RadioButton radioButton2 = new RadioButton("Medium (8 reps per minute)");
        RadioButton radioButton3 = new RadioButton("Hard(12 reps per minute)");

        ToggleGroup toggleGroup = new ToggleGroup();
        radioButton1.setToggleGroup(toggleGroup);
        radioButton2.setToggleGroup(toggleGroup);
        radioButton3.setToggleGroup(toggleGroup);
        
        toggleGroup.selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
            if (newVal == radioButton1) {
                diff=1;
            } else if (newVal == radioButton2) {
                diff=2;
            } else if (newVal == radioButton3) {
                diff=3;
            }
        });
        
        Button startWorkout = new Button("Start Workout");
        startWorkout.setOnAction(e -> {
        	time = Integer.valueOf(timeBox.getText());
        	weight = Integer.valueOf(weightBox.getText());
        	window.setScene(subpage11Scene);
        });
        
        subpage1Layout.getChildren().addAll(
        	backOne, recomm, blankOne, weightLabel, weightBox,
        	blankTwo, difficulty,radioButton1, radioButton2, radioButton3,
        	blankThree, timeLabel, timeBox,
        	blankFour, startWorkout
        );
        subpage1Scene = new Scene(subpage1Layout, 400, 400);
        
        
        
        // Create Workout Page 2
        VBox subpage11Layout = new VBox(20);
        
        Label blankFive = new Label("");
        Label blankSix = new Label("");
        Label blankSeven = new Label("");
        
        TextField repsDone = new TextField();
        TextField rating = new TextField();
        
        Label timeFinishWorkoutLabel = new Label("TIME");
        Label howWouldYouRate = new Label("How would you rate this session? 1/10");
        Label howManyReps = new Label("How many reps did you do?");
        
        Button finishWorkout = new Button("Finish Workout");
        finishWorkout.setOnAction(e -> {
        	int rat = Integer.valueOf(rating.getText());
        	Workout workout = new Workout(diff, time, weight, rat);
        	library.addWorkout(workout);
            library.setRecommendation(Integer.valueOf(repsDone.getText()),diff, weight, time);
        	timeBox.setText("");
        	weightBox.setText("");
        	rating.setText("");
        	repsDone.setText("");
            
        	Platform.runLater(() -> {
            	data = library.getWorkoutList();
                listView.setItems(FXCollections.observableArrayList(data));
                recomm.setText(library.getRecommendation());
            });

        	window.setScene(homeScene);
        });
        
        subpage11Layout.getChildren().addAll(
        	timeFinishWorkoutLabel,
        	blankFive,howWouldYouRate,rating,
       		blankSix, howManyReps,repsDone,
       		blankSeven,finishWorkout
       	);
        subpage11Scene = new Scene(subpage11Layout, 400, 400);
        
        
        // Create Past Workouts Page
        Button backTwo = new Button("Back to Home");
        backTwo.setOnAction(e -> window.setScene(homeScene));
        
        Button sortByDate = new Button("Sort Date");
        sortByDate.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	library.sortByDate();
                data = library.getWorkoutList();
            	listView.setItems(FXCollections.observableArrayList(data));
            }
        });

        Button sortByDifficulty = new Button("Sort Difficulty");
        sortByDifficulty.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	library.sortByDifficulty();
                data = library.getWorkoutList();
            	listView.setItems(FXCollections.observableArrayList(data));
            }
        });
        
        Button sortByRating = new Button("Sort Rating");
        sortByRating.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	library.sortByRating();
                data = library.getWorkoutList();
                listView.setItems(FXCollections.observableArrayList(data));
            }
        });
        
        data = library.getWorkoutList();
    	listView.setItems(FXCollections.observableArrayList(data));
        listView.setCellFactory(param -> new ListCell<Workout>() {
            @Override
            protected void updateItem(Workout item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getInfo() == null) {
                    setText(null);
                } else {
                    setText(item.getInfo());
                }
            }
        });
        
        // Format Past Workouts Page
        HBox buttonLayout = new HBox(20);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.getChildren().addAll(backTwo, sortByDate, sortByDifficulty, sortByRating);
        VBox subpage2Layout = new VBox(20);
        subpage2Layout.getChildren().addAll(buttonLayout, listView);
        subpage2Scene = new Scene(subpage2Layout, 400, 400);

          
        // Create Equipment page        
        HBox subpage3LayoutH = new HBox(210);
        subpage3Layout = new VBox(20);
        Button backThree = new Button("Back to Home");
        backThree.setOnAction(e -> window.setScene(homeScene));
        subpage3LayoutH.getChildren().add(backThree);
        Label eqpList = new Label("Equipment List"); 
        subpage3Layout.getChildren().addAll(subpage3LayoutH,eqpList);
        // load equipment information
        String equipment = "";
        for(int i = 0; i < library.getEquipmentList().size(); i++) {
        	equipment += library.getEquipmentList().get(i) + "\n";
        }
        Text pastEquipments = new Text(equipment);
        subpage3Layout.getChildren().add(pastEquipments);
        temp = new TextField();
        add = new Button("Add");
        add.setOnAction(this::eventHandler);
        Button addEquipment = new Button("Add Equipment");
        addEquipment.setOnAction(e -> subpage3Layout.getChildren().addAll(temp,add));
        subpage3LayoutH.getChildren().add(addEquipment);
        subpage3Layout.setAlignment(Pos.TOP_CENTER);
        subpage3Scene = new Scene(subpage3Layout, 400, 400);
        
        
        // Create Schedule page
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        // Add column labels
        for (int col = 0; col < 7; col++) {
            Label dayLabel = new Label(DAYS_OF_WEEK[col]);
            StackPane dayPane = new StackPane(dayLabel);
            dayPane.setPrefSize(50, 30);
            dayPane.setAlignment(Pos.CENTER);
            gridPane.add(dayPane, col + 1, 0);
        }

        // Add row labels and checkboxes
        for (int row = 0; row < 24; row++) {
            Label hourLabel = new Label(String.format("%02d:00", row));
            StackPane hourPane = new StackPane(hourLabel);
            hourPane.setPrefSize(50, 30);
            hourPane.setAlignment(Pos.CENTER);
            gridPane.add(hourPane, 0, row + 1);

            for (int col = 0; col < 7; col++) {
                CheckBox checkbox = new CheckBox();
                checkbox.setSelected(library.getScheduleState(row, col)); 
                int currentRow = row;
                int currentCol = col;
                checkbox.setOnAction(event -> {
                	library.updateScheduleState(currentRow, currentCol, checkbox.isSelected());
                });
                gridPane.add(checkbox, col + 1, row + 1);
            }
        }
        
        
        VBox subpage4Layout = new VBox(gridPane);
        Button backFour = new Button("Back to Home");
        backFour.setOnAction(e -> window.setScene(homeScene));
        
        subpage4Layout.getChildren().add(backFour);
        subpage4Scene = new Scene(subpage4Layout, 800, 800);

        window.setScene(homeScene);
        window.show();
    }
    
    private void eventHandler(ActionEvent e) {
    	String equipmentName = temp.getText();
    	subpage3Layout.getChildren().remove(temp);
    	subpage3Layout.getChildren().remove(add);
    	library.addEquipment(equipmentName);
    	Text equipments = new Text(equipmentName);
    	subpage3Layout.getChildren().add(equipments);
    }

    public static void main(String[] args) {
                
        library = new Library();
        
        launch(args);


    }
}
