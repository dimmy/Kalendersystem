package no.ntnu.fp.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author Øyvind M
 *
 */
public class MeetingForm extends JPanel{
	
	private JLabel name;
	private JTextField nameField;
	
	private JPanel place;
	
	private JLabel time;
	
	private JLabel from;
	private JTextField fromField;
	
	private JLabel to;
	private JTextField toField;
	
	private JLabel date;
	private JTextField dateField;
	
	private JLabel description;
	private JTextArea descriptionArea;
	private JScrollPane descriptionScroll;
	
	private JLabel participants;
	private JTextArea participantsArea;
	private JScrollPane participantScroll;
	
	private JButton addParticipant;
	private JButton saveMeeting;
	
	public MeetingForm (){
		
		setBorder(new EmptyBorder(10, 10, 10, 10) );
		
		name = new JLabel("Navn: ");
		nameField = new JTextField("", 20);
		
		place = new PlaceRoomChooserPanel();
		
		time = new JLabel("Tid: ");
		
		from = new JLabel("Fra: ");
		fromField = new JTextField("", 6);
		
		to = new JLabel("Til: ");
		toField = new JTextField("", 6);
		
		date = new JLabel("Dato:");
		dateField = new JTextField("", 10);
		
		description = new JLabel("Beskrivelse:");
		descriptionArea = new JTextArea();
		descriptionScroll = new JScrollPane(descriptionArea);
		descriptionScroll.setPreferredSize(new Dimension(200,100));
		descriptionArea.setWrapStyleWord(true);
		descriptionArea.setLineWrap(true);
		
		participants = new JLabel("Deltagere: ");
		participantsArea = new JTextArea();
		participantScroll = new JScrollPane(participantsArea);
		participantScroll.setPreferredSize(new Dimension(200,100));
		participantsArea.setWrapStyleWord(true);
		participantsArea.setLineWrap(true);
		
		
		addParticipant = new JButton("Legg til deltagere");
		addParticipant.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame addparticipantframe = new JFrame();
				addparticipantframe.add(new ChooseParticipantPanel());
				addparticipantframe.setVisible(true);
				addparticipantframe.pack();
				
			}
		});
		
		
		saveMeeting = new JButton("Lagre");
		
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		c.anchor = GridBagConstraints.WEST;
		c.gridwidth = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(name, c);
		
		c.gridwidth = 5;
		c.ipadx = 10;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(place, c);
		
		c.gridwidth = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		add(time, c);
		
		c.gridwidth = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		add(from, c);
		
		c.gridwidth = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 3;
		c.gridy = 2;
		add(to, c);
		
		c.gridwidth = 1;
		c.ipadx = 0;
		c.ipady = 20;
		c.gridx = 0;
		c.gridy = 3;
		add(date, c);
		
		c.gridwidth = 1;
		c.ipadx = 10;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 4;
		add(description, c);
		
		c.gridwidth = 4;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 1;
		c.gridy = 4;
		add(descriptionScroll, c);
		
		c.gridwidth = 1;
		c.ipadx = 35;
		c.ipady = 100;
		c.gridx = 0;
		c.gridy = 5;
		add(participants, c);
		
		c.gridwidth = 4;
		c.ipadx = 10;
		c.ipady = 0;
		c.gridx = 1;
		c.gridy = 5;
		add(participantScroll, c);
		
		c.gridwidth = 4;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 1;
		c.gridy = 0;
		add(nameField, c);		
		
		c.gridwidth = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 2;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		add(fromField, c);
		
		c.gridwidth = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 4;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		add(toField, c);
		
		c.gridwidth = 4;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 1;
		c.gridy = 3;
		add(dateField, c);
		
		c.gridwidth = 2;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 2;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.EAST;
		add(addParticipant, c);
		
		c.gridwidth = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 4;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.EAST;
		add(saveMeeting, c);
		
		
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new MeetingForm());
		frame.setVisible(true);
		frame.pack();
		
	}
	
	
	
}
