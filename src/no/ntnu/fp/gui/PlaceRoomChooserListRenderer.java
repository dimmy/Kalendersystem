package no.ntnu.fp.gui;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import no.ntnu.fp.model.Room;

@SuppressWarnings("serial")
public class PlaceRoomChooserListRenderer extends JLabel implements ListCellRenderer {

	public Component getListCellRendererComponent(
			JList list, 
			Object value,
			int index, 
			boolean isSelected, 
			boolean cellHasFocus) 
	{
		Room r = (Room) value;
		if(r != null){
			String s = r.getRoomID() + " - " + r.getCapacity();
			setText(s);
		}
		return this;
	}
	
}
