/**
 * @author Shreeniket Bendre
 * Project: Bank Account GUI
 * Class: AccoountFrame
 */

import java.util.ArrayList;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AccountFrame extends JFrame{
	
	private static ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
	
	public AccountFrame() {

		//================================================================================
		// Frame Setup
		//================================================================================

		this.setBounds(100,100,300,300);
		this.setResizable(false);
		this.setTitle("Ellis National Bank");
		this.getContentPane().setBackground(new Color(255,255,225)); //Wanted to add a background color
		this.getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK)); //Also wanted a border

		//================================================================================
		// JFrame Elements
		//================================================================================

		JLabel nameLbl = new JLabel ("Name:");
		JLabel checkBoxLbl = new JLabel ("Initial Deposit:"); 
		JLabel amtLbl = new JLabel ("Amount:");
		JLabel dropdownLbl = new JLabel ("Account Type: "); 

		JTextField nameField = new JTextField ();
		nameField.setPreferredSize(new Dimension(100, 20));

		JTextField amtField = new JTextField ();
		amtField.setPreferredSize(new Dimension(100, 20));
		amtField.setText("DISABLED");
		amtField.setEnabled(false);

		JCheckBox checkBox = new JCheckBox ();
		checkBox.setBackground(new Color(255,255,225));

		String[] comboString = { "Select", "Savings", "Checkings"};
		JComboBox dropdown = new JComboBox (comboString);
		dropdown.setSelectedIndex(0);
		dropdown.setPreferredSize(new Dimension(100, 20));

		JButton continueButton = new JButton("Continue");
		continueButton.setPreferredSize(new Dimension(100, 30));

		//================================================================================
		// Grid Bag Layout Setup
		//================================================================================

		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = gbc.WEST;

		gbc.gridx = 0;
		gbc.gridy = 0;
		add(nameLbl, gbc);

		gbc.gridx = 1;
		add(nameField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		add(checkBoxLbl, gbc);

		gbc.gridx = 1;
		add(checkBox, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		add(dropdownLbl, gbc);

		gbc.gridx = 1;
		add(dropdown, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		add(amtLbl, gbc);

		gbc.gridx = 1;
		add(amtField, gbc);
		
		// Credit to a stack overflow post on expanding objects to multiple cells
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.0;
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 10;
		add (continueButton, gbc);

		//================================================================================
		// Action Listeners
		//================================================================================
		checkBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (!checkBox.isSelected()) {
					amtField.setText("DISABLED");
					amtField.setEnabled(false);
				}
				else {
					amtField.setText("");
					amtField.setEnabled(true);
				}
			}

		});

		continueButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean noError = true;
				String name = nameField.getText();
				double initial = 0;
				int typeIndex = dropdown.getSelectedIndex();
				
				if (name.equals("")) {
					noError = false;
				}
				if (!(typeIndex==1||typeIndex==2)) {
					noError = false;
				}

				if (checkBox.isSelected()) {
					try {
						initial = Double.parseDouble(amtField.getText());
					}
					catch(Exception error) {
						noError = false;
					}
				}
				
				if(initial<0) {
					noError = false;
				}

				if (noError) {
					BankAccount account;
					if(typeIndex == 1) {
						account = new SavingsAccount(name, initial);
					}
					else {
						account = new CheckingAccount(name, initial);
					}
					accounts.add(account);
					
					checkBox.setSelected(false);
					nameField.setText("");
					amtField.setText("DISABLED");
					amtField.setEnabled(false);
					dropdown.setSelectedIndex(0);
					
				}

			}

		});

		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AccountFrame();
	}
}
