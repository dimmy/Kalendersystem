package no.ntnu.fp.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MeetingSummon extends JFrame{
	
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
	private JScrollPane descriptionScroll;
	
	private JLabel participants;
	private JTextArea participantsArea;
	private JScrollPane participantsScroll;
	
	private JButton accept;
	private JButton decline; 
	
	private JPanel panel;
	
	public MeetingSummon (){
	
		panel = new JPanel();
		
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
		descriptionArea.setWrapStyleWord(true);
		descriptionArea.setLineWrap(true);
		descriptionScroll = new JScrollPane(descriptionArea);
		
		participants = new JLabel("Deltagere: ");
		participantsArea = new JTextArea(4, 20);
		participantsArea.setEditable(false);
		participantsArea.setWrapStyleWord(true);
		participantsArea.setLineWrap(true);
		participantsScroll = new JScrollPane(participantsArea);
		
		accept = new JButton("Godta");
		decline = new JButton("Avslå"); 
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.WEST;
		c.ipadx = 10;
		c.ipady = 10;
		
		c.gridx = 0;
		c.gridy = 0;
		panel.add(name, c);
		
		c.gridx = 0;
		c.gridy = 1;
		panel.add(from, c);
		
		c.gridx = 0;
		c.gridy = 2;
		panel.add(from, c);
		
		c.gridx = 0;
		c.gridy = 3;
		panel.add(to, c);
		
		c.gridx = 0;
		c.gridy = 4;
		panel.add(description, c);
		
		c.gridx = 0;
		c.gridy = 5;
		panel.add(participants, c);
		
		c.ipadx = 0;
		c.ipady = 0;
		
		c.gridx = 1;
		c.gridy = 0;
		panel.add(nameField, c);
		
		c.gridx = 1;
		c.gridy = 1;
		panel.add(placeField, c);
		
		c.gridx = 1;
		c.gridy = 2;
		panel.add(toField, c);
		
		c.gridx = 1;
		c.gridy = 3;
		panel.add(fromField, c);
		
		c.gridx = 1;
		c.gridy = 4;
		panel.add(descriptionScroll, c);
		
		c.gridx = 1;
		c.gridy = 5;
		panel.add(participantsScroll, c);
		
		c.gridx = 0;
		c.gridy = 6;
		panel.add(decline, c);
		
		c.gridx = 1;
		c.gridy = 6;
		panel.add(accept, c);
		
		add(panel);
		setVisible(true);
		pack();
		
		
	}
	
	public static void main(String[] args) {
		MeetingSummon meetingSummon = new MeetingSummon();
	}
	
	
	
}
