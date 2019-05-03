package view;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import model.interfaces.GameEngine;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class ResultDialog extends JDialog {
	
	private JPanel mainPanel;
	
	public ResultDialog(GameFrame frame, GameEngine gameEngine, int result) {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		for (Player player : gameEngine.getAllPlayers()) {
			JPanel panel = new JPanel();
			panel.add(Box.createHorizontalGlue());
			JLabel id, name, points;
			id = new JLabel("ID: " + player.getPlayerId());
			name = new JLabel("Player: " + player.getPlayerName());
			points = new JLabel("Points: " + player.getPoints());
			panel.add(id);
			panel.add(name);
			panel.add(points);
			panel.add(Box.createHorizontalGlue());

			mainPanel.add(panel);
		}
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel("House result: " + result);
		panel.add(label);
		mainPanel.add(panel);
		
	}
	
	public void createDialog() {
		JOptionPane.showMessageDialog(null, mainPanel, "Results", JOptionPane.INFORMATION_MESSAGE);
	}
}
