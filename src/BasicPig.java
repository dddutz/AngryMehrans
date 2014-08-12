/*
 * File: BasicPig.java
 * -------------------
 * Name: Derin Dutz
 * Section Leader: Danielle Kain
 * 
 * This class allows for the use and creation of a Basic Pig object.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import acm.graphics.*;

public class BasicPig extends Pig implements AngryMehransConstants {

	/**
	 * Constructor: creates a new basic pig (which in this case is an image of
	 * the Mona Lisa), and sets a timer. Each time the timer finishes, updates
	 * the velocity of the pig in accordance with the laws of gravity.
	 * 
	 * @param w
	 *            width
	 * @param h
	 *            height
	 */
	public BasicPig(double w, double h) {
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

		basicPig = new GImage("framed_mona_lisa.jpg");
		basicPig.setSize(width, height);
		add(basicPig, 0, 0);
	}

	/**
	 * Sets the bounds of the pig.
	 * 
	 * @param w
	 *            width
	 * @param h
	 *            height
	 */
	public void setPigBounds(double w, double h) {
		remove(basicPig);
		width = w;
		height = h;
		basicPig.setSize(width, height);
		add(basicPig, 0, 0);
	}

	/**
	 * Returns the width of the pig.
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Returns the height of the pig.
	 */
	public double getHeight() {
		return height;
	}

	/* Private instance variables */
	private ActionListener taskPerformer;
	private Timer timer;

	private GImage basicPig;
	private double width;
	private double height;

}
