package com.group2.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.group2.SpriteSheet;

/**
 * class to initialize and create a character select feature
 */
public class CharacterSelect {
	
	private Rectangle character1 = new Rectangle (0, 200, 300, 100);
	private Rectangle character2 = new Rectangle (0, 325, 300, 100);
	private Rectangle character3 = new Rectangle (0, 450, 300, 100);
	private Rectangle character4 = new Rectangle (0, 575, 300, 100);
	private Rectangle character5 = new Rectangle (500, 200, 300, 100);
	private BufferedImage char1Sprite;
	private BufferedImage char2Sprite;
	private BufferedImage char3Sprite;
	private BufferedImage char4Sprite;
	private BufferedImage char5Sprite;

	/**
	 * Constructor for CharacterSelect
	 * @param spritesheet passes spritesheet for each different character
	 */
	public CharacterSelect(SpriteSheet spritesheet) {
		char1Sprite = spritesheet.getSprite(1, 1);
		char2Sprite = spritesheet.getSprite(2, 1);
		char3Sprite = spritesheet.getSprite(3, 1);
		char4Sprite = spritesheet.getSprite(4, 1);
		char5Sprite = spritesheet.getSprite(0, 0);
	}
	
	/**
	 * renders the characterselect menu
	 * @param g graphics library
	 */
	public void render(Graphics2D g) {
		Font font = new Font("arial", Font.BOLD, 50);
		g.setFont(font);
		g.setColor(Color.white);
		FontMetrics metrics = g.getFontMetrics(font);
		g.drawString("SELECT YOUR CHARACTER", 1024 / 2 - metrics.stringWidth("SELECT YOUR CHARACTER") / 2, 100);
		g.setColor(Color.lightGray);
		g.fill(character1);
		g.fill(character2);
		g.fill(character3);
		g.fill(character4);
		g.fill(character5);
		g.setColor(Color.black);
		g.drawString("Sahba", character1.x + 100, character1.y + 70);
		g.drawString("John", character2.x + 100, character2.y + 70);
		g.drawString("David", character3.x + 100, character3.y + 70);
		g.drawString("Dylan", character4.x + 100, character4.y + 70);
		g.drawString("Guest", character5.x + 100, character5.y + 70);
		g.drawImage(char1Sprite, 300, character1.y, 100, 100, null);
		g.drawImage(char2Sprite, 300, character2.y, 100, 100, null);
		g.drawImage(char3Sprite, 300, character3.y, 100, 100, null);
		g.drawImage(char4Sprite, 300, character4.y, 100, 100, null);
		g.drawImage(char5Sprite, character5.x + 300, character5.y, 100, 100, null);

	}

}
