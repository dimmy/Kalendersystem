package no.ntnu.fp.gui;

import java.awt.GridBagLayout;
import javax.swing.BorderFactory;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DeleteMeetingOption extends JPanel{

	private JOptionPane text;
	
	public static void main (String []args){
		JFrame deletemeeting = new JFrame("Slette møte?");
		deletemeeting.setSize(260,140);
		deletemeeting.getContentPane().add(new DeleteMeetingOption());
		deletemeeting.setLocationRelativeTo(null);
		deletemeeting.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		deletemeeting.setVisible(true);
}

	public DeleteMeetingOption(){
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7));
		
		text = new JOptionPane("Er du sikker du vil slette møtet?", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
		add(text);
	
	}
}
