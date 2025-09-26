package CMSC315Project2;

public class Point implements Comparable<Point> {
	private final double x;
	private final double y;
	
	public Point (double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public boolean belowToLeft(Point other) {
		return other.x > this.x && 
			   other.y < this.y;
	}
	
	public int compareTo(Point other) {
		return Double.compare(this.x, other.x);
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
