package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import view.AddPlayerDialog;
import view.GameFrame;

public class AddPlayerListener implements ActionListener {
	private GameFrame frame;
	private GameEngine gameEngine;
	
	public AddPlayerListener(GameFrame frame, GameEngine gameEngine) {
		this.frame = frame;
		this.gameEngine = gameEngine;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Add a new player with a simple input dialog
		AddPlayerDialog dialog = new AddPlayerDialog();
		if (dialog.getResult()) {
			if (dialog.getPlayerPoints() == 0.0) {
				// Displays error message when invalid points are entered
				JOptionPane.showMessageDialog(null, "Player not added - please enter a valid number for inital points!", "Error",JOptionPane.ERROR_MESSAGE);
			}
			else {
				String id = String.valueOf(gameEngine.getAllPlayers().size());
				String name = dialog.getPlayerName();
				int points = (int) dialog.getPlayerPoints();
				
				// Add player to GameEngine
				gameEngine.addPlayer(new SimplePlayer(id, name, points));
				
				// Add player to player list
				frame.getPlayerList().addPlayer(id, name);
				frame.getPlayerPanel().newPlayer();
				
				// Add player to summary list
				frame.getSummaryPanel().addPlayer(name,points);
			}
		}
	}
}
