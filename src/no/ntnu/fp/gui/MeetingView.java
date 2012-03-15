package no.ntnu.fp.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class MeetingView extends JPanel{
	
	private JLabel placeLabel;
	private JTextField placeField;
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
		JFrame appointmentview = new JFrame("Personalmøte");
		appointmentview.setSize(350,350);
//		appointmentview.pack();
		appointmentview.getContentPane().add(new MeetingView());
		appointmentview.setLocationRelativeTo(null);
		appointmentview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appointmentview.setVisible(true);
	}
	public MeetingView(){
		
		GridBagConstraints c = new GridBagConstraints();
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7));

		//Sted
		placeLabel = new JLabel("Sted:");
		c.gridwidth=1;
		c.ipadx=10; c.ipady=10;
		c.gridx=0; c.gridy=0;
		c.anchor = GridBagConstraints.WEST;
		add(placeLabel, c);
		placeField = new JTextField();
		placeField.setColumns(20);
		c.gridwidth=4;
		c.ipadx=0; c.ipady=0;
		c.gridx=1; c.gridy=0;
		add(placeField, c);
		
		//Tid, fra-til
		timeLabel = new JLabel("Tid:");
		c.gridwidth=1;
		c.ipadx=10; c.ipady=10;
		c.gridx=0; c.gridy=1;
		c.anchor = GridBagConstraints.WEST;
		add(timeLabel, c);
		romLabel = new JLabel("Fra:");
		c.gridwidth=1;
		c.ipadx=10; c.ipady=10;
		c.gridx=1; c.gridy=1;
		add(romLabel, c);
		timeFromField = new JTextField();
		timeFromField.setColumns(6);
		c.gridwidth=1;
		c.ipadx=0; c.ipady=0;
		c.gridx=2; c.gridy=1;
		add(timeFromField, c);
		
		toLabel = new JLabel("Til: ");
		c.gridx=3; c.gridy=1;
		c.ipadx=10; c.ipady=10;
		c.gridwidth=1;
		add(toLabel, c);
		timeToField = new JTextField();
		timeToField.setColumns(6);
		c.gridwidth=1;
		c.ipadx=0; c.ipady=0;
		c.gridx=4; c.gridy=1;
		add(timeToField, c);
		
		
		//Dato
		dateLabel = new JLabel("Dato:");
		c.gridwidth=1;
		c.ipadx=10; c.ipady=10;
		c.gridx=0; c.gridy=2;
		c.anchor = GridBagConstraints.WEST;
		add(dateLabel, c);
		dateField = new JTextField();
		dateField.setColumns(6);
		c.gridwidth=2;
		c.ipadx=0; c.ipady=0;
		c.gridx=1; c.gridy=2;
		add(dateField, c);
		
		//Beskrivelse
		descriptionLabel = new JLabel("Beskrivelse: ");
		c.gridwidth=1;
		c.ipadx=10; c.ipady=10;
		c.gridx=0; c.gridy=3;
		c.anchor = GridBagConstraints.WEST;
		add(descriptionLabel, c);
		descriptionField = new JTextArea();
		descriptionField.setRows(3);
		descriptionField.setColumns(20);
		descriptionField.setWrapStyleWord(true);
		descriptionField.setLineWrap(true);
		JScrollPane beskrivelsescroll = new JScrollPane(descriptionField);
		
		c.gridwidth=4;
		c.gridx=1; c.gridy=3;
		c.anchor = GridBagConstraints.WEST;
		add(beskrivelsescroll, c);

		//Deltagere
		participantLabel = new JLabel("Deltagere:");
		c.gridwidth=1;
		c.ipadx=10; c.ipady=10;
		c.gridx=0; c.gridy=4;
		c.anchor = GridBagConstraints.WEST;
		add(participantLabel, c);
		participantField = new JTextArea();
		participantField.setRows(3);
		participantField.setColumns(20);
		participantField.setWrapStyleWord(true);
		participantField.setLineWrap(true);
		JScrollPane deltagerscroll = new JScrollPane(participantField);
		
		c.gridwidth=4;
		c.gridx=1; c.gridy=4;
		c.anchor = GridBagConstraints.WEST;
		add(deltagerscroll, c);
		
		
		//Endre/Lagre
		changeSave = new JButton("Endre/Lagre");
		c.gridx=0; c.gridy=5;
		c.gridwidth=2;
		add(changeSave, c);
		//Legge Til/Slette deltagere
		addDelete = new JButton("Deltagere");
		c.gridx=2; c.gridy=5;
		c.gridwidth=2;
		add(addDelete, c);
		cancelMeeting = new JButton("Avlys");
		c.gridwidth=2;
		c.gridx=4; c.gridy=5;
		add(cancelMeeting, c);
		
	}
}
