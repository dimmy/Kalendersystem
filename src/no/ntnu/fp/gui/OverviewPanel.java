package no.ntnu.fp.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.ParseException;
import java.util.Date;

import javax.swing.*;

import no.ntnu.fp.gui.cal.CalendarView;
import no.ntnu.fp.model.Calendar;
import no.ntnu.fp.model.CalendarChangeEvent;
import no.ntnu.fp.model.CalendarChangeEventListener;
import no.ntnu.fp.model.CalendarPerspective;
import no.ntnu.fp.model.Event;

/**
 * Panel for main overview window
 * 
 * @author David M
 * 
 */

public class OverviewPanel extends JPanel implements CalendarChangeEventListener {

	static int buttonVerticalPadding = 10;
	static int buttonHorizontalPadding = 5;

	JButton btnNewAppointment;
	JButton btnAddCalendar;
	JButton btnInbox;
	JButton btnLogOut;

	JPanel panLeftButtonPanel;

	JLabel lblWeekDisplay;
	JLabel lblLoginInfo;
	JPanel panTitle;

	CalendarView calendarView;
	
	private CalendarPerspective perspective;
	private Calendar calendar;

	public OverviewPanel() {
		btnNewAppointment = new JButton("Ny avtale");
		btnAddCalendar = new JButton("Add calendar");
		btnInbox = new JButton("Inbox (?)");
		btnLogOut = new JButton("Log out");

		// Left side button panel

		SpringLayout spring = new SpringLayout();

		panLeftButtonPanel = new JPanel();
		panLeftButtonPanel.setLayout(spring);

		panLeftButtonPanel.add(btnNewAppointment);
		panLeftButtonPanel.add(btnAddCalendar);
		panLeftButtonPanel.add(btnInbox);
		panLeftButtonPanel.add(btnLogOut);

		spring.putConstraint(SpringLayout.WEST, btnNewAppointment,
				buttonHorizontalPadding, SpringLayout.WEST, panLeftButtonPanel);
		spring.putConstraint(SpringLayout.WEST, btnAddCalendar,
				buttonHorizontalPadding, SpringLayout.WEST, panLeftButtonPanel);
		spring.putConstraint(SpringLayout.WEST, btnInbox,
				buttonHorizontalPadding, SpringLayout.WEST, panLeftButtonPanel);
		spring.putConstraint(SpringLayout.WEST, btnLogOut,
				buttonHorizontalPadding, SpringLayout.WEST, panLeftButtonPanel);

		Spring rightBorder = Spring.constant(0);
		rightBorder = rightBorder.max(rightBorder,
				spring.getConstraints(btnNewAppointment).getWidth());
		rightBorder = rightBorder.max(rightBorder,
				spring.getConstraints(btnAddCalendar).getWidth());
		rightBorder = rightBorder.max(rightBorder,
				spring.getConstraints(btnInbox).getWidth());
		rightBorder = rightBorder.max(rightBorder,
				spring.getConstraints(btnLogOut).getWidth());

		spring.getConstraints(btnNewAppointment).setWidth(rightBorder);
		spring.getConstraints(btnAddCalendar).setWidth(rightBorder);
		spring.getConstraints(btnInbox).setWidth(rightBorder);
		spring.getConstraints(btnLogOut).setWidth(rightBorder);

		rightBorder = Spring.sum(rightBorder, Spring.constant(10));

		spring.getConstraints(panLeftButtonPanel).setWidth(rightBorder);

		spring.putConstraint(SpringLayout.NORTH, btnNewAppointment,
				buttonVerticalPadding, SpringLayout.NORTH, panLeftButtonPanel);
		spring.putConstraint(SpringLayout.NORTH, btnAddCalendar,
				buttonVerticalPadding, SpringLayout.SOUTH, btnNewAppointment);
		spring.putConstraint(SpringLayout.NORTH, btnInbox,
				buttonVerticalPadding, SpringLayout.SOUTH, btnAddCalendar);
		spring.putConstraint(SpringLayout.NORTH, btnLogOut,
				buttonVerticalPadding, SpringLayout.SOUTH, btnInbox);

		// Title

		lblWeekDisplay = new JLabel("<html><h1>Uke ?</h1></html>",
				SwingConstants.CENTER);
		lblLoginInfo = new JLabel("<html>Logget inn som: brukernavn</html>",
				SwingConstants.RIGHT);

		panTitle = new JPanel();
		panTitle.setLayout(new BoxLayout(panTitle, BoxLayout.Y_AXIS));

		panTitle.add(lblLoginInfo);
		panTitle.add(lblWeekDisplay);

		lblLoginInfo.setOpaque(true);
		lblWeekDisplay.setOpaque(true);

		// Calendar view

		calendarView = new CalendarView();

		// Combine!

		spring = new SpringLayout();

		setLayout(spring);

		add(panLeftButtonPanel);
		add(calendarView);
		add(panTitle);

		spring.putConstraint(SpringLayout.NORTH, panTitle, 0,
				SpringLayout.NORTH, this);
		// PS: This line sets the height of the title panel
		spring.putConstraint(SpringLayout.SOUTH, panTitle, 60,
				SpringLayout.NORTH, this);
		spring.putConstraint(SpringLayout.WEST, panTitle, 0, SpringLayout.WEST,
				this);
		spring.putConstraint(SpringLayout.EAST, panTitle, 0, SpringLayout.EAST,
				this);

		spring.putConstraint(SpringLayout.NORTH, panLeftButtonPanel, 0,
				SpringLayout.SOUTH, panTitle);
		spring.putConstraint(SpringLayout.SOUTH, panLeftButtonPanel, 0,
				SpringLayout.SOUTH, this);
		// PS: This line sets the width of the button panel
		// spring.putConstraint(SpringLayout.EAST, panLeftButtonPanel, 200,
		// SpringLayout.WEST, this);
		spring.putConstraint(SpringLayout.WEST, panLeftButtonPanel, 0,
				SpringLayout.WEST, this);

		spring.putConstraint(SpringLayout.WEST, calendarView, 5,
				SpringLayout.EAST, panLeftButtonPanel);
		spring.putConstraint(SpringLayout.NORTH, calendarView, 5,
				SpringLayout.SOUTH, panTitle);
		spring.putConstraint(SpringLayout.EAST, calendarView, -5,
				SpringLayout.EAST, this);
		spring.putConstraint(SpringLayout.SOUTH, calendarView, -5,
				SpringLayout.SOUTH, this);

	}

	
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
		if (perspective != null) {
			perspective.removeCalendarChangeEventListener(this);
			perspective.liberate();
		}
		perspective = new CalendarPerspective(calendar);
		perspective.addCalendarChangeEventListener(this);
		calendarView.setPerspective(perspective);
		perspective.update();
	}
	
	public Calendar getCalendar() {
		return calendar;
	}

	@Override
	public void calendarChanged(CalendarChangeEvent ev) {
		lblWeekDisplay.setText("<html><h1>Uke "+Integer.toString(perspective.getWeek())+"</h1></html>");
	}
	
	/**
	 * Changes the perspective to the current week
	 */
	public void goToNow() {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(new Date());
		perspective.setYear(c.get(java.util.Calendar.YEAR));
		perspective.setWeek(c.get(java.util.Calendar.WEEK_OF_YEAR));
	}
	

	public static void main(String[] args) {
		Calendar cal = new Calendar();
		OverviewPanel op = new OverviewPanel();
		
		op.setCalendar(cal);
		op.goToNow();

		Event ev = new Event();
		
		ev.setTime("2012-03-22 13:00");
		ev.setTimeLength(60);
		ev.setEventname("Testevent");
		ev.setEventdescription("This is a test event. everyone is invited.");
		
		cal.addEvent(ev);

		ev = new Event();
		
		ev.setTime("2012-03-23 14:00");
		ev.setTimeLength(80);
		ev.setEventname("Testevent 2");
		ev.setEventdescription("Barbecue on roof.");
		
		cal.addEvent(ev);
		

		ev = new Event();
		
		ev.setTime("2012-03-27 09:00");
		ev.setTimeLength(90);
		ev.setEventname("Testevent 3");
		ev.setEventdescription("Woot woot!");
		
		cal.addEvent(ev);
		
		JFrame frame = new JFrame();
		frame.add(op);
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
	}
}
