package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameFrame;

public class DealPlayerListener implements ActionListener {

	private GameFrame frame;
	private GameEngine gameEngine;
	
	public DealPlayerListener(GameFrame frame, GameEngine gameEngine) {
		this.frame = frame;
		this.gameEngine = gameEngine;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Update status bar
		frame.getStatusBar().dealingPlayerStatus(frame.getPlayerList().getSelectedPlayer(gameEngine).getPlayerName());
		
		new Thread() {
			@Override
			public void run() {
				// Deals to selected player
				gameEngine.dealPlayer(frame.getPlayerList().getSelectedPlayer(gameEngine), 1000);
				
				/*
				 *  Cases where house will be dealt:
				 *  	- all players have been dealt
				 *  	- some players have been dealt, the rest have not placed their bet
				 *  
				 *  Cases where house will NOT be dealt:
				 *  	- some players that have placed bet although NOT been dealt
				 *  
				 */
				boolean houseDeal = false;
				DefaultListModel<String> listModel = frame.getPlayerList().getlistModel();
				
				for (int i = 0; i < listModel.size(); i++) {
					String selected = listModel.getElementAt(i);
					int index = selected.lastIndexOf("(");
					String id = selected.substring(index + 1, selected.length() - 1);	
					
					Player player = gameEngine.getPlayer(id);
					
					if (player.getResult() > 0 || player.getBet() == 0) {
						houseDeal = true;
					}
					else {
						houseDeal = false;
					}
				}
				
				if (houseDeal) {
					frame.getStatusBar().dealingHouseStatus();
					frame.getPlayerList().addHouse();
					gameEngine.dealHouse(1000);
					
					// Resets result so that house won't deal automatically after first hand if another round played
					for (Player player : gameEngine.getAllPlayers()) {
						player.setResult(0);
					}
				}
			}
		}.start();		
	}
}
