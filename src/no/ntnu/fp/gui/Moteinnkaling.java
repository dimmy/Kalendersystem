package no.ntnu.fp.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Moteinnkaling extends JPanel{
	
	private JLabel name;
	private JTextField nameField;
	
	private JLabel place;
	private JTextField placeField;
	
	private JLabel from;
	private JTextField fromField;
	
	private JLabel to;
	private JTextField toField;
	
	private JLabel description;
	private JTextArea descriptionArea;
	
	private JLabel participants;
	private JTextArea participantsArea;
	
	private JButton accept;
	private JButton decline; 
	
	
	public Moteinnkaling (){
	
		name = new JLabel("Navn: ");
		nameField = new JTextField("", 20);
		nameField.setEditable(false);
		
		place = new JLabel("Sted: ");
		placeField = new JTextField("", 20);
		placeField.setEditable(false);
		
		from = new JLabel("Fra: ");
		fromField = new JTextField("", 10);
		fromField.setEditable(false);
		
		to = new JLabel("Til: ");
		toField = new JTextField("", 10);
		toField.setEditable(false);
		
		description = new JLabel("Beskrivelse: ");
		descriptionArea = new JTextArea(4, 20);
		descriptionArea.setEditable(false);
		
		participants = new JLabel("Deltagere: ");
		participantsArea = new JTextArea(4, 20);
		participantsArea.setEditable(false);
		
		accept = new JButton("Godta");
		decline = new JButton("Avslå"); 
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.WEST;
		c.ipadx = 10;
		c.ipady = 10;
		
		c.gridx = 0;
		c.gridy = 0;
		this.add(name, c);
		

		c.gridx = 0;
		c.gridy = 1;
		this.add(from, c);
		
		c.gridx = 0;
		c.gridy = 2;
		this.add(from, c);
		
		c.gridx = 0;
		c.gridy = 3;
		this.add(to, c);
		
		c.gridx = 0;
		c.gridy = 4;
		this.add(description, c);
		
		c.gridx = 0;
		c.gridy = 5;
		this.add(participants, c);
		
		
		c.ipadx = 0;
		c.ipady = 0;
		
		c.gridx = 1;
		c.gridy = 0;
		this.add(nameField, c);
		
		c.gridx = 1;
		c.gridy = 1;
		this.add(placeField, c);
		
		c.gridx = 1;
		c.gridy = 2;
		this.add(toField, c);
		
		c.gridx = 1;
		c.gridy = 3;
		this.add(fromField, c);
		
		c.gridx = 1;
		c.gridy = 4;
		this.add(descriptionArea, c);
		
		c.gridx = 1;
		c.gridy = 5;
		this.add(participantsArea, c);
		
		c.gridx = 0;
		c.gridy = 6;
		this.add(decline, c);
		
		c.gridx = 1;
		c.gridy = 6;
		this.add(accept, c);
		
		
		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Moteinnkaling());
		frame.setVisible(true);
		frame.pack();
		
	}
	
	
	
}
