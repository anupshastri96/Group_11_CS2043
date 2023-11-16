/**
 * TestGUI class represents a GUI application for managing events and stadiums.
 * Contributors: Albert Koesoema
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TestGUI {
    private static int uniqueId = 0;
    private static ArrayList<Event> eventList = new ArrayList<>();
    private static Stadium stadium = new Stadium("Roger's Centre", "Toronto", 100);

    public static void main(String[] args) {
        JFrame frame = new JFrame("Event Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 800);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 1));

        JLabel label = new JLabel("Select the following options:");
        panel.add(label);

        JButton createEventButton = new JButton("Create an Event");
        createEventButton.addActionListener(e -> createEvent());
        panel.add(createEventButton);

        JButton addEventToStadiumButton = new JButton("Add an event to a stadium");
        addEventToStadiumButton.addActionListener(e -> addEventToStadium());
        panel.add(addEventToStadiumButton);
		
		JButton editButton = new JButton("Edit an existing event");
        editButton.addActionListener(e -> editEvent());
        panel.add(editButton);
		
		JButton removeButton = new JButton("Remove an existing event");
        removeButton.addActionListener(e -> removeEventFromStadium());
        panel.add(removeButton);
		
		JButton displayStadiumButton = new JButton("Display all events in the stadium");
        displayStadiumButton.addActionListener(e -> displayEventsInStadium());
        panel.add(displayStadiumButton);
		
		JButton displayEventButton = new JButton("Display all existing events");
        displayEventButton.addActionListener(e -> displayAllCreatedEvents());
        panel.add(displayEventButton);
		
		JButton purchaseTicketsButton = new JButton("Purchase Tickets for an event");
        purchaseTicketsButton.addActionListener(e -> purchaseEventTickets());
        panel.add(purchaseTicketsButton);

		JButton refundTicketsButton = new JButton("Refund Tickets for an event");
        refundTicketsButton.addActionListener(e -> refundEventTickets());
        panel.add(refundTicketsButton);
        

        frame.add(panel);
		frame.setLocationRelativeTo(null);
        frame.setVisible(true);
		
    }

    private static void createEvent() {
		JFrame createEventFrame = new JFrame("Create Event");
		createEventFrame.setSize(400, 300);

		JPanel createEventPanel = new JPanel();
		createEventPanel.setLayout(new GridLayout(6, 1));

		JLabel nameLabel = new JLabel("Name of the event:");
		JTextField nameField = new JTextField();

		JLabel dateLabel = new JLabel("Date of the event:");
		JTextField dateField = new JTextField();

		JLabel descLabel = new JLabel("Description of the event:");
		JTextField descField = new JTextField();

		JLabel priceLabel = new JLabel("Price of the event's ticket:");
		JTextField priceField = new JTextField();

		JButton createButton = new JButton("Create Event");

		createButton.addActionListener(e -> {
			int uniqueId = eventList.size() + 1; // Generate unique ID
			String name = nameField.getText();
			String date = dateField.getText();
			String desc = descField.getText();
			double price = Double.parseDouble(priceField.getText());

			eventList.add(new Event(uniqueId, name, date, desc, price));
			JOptionPane.showMessageDialog(createEventFrame, "Event created successfully!");
			createEventFrame.setVisible(false);
		});

		createEventPanel.add(nameLabel);
		createEventPanel.add(nameField);
		createEventPanel.add(dateLabel);
		createEventPanel.add(dateField);
		createEventPanel.add(descLabel);
		createEventPanel.add(descField);
		createEventPanel.add(priceLabel);
		createEventPanel.add(priceField);
		createEventPanel.add(createButton);

		createEventFrame.add(createEventPanel);
		createEventFrame.setLocationRelativeTo(null);
		createEventFrame.setVisible(true);
	}

	
	private static void addEventToStadium() {
		JFrame addEventFrame = new JFrame("Add Event to Stadium");
		addEventFrame.setSize(300, 150);

		JPanel addEventPanel = new JPanel();
		addEventPanel.setLayout(new GridLayout(3, 1));

		JLabel enterEventLabel = new JLabel("Which event would you like to add?");
		JTextField nameField = new JTextField();

		JButton addButton = new JButton("Add Event");
		addButton.addActionListener(e -> {
			String name = nameField.getText();
			boolean Added = true;

			for (Event event : eventList) {
				if (event.getName().equals(name)) {
					Added = stadium.addEvent(event);
					break;
				}
			}

			if (Added) {
				JOptionPane.showMessageDialog(addEventFrame, "Event added to stadium successfully!");
			}
			else{
				JOptionPane.showMessageDialog(addEventFrame, "Error! Nothing was added.", "Error", JOptionPane.ERROR_MESSAGE);
			}

			addEventFrame.dispose(); // Close the frame after adding the event
		});

		addEventPanel.add(enterEventLabel);
		addEventPanel.add(nameField);
		addEventPanel.add(addButton);

		addEventFrame.add(addEventPanel);
		addEventFrame.setLocationRelativeTo(null);
		addEventFrame.setVisible(true);
	}
	
	private static void editEvent() {
		JFrame editEventFrame = new JFrame("Edit Event");
		editEventFrame.setSize(350, 200);

		JPanel editEventPanel = new JPanel();
		editEventPanel.setLayout(new GridLayout(1, 3));

		JButton nameButton = new JButton("Edit Name");
		nameButton.addActionListener(e -> editEventName());
		JButton descButton = new JButton("Edit Description");
		descButton.addActionListener(e -> editEventDescription());
		JButton priceButton = new JButton("Edit Price");
		priceButton.addActionListener(e -> editEventPrice());

		editEventPanel.add(nameButton);
		editEventPanel.add(descButton);
		editEventPanel.add(priceButton);

		editEventFrame.add(editEventPanel);
		editEventFrame.setLocationRelativeTo(null);
		editEventFrame.setVisible(true);
	}
	
	private static void editEventName() {
		JFrame editEventNameFrame = new JFrame("Edit Event Name");
		editEventNameFrame.setSize(600, 200);

		JPanel editEventNamePanel = new JPanel();
		editEventNamePanel.setLayout(new GridLayout(3, 1));

		JLabel nameLabel = new JLabel("Enter the name of the event you want to edit:");
		JTextField eventNameField = new JTextField();

		JLabel newNameLabel = new JLabel("Enter the new name of the event:");
		JTextField newEventNameField = new JTextField();

		JButton editButton = new JButton("Edit Name");

		editButton.addActionListener(e -> {
			String name = eventNameField.getText();
			String newName = newEventNameField.getText();
			boolean eventFound = false;

			for (Event event : eventList) {
				if (event.getName().equals(name)) {
					event.setName(newName);
					JOptionPane.showMessageDialog(editEventNameFrame, "Event name updated successfully!");
					eventFound = true;
					break;
				}
			}

			if (!eventFound) {
				JOptionPane.showMessageDialog(editEventNameFrame, "Event not found!", "Error", JOptionPane.ERROR_MESSAGE);
			}

			editEventNameFrame.dispose(); // Close the frame after editing the event name
		});

		editEventNamePanel.add(nameLabel);
		editEventNamePanel.add(eventNameField);
		editEventNamePanel.add(newNameLabel);
		editEventNamePanel.add(newEventNameField);
		editEventNamePanel.add(editButton);

		editEventNameFrame.add(editEventNamePanel);
		editEventNameFrame.setLocationRelativeTo(null);
		editEventNameFrame.setVisible(true);
	}
	
	private static void editEventDescription() {
		JFrame editEventDescriptionFrame = new JFrame("Edit Event Description");
		editEventDescriptionFrame.setSize(600, 200);

		JPanel editEventDescriptionPanel = new JPanel();
		editEventDescriptionPanel.setLayout(new GridLayout(3, 1));

		JLabel nameLabel = new JLabel("Enter the name of the event you want to edit:");
		JTextField eventNameField = new JTextField();

		JLabel newDescLabel = new JLabel("Enter the new description of the event:");
		JTextField newDescField = new JTextField();

		JButton editButton = new JButton("Edit Description");

		editButton.addActionListener(e -> {
			String name = eventNameField.getText();
			String newDescription = newDescField.getText();
			boolean eventFound = false;

			for (Event event : eventList) {
				if (event.getName().equals(name)) {
					event.setDescription(newDescription);
					JOptionPane.showMessageDialog(editEventDescriptionFrame, "Event description updated successfully!");
					eventFound = true;
					break;
				}
			}

			if (!eventFound) {
				JOptionPane.showMessageDialog(editEventDescriptionFrame, "Event not found!", "Error", JOptionPane.ERROR_MESSAGE);
			}

			editEventDescriptionFrame.dispose(); // Close the frame after editing the event description
		});

		editEventDescriptionPanel.add(nameLabel);
		editEventDescriptionPanel.add(eventNameField);
		editEventDescriptionPanel.add(newDescLabel);
		editEventDescriptionPanel.add(newDescField);
		editEventDescriptionPanel.add(editButton);

		editEventDescriptionFrame.add(editEventDescriptionPanel);
		editEventDescriptionFrame.setLocationRelativeTo(null);
		editEventDescriptionFrame.setVisible(true);
	}
	
	private static void editEventPrice() {
		JFrame editEventPriceFrame = new JFrame("Edit Event Price");
		editEventPriceFrame.setSize(600, 200);

		JPanel editEventPricePanel = new JPanel();
		editEventPricePanel.setLayout(new GridLayout(3, 1));

		JLabel nameLabel = new JLabel("Enter the name of the event you want to edit:");
		JTextField eventNameField = new JTextField();

		JLabel newPriceLabel = new JLabel("Enter the new price of the event:");
		JTextField newPriceField = new JTextField();

		JButton editButton = new JButton("Edit Price");

		editButton.addActionListener(e -> {
			String name = eventNameField.getText();
			double newPrice = Double.parseDouble(newPriceField.getText());
			boolean eventFound = false;

			for (Event event : eventList) {
				if (event.getName().equals(name)) {
					event.setPrice(newPrice);
					JOptionPane.showMessageDialog(editEventPriceFrame, "Event price updated successfully!");
					eventFound = true;
					break;
				}
			}

			if (!eventFound) {
				JOptionPane.showMessageDialog(editEventPriceFrame, "Event not found!", "Error", JOptionPane.ERROR_MESSAGE);
			}

			editEventPriceFrame.dispose(); // Close the frame after editing the event price
		});

		editEventPricePanel.add(nameLabel);
		editEventPricePanel.add(eventNameField);
		editEventPricePanel.add(newPriceLabel);
		editEventPricePanel.add(newPriceField);
		editEventPricePanel.add(editButton);

		editEventPriceFrame.add(editEventPricePanel);
		editEventPriceFrame.setLocationRelativeTo(null);
		editEventPriceFrame.setVisible(true);
	}

	private static void removeEventFromStadium() {
		JFrame removeEventFrame = new JFrame("Remove Event from Stadium");
		removeEventFrame.setSize(300, 150);

		JPanel removeEventPanel = new JPanel();
		removeEventPanel.setLayout(new GridLayout(2, 1));

		JLabel enterNameLabel = new JLabel("Insert the name of the event you want to remove:");
		JTextField nameField = new JTextField();

		JButton removeButton = new JButton("Remove Event");
		removeButton.addActionListener(e -> {
			String name = nameField.getText();
			boolean eventRemoved = false;

			for (Event event : eventList) {
				if (event.getName().equals(name)) {
					stadium.removeEvent(event);
					eventRemoved = true;
					JOptionPane.showMessageDialog(removeEventFrame, "Event removed from stadium successfully!");
					break;
				}
			}
			
			int indexToRemove = -1;
			for (int i = 0; i < eventList.size(); i++) {
				Event existingEvent = eventList.get(i);
				if (existingEvent.getName().equals(name)) {
					indexToRemove = i;
					break;
				}
			}

			if (indexToRemove != -1) {
				eventList.remove(indexToRemove);
			}

			if (!eventRemoved) {
				JOptionPane.showMessageDialog(removeEventFrame, "Event not found!", "Error", JOptionPane.ERROR_MESSAGE);
			}

			removeEventFrame.dispose(); // Close the frame after removing the event
		});

		removeEventPanel.add(enterNameLabel);
		removeEventPanel.add(nameField);
		removeEventPanel.add(removeButton);

		removeEventFrame.add(removeEventPanel);
		removeEventFrame.setLocationRelativeTo(null);
		removeEventFrame.setVisible(true);
	}
	
	private static void displayEventsInStadium() {
		JFrame displayEventsFrame = new JFrame("Events in Stadium");
		displayEventsFrame.setSize(400, 300);

		JPanel displayEventsPanel = new JPanel();
		displayEventsPanel.setLayout(new BorderLayout());

		JTextArea eventsTextArea = new JTextArea();
		eventsTextArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(eventsTextArea);

		StringBuilder displayText = new StringBuilder();
		displayText.append("Displaying Events in the stadium:\n");
		displayText.append(stadium.toString());
		eventsTextArea.setText(displayText.toString());

		displayEventsPanel.add(scrollPane, BorderLayout.CENTER);

		displayEventsFrame.add(displayEventsPanel);
		displayEventsFrame.setLocationRelativeTo(null);
		displayEventsFrame.setVisible(true);
	}
	
	private static void displayAllCreatedEvents() {
		JFrame displayAllEventsFrame = new JFrame("All Created Events");
		displayAllEventsFrame.setSize(400, 300);

		JPanel displayAllEventsPanel = new JPanel();
		displayAllEventsPanel.setLayout(new BorderLayout());

		JTextArea eventsTextArea = new JTextArea();
		eventsTextArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(eventsTextArea);

		
		StringBuilder displayText = new StringBuilder();
		displayText.append("Displaying all created events:\n");
		for (Event event : eventList) {
			displayText.append(event.toString()).append("\n\n");
		}
		eventsTextArea.setText(displayText.toString());

		displayAllEventsPanel.add(scrollPane, BorderLayout.CENTER);

		displayAllEventsFrame.add(displayAllEventsPanel);
		displayAllEventsFrame.setLocationRelativeTo(null);
		displayAllEventsFrame.setVisible(true);
	}
	
	private static void purchaseEventTickets() {
		JFrame purchaseTicketsFrame = new JFrame("Purchase Event Tickets");
		purchaseTicketsFrame.setSize(400, 400);

		JPanel purchaseTicketsPanel = new JPanel();
		purchaseTicketsPanel.setLayout(new GridLayout(4, 1));

		JLabel nameLabel = new JLabel("Insert the name of the event for the ticket you want to purchase:");
		JTextField eventNameField = new JTextField();

		JButton purchaseButton = new JButton("Purchase Tickets");

		JTextArea resultTextArea = new JTextArea();
		resultTextArea.setEditable(false);

		purchaseButton.addActionListener(e -> {
			String name = eventNameField.getText();
			boolean eventFound = false;

			for (Event event : eventList) {
				if (event.getName().equals(name)) {
					eventFound = true;
					int ticketsAvailable = event.getTickets();
					double pricePerTicket = event.getPrice();

					resultTextArea.setText("Tickets Available: " + ticketsAvailable + "\n");
					resultTextArea.append("Price per ticket: " + pricePerTicket + "\n");

					String inputStr = JOptionPane.showInputDialog(purchaseTicketsFrame, "Enter the number of tickets you would like to purchase:");
					if (inputStr != null) {
						try {
							int input = Integer.parseInt(inputStr);
							if (input > 0 && input <= ticketsAvailable) {
								event.buyTickets(input);
								resultTextArea.append("Tickets purchased successfully!");
							} else {
								resultTextArea.append("Invalid number of tickets.");
							}
						} catch (NumberFormatException ex) {
							resultTextArea.append("Invalid input for number of tickets.");
						}
					}
					break;
				}
			}

			if (!eventFound) {
				resultTextArea.setText("Event not found!");
			}
		});

		purchaseTicketsPanel.add(nameLabel);
		purchaseTicketsPanel.add(eventNameField);
		purchaseTicketsPanel.add(purchaseButton);
		purchaseTicketsPanel.add(resultTextArea);

		purchaseTicketsFrame.add(purchaseTicketsPanel);
		purchaseTicketsFrame.setLocationRelativeTo(null);
		purchaseTicketsFrame.setVisible(true);
	}

	private static void refundEventTickets() {
		JFrame refundTicketsFrame = new JFrame("Refund Event Tickets");
		refundTicketsFrame.setSize(400, 400);

		JPanel refundTicketsPanel = new JPanel();
		refundTicketsPanel.setLayout(new GridLayout(4, 1));

		JLabel nameLabel = new JLabel("Insert the name of the event for the ticket you want to refund:");
		JTextField eventNameField = new JTextField();

		JButton refundButton = new JButton("Refund Tickets");

		JTextArea resultTextArea = new JTextArea();
		resultTextArea.setEditable(false);

		refundButton.addActionListener(e -> {
			String name = eventNameField.getText();
			boolean eventFound = false;

			for (Event event : eventList) {
				if (event.getName().equals(name)) {
					eventFound = true;
					int ticketsAvailable = event.getTickets();
					double pricePerTicket = event.getPrice();

					resultTextArea.setText("Tickets Available: " + ticketsAvailable + "\n");
					resultTextArea.append("Price per ticket: " + pricePerTicket + "\n");

					String inputStr = JOptionPane.showInputDialog(refundTicketsFrame, "Enter the number of tickets you would like to refund:");
					if (inputStr != null) {
						try {
							int input = Integer.parseInt(inputStr);
							if (input > 0 && input <= ticketsAvailable) {
								event.refundTickets(input);
								resultTextArea.append("Tickets refunded successfully!");
							} else {
								resultTextArea.append("Invalid number of tickets.");
							}
						} catch (NumberFormatException ex) {
							resultTextArea.append("Invalid input for number of tickets.");
						}
					}
					break;
				}
			}

			if (!eventFound) {
				resultTextArea.setText("Event not found!");
			}
		});

		refundTicketsPanel.add(nameLabel);
		refundTicketsPanel.add(eventNameField);
		refundTicketsPanel.add(refundButton);
		refundTicketsPanel.add(resultTextArea);

		refundTicketsFrame.add(refundTicketsPanel);
		refundTicketsFrame.setLocationRelativeTo(null);
		refundTicketsFrame.setVisible(true);
	}
	
} // end TestGUI
