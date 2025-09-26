package CMSC315Project2;

import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;

public class Driver extends Application {
	private static final String FILE_NAME = "points.txt";

	private ArrayList<Point> fileCoordinates(){
		ArrayList<Point> coordinates = new ArrayList<>();

		try(Scanner scan = new Scanner(new File(FILE_NAME))) {
			while(scan.hasNextDouble()){
				double x = scan.nextDouble();
				double y = scan.nextDouble();
				coordinates.add(new Point(x, y));

			}
		} catch(FileNotFoundException e) {
			System.out.println("File: " + FILE_NAME + " not found.");
		}
		return coordinates;
	}
	
	public void start(Stage primaryStage) {
		ArrayList<Point> points = fileCoordinates();
		DisplayPane displayPane = new DisplayPane(points);
		Scene scene = new Scene(displayPane, 500, 500);
		
		primaryStage.setTitle("Project 2");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
