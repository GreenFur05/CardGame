package view;

import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;


@SuppressWarnings("serial")
public class AddPlayerDialog extends JDialog {
	
	private JPanel panel;
	private TextField playerName;
	private JSpinner points;
	
	private final double CURRENT = 0, MIN = 0, MAX = 100000, STEP = 1;
	
	public AddPlayerDialog() {		
		panel = new JPanel(new GridLayout(2,2));
		playerName = new TextField(10);
		points = new JSpinner(new SpinnerNumberModel(CURRENT,MIN,MAX,STEP));
		panel.add(new JLabel("Player name:"));
		panel.add(playerName);
		panel.add(new JLabel("Initial points:"));
		panel.add(points);
		
	}
	
	public boolean getResult() {
		return (JOptionPane.showConfirmDialog(null, panel, "Add player", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) ? true : false;
	}
	
	public String getPlayerName() {
		return playerName.getText();
	}
	
	public double getPlayerPoints() {
		try {
			points.commitEdit();
		}
		catch (java.text.ParseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Error" , JOptionPane.ERROR_MESSAGE);
		}
		
		return (double) points.getValue();
	}
}
