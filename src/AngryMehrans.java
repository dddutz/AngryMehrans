/*
 * File: AngryMehrans.java
 * -----------------------
 * Name: Derin Dutz
 * Section Leader: Danielle Kain
 * 
 * This program runs the Angry Mehrans game. 
 * Run the game from this program, not AngryMehransLevelEditor.java or AngryMehransGame.java.
 * 
 * IMPORTANT NOTE: This program is memory intensive. In order to avoid lag and memory issues,
 * we must increase the heap size. One way to do this is to go to the "Run" menu, then click
 * on the "Run Configurations" option, then find AngryMehrans.java, AngryMehransGame.java,
 * and AngryMehransLevelEditor.java, select each one, go to the "Arguments" section, and 
 * add the following: "-Xms512M -Xmx1524M" (without the "") to the "VM Arguments:" section.
 * Finally, click apply for each one. Even with this memory expansion, in some rare cases,
 * because of the size of some of the files involved in this program, the program can still
 * slow down over long periods of time. In these rare cases, the only solution I could find was
 * to close the program and start it up again.
 */

import java.applet.AudioClip;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import acm.program.*;
import acm.util.MediaTools;
import acm.graphics.*;

public class AngryMehrans extends GraphicsProgram {

	/** The width of the application window */
	public static final int APPLICATION_WIDTH = 1350;

	/** The height of the application window */
	public static final int APPLICATION_HEIGHT = 700;

	/**
	 * Initializes the program: sets the window size, sets up buttons.
	 */
	public void init() {
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);

		setupButtons();

		addMouseListeners();
		addActionListeners();
	}

	/**
	 * Sets up various JComboBoxes and JButtons for the music control.
	 */
	private void setupButtons() {
		initSongChooser1();
		add(new JButton("Start Music"), SOUTH);
		add(new JButton("Stop Music"), SOUTH);
		initSongChooser2();
		add(new JButton("Mix Song"), SOUTH);
		add(new JButton("Stop Mix"), SOUTH);
	}

	/**
	 * Sets up the first song chooser: creates a ComboBox.
	 */
	private void initSongChooser1() {
		pickSong1 = new JComboBox();
		pickSong1.addItem("Medieval");
		pickSong1.addItem("Epic");
		pickSong1.addItem("Awesome");
		pickSong1.addItem("Star Wars");
		pickSong1.addItem("Spiritual");
		pickSong1.addItem("Epically epic");
		pickSong1.setEditable(false);
		pickSong1.setSelectedItem("Medieval");
		add(new JLabel("   Music:"), SOUTH);
		add(pickSong1, SOUTH);
	}

	/**
	 * Sets up the second song chooser: creates a ComboBox.
	 */
	private void initSongChooser2() {
		pickSong2 = new JComboBox();
		pickSong2.addItem("Medieval");
		pickSong2.addItem("Epic");
		pickSong2.addItem("Awesome");
		pickSong2.addItem("Star Wars");
		pickSong2.addItem("Spiritual");
		pickSong2.addItem("Epically epic");
		pickSong2.setEditable(false);
		pickSong2.setSelectedItem("Medieval");
		add(new JLabel("   Additional Music:"), SOUTH);
		add(pickSong2, SOUTH);
	}

	/**
	 * Gets the current song from the first song chooser.
	 * 
	 * @return song file
	 */
	private String getCurrentSong1() {
		String songFile = (String) pickSong1.getSelectedItem();
		if (songFile.equals("Medieval")) {
			return "aom_soundtrack.wav";
		} else if (songFile.equals("Epic")) {
			return "epic.wav";
		} else if (songFile.equals("Awesome")) {
			return "lotr.wav";
		} else if (songFile.equals("Star Wars")) {
			return "star_wars_medley.wav";
		} else if (songFile.equals("Spiritual")) {
			return "baba_yetu.wav";
		} else if (songFile.equals("Epically epic")) {
			return "narnia.wav";
		} else {
			return null;
		}
	}

	/**
	 * Gets the current song from the second song chooser.
	 * 
	 * @return song file
	 */
	private String getCurrentSong2() {
		String songFile = (String) pickSong2.getSelectedItem();
		if (songFile.equals("Medieval")) {
			return "aom_soundtrack.wav";
		} else if (songFile.equals("Epic")) {
			return "narnia.wav";
		} else if (songFile.equals("Awesome")) {
			return "lotr.wav";
		} else if (songFile.equals("Star Wars")) {
			return "star_wars_medley.wav";
		} else if (songFile.equals("Spiritual")) {
			return "baba_yetu.wav";
		} else if (songFile.equals("Epically epic")) {
			return "epic.wav";
		} else {
			return null;
		}
	}

	/**
	 * Runs the program: plays the initial music, draws the background, and
	 * loops through animating various objects.
	 */
	public void run() {
		playInitialMusic();
		drawBackground();
		while (true) {
			while (!gameStarted && !levelEditorStarted) {
				animate();
				int counter = 0;
				while (animationIsRunning && counter < 1300) {
					pause(1);
					counter++;
					checkAnimation();
				}
			}
		}
	}

	/**
	 * Draws the bacgkround.
	 */
	private void drawBackground() {
		GImage background = new GImage("splash_screen_background.jpg");
		background.setSize(getWidth(), getHeight());
		add(background, 0, 0);

		GImage titleImage = new GImage("title.png");
		titleImage.setSize(getWidth() * (400.0 / 1021.0), getHeight()
				* (150.0 / 643.0));
		add(titleImage, getWidth() / 2.0 - titleImage.getWidth() / 2.0,
				getHeight() * (50.0 / 643.0));

		startGameImage = new GImage("start_game_text.png");
		startGameImage.setSize(getWidth() * (200.0 / 1021.0), getHeight()
				* (69.0 / 643.0));
		add(startGameImage, (getWidth() * (80.0 / 1021.0)),
				(getHeight() * (540.0 / 643.0)));

		levelSelectImage = new GImage("level_select_text.png");
		levelSelectImage.setSize(getWidth() * (200.0 / 1021.0), getHeight()
				* (69.0 / 643.0));
		add(levelSelectImage, (getWidth() - levelSelectImage.getWidth()) / 2.0,
				(getHeight() * (540.0 / 643.0)));

		levelEditorImage = new GImage("level_editor_text.png");
		levelEditorImage.setSize(getWidth() * (200.0 / 1021.0), getHeight()
				* (69.0 / 643.0));
		add(levelEditorImage,
				(getWidth() / 2.0)
						+ (getWidth() / 2.0 - startGameImage.getX() - startGameImage
								.getWidth()), startGameImage.getY());
	}

	/**
	 * Plays the initial music for the game.
	 */
	private void playInitialMusic() {
		mainSoundtrack = MediaTools.loadAudioClip("aom_soundtrack.wav");
		mainSoundtrack.loop();
	}

	/**
	 * Animates the screen: puts rings in front of the menu options that the
	 * user rolls their mouse over.
	 */
	private void animate() {
		if (startGameImage.contains(currentMouseX, currentMouseY)) {
			animationIsRunning = true;
			if (levelSelectAnimation != null) {
				remove(levelSelectAnimation);
			}
			if (levelEditorAnimation != null) {
				remove(levelEditorAnimation);
			}

			startGameAnimation = new RingAnimation(startGameImage.getHeight(),
					startGameImage.getHeight());
			add(startGameAnimation,
					startGameImage.getX() - (startGameImage.getHeight() / 2.0),
					startGameImage.getY() + (startGameImage.getHeight() / 2.0));

		} else if (levelSelectImage.contains(currentMouseX, currentMouseY)) {
			animationIsRunning = true;
			if (startGameAnimation != null) {
				remove(startGameAnimation);
			}
			if (levelEditorAnimation != null) {
				remove(levelEditorAnimation);
			}

			levelSelectAnimation = new RingAnimation(
					levelSelectImage.getHeight(), levelSelectImage.getHeight());
			add(levelSelectAnimation, levelSelectImage.getX()
					- (levelSelectImage.getHeight() / 2.0),
					levelSelectImage.getY()
							+ (levelSelectImage.getHeight() / 2.0));

		} else if (levelEditorImage.contains(currentMouseX, currentMouseY)) {
			animationIsRunning = true;
			if (startGameAnimation != null) {
				remove(startGameAnimation);
			}
			if (levelSelectAnimation != null) {
				remove(levelSelectAnimation);
			}

			levelEditorAnimation = new RingAnimation(
					levelEditorImage.getHeight(), levelEditorImage.getHeight());
			add(levelEditorAnimation, levelEditorImage.getX()
					- (levelEditorImage.getHeight() / 2.0),
					levelEditorImage.getY()
							+ (levelEditorImage.getHeight() / 2.0));
		}
	}

	/**
	 * Checks the animations: makes sure more than one aren't running at the
	 * same time.
	 */
	private void checkAnimation() {
		if (!startGameImage.contains(currentMouseX, currentMouseY)
				&& !levelSelectImage.contains(currentMouseX, currentMouseY)
				&& !levelEditorImage.contains(currentMouseX, currentMouseY)) {
			animationIsRunning = false;
			if (startGameAnimation != null) {
				// startGameAnimation.sendToBack();
				remove(startGameAnimation);
			}
			if (levelEditorAnimation != null) {
				// levelEditorAnimation.sendToBack();
				remove(levelEditorAnimation);
			}
			if (levelSelectAnimation != null) {
				// levelEditorAnimation.sendToBack();
				remove(levelSelectAnimation);
			}
		}
	}

	/**
	 * Responds when the mouse is clicked: starts the game, level selection
	 * screen, or level editor depending on what the user clicks.
	 */
	public void mouseClicked(MouseEvent e) {
		if (startGameImage.contains(e.getX(), e.getY())) {
			if (startGameImage != null) {
				gameStarted = true;
				isPlayingSelectedLevel = false;
				RunnableAngryMehransGame runnableAngryMehransGame = new RunnableAngryMehransGame();
				Thread angryMehransGameThread = new Thread(
						runnableAngryMehransGame);
				angryMehransGameThread.start();
			}
		}

		if (levelSelectImage.contains(e.getX(), e.getY())) {
			if (levelSelectImage != null) {
				gameStarted = true;
				isPlayingSelectedLevel = true;
				RunnableAngryMehransGame runnableAngryMehransGame = new RunnableAngryMehransGame();
				Thread angryMehransGameThread = new Thread(
						runnableAngryMehransGame);
				angryMehransGameThread.start();
			}
		}

		if (levelEditorImage.contains(e.getX(), e.getY())) {
			if (levelEditorImage != null) {
				levelEditorStarted = true;
				isPlayingSelectedLevel = false;
				RunnableAngryMehransLevelEditor runnableAngryMehransLevelEditor = new RunnableAngryMehransLevelEditor();
				Thread angryMehransLevelEditorThread = new Thread(
						runnableAngryMehransLevelEditor);
				angryMehransLevelEditorThread.start();
			}
		}

	}

	/**
	 * Responds when the mouse is moved.
	 */
	public void mouseMoved(MouseEvent e) {
		currentMouseX = e.getX();
		currentMouseY = e.getY();
	}

	/**
	 * Responds when buttons are pressed: controls the music.
	 */
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Start Music")) {
			mainSoundtrack.stop();
			mainSoundtrack = MediaTools.loadAudioClip(getCurrentSong1());
			mainSoundtrack.loop();
			mainSoundtrackStopped = false;
		} else if (cmd.equals("Stop Music")) {
			if (!mainSoundtrackStopped) {
				mainSoundtrack.stop();
				mainSoundtrackStopped = true;
			}
		} else if (cmd.equals("Mix Song")) {
			mixingSoundtrack = MediaTools.loadAudioClip(getCurrentSong2());
			mixingSoundtrack.loop();
			mixingSoundtrackSelected = true;
			mixingSoundtrackStopped = false;
		} else if (cmd.equals("Stop Mix")) {
			if (!mixingSoundtrackStopped) {
				mixingSoundtrack.stop();
				mixingSoundtrackStopped = true;
			}
		}
	}

	/**
	 * Starts the soundtrack.
	 */
	public static void startSoundtrack() {
		mainSoundtrack.loop();
		mainSoundtrackStopped = false;
		if (mixingSoundtrackSelected) {
			mixingSoundtrack.loop();
			mixingSoundtrackStopped = false;
		}
	}

	/**
	 * Stops the soundtrack.
	 */
	public static void stopSoundtrack() {
		if (!mainSoundtrackStopped) {
			mainSoundtrack.stop();
			mainSoundtrackStopped = true;
		}
		if (!mixingSoundtrackStopped) {
			mixingSoundtrack.stop();
			mixingSoundtrackStopped = true;
		}
	}

	/* Instance Variables */
	private boolean animationIsRunning = false;
	private boolean gameStarted = false;
	public static boolean isPlayingSelectedLevel = false;
	private boolean levelEditorStarted = false;

	private double currentMouseX;
	private double currentMouseY;

	private RingAnimation startGameAnimation;
	private RingAnimation levelSelectAnimation;
	private RingAnimation levelEditorAnimation;
	private GImage startGameImage;
	private GImage levelSelectImage;
	private GImage levelEditorImage;

	private JComboBox pickSong1;
	private JComboBox pickSong2;

	private static AudioClip mainSoundtrack;
	private static boolean mainSoundtrackStopped = false;
	private static AudioClip mixingSoundtrack;
	private static boolean mixingSoundtrackStopped = true;
	private static boolean mixingSoundtrackSelected = false;
}
