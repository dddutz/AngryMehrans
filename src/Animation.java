/*
 * File: Animation.java
 * --------------------
 * Name: Derin Dutz
 * Section Leader: Danielle Kain
 * 
 * This class allows for the use of an Animation object.
 */

import acm.graphics.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import acm.graphics.GImage;

public class Animation extends GCompound {

	/**
	 * Reads a sprite sheet and partitions the sprite sheet into individual
	 * sprites. Then puts the sprites in an ArrayList.
	 * 
	 * @param spritesArray
	 *            ArrayList of sprites
	 * @param fileName
	 *            file name
	 * @param rows
	 *            number of rows
	 * @param columns
	 *            number of columns
	 */
	public void readImage(ArrayList<GImage> spritesArray, String fileName,
			int rows, int columns) {
		BufferedImage spriteSheet;
		try {
			spriteSheet = ImageIO.read(new File(fileName));
			spriteWidth = spriteSheet.getWidth() / columns;
			spriteHeight = spriteSheet.getHeight() / rows;
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					BufferedImage spriteBufferedImage = spriteSheet
							.getSubimage(j * spriteWidth, i * spriteHeight,
									spriteWidth, spriteHeight);

					int pixelArrayHeight = spriteBufferedImage.getHeight();
					int pixelArrayWidth = spriteBufferedImage.getWidth();

					int[][] pixelArray = new int[pixelArrayWidth][pixelArrayHeight];
					for (int pixelRow = 0; pixelRow < pixelArrayHeight; pixelRow++) {
						for (int pixelCol = 0; pixelCol < pixelArrayWidth; pixelCol++) {
							pixelArray[pixelCol][pixelRow] = spriteBufferedImage
									.getRGB(pixelCol, pixelRow);
						}
					}

					spritesArray.add(new GImage(pixelArray));
				}
			}
		} catch (IOException e) {
			System.out.println("Illegal image.");
		}
	}

	/* Private instance variables */
	private int spriteWidth;
	private int spriteHeight;
}
