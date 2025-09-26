/**
 * CMSC335 Final Project
 * @author Brandon Baird
 * @DateModified: 05/05/2025
 * @Purpose: TrafficSimGUI creates the GUI
 * including the TrafficLight Pane, Car Pane and
 * CarCanvas. Displays the status and information
 * of these objects.
 */

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TrafficSimGUI {
	
	//GUI Overlay
	private JFrame frame;
	private JLabel timeLabel;
	private JButton startButton, stopButton, pauseButton;
	private JButton addCarButton, addIntersectionButton;

	//program status
	private enum Status{STARTED, STOPPED, PAUSED, FINISHED}
	private Status programStatus = Status.FINISHED;
	
	//Timer
	private Timer timer;
	private final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm:ss");
	
	//Traffic Lights, Cars & Intersections
	private JPanel carsPanel;
	private JPanel trafficLightPanel;
	private List<TrafficLight> trafficLightList;
	private List<TrafficLightController> controllerList;
	private List<Car> carList;

	//visualizing Cars
	private StreetCanvas streetCanvas;
	
	public TrafficSimGUI() {
		frame = new JFrame("Traffic Simulation");
		frame.setSize(1600, 800);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		JPanel topPanel = new JPanel(new BorderLayout());
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		//initialize the buttons
		startButton = new JButton("Start");
		pauseButton = new JButton("Pause");
		stopButton = new JButton("Stop");
		addCarButton = new JButton("Add Car");
		addIntersectionButton = new JButton("Add Intersection");

		buttonsPanel.add(startButton);
		buttonsPanel.add(pauseButton);
		buttonsPanel.add(stopButton);
		buttonsPanel.add(addCarButton);
		buttonsPanel.add(addIntersectionButton);
		
		topPanel.add(buttonsPanel, BorderLayout.WEST);
		
		timeLabel = new JLabel("Time: 00:00:00", SwingConstants.RIGHT);
		timeLabel.setFont(new Font("Arial", Font.BOLD, 24));
		topPanel.add(timeLabel, BorderLayout.EAST);

		frame.add(topPanel, BorderLayout.NORTH);

		JPanel simulationPanel = new JPanel(new BorderLayout());
		simulationPanel.setPreferredSize(new Dimension(100, 100));
		
		//initialize the Lists
		trafficLightList = new ArrayList<>();
		controllerList = new ArrayList<>();
		carList = new ArrayList<>();
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		
		//Display the model of Cars and Intersections
		streetCanvas = new StreetCanvas(carList, trafficLightList);
		streetCanvas.setPreferredSize(new Dimension(1000, 200));

		JScrollPane canvasScroll = new JScrollPane(streetCanvas);
		simulationPanel.add(canvasScroll, BorderLayout.CENTER);
		
		//start timer
		startClock();
	
		//Traffic Light Display LEFT
		trafficLightPanel = new JPanel();
		trafficLightPanel.setLayout(new BoxLayout(trafficLightPanel, BoxLayout.Y_AXIS));
		JScrollPane leftScroll = new JScrollPane(trafficLightPanel);
		leftScroll.setPreferredSize(new Dimension(200,400));
		frame.add(leftScroll, BorderLayout.WEST);
		
		trafficLightManagement();
		
		//Cars Display RIGHT
		carsPanel = new JPanel();
		carsPanel.setLayout(new BoxLayout(carsPanel, BoxLayout.Y_AXIS));
		JScrollPane rightScroll = new JScrollPane(carsPanel);
		rightScroll.setPreferredSize(new Dimension(200,400));
		frame.add(rightScroll, BorderLayout.EAST);
		
		frame.add(simulationPanel, BorderLayout.CENTER);
		
		//Button actions
		startButton.addActionListener(e -> startOnPress());
		pauseButton.addActionListener(e -> pauseOnPress());
		stopButton.addActionListener(e -> stopOnPress());
		addCarButton.addActionListener(e -> addCarOnPress());
		addIntersectionButton.addActionListener(e -> addIntersectionOnPress());
	}

	private void startOnPress() {
		switch(programStatus) {
		case FINISHED, STOPPED :
			System.out.println("Starting Traffic Simulatior...");
		programStatus = Status.STARTED;
		startButton.setText("Continue");
		break;

		case PAUSED :
			System.out.println("Resuming Simulation...");
			programStatus = Status.STARTED;
			
			default:
				//Suppressing warning
		}
		
		for(TrafficLightController t : controllerList) {
			t.start();
		}
		for(Car c : carList) {
			c.start();
		}
		
		Timer repaintTimer = new Timer(100, e -> streetCanvas.repaint());
		repaintTimer.start();
	}

	private void pauseOnPress() {
		if(programStatus == Status.STARTED) {
			System.out.println("Pausing Simulation...");
			programStatus = Status.PAUSED;
		}
		
		for(TrafficLightController t : controllerList) {
			t.stop();
		}
		for(Car c : carList) {
			c.stop();
		}
	}

	private void stopOnPress() {
		if(programStatus != Status.STOPPED) {
			System.out.println("Stopping Simuation...");
			programStatus = Status.STOPPED;
			startButton.setText("Start");
		}
		
		for(TrafficLightController t : controllerList) {
			t.stop();
			t.reset();
		}
		for(Car c : carList) {
			c.stop();
			c.reset();
		}
	}
	
	private void addCarOnPress() {
		int carNum = carList.size() + 1;
	    Car car = new Car(carNum);
	    
	    //updates attention to trafficLights for new cars added.
	    car.setTrafficLights(trafficLightList);
	    
	    carList.add(car);
	    carsPanel.add(car.getCarPanel());
	    carsPanel.revalidate();
	    streetCanvas.repaint();
	}
	
	private void addIntersectionOnPress() {
		int intersectionNum = trafficLightList.size() + 1;
		int xPos = 500 * intersectionNum;
		TrafficLight trafficLight = new TrafficLight("Intersection " + intersectionNum, xPos);
		
	    trafficLightList.add(trafficLight);
	    trafficLightPanel.add(trafficLight);
	    
	    TrafficLightController newController = new TrafficLightController(trafficLight, 3000, controllerList.size() * 300);
	    controllerList.add(newController);

	    // Start if simulation is running
	    if (programStatus == Status.STARTED) {
	        newController.start();
	    }
	    
	    for(Car c : carList) {
	    	c.setTrafficLights(trafficLightList);
	    }
	    
	    //resize the canvas
	    int newCanvasWidth = 250 * trafficLightList.size() + 250;
	    streetCanvas.setPreferredSize(new Dimension(newCanvasWidth, 300));
	    streetCanvas.revalidate(); 
	    
	    trafficLightPanel.revalidate();
	    trafficLightPanel.repaint();
	}
	
	private void startClock() {
		timer = new Timer(1000, e -> {
			LocalTime now = LocalTime.now();
			timeLabel.setText("Time: " + now.format(timeFormat));
		});
		timer.start();
	}
	
	private void trafficLightManagement() {
		for(int i = 0; i < 3; i++) {
			//500m spacing
			int xPos = 500 * (i + 1);
			TrafficLight panel = new TrafficLight("Intersection " + (i + 1), xPos);
			
			trafficLightList.add(panel);
			trafficLightPanel.add(panel);
			
			//control the traffic lights
			TrafficLightController controller = new TrafficLightController(panel, 3000, i * 300);
			controllerList.add(controller);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new TrafficSimGUI());
	}
}
