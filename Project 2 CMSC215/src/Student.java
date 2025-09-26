/**
 * 
 * @author Brandon Baird
 * Student Class
 *
 */
public class Student {
	private String name;
	private int creditHours;
	private double qualityPoints;
	private static double GPA_THRESHOLD;
	
	public Student(String name, int creditHours, double qualityPoints) {
		this.name = name;
		this.creditHours = creditHours;
		this.qualityPoints = qualityPoints;
	}
	
	public double calculateGpa() {
		return qualityPoints / creditHours;
	}
	
	public boolean eligibleForHonorSociety() {
		return this.calculateGpa() >= GPA_THRESHOLD;
	}
	
	@Override
	public String toString() {
		return name + " GPA " + String.format("%.2f", calculateGpa());
	}
	
	public static void setGpaThreshold(double newGpaThreshold) {
		GPA_THRESHOLD = newGpaThreshold;
	}

	public String getName() {
		return name;
	}
}
