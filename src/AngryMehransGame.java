/*
 * File: AngryMehransGame.java
 * ---------------------------
 * Name: Derin Dutz
 * Section Leader: Danielle Kain
 * 
 * Plays the Angry Mehrans game.
 */

import acm.program.*;
import acm.util.ErrorException;
import acm.util.MediaTools;
import acm.graphics.*;
import acm.io.IODialog;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.swing.JFileChooser;

public class AngryMehransGame extends GraphicsProgram implements
		AngryMehransConstants {

	/**
	 * Constructor: does nothing.
	 */
	public AngryMehransGame() {

	}

	/**
	 * Runs the game: goes through the initial setup, gets the player name, and
	 * loops through displaying the level select screen and playing the levels.
	 */
	public void run() {
		initialSetup();
		String playerName = getPlayerName();
		if (AngryMehrans.isPlayingSelectedLevel) {
			String selectedFileName = openFileReaderUsingChooser();
			playLevel(playerName, selectedFileName);
		} else {
			while (true) {
				displayLevelSelect();
				while (isOnLevelSelectScreen) {
					BasicExplosion explosionLeft = new BasicExplosion(
							getWidth() * 0.05, getWidth() * 0.05);
					add(explosionLeft, getWidth() * (360.0 / 1021.0),
							(getHeight() * 0.15));
					BasicExplosion explosionRight = new BasicExplosion(
							getWidth() * 0.05, getWidth() * 0.05);
					add(explosionRight, getWidth() * (660.0 / 1021.0),
							(getHeight() * 0.15));
					pause(2000);
					remove(explosionLeft);
					remove(explosionRight);
				}
				String levelFile = getLevelFileFromLevelNumber(levelNumber);
				if (levelFile.equals("level_01.txt")) {
					level1Story();
				}
				playLevel(playerName, levelFile);
			}
		}
	}

	/**
	 * Level 1 Story: displays the background story for the game.
	 */
	private void level1Story() {
		removeAll();
		drawBackground("mehran_2012.jpg");
		GLabel line1 = new GLabel(
				"\"If there's one piece of advice I'd give you on the eve of your graduation,");
		GLabel line2 = new GLabel(
				"it's that the only real currency you have is your time,\"");
		GLabel line3 = new GLabel(
				"said esteemed associate professor Mehran Sahami at the 2013 Class Day Lecture.");
		GLabel line4 = new GLabel("Someone in the crowd defiantly");
		GLabel line5 = new GLabel("raised their hand, undoubtedly");
		GLabel line6 = new GLabel("to ask a pertinent question...");

		line1.setFont("Helvetica-36");
		line1.setColor(Color.WHITE);
		line2.setFont("Helvetica-36");
		line2.setColor(Color.WHITE);
		line3.setFont("Helvetica-36");
		line3.setColor(Color.WHITE);
		line4.setFont("Helvetica-36");
		line4.setColor(Color.WHITE);
		line5.setFont("Helvetica-36");
		line5.setColor(Color.WHITE);
		line6.setFont("Helvetica-36");
		line6.setColor(Color.WHITE);
		add(line1, 5, 50);
		add(line2, 5, 50 + line1.getHeight());
		add(line3, 5, 50 + line1.getHeight() * 2);
		add(line4, 5, 50 + line1.getHeight() * 4);
		add(line5, 5, 50 + line1.getHeight() * 5);
		add(line6, 5, 50 + line1.getHeight() * 6);

		waitForClick();

		line1.setLabel("Always at the ready, Mehran withdrew a bag of Snickers from his jacket.");
		line2.setLabel("He tore it open and attempted to throw a bar to the inquiring person.");
		line3.setLabel("The bar fell short, and careened off of President John Hennessy's forehead.");
		line4.setLabel("The entire audience laughed");
		line5.setLabel("and sneered and gibed");
		line6.setLabel("and jeered.");

		waitForClick();

		AngryMehrans.stopSoundtrack();
		AudioClip starWarsClip = MediaTools.loadAudioClip("imp_march.wav");
		starWarsClip.loop();

		removeAll();
		drawBackground("mehran_lightsaber.jpg");
		line1.setLabel("Mehran was pissed. He got angrier and angrier and angrier.");
		line2.setLabel("This was the last straw. He stormed off the stage and retreated");
		line3.setLabel("to his office. He hatched a plan. The world was going to pay.");
		line4.setLabel("He knew what to do. He had thought about it since he visited France as a kid.");
		line5.setLabel("There was little mankind cherished");
		line6.setLabel("more than the MONA LISA!");
		line1.setColor(Color.BLACK);
		line2.setColor(Color.BLACK);
		line3.setColor(Color.BLACK);
		line4.setColor(Color.BLACK);
		line5.setColor(Color.BLACK);
		line6.setColor(Color.BLACK);

		add(line1, 5, 50);
		add(line2, 5, 50 + line1.getHeight());
		add(line3, 5, 50 + line1.getHeight() * 2);
		add(line4, 5, 50 + line1.getHeight() * 4);
		add(line5, 5, 50 + line1.getHeight() * 5);
		add(line6, 5, 50 + line1.getHeight() * 6);

		waitForClick();

		removeAll();
		drawBackground("darth_mehran.png");
		line1.setLabel("It's simple.");
		line2.setLabel("We kill the Mona Lisa.");
		line1.setColor(Color.WHITE);
		line2.setColor(Color.WHITE);

		add(line1, 5, 50);
		add(line2, 5, 50 + line1.getHeight());

		starWarsClip.stop();
		AudioClip jokerClip = MediaTools.loadAudioClip("joker_audio.wav");
		jokerClip.play();
		pause(1500);
		AudioClip monaLisaClip = MediaTools
				.loadAudioClip("mona_lisa_audio.wav");
		monaLisaClip.play();
		pause(1000);
		starWarsClip.loop();

		waitForClick();

		removeAll();
		drawBackground("paris.jpg");
		line1.setLabel("Mehran's plan was set. He created an army of robotic clones.");
		line2.setLabel("He would throw the clones at the Mona Lisa and they would explode on impact.");
		line3.setLabel("However, he would learn from his mistakes,");
		line4.setLabel("and use his trusty slingshot this time.");
		line5.setLabel("Mehran traveled to Paris.");
		line6.setLabel("And this is where our journey begins.");
		line3.setColor(Color.WHITE);
		line4.setColor(Color.WHITE);
		line5.setColor(Color.WHITE);
		line6.setColor(Color.WHITE);

		add(line1, 5, 50);
		add(line2, 5, 50 + line1.getHeight());
		add(line3, 5, 50 + line1.getHeight() * 2);
		add(line4, 5, 50 + line1.getHeight() * 3);
		add(line5, 5, 50 + line1.getHeight() * 5);
		add(line6, 5, 50 + line1.getHeight() * 6);

		waitForClick();
		starWarsClip.stop();
		AngryMehrans.startSoundtrack();

	}

	/**
	 * Opens the file reader: uses JFileChooser to provide an appealing way to
	 * search for files.
	 * 
	 * @return file name
	 */
	private String openFileReaderUsingChooser() {
		JFileChooser fileChooser = new JFileChooser();
		int userDecision = fileChooser.showOpenDialog(this);
		if (userDecision == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			return file.getName();
		}
		return null;
	}

	/**
	 * Gets the player name from the user.
	 * 
	 * @return player name
	 */
	private String getPlayerName() {
		IODialog dialog = getDialog();
		return dialog.readLine("Enter player name: ");
	}

	/**
	 * Initial setup for the game: sets teh screen size, draws the background,
	 * resets the variables, and adds listeners.
	 */
	private void initialSetup() {
		setSize(GAME_RECT_WIDTH, GAME_RECT_HEIGHT);

		drawBackground("select_level_background.jpg");
		resetVariables();

		addMouseListeners();
		addKeyListeners();
	}

	/**
	 * Resets the variables.
	 */
	private void resetVariables() {
		numBirds = 0;
		numPigs = 0;
		currentBird = null;
		currentSlingshot = null;
		slingshotLeftStrip = null;
		slingshotRightStrip = null;

		prevLevelSelectIcon = null;

		mouseX = 0;
		mouseY = 0;
		lastMouseX = 0;
		lastMouseY = 0;
		shootingStartX = 0;
		shootingStartY = 0;
		birdVelX = 0;
		birdVelY = 0;
		tempCurrentBirdInitialX = 0;
		tempCurrentBirdInitialY = 0;

		isBirdReadyToShoot = false;
		isShootingBird = false;
		isBirdFlying = false;
		isOnLevelSelectScreen = false;

		objectList.clear();

		collisionObjectList.clear();

		readWorldArrayList.clear();
	}

	/**
	 * Draws the ground.
	 */
	private void drawGround() {
		ground = new GImage("ground.png");
		ground.setSize(getWidth(), (getHeight() * 0.2));
		add(ground, 0, (getHeight() - (getHeight() * 0.2)));
	}

	/**
	 * Displays the level select screen. Displays icons to guide the user to
	 * levels and shows which levels are unlocked.
	 */
	private void displayLevelSelect() {
		getUnlockedLevels();

		removeAll();
		drawBackground("select_level_background.jpg");
		isOnLevelSelectScreen = true;

		GImage selectLevelTextBorder = new GImage(
				"level_select_icon_background.png");
		selectLevelTextBorder.setSize(getWidth() * (250.0 / 1021.0),
				getHeight() * (80.0 / 643.0));
		add(selectLevelTextBorder,
				(getWidth() - selectLevelTextBorder.getWidth()) / 2.0,
				(getHeight() * 0.09));

		GImage selectLevelText = new GImage("select_level_text.png");
		selectLevelText.setSize(getWidth() * (200.0 / 1021.0), getHeight()
				* (69.0 / 643.0));
		add(selectLevelText, (getWidth() - selectLevelText.getWidth()) / 2.0,
				(getHeight() * 0.1));

		for (int i = 0; i < LEVEL_SELECT_COLS; i++) {
			for (int j = 0; j < LEVEL_SELECT_ROWS; j++) {
				int levelSelectIconNumber = (i + 1) + (j * 5);
				if (unlockedLevelsArrayList.contains(levelSelectIconNumber)) {
					String levelSelectIconLabel = "" + levelSelectIconNumber;
					LevelSelectIcon icon = new LevelSelectIcon(
							levelSelectIconLabel, getWidth() * 0.1,
							getHeight() * 0.1);
					add(icon, (getWidth() * 0.225) + i * icon.getWidth() + i
							* (getWidth() * 0.01), (getHeight() * 0.3)
							+ (j * icon.getHeight()) + j * (getWidth() * 0.01));
				} else {
					LockedLevelSelectIcon icon = new LockedLevelSelectIcon(
							getWidth() * 0.1, getHeight() * 0.1);
					add(icon, (getWidth() * 0.225) + i * icon.getWidth() + i
							* (getWidth() * 0.01), (getHeight() * 0.3)
							+ (j * icon.getHeight()) + j * (getWidth() * 0.01));
				}
			}
		}
	}

	/**
	 * Gets the unlocked levels from a text file, thus saving the user's
	 * progress even when the program is closed.
	 */
	private void getUnlockedLevels() {
		unlockedLevelsArrayList.clear();
		try {
			BufferedReader rd = new BufferedReader(new FileReader(
					"unlocked_levels.txt"));
			while (true) {
				String line = rd.readLine();
				if (line == null)
					break;
				int lineInt = Integer.parseInt(line);
				unlockedLevelsArrayList.add(lineInt);
			}
			rd.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}

	/**
	 * Gets the level file from the level number.
	 * 
	 * @param levelNum
	 *            level number
	 * @return level file
	 */
	private String getLevelFileFromLevelNumber(int levelNum) {
		switch (levelNum) {
		case 1:
			return "level_01.txt";
		case 2:
			return "level_02.txt";
		case 3:
			return "level_03.txt";
		case 4:
			return "level_04.txt";
		case 5:
			return "level_05.txt";
		case 6:
			return "level_06.txt";
		case 7:
			return "level_07.txt";
		case 8:
			return "level_08.txt";
		case 9:
			return "level_09.txt";
		case 10:
			return "level_10.txt";
		case 11:
			return "level_11.txt";
		case 12:
			return "level_12.txt";
		case 13:
			return "level_13.txt";
		case 14:
			return "level_14.txt";
		case 15:
			return "level_15.txt";
		case 16:
			return "level_16.txt";
		case 17:
			return "level_17.txt";
		case 18:
			return "level_18.txt";
		case 19:
			return "level_19.txt";
		case 20:
			return "level_20.txt";
		case 21:
			return "level_21.txt";
		case 22:
			return "level_22.txt";
		case 23:
			return "level_23.txt";
		case 24:
			return "level_24.txt";
		case 25:
			return "level_25.txt";
		default:
			return "level_1.txt";
		}
	}

	/**
	 * Plays a level: draws the ground, sets up the level, and loops through
	 * moving objects, checking for collisions, and pausing.
	 * 
	 * @param playerName
	 *            player name
	 * @param levelFile
	 *            level file
	 */
	private void playLevel(String playerName, String levelFile) {
		removeAll();
		drawGround();
		levelSetup(levelFile);
		while (!gameOver()) {
			moveObjects();
			moveCollisionObjects();
			checkForCollisions();
			pause(1);
		}
		endLevel(playerName);
	}

	/**
	 * Sets up the level. Resets the variables, reads the world from a file, and
	 * generates the world.
	 * 
	 * @param levelFile
	 *            level file
	 */
	private void levelSetup(String levelFile) {
		resetVariables();
		readWorldFromFile(levelFile);
		generateWorld(readWorldArrayList);
	}

	/**
	 * Draws the background.
	 * 
	 * @param backgroundFileName
	 *            background file name
	 */
	private void drawBackground(String backgroundFileName) {
		currentBackground = new Background(backgroundFileName, getWidth(),
				getHeight());
		add(currentBackground, 0, 0);
		currentBackground.sendToBack();
	}

	/**
	 * Moves objects: moves objects in the objectList ArrayList and moves the
	 * current bird.
	 */
	private void moveObjects() {
		for (int i = 0; i < objectList.size(); i++) {
			Object obj = objectList.get(i);
			moveWoodBlock(obj);
			movePig(obj);
		}

		moveBird();
	}

	/**
	 * Moves objects that have been collided with.
	 */
	private void moveCollisionObjects() {
		for (int i = 0; i < collisionObjectList.size(); i++) {
			Object obj = collisionObjectList.get(i);
			moveCollisionWoodBlock(obj);
		}
	}

	/**
	 * Moves the wood block: checks if the bottom of the block is clear, and if
	 * so moves in accordance with the laws of gravity.
	 * 
	 * @param obj
	 *            wood block
	 */
	private void moveWoodBlock(Object obj) {
		if (obj instanceof WoodBlock) {
			boolean bottomOfBlockClear = true;
			for (int pixel = 0; pixel < ((WoodBlock) obj).getWidth(); pixel++) {
				if (getElementAt(
						((WoodBlock) obj).getX() + pixel,
						((WoodBlock) obj).getY()
								+ ((WoodBlock) obj).getHeight() + 1) != null
						&& !(getElementAt(
								((WoodBlock) obj).getX() + pixel,
								((WoodBlock) obj).getY()
										+ ((WoodBlock) obj).getHeight() + 1) instanceof Explosion)
						&& !(getElementAt(
								((WoodBlock) obj).getX() + pixel,
								((WoodBlock) obj).getY()
										+ ((WoodBlock) obj).getHeight() + 1) instanceof Background)) {
					bottomOfBlockClear = false;
					break;
				}
			}
			if (bottomOfBlockClear) {
				((WoodBlock) obj).moveWithGravity();
			}
		}
	}

	/**
	 * Moves the woodblock in accordance with the laws of gravity in the
	 * occasion of a collision.
	 * 
	 * @param obj
	 *            wood block
	 */
	private void moveCollisionWoodBlock(Object obj) {
		if (obj instanceof WoodBlock) {
			((WoodBlock) obj).moveWithGravity();
		}
	}

	/**
	 * Moves a pig: checks if the bottom of the pig is clear, and if so moves in
	 * accordance with the laws of gravity.
	 * 
	 * @param obj
	 *            basic pig
	 */
	private void movePig(Object obj) {
		if (obj instanceof BasicPig) {
			boolean bottomOfPigClear = true;
			for (int pixel = 0; pixel < ((BasicPig) obj).getWidth(); pixel++) {
				if (getElementAt(((BasicPig) obj).getX() + pixel,
						((BasicPig) obj).getY() + ((BasicPig) obj).getHeight()
								+ 1) != null
						&& !(getElementAt(
								((BasicPig) obj).getX() + pixel,
								((BasicPig) obj).getY()
										+ ((BasicPig) obj).getHeight() + 1) instanceof Explosion)
						&& !(getElementAt(
								((BasicPig) obj).getX() + pixel,
								((BasicPig) obj).getY()
										+ ((BasicPig) obj).getHeight() + 1) instanceof Background)) {
					bottomOfPigClear = false;
					break;
				}
			}
			if (bottomOfPigClear) {
				((BasicPig) obj).moveWithGravity();
			}
		}
	}

	/**
	 * Moves the current bird in accordance with the laws of gravity.
	 */
	private void moveBird() {
		if (isBirdFlying) {
			currentBird.moveWithGravity();
		}
	}

	/**
	 * Checks for collisions: checks for bird and pig collisions.
	 */
	private void checkForCollisions() {
		checkBirdCollisions();
		checkPigCollisions();
	}

	/**
	 * Checks for bird collisions: checks if the bird is off the screen or if
	 * the bird has collided with another object and accounts for the collision.
	 */
	private void checkBirdCollisions() {
		if (currentBird != null && currentBird.isVisible()) {

			if (currentBird.getX() < 0) {
				currentBird.setVisible(false); // This is necessary so that the
												// program doesn't go through
												// this code multiple times. The
												// method doesn't execute if the
												// bird is not visible.
				remove(currentBird);
				numBirds--;
				isBirdFlying = false;
			} else if (currentBird.getX() + currentBird.getWidth() > getWidth()) {
				currentBird.setVisible(false);
				remove(currentBird);
				numBirds--;
				isBirdFlying = false;
			} else if (currentBird.getY() + currentBird.getHeight() > ground
					.getY()) {
				currentBird.setVisible(false);
				remove(currentBird);
				numBirds--;
				isBirdFlying = false;
			}

			checkBirdCollision();
		}
	}

	/**
	 * Checks if the bird has collided with an object: if the bird collides with
	 * a block, the bird bounces appropriately. If the bird collides with a pig,
	 * the pig is removed, an explosion is displayed, and the counter for the
	 * number of pigs is altered accordingly.
	 */
	private void checkBirdCollision() {
		Object[] collisionArray = getFirstCollidingObject(currentBird);
		if (collisionArray != null && currentBird.isVisible()) {
			if (collisionArray[0] instanceof WoodBlock) {
				if (collisionArray[1].equals("top")) {
					moveCollisionBlock((WoodBlock) collisionArray[0]);
					currentBird.setYVel(-Math.abs(currentBird.getYVel()));
					if (currentBird instanceof BlueBird) {
						BasicExplosion explosion = new BasicExplosion(
								currentBird.getWidth(), currentBird.getHeight());
						add(explosion,
								currentBird.getX() + currentBird.getWidth()
										/ 2.0,
								currentBird.getY() + currentBird.getHeight()
										/ 2.0);
						currentBird.setVisible(false);
						remove(currentBird);
						isBirdFlying = false;
					}
				}

				if (collisionArray[1].equals("bottom")) {
					moveCollisionBlock((WoodBlock) collisionArray[0]);
					currentBird.setYVel(Math.abs(currentBird.getYVel()));
				}

				if (collisionArray[1].equals("left")) {
					moveCollisionBlock((WoodBlock) collisionArray[0]);
					currentBird.setXVel(-Math.abs(currentBird.getXVel()));
				}

				if (collisionArray[1].equals("right")) {
					moveCollisionBlock((WoodBlock) collisionArray[0]);
					currentBird.setXVel(Math.abs(currentBird.getXVel()));
				}
			}

			if (collisionArray[0] instanceof Pig) {
				Pig collisionPig = (Pig) collisionArray[0];
				if (collisionPig.isVisible()) {
					BasicExplosion explosion = new BasicExplosion(
							collisionPig.getWidth() * 1.5,
							collisionPig.getHeight() * 1.5);
					add(explosion,
							(collisionPig.getX() + collisionPig.getWidth() / 2.0),
							(collisionPig.getY() + collisionPig.getHeight() / 2.0));
					remove(collisionPig);
					collisionPig.setVisible(false);
					if (currentBird instanceof BlueBird) {
						currentBird.setVisible(false);
						remove(currentBird);
						isBirdFlying = false;
					}
					numPigs--;
				}
			}
		}
	}

	/**
	 * Checks the pig collisions: if the pig hits the ground, the pig is
	 * removed, an explosion is displayed, and the counter for the number of
	 * pigs is altered accordingly.
	 */
	private void checkPigCollisions() {
		for (int i = 0; i < objectList.size(); i++) {
			Object obj = objectList.get(i);
			if (obj instanceof Pig) {
				Pig pig = (Pig) obj;
				if (pig != null && pig.isVisible()
						&& (pig.getY() + pig.getHeight() > ground.getY() - 2)) {
					BasicExplosion explosion = new BasicExplosion(
							pig.getWidth() * 1.5, pig.getHeight() * 1.5);
					add(explosion, (pig.getX() + pig.getWidth() / 2.0),
							(pig.getY() + pig.getHeight() / 2.0));
					pig.setVisible(false);
					remove(pig);
					numPigs--;
				}
			}
		}
	}

	/**
	 * Moves a wood block in the occasion of a collision with a bird. The forces
	 * due to the collision and due to gravity act on the block and the block
	 * careens off the screen.
	 * 
	 * @param collisionBlock
	 *            wood block that has collided with a bird
	 */
	private void moveCollisionBlock(WoodBlock collisionBlock) {
		setObjectListVelocities(0, 0);
		if (objectList.contains(collisionBlock)) {
			objectList.remove(collisionBlock);
		}
		collisionBlock.setXVel(currentBird.getXVel());
		collisionBlock.setYVel(currentBird.getYVel());
		collisionObjectList.add(collisionBlock);
	}

	/**
	 * Sets the velocities of the objects in the objectList ArrayList to 0 so
	 * that they don't behave inappropriately and so that the effects of gravity
	 * on the objects while they were at rest are annulled.
	 * 
	 * @param xVelocity
	 *            x velocity
	 * @param yVelocity
	 *            y velocity
	 */
	private void setObjectListVelocities(double xVelocity, double yVelocity) {
		for (int i = 0; i < objectList.size(); i++) {
			Object obj = objectList.get(i);
			if (obj instanceof Block) {
				((Block) obj).setXVel(0);
				((Block) obj).setYVel(0);
			} else if (obj instanceof Pig) {
				((Pig) obj).setXVel(0);
				((Pig) obj).setYVel(0);
			}
		}
	}

	/**
	 * Gets the first object the bird collides with: gets the object at a corner
	 * of the rectangle bounding an object, thus checking for a collision.
	 * 
	 * @param checkingObject
	 *            Object to check collisions for
	 * @return an array containing the object the bird collided with and the
	 *         side of the object the bird hit.
	 */
	private Object[] getFirstCollidingObject(GObject checkingObject) {
		Object[] collisionArray = new Object[2];
		GObject obj1;

		// Checks for object at top left corner
		obj1 = getElementAt(checkingObject.getX() - 1,
				checkingObject.getY() - 1);
		if (obj1 != null && obj1 != checkingObject
				&& !(obj1 instanceof Background)) {
			collisionArray[0] = obj1;
			if ((obj1.getX() + obj1.getWidth() > checkingObject.getX())
					&& (obj1.getY() + obj1.getHeight() < checkingObject.getY())) {
				collisionArray[1] = "bottom";
			} else {
				collisionArray[1] = "right";
			}
			return collisionArray;
		}

		// Checks for object at bottom left corner
		obj1 = getElementAt(checkingObject.getX() - 1, checkingObject.getY()
				+ checkingObject.getHeight() + 1);
		if (obj1 != null && obj1 != checkingObject
				&& !(obj1 instanceof Background)) {
			collisionArray[0] = obj1;
			if ((obj1.getX() + obj1.getWidth() > checkingObject.getX())
					&& (checkingObject.getY() + checkingObject.getHeight() < obj1
							.getY())) {
				collisionArray[1] = "top";
			} else {
				collisionArray[1] = "right";
			}
			return collisionArray;
		}

		// Checks for object at bottom right corner
		obj1 = getElementAt(checkingObject.getX() + checkingObject.getWidth()
				+ 1, checkingObject.getY() + checkingObject.getHeight() + 1);
		if (obj1 != null && obj1 != checkingObject
				&& !(obj1 instanceof Background)) {
			collisionArray[0] = obj1;
			if ((obj1.getX() < checkingObject.getX()
					+ checkingObject.getWidth())
					&& (checkingObject.getY() + checkingObject.getHeight() < obj1
							.getY())) {
				collisionArray[1] = "top";
			} else {
				collisionArray[1] = "left";
			}
			return collisionArray;
		}

		// Checks for object at top right corner
		obj1 = getElementAt(checkingObject.getX() + checkingObject.getWidth()
				+ 1, checkingObject.getY() - 1);
		if (obj1 != null && obj1 != checkingObject
				&& !(obj1 instanceof Background)) {
			collisionArray[0] = obj1;
			if ((obj1.getX() < checkingObject.getX()
					+ checkingObject.getWidth())
					&& (obj1.getY() + obj1.getHeight() < checkingObject.getY())) {
				collisionArray[1] = "bottom";
			} else {
				collisionArray[1] = "left";
			}
			return collisionArray;
		}

		return null;
	}

	/**
	 * Responds when the mouse is moved: if the mouse is on the level select
	 * screen, changes the colors of the level select icons as the mouse scrolls
	 * over them.
	 */
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();

		if (isOnLevelSelectScreen) {
			Object obj = getElementAt(mouseX, mouseY);
			if (obj instanceof LevelSelectIcon) {
				if (prevLevelSelectIcon != null) {
					prevLevelSelectIcon.setLabelColor(Color.BLACK);
				}
				((LevelSelectIcon) obj).setLabelColor(Color.YELLOW);
				prevLevelSelectIcon = (LevelSelectIcon) obj;
			} else {
				if (prevLevelSelectIcon != null) {
					prevLevelSelectIcon.setLabelColor(Color.BLACK);
				}
			}
		}
	}

	/**
	 * Responds when the mouse is pressed: if the mouse is pressed on a bird,
	 * prepares the bird to be fired. If the mouse is pressed on a slingshot,
	 * sets that slingshot as the current slingshot.
	 */
	public void mousePressed(MouseEvent e) {
		if (getElementAt(e.getX(), e.getY()) instanceof Bird) {
			if ((Bird) getElementAt(e.getX(), e.getY()) == currentBird
					&& isBirdReadyToShoot) {
				Bird birdBeingShot = (Bird) getElementAt(e.getX(), e.getY());
				shootingStartX = birdBeingShot.getX()
						+ (birdBeingShot.getWidth() / 2.0);
				lastMouseX = shootingStartX;
				shootingStartY = birdBeingShot.getY()
						+ (birdBeingShot.getHeight() / 2.0);
				lastMouseY = shootingStartY;

				double slingshotLeftLeatherX = currentSlingshot.getX()
						+ (currentSlingshot.getWidth() * 0.2);
				double slingshotLeftLeatherY = currentSlingshot.getY()
						+ (currentSlingshot.getHeight() * 0.2);
				slingshotLeftStrip = new GLine(slingshotLeftLeatherX,
						slingshotLeftLeatherY, slingshotLeftLeatherX,
						slingshotLeftLeatherY);
				add(slingshotLeftStrip);

				double slingshotRightLeatherX = currentSlingshot.getX()
						+ (currentSlingshot.getWidth() * 0.8);
				double slingshotRightLeatherY = currentSlingshot.getY()
						+ (currentSlingshot.getHeight() * 0.2);
				slingshotRightStrip = new GLine(slingshotRightLeatherX,
						slingshotRightLeatherY, slingshotRightLeatherX,
						slingshotRightLeatherY);
				add(slingshotRightStrip);
				slingshotRightStrip.sendToBack();
				currentBackground.sendToBack();
				slingshotRightStrip.sendForward();
				slingshotRightStrip.sendForward();
				slingshotRightStrip.sendForward();
				slingshotRightStrip.sendForward();
				slingshotRightStrip.sendForward();
				slingshotRightStrip.sendForward();

				isShootingBird = true;
			} else {
				currentBird.setLocation(tempCurrentBirdInitialX,
						tempCurrentBirdInitialY);

				currentBird = ((Bird) getElementAt(e.getX(), e.getY()));
				tempCurrentBirdInitialX = currentBird.getX();
				tempCurrentBirdInitialY = currentBird.getY();

				/*
				 * double slingshotStripsMidpointX = ((currentSlingshot.getX() +
				 * (currentSlingshot.getWidth() * 0.2)) +
				 * (currentSlingshot.getX() + (currentSlingshot.getWidth() *
				 * 0.8))) / 2.0; double slingshotStripsMidpointY =
				 * ((currentSlingshot.getY() + (currentSlingshot.getHeight() *
				 * 0.2)) + (currentSlingshot.getY() +
				 * (currentSlingshot.getHeight() * 0.2))) / 2.0;
				 * currentBird.setLocation(slingshotStripsMidpointX,
				 * slingshotStripsMidpointY);
				 */

				currentBird.setLocation(currentSlingshot.getX(),
						currentSlingshot.getY());
				currentBird.sendToFront();
				isBirdReadyToShoot = true;
			}
		}

		if (getElementAt(e.getX(), e.getY()) instanceof Slingshot) {
			currentSlingshot = (Slingshot) getElementAt(e.getX(), e.getY());
			currentSlingshot.sendBackward();
		}
	}

	/**
	 * Responds when the mouse is dragged: if a bird is being shot, updates the
	 * slingshot strip images, and keeps track of the bird's displacement from
	 * the location it started.
	 */
	public void mouseDragged(MouseEvent e) {
		if (isShootingBird) {
			if (!playedStretchingSound) {				
				AudioClip slingshotStretchSound = MediaTools.loadAudioClip("slingshot_stretch.wav");
				slingshotStretchSound.play();
				playedStretchingSound = true;
			}
			
			double mouseDiffX = e.getX() - lastMouseX;
			double mouseDiffY = e.getY() - lastMouseY;

			double slingshotStripsMidpointX = (slingshotLeftStrip.getX() + slingshotRightStrip
					.getX()) / 2.0;
			double slingshotStripsMidpointY = (slingshotLeftStrip.getY() + slingshotRightStrip
					.getY()) / 2.0;

			double currentBirdCenterX = currentBird.getX()
					+ (currentBird.getWidth() / 2.0);
			double currentBirdCenterY = currentBird.getY()
					+ (currentBird.getHeight() / 2.0);

			double lineConnectingCenterAndMidpointRun = (currentBirdCenterX - slingshotStripsMidpointX);
			double lineConnectingCenterAndMidpointRise = (currentBirdCenterY - slingshotStripsMidpointY);

			double slingshotStripsEndpointX = currentBirdCenterX;
			double slingshotStripsEndpointY = currentBirdCenterY;
			while ((slingshotStripsEndpointX > currentBird.getX())
					&& (slingshotStripsEndpointX < currentBird.getX()
							+ currentBird.getWidth())
					&& (slingshotStripsEndpointY > currentBird.getY())
					&& (slingshotStripsEndpointY < currentBird.getY()
							+ currentBird.getHeight())) {
				slingshotStripsEndpointX += (lineConnectingCenterAndMidpointRun / 100.0);
				slingshotStripsEndpointY += (lineConnectingCenterAndMidpointRise / 100.0);
			}

			slingshotLeftStrip.setEndPoint(slingshotStripsEndpointX,
					slingshotStripsEndpointY);
			slingshotRightStrip.setEndPoint(slingshotStripsEndpointX,
					slingshotStripsEndpointY);

			currentBird.move(mouseDiffX, mouseDiffY);
			lastMouseX = e.getX();
			lastMouseY = e.getY();
		}
	}

	/**
	 * Responds when the mouse is released: if a bird is being shot, fires the
	 * bird by setting appropriate x and y velocities based on how much the user
	 * pulled on the slingshot.
	 */
	public void mouseReleased(MouseEvent e) {
		if (isShootingBird) {
			AudioClip slingshotFireSound = MediaTools.loadAudioClip("slingshot_fire.wav");
			slingshotFireSound.play();
			playedStretchingSound = false;
			
			birdVelX = (shootingStartX - (currentBird.getX() + (currentBird
					.getWidth() / 2.0))) / 30.0;
			birdVelY = (shootingStartY - (currentBird.getY() + (currentBird
					.getHeight() / 2.0))) / 30.0;
			isShootingBird = false;
			isBirdFlying = true;
			remove(slingshotLeftStrip);
			remove(slingshotRightStrip);

			currentBird.setXVel(birdVelX);
			currentBird.setYVel(birdVelY);
		}
	}

	/**
	 * Responds when a key is pressed: Tools that helped with testing for the
	 * game, which have now turned into "cheat codes."
	 */
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_M:
			WoodBlock verticalBlock = new WoodBlock(20, 80);
			add(verticalBlock, mouseX, mouseY);
			verticalBlock.setXVel(0);
			verticalBlock.setYVel(0);
			objectList.add(verticalBlock);
			break;
		case KeyEvent.VK_N:
			WoodBlock horizontalBlock = new WoodBlock(80, 20);
			add(horizontalBlock, mouseX, mouseY);
			horizontalBlock.setXVel(0);
			horizontalBlock.setYVel(0);
			objectList.add(horizontalBlock);
			break;
		case KeyEvent.VK_R:
			currentSlingshot = new Slingshot(getWidth() * (0.3 / 9.0),
					getHeight() * (0.8 / 5.0));
			add(currentSlingshot, mouseX, mouseY);

			currentBird = new BlueBird(getWidth() * (0.2 / 9.0), getHeight()
					* (0.2 / 4.0));
			add(currentBird, mouseX, mouseY);
			break;
		case KeyEvent.VK_F:
			readWorldFromFile("level_1.txt");
			generateWorld(readWorldArrayList);
		}
	}

	/**
	 * Reads the world from a file and stores the lines read from the file into
	 * an ArrayList.
	 * 
	 * @param fileName
	 *            file name
	 */
	private void readWorldFromFile(String fileName) {
		try {
			BufferedReader rd = new BufferedReader(new FileReader(fileName));
			while (true) {
				String line = rd.readLine();
				if (line == null)
					break;
				readWorldArrayList.add(line);
			}
			rd.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}

	/**
	 * Generates the world based on an ArrayList: draws an object for each
	 * element in the ArrayList.
	 * 
	 * @param generateWorldStringArrayList
	 *            ArrayList of String containing information for setting up the
	 *            world
	 */
	private void generateWorld(ArrayList<String> generateWorldStringArrayList) {
		for (int i = 0; i < generateWorldStringArrayList.size(); i++) {
			String line = generateWorldStringArrayList.get(i);
			ArrayList<Object> generateObjectArrayList = parseLine(line);
			drawGeneratedObject(generateObjectArrayList);
		}
	}

	/**
	 * Parses a line: takes a given line which represents information for an
	 * object on the screen. Parses the information on that line depending on
	 * what the object type is and stores the information in an ArrayList
	 * 
	 * @param line
	 *            line to parse
	 * @return ArrayList of object information
	 */
	private ArrayList<Object> parseLine(String line) {
		ArrayList<Object> generateObjectArrayList = new ArrayList<Object>();

		int objectTypeStart = line.indexOf("[") + 1;
		int objectTypeEnd = line.indexOf("]");
		String objectType = line.substring(objectTypeStart, objectTypeEnd);
		generateObjectArrayList.add(objectType);

		if (!objectType.equals("Background") && !objectType.equals("GLabel")) {
			int objectWidthStart = line.indexOf("[", objectTypeEnd + 1) + 1;
			int objectWidthEnd = line.indexOf("]", objectTypeEnd + 1);
			String objectWidthString = line.substring(objectWidthStart,
					objectWidthEnd);
			double objectWidth = Double.parseDouble(objectWidthString)
					* getWidth();
			generateObjectArrayList.add(objectWidth);

			int objectHeightStart = line.indexOf("[", objectWidthEnd + 1) + 1;
			int objectHeightEnd = line.indexOf("]", objectWidthEnd + 1);
			String objectHeightString = line.substring(objectHeightStart,
					objectHeightEnd);
			double objectHeight = Double.parseDouble(objectHeightString)
					* getHeight();
			generateObjectArrayList.add(objectHeight);

			int objectXStart = line.indexOf("[", objectHeightEnd + 1) + 1;
			int objectXEnd = line.indexOf("]", objectHeightEnd + 1);
			String objectXString = line.substring(objectXStart, objectXEnd);
			double objectX = Double.parseDouble(objectXString) * getWidth();
			generateObjectArrayList.add(objectX);

			int objectYStart = line.indexOf("[", objectXEnd + 1) + 1;
			int objectYEnd = line.indexOf("]", objectXEnd + 1);
			String objectYString = line.substring(objectYStart, objectYEnd);
			double objectY = Double.parseDouble(objectYString) * getHeight();
			generateObjectArrayList.add(objectY);
		} else if (objectType.equals("Background")) {
			int fileNameStart = line.indexOf("[", objectTypeEnd + 1) + 1;
			int fileNameEnd = line.indexOf("]", objectTypeEnd + 1);
			String fileNameString = line.substring(fileNameStart, fileNameEnd);
			generateObjectArrayList.add(fileNameString);
		} else if (objectType.equals("GLabel")) {
			int fileNameStart = line.indexOf("[", objectTypeEnd + 1) + 1;
			int fileNameEnd = line.indexOf("]", objectTypeEnd + 1);
			String fileNameString = line.substring(fileNameStart, fileNameEnd);
			generateObjectArrayList.add(fileNameString);

			int objectXStart = line.indexOf("[", fileNameEnd + 1) + 1;
			int objectXEnd = line.indexOf("]", fileNameEnd + 1);
			String objectXString = line.substring(objectXStart, objectXEnd);
			double objectX = Double.parseDouble(objectXString) * getWidth();
			generateObjectArrayList.add(objectX);

			int objectYStart = line.indexOf("[", objectXEnd + 1) + 1;
			int objectYEnd = line.indexOf("]", objectXEnd + 1);
			String objectYString = line.substring(objectYStart, objectYEnd);
			double objectY = Double.parseDouble(objectYString) * getHeight();
			generateObjectArrayList.add(objectY);

		}

		return generateObjectArrayList;
	}

	/**
	 * Draws an object based on an ArrayList of information for that object.
	 * 
	 * @param generateObjectArrayList
	 *            ArrayList of information for an object
	 */
	private void drawGeneratedObject(ArrayList<Object> generateObjectArrayList) {
		String objectType = (String) generateObjectArrayList.get(0);
		if (objectType.equals("WoodBlock")) {
			double objectWidth = (Double) generateObjectArrayList.get(1);
			double objectHeight = (Double) generateObjectArrayList.get(2);
			double objectX = (Double) generateObjectArrayList.get(3);
			double objectY = (Double) generateObjectArrayList.get(4);
			WoodBlock block = new WoodBlock(objectWidth, objectHeight);
			add(block, objectX, objectY);
			block.setXVel(0);
			block.setYVel(0);
			objectList.add(block);
		} else if (objectType.equals("BasicPig")) {
			double objectWidth = (Double) generateObjectArrayList.get(1);
			double objectHeight = (Double) generateObjectArrayList.get(2);
			double objectX = (Double) generateObjectArrayList.get(3);
			double objectY = (Double) generateObjectArrayList.get(4);
			BasicPig pig = new BasicPig(objectWidth, objectHeight);
			add(pig, objectX, objectY);
			pig.setXVel(0);
			pig.setYVel(0);
			objectList.add(pig);
			numPigs++;
		} else if (objectType.equals("RedBird")) {
			double objectWidth = (Double) generateObjectArrayList.get(1);
			double objectHeight = (Double) generateObjectArrayList.get(2);
			double objectX = (Double) generateObjectArrayList.get(3);
			double objectY = (Double) generateObjectArrayList.get(4);
			currentBird = new RedBird(objectWidth, objectHeight);
			add(currentBird, objectX, objectY);
			tempCurrentBirdInitialX = currentBird.getX();
			tempCurrentBirdInitialY = currentBird.getY();
			numBirds++;
		} else if (objectType.equals("YellowBird")) {
			double objectWidth = (Double) generateObjectArrayList.get(1);
			double objectHeight = (Double) generateObjectArrayList.get(2);
			double objectX = (Double) generateObjectArrayList.get(3);
			double objectY = (Double) generateObjectArrayList.get(4);
			currentBird = new YellowBird(objectWidth, objectHeight);
			add(currentBird, objectX, objectY);
			tempCurrentBirdInitialX = currentBird.getX();
			tempCurrentBirdInitialY = currentBird.getY();
			numBirds++;
		} else if (objectType.equals("BlueBird")) {
			double objectWidth = (Double) generateObjectArrayList.get(1);
			double objectHeight = (Double) generateObjectArrayList.get(2);
			double objectX = (Double) generateObjectArrayList.get(3);
			double objectY = (Double) generateObjectArrayList.get(4);
			currentBird = new BlueBird(objectWidth, objectHeight);
			add(currentBird, objectX, objectY);
			tempCurrentBirdInitialX = currentBird.getX();
			tempCurrentBirdInitialY = currentBird.getY();
			numBirds++;
		} else if (objectType.equals("Slingshot")) {
			double objectWidth = (Double) generateObjectArrayList.get(1);
			double objectHeight = (Double) generateObjectArrayList.get(2);
			double objectX = (Double) generateObjectArrayList.get(3);
			double objectY = (Double) generateObjectArrayList.get(4);
			currentSlingshot = new Slingshot(objectWidth, objectHeight);
			add(currentSlingshot, objectX, objectY);
		} else if (objectType.equals("Background")) {
			String backgroundFile = (String) generateObjectArrayList.get(1);
			drawBackground(backgroundFile);
		} else if (objectType.equals("GLabel")) {
			String labelValue = (String) generateObjectArrayList.get(1);
			double objectX = (Double) generateObjectArrayList.get(2);
			double objectY = (Double) generateObjectArrayList.get(3);
			GLabel text = new GLabel(labelValue);
			text.setFont("Helvetica-24");
			text.setColor(Color.WHITE);
			add(text, objectX, objectY);
		}
	}

	/**
	 * Returns whether the game is over: the game is over when the number of
	 * birds or pigs is 0.
	 * 
	 * @return true if the game is over, false otherwise
	 */
	private boolean gameOver() {
		return (numBirds <= 0 || numPigs <= 0);
	}

	/**
	 * Creates an appropriate display when the user finishes a level, if the
	 * user beat the level, unlocks the next level.
	 * 
	 * @param playerName
	 *            name of the player
	 */
	private void endLevel(String playerName) {
		removeAll();
		if (numPigs <= 0) {
			drawBackground("level_end_background.jpg");

			GLabel label = new GLabel("CONGRATS " + playerName.toUpperCase()
					+ "!");
			label.setFont("Helvetica-24");
			label.setColor(Color.RED);
			add(label, (getWidth() - label.getWidth()) / 2.0, getHeight()
					* (50.0 / 478.0));

			double imageHeightFactor = 0.08;

			GImage levelEnd1 = new GImage("level_end1.png");
			levelEnd1.setSize(getWidth() * 0.2, getHeight() * imageHeightFactor
					* 0.7);
			add(levelEnd1, (getWidth() - levelEnd1.getWidth()) / 2.0,
					getHeight() * (66.0 / 478.0));

			GImage levelEnd2 = new GImage("level_end2.png");
			levelEnd2
					.setSize(getWidth() * 0.4, getHeight() * imageHeightFactor);
			add(levelEnd2, (getWidth() - levelEnd2.getWidth()) / 2.0,
					getHeight() * (159.0 / 478.0));

			GImage levelEnd3 = new GImage("level_end3.png");
			levelEnd3
					.setSize(getWidth() * 0.4, getHeight() * imageHeightFactor);
			add(levelEnd3, (getWidth() - levelEnd3.getWidth()) / 2.0,
					getHeight() * (223.0 / 478.0));

			GImage levelEnd4 = new GImage("level_end4.png");
			levelEnd4.setSize(getWidth() * 0.3, getHeight() * imageHeightFactor
					* 0.4);
			add(levelEnd4, (getWidth() - levelEnd4.getWidth()) / 2.0,
					getHeight() * (430.0 / 478.0));

			int nextLevel = levelNumber + 1;
			if (!unlockedLevelsArrayList.contains(nextLevel)) {
				unlockedLevelsArrayList.add(nextLevel);
				writeToUnlockedLevelsFile();
			}
		} else {
			drawBackground("black_box.jpg");

			GImage levelFailedText = new GImage("level_failed.png");
			levelFailedText.setSize(getWidth() * 0.4, getHeight() * 0.2);
			add(levelFailedText,
					(getWidth() - levelFailedText.getWidth()) / 2.0,
					getHeight() * (30.0 / 478.0));

			GImage levelFailedImage = new GImage("level_failed_background.jpg");
			levelFailedImage.setSize(getWidth() * (344.0 * 0.001), getWidth()
					* (355.0 * 0.001));
			add(levelFailedImage,
					(getWidth() - levelFailedImage.getWidth()) / 2.0,
					getHeight() * (140.0 / 478.0));

		}

		waitForClick();
	}

	/**
	 * Writes the levels the user has unlocked to a file.
	 */
	private void writeToUnlockedLevelsFile() {
		try {
			PrintWriter wr = new PrintWriter(new FileWriter(
					"unlocked_levels.txt"));
			for (int i = 0; i < unlockedLevelsArrayList.size(); i++) {
				wr.println(unlockedLevelsArrayList.get(i));
			}
			wr.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}

	/**
	 * Responds when the mouse is clicked: if the user is on the level screen,
	 * goes to the level the user clicks on, and if the user is in a game and
	 * the bird is flying, dashes the bird forward if the bird is a yellow bird,
	 * and drops a bomb if the bird is a blue bird.
	 */
	public void mouseClicked(MouseEvent e) {
		if (isOnLevelSelectScreen) {
			Object obj = getElementAt(mouseX, mouseY);
			if (obj instanceof LevelSelectIcon) {
				String labelString = ((LevelSelectIcon) obj).getLabelString();
				levelNumber = Integer.parseInt(labelString);
				isOnLevelSelectScreen = false;
			}
		}

		if (isBirdFlying) {
			if (currentBird instanceof YellowBird) {
				((YellowBird) currentBird).dashForward();
			} else if (currentBird instanceof BlueBird) {
				BasicExplosion explosion = new BasicExplosion(
						currentBird.getWidth() * 0.7,
						currentBird.getHeight() * 0.7);
				add(explosion, currentBird.getX() + currentBird.getWidth()
						/ 2.0, currentBird.getY() + currentBird.getHeight()
						/ 2.0);
				((BlueBird) currentBird).dropBomb();
				((BlueBird) currentBird).setBirdBounds(currentBird.getWidth(),
						currentBird.getHeight());
			}
		}
	}

	/* Private instance variables */
	private int numBirds = 0;
	private int numPigs = 0;
	private Bird currentBird;
	private Slingshot currentSlingshot;
	private GLine slingshotLeftStrip;
	private GLine slingshotRightStrip;
	private Background currentBackground;

	private LevelSelectIcon prevLevelSelectIcon;
	private int levelNumber;
	private ArrayList<Integer> unlockedLevelsArrayList = new ArrayList<Integer>();

	private GImage ground;

	private double mouseX;
	private double mouseY;
	private double lastMouseX;
	private double lastMouseY;

	private double shootingStartX;
	private double shootingStartY;
	private double birdVelX;
	private double birdVelY;

	private double tempCurrentBirdInitialX;
	private double tempCurrentBirdInitialY;

	private boolean isBirdReadyToShoot = false;
	private boolean isShootingBird = false;
	private boolean isBirdFlying = false;
	private boolean playedStretchingSound = false;

	private boolean isOnLevelSelectScreen = false;

	/* Instance variables to keep track of various objects in the game */
	private ArrayList<Object> objectList = new ArrayList<Object>();;
	private ArrayList<Object> collisionObjectList = new ArrayList<Object>();;

	private ArrayList<String> readWorldArrayList = new ArrayList<String>();
}