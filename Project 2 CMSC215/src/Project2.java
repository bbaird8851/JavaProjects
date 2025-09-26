/**
 * @author Brandon Baird
 * @date 09/07/2024
 * 
 * @function Process multiple student objects & 
 * check eligibility for honor society.
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Project2 {
	
	public static void main(String[] args) {
		
		ArrayList<Student> students = new ArrayList<>();
		String filename = "students.txt";
		
		
		try{
			File file = new File(filename);
			Scanner scan = new Scanner(file);
			//System.out.println("File found successfully!");
			
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				String[] parts = line.split(" ");
				
				String name = parts[0];
				int creditHours = Integer.parseInt(parts[1]);
				double qualityPoints = Double.parseDouble(parts[2]);
				String gradOrUndergrad = parts[3];
				
				if(gradOrUndergrad.equalsIgnoreCase("Freshman") ||
				   gradOrUndergrad.equalsIgnoreCase("Sophomore") ||
				   gradOrUndergrad.equalsIgnoreCase("Junior") ||
				   gradOrUndergrad.equalsIgnoreCase("Senior") ) {
					
					students.add(new Undergraduate(name, creditHours, qualityPoints, gradOrUndergrad));
				} else if (gradOrUndergrad.equalsIgnoreCase("Masters") ||
						(gradOrUndergrad.equalsIgnoreCase("Doctorate"))){
					students.add(new Graduate(name, creditHours, qualityPoints, gradOrUndergrad));
				}
			}
			scan.close();
			
		}catch(FileNotFoundException e) {
			System.out.println("The file: " + filename + " was not found. End Program." + " " + e.getMessage());
			System.exit(1);
		}
		calculateHonorSocietyEligibility(students);
	}
	
	public static void calculateHonorSocietyEligibility(ArrayList<Student> students) {
		double totalGPA = 0;
		int totalStudents = students.size();
		final double HIGHEST_GPA = 4.0;
		
		for(Student s : students) {
			totalGPA += s.calculateGpa();
		}
		
		double averageGPA = totalGPA / totalStudents;
		double gpaThreshold = (averageGPA + HIGHEST_GPA) / 2;
		
		Student.setGpaThreshold(gpaThreshold);
		
		System.out.printf("GPA threshold for membership is %.2f%n", gpaThreshold);
		
		System.out.println("Student(s) eligible for Honor Society: ");
		for(Student s : students) {
			if(s.eligibleForHonorSociety()) {
				System.out.println(s.toString());
			}
		}
	}

}
