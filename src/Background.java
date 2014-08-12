/*
 * File: Background.java
 * ---------------------
 * Name: Derin Dutz
 * Section Leader: Danielle Kain
 * 
 * This class allows for the use and creation of a Background object.
 */

import acm.graphics.*;

public class Background extends GCompound {

	/**
	 * Constructor: creates a new background GImage.
	 * 
	 * @param w
	 *            width
	 * @param h
	 *            height
	 */
	public Background(double w, double h) {
		width = w;
		height = h;

		backgroundFile = "splash_screen_background.jpg";
		background = new GImage(backgroundFile);
		background.setSize(width, height);
		add(background, 0, 0);
	}

	/**
	 * Constructor: creates a new backgroundGImage.
	 * 
	 * @param backgroundFileName
	 *            file name
	 * @param w
	 *            width
	 * @param h
	 *            height
	 */
	public Background(String backgroundFileName, double w, double h) {
		width = w;
		height = h;

		backgroundFile = backgroundFileName;
		background = new GImage(backgroundFile);
		background.setSize(width, height);
		add(background, 0, 0);
	}

	/**
	 * Changes the background: keeps track of which image is currently up and
	 * displays the next image when called. Loops to start when all the images
	 * have been cycled.
	 * 
	 * @return background image file name
	 */
	public String changeBackground() {
		backgroundCounter++;
		switch (backgroundCounter) {
		case 0:
			backgroundFile = "splash_screen_background.jpg";
			break;
		case 1:
			backgroundFile = "abstract_background.jpg";
			break;
		case 2:
			backgroundFile = "blue_sky_background.jpg";
			break;
		case 3:
			backgroundFile = "sky_mountain_background.jpg";
			break;
		case 4:
			backgroundFile = "harry_potter_castle_background.jpg";
			break;
		case 5:
			backgroundFile = "castle_background1.jpg";
			break;
		default:
			backgroundFile = "splash_screen_background.jpg";
			backgroundCounter = 0;
			break;
		}

		background.setImage(backgroundFile);
		background.setSize(width, height);
		return backgroundFile;
	}

	/**
	 * Sets the bounds.
	 * 
	 * @param w
	 *            width
	 * @param h
	 *            height
	 */
	public void setBackgroundBounds(double w, double h) {
		remove(background);
		width = w;
		height = h;
		background.setSize(width, height);
		add(background, 0, 0);
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

	/* Instance variables */
	private int backgroundCounter = 0;
	public String backgroundFile;
	private GImage background;
	private double width;
	private double height;

}
