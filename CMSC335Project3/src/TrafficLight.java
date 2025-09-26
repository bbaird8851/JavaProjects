/**
 * CMSC335 Final Project
 * @author Brandon Baird
 * @DateModified: 05/05/2025
 * @Purpose: TrafficLight extends JPanel
 * it is a panel that displays the status
 * of the Traffic Lights.
 */

import javax.swing.*;
import java.awt.*;

public class TrafficLight extends JPanel {
	
	//LightColor enum maps to Color object, allows
	//functionality for obtaining color.
	enum LightColor {
		RED(Color.RED), YELLOW(Color.YELLOW), GREEN(Color.GREEN);

		private Color color;

		LightColor(Color c) {
			this.color = c;
		}
		
		Color getColor() {return color;}
	}

	private JLabel nameLabel;
	private JPanel lightIndicator;
	private int xPos;
	
	public TrafficLight(String name) {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(150,100));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		nameLabel = new JLabel(name, SwingConstants.CENTER);
		nameLabel.setFont(new Font("Arial", Font.BOLD, 12));
		
		lightIndicator = new JPanel();
		lightIndicator.setPreferredSize(new Dimension(50,50));
		lightIndicator.setBackground(LightColor.RED.getColor());
		
		JPanel centerPanel = new JPanel();
		centerPanel.add(lightIndicator);
		
		add(nameLabel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		
	}
	
	public TrafficLight(String name, int xPos) {
		this(name);
		this.xPos = xPos;
	}
	
	public int getXPos() {
		return xPos;
	}
	
	public LightColor getLightColor() {
        Color curr = lightIndicator.getBackground();
        for (LightColor c : LightColor.values()) {
            if (c.getColor().equals(curr)) {
                return c;
            }
        }
        return null;
    }
	
	public void setLightColor(LightColor color) {
		lightIndicator.setBackground(color.getColor());
	}
		
}
