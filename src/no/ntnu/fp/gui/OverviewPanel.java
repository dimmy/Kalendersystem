package no.ntnu.fp.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

/**
 * Panel for main overview window
 * 
 * @author David M
 * 
 */

public class OverviewPanel extends JPanel {

	JButton btnNewAppointment;
	JButton btnAddCalendar;
	JButton btnInbox;
	JButton btnLogOut;

	JPanel panLeftButtonPanel;

	JLabel lblWeekDisplay;
	JLabel lblLoginInfo;
	JPanel panTitle;

	CalendarView calendarView;

	public OverviewPanel() {
		btnNewAppointment = new JButton("Ny avtale");
		btnAddCalendar = new JButton("Add calendar");
		btnInbox = new JButton("Inbox (?)");
		btnLogOut = new JButton("Log out");

		// Left side button panel

		panLeftButtonPanel = new JPanel();
		panLeftButtonPanel.setLayout(new BoxLayout(panLeftButtonPanel,
				BoxLayout.Y_AXIS));

		panLeftButtonPanel.add(btnNewAppointment);
		panLeftButtonPanel.add(btnAddCalendar);
		panLeftButtonPanel.add(btnInbox);
		panLeftButtonPanel.add(btnLogOut);

		// Title

		lblWeekDisplay = new JLabel("<html><h1>Uke ?</h1></html>");
		lblLoginInfo = new JLabel("Logget inn som: <brukernavn>");
		lblLoginInfo.setHorizontalTextPosition(JLabel.RIGHT);
		
		panTitle = new JPanel();
		panTitle.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		panTitle.add(lblLoginInfo, c);
		c.gridy = 1;
		panTitle.add(lblWeekDisplay, c);

		panTitle.setBackground(new Color(255, 128, 128)); // color for testing

		// Calendar view

		calendarView = new CalendarView();
		calendarView.setBackground(new Color(128, 255, 128));

		// Combine!

		SpringLayout spring = new SpringLayout();

		setLayout(spring);

		add(panLeftButtonPanel);
		add(calendarView);
		add(panTitle);

		spring.putConstraint(SpringLayout.NORTH, panTitle, 0,
				SpringLayout.NORTH, this);
		// PS: This line sets the height of the title panel
		spring.putConstraint(SpringLayout.SOUTH, panTitle, 60,
				SpringLayout.NORTH, this);
		spring.putConstraint(SpringLayout.WEST, panTitle, 0, SpringLayout.WEST,
				this);
		spring.putConstraint(SpringLayout.EAST, panTitle, 0, SpringLayout.EAST,
				this);

		spring.putConstraint(SpringLayout.NORTH, panLeftButtonPanel, 0,
				SpringLayout.SOUTH, panTitle);
		spring.putConstraint(SpringLayout.SOUTH, panLeftButtonPanel, 0,
				SpringLayout.SOUTH, this);
		// PS: This line sets the width of the button panel
		spring.putConstraint(SpringLayout.EAST, panLeftButtonPanel, 200,
				SpringLayout.WEST, this);
		spring.putConstraint(SpringLayout.WEST, panLeftButtonPanel, 0,
				SpringLayout.WEST, this);

		spring.putConstraint(SpringLayout.WEST, calendarView, 5,
				SpringLayout.EAST, panLeftButtonPanel);
		spring.putConstraint(SpringLayout.NORTH, calendarView, 5,
				SpringLayout.SOUTH, panTitle);
		spring.putConstraint(SpringLayout.EAST, calendarView, -5,
				SpringLayout.EAST, this);
		spring.putConstraint(SpringLayout.SOUTH, calendarView, -5,
				SpringLayout.SOUTH, this);

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new OverviewPanel());
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
	}
}
