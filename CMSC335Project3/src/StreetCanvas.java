/**
 * CMSC335 Final Project
 * @author Brandon Baird
 * @DateModified: 05/05/2025
 * @Purpose: StreetCanvas class provides
 * the illustration of the cars and intersections.
 */
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StreetCanvas extends JPanel {
	//every 250px spacing is 1000m.
	private static final double SCALE = 0.25;
	private List<Car> cars;
	private List<TrafficLight> lights;

    public StreetCanvas(List<Car> cars, List<TrafficLight> lights) {
        this.cars = cars;
        this.lights = lights;
        setBackground(Color.GRAY);
        //set this to something big so the cars can move irregardless of window space
        setPreferredSize(new Dimension(1000, 1000));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //vertical offset
        int y = 30;
        
        for (Car car : cars) {
            int x = (int) (car.getXPos() * SCALE);
            g.setColor(Color.BLACK);
            g.fillRect(x, y, 40, 20);
            
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 12));
            g.drawString("Car " + car.getCarNum(), x, y - 5);
            //vertical spacing between cars
            y += 60;
        }
        
        int trafficLightNum = 0;
        //Intersection Lines
        for (TrafficLight light : lights) {
        	trafficLightNum++;
        	//explicit int cast from double
            int x = (int) (light.getXPos() * SCALE);
            Color color = light.getLightColor().getColor();
            //draw intersection line
            g.setColor(color);
            g.fillRect(x, 0, 3, getHeight());
            
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 12));
            g.drawString("Light " + trafficLightNum, x - 10, 15);
        }
    }
}
