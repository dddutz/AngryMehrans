/*
 * File: YellowBird.java
 * ---------------------
 * Name: Derin Dutz
 * Section Leader: Danielle Kain
 * 
 * This class allows for the use and creation of a Yellow Bird object.
 */

import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.util.MediaTools;

public class YellowBird extends Bird implements AngryMehransConstants {

	/**
	 * Constructor: creates a new yellow bird (which in this case is a Mehran
	 * clone), and sets a timer. Each time the timer finishes, updates the
	 * velocity of the bird in accordance with the laws of gravity.
	 * 
	 * @param w
	 *            width
	 * @param h
	 *            height
	 */
	public YellowBird(double w, double h) {
		taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!dashingForward) {
					updateGravity(FORCE_GRAVITY);
				}
			}
		};

		timer = new Timer(100, taskPerformer);
		timer.setInitialDelay(100);
		timer.start();

		width = w;
		height = h;

		yellowBird = new GImage("yellow_bird.png");
		yellowBird.setSize(width, height);
		add(yellowBird, 0, 0);
	}

	/**
	 * Dashes the bird forward: sets the y velocity to 0 and multiplies the x
	 * velocity by a constant factor.
	 */
	public void dashForward() {
		AudioClip dashSound = MediaTools.loadAudioClip("dash_sound.wav");
		dashSound.play();
		
		dashingForward = true;
		this.setXVel(getXVel() * 8);
		this.setYVel(0);
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
		remove(yellowBird);
		width = w;
		height = h;
		yellowBird.setSize(width, height);
		add(yellowBird, 0, 0);
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

	private GImage yellowBird;
	private double width;
	private double height;

	private boolean dashingForward = false;
}
