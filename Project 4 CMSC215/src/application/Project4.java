/**
 * @author Brandon Baird
 * @date 10/06/24
 * 
 * @function allow the user to compare time intervals
 * 
 */
package application;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Project4 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		pane.setHgap(5.5); 
		pane.setVgap(5.5);
		
		//Top labels for both intervals
		pane.add(new Label ("Start Time"), 1, 0);
		pane.add(new Label ("End Time"), 2, 0);
		
		//For Interval 1
		pane.add(new Label ("Time Interval 1"), 0, 1);
		TextField interval1StartField = new TextField();
		TextField interval1EndField = new TextField();
		
		pane.add(interval1StartField, 1, 1);
		pane.add(interval1EndField, 2, 1);
		//For Interval 2
		pane.add(new Label ("Time Interval 2"), 0, 2);
		TextField interval2StartField = new TextField();
		TextField interval2EndField = new TextField();
		
		pane.add(interval2StartField, 1, 2);
		pane.add(interval2EndField, 2, 2);
		
		
		pane.add(new Label ("Time to Check"), 0, 4);
		
		Label resultLabel = new Label();
		TextField timeToCheckField = new TextField();

		Button compareIntervalsButton = new Button("Compare Intervals");
		Button checkTimeButton = new Button("Check Time");

		pane.add(compareIntervalsButton, 1, 3);
		pane.add(timeToCheckField, 1, 4);
		pane.add(checkTimeButton, 1, 5);
		pane.add(resultLabel, 0, 6, 2, 1);

		compareIntervalsButton.setOnAction(e -> {
			String interval1Start = interval1StartField.getText();
			String interval1End = interval1EndField.getText();
			String interval2Start = interval2StartField.getText();
			String interval2End = interval2EndField.getText();

			try {
				Time start1 = new Time(interval1Start);
				Time end1 = new Time(interval1End);
				Time start2 = new Time(interval2Start);
				Time end2 = new Time(interval2End);

				Interval<Time> interval1 = new Interval<>(start1, end1);
				Interval<Time> interval2 = new Interval<>(start2, end2);
				
				if (start1.compareTo(start2) <= 0 && end2.compareTo(end1) <= 0) {
					resultLabel.setText("Interval 2 is a sub-interval of Interval 1.");
				} else if (start2.compareTo(start1) <= 0 && end1.compareTo(end2) <= 0) {
					resultLabel.setText("Interval 1 is a sub-interval of Interval 2.");
				} else if(end1.compareTo(start2) < 0 || end2.compareTo(start1) < 0) {
					resultLabel.setText("The intervals are disjoint.");
				} else  if (interval1.overlaps(interval2)) {
					resultLabel.setText("The intervals overlap.");
				}
			} catch (InvalidTime e1) {
				resultLabel.setText("Error: " + e1.getMessage());
			}
		});


		checkTimeButton.setOnAction(e -> {
			String timeToCheck = timeToCheckField.getText();
			String interval1Start = interval1StartField.getText();
			String interval1End = interval1EndField.getText();
			String interval2Start = interval2StartField.getText();
			String interval2End = interval2EndField.getText();

			try {
				Time checkTime = new Time(timeToCheck);
				Time start1 = new Time(interval1Start);
				Time end1 = new Time(interval1End);
				Time start2 = new Time(interval2Start);
                Time end2 = new Time(interval2End);

				Interval<Time> interval1 = new Interval<>(start1, end1);
				Interval<Time> interval2 = new Interval<>(start2, end2);
				
				boolean checkInterval1 = interval1.within(checkTime);
				boolean checkInterval2 = interval2.within(checkTime);

				if (checkInterval1 && !checkInterval2) {
					resultLabel.setText("The time is only within interval 1.");
				} else if (checkInterval2 && !checkInterval1){
					resultLabel.setText("The time is only within interval 2.");
				} else if (checkInterval1 && checkInterval2) {
					resultLabel.setText("The time is in both intervals.");
				} else {
					resultLabel.setText("The time in neither interval.");
				}
			} catch (InvalidTime e2) {
				resultLabel.setText("Error: " + e2.getMessage());
			}
		});
		
		
		Scene scene = new Scene(pane);
		primaryStage.setTitle("Time Interval Checker");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}

