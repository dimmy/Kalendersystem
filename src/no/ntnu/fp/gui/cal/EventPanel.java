package no.ntnu.fp.gui.cal;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import no.ntnu.fp.model.*;

public class EventPanel extends JPanel {

	private int fromHour = 8;
	private int fromMin = 0;

	private int lengthMins = 55;

	private int day = 0;

	private String title = "(avtale)";

	private Event model = null;

	private void initAppearance() {

		setBorder(new BevelBorder(BevelBorder.RAISED));
		setBackground(new Color(255, 255, 128));
		setMinimumSize(new Dimension(0, CalendarView.HOUR_HEIGHT));
	}

	public EventPanel(Event model) {
		initAppearance();

		this.model = model;

		updatePos();

		add(new JLabel(model.getEventname()));
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

	public void updatePos() {
		java.util.Calendar c = java.util.Calendar.getInstance();

		c.setTime(model.getStartTime());

		day = javaToNorwegianWeekday(c.get(java.util.Calendar.DAY_OF_WEEK));

		lengthMins = model.getTimeLength();
		
		fromHour = c.get(java.util.Calendar.HOUR_OF_DAY);
		fromMin = c.get(java.util.Calendar.MINUTE);
	}

	private int javaToNorwegianWeekday(int javaDay) {
		switch (javaDay) {
		case java.util.Calendar.MONDAY:
			return 0;
		case java.util.Calendar.TUESDAY:
			return 1;
		case java.util.Calendar.WEDNESDAY:
			return 2;
		case java.util.Calendar.THURSDAY:
			return 3;
		case java.util.Calendar.FRIDAY:
			return 4;
		case java.util.Calendar.SATURDAY:
			return 5;
		case java.util.Calendar.SUNDAY:
			return 6;
		default:
			return 0;
		}
	}
}
