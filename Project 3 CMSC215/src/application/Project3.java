/**
 * @author Brandon Baird
 * @date 09/21/24
 * 
 * @function allow the user to effortlessly calculate
 * a total trip cost with given parameters.
 */
package application;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Project3 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private TextField distanceTextField, gasCostTextField, gasMileageTextField, 
	hotelCostTextField, foodCostTextField, attractionsCostTextField, daysTextField;
	private Label totalCostLabel;
	private ComboBox<String> distanceUnits, gasCostUnits, gasMileageUnits;

	/**
	 * Create the grid and initialize all the textFields & Labels
	 */
	@Override
	public void start(Stage primaryStage) {

		//default value is 5.5px
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		pane.setHgap(5.5); 
		pane.setVgap(5.5);  

		pane.add(new Label("Distance:"), 0, 0);
		distanceTextField = new TextField();
		pane.add(distanceTextField, 1, 0);

		distanceUnits = new ComboBox<>();
		distanceUnits.getItems().addAll("miles", "kilometers");
		distanceUnits.setValue("miles");
		pane.add(distanceUnits, 2, 0);

		pane.add(new Label("Gasoline Cost:"), 0, 1);
		gasCostTextField = new TextField();
		pane.add(gasCostTextField, 1, 1);

		gasCostUnits = new ComboBox<>();
		gasCostUnits.getItems().addAll("dollars/gal", "dollars/liter");
		gasCostUnits.setValue("dollars/gal");
		pane.add(gasCostUnits, 2, 1);

		pane.add(new Label("Gas Mileage"), 0, 2);
		gasMileageTextField = new TextField();
		pane.add(gasMileageTextField, 1, 2);

		gasMileageUnits = new ComboBox<>();
		gasMileageUnits.getItems().addAll("miles/gallon", "kms/liter");
		gasMileageUnits.setValue("miles/gallon");
		pane.add(gasMileageUnits, 2, 2);

		pane.add(new Label("Number Of Days:"), 0, 3);
		daysTextField = new TextField();
		pane.add(daysTextField, 1, 3);

		pane.add(new Label("Hotel Cost"), 0, 4);
		hotelCostTextField = new TextField();
		pane.add(hotelCostTextField, 1, 4);

		pane.add(new Label("Food Cost"), 0, 5);
		foodCostTextField = new TextField();
		pane.add(foodCostTextField, 1, 5);

		pane.add(new Label("Attractions"), 0, 6);
		attractionsCostTextField = new TextField();
		pane.add(attractionsCostTextField, 1, 6);

		Button calculateButton = new Button("Calculate");
		calculateButton.setOnAction(e -> calculateTotalCost());
		pane.add(calculateButton, 1, 7);
		GridPane.setHalignment(calculateButton, HPos.CENTER);

		pane.add(new Label("Total Trip Cost"), 0, 8);
		totalCostLabel = new Label("$0.00");
		pane.add(totalCostLabel, 1, 8);

		Scene scene = new Scene(pane);
		primaryStage.setTitle("Trip Cost Estimator");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Read and parse the user inputs then 
	 * get selected units and use the tripcost 
	 * object to compute total trip cost.
	 * Display the total cost and handle any
	 * errors that may occur.
	 */
	private void calculateTotalCost() {
		try {
			double distance = Double.parseDouble(distanceTextField.getText());
			double gasCost = Double.parseDouble(gasCostTextField.getText());
			double gasMileage = Double.parseDouble(gasMileageTextField.getText());
			int numOfDays = Integer.parseInt(daysTextField.getText());
			double hotelCost = Double.parseDouble(hotelCostTextField.getText());
			double foodCost = Double.parseDouble(foodCostTextField.getText());
			double attractionsCost = Double.parseDouble(attractionsCostTextField.getText());

			String distanceUnitSelected = distanceUnits.getValue();
			String gasCostUnitSelected = gasCostUnits.getValue();
			String gasMileageUnitSelected = gasMileageUnits.getValue();

			TripCost tripCost = new TripCost(distance, gasCost, gasMileage, numOfDays, 
					hotelCost, foodCost, attractionsCost, distanceUnitSelected, 
					gasCostUnitSelected, gasMileageUnitSelected);

			double totalCost = tripCost.getTotalTripCost();

			totalCostLabel.setText(String.format("$%.2f", totalCost));

		} catch (IllegalArgumentException e) {
			System.out.println("Please enter appropriate values in the box.");
			e.printStackTrace();
		}
	}
}
