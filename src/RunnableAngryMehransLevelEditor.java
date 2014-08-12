/*
 * File: RunnableAngryMehransLevelEditor.java
 * ------------------------------------------
 * Name: Derin Dutz
 * Section Leader: Danielle Kain
 * 
 * This class creates a RunnableAngryMehransLevelEditor for AngryMehrans. 
 * It allows AngryMehrans to run an AngryMehransLevelEditor() as a Thread. 
 * This is needed so that AngryMehrans can run an AngryMehransGame()
 * at the same time as an AngryMehransLevelEditor() at the same time
 * that AngryMehrans itself is running.
 */
public class RunnableAngryMehransLevelEditor implements Runnable {

	/**
	 * Runs the program: creates a new AngryMehransLevelEditor and starts it.
	 */
	public void run() {
		AngryMehransLevelEditor levelEditor = new AngryMehransLevelEditor();
		levelEditor.start();
	}

}
