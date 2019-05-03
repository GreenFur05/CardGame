package view;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.interfaces.GameEngine;


@SuppressWarnings("serial")
public class StatusBar extends JPanel {
	private JLabel status;
	
	public StatusBar(GameEngine gameEngine) {
		status = new JLabel("Welcome! Add a player to get started!",SwingConstants.CENTER);
		status.setFont(new Font(null, Font.BOLD, 12));
		
		add(status);
		// add specific text from playerlist
	}
	
	public void dealingPlayerStatus(String name) {
		status.setText("Dealing to " + name + "...");
	}
	
	public void finishedPlayerStatus(String name) {
		status.setText("Finished dealing to " + name + "!");
	}
	
	public void betPlayerStatus(String name) {
		status.setText("Place a bet for " + name + "!");
	}
	
	public void dealPlayerStatus(String name) {
		status.setText("Deal to " + name + "!");
	}
	
	public void dealingHouseStatus() {
		status.setText("Dealing to house...");
	}
	
	public void finishedHouseStatus() {
		status.setText("Finished dealing to house!");
	}
}
