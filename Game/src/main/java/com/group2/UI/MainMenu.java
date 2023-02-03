package com.group2.UI;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Rectangle;

/**
 * class to initialize and create a main menu
 */
public class MainMenu {
	
	private Rectangle playButton = new Rectangle (0, 200, 300, 100);
	private Rectangle levelSelectButton = new Rectangle (0, 325, 300, 100);
	private Rectangle instructionsButton = new Rectangle (0, 450, 300, 100);
	private Rectangle quitButton = new Rectangle (0, 575, 300, 100);

	/**
	 * draws MainMenu
	 * @param g graphics
	 */
	public void render(Graphics2D g) {
		Font font = new Font("arial", Font.BOLD, 50);
		g.setFont(font);
		g.setColor(Color.white);
		FontMetrics metrics = g.getFontMetrics(font);
		g.drawString("Coding Mania", 1024 / 2 - metrics.stringWidth("Coding Mania") / 2, 100);
		g.setColor(Color.lightGray);
		g.fill(playButton);
		g.fill(levelSelectButton);
		g.fill(instructionsButton);
		g.fill(quitButton);
		g.setColor(Color.black);
		g.drawString("Play", playButton.x + 100, playButton.y + 70);
		g.drawString("Levels", levelSelectButton.x + 60, levelSelectButton.y + 70);
		g.drawString("Instructions", instructionsButton.x + 10, instructionsButton.y + 70);
		g.drawString("Quit", quitButton.x + 100, quitButton.y + 70);
	}
}
