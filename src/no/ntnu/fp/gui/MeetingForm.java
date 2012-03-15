package no.ntnu.fp.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 
 * @author Øyvind M
 *
 */
public class MeetingForm extends JFrame{
	
	private JPanel panel;
	
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
	
	private JButton addParticipant;
	
	private JButton saveMeeting;
	
	public MeetingForm (){
		
		panel = new JPanel();
		
		name = new JLabel("Navn: ");
		nameField = new JTextField("", 20);
		
		place = new JLabel("Sted: ");
		placeField = new JTextField("", 20);
		
		from = new JLabel("Fra: ");
		fromField = new JTextField("", 10);
		
		to = new JLabel("Til: ");
		toField = new JTextField("", 10);
		
		description = new JLabel("Beskrivelse:");
		descriptionArea = new JTextArea(4, 20);
		
		participants = new JLabel("Deltagere: ");
		participantsArea = new JTextArea(4, 20);
		
		addParticipant = new JButton("Legg til deltagere");
		
		saveMeeting = new JButton("Lagre");
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.WEST;
		c.ipadx = 10;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(name, c);
		
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 1;
		c.gridy = 0;
		panel.add(nameField, c);
		
		c.ipadx = 10;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 1;
		panel.add(place, c);
		
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 1;
		c.gridy = 1;
		panel.add(placeField, c);
		
		c.ipadx = 10;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 2;
		panel.add(from, c);
		
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 1;
		c.gridy = 2;
		panel.add(fromField, c);
		
		c.ipadx = 10;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 3;
		panel.add(to, c);
		
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 1;
		c.gridy = 3;
		panel.add(toField, c);
		
		c.ipadx = 10;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 4;
		panel.add(description, c);
		
		c.ipadx = 10;
		c.ipady = 10;
		c.gridx = 1;
		c.gridy = 4;
		panel.add(descriptionArea, c);
		
		c.ipadx = 10;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 5;
		panel.add(participants, c);
		
		c.ipadx = 10;
		c.ipady = 10;
		c.gridx = 1;
		c.gridy = 5;
		panel.add(participantsArea, c);
		
		
		c.gridx = 0;
		c.gridy = 6;
		panel.add(addParticipant, c);
		
		c.gridx = 1;
		c.gridy = 6;
		panel.add(saveMeeting, c);
		
		this.add(panel);
		
		
	}
	public static void main(String[] args) {
		MeetingForm form = new MeetingForm();
		form.pack();
		form.setVisible(true);
	}
	
	
	
}
