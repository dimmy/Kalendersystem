package no.ntnu.fp.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PlaceRoomChooserPanel extends JPanel{

	/*
	 * @author: Fredrik
	 * 
	 */
	
	JTextField placeInput;
	JLabel placeLabel;
	JButton roomButton;
	
	JPanel roomPanel;
		JList roomList;
		JScrollPane roomScrollPane;
		JLabel roomLabel;
		JButton roomCancelButton;
	
	JButton saveButton;
	
	public PlaceRoomChooserPanel()	
	{
		
		// layout and constraints
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		/**
		 * The label and input box for the spesific place, + the button for choosing a room instead.
		 */
		
		// Label
		placeLabel = new JLabel("Sted:");
		c.gridx=0; c.gridy=0; c.ipadx=5;c.weightx=1;
		add(placeLabel, c);
		
		// Input
		placeInput = new JTextField(15);
		
		c.gridx=0; c.gridy=1; c.ipadx=5;c.weightx=2;
		add(placeInput, c);
		
		
		// Button
		roomButton = new JButton("Velg rom istedet");
		roomButton.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				placeInput.setEditable(false);
				roomPanel.setVisible(true);
				
				
			}
		});
		
		c.gridx=0; c.gridy=2; c.ipadx=5; c.weightx=1;
		add(roomButton, c);
		
		
		
		/**
		 * JPanel that contains the JList and the Jlabel for the Room chooser.
		 * 
		 */
		
		roomPanel = new JPanel();
		GridBagLayout roomLayout = new GridBagLayout();
		GridBagConstraints roomC = new GridBagConstraints();
		roomPanel.setLayout(roomLayout);
		roomPanel.setVisible(false);
		roomPanel.setBackground(new Color(0,0,0,64));
		this.setSize(new Dimension(this.getWidth(), (this.getHeight()+250)));
		// add the panel to the application 
		c.gridx=0; c.gridy=1; c.ipadx=5; c.weightx=3;
		add(roomPanel, c);
		
			// Label in the RoomPanel
			roomLabel = new JLabel("Velg et rom");
			
			roomC.gridx=0; roomC.gridy=0; roomC.ipadx=5;
			roomPanel.add(roomLabel, roomC);
			
			// List in the RoomPanel
			roomList = new JList();
			roomScrollPane = new JScrollPane(roomList);
			
			roomC.gridx=0; roomC.gridy=1; roomC.ipadx=5;
			roomPanel.add(roomScrollPane, roomC);
			
			// List in the RoomPanel
			roomCancelButton = new JButton("Avbryt");
			roomCancelButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					roomPanel.setVisible(false);
					placeInput.setEditable(true);
					
				}
			});
						
			roomC.gridx=0; roomC.gridy=2; roomC.ipadx=5; roomC.anchor =  GridBagConstraints.EAST;
			roomPanel.add(roomCancelButton, roomC);
			
		
		
		c.gridx=0; c.gridy=1; c.ipadx=5; c.weightx=3;
		
		
		
		
		
	}
	
	public static void main(String[] args)
	{
		JFrame jframe = new JFrame("Test");
		jframe.setSize(400,400);
		JPanel ppanel = new PlaceRoomChooserPanel();
		jframe.getContentPane().add(ppanel);
		jframe.pack();
		jframe.setVisible(true);
	}
	
	
}
