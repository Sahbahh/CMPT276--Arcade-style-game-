package com.group2;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * spritesheet class for game
 */
public class SpriteSheet {
	
	private BufferedImage image;
	
	/**
	 * SpriteSheet Constructor
	 */
	public SpriteSheet() {
		try {
			this.image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("sprites.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * returns spritesheet
	 * @param x sprite at x
	 * @param y sprite at y
	 * @return sprite image
	 */
	public BufferedImage getSprite(int x, int y) {
		BufferedImage sprite = image.getSubimage(x * 32, y * 32, 32, 32);
		return sprite;
	}
}
