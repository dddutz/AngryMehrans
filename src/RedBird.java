/*
 * File: RedBird.java
 * -------------------
 * Name: Derin Dutz
 * Section Leader: Danielle Kain
 * 
 * This class allows for the use and creation of a Red Bird object.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import acm.graphics.*;

public class RedBird extends Bird implements AngryMehransConstants {

	/**
	 * Constructor: creates a new blue bird (which in this case is a Mehran
	 * clone), and sets a timer. Each time the timer finishes, updates the
	 * velocity of the bird in accordance with the laws of gravity.
	 * 
	 * @param w
	 *            width
	 * @param h
	 *            height
	 */
	public RedBird(double w, double h) {
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

		redBird = new GImage("mehran_circle.png");
		redBird.setSize(width, height);
		add(redBird, 0, 0);
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
		remove(redBird);
		width = w;
		height = h;
		redBird.setSize(width, height);
		add(redBird, 0, 0);
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

	private GImage redBird;
	private double width;
	private double height;
}
