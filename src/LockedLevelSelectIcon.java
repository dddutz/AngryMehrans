/*
 * File: LockedLevelSelectIcon.java
 * --------------------------------
 * Name: Derin Dutz
 * Section Leader: Danielle Kain
 * 
 * This class allows for the use and creation of a LockedLevelSelectIcon object.
 */

import acm.graphics.*;

public class LockedLevelSelectIcon extends GCompound {

	/**
	 * Constructor: draws an icon border and label.
	 * 
	 * @param w
	 *            width
	 * @param h
	 *            height
	 */
	public LockedLevelSelectIcon(double w, double h) {

		width = w;
		height = h;

		levelSelectIconBorder = new GImage("level_select_icon_background.png");
		levelSelectIconBorder.setSize(w, h);
		add(levelSelectIconBorder, 0, 0);

		lockIcon = new GImage("lock_icon.png");
		lockIcon.setSize(w * 0.65, h * 0.65);
		add(lockIcon,
				(levelSelectIconBorder.getWidth() - lockIcon.getWidth()) / 2.0,
				(levelSelectIconBorder.getHeight() - lockIcon.getHeight()) / 2.0);

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
	private GImage levelSelectIconBorder;
	private GImage lockIcon;
	private double width;
	private double height;
}