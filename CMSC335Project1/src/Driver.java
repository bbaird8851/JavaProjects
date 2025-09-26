/**
 * @author Brandon Baird
 * Date: 3/22/2025
 * 
 * Purpose: The driver class contains
 * the menu to construct the various
 * shapes.
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean operating = true;

		while (operating) {
			try {
				System.out.println("\n****** Java Shapes Program ******\n");
				System.out.println("Select from the menu below: ");
				System.out.println("1. Construct a Circle");
				System.out.println("2. Construct a Rectangle");
				System.out.println("3. Construct a Square");
				System.out.println("4. Construct a Triangle");
				System.out.println("5. Construct a Sphere");
				System.out.println("6. Construct a Cube");
				System.out.println("7. Construct a Cone");
				System.out.println("8. Construct a Cylinder");
				System.out.println("9. Construct a Torus");
				System.out.println("10. Exit the program");

				int option = scan.nextInt();
				double radius, length, width, side, base, height, minorRad, majorRad;
				
				switch (option) {
				case 1:
					System.out.println("You have selected a Circle");
					System.out.print("Enter radius: ");
					radius = scan.nextDouble();

					TwoDimensionalShape circle = new Circle(radius);
					System.out.printf("Area of Circle: %.2f", circle.area());
					break;
				case 2:
					System.out.println("You have selected a Rectangle");
					System.out.print("Enter length: ");
					length = scan.nextDouble();
					System.out.print("Enter width: ");
					width = scan.nextDouble();

					TwoDimensionalShape rectangle = new Rectangle(length, width);
					System.out.printf("Area of Rectangle: %.2f", rectangle.area());
					System.out.println("\n" + rectangle.toString() + 
							" and the number of dimensions is has is: " + rectangle.numberOfDimensions());
					break;
				case 3:
					System.out.println("You have selected a Square");
					System.out.print("Enter side length: ");
					side = scan.nextDouble();

					TwoDimensionalShape square = new Square(side);
					System.out.printf("Area of Square: %.2f", square.area());
					break;
				case 4:
					System.out.println("You have selected a Triangle");
					System.out.print("Enter base: ");
					base = scan.nextDouble();
					System.out.print("Enter height: ");
					height = scan.nextDouble();

					TwoDimensionalShape triangle = new Triangle(base, height);
					System.out.printf("Area of Triangle: %.2f", triangle.area());
					break;
				case 5:
					System.out.println("You have selected a Sphere");
					System.out.print("Enter radius: ");
					radius = scan.nextDouble();

					ThreeDimensionalShape sphere = new Sphere(radius);
					System.out.printf("Volume of Sphere: %.2f", sphere.volume() );
					break;
				case 6:
					System.out.println("You have selected a Cube");
					System.out.print("Enter side length: ");
					side = scan.nextDouble();

					ThreeDimensionalShape cube = new Cube(side);
					System.out.printf("Volume of Cube: %.2f", cube.volume());
					System.out.println("\n" + cube.toString() + 
							" and the number of dimensions is has is: " + cube.numberOfDimensions());
					break;
				case 7:
					System.out.println("You have selected a Cone");
					System.out.print("Enter radius: ");
					radius = scan.nextDouble();
					System.out.print("Enter height: ");
					height = scan.nextDouble();

					ThreeDimensionalShape cone = new Cone(radius, height);
					System.out.printf("Volume of Cone: %.2f", cone.volume());
					break;
				case 8:
					System.out.println("You have selected a Cylinder");
					System.out.print("Enter radius: ");
					radius = scan.nextDouble();
					System.out.print("Enter height: ");
					height = scan.nextDouble();

					ThreeDimensionalShape cylinder = new Cylinder(radius, height);
					System.out.printf("Volume of Cylinder: %.2f", cylinder.volume());
					break;
				case 9:
					System.out.println("You have selected a Torus");
					System.out.print("Enter minor radius: ");
					minorRad = scan.nextDouble();
					System.out.print("Enter major radius: ");
					majorRad = scan.nextDouble();

					ThreeDimensionalShape torus = new Torus(minorRad, majorRad);
					System.out.printf("Volume of Torus: %.2f", torus.volume());
					break;
				case 10:
					operating = false;
					System.out.println("Thank you for using the Java Shapes Program!");
					break;
				}
			} catch(InputMismatchException e) {
				System.out.println("Please enter either an integer from 1-10.");
				scan.nextLine();
			}

			if(operating) {
				boolean validEntry = false;
				while(!validEntry) {
					System.out.print("\nWould you like to continue? (Y/N): ");
					String result = scan.next();
					if(result.equalsIgnoreCase("Y")) {
						validEntry = true;
					} else if(result.equalsIgnoreCase("N")) {
						validEntry = true;
						operating = false;
						System.out.println("Thank you for using the Java Shapes Program!");
					} else {
						System.out.println("Sorry I do not understand. Enter Y or N.");
					}
				}
			}
		}
		scan.close();
	}
}