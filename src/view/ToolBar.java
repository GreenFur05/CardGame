package view;

import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JToolBar;

import controller.AddPlayerListener;
import controller.BetPlayerListener;
import controller.DealPlayerListener;
import model.interfaces.GameEngine;

@SuppressWarnings("serial")
public class ToolBar extends JToolBar {
	
	private JButton addButton;
	private JButton betButton;
	private JButton dealButton;
	
	public ToolBar(GameFrame frame, GameEngine gameEngine) {
		add(Box.createHorizontalGlue());
		Font plain = new Font(null, Font.PLAIN, 18);
		
		// Create buttons
		addButton = new JButton("Add player");
		addButton.setFont(plain);
		addButton.setHorizontalTextPosition(AbstractButton.CENTER);
		addButton.setActionCommand("add");
		addButton.addActionListener(new AddPlayerListener(frame, gameEngine));
		add(addButton);
		
		betButton = new JButton("Place bet");
		betButton.setFont(plain);
		betButton.setHorizontalTextPosition(AbstractButton.CENTER);
		betButton.setActionCommand("bet");
		betButton.addActionListener(new BetPlayerListener(frame, gameEngine));
		betButton.setEnabled(false);; // Disabled on load since no players
		add(betButton);

		dealButton = new JButton("Deal player");
		dealButton.setFont(plain);
		dealButton.setHorizontalTextPosition(AbstractButton.CENTER);
		dealButton.setActionCommand("deal");
		dealButton.addActionListener(new DealPlayerListener(frame, gameEngine));
		dealButton.setEnabled(false);; // Disabled on load since no players
		add(dealButton);
		
		add(Box.createHorizontalGlue());
		setFloatable(false);
	}
	
	public void setDealEnabled(boolean enabled) {
		dealButton.setEnabled(enabled);
	}

	
	public void setBetEnabled(boolean enabled) {
		betButton.setEnabled(enabled);
	}
}
