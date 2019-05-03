package view;

import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/* 
 * NOTE:
 * I originally shared the same dialog class for both adding players and placing bets, however
 * there was more code created than code shared, therefore making it inefficient. So whilst
 * this doesn't currently promote code polymorphism, it is arguably more geared towards OO 
 * programming.
 *
 */

@SuppressWarnings("serial")
public class PlaceBetDialog extends JDialog {
	
	private JPanel panel;
	private JSpinner bet;
	
	private final double CURRENT = 0, MIN = 0, MAX = 100000, STEP = 1;
	
	public PlaceBetDialog() {		
		panel = new JPanel(new GridLayout(1,2));
		bet = new JSpinner(new SpinnerNumberModel(CURRENT,MIN,MAX,STEP));
		panel.add(new JLabel("Bet amount:"));
		panel.add(bet);
	}
	
	public boolean getResult() {
		return (JOptionPane.showConfirmDialog(null, panel, "Place bet", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) ? true : false;
	}
	
	public double getBet() {
		try {
			bet.commitEdit();
		}
		catch (java.text.ParseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Error" , JOptionPane.ERROR_MESSAGE);
		}
		
		return (double) bet.getValue();
	}
}
