package no.ntnu.fp.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import no.ntnu.fp.model.ref.RoomRef;

/*
 * @author: Fredrik
 * 
 */

@SuppressWarnings("serial")
public class PlaceRoomChooserPanel extends JPanel {

	List<PlaceRoomListener> selectPlaceRoomListener;

	JRadioButton place;
	JTextField placeInput;
	JRadioButton room;
	JComboBox roomInput;

	DefaultComboBoxModel defModel;

	ButtonGroup buttonGroup;

	public PlaceRoomChooserPanel() {
		// init of list with the events
		selectPlaceRoomListener = new ArrayList<PlaceRoomListener>();

		// layout and constraints
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(layout);

		// ------------------------------------------------------------------------------------

		// Radio place
		place = new JRadioButton("Place:");
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
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 0;
		c.ipady = 0;
		c.weightx = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		add(place, c);

		// ------------------------------------------------------------------------------------

		// Radio room
		room = new JRadioButton("Choose room:");
		room.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				firePlaceRoomSelectEvent();
				placeInput.setEditable(false);
				roomInput.setEnabled(true);

			}
		});

		// constrains
		c.gridx = 0;
		c.gridy = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.weightx = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
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
			public void removeUpdate(DocumentEvent arg0) {
				firePlaceRoomSelectEvent();
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				firePlaceRoomSelectEvent();
			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				firePlaceRoomSelectEvent();
			}
		});

		// constrains
		c.gridx = 1;
		c.gridy = 0;
		c.ipadx = 0;
		c.ipady = 0;
		c.weightx = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.WEST;
		add(placeInput, c);

		// ------------------------------------------------------------------------------------

		// Room List FOR DEBUGING
		ArrayList<RoomRef> roomList = new ArrayList<RoomRef>();
		RoomRef r = new RoomRef("Rom 23");
		roomList.add(r);
		r = new RoomRef("Rom 3");
		roomList.add(r);
		r = new RoomRef("Rom 23");
		roomList.add(r);
		r = new RoomRef("Rom A1");
		roomList.add(r);
		r = new RoomRef("Rom gul");
		roomList.add(r);
		// END FOR DEBUGING

		// ComboBox
		defModel = new DefaultComboBoxModel();
		for (RoomRef room : roomList) {
			defModel.addElement(room);
		}
		roomInput = new JComboBox(defModel);
		roomInput.setEnabled(false);
		roomInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				firePlaceRoomSelectEvent();
			}
		});
		roomInput.setRenderer(new PlaceRoomChooserListRenderer());

		// constrains
		c.gridx = 1;
		c.gridy = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.weightx = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.WEST;
		add(roomInput, c);

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Testing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new PlaceRoomChooserPanel());
		frame.pack();
		frame.setVisible(true);

	}

	public void addPlaceRoomSelectListener(PlaceRoomListener listener) {
		this.selectPlaceRoomListener.add(listener);
	}

	public void setPlaceRoom(String place, RoomRef room) {
		if (room != null && room.getRoomid() != null) {
			int roomindex = 0;
			for (int i = 0; i < roomInput.getItemCount(); i++) {
				if (room.getRoomid().equals(
						((RoomRef) roomInput.getItemAt(i)).getRoomid())) {
					roomindex = i;
					break;
				}
			}
			this.room.setSelected(true);
			roomInput.setEnabled(true);
			placeInput.setEditable(false);
			roomInput.setSelectedIndex(roomindex);
		} else if (place != null) {
			this.place.setSelected(true);
			placeInput.setEditable(true);
			roomInput.setEnabled(false);
			placeInput.setText(place);
		}
	}

	private void firePlaceRoomSelectEvent() {

		PlaceRoomSelectEvent e;

		if (room.isSelected()) {
			e = new PlaceRoomSelectEvent((RoomRef) roomInput.getSelectedItem(),
					null, this);
		} else {
			e = new PlaceRoomSelectEvent(null, placeInput.getText(), this);

		}

		for (PlaceRoomListener listener : selectPlaceRoomListener) {
			listener.selectPlaceOrRoom(e);
		}

	}

	public void setDefaultComboBoxModel(DefaultComboBoxModel that) {
		this.defModel = that;
	}

	public String getPlace() {
		if (place.isSelected()) return null;
		else return placeInput.getText();
	}

	public RoomRef getRoom() {
		if (room.isSelected()) return null;
		else return (RoomRef) roomInput.getSelectedItem();
	}
}
