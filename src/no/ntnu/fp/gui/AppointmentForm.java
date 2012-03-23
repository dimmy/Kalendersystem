package no.ntnu.fp.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AppointmentForm extends JPanel {

	private JLabel name;
	private JLabel description;
	private JLabel timeFrom;
	private JLabel timeTo;
	private JLabel date;

	private JTextField nameField;
	private JTextArea descriptionField;
	private JTextField timeFromField;
	private JTextField timeToField;
	private JTextField dateField;

	private PlaceRoomChooserPanel place;
	
	private JButton save;
	private JButton cancel;

	private GridBagLayout gbl;
	private GridBagConstraints c;

	
	public AppointmentForm() {
		
		setBorder(new EmptyBorder(10, 10, 10, 10) );
		
		gbl = new GridBagLayout();
		c = new GridBagConstraints();
		setLayout(gbl);

		name = new JLabel("Name:");
		description = new JLabel("Description:");
		timeTo = new JLabel("To");
		timeFrom = new JLabel("From");
		date = new JLabel("Date");

		nameField = new JTextField();
		descriptionField = new JTextArea();
		timeToField = new JTextField();
		timeFromField = new JTextField();
		dateField = new JTextField();

		place = new PlaceRoomChooserPanel();
		
		save = new JButton("Save");
		
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		cancel = new JButton("Cancel");
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getParent().getParent().getParent().getParent().setVisible(false);
			}
		});


		nameField.setPreferredSize(new Dimension(200, 20));
		dateField.setPreferredSize(new Dimension(100, 20));
		timeFromField.setPreferredSize(new Dimension(100, 20));
		timeToField.setPreferredSize(new Dimension(100, 20));
		descriptionField.setPreferredSize(new Dimension(190, 50));


		c.ipadx = 10;
		c.ipady = 10;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;

		// Grid layout
		c.gridx = 0;
		c.gridy = 0;

		add(name, c);
		c.gridx = 1; c.gridy = 0;
		add(nameField, c);
		
		c.gridx = 0; c.gridy = 1; c.gridwidth = 2;
		add(place, c);
		
		c.gridx = 0; c.gridwidth = 1;
		c.gridy = 2;
		add(timeFrom, c);
		
		c.gridx = 1; c.gridy = 2;
		add(timeFromField, c);

		c.gridx = 0;
		c.gridy = 3;
		add(timeTo, c);
		c.gridx = 1;
		add(timeToField, c);

		c.gridx = 0;
		c.gridy = 4;
		add(date, c);
		c.gridx = 1;
		add(dateField, c);

		c.gridx = 0;
		c.gridy = 5;
		add(description, c);
		c.gridx = 1;
		add(descriptionField, c);

		c.ipadx = 0;
		c.ipady = 0;

		c.gridx = 0;
		c.gridy = 6;
		add(save, c);
		c.gridx = 1;
		add(cancel, c);
		

	}

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.add(new AppointmentForm());
		frame.setVisible(true);
		frame.pack();

	}

}
