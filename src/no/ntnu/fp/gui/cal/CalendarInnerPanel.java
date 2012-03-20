package no.ntnu.fp.gui.cal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class CalendarInnerPanel extends JPanel {

	public final static Color LINE_COLOR = Color.LIGHT_GRAY;
	
	CalendarInnerPanel() {
		super();
		setOpaque(false);
	}

	@Override
	public void paint(Graphics g) {
		Dimension size = getSize();

		float w = getWidth() / 7.0f;
		
		g.setColor(LINE_COLOR);

		for (int i = 0; i < 7; i++) {
			g.drawLine((int) (w * i), 0, (int) (w * i), (int) size.getHeight());
		}

		int last = CalendarView.LAST_HOUR - CalendarView.FIRST_HOUR - 1;

		for (int i = 0; i < last; i ++) {
			float y = i * CalendarView.HOUR_HEIGHT;
			g.drawLine(0, (int) y, getWidth(), (int) y);
		}

		super.paint(g);
	}
}
