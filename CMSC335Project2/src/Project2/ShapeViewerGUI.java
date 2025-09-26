/**
 * @author Brandon Baird
 * @date 04/04/25
 * 
 * @function creates a GUI for the user to
 * select from a variety of shapes to view
 * an illustration along with the shapes'
 * area or volume.
 */

package Project2;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.InputStream;

public class ShapeViewerGUI extends Application {
	private ComboBox<String> shapesMenu;
	private ImageView image;
	private Text prompt;
	private Shape shape;
	private double radius, length, width, side, base, height, minorRad, majorRad;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Shape Viewer");
		shapesMenu =  new ComboBox<>();
		shapesMenu.getItems().addAll("Circle","Square","Triangle","Rectangle",
									 "Sphere","Cube","Cone","Torus");
		prompt = new Text("Select a shape from the dropdown menu.");
		
		image = new ImageView();
		image.setFitWidth(450);
		image.setFitHeight(450);
		image.setPreserveRatio(true);
		
		//After button pressed, select the appropriate shape object and display area/volume and image.
		shapesMenu.setOnAction(e -> {
			String selectedShape = shapesMenu.getValue();
			chooseShape(selectedShape);
		});

		VBox layout = new VBox(10, prompt, shapesMenu, image);
		layout.setPadding(new Insets(10));
		layout.setAlignment(Pos.TOP_CENTER);
		Scene scene = new Scene(layout, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	private void chooseShape(String selectedShape) {
		String imageFileName = null;
		/*
		 * For shapes that share dimensions, they are grouped together.
		 * 
		 */
		try {
			switch (selectedShape) {
			case "Circle", "Sphere" :
				radius = getDimensions("Enter the radius of the " + selectedShape + ":");
			if (selectedShape.equals("Circle")) {
				shape = new Circle(radius);
				prompt.setText(String.format("Area: %.2f", ((Circle) shape).area()));
				imageFileName = "circle.png";
			} else {
				shape = new Sphere(radius);
				prompt.setText(String.format("Volume: %.2f", ((Sphere) shape).volume()));
				imageFileName = "sphere.png";
			}
			break;
			
			case "Square", "Cube" :
				side = getDimensions("Enter the side length of the " + selectedShape + ":");
			if (selectedShape.equals("Square")) {
				shape = new Square(side);
				prompt.setText(String.format("Area: %.2f", ((Square) shape).area()));
				imageFileName = "square.png";
			} else {
				shape = new Cube(side);
				prompt.setText(String.format("Volume: %.2f", ((Cube) shape).volume()));
				imageFileName = "cube.png";
			}
			break;
			
			case "Cone", "Cylinder" :
				radius = getDimensions("Enter the radius of the " + selectedShape + ": ");
				height = getDimensions("Enter the height of the " + selectedShape + ": ");
			if (selectedShape.equals("Cone")) {
				shape = new Cone(radius, height);
				prompt.setText(String.format("Volume: %.2f", ((Cone) shape).volume()));
				imageFileName = "cone.png";
			} else {
				shape = new Cylinder(radius, height);
				prompt.setText(String.format("Volume: %.2f", ((Cylinder) shape).volume()));
				imageFileName = "cylinder.png";
			}
			break;
			
			case "Rectangle" :
				length = getDimensions("Enter the length of the rectangle: ");
				width = getDimensions("Enter the width of the rectangle: ");
				shape = new Rectangle(length, width);
				imageFileName = "rectangle.png";
				prompt.setText(String.format("Area: %.2f", ((Rectangle) shape).area()));
				break;
				
			case "Triangle" :
				base = getDimensions("Enter the base of the triangle:");
				height = getDimensions("Enter the height of the triangle:");
				shape = new Triangle(base, height);
				imageFileName = "triangle.png";
				prompt.setText(String.format("Area: %.2f", ((Triangle) shape).area()));
				break;
				
			case "Torus" :
				minorRad = getDimensions("Enter the minor radius of the torus:");
				majorRad = getDimensions("Enter the major radius of the torus:");
				shape = new Torus(minorRad, majorRad);
				imageFileName = "torus.png";
				prompt.setText(String.format("Volume: %.2f", + ((Torus) shape).volume()));
				break;
			}

			if (imageFileName != null) {
				InputStream imageStream = getClass().getResourceAsStream("/images/" + imageFileName);
                if (imageStream != null) {
                    image.setImage(new Image(imageStream));
                } else {
                    prompt.setText(prompt.getText() + "\nInvalid file name, image not found.");
                }
			}
		} catch(NumberFormatException f) {
			System.out.println("This is not a valid number format.");
		}
	}
	
	/*
	 * create a separate pop-up to collect and parse the user inputs.
	 */
	private double getDimensions(String str) {
		TextField textField = new TextField();
		textField.setPromptText("Enter dimension");

		Alert prompt = new Alert(Alert.AlertType.CONFIRMATION);
		prompt.setTitle("Enter in the format 0.00 or 0");
		prompt.setHeaderText(str);
		prompt.getDialogPane().setContent(textField);
		prompt.showAndWait();

		return Double.parseDouble(textField.getText());
	}

}
