package no.ntnu.fp.gui;

import java.awt.*;

import javax.swing.*;


public class LoginPanel extends JPanel {

	private JLabel text;
	private JLabel username;
	private JLabel password;
	private JLabel lock;
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	
	private JButton logInButton;
	
	private GridBagLayout gbl;
	private GridBagConstraints c;
	
	private ImageIcon icon;
	
	
	public LoginPanel() {
		gbl = new GridBagLayout();
		c = new GridBagConstraints();
		setLayout(gbl);
		
		lock = new JLabel();
		
		icon = new ImageIcon("Icons/lock.png");
	    lock.setIcon(icon);
		
		
		text = new JLabel("Tast inn brukernavn og passord");
		username = new JLabel("Brukernavn:");
		password = new JLabel("Passord:");
		
		usernameField = new JTextField();
		passwordField = new JPasswordField();
		
		logInButton = new JButton("Log inn");
		
		usernameField.setPreferredSize(new Dimension(150, 20));
		passwordField.setPreferredSize(new Dimension(150, 20));
		
		
		
		c.anchor = GridBagConstraints.WEST;
		c.ipady = 10;
		c.ipadx = 10;
		
		c.gridx = 0;
		c.gridy = 0;
		add(lock, c);
		
		c.gridx = 1;
		add(text, c);
		
		c.gridx = 0;
		c.gridy = 1;
		add(username, c);
		c.gridx = 1;
		add(usernameField, c);
		
		c.gridx = 0;
		c.gridy = 2;
		add(password, c);
		c.gridx = 1;
		add(passwordField, c);
		
		c.gridy = 3;
		add(logInButton, c);
		
		
		setBackground(Color.white);
		
		
	}
	
	
	public static void main(String[]args) {
		JFrame frame = new JFrame("Log inn");
		frame.setContentPane(new LoginPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	
}

