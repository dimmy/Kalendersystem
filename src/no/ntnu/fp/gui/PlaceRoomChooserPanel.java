package no.ntnu.fp.gui;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PlaceRoomChooserPanel extends JFrame implements ListSelectionListener, ActionListener{

	/*
	 * @author: Fredrik
	 * 
	 */
	
	JList roomList;
	JScrollPane roomScrollPane;
	JLabel roomLabel;
	JButton roomCancelButton;
	JButton roomSaveButton;
	
	public PlaceRoomChooserPanel()
	{
		this.setName("Choose a Room");
		
		JPanel ppanel = new JPanel();
		this.getContentPane().add(ppanel);
		
		
		ppanel.setBorder(new EmptyBorder(10, 10, 10, 10) );
//		ppanel.setPreferredSize(new Dimension(400,400));
		
		// layout and constraints
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		ppanel.setLayout(layout);
		
		/**
		 * The label and input box for the spesific place, + the button for choosing a room instead.
		 */
		
		// Label
		roomLabel = new JLabel("Velg rom:");
		c.gridx=0; c.gridy=0; c.ipadx=1; c.ipady=10; c.weightx=1; c.gridwidth=1; c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		ppanel.add(roomLabel, c);
		
		
		// List in the RoomPanel
		roomList = new JList();
		roomList.setFixedCellWidth(300);
		roomList.addListSelectionListener(this);
		roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		roomScrollPane = new JScrollPane(roomList);
		roomScrollPane.setPreferredSize(new Dimension(250, 150));
			
		c.gridx=0; c.gridy=1; c.ipadx=0; c.ipady=1; c.weightx=4; c.gridwidth=3; c.fill = GridBagConstraints.BOTH;
		ppanel.add(roomScrollPane, c);
		
		// Room cancel button
		roomCancelButton = new JButton("Cancel");
		roomCancelButton.addActionListener(this);
		
		c.gridx=1; c.gridy=2; c.ipadx=1; c.ipady=1; c.weightx=1; c.gridwidth=1; c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.EAST;
		ppanel.add(roomCancelButton, c);
		
		// Room save button
		roomSaveButton = new JButton("Save");
		
		c.gridx=2; c.gridy=2; c.ipadx=1; c.ipady=1; c.weightx=1; c.gridwidth=0; c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		ppanel.add(roomSaveButton, c);
			

		this.setVisible(true);
		this.setResizable(false);
		this.pack();
		
	}
	
	public static void main(String[] args)
	{
		new PlaceRoomChooserPanel();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(roomCancelButton))
		{
			this.setVisible(false);
			this.dispose();
		}
		else if(arg0.getSource().equals(roomSaveButton))
		{
			this.setVisible(false);
			this.dispose();
		}
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		
	}
	
}
