/*
 * File: WoodBlock.java
 * -------------------
 * Name: Derin Dutz
 * Section Leader: Danielle Kain
 * 
 * This class allows for the use and creation of a Wood Block object.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import acm.graphics.*;

public class WoodBlock extends Block implements AngryMehransConstants {

	/**
	 * Constructor: creates a new wood block, and sets a timer. Each time the
	 * timer finishes, updates the velocity of the block in accordance with the
	 * laws of gravity.
	 * 
	 * @param w
	 *            width
	 * @param h
	 *            height
	 */
	public WoodBlock(double w, double h) {
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
		woodBlock = new GImage("wood_texture.png");
		woodBlock.setSize(width, height);
		add(woodBlock, 0, 0);
	}

	/**
	 * Sets the bounds of the block.
	 * 
	 * @param w
	 *            width
	 * @param h
	 *            height
	 */
	public void setBlockBounds(double w, double h) {
		remove(woodBlock);
		width = w;
		height = h;
		woodBlock.setSize(width, height);
		add(woodBlock, 0, 0);
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

	private double width;
	private double height;
	private GImage woodBlock;
}
