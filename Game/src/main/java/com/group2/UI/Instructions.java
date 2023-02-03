package com.group2.UI;

import com.group2.SpriteSheet;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * class to initialize and create instructions menu feature
 */
public class Instructions {

	private Rectangle backButton = new Rectangle (0, 500, 300, 100);

	private BufferedImage code;
	private BufferedImage bonus;
	private BufferedImage bug;
	private BufferedImage wall;
	private BufferedImage goal;
	private BufferedImage error;

	/**
	 * Instructions constructor
	 * @param spritesheet passes spritesheet to use sprites in instructions
	 */
	public Instructions(SpriteSheet spritesheet){
		code = spritesheet.getSprite(5, 0);
		bonus = spritesheet.getSprite(2, 0);
		bug = spritesheet.getSprite(3, 0);
		wall = spritesheet.getSprite(1, 0);
		goal = spritesheet.getSprite(0, 1);
		error = spritesheet.getSprite(4, 0);
	}

	/**
	 * draws instructions menu
	 * @param g graphics
	 */
	public void render(Graphics2D g) {
		Font font = new Font("arial", Font.BOLD, 50);
		g.setFont(font);
		g.setColor(Color.white);
		FontMetrics metrics = g.getFontMetrics(font);
		g.drawString("INSTRUCTIONS", 1024 / 2 - metrics.stringWidth("INSTRUCTIONS") / 2, 100);
		g.setColor(Color.lightGray);
		g.fill(backButton);
		g.setColor(Color.black);
		g.drawString("Back", backButton.x + 100, backButton.y + 70);
		font = new Font("arial", Font.PLAIN, 30);
		g.setFont(font);
		g.setColor(Color.white);
		metrics = g.getFontMetrics(font);
		g.drawString("You need to collect all the code" , 50, 175);
		g.drawImage(code, 50 + metrics.stringWidth("You need to collect all the code "), 175 - 28 , 32, 32, null);

		g.drawString("After collecting all the code, finish the level with run()" , 50, 225);
		g.drawImage(goal, 50 + metrics.stringWidth("After collecting all the code, finish the level with run() "), 225 - 28 , 40, 40, null);

		g.drawString("Avoid the bugs " , 50, 275);
		g.drawString(" chasing you" , metrics.stringWidth("Avoid the bugs ") + 82, 275);
		g.drawImage(bug, 50 + metrics.stringWidth("Avoid the bugs "), 275 - 28 , 32, 32, null);

		g.drawString("Stepping on the Errors " , 50, 325);
		g.drawString(" will lose you points" , metrics.stringWidth("Stepping on the Errors ") + 82, 325);
		g.drawImage(error, 50 + metrics.stringWidth("Stepping on the Errors "), 325 - 28 , 32, 32, null);

		g.drawString("If you run out of points or get caught by a bug, you lose" , 50, 375);

		g.drawString("Semicolon walls " , 50, 425);
		g.drawString(" will block you" , metrics.stringWidth("Semicolon walls ") + 82, 425);
		g.drawImage(wall, 50 + metrics.stringWidth("Semicolon walls "), 425 - 28 , 32, 32, null);

		g.drawString("Collect energy drinks " , 50, 475);
		g.drawString(" for bonus points" , metrics.stringWidth("Collect energy drinks ") + 82, 475);
		g.drawImage(bonus, 50 + metrics.stringWidth("Collect energy drinks "), 475 - 28 , 32, 32, null);
	}
}
