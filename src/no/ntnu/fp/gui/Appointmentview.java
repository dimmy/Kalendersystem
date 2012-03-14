package no.ntnu.fp.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class Appointmentview extends JPanel{
	
	private JLabel stedLabel;
	private JTextField stedField;
	private JLabel tidLabel;
	private JLabel fraLabel;
	private JLabel tilLabel;
	private JTextField tidFraField;
	private JTextField tidTilField;
	private JLabel datoLabel;
	private JTextField datoField;
	private JLabel beskrivelseLabel;
	private JTextArea beskrivelseField;
	private JLabel deltagerLabel;
	private JTextArea deltagerField;
	private JButton endrelagre;
	private JButton leggetilslett;
	private JButton avlys;
	
	
	public static void main (String []args){
		JFrame appointmentview = new JFrame("Personalmøte");
		appointmentview.setSize(350,350);
//		appointmentview.pack();
		appointmentview.getContentPane().add(new Appointmentview());
		appointmentview.setLocationRelativeTo(null);
		appointmentview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appointmentview.setVisible(true);
	}
	public Appointmentview(){
		
		GridBagConstraints c = new GridBagConstraints();
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7));

		//Sted
		stedLabel = new JLabel("Sted:");
		c.gridwidth=1;
		c.ipadx=10; c.ipady=10;
		c.gridx=0; c.gridy=0;
		c.anchor = GridBagConstraints.WEST;
		add(stedLabel, c);
		stedField = new JTextField();
		stedField.setColumns(20);
		c.gridwidth=4;
		c.ipadx=0; c.ipady=0;
		c.gridx=1; c.gridy=0;
		add(stedField, c);
		
		//Tid, fra-til
		tidLabel = new JLabel("Tid:");
		c.gridwidth=1;
		c.ipadx=10; c.ipady=10;
		c.gridx=0; c.gridy=1;
		c.anchor = GridBagConstraints.WEST;
		add(tidLabel, c);
		fraLabel = new JLabel("Fra:");
		c.gridwidth=1;
		c.ipadx=10; c.ipady=10;
		c.gridx=1; c.gridy=1;
		add(fraLabel, c);
		tidFraField = new JTextField();
		tidFraField.setColumns(6);
		c.gridwidth=1;
		c.ipadx=0; c.ipady=0;
		c.gridx=2; c.gridy=1;
		add(tidFraField, c);
		
		tilLabel = new JLabel("Til: ");
		c.gridx=3; c.gridy=1;
		c.ipadx=10; c.ipady=10;
		c.gridwidth=1;
		add(tilLabel, c);
		tidTilField = new JTextField();
		tidTilField.setColumns(6);
		c.gridwidth=1;
		c.ipadx=0; c.ipady=0;
		c.gridx=4; c.gridy=1;
		add(tidTilField, c);
		
		
		//Dato
		datoLabel = new JLabel("Dato:");
		c.gridwidth=1;
		c.ipadx=10; c.ipady=10;
		c.gridx=0; c.gridy=2;
		c.anchor = GridBagConstraints.WEST;
		add(datoLabel, c);
		datoField = new JTextField();
		datoField.setColumns(6);
		c.gridwidth=2;
		c.ipadx=0; c.ipady=0;
		c.gridx=1; c.gridy=2;
		add(datoField, c);
		
		//Beskrivelse
		beskrivelseLabel = new JLabel("Beskrivelse: ");
		c.gridwidth=1;
		c.ipadx=10; c.ipady=10;
		c.gridx=0; c.gridy=3;
		c.anchor = GridBagConstraints.WEST;
		add(beskrivelseLabel, c);
		beskrivelseField = new JTextArea();
		beskrivelseField.setRows(3);
		beskrivelseField.setColumns(20);
		beskrivelseField.setWrapStyleWord(true);
		beskrivelseField.setLineWrap(true);
		JScrollPane beskrivelsescroll = new JScrollPane(beskrivelseField);
		
		c.gridwidth=4;
		c.gridx=1; c.gridy=3;
		c.anchor = GridBagConstraints.WEST;
		add(beskrivelsescroll, c);

		//Deltagere
		deltagerLabel = new JLabel("Deltagere:");
		c.gridwidth=1;
		c.ipadx=10; c.ipady=10;
		c.gridx=0; c.gridy=4;
		c.anchor = GridBagConstraints.WEST;
		add(deltagerLabel, c);
		deltagerField = new JTextArea();
		deltagerField.setRows(3);
		deltagerField.setColumns(20);
		deltagerField.setWrapStyleWord(true);
		deltagerField.setLineWrap(true);
		JScrollPane deltagerscroll = new JScrollPane(deltagerField);
		
		c.gridwidth=4;
		c.gridx=1; c.gridy=4;
		c.anchor = GridBagConstraints.WEST;
		add(deltagerscroll, c);
		
		
		//Endre/Lagre
		endrelagre = new JButton("Endre/Lagre");
		c.gridx=0; c.gridy=5;
		c.gridwidth=2;
		add(endrelagre, c);
		//Legge Til/Slette deltagere
		leggetilslett = new JButton("Deltagere");
		c.gridx=2; c.gridy=5;
		c.gridwidth=2;
		add(leggetilslett, c);
		avlys = new JButton("Avlys");
		c.gridwidth=2;
		c.gridx=4; c.gridy=5;
		add(avlys, c);
		
	}
}
