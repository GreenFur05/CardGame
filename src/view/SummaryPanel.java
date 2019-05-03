package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import model.interfaces.GameEngine;

@SuppressWarnings("serial")
public class SummaryPanel extends JPanel {
			
	private JPanel mainPanel;
	
	public SummaryPanel(GameFrame frame, GameEngine gameEngine) {
		setPreferredSize(new Dimension(frame.getWidth() / 4,getSize().height));
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel header = new JLabel("Summary");
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		header.setFont(frame.getBoldFont());
		mainPanel.add(header);
		JScrollPane scrollPane = new JScrollPane(mainPanel);
		add(scrollPane);
	}

	public void addPlayer(String name, int points) {
		JPanel panel = new JPanel(new GridLayout(2,1));
		JLabel playerLabel = new JLabel("Player: " + name);
		JLabel pointsLabel = new JLabel("Points: " + points);
		playerLabel.setFont(new Font(null, Font.PLAIN, 18));
		pointsLabel.setFont(new Font(null, Font.PLAIN, 18));
		panel.add(playerLabel);
		panel.add(pointsLabel);
		
		panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		
		mainPanel.add(panel);
	}
	
	public void updateSelectedPlayer(GameFrame frame, int points, int iterator) {	
		// Find component to update by scaling down tree of components
		Component[] root = this.getComponents();
		JScrollPane firstBranches = (JScrollPane) root[0];
		Component[] firstBranch = firstBranches.getComponents();
		JViewport secondBranches = (JViewport) firstBranch[0];
		Component[] secondBranch = secondBranches.getComponents();
		JPanel thirdBranches = (JPanel) secondBranch[0];
		Component[] thirdBranch = thirdBranches.getComponents();
		JPanel panel = (JPanel) thirdBranch[iterator + 1];
		
		Component[] subComponents = panel.getComponents();
		JLabel pointsLabel = (JLabel)subComponents[1];
		pointsLabel.setText("Points: " + points);
		mainPanel.repaint();
	}

}
