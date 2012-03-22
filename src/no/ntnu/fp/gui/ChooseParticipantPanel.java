package no.ntnu.fp.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ChooseParticipantPanel extends JPanel{
	
	private JList participantList;
	private JList employeeList;
	
	private JScrollPane participantListScroll;
	private JScrollPane employeeListScroll;
	
	private JLabel participants;
	private JLabel employee;
	
	private JButton addParticipant;
	private JButton deleteParticipant;
	private JButton okButton;
	
	private DefaultListModel listModelParticipant;
	private DefaultListModel listModelEmployee;
	
	
	public ChooseParticipantPanel(){
		
		this.setBorder(new EmptyBorder(10,10,10,10));
		
		listModelParticipant = new DefaultListModel();
		participantList = new JList(listModelParticipant);
		participantListScroll = new JScrollPane(participantList);
		participants = new JLabel("Deltagere: ");
		participantListScroll.setPreferredSize(new Dimension(150, 200));
		
		//Testing
		listModelParticipant.addElement("Per");
		
		//
		
		listModelEmployee = new DefaultListModel();
		employeeList = new JList(listModelEmployee);
		employeeListScroll = new JScrollPane(employeeList);
		employee = new JLabel("Ansatt:");
		employeeListScroll.setPreferredSize(new Dimension(150, 200));
		
		addParticipant = new JButton("Legg til deltager");
		
		addParticipant.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(participantList.getSelectedValue() != null){
					Object temp = participantList.getSelectedValue();
					listModelParticipant.removeElement(temp);
					listModelEmployee.addElement(temp);
				}
				
			}
		});
		
		deleteParticipant = new JButton("Slett deltager");
		deleteParticipant.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(employeeList.getSelectedValue() != null){
					Object temp = employeeList.getSelectedValue();
					listModelEmployee.removeElement(temp);
					listModelParticipant.addElement(temp);
				}
				
			}
		});
		
		okButton = new JButton("Ok");
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getParent().getParent().getParent().getParent().setVisible(false);
			}
		});
				
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints participantC = new GridBagConstraints();
		
		
		participantC.gridx = 0;
		participantC.gridy = 0;
		this.add(employee, participantC);
		
		participantC.gridx = 0;
		participantC.gridy = 1;
		this.add(participantListScroll, participantC);
		
		participantC.gridx = 1;
		participantC.gridy = 0;
		this.add(participants, participantC);
		
		participantC.gridx = 1;
		participantC.gridy = 1;
		this.add(employeeListScroll, participantC);
		
		participantC.anchor = GridBagConstraints.WEST;
		
		participantC.gridx = 0;
		participantC.gridy = 2;
		this.add(addParticipant, participantC);
		
		participantC.gridx = 1;
		participantC.gridy = 2;
		this.add(deleteParticipant, participantC);
		
		participantC.anchor = GridBagConstraints.EAST;
		
		participantC.insets = new Insets(10, 10, 0, 0);
		participantC.gridx = 1;
		participantC.gridy = 3;
		this.add(okButton, participantC);
		
		
	}
	
	public void getParticipants()
	{
		
	}
	
	public void setAvaliblePersons(ArrayList<User> users )
	{
		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new ChooseParticipantPanel());
		frame.pack();
		frame.setVisible(true);
	}
	
	

}
