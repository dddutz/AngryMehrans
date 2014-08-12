/*
 * File: RingAnimation.java
 * --------------------
 * Name: Derin Dutz
 * Section Leader: Danielle Kain
 * 
 * This class allows for the use and creation of a Ring Animation object.
 */

import java.util.*;

import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import acm.graphics.*;

public class RingAnimation extends Animation {

	private static final int ROWS = 4;
	private static final int COLUMNS = 4;

	/**
	 * Constructor: reads an into an array of sprites, and then sets a timer.
	 * Each time the timer finishes, adds the next sprite in the array of
	 * sprites. Doing this fast enough produces the effect of animation.
	 * 
	 * @param width
	 *            width of the ring
	 * @param height
	 *            height of the ring
	 */
	public RingAnimation(double width, double height) {
		spritesArray = new ArrayList<GImage>();
		readImage(spritesArray, "ring.png", ROWS, COLUMNS);

		numberOfSprites = spritesArray.size();
		spriteCounter = 0;

		taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNextSprite();
			}
		};

		timer = new Timer(100, taskPerformer);
		timer.setInitialDelay(100);
		timer.start();
	}

	/**
	 * Adds the next sprite in the ArrayList of sprites.
	 */
	private void addNextSprite() {
		if (spriteCounter < numberOfSprites) {
			if (spriteCounter > 0) {
				remove(spritesArray.get(spriteCounter - 1));
			}
			GImage currentImage = spritesArray.get(spriteCounter);
			add(currentImage, -(currentImage.getWidth() / 2.0),
					-(currentImage.getHeight() / 2.0));
			spriteCounter++;
		} else {
			removeAll();
		}
	}

	/* Private instance variables */
	private ActionListener taskPerformer;
	private Timer timer;

	private ArrayList<GImage> spritesArray;
	private int spriteCounter;
	private int numberOfSprites;
}