/**
 * CMSC335 Final Project
 * @author Brandon Baird
 * @DateModified: 05/05/2025
 * @Purpose: Car class is the representation
 * of the object Car. Every car has a number, 
 * horizontal position, and speed.
 */

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Car {
	private int carNum;
	private int xPos;
	private int speed;
	
	
	private Timer timer;
	private List<TrafficLight> trafficLights;
	
	private JPanel carPanel;
	private JLabel speedLabel;
	private JLabel posLabel;
	
	public Car(int carNumber) {
		//Car speeds are randomized between (40-60 m/s) * 3.6 = (36-216 km/h).
		this.speed = (int) (Math.random() * 50 + 10);
		this.xPos = 0;
		this.carNum = carNumber;
		
		
        //Car Info Panel, includes all instance variables for Car.
		//Displays the status of each instance variable.
        carPanel = new JPanel();
        carPanel.setBorder(BorderFactory.createTitledBorder("Car " + carNumber));
        carPanel.setLayout(new GridLayout(2, 1));

        posLabel = new JLabel("X: " + xPos + " m");
        speedLabel = new JLabel("Speed: " + speed * 3.6 + " km/h");

        carPanel.add(posLabel);
        carPanel.add(speedLabel);
        //if not red light, move and update xPos.
        timer = new Timer(1000, e -> {
        	if(!isRedLight()) {
        		xPos += speed;
        		posLabel.setText("X: " + xPos + " m");
        	}
        });
	}
	
	public JPanel getCarPanel() {
        return carPanel;
    }
	
	public int getXPos() {
		return xPos;
	}
	
	public int getCarNum() {
		return carNum;
	}

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void reset() {
        timer.stop();
        xPos = 0;
        posLabel.setText("X: 0 m");
    }
    
    public void setTrafficLights(List<TrafficLight> lights) {
    	this.trafficLights  = lights;
    }
    
    private boolean isRedLight() {
    	if (trafficLights == null) return false;

        for (TrafficLight l : trafficLights) {
            int distance = l.getXPos() - xPos;
            
            //each light is spaced 500m instead of 1000m
            if (distance > 0 && distance <= 500) {
                if (l.getLightColor() == TrafficLight.LightColor.RED) {
                    return true;
                }
            }
        }
        return false;
    }
}
