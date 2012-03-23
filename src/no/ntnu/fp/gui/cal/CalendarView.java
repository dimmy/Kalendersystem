package no.ntnu.fp.gui.cal;

import no.ntnu.fp.model.CalendarChangeEvent;
import no.ntnu.fp.model.CalendarChangeEventListener;
import no.ntnu.fp.model.CalendarPerspective;
import no.ntnu.fp.model.Event;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.SpringLayout.Constraints;

import no.ntnu.fp.model.Event;

/**
 * Panel for displaying a 7 day week calendar
 * 
 * @author David M
 * 
 */

public class CalendarView extends JPanel implements CalendarChangeEventListener {

	JButton btnPrevWeek;
	JButton btnNextWeek;
	JScrollPane scrollPane;
	JPanel headerPanel;
	JPanel rowHeaderPanel;

	private final static String dayNames[] = { "Mon", "Tue", "Wed", "Thu",
			"Fri", "Sat", "Sun" };
	JLabel headerDayLabes[];

	JPanel innerPanel;
	SpringLayout innerSpring;
	Spring innerColSprings[];

	List<EventPanel> eventPanels;

	public final static int HOUR_HEIGHT = 64;
	public final static int FIRST_HOUR = 7;
	public final static int LAST_HOUR = 24;

	private final static DateFormat dateFormat = DateFormat.getInstance();

	private CalendarPerspective perspective;

	public CalendarView() {
		// Placeholder
		btnPrevWeek = new JButton();
		btnNextWeek = new JButton();
		scrollPane = new JScrollPane();

		btnPrevWeek.setIcon(new ImageIcon("Icons/leftarrow.png"));
		btnNextWeek.setIcon(new ImageIcon("Icons/rightarrow.png"));

		btnPrevWeek.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (perspective != null) {
					perspective.setWeek(perspective.getWeek() - 1);
				}
			}
		});

		btnNextWeek.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (perspective != null) {
					perspective.setWeek(perspective.getWeek() + 1);
				}
			}
		});

		SpringLayout spring = new SpringLayout();
		setLayout(spring);

		add(btnPrevWeek);
		add(scrollPane);
		add(btnNextWeek);

		spring.putConstraint(SpringLayout.WEST, btnPrevWeek, 0,
				SpringLayout.WEST, this);
		spring.putConstraint(SpringLayout.EAST, btnNextWeek, 0,
				SpringLayout.EAST, this);

		spring.putConstraint(SpringLayout.WEST, scrollPane, 5,
				SpringLayout.EAST, btnPrevWeek);
		spring.putConstraint(SpringLayout.EAST, scrollPane, -5,
				SpringLayout.WEST, btnNextWeek);

		spring.putConstraint(SpringLayout.NORTH, scrollPane, 0,
				SpringLayout.NORTH, this);
		spring.putConstraint(SpringLayout.SOUTH, scrollPane, 0,
				SpringLayout.SOUTH, this);

		Spring middle = Spring.scale(spring.getConstraints(scrollPane)
				.getHeight(), 0.5f);
		Spring halfButton = Spring.scale(spring.getConstraints(btnPrevWeek)
				.getHeight(), 0.5f);
		Spring btnVertPos = Spring.sum(middle, Spring.minus(halfButton));
		spring.getConstraints(btnPrevWeek).setY(btnVertPos);
		spring.getConstraints(btnNextWeek).setY(btnVertPos);

		// Inner calendar view

		// scrollPane = new JScrollPane();
		// innerCalendarView.add(scrollPane);

		innerPanel = new CalendarInnerPanel();

		scrollPane.setViewportView(innerPanel);
		scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		scrollPane.getVerticalScrollBar().setBlockIncrement(HOUR_HEIGHT);
		scrollPane.getVerticalScrollBar().setUnitIncrement(HOUR_HEIGHT/4);

		scrollPane.setBackground(Color.WHITE);

		innerSpring = new SpringLayout();
		innerPanel.setLayout(innerSpring);

		innerPanel.setMinimumSize(new Dimension((int) innerPanel
				.getMinimumSize().getWidth(), HOUR_HEIGHT
				* (LAST_HOUR - FIRST_HOUR)));
		innerPanel.setPreferredSize(new Dimension((int) innerPanel
				.getPreferredSize().getWidth(), HOUR_HEIGHT
				* (LAST_HOUR - FIRST_HOUR)));

		innerColSprings = new Spring[8];
		for (int i = 0; i < 8; i++) {
			innerColSprings[i] = Spring.scale(
					innerSpring.getConstraints(innerPanel).getWidth(),
					((float) i) / 7.0f);
		}

		// add some example events

		eventPanels = new ArrayList<EventPanel>();

		// Header panel

		headerPanel = new JPanel();
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
		scrollPane.setColumnHeaderView(headerPanel);

		headerDayLabes = new JLabel[7];
		
		for (int i = 0; i < 7; i++) {
			headerPanel.add(makeNewHeader(dayNames[i], i));
		}

		// Row header panel

		SpringLayout rowSpring = new SpringLayout();

		rowHeaderPanel = new JPanel();
		rowHeaderPanel.setOpaque(false);
		rowHeaderPanel.setLayout(rowSpring);

		scrollPane.setRowHeaderView(rowHeaderPanel);

		Spring maxWidth = Spring.constant(0);

		for (int hour = FIRST_HOUR, ypos = 0; hour <= LAST_HOUR; hour++, ypos += HOUR_HEIGHT) {
			JLabel rowHeader = makeNewRowHeader(new Integer(hour).toString()
					+ ":00");
			rowHeaderPanel.add(rowHeader);

			rowSpring.putConstraint(SpringLayout.WEST, rowHeader, 0,
					SpringLayout.WEST, rowHeaderPanel);
			rowSpring.getConstraints(rowHeader).setY(Spring.constant(ypos));
			maxWidth = Spring.max(maxWidth,
					rowSpring.getConstraint(SpringLayout.EAST, rowHeader));
			rowSpring.putConstraint(SpringLayout.SOUTH, rowHeaderPanel, 0,
					SpringLayout.SOUTH, rowHeader);
		}
		rowSpring.getConstraints(rowHeaderPanel).setWidth(maxWidth);

		// scrollPane.getHeader

	}

	private JPanel makeNewHeader(String caption, int index) {
		JPanel p = new JPanel();
		p.setOpaque(false);
		JLabel l = new JLabel(caption + " ??/??");
		headerDayLabes[index] = l;
		p.add(l);
		return p;
	}

	private JLabel makeNewRowHeader(String caption) {
		JLabel l = new JLabel(caption);
		l.setSize(100, 100);
		return l;
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Calendar view test");
		frame.add(new CalendarView());
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);
	}

	public void addEvent(Event e) {
		addEventPanel(new EventPanel(e));
	}

	private void addEventPanel(EventPanel ep) {
		if (ep == null)
			return;

		innerPanel.add(ep);
		eventPanels.add(ep);

		Constraints c = innerSpring.getConstraints(ep);

		c.setX(innerColSprings[ep.getDay()]);
		c.setWidth(innerColSprings[1]);
		c.setY(Spring.constant(ep.getEventYPos() - (HOUR_HEIGHT * FIRST_HOUR)));
		c.setHeight(Spring.constant(ep.getEventYSize()));
	}

	public void setPerspective(CalendarPerspective perspective) {
		if (this.perspective != null) {
			this.perspective.removeCalendarChangeEventListener(this);
		}
		this.perspective = perspective;
		perspective.addCalendarChangeEventListener(this);
	}

	public CalendarPerspective getPerspective() {
		return perspective;
	}

	@Override
	public void calendarChanged(CalendarChangeEvent ev) {
		if (perspective != null) {
			clear();
			populate();
			repaint();
		}

		java.util.Calendar c = java.util.Calendar.getInstance();

		c.setTime(perspective.getFrom());

		int dayOfYear = c.get(java.util.Calendar.DAY_OF_YEAR);

		for (int i = 0; i < 7; i++) {
			c.set(java.util.Calendar.DAY_OF_YEAR, dayOfYear+i);
			int day = c.get(java.util.Calendar.DAY_OF_MONTH);
			int month = c.get(java.util.Calendar.MONTH)+1;
			headerDayLabes[i].setText(dayNames[i] + " " + Integer.toString(day)
					+ "/" + Integer.toString(month));
		}
	}

	private void clear() {
		for (EventPanel ep : eventPanels) {
			// TODO: uncomment this line when it is possible
			// ep.setModel(null);
			innerPanel.remove(ep);
		}
		eventPanels.clear();
	}

	private void populate() {
		List<Event> events = perspective.getEvents();

		for (Event ev : events) {
			addEvent(ev);
		}
	}
}
