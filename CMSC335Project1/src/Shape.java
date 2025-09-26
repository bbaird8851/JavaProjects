/**
 * @author Brandon Baird
 * Date: 3/21/2025
 * 
 * Purpose: Shape Class is the abstract class
 * that will be associated with all the
 * various shapes. A Shape has a number of 
 * dimensions.
 */
public abstract class Shape {
	public String toString() {
		return "This Object is a Shape.";
	}
	abstract int numberOfDimensions();
}

/*
 * Two Dimensional Shape is a Shape
 * Contains the area function and the
 * number of dimensions is 2.
 */
abstract class TwoDimensionalShape extends Shape {
	abstract double area();
	
	@Override
	public String toString() {
		return "This Shape is 2D.";
	}
	
	@Override
	int numberOfDimensions() {return 2;}
}

/*
 * Three Dimensional Shape is a Shape
 * Contains the volume function and the
 * number of dimensions is 3.
 */
abstract class ThreeDimensionalShape extends Shape {
	abstract double volume();
	
	@Override
	public String toString() {
		return "This Shape is 3D.";
	}
	
	@Override
	int numberOfDimensions() {return 3;}
}

class Circle extends TwoDimensionalShape {
	private double radius;
	
	Circle(double radius) {this.radius = radius;}

	@Override
	double area() {return Math.PI * Math.pow(radius, 2);}
}

class Square extends TwoDimensionalShape {
	private double side;
	
	Square(double side) {this.side = side;}

	@Override
	double area() {return Math.pow(side, 2);}
}

class Triangle extends TwoDimensionalShape {
	private double base, height;
	
	Triangle(double base, double height){
		this.base = base;
		this.height = height;
	}

	@Override
	double area() {return ((base * height)/2);}
}

class Rectangle extends TwoDimensionalShape {
	private double length, width;
	
	Rectangle(double length, double width) {
		this.length = length;
		this.width = width;
	}

	@Override
	double area() {return length * width;}
}

class Sphere extends ThreeDimensionalShape {
	private double radius;
	
	Sphere(double radius) {this.radius = radius;}

	@Override
	double volume() {return (4.0 / 3) * Math.PI * Math.pow(radius, 3);}
}

class Cube extends ThreeDimensionalShape {
	private double side;
	
	Cube(double side) {this.side = side;}

	@Override
	double volume() {return Math.pow(side, 3);}
}

class Cone extends ThreeDimensionalShape {
	private double radius, height;
	
	Cone(double radius, double height) {
		this.radius = radius;
		this.height = height;
	}

	@Override
	double volume() {return Math.PI * Math.pow(radius, 2) * (height/3);}
}

class Cylinder extends ThreeDimensionalShape {
	private double radius, height;
	Cylinder(double radius, double height) {
		this.radius = radius;
		this.height = height;
	}

	@Override
	double volume() {return Math.PI * Math.pow(radius, 2) * height;}
}

class Torus extends ThreeDimensionalShape {
	private double minRadius, majRadius;
	Torus(double minRadius, double majRadius){
		this.minRadius = minRadius;
		this.majRadius = majRadius;
	}

	@Override
	double volume() {return (Math.PI * Math.pow(minRadius, 2)) * (2 * Math.PI * majRadius);}
}
