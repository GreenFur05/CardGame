package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.Toolkit;

import javax.swing.JFrame;

import model.interfaces.GameEngine;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	
	private MenuBar menu;
	private StatusBar status;
	private ToolBar tool;
	private PlayerList list;
	private PlayerPanel player;
	private SummaryPanel summary;
	
	private int width, height;
	
	public GameFrame(GameEngine gameEngine) {
		
		// Set-up frame
		super("Card Game");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize.width / 2;
		height = screenSize.height / 2;
		
		
		// Create components
		menu = new MenuBar();
		status = new StatusBar(gameEngine);
		tool = new ToolBar(this, gameEngine);
		list = new PlayerList(this, gameEngine);
		summary = new SummaryPanel(this, gameEngine);
		player = new PlayerPanel(this, gameEngine);
	
		
		// Add components
		setJMenuBar(menu);
		add(tool, BorderLayout.NORTH);
		add(list,BorderLayout.WEST);
		add(player, BorderLayout.CENTER);
		add(summary, BorderLayout.EAST);
		add(status, BorderLayout.SOUTH);
		
		// Finish setting up frame
		pack();
		setMinimumSize(new Dimension(width, height));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public PlayerList getPlayerList() {
		return list;
	}
	
	public SummaryPanel getSummaryPanel() {
		return summary;
	}
	
	public ToolBar getToolbar() {
		return tool;
	}
	
	public PlayerPanel getPlayerPanel() {
		return player;
	}
	
	public StatusBar getStatusBar() {
		return status;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Font getBoldFont() {
		return new Font(null, Font.BOLD, 18);
	}
	
	public Font getPlainFont() {
		return new Font(null, Font.PLAIN, 18);
	}
	
}
