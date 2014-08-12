/*
 * File: BasicExplosion.java
 * -------------------------
 * Name: Derin Dutz
 * Section Leader: Danielle Kain
 * 
 * This class allows for the use and creation of a BasicExplosion object.
 */

import java.util.*;

import javax.swing.Timer;

import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import acm.graphics.*;
import acm.util.MediaTools;

public class BasicExplosion extends Explosion {

	private static final int ROWS = 5;
	private static final int COLUMNS = 5;

	/**
	 * Constructor: reads an into an array of sprites, and then sets a timer.
	 * Each time the timer finishes, adds the next sprite in the array of
	 * sprites. Doing this fast enough produces the effect of animation.
	 * 
	 * @param w
	 *            width of the explosion
	 * @param h
	 *            height of the explosion
	 */
	public BasicExplosion(double w, double h) {
		width = w;
		height = h;
		
		AudioClip explosionSound = MediaTools.loadAudioClip("bomb_explosion.wav");
		explosionSound.play();

		spritesArray = new ArrayList<GImage>();
		readImage(spritesArray, "explosion.png", ROWS, COLUMNS);

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
			currentImage.setSize(width, height);
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

	private double width;
	private double height;
}
