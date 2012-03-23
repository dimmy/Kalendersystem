package no.ntnu.fp.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import no.ntnu.fp.model.Tools;
import no.ntnu.fp.storage.KalSysLogin;


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
	
	private KalSysLogin login;
	
	
	public LoginPanel() {
		
		setBorder(new EmptyBorder(10, 10, 10, 10) );
		
		login = new KalSysLogin();
		
		gbl = new GridBagLayout();
		c = new GridBagConstraints();
		setLayout(gbl);
		
		lock = new JLabel();
		
		icon = new ImageIcon("Icons/lock.png");
	    lock.setIcon(icon);
		
		
		text = new JLabel("Enter username and password");
		username = new JLabel("Username:");
		password = new JLabel("Password:");
		
		usernameField = new JTextField();
		passwordField = new JPasswordField();
		
		logInButton = new JButton("Log in");
		logInButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					if(login.authenticate(usernameField.getText(), Tools.SHAHash(new String(passwordField.getPassword())))){
						JFrame f =  new JFrame();
						f.setContentPane(new OverviewPanel());
						f.pack();
						f.setVisible(true);
					}
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		
		usernameField.setPreferredSize(new Dimension(150, 20));
		passwordField.setPreferredSize(new Dimension(150, 20));
		
		
		
		c.anchor = GridBagConstraints.WEST;
		c.ipady = 10;
		c.ipadx = 20;
		
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
		
		c.ipady = 0;
		c.ipadx = 0;
		c.gridy = 3;
		add(logInButton, c);
		
		revalidate();
		setBackground(Color.white);
		
		
	}
	
	
	public static void main(String[]args) {
		JFrame frame = new JFrame("Log in");
		frame.setContentPane(new LoginPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	
}

