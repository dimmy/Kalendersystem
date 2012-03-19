package no.ntnu.fp.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MeetingView extends JFrame{
	
	private JLabel nameLabel;
	private JTextField nameField;
	private JLabel placeLabel;
	private JTextField placeField;
	private JButton room;
	private JLabel timeLabel;
	private JLabel romLabel;
	private JLabel toLabel;
	private JTextField timeFromField;
	private JTextField timeToField;
	private JLabel dateLabel;
	private JTextField dateField;
	private JLabel descriptionLabel;
	private JTextArea descriptionField;
	private JLabel participantLabel;
	private JTextArea participantField;
	private JButton changeSave;
	private JButton addDelete;
	private JButton cancelMeeting;
	
	
	public static void main (String []args){
		MeetingView mmetingView = new MeetingView();
	}
	
	public String toString(){
		return "MeetingView";
	}
	public MeetingView(){
		
		JPanel panel = new JPanel();
		GridBagConstraints c = new GridBagConstraints();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7));

		//navn
		nameLabel = new JLabel("Navn:");
		c.gridwidth=1;
		c.ipadx=10; c.ipady=10;
		c.gridx=0; c.gridy=0;
		c.anchor = GridBagConstraints.WEST;
		panel.add(nameLabel, c);
		nameField = new JTextField();
		nameField.setColumns(20);
		c.gridwidth=4;
		c.ipadx=0; c.ipady=0;
		c.gridx=1; c.gridy=0;
		panel.add(nameField, c);
		
		//Sted
		placeLabel = new JLabel("Sted:");
		c.gridwidth=1;
		c.ipadx=10; c.ipady=10;
		c.gridx=0; c.gridy=1;
		c.anchor = GridBagConstraints.WEST;
		panel.add(placeLabel, c);
		placeField = new JTextField();
		placeField.setColumns(10);
		c.gridwidth=4;
		c.ipadx=0; c.ipady=0;
		c.gridx=1; c.gridy=1;
		panel.add(placeField, c);
		//room
		room = new JButton("Room");
		c.gridwidth=2;
		c.ipadx=0; c.ipady=0;
		c.gridx=4; c.gridy=1;
		panel.add(room, c);
		
		//Tid, fra-til
		timeLabel = new JLabel("Tid:");
		c.gridwidth=1;
		c.ipadx=10; c.ipady=10;
		c.gridx=0; c.gridy=2;
		c.anchor = GridBagConstraints.WEST;
		panel.add(timeLabel, c);
		romLabel = new JLabel("Fra:");
		c.gridwidth=1;
		c.ipadx=10; c.ipady=10;
		c.gridx=1; c.gridy=2;
		panel.add(romLabel, c);
		timeFromField = new JTextField();
		timeFromField.setColumns(6);
		c.gridwidth=1;
		c.ipadx=0; c.ipady=0;
		c.gridx=2; c.gridy=2;
		panel.add(timeFromField, c);
		
		toLabel = new JLabel("Til: ");
		c.gridx=3; c.gridy=2;
		c.ipadx=10; c.ipady=10;
		c.gridwidth=1;
		panel.add(toLabel, c);
		timeToField = new JTextField();
		timeToField.setColumns(6);
		c.gridwidth=1;
		c.ipadx=0; c.ipady=0;
		c.gridx=4; c.gridy=2;
		panel.add(timeToField, c);
		
		
		//Dato
		dateLabel = new JLabel("Dato:");
		c.gridwidth=1;
		c.ipadx=10; c.ipady=10;
		c.gridx=0; c.gridy=3;
		c.anchor = GridBagConstraints.WEST;
		panel.add(dateLabel, c);
		dateField = new JTextField();
		dateField.setColumns(6);
		c.gridwidth=2;
		c.ipadx=0; c.ipady=0;
		c.gridx=1; c.gridy=3;
		panel.add(dateField, c);
		
		//Beskrivelse
		descriptionLabel = new JLabel("Beskrivelse: ");
		c.gridwidth=1;
		c.ipadx=10; c.ipady=10;
		c.gridx=0; c.gridy=4;
		c.anchor = GridBagConstraints.WEST;
		panel.add(descriptionLabel, c);
		descriptionField = new JTextArea();
		descriptionField.setRows(3);
		descriptionField.setColumns(20);
		descriptionField.setWrapStyleWord(true);
		descriptionField.setLineWrap(true);
		JScrollPane beskrivelsescroll = new JScrollPane(descriptionField);
		c.gridwidth=4;
		c.gridx=1; c.gridy=4;
		c.anchor = GridBagConstraints.WEST;
		panel.add(beskrivelsescroll, c);

		//Deltagere
		participantLabel = new JLabel("Deltagere:");
		c.gridwidth=1;
		c.ipadx=10; c.ipady=10;
		c.gridx=0; c.gridy=5;
		c.anchor = GridBagConstraints.WEST;
		panel.add(participantLabel, c);
		participantField = new JTextArea();
		participantField.setRows(3);
		participantField.setColumns(20);
		participantField.setWrapStyleWord(true);
		participantField.setLineWrap(true);
		JScrollPane deltagerscroll = new JScrollPane(participantField);
		
		c.gridwidth=4;
		c.gridx=1; c.gridy=5;
		c.anchor = GridBagConstraints.WEST;
		panel.add(deltagerscroll, c);
		
		
		//Endre/Lagre
		changeSave = new JButton("Endre/Lagre");
		c.gridx=0; c.gridy=6;
		c.gridwidth=2;
		panel.add(changeSave, c);
		//Legge Til/Slette deltagere
		addDelete = new JButton("Deltagere");
		c.gridx=2; c.gridy=6;
		c.gridwidth=2;
		panel.add(addDelete, c);
		cancelMeeting = new JButton("Avlys");
		c.gridwidth=2;
		c.gridx=4; c.gridy=6;
		panel.add(cancelMeeting, c);
		
		add(panel);
		setVisible(true);
		pack();
	}
}
