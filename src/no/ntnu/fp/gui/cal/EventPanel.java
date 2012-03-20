package no.ntnu.fp.gui.cal;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.BevelBorder;

public class EventPanel extends JPanel {

	private int fromHour = 8;
	private int fromMin = 0;

	private int lengthMins = 60;

	private int day = 0;
	
	private String title = "(avtale)";

	public EventPanel(String title) {
		setBorder(new BevelBorder(BevelBorder.RAISED));
		setBackground(new Color(255, 255, 128));
		setMinimumSize(new Dimension(0, CalendarView.HOUR_HEIGHT));

		this.title = title;
		
		add(new JLabel(title));
	}

	public void setFrom(int hour, int minute) {
		fromHour = hour;
		fromMin = minute;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public void setLength(int mins) {
		lengthMins = mins;
	}

	public int getDay() {
		return day;
	}

	public int getEventYPos() {
		return (CalendarView.HOUR_HEIGHT * (60 * fromHour + fromMin)) / 60;
	}

	public int getEventYSize() {
		return (CalendarView.HOUR_HEIGHT * (lengthMins)) / 60;
	}
}
