package no.ntnu.fp.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.StyleConstants.FontConstants;

public class InboxPanel extends JPanel{
	
	private JPanel leftPanel;
	private JPanel rigthPanel;
	
	private JList messages;
	private JScrollPane messageScroll;
	private DefaultListModel messageModel;
	
	private JTextArea textArea;
	private JScrollPane textAreaScroll;
	
	public InboxPanel(){
		
		this.setBorder(new EmptyBorder(10,10,10,10));
		
		leftPanel = new JPanel();
		rigthPanel = new JPanel();
		//rigthPanel.setBorder(BorderFactory.createBevelBorder(1, Color.BLACK, Color.BLACK));
		
		messageModel = new DefaultListModel();
		messages = new JList(messageModel);
		messageScroll = new JScrollPane(messages);
		messageScroll.setPreferredSize(new Dimension(150,300));
		leftPanel.add(messageScroll);
		
		textArea = new JTextArea();
		textAreaScroll = new JScrollPane(textArea);
		textAreaScroll.setPreferredSize(new Dimension(300,300));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Testing
		messageModel.addElement(new MeetingView());
		
		messages.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				rigthPanel.add((Component) messages.getSelectedValue());
				((JFrame)rigthPanel.getParent().getParent().getParent().getParent().getParent()).pack();
				
			}
		});
		
		//
		
		
		c.gridx = 0;
		c.gridy = 1;
		this.add(leftPanel, c);
		
		c.gridx = 1;
		c.gridy = 1;
		this.add(rigthPanel, c);
		
		
	}
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		InboxPanel panel = new InboxPanel();
		frame.add(panel);
		frame.setVisible(true);
		frame.pack();
		
	}
}
