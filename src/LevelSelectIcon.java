/*
 * File: LevelSelectIcon.java
 * --------------------------
 * Name: Derin Dutz
 * Section Leader: Danielle Kain
 * 
 * This class allows for the use and creation of a LevelSelectIcon object.
 */

import java.awt.Color;
import acm.graphics.*;

public class LevelSelectIcon extends GCompound {

	/**
	 * Constructor: draws an icon border and label.
	 * 
	 * @param label
	 *            icon label
	 * @param w
	 *            width
	 * @param h
	 *            height
	 */
	public LevelSelectIcon(String label, double w, double h) {

		width = w;
		height = h;

		levelSelectIconBorder = new GImage("level_select_icon_background.png");
		levelSelectIconBorder.setSize(w, h);
		add(levelSelectIconBorder, 0, 0);

		levelSelectIconLabel = new GLabel(label);
		levelSelectIconLabel.setFont("Helvetica-32");
		levelSelectIconLabel.setColor(Color.BLACK);
		add(levelSelectIconLabel,
				(width / 2.0) - (levelSelectIconLabel.getWidth() / 2.0),
				(height / 2.0) + (levelSelectIconLabel.getAscent() / 2.0));
	}

	/**
	 * Sets the label color.
	 * 
	 * @param color
	 *            color
	 */
	public void setLabelColor(Color color) {
		levelSelectIconLabel.setColor(color);
	}

	/**
	 * Returns the width.
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Returns the height.
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Gets a String representation of the label.
	 * 
	 * @return label String
	 */
	public String getLabelString() {
		return levelSelectIconLabel.getLabel();
	}

	/* Private instance variables */
	private GLabel levelSelectIconLabel;
	private GImage levelSelectIconBorder;
	private double width;
	private double height;
}
