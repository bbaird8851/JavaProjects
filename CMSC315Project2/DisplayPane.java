package CMSC315Project2;

import java.util.*;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;


public class DisplayPane extends Pane {
	private ArrayList<Point> points;
	private ArrayList<Line> lines = new ArrayList<>();
	private final double RADIUS = 5.0;

	/*
	 * Constructor that takes
	 * an ArrayList of points
	 * and connects max points.
	 */
	public DisplayPane(ArrayList<Point> listOfPoints) {
		points = new ArrayList<>(listOfPoints);
		setPrefSize(500, 500);
		connectMaximalPoints();

		setOnMouseClicked(event -> {
			if(event.getButton() == MouseButton.PRIMARY) {
				addPoint(event.getX(), event.getY());
			} else if(event.getButton() == MouseButton.SECONDARY) {
				removePoint(event.getX(), event.getY());
			}
		});
	}

	private void addPoint(double x, double y) {
		points.add(new Point(x, y));
		connectMaximalPoints();
	}

	private void removePoint(double x, double y) {
		Iterator<Point> iterator = points.iterator();
		while(iterator.hasNext()) {
			Point p = iterator.next();

			if(Math.abs(p.getX() - x) <= RADIUS && 
			   Math.abs(p.getY() - y) <= RADIUS) {
				iterator.remove();
				break;
			}
		}
		connectMaximalPoints();
	}

	/*
	 * Clear previous drawings and display all points
	 * Find and Sort all maximal points
	 * Connect maximal points with lines 
	 */
	private void connectMaximalPoints() {
		getChildren().clear();
		lines.clear();

		for(Point p : points) {
			Circle coordinate = new Circle(p.getX(), p.getY(), RADIUS);
			getChildren().add(coordinate);
		}

		ArrayList<Point> maximalPoints = determineMaximalPoints();
		Collections.sort(maximalPoints);

		for(int i = 0; i < maximalPoints.size() - 1; i++) {
			Point p1 = maximalPoints.get(i);
			Point p2 = maximalPoints.get(i + 1);

			Line verticalLine = new Line(p1.getX(), p1.getY(),
										 p1.getX(), p2.getY());
			lines.add(verticalLine);

			Line horizontalLine = new Line(p1.getX(), p2.getY(), 
										   p2.getX(), p2.getY());
			lines.add(horizontalLine);
		}

		getChildren().addAll(lines);
	}

	/*
	 * If the point is already
	 * a maximal point then add it,
	 * else skip.
	 */
	private ArrayList<Point> determineMaximalPoints() {
		ArrayList<Point> maximalPoints = new ArrayList<>();
		
		for(Point curr : points) {
			boolean alreadyMaximal = true;
			for(Point other : points) {
				if(curr.belowToLeft(other)) {
					alreadyMaximal = false;
					break;
				}
			}
			if(alreadyMaximal) {
				maximalPoints.add(curr);
			}
		}
		return maximalPoints;
	}
}