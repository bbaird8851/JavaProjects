/**
 * CMSC335 Final Project
 * @author Brandon Baird
 * @DateModified: 05/05/2025
 * @Purpose: TrafficLightController
 * controls the state of the traffic
 * light and allows for delay like how
 * a real life intersection would.
 */

import javax.swing.*;

//order follows red -> green -> yellow -> red.
public class TrafficLightController {
	private TrafficLight trafficPanel;
	private TrafficLight.LightColor[] lightSequence = {
			TrafficLight.LightColor.RED,
			TrafficLight.LightColor.GREEN,
			TrafficLight.LightColor.YELLOW
	};
	
	private int currIndex = 0;
	private Timer timer;
	
	public TrafficLightController(TrafficLight trafficPanel, int interval, int delay) {
		this.trafficPanel= trafficPanel;
		//interval is in milliseconds
		trafficPanel.setLightColor(lightSequence[0]);
		
		//swing timer that sets the light sequence,
		//when (currIndex + 1) % 3 = 0, loops back to RED.
		timer = new Timer(interval, e ->{
			trafficPanel.setLightColor(lightSequence[currIndex]);
			currIndex = (currIndex + 1) % lightSequence.length;
		});
		timer.setInitialDelay(delay);
	}
	
	public void start() {
		timer.start();
	}
	
	public void stop() {
		timer.stop();
	}
	
	public void reset() {
		timer.stop();
		currIndex = 0;
		trafficPanel.setLightColor(lightSequence[currIndex]);
	}
}
