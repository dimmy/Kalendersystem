package no.ntnu.fp.gui;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import no.ntnu.fp.model.Room;
import no.ntnu.fp.model.ref.RoomRef;

@SuppressWarnings("serial")
public class PlaceRoomChooserListRenderer extends JLabel implements ListCellRenderer {

	public Component getListCellRendererComponent(
			JList list, 
			Object value,
			int index, 
			boolean isSelected, 
			boolean cellHasFocus) 
	{
		RoomRef r = (RoomRef) value;
		if(r != null){
			String s = r.getRoomid();
			setText(s);
		}
		return this;
	}
	
}
