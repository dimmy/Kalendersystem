package no.ntnu.fp.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

public class AppointmentForm extends JPanel {

	private JLabel name;
	private JLabel place;
	private JLabel description;
	private JLabel timeFrom;
	private JLabel timeTo;
	private JLabel date;

	private JTextField nameField;
	private JTextField placeField;
	private JTextArea descriptionField;
	private JTextField timeFromField;
	private JTextField timeToField;
	private JTextField dateField;

	private JButton save;
	private JButton delete;

	private GridBagLayout gbl;
	private GridBagConstraints c;

	public AppointmentForm() {
		gbl = new GridBagLayout();
		c = new GridBagConstraints();
		setLayout(gbl);

		name = new JLabel("Navn:");
		place = new JLabel("Sted:");
		description = new JLabel("Beskrivelse:");
		timeTo = new JLabel("Tid fra");
		timeFrom = new JLabel("Tid til");
		date = new JLabel("Dato");

		nameField = new JTextField();
		placeField = new JTextField();
		descriptionField = new JTextArea();
		timeToField = new JTextField();
		timeFromField = new JTextField();
		dateField = new JTextField();

		save = new JButton("Lagre");
		delete = new JButton("Slett");


		nameField.setPreferredSize(new Dimension(200, 20));
		placeField.setPreferredSize(new Dimension(200, 20));
		dateField.setPreferredSize(new Dimension(100, 20));
		timeFromField.setPreferredSize(new Dimension(100, 20));
		timeToField.setPreferredSize(new Dimension(100, 20));
		descriptionField.setPreferredSize(new Dimension(190, 50));

		c.ipadx = 10;
		c.ipady = 10;
		c.anchor = GridBagConstraints.WEST;

		// Grid layout
		c.gridx = 0;
		c.gridy = 0;
		add(name, c);
		c.gridx = 1;
		add(nameField, c);

		c.gridx = 0;
		c.gridy = 1;
		add(place, c);
		c.gridx = 1;
		add(placeField, c);

		c.gridx = 0;
		c.gridy = 2;
		add(timeFrom, c);
		c.gridx = 1;
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
		add(delete, c);

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Min Avtale");
		frame.setContentPane(new AppointmentForm());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

}
