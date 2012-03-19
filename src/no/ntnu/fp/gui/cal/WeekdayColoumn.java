package no.ntnu.fp.gui.cal;

import javax.swing.*;
import javax.swing.border.BevelBorder;

public class WeekdayColoumn extends JPanel {
	String day;
	
	public WeekdayColoumn(String day) {
		this.day = day;
		
		add(new JLabel(day));
		setBorder(new BevelBorder(BevelBorder.LOWERED));
	}
}
