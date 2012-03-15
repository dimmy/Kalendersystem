package no.ntnu.fp.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ChooseParticipantPanel extends JPanel{
	
	private JList participantList;
	private JScrollPane participantListScroll;
	private JList employeeList;
	private JScrollPane employeeListScroll;
	private JLabel participants;
	private JLabel employee;
	private JButton addParticipant;
	private JButton deleteParticipant;
	
	public ChooseParticipantPanel(){
		
		String[] navn = new String[10];
		navn[0] = "Per Knutsen";
		navn[1] = "Knut Persen";
		
		participantList = new JList(navn);
		participantListScroll = new JScrollPane(participantList);
		participants = new JLabel("Deltagere: ");
		participantListScroll.setPreferredSize(new Dimension(150, 200));
		participantList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		employeeList = new JList();
		employeeListScroll = new JScrollPane(employeeList);
		employee = new JLabel("Ansatt:");
		employeeListScroll.setPreferredSize(new Dimension(150, 200));
		
		addParticipant = new JButton("Legg til deltager");
		deleteParticipant = new JButton("Slett deltager");
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints participantC = new GridBagConstraints();
		
		participantC.gridx = 0;
		participantC.gridy = 0;
		this.add(participants, participantC);
		
		participantC.gridx = 0;
		participantC.gridy = 1;
		this.add(participantListScroll, participantC);
		
		participantC.gridx = 1;
		participantC.gridy = 0;
		this.add(employee, participantC);
		
		participantC.gridx = 1;
		participantC.gridy = 1;
		this.add(employeeListScroll, participantC);
		
		participantC.gridx = 0;
		participantC.gridy = 2;
		this.add(addParticipant, participantC);
		
		participantC.gridx = 1;
		participantC.gridy = 2;
		this.add(deleteParticipant, participantC);
		
		
		
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new ChooseParticipantPanel());
		frame.pack();
		frame.setVisible(true);
	}
	
	

}
