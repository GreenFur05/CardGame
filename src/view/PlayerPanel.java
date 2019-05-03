package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

@SuppressWarnings("serial")
public class PlayerPanel extends JSplitPane {
	
	BufferedImage pic;
	
	private JLabel cardLabel;
	private JPanel detailsPanel;
	
	private JPanel panelName, panelScore, panelBet, panelPoints;
	private JLabel name, score, bet, points, playerName, playerScore, playerBet, playerPoints;
		
	private final String PREFIX = "img/";
	private final String SUFFIX = ".png";
	
	private String[] fileNames = new String[1];
	
	private int houseCard;

	public PlayerPanel(GameFrame frame, GameEngine gameEngine) {
				
		//Initialise panel for details
		detailsPanel = new JPanel();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = screenSize.width / 8;
		int y = (int) (x*1.4);
		
		cardLabel = new JLabel(new ImageIcon());
		cardLabel.setMinimumSize(new Dimension(x,y));
		
		Font plain = frame.getPlainFont();
		Font bold = frame.getBoldFont();
		
		name = new JLabel("Player: ");
		playerName = new JLabel("-");
		name.setFont(plain);
		playerName.setFont(bold);
		panelName = new JPanel();
		panelName.add(name);
		panelName.add(playerName);
		
		score = new JLabel("Score: ");
		playerScore = new JLabel("-");
		score.setFont(plain);
		playerScore.setFont(bold);
		panelScore = new JPanel();
		panelScore.add(score);
		panelScore.add(playerScore);
		
		bet = new JLabel("Bet: ");
		playerBet = new JLabel("-");
		bet.setFont(plain);
		playerBet.setFont(bold);
		panelBet = new JPanel();
		panelBet.add(bet);
		panelBet.add(playerBet);
		
		points = new JLabel("Points: ");
		playerPoints = new JLabel("-");
		points.setFont(plain);
		playerPoints.setFont(bold);
		panelPoints = new JPanel();
		panelPoints.add(points);
		panelPoints.add(playerPoints);
		
		
		// Finish set-up
        setBorder(BorderFactory.createLineBorder(Color.black));
		detailsPanel.add(panelName);
		detailsPanel.add(panelScore);
		detailsPanel.add(panelBet);
		detailsPanel.add(panelPoints);
		
		setOrientation(JSplitPane.VERTICAL_SPLIT);
		setTopComponent(cardLabel);
		setBottomComponent(detailsPanel);
	}
	
	public void updateDetails(GameFrame frame, GameEngine gameEngine) {
		
		// Update state of card specific to player
		try {
			pic = ImageIO.read(new File(fileNames[frame.getPlayerList().getSelectedIndex()]));
		}
		catch (NullPointerException np) {
			try {
				pic = ImageIO.read(new File(PREFIX + "back.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = screenSize.width / 8;
		int y = (int) (x*1.4);
		Image img = pic.getScaledInstance(x, y, Image.SCALE_SMOOTH);

		cardLabel.setIcon(new ImageIcon(img));
		
		
		if (frame.getPlayerList().getHouseActive()) {
			if (frame.getPlayerList().getSelectedIndex() != frame.getPlayerList().getHouseIndex()) {
				updatePlayerDetails(frame, gameEngine);
			}
		}
		else {
			updatePlayerDetails(frame, gameEngine);
		}
		
		
		this.repaint();
	}
	
	public void updatePlayerDetails(GameFrame frame, GameEngine gameEngine) {
		// Update details specific to player
		Player player = frame.getPlayerList().getSelectedPlayer(gameEngine);
		playerName.setText(player.getPlayerName());
		playerScore.setText(String.valueOf(player.getResult()));
		playerBet.setText(String.valueOf(player.getBet()));
		playerPoints.setText(String.valueOf(player.getPoints()));
	}
	
	public void updateHouse(GameFrame frame, GameEngine gameEngine) {
		updateDetails(frame, gameEngine);
		playerName.setText("House");
		playerScore.setText(String.valueOf(houseCard));
		playerBet.setText("-");
		playerPoints.setText("-");
	}
	
	public void newCard(Player player, PlayingCard card) {		
		/*
		 * The following declarations stores the filename of the last dealt card. Since my implementation does not
		 * include a ViewModel, this is the simplest method of storing state in the view.
		 */
		fileNames[Integer.parseInt(player.getPlayerId())] = PREFIX + card.getValue().toString() + card.getSuit().toString() + SUFFIX;
	}
	
	public void newPlayer() {
		fileNames = Arrays.copyOf(fileNames,fileNames.length + 1);
	}
	
	public void newHouseCard(PlayingCard card) {
		/*
		 * The following declaration stores the filename of the last dealt card. Since my implementation does not
		 * include a ViewModel, this is the simplest method of storing state in the view.
		 */
		fileNames[fileNames.length - 1] = PREFIX + card.getValue().toString() + card.getSuit().toString() + SUFFIX;
		
		this.houseCard = card.getScore();
	}
	

}
