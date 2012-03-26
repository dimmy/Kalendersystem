package no.ntnu.fp.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import no.ntnu.fp.model.ClientSession;
import no.ntnu.fp.model.Tools;
import no.ntnu.fp.model.User;
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

	List<ActionListener> closeListeners;

	public LoginPanel() {

		setBorder(new EmptyBorder(10, 10, 10, 10));

		login = new KalSysLogin();

		gbl = new GridBagLayout();
		c = new GridBagConstraints();
		setLayout(gbl);

		lock = new JLabel();

		icon = new ImageIcon("Icons/lock.png");
		lock.setIcon(icon);

		closeListeners = new ArrayList<ActionListener>();

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
					String uname = usernameField.getText();
					char[] pwd = passwordField.getPassword();

					if (login.authenticate(uname,
							Tools.SHAHash(new String(pwd)))) {
						User user = new User();
						user.setUsername(uname);
						user.setName("(" + uname + ")");
						ClientSession session = new ClientSession(user);
						OverviewPanel.openWindow(session);

						fireCloseAction();
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

	private void addCloseActionListener(ActionListener al) {
		closeListeners.add(al);
	}

	private void fireCloseAction() {
		for (ActionListener al : closeListeners) {
			al.actionPerformed(new ActionEvent(this, 0, "close"));
		}
	}

	public static void main(String[] args) {
		final JFrame frame = new JFrame("Log in");
		LoginPanel lp = new LoginPanel();
		frame.setContentPane(lp);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		lp.addCloseActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}

		});
	}
}
