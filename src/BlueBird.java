/*
 * File: BlueBird.java
 * -------------------
 * Name: Derin Dutz
 * Section Leader: Danielle Kain
 * 
 * This class allows for the use and creation of a Blue Bird object.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import acm.graphics.GImage;

public class BlueBird extends Bird implements AngryMehransConstants {

	/**
	 * Constructor: creates a new blue bird (which in this case is an image of
	 * Karel), and sets a timer. Each time the timer finishes, updates the
	 * velocity of the bird in accordance with the laws of gravity.
	 * 
	 * @param w
	 *            width
	 * @param h
	 *            height
	 */
	public BlueBird(double w, double h) {
		taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGravity(FORCE_GRAVITY);
			}
		};

		timer = new Timer(100, taskPerformer);
		timer.setInitialDelay(100);
		timer.start();

		width = w;
		height = h;

		blueBird = new GImage("karel.png");
		blueBird.setSize(width, height);
		add(blueBird, 0, 0);
	}

	/**
	 * Drops a bomb: changes the image of the bird to that of a bomb and sets
	 * the x velocity to 0.
	 */
	public void dropBomb() {
		if (!isBombDropped) {
			isBombDropped = true;
			blueBird.setImage("bomb.png");
			blueBird.setSize(width, height);
			this.setXVel(0);
		}
	}

	/**
	 * Sets the bounds of the bird.
	 * 
	 * @param w
	 *            width
	 * @param h
	 *            height
	 */
	public void setBirdBounds(double w, double h) {
		remove(blueBird);
		width = w;
		height = h;
		blueBird.setSize(width, height);
		add(blueBird, 0, 0);
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
	private ActionListener taskPerformer;
	private Timer timer;

	private GImage blueBird;
	private double width;
	private double height;

	private boolean isBombDropped = false;
}