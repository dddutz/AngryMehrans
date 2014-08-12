/*
 * File: RunnableAngryMehransGame.java
 * -----------------------------------
 * Name: Derin Dutz
 * Section Leader: Danielle Kain
 * 
 * This class creates a RunnableAngryMehransGame for AngryMehrans. 
 * It allows AngryMehrans to run an AngryMehransGame() as a Thread. 
 * This is needed so that AngryMehrans can run an AngryMehransGame()
 * at the same time as an AngryMehransLevelEditor() at the same time
 * that AngryMehrans itself is running.
 */

public class RunnableAngryMehransGame implements Runnable {

	/**
	 * Runs the program: creates a new AngryMehransGame and starts it.
	 */
	public void run() {
		game = new AngryMehransGame();
		game.start();
	}

	public static AngryMehransGame game;

}
