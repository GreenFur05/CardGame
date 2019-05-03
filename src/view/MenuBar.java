package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.MenuBarListener;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	private JMenu game;
	private JMenuItem exit;
	
	public MenuBar() {
		game = new JMenu("Game");
		exit = new JMenuItem("Exit");
		exit.addActionListener(new MenuBarListener());
		
		this.add(game);
		game.add(exit);
		
	}
	
}
