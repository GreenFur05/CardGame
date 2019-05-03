package view;

import java.awt.Color;
import java.awt.Dimension;


import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import controller.PlayerListListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class PlayerList extends JPanel {
	
	private JList<String> list;
	private DefaultListModel<String> listModel;
	private final int SELECTED_INDEX = 0;
	private boolean houseActive = false;
	private int houseIndex;
	
	public PlayerList(GameFrame frame, GameEngine gameEngine) {		
		
		// Setting up main panel
		setBorder(BorderFactory.createLineBorder(Color.black));
		setPreferredSize(new Dimension(frame.getWidth() / 4, getSize().height));
		
		// Create list
		listModel = new DefaultListModel<String>();
		list = new JList<String>(listModel);
		list.setFont(frame.getPlainFont());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(SELECTED_INDEX);
        list.setPreferredSize(new Dimension((frame.getWidth() / 4) - 2, getSize().height));

		JScrollPane listScrollPane = new JScrollPane(list);
		
		// Add action listener
        list.addListSelectionListener(new PlayerListListener(frame, gameEngine));
        
        // Finish set-up
        add(listScrollPane);
	}
	
	public Player getSelectedPlayer(GameEngine gameEngine) {
		String selected = list.getSelectedValue();
		int index = selected.lastIndexOf("(");
		String id = selected.substring(index + 1, selected.length() - 1);	
		
		return gameEngine.getPlayer(id);
	}
	
	public void addPlayer(String id, String name) {
		listModel.addElement(name + " (" + id + ")");
	}
	
	public int getSelectedIndex() {
		return list.getSelectedIndex();
	}
	
	public DefaultListModel<String> getlistModel() {
		return listModel;
	}
	
	public void addHouse() {
		listModel.addElement("House");
		houseActive = true;
		houseIndex = listModel.getSize() - 1;
	}
	
	public void removeHouse() {
		listModel.removeElementAt(houseIndex);
		houseActive = false;
	}
	
	public boolean getHouseActive() {
		return houseActive;
	}
	
	public int getHouseIndex() {
		return houseIndex;
	}
	
	public void setSelectedIndex(int index) {
		list.setSelectedIndex(index);
	}
}
