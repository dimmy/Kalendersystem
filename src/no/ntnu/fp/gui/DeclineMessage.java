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

public class DeclineMessage extends JPanel{
	
	private JButton changeMeeting;
	
	private JLabel description;
	private JTextArea descriptionArea;
	private JScrollPane descriptionScroll;
	
	private JLabel time;
	
	private JLabel name;
	private JTextField nameField;
	
	private JLabel from;
	private JTextField fromField;
	
	private JLabel to;
	private JTextField toField;
	
	
	public DeclineMessage(){
		
		changeMeeting = new JButton();
		
		description = new JLabel("Beskrivelse: ");
		descriptionArea = new JTextArea(4, 30);
		descriptionArea.setWrapStyleWord(true);
		descriptionArea.setLineWrap(true);
		descriptionScroll = new JScrollPane(descriptionArea);
		
		time = new JLabel("Tid: ");
		
		name = new JLabel("Navn");
		nameField = new JTextField("", 30);
		
		from = new JLabel("Fra: ");
		fromField = new JTextField("", 10);
		
		to = new JLabel("Til: ");
		toField = new JTextField("", 10);
		
		changeMeeting = new JButton("Endre møtet");

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.WEST;
		c.ipadx = 10;
		c.ipady = 10;
		
		c.gridx = 0;
		c.gridy = 0;
		this.add(description, c);
		
		c.gridx = 0;
		c.gridy = 1;
		this.add(name, c);
		
		c.gridx = 0;
		c.gridy = 2;
		this.add(time, c);
		
		c.gridx = 0;
		c.gridy = 3;
		this.add(to, c);
		
		c.ipadx = 0;
		c.ipady = 0;
		
		c.gridwidth = 4;
		c.gridx = 1;
		c.gridy = 0;
		this.add(descriptionScroll, c);
		
		c.gridx = 1;
		c.gridy = 1;
		this.add(nameField, c);
		
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 2;
		this.add(from, c);
		
		c.gridx = 2;
		c.gridy = 2;
		this.add(fromField, c);
		
		c.gridx = 3;
		c.gridy = 2;
		this.add(to, c);
		
		c.gridx = 4;
		c.gridy = 2;
		this.add(toField, c);
		
		c.gridx = 0;
		c.gridy = 4;
		this.add(changeMeeting, c);
		
		
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new DeclineMessage());
		frame.setVisible(true);
		frame.pack();
			
	}
}
