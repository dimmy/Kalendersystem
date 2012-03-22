package no.ntnu.fp.gui.cal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.jws.Oneway;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import no.ntnu.fp.model.*;

public class EventPanel extends JPanel implements PropertyChangeListener, MouseListener {

	private class Popup extends JPopupMenu {
		public Popup() {
			add(new JMenuItem("Rediger"));	//TODO: Implement
			add(new JMenuItem("Meld avbud")); //TODO: Implement
		}
	}
	
	private int fromHour = 8;
	private int fromMin = 0;

	private int lengthMins = 55;

	private int day = 0;

	private JLabel title;
	private JLabel desc;

	private Event model = null;

	private static final Font TITLE_FONT = new Font("Serif", Font.BOLD, 11);
	private static final Font DESC_FONT = new Font("Serif", 0, 11);

	private void initAppearance() {

		setBorder(new LineBorder(Color.DARK_GRAY));
		setBackground(new Color(255, 255, 128));
		setMinimumSize(new Dimension(0, CalendarView.HOUR_HEIGHT));
	}

	public EventPanel(Event model) {

		initAppearance();

		title = new JLabel("(title)");
		desc = new JLabel("<html>(desc)</html>");

		title.setFont(TITLE_FONT);
		desc.setFont(DESC_FONT);
		desc.setVerticalAlignment(JLabel.TOP);

		SpringLayout spring = new SpringLayout();

		setLayout(spring);

		add(title);
		add(desc);

		spring.putConstraint(SpringLayout.WEST, title, 2, SpringLayout.WEST,
				this);
		spring.putConstraint(SpringLayout.NORTH, title, 2, SpringLayout.NORTH,
				this);
		spring.putConstraint(SpringLayout.EAST, title, -2, SpringLayout.EAST,
				this);

		spring.putConstraint(SpringLayout.WEST, desc, 2, SpringLayout.WEST,
				this);
		spring.putConstraint(SpringLayout.NORTH, desc, 2, SpringLayout.SOUTH,
				title);
		spring.putConstraint(SpringLayout.EAST, desc, -2, SpringLayout.EAST,
				this);
		spring.putConstraint(SpringLayout.SOUTH, desc, -2, SpringLayout.SOUTH,
				this);

		setModel(model);
		
		// Popup menu
		
		addMouseListener(this);
	}

	private void setModel(Event model) {

		if (this.model != null)
			this.model.removePropertyChangedListener(this);

		this.model = model;

		if (model != null) {
			title.setText(model.getEventname());
			desc.setText("<html>" + model.getEventdescription() + "</html>");

			updatePos();
		}
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

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName() == "eventname") {
			title.setText((String) evt.getOldValue());
		}
		else if (evt.getPropertyName() == "eventdescription") {
			desc.setText("<html>"+(String) evt.getOldValue()+"</html>");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.isPopupTrigger()) {
			doPop(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger()) {
			doPop(e);
		}
	}
	
	private void doPop(MouseEvent e) {
		Popup popup = new Popup();
		popup.show(e.getComponent(), e.getX(), e.getY());
	}
}
