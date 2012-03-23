package no.ntnu.fp.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import no.ntnu.fp.model.Event;

/**
 * 
 * @author Øyvind M
 * 
 */
public class MeetingForm extends JPanel implements PropertyChangeListener,
		PlaceRoomListener, DocumentListener {

	private JLabel name;
	private JTextField nameField;

	private PlaceRoomChooserPanel place;

	private JLabel time;

	private JLabel from;
	private JTextField fromField;

	private JLabel to;
	private JTextField toField;

	private JLabel date;
	private JTextField dateField;

	private JLabel description;
	private JTextArea descriptionArea;
	private JScrollPane descriptionScroll;

	private JLabel participants;
	private JTextArea participantsArea;
	private JScrollPane participantScroll;

	private JButton addParticipant;
	private JButton saveMeeting;
	private JButton cancel;

	private Event model;

	boolean locked = false;

	private void unlock() {
		locked = false;
	}

	private void lock() {
		locked = true;
	}

	public MeetingForm() {

		setBorder(new EmptyBorder(10, 10, 10, 10));

		name = new JLabel("Name: ");
		nameField = new JTextField("", 20);

		place = new PlaceRoomChooserPanel();

		time = new JLabel("Time: ");

		from = new JLabel("From: ");
		fromField = new JTextField("", 6);

		to = new JLabel("To: ");
		toField = new JTextField("", 6);

		date = new JLabel("Date:");
		dateField = new JTextField("", 10);

		description = new JLabel("Description:");
		descriptionArea = new JTextArea();
		descriptionScroll = new JScrollPane(descriptionArea);
		descriptionScroll.setPreferredSize(new Dimension(200, 100));
		descriptionArea.setWrapStyleWord(true);
		descriptionArea.setLineWrap(true);

		participants = new JLabel("Participants: ");
		participantsArea = new JTextArea();
		participantScroll = new JScrollPane(participantsArea);
		participantScroll.setPreferredSize(new Dimension(200, 100));
		participantsArea.setWrapStyleWord(true);
		participantsArea.setLineWrap(true);

		addParticipant = new JButton("Add participant");
		addParticipant.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame addparticipantframe = new JFrame();
				addparticipantframe.add(new ChooseParticipantPanel());
				addparticipantframe.setVisible(true);
				addparticipantframe.pack();

			}
		});

		saveMeeting = new JButton("Save");

		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				getParent().getParent().getParent().getParent()
						.setVisible(false);

			}
		});

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.WEST;
		c.gridwidth = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(name, c);

		c.gridwidth = 5;
		c.ipadx = 10;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(place, c);

		c.gridwidth = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		add(time, c);

		c.gridwidth = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		add(from, c);

		c.gridwidth = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 3;
		c.gridy = 2;
		add(to, c);

		c.gridwidth = 1;
		c.ipadx = 0;
		c.ipady = 20;
		c.gridx = 0;
		c.gridy = 3;
		add(date, c);

		c.gridwidth = 1;
		c.ipadx = 10;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 4;
		add(description, c);

		c.gridwidth = 4;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 1;
		c.gridy = 4;
		add(descriptionScroll, c);

		c.gridwidth = 1;
		c.ipadx = 35;
		c.ipady = 100;
		c.gridx = 0;
		c.gridy = 5;
		add(participants, c);

		c.gridwidth = 4;
		c.ipadx = 10;
		c.ipady = 0;
		c.gridx = 1;
		c.gridy = 5;
		add(participantScroll, c);

		c.gridwidth = 4;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 1;
		c.gridy = 0;
		add(nameField, c);

		c.gridwidth = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 2;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		add(fromField, c);

		c.gridwidth = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 4;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		add(toField, c);

		c.gridwidth = 4;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 1;
		c.gridy = 3;
		add(dateField, c);

		c.gridwidth = 2;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 1;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.EAST;
		add(addParticipant, c);

		c.gridwidth = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 3;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.EAST;
		add(saveMeeting, c);

		c.gridwidth = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 4;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.EAST;
		add(cancel, c);

	}

	public void setModel(Event model) {
		if (model != null)
			model.removePropertyChangedListener(this);
		this.model = model;
		model.addPropertyChangedListener(this);

		fromField.setText(model.getFromText());
		toField.setText(model.getToText());
		dateField.setText(model.getDateText());
		nameField.setText(model.getEventname());
		descriptionArea.setText(model.getEventdescription());
		place.setPlaceRoom(model.getPlace(), model.getRoom());

		// Change listeners
		fromField.getDocument().addDocumentListener(this);
		toField.getDocument().addDocumentListener(this);
		dateField.getDocument().addDocumentListener(this);
		nameField.getDocument().addDocumentListener(this);
		descriptionArea.getDocument().addDocumentListener(this);
		place.addPlaceRoomSelectListener(this);
	}

	public Event getModel() {
		return model;
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new MeetingForm());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();

	}

	public static void editMeeting(Event event) {
		JFrame frame = new JFrame("Edit event");
		MeetingForm mf = new MeetingForm();
		mf.setModel(event);
		frame.add(mf);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (!locked) {
			if (evt.getPropertyName() == "eventname") {
				if (nameField.getText() != evt.getNewValue()) {
					nameField.setText((String) evt.getNewValue());
				}
			}
			if (evt.getPropertyName() == "eventdescription") {
				if (descriptionArea.getText() != evt.getNewValue()) {
					descriptionArea.setText((String) evt.getNewValue());
				}
			}
			// TODO: Add more
		}
	}

	@Override
	public void selectPlaceOrRoom(PlaceRoomSelectEvent e) {
		model.setRoom(e.getRoom());
		model.setPlace(e.getPlace());
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		lock();
		if (e.getDocument() == nameField.getDocument()) {
			model.setEventname(nameField.getText());
		}
		if (e.getDocument() == descriptionArea.getDocument()) {
			model.setEventdescription(descriptionArea.getText());
		}
		if (e.getDocument() == fromField.getDocument()) {
			model.setFromText(fromField.getText());
		}
		if (e.getDocument() == toField.getDocument()) {
			model.setToText(toField.getText());
		}
		if (e.getDocument() == dateField.getDocument()) {
			model.setDateText(dateField.getText());
		}
		// TODO: Add more
		unlock();
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		changedUpdate(arg0);
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		changedUpdate(arg0);
	}

}
