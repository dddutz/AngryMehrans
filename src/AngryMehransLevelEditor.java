/*
 * File: AngryMehransLevelEditor.java
 * ----------------------------------
 * Name: Derin Dutz
 * Section Leader: Danielle Kain
 * 
 * Allows for the use and creation of a level editor for the Angry Mehrans game.
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import acm.program.*;
import acm.util.ErrorException;
import acm.graphics.*;

public class AngryMehransLevelEditor extends GraphicsProgram implements
		AngryMehransConstants {

	/**
	 * Constructor: adds interactors.
	 */
	public AngryMehransLevelEditor() {
		addInteractors();
	}

	/**
	 * Adds interactors: adds a default size checkbox, an input field for file
	 * names and text, and JButtons for saving the world, adding text, and
	 * removing everything on the screen.
	 */
	private void addInteractors() {
		defaultCheckBox = new JCheckBox("Default Sizes");
		defaultCheckBox.setSelected(true);
		add(defaultCheckBox, SOUTH);

		nameLabel = new JLabel("     Export File Name:");
		add(nameLabel, SOUTH);

		fileNameInputField = new JTextField(20);
		add(fileNameInputField, SOUTH);
		fileNameInputField.addActionListener(this);

		saveWorldButton = new JButton("Save World");
		add(saveWorldButton, SOUTH);

		addTextButton = new JButton("Add Text");
		add(addTextButton, SOUTH);

		removeAllButton = new JButton("Remove All");
		add(removeAllButton, SOUTH);

	}

	/**
	 * Runs the game: sets the size of the screen, adds the legends, draws the
	 * ground and the backgroudn, and adds listeners.
	 */
	public void run() {
		setSize(GAME_RECT_WIDTH, GAME_RECT_HEIGHT);

		levelEditorExportArrayList = new ArrayList<String>();
		levelEditorObjectsArrayList = new ArrayList<Object>();

		addLegend();

		drawBackground();
		drawGround();

		addMouseListeners();
		addActionListeners();
	}

	/**
	 * Adds the legend: creates boxes and icons that the user can click on to
	 * create various objects.
	 */
	private void addLegend() {
		/* Block box */
		blockBox = new GRect(BOX_SPACE_LEFT, PALETTE_SPACE_TOP, BOX_WIDTH,
				BOX_HEIGHT);
		WoodBlock blockIcon = new WoodBlock(ICON_WIDTH, ICON_HEIGHT);
		add(blockBox);
		add(blockIcon, BOX_SPACE_LEFT + (BOX_WIDTH / 2) - (ICON_WIDTH / 2),
				PALETTE_SPACE_TOP + (BOX_HEIGHT / 2) - (ICON_HEIGHT / 2));

		/* Pig box */
		pigBox = new GRect(BOX_SPACE_LEFT, PALETTE_SPACE_TOP + BOX_HEIGHT
				+ BOX_SPACE_TOP, BOX_WIDTH, BOX_HEIGHT);
		BasicPig pigIcon = new BasicPig(ICON_WIDTH, ICON_HEIGHT);
		add(pigBox);
		add(pigIcon, BOX_SPACE_LEFT + (BOX_WIDTH / 2) - (ICON_WIDTH / 2),
				PALETTE_SPACE_TOP + BOX_HEIGHT + BOX_SPACE_TOP
						+ (BOX_HEIGHT / 2) - (ICON_HEIGHT / 2));

		/* Red Bird box */
		redBirdBox = new GRect(BOX_SPACE_LEFT, PALETTE_SPACE_TOP
				+ (BOX_HEIGHT * 2) + (BOX_SPACE_TOP * 2), BOX_WIDTH, BOX_HEIGHT);
		RedBird redBirdIcon = new RedBird(ICON_WIDTH, ICON_HEIGHT);
		add(redBirdBox);
		add(redBirdIcon, BOX_SPACE_LEFT + (BOX_WIDTH / 2) - (ICON_WIDTH / 2),
				PALETTE_SPACE_TOP + (BOX_HEIGHT * 2) + (BOX_SPACE_TOP * 2)
						+ (BOX_HEIGHT / 2) - (ICON_HEIGHT / 2));

		/* Slingshot box */
		slingshotBox = new GRect(BOX_SPACE_LEFT, PALETTE_SPACE_TOP
				+ (BOX_HEIGHT * 3) + (BOX_SPACE_TOP * 3), BOX_WIDTH, BOX_HEIGHT);
		GImage slingshotIcon = new GImage("slingshot.png");
		slingshotIcon.setSize(ICON_WIDTH, ICON_HEIGHT);
		add(slingshotBox);
		add(slingshotIcon, BOX_SPACE_LEFT + (BOX_WIDTH / 2) - (ICON_WIDTH / 2),
				PALETTE_SPACE_TOP + (BOX_HEIGHT * 3) + (BOX_SPACE_TOP * 3)
						+ (BOX_HEIGHT / 2) - (ICON_HEIGHT / 2));

		/* Eraser box */
		eraserBox = new GRect(BOX_SPACE_LEFT, PALETTE_SPACE_TOP
				+ (BOX_HEIGHT * 4) + (BOX_SPACE_TOP * 4), BOX_WIDTH, BOX_HEIGHT);
		GImage eraserIcon = new GImage("eraser_icon.png");
		eraserIcon.setSize(ICON_WIDTH, ICON_HEIGHT);
		add(eraserBox);
		add(eraserIcon, BOX_SPACE_LEFT + (BOX_WIDTH / 2) - (ICON_WIDTH / 2),
				PALETTE_SPACE_TOP + (BOX_HEIGHT * 4) + (BOX_SPACE_TOP * 4)
						+ (BOX_HEIGHT / 2) - (ICON_HEIGHT / 2));

		/* Move box */
		moveBox = new GRect(BOX_SPACE_LEFT, PALETTE_SPACE_TOP
				+ (BOX_HEIGHT * 5) + (BOX_SPACE_TOP * 5), BOX_WIDTH, BOX_HEIGHT);
		GImage moveIcon = new GImage("move_icon.png");
		moveIcon.setSize(ICON_WIDTH, ICON_HEIGHT);
		add(moveBox);
		add(moveIcon, BOX_SPACE_LEFT + (BOX_WIDTH / 2) - (ICON_WIDTH / 2),
				PALETTE_SPACE_TOP + (BOX_HEIGHT * 5) + (BOX_SPACE_TOP * 5)
						+ (BOX_HEIGHT / 2) - (ICON_HEIGHT / 2));

		backgroundBox = new GRect(BOX_SPACE_LEFT, PALETTE_SPACE_TOP
				+ (BOX_HEIGHT * 6) + (BOX_SPACE_TOP * 6), BOX_WIDTH, BOX_HEIGHT);
		GImage backgroundIcon = new GImage("background_icon.png");
		backgroundIcon.setSize(ICON_WIDTH, ICON_HEIGHT);
		add(backgroundBox);
		add(backgroundIcon,
				BOX_SPACE_LEFT + (BOX_WIDTH / 2) - (ICON_WIDTH / 2),
				PALETTE_SPACE_TOP + (BOX_HEIGHT * 6) + (BOX_SPACE_TOP * 6)
						+ (BOX_HEIGHT / 2) - (ICON_HEIGHT / 2));

		/* Yellow Bird box */
		yellowBirdBox = new GRect(BOX_SPACE_LEFT, PALETTE_SPACE_TOP
				+ (BOX_HEIGHT * 7) + (BOX_SPACE_TOP * 7), BOX_WIDTH, BOX_HEIGHT);
		YellowBird yellowBirdIcon = new YellowBird(ICON_WIDTH, ICON_HEIGHT);
		add(yellowBirdBox);
		add(yellowBirdIcon,
				BOX_SPACE_LEFT + (BOX_WIDTH / 2) - (ICON_WIDTH / 2),
				PALETTE_SPACE_TOP + (BOX_HEIGHT * 7) + (BOX_SPACE_TOP * 7)
						+ (BOX_HEIGHT / 2) - (ICON_HEIGHT / 2));

		/* Blue Bird box */
		blueBirdBox = new GRect(BOX_SPACE_LEFT, PALETTE_SPACE_TOP
				+ (BOX_HEIGHT * 8) + (BOX_SPACE_TOP * 8), BOX_WIDTH, BOX_HEIGHT);
		BlueBird blueBirdIcon = new BlueBird(ICON_WIDTH, ICON_HEIGHT);
		add(blueBirdBox);
		add(blueBirdIcon, BOX_SPACE_LEFT + (BOX_WIDTH / 2) - (ICON_WIDTH / 2),
				PALETTE_SPACE_TOP + (BOX_HEIGHT * 8) + (BOX_SPACE_TOP * 8)
						+ (BOX_HEIGHT / 2) - (ICON_HEIGHT / 2));

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
	 * Draws the background.
	 */
	private void drawBackground() {
		currentBackground = new Background(getWidth(), getHeight());
		add(currentBackground, 0, 0);
		currentBackgroundFile = currentBackground.backgroundFile;
		currentBackground.sendToBack();
		levelEditorObjectsArrayList.add(currentBackground);
	}

	/**
	 * Responds when the mouse is pressed: If the user clicks in the legend area
	 * of the screen, depending on which icon in the legend was clicked on, box
	 * fill and boolean variables are changed accordingly so that the correct
	 * object can be drawn. If the user clicks in the drawing area of the
	 * screen, draws an object depending on which icon was clicked previously.
	 * Also moves or removes objects if the respective icons were clicked.
	 */
	public void mousePressed(MouseEvent e) {
		if ((BOX_SPACE_LEFT < e.getX())
				&& (e.getX() < BOX_SPACE_LEFT + BOX_WIDTH)
				&& (PALETTE_SPACE_TOP < e.getY())
				&& (e.getY() < PALETTE_SPACE_TOP + BOX_HEIGHT)) {
			bBlock = true;
			bPig = false;
			bRedBird = false;
			bSlingshot = false;
			bEraser = false;
			bMove = false;
			bYellowBird = false;
			bBlueBird = false;

			blockBox.setFilled(true);
			blockBox.setFillColor(Color.YELLOW);
			pigBox.setFilled(false);
			redBirdBox.setFilled(false);
			slingshotBox.setFilled(false);
			eraserBox.setFilled(false);
			moveBox.setFilled(false);
			yellowBirdBox.setFilled(false);
			blueBirdBox.setFilled(false);
		}

		if ((BOX_SPACE_LEFT < e.getX())
				&& (e.getX() < BOX_SPACE_LEFT + BOX_WIDTH)
				&& (PALETTE_SPACE_TOP + BOX_HEIGHT + BOX_SPACE_TOP < e.getY())
				&& (e.getY() < PALETTE_SPACE_TOP + BOX_HEIGHT * 2
						+ BOX_SPACE_TOP)) {
			bBlock = false;
			bPig = true;
			bRedBird = false;
			bSlingshot = false;
			bEraser = false;
			bMove = false;
			bYellowBird = false;
			bBlueBird = false;

			blockBox.setFilled(false);
			pigBox.setFilled(true);
			pigBox.setFillColor(Color.YELLOW);
			redBirdBox.setFilled(false);
			slingshotBox.setFilled(false);
			eraserBox.setFilled(false);
			moveBox.setFilled(false);
			yellowBirdBox.setFilled(false);
			blueBirdBox.setFilled(false);
		}

		if ((BOX_SPACE_LEFT < e.getX())
				&& (e.getX() < BOX_SPACE_LEFT + BOX_WIDTH)
				&& (PALETTE_SPACE_TOP + BOX_HEIGHT * 2 + BOX_SPACE_TOP * 2 < e
						.getY())
				&& (e.getY() < PALETTE_SPACE_TOP + BOX_HEIGHT * 3
						+ BOX_SPACE_TOP * 2)) {
			bBlock = false;
			bPig = false;
			bRedBird = true;
			bSlingshot = false;
			bEraser = false;
			bMove = false;
			bYellowBird = false;
			bBlueBird = false;

			blockBox.setFilled(false);
			pigBox.setFilled(false);
			redBirdBox.setFilled(true);
			redBirdBox.setFillColor(Color.YELLOW);
			slingshotBox.setFilled(false);
			eraserBox.setFilled(false);
			moveBox.setFilled(false);
			yellowBirdBox.setFilled(false);
			blueBirdBox.setFilled(false);
		}

		if ((BOX_SPACE_LEFT < e.getX())
				&& (e.getX() < BOX_SPACE_LEFT + BOX_WIDTH)
				&& (PALETTE_SPACE_TOP + BOX_HEIGHT * 3 + BOX_SPACE_TOP * 3 < e
						.getY())
				&& (e.getY() < PALETTE_SPACE_TOP + BOX_HEIGHT * 4
						+ BOX_SPACE_TOP * 3)) {
			bBlock = false;
			bPig = false;
			bRedBird = false;
			bSlingshot = true;
			bEraser = false;
			bMove = false;
			bYellowBird = false;
			bBlueBird = false;

			blockBox.setFilled(false);
			pigBox.setFilled(false);
			redBirdBox.setFilled(false);
			slingshotBox.setFilled(true);
			slingshotBox.setFillColor(Color.YELLOW);
			eraserBox.setFilled(false);
			moveBox.setFilled(false);
			yellowBirdBox.setFilled(false);
			blueBirdBox.setFilled(false);
		}

		if ((BOX_SPACE_LEFT < e.getX())
				&& (e.getX() < BOX_SPACE_LEFT + BOX_WIDTH)
				&& (PALETTE_SPACE_TOP + BOX_HEIGHT * 4 + BOX_SPACE_TOP * 4 < e
						.getY())
				&& (e.getY() < PALETTE_SPACE_TOP + BOX_HEIGHT * 5
						+ BOX_SPACE_TOP * 4)) {
			bBlock = false;
			bPig = false;
			bRedBird = false;
			bSlingshot = false;
			bEraser = true;
			bMove = false;
			bYellowBird = false;
			bBlueBird = false;

			blockBox.setFilled(false);
			pigBox.setFilled(false);
			redBirdBox.setFilled(false);
			slingshotBox.setFilled(false);
			eraserBox.setFilled(true);
			eraserBox.setFillColor(Color.YELLOW);
			moveBox.setFilled(false);
			yellowBirdBox.setFilled(false);
			blueBirdBox.setFilled(false);
		}

		if ((BOX_SPACE_LEFT < e.getX())
				&& (e.getX() < BOX_SPACE_LEFT + BOX_WIDTH)
				&& (PALETTE_SPACE_TOP + BOX_HEIGHT * 5 + BOX_SPACE_TOP * 5 < e
						.getY())
				&& (e.getY() < PALETTE_SPACE_TOP + BOX_HEIGHT * 6
						+ BOX_SPACE_TOP * 6)) {
			bBlock = false;
			bPig = false;
			bRedBird = false;
			bSlingshot = false;
			bEraser = false;
			bMove = true;
			bYellowBird = false;
			bBlueBird = false;

			blockBox.setFilled(false);
			pigBox.setFilled(false);
			redBirdBox.setFilled(false);
			slingshotBox.setFilled(false);
			eraserBox.setFilled(false);
			moveBox.setFilled(true);
			moveBox.setFillColor(Color.YELLOW);
			yellowBirdBox.setFilled(false);
			blueBirdBox.setFilled(false);
		}

		if ((BOX_SPACE_LEFT < e.getX())
				&& (e.getX() < BOX_SPACE_LEFT + BOX_WIDTH)
				&& (PALETTE_SPACE_TOP + BOX_HEIGHT * 6 + BOX_SPACE_TOP * 6 < e
						.getY())
				&& (e.getY() < PALETTE_SPACE_TOP + BOX_HEIGHT * 7
						+ BOX_SPACE_TOP * 7)) {
			currentBackgroundFile = currentBackground.changeBackground();
			currentBackground.sendToBack();
		}

		if ((BOX_SPACE_LEFT < e.getX())
				&& (e.getX() < BOX_SPACE_LEFT + BOX_WIDTH)
				&& (PALETTE_SPACE_TOP + BOX_HEIGHT * 7 + BOX_SPACE_TOP * 7 < e
						.getY())
				&& (e.getY() < PALETTE_SPACE_TOP + BOX_HEIGHT * 8
						+ BOX_SPACE_TOP * 8)) {
			bBlock = false;
			bPig = false;
			bRedBird = false;
			bSlingshot = false;
			bEraser = false;
			bMove = false;
			bYellowBird = true;
			bBlueBird = false;

			blockBox.setFilled(false);
			pigBox.setFilled(false);
			redBirdBox.setFilled(false);
			slingshotBox.setFilled(false);
			eraserBox.setFilled(false);
			moveBox.setFilled(false);
			yellowBirdBox.setFilled(true);
			yellowBirdBox.setFillColor(Color.YELLOW);
			blueBirdBox.setFilled(false);
		}

		if ((BOX_SPACE_LEFT < e.getX())
				&& (e.getX() < BOX_SPACE_LEFT + BOX_WIDTH)
				&& (PALETTE_SPACE_TOP + BOX_HEIGHT * 8 + BOX_SPACE_TOP * 8 < e
						.getY())
				&& (e.getY() < PALETTE_SPACE_TOP + BOX_HEIGHT * 9
						+ BOX_SPACE_TOP * 9)) {
			bBlock = false;
			bPig = false;
			bRedBird = false;
			bSlingshot = false;
			bEraser = false;
			bMove = false;
			bYellowBird = false;
			bBlueBird = true;

			blockBox.setFilled(false);
			pigBox.setFilled(false);
			redBirdBox.setFilled(false);
			slingshotBox.setFilled(false);
			eraserBox.setFilled(false);
			moveBox.setFilled(false);
			yellowBirdBox.setFilled(false);
			blueBirdBox.setFilled(true);
			blueBirdBox.setFillColor(Color.YELLOW);
		}

		if (e.getX() > BOX_SPACE_LEFT + BOX_WIDTH) {
			startX = e.getX();
			startY = e.getY();

			if (bBlock) {
				if (defaultCheckBox.isSelected()) {
					currentBlock = new WoodBlock(20, 80);
				} else {
					currentBlock = new WoodBlock(0, 0);
				}
				add(currentBlock, startX, startY);
				levelEditorObjectsArrayList.add(currentBlock);
			}

			if (bPig) {
				if (defaultCheckBox.isSelected()) {
					currentPig = new BasicPig(getWidth() * (0.2 / 9.0),
							getHeight() * (0.2 / 4.0));
				} else {
					currentPig = new BasicPig(0, 0);
				}
				add(currentPig, startX, startY);
				levelEditorObjectsArrayList.add(currentPig);
			}

			if (bRedBird) {
				if (defaultCheckBox.isSelected()) {
					currentRedBird = new RedBird(getWidth() * (0.2 / 9.0),
							getHeight() * (0.2 / 4.0));
				} else {
					currentRedBird = new RedBird(0, 0);
				}
				add(currentRedBird, startX, startY);
				levelEditorObjectsArrayList.add(currentRedBird);
			}

			if (bYellowBird) {
				if (defaultCheckBox.isSelected()) {
					currentYellowBird = new YellowBird(
							getWidth() * (0.2 / 9.0), getHeight() * (0.2 / 4.0));
				} else {
					currentYellowBird = new YellowBird(0, 0);
				}
				add(currentYellowBird, startX, startY);
				levelEditorObjectsArrayList.add(currentYellowBird);
			}

			if (bBlueBird) {
				if (defaultCheckBox.isSelected()) {
					currentBlueBird = new BlueBird(getWidth() * (0.2 / 9.0),
							getHeight() * (0.2 / 4.0));
				} else {
					currentBlueBird = new BlueBird(0, 0);
				}
				add(currentBlueBird, startX, startY);
				levelEditorObjectsArrayList.add(currentBlueBird);
			}

			if (bSlingshot) {
				if (defaultCheckBox.isSelected()) {
					currentSlingshot = new Slingshot(getWidth() * (0.3 / 9.0),
							getHeight() * (0.8 / 5.0));
				} else {
					currentSlingshot = new Slingshot(0, 0);
				}
				add(currentSlingshot, startX, startY);
				levelEditorObjectsArrayList.add(currentSlingshot);
			}

			if (bEraser) {
				GObject obj = getElementAt(e.getX(), e.getY());
				if (obj != null && !(obj instanceof Background)) {
					levelEditorObjectsArrayList.remove(obj);
					remove(obj);
				}
			}

			if (bMove) {
				movingObject = getElementAt(e.getX(), e.getY());
				lastX = e.getX();
				lastY = e.getY();
			}
		}
	}

	/**
	 * Responds when the mouse is dragged: resizes the current object the user
	 * is drawing.
	 */
	public void mouseDragged(MouseEvent e) {
		double x = Math.min(e.getX(), startX);
		double y = Math.min(e.getY(), startY);
		double width = Math.abs(e.getX() - startX);
		double height = Math.abs(e.getY() - startY);

		if (bBlock) {
			if (currentBlock != null) {
				currentBlock.setBlockBounds(width, height);
				add(currentBlock, x, y);
			}
		}

		if (bPig) {
			if (currentPig != null) {
				currentPig.setPigBounds(width, height);
				add(currentPig, x, y);
			}
		}

		if (bRedBird) {
			if (currentRedBird != null) {
				currentRedBird.setBirdBounds(width, height);
				add(currentRedBird, x, y);
			}
		}

		if (bYellowBird) {
			if (currentYellowBird != null) {
				currentYellowBird.setBirdBounds(width, height);
				add(currentYellowBird, x, y);
			}
		}

		if (bBlueBird) {
			if (currentBlueBird != null) {
				currentBlueBird.setBirdBounds(width, height);
				add(currentBlueBird, x, y);
			}
		}

		if (bSlingshot) {
			if (currentSlingshot != null) {
				currentSlingshot.setSlingshotBounds(width, height);
			}
		}

		if (bMove) {
			if (movingObject != null) {
				movingObject.move(e.getX() - lastX, e.getY() - lastY);
				lastX = e.getX();
				lastY = e.getY();
			}
		}
	}

	/**
	 * Writes lines from an ArrayList to a file.
	 */
	private void writeToFile(String fileName) {

		try {
			PrintWriter wr = new PrintWriter(new FileWriter(fileName + ".txt"));
			for (int i = 0; i < levelEditorExportArrayList.size(); i++) {
				String line = levelEditorExportArrayList.get(i);
				wr.println(line);
			}
			wr.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}

	/**
	 * Checks the exporting objects. Goes through the ArrayList of objects the
	 * user has drawn, creating strings representing information to draw the
	 * objects. The information is scaled, so you can make levels on any
	 * computer with any screen size and then when you draw them from the main
	 * game, they will scale to the game screen size.
	 */
	private void checkExportingObjects() {
		for (int i = 0; i < levelEditorObjectsArrayList.size(); i++) {
			Object obj = levelEditorObjectsArrayList.get(i);
			if (obj == null) {
				levelEditorObjectsArrayList.remove(obj);
			}

			if (obj instanceof WoodBlock) {
				String str = "[WoodBlock] " + "["
						+ (((WoodBlock) obj).getWidth() / getWidth()) + "] "
						+ "[" + (((WoodBlock) obj).getHeight() / getHeight())
						+ "] " + "[" + (((WoodBlock) obj).getX() / getWidth())
						+ "] " + "[" + (((WoodBlock) obj).getY() / getHeight())
						+ "]";
				levelEditorExportArrayList.add(str);
			}

			if (obj instanceof BasicPig) {
				String str = "[BasicPig] " + "["
						+ (((BasicPig) obj).getWidth() / getWidth()) + "] "
						+ "[" + (((BasicPig) obj).getHeight() / getHeight())
						+ "] " + "[" + (((BasicPig) obj).getX() / getWidth())
						+ "] " + "[" + (((BasicPig) obj).getY() / getHeight())
						+ "]";
				levelEditorExportArrayList.add(str);
			}

			if (obj instanceof RedBird) {
				String str = "[RedBird] " + "["
						+ (((RedBird) obj).getWidth() / getWidth()) + "] "
						+ "[" + (((RedBird) obj).getHeight() / getHeight())
						+ "] " + "[" + (((RedBird) obj).getX() / getWidth())
						+ "] " + "[" + (((RedBird) obj).getY() / getHeight())
						+ "]";
				levelEditorExportArrayList.add(str);
			}

			if (obj instanceof YellowBird) {
				String str = "[YellowBird] " + "["
						+ (((YellowBird) obj).getWidth() / getWidth()) + "] "
						+ "[" + (((YellowBird) obj).getHeight() / getHeight())
						+ "] " + "[" + (((YellowBird) obj).getX() / getWidth())
						+ "] " + "["
						+ (((YellowBird) obj).getY() / getHeight()) + "]";
				levelEditorExportArrayList.add(str);
			}

			if (obj instanceof BlueBird) {
				String str = "[BlueBird] " + "["
						+ (((BlueBird) obj).getWidth() / getWidth()) + "] "
						+ "[" + (((BlueBird) obj).getHeight() / getHeight())
						+ "] " + "[" + (((BlueBird) obj).getX() / getWidth())
						+ "] " + "[" + (((BlueBird) obj).getY() / getHeight())
						+ "]";
				levelEditorExportArrayList.add(str);
			}

			if (obj instanceof Slingshot) {
				String str = "[Slingshot] " + "["
						+ (((Slingshot) obj).getWidth() / getWidth()) + "] "
						+ "[" + (((Slingshot) obj).getHeight() / getHeight())
						+ "] " + "[" + (((Slingshot) obj).getX() / getWidth())
						+ "] " + "[" + (((Slingshot) obj).getY() / getHeight())
						+ "]";
				levelEditorExportArrayList.add(str);
			}

			if (obj instanceof Background) {
				String str = "[Background] " + "[" + currentBackgroundFile
						+ "]";
				levelEditorExportArrayList.add(str);
			}

			if (obj instanceof GLabel) {
				String str = "[GLabel] " + "[" + ((GLabel) obj).getLabel()
						+ "] " + "[" + (((GLabel) obj).getX() / getWidth())
						+ "] " + "[" + (((GLabel) obj).getY() / getHeight())
						+ "]";
				levelEditorExportArrayList.add(str);
			}
		}
	}

	/**
	 * Responds if an action is performed: if the save world button is clicked,
	 * saves the current world, if the add text button is clicked, adds text to
	 * the screen, and if the remove all button is clicked, removes everything
	 * from the screen, and then draws back the ground, background, and legend.
	 */
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Save World")) {
			checkExportingObjects();
			writeToFile(fileNameInputField.getText());
			levelEditorExportArrayList.clear();
		} else if (cmd.equals("Add Text")) {
			GLabel label = new GLabel(fileNameInputField.getText());
			label.setFont("Helvetica-24");
			label.setColor(Color.WHITE);
			add(label, getWidth() / 2.0, getHeight() / 2.0);
			levelEditorObjectsArrayList.add(label);
		} else if (cmd.equals("Remove All")) {
			levelEditorObjectsArrayList.clear();
			removeAll();
			drawBackground();
			drawGround();
			addLegend();
		}
	}

	/* Private instance variables */
	private GImage ground;
	private Background currentBackground;
	private String currentBackgroundFile;

	private boolean bBlock;
	private boolean bPig;
	private boolean bRedBird;
	private boolean bSlingshot;
	private boolean bEraser;
	private boolean bMove;
	private boolean bYellowBird;
	private boolean bBlueBird;

	private WoodBlock currentBlock;
	private BasicPig currentPig;
	private RedBird currentRedBird;
	private Slingshot currentSlingshot;
	private GObject movingObject;
	private YellowBird currentYellowBird;
	private BlueBird currentBlueBird;

	private GRect blockBox;
	private GRect pigBox;
	private GRect redBirdBox;
	private GRect slingshotBox;
	private GRect eraserBox;
	private GRect moveBox;
	private GRect backgroundBox;
	private GRect yellowBirdBox;
	private GRect blueBirdBox;

	private double startX;
	private double startY;
	private double lastX;
	private double lastY;

	private JCheckBox defaultCheckBox;
	private JLabel nameLabel;
	private JTextField fileNameInputField;
	private JButton saveWorldButton;
	private JButton addTextButton;
	private JButton removeAllButton;

	private ArrayList<Object> levelEditorObjectsArrayList;
	private ArrayList<String> levelEditorExportArrayList;

}
