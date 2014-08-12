/*
 * File: Slingshot.java
 * --------------------
 * Name: Derin Dutz
 * Section Leader: Danielle Kain
 * 
 * This class allows for the use and creation of a Slingshot object.
 */

import acm.graphics.*;

public class Slingshot extends GCompound {

	/**
	 * Constructor: creates a new slingshot.
	 * 
	 * @param w
	 *            width
	 * @param h
	 *            height
	 */
	public Slingshot(double w, double h) {

		width = w;
		height = h;

		slingshot = new GImage("slingshot.png");
		slingshot.setSize(width, height);
		add(slingshot, 0, 0);
	}

	/**
	 * Sets the bounds of the slingshot.
	 * 
	 * @param w
	 *            width
	 * @param h
	 *            height
	 */
	public void setSlingshotBounds(double w, double h) {
		remove(slingshot);
		width = w;
		height = h;
		slingshot.setSize(width, height);
		add(slingshot, 0, 0);
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

	/* Private instance variables */
	private GImage slingshot;
	private double width;
	private double height;

}
