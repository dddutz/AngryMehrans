/*
 * File: MovableObject.java
 * ------------------------
 * Name: Derin Dutz
 * Section Leader: Danielle Kain
 * 
 * This class allows for the use and creation of a movable object
 * and implements gravity functionality.
 */

import acm.graphics.*;

public class MovableObject extends GCompound {

	/**
	 * Sets the x velocity.
	 * 
	 * @param xVelocity
	 *            x velocity
	 */
	public void setXVel(double xVelocity) {
		xVel = xVelocity;
	}

	/**
	 * Gets the x velocity.
	 * 
	 * @return x velocity
	 */
	public double getXVel() {
		return xVel;
	}

	/**
	 * Sets the y velocity.
	 * 
	 * @param yVelocity
	 *            y velocity
	 */
	public void setYVel(double yVelocity) {
		yVel = yVelocity;
	}

	/**
	 * Gets the y velocity.
	 * 
	 * @return y velocity
	 */
	public double getYVel() {
		return yVel;
	}

	/**
	 * Updates the y velocity in accordance with the laws of gravity.
	 * 
	 * @param gravity
	 *            force of gravity
	 */
	public void updateGravity(double gravity) {
		yVel += gravity;
	}

	/**
	 * Moves the object in accordance with the laws of gravity.
	 */
	public void moveWithGravity() {
		this.move(xVel, yVel);
	}

	/* Private instance variables */
	private double xVel;
	private double yVel;
}
