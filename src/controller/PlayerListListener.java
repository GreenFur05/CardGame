package controller;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.interfaces.GameEngine;
import view.GameFrame;

public class PlayerListListener implements ListSelectionListener {

	private GameFrame frame;
	private GameEngine gameEngine;
	
	public PlayerListListener(GameFrame frame, GameEngine gameEngine) {
		this.frame = frame;
		this.gameEngine = gameEngine;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {
			if (!(frame.getPlayerList().getHouseActive())) {
				frame.getToolbar().setBetEnabled(true);
				
				if (frame.getPlayerList().getSelectedPlayer(gameEngine).getBet() > 0) {
						frame.getToolbar().setDealEnabled(true);
						// Update status bar
						frame.getStatusBar().dealPlayerStatus(frame.getPlayerList().getSelectedPlayer(gameEngine).getPlayerName());
						
				}
				else {
					frame.getToolbar().setDealEnabled(false);
					// Update status bar
					frame.getStatusBar().betPlayerStatus(frame.getPlayerList().getSelectedPlayer(gameEngine).getPlayerName());
				}	
				
				//Update player panel
				frame.getPlayerPanel().updateDetails(frame, gameEngine);
			}
			else if (frame.getPlayerList().getSelectedIndex() != frame.getPlayerList().getHouseIndex()) {
				frame.getPlayerPanel().updateHouse(frame, gameEngine);
			}

		}
	}
}
