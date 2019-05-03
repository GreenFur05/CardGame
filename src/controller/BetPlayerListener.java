package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import view.GameFrame;
import view.PlaceBetDialog;

public class BetPlayerListener implements ActionListener {

	private GameFrame frame;
	private GameEngine gameEngine;
	
	public BetPlayerListener(GameFrame frame, GameEngine gameEngine) {
		this.frame = frame;
		this.gameEngine = gameEngine;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Place bet
		PlaceBetDialog dialog = new PlaceBetDialog();
		if (dialog.getResult()) {
			double bet = dialog.getBet();
			if (bet == 0.0) {
				JOptionPane.showMessageDialog(null, "Bet not added - please enter a valid amount for bet!", "Error",JOptionPane.ERROR_MESSAGE);
			}
			else {
				if (!(gameEngine.placeBet(frame.getPlayerList().getSelectedPlayer(gameEngine), (int) bet))) {
					JOptionPane.showMessageDialog(null, "Bet not added - please enter a bet less than the player's available points!", "Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
					frame.getToolbar().setDealEnabled(true);
					frame.getPlayerPanel().updateDetails(frame, gameEngine);
					frame.getStatusBar().dealPlayerStatus(frame.getPlayerList().getSelectedPlayer(gameEngine).getPlayerName());
				}
			}
		}
	}
}

