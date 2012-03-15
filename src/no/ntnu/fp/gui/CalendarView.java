package no.ntnu.fp.gui;

import java.awt.Color;

import javax.swing.*;

/**
 * Panel for displaying a 7 day week calendar
 * 
 * @author David M
 * 
 */

public class CalendarView extends JPanel {

	JButton btnPrevWeek;
	JButton btnNextWeek;
	JPanel innerCalendarView;

	public CalendarView() {
		// Placeholder
		btnPrevWeek = new JButton();
		btnNextWeek = new JButton();
		innerCalendarView = new JPanel();
		innerCalendarView.setBackground(new Color(255, 128, 128));

		btnPrevWeek.setIcon(new ImageIcon("Icons/leftarrow.png"));
		btnNextWeek.setIcon(new ImageIcon("Icons/rightarrow.png"));
		
		SpringLayout spring = new SpringLayout();
		setLayout(spring);

		add(btnPrevWeek);
		add(innerCalendarView);
		add(btnNextWeek);

		spring.putConstraint(SpringLayout.WEST, btnPrevWeek, 0,
				SpringLayout.WEST, this);
		spring.putConstraint(SpringLayout.EAST, btnNextWeek, 0,
				SpringLayout.EAST, this);

		spring.putConstraint(SpringLayout.WEST, innerCalendarView, 5,
				SpringLayout.EAST, btnPrevWeek);
		spring.putConstraint(SpringLayout.EAST, innerCalendarView, -5,
				SpringLayout.WEST, btnNextWeek);

		spring.putConstraint(SpringLayout.NORTH, innerCalendarView, 0,
				SpringLayout.NORTH, this);
		spring.putConstraint(SpringLayout.SOUTH, innerCalendarView, 0,
				SpringLayout.SOUTH, this);

		Spring middle = Spring.scale(spring.getConstraints(innerCalendarView).getHeight(),
				0.5f);
		Spring halfButton = Spring.scale(spring.getConstraints(btnPrevWeek)
				.getHeight(), 0.5f);
		Spring btnVertPos = Spring.sum(middle, Spring.minus(halfButton));
		spring.getConstraints(btnPrevWeek).setY(btnVertPos);
		spring.getConstraints(btnNextWeek).setY(btnVertPos);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Calendar view test");
		frame.add(new CalendarView());
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
	}

}
