package no.ntnu.fp.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import no.ntnu.fp.model.Room;

/*
 * @author: Fredrik
 * 
 */

@SuppressWarnings("serial")
public class PlaceRoomChooserPanel extends JPanel
{

	List<PlaceRoomListener> selectPlaceRoomListener;
	
	JRadioButton place;
	JTextField placeInput;
	JRadioButton room;
	JComboBox roomInput;
	
	DefaultComboBoxModel defModel;
	
	ButtonGroup buttonGroup;
	
	
	
	public PlaceRoomChooserPanel()
	{
		// init of list with the events
		selectPlaceRoomListener = new ArrayList<PlaceRoomListener>();
		
		// layout and constraints
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(layout);
		
		// ------------------------------------------------------------------------------------
		
		// Radio place
		place = new JRadioButton("Skriv sted:");
		place.setSelected(true);
		place.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				firePlaceRoomSelectEvent();
				roomInput.setEnabled(false);
				placeInput.setEditable(true);
				
			}
		});
		
		// constrains
		c.gridx=0; c.gridy=0; c.ipadx=0; c.ipady=0; c.weightx=1; c.gridwidth=1; c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		add(place, c);
		
		// ------------------------------------------------------------------------------------
		
		// Radio room
		room = new JRadioButton("Velg rom:");
		room.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				firePlaceRoomSelectEvent();
				placeInput.setEditable(false);
				roomInput.setEnabled(true);
				
			}
		});
		
		// constrains
		c.gridx=0; c.gridy=1; c.ipadx=0; c.ipady=0; c.weightx=1; c.gridwidth=1; c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		add(room, c);
		
		// Button Group
		buttonGroup = new ButtonGroup();
		buttonGroup.add(place);
		buttonGroup.add(room);
	
		// ------------------------------------------------------------------------------------
		
		// Input field
		placeInput = new JTextField(20);
		placeInput.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent arg0) { firePlaceRoomSelectEvent(); }
			@Override
			public void insertUpdate(DocumentEvent arg0) { firePlaceRoomSelectEvent(); }
			@Override
			public void changedUpdate(DocumentEvent arg0) {	firePlaceRoomSelectEvent(); }
		});
		
		// constrains
		c.gridx=1; c.gridy=0; c.ipadx=0; c.ipady=0; c.weightx=1; c.gridwidth=1; c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.WEST;
		add(placeInput, c);
		
		// ------------------------------------------------------------------------------------
		
		// Room List FOR DEBUGING
		ArrayList<Room> roomList = new ArrayList<Room>();
		Room r = new Room("Rom 23",  30);
		roomList.add(r);
		r = new Room("Rom 3", 50);
		roomList.add(r);
		r = new Room("Rom 23", 10);
		roomList.add(r);
		r = new Room("Rom A1", 30);
		roomList.add(r);
		r = new Room("Rom gul", 15);
		roomList.add(r);
		// END FOR DEBUGING
		
		// ComboBox
		defModel = new DefaultComboBoxModel();
		for(Room room : roomList){
			defModel.addElement(room);
		}
		roomInput = new JComboBox(defModel);
		roomInput.setEnabled(false);
		roomInput.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) { firePlaceRoomSelectEvent(); }
		});
		roomInput.setRenderer(new PlaceRoomChooserListRenderer());
		
		// constrains
		c.gridx=1; c.gridy=1; c.ipadx=0; c.ipady=0; c.weightx=1; c.gridwidth=1; c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.WEST;
		add(roomInput, c);
		
	}
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Testing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new PlaceRoomChooserPanel());
		frame.pack();
		frame.setVisible(true);
		
	}
	
	public void setSelected(Room room)
	{
		roomInput.setSelectedItem(room);
	}
	
	public void addPlaceRoomSelectListener(PlaceRoomListener listener)
	{
		this.selectPlaceRoomListener.add(listener);
	}
	
	
	
	private void firePlaceRoomSelectEvent(){
		
		PlaceRoomSelectEvent e;
		
		if(buttonGroup.getSelection() == room)
		{
			e = new PlaceRoomSelectEvent((Room)roomInput.getSelectedItem(), null, this);
		}
		else
		{
			e = new PlaceRoomSelectEvent(null, placeInput.getText(), this);
			
		}
		
		for( PlaceRoomListener listener : selectPlaceRoomListener )
		{
			listener.selectPlaceOrRoom(e);
		}

	}
	
	public void setDefaultComboBoxModel(DefaultComboBoxModel that)
	{
		this.defModel = that;
	}
	
}
