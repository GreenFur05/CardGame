package view;

import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback {

	private GameFrame frame;
	
	public GameEngineCallbackGUI(GameFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.getPlayerPanel().newCard(player, card);
				frame.getPlayerPanel().updateDetails(frame, engine);
			}
		});
		
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.getPlayerPanel().newCard(player, card);
				frame.getPlayerPanel().updateDetails(frame, engine);
			}
		});
		
	}

	@Override
	public void result(Player player, int result, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.getPlayerPanel().updateDetails(frame, engine);				
			}
		});
		
	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		frame.getPlayerPanel().newHouseCard(card);
		frame.getPlayerPanel().updateHouse(frame, engine);
		
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		frame.getPlayerPanel().newHouseCard(card);
		frame.getPlayerPanel().updateHouse(frame, engine);
		
	}

	@Override
	public void houseResult(int result, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.getPlayerPanel().updateDetails(frame, engine);
				frame.getStatusBar().finishedHouseStatus();
				
				DefaultListModel<String> listModel = frame.getPlayerList().getlistModel();
				
				if (frame.getPlayerList().getSelectedIndex() == frame.getPlayerList().getHouseIndex()) {
					frame.getPlayerList().setSelectedIndex(frame.getPlayerList().getHouseIndex() - 1);
				}
				
				frame.getPlayerList().removeHouse();
				
				for (int i = 0; i < listModel.size(); i++) {
					String selected = listModel.getElementAt(i);
					int index = selected.lastIndexOf("(");
					String id = selected.substring(index + 1, selected.length() - 1);	
					
					Player player = engine.getPlayer(id);
					
					frame.getSummaryPanel().updateSelectedPlayer(frame, player.getPoints(), i);
				}
				
				// Calls a JOptionPane, and then proceed to reset all bets
				ResultDialog dialog = new ResultDialog(frame, engine, result);
				dialog.createDialog();
				
				
			}
		});
		
	}

}
