package com.group2.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import com.group2.entities.userCharacter;

/**
 * class to initialize and create a HUD timer feature
 */
public class HUD {
	private userCharacter player;
	private int seconds;
	private int minutes;
	private long startTime;
	private long elapsedTime;
	private String timerString;
	
	/**
	 * HUD constructor
	 * @param player passes player type to HUD
	 */
	public HUD(userCharacter player) {
		this.player = player;
	}
	
	/**
	 * starts timer for game
	 */
	public void startTimer() {
		startTime = System.currentTimeMillis();
	}
	
	/**
	 * translates timer to string
	 * @return timer as string
	 */
	public String getTimerString(){
		return timerString;
	}
	
	/**
	 * draws HUD timer
	 * @param g graphics
	 */
	public void render(Graphics2D g) {
		elapsedTime = System.currentTimeMillis() - startTime;
		seconds = (int)Math.floor((elapsedTime / 1000) % 60);
		minutes = (int)Math.floor(elapsedTime / (1000 * 60));
		Font font = new Font("Helvetica", Font.BOLD, 30);
		g.setFont(font);
		g.setColor(new Color(255, 255, 255, 255));
		if(Math.floor(minutes / 10) > 0) {
			timerString = minutes + ":";
		}
		else {
			timerString = "0" + minutes + ":";
		}
		if(Math.floor(seconds / 10) > 0) {
			timerString = timerString + seconds;
		}
		else {
			timerString = timerString + "0" + seconds;
		}
		
		String scoreString = "Code Quality: " + player.getScore();
		FontMetrics metrics = g.getFontMetrics(font);
		g.drawString(timerString, 0, 25);
		g.drawString(scoreString, 1024/2 - metrics.stringWidth(scoreString) / 2, 25);
	}
}
