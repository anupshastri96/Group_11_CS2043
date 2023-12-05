import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestGUI {
    private static int uniqueId = 0;
    private static ArrayList<Event> eventList = new ArrayList<>();
    private static Stadium stadium = new Stadium(1,"Roger's Centre", "Toronto", 100);

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

// Assume your Event and eventList are properly defined

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
        // Assuming you have database credentials and connection URL
        String url = "jdbc:mysql://localhost:3306/cs2043";
        String username = "ahadikoe";
        String password = "Potatogun_0811307730";

        int uniqueId = eventList.size() + 1; // Generate unique ID
        String name = nameField.getText();
        String date = dateField.getText();
        String desc = descField.getText();
        double price = Double.parseDouble(priceField.getText());

        // Add event to eventList
        Event newEvent = new Event(uniqueId, name, date, desc, price);
        eventList.add(newEvent);

        // Add event to the database
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String insertQuery = "INSERT INTO events (eventId, eventName, eventDate, eventDesc, eventPrice) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, uniqueId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, date);
            preparedStatement.setString(4, desc);
            preparedStatement.setDouble(5, price);

            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(createEventFrame, "Event created successfully!");
            createEventFrame.setVisible(false);
        } catch (SQLException ex) {
            // Handle database exceptions (e.g., connection error, SQL error)
            ex.printStackTrace();
            JOptionPane.showMessageDialog(createEventFrame, "Error creating event!", "Error", JOptionPane.ERROR_MESSAGE);
        }
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

        // Assuming eventManager, stadium, and database connection are properly instantiated
        ManageEvent eventManager = new ManageEvent(); // Create ManageEvent instance

        boolean added = false;

        for (Event event : eventList) {
            if (event.getName().equals(name)) {
                // Add event to the stadium in the database
                added = eventManager.addEvent(event, stadium);

                if (added) {
                    // Update stadium record in the database with the added event
                    updateStadiumInDatabase(stadium, event);
                    JOptionPane.showMessageDialog(addEventFrame, "Event added to stadium successfully!");
                } else {
                    JOptionPane.showMessageDialog(addEventFrame, "Error! Nothing was added.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
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

// Method to update stadium record in the database with the added event
private static void updateStadiumInDatabase(Stadium stadium, Event event) {
    // Your database connection details
    String url = "jdbc:mysql://localhost:3306/your_database";
    String username = "your_username";
    String password = "your_password";

    try (Connection connection = DriverManager.getConnection(url, username, password)) {
        String updateQuery = "UPDATE stadiums SET event_id = ? WHERE stadium_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

        // Assuming stadium object has methods to get event ID and stadium ID
        preparedStatement.setInt(1, event.getEventId());
        preparedStatement.setInt(2, stadium.getStadiumId());

        preparedStatement.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Handle database exceptions
    }
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

        // Your database connection details
        String url = "jdbc:mysql://localhost:3306/your_database";
        String username = "your_username";
        String password = "your_password";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Update query to edit event name based on the event name
            String updateQuery = "UPDATE events SET event_name = ? WHERE event_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, name);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(editEventNameFrame, "Event name updated successfully!");
                eventFound = true;
            } else {
                JOptionPane.showMessageDialog(editEventNameFrame, "Event not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle database exceptions
            JOptionPane.showMessageDialog(editEventNameFrame, "Error updating event name.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (eventFound) {
            // Update the event name in eventList if the event was found and updated in the database
            for (Event event : eventList) {
                if (event.getName().equals(name)) {
                    event.setName(newName);
                    break;
                }
            }
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

        // Your database connection details
        String url = "jdbc:mysql://localhost:3306/your_database";
        String username = "your_username";
        String password = "your_password";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Update query to edit event description based on the event name
            String updateQuery = "UPDATE events SET event_description = ? WHERE event_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

            preparedStatement.setString(1, newDescription);
            preparedStatement.setString(2, name);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(editEventDescriptionFrame, "Event description updated successfully!");
                eventFound = true;
            } else {
                JOptionPane.showMessageDialog(editEventDescriptionFrame, "Event not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle database exceptions
            JOptionPane.showMessageDialog(editEventDescriptionFrame, "Error updating event description.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (eventFound) {
            // Update the event description in eventList if the event was found and updated in the database
            for (Event event : eventList) {
                if (event.getName().equals(name)) {
                    event.setDescription(newDescription);
                    break;
                }
            }
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

        // Your database connection details
        String url = "jdbc:mysql://localhost:3306/your_database";
        String username = "your_username";
        String password = "your_password";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Update query to edit event price based on the event name
            String updateQuery = "UPDATE events SET event_price = ? WHERE event_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

            preparedStatement.setDouble(1, newPrice);
            preparedStatement.setString(2, name);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(editEventPriceFrame, "Event price updated successfully!");
                eventFound = true;
            } else {
                JOptionPane.showMessageDialog(editEventPriceFrame, "Event not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle database exceptions
            JOptionPane.showMessageDialog(editEventPriceFrame, "Error updating event price.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (eventFound) {
            // Update the event price in eventList if the event was found and updated in the database
            for (Event event : eventList) {
                if (event.getName().equals(name)) {
                    event.setPrice(newPrice);
                    break;
                }
            }
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

        // Your database connection details
        String url = "jdbc:mysql://localhost:3306/your_database";
        String username = "your_username";
        String password = "your_password";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Delete query to remove event from the database based on the event name
            String deleteQuery = "DELETE FROM events WHERE event_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);

            preparedStatement.setString(1, name);

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                eventRemoved = true;
                JOptionPane.showMessageDialog(removeEventFrame, "Event removed from stadium successfully!");
            } else {
                JOptionPane.showMessageDialog(removeEventFrame, "Event not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle database exceptions
            JOptionPane.showMessageDialog(removeEventFrame, "Error removing event from stadium.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (eventRemoved) {
            // Remove the event from eventList if the event was successfully removed from the database
            eventList.removeIf(event -> event.getName().equals(name));
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
		ManageTickets ticketManager = new ManageTickets();
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
								ticketManager.buyTickets(event, input);
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
		ManageTickets ticketManager = new ManageTickets();
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
					int ticketsSold = event.getSoldTickets();
					double pricePerTicket = event.getPrice();

					resultTextArea.setText("Sold Tickets: " + ticketsSold + "\n");
					resultTextArea.append("Price per ticket: " + pricePerTicket + "\n");

					String inputStr = JOptionPane.showInputDialog(refundTicketsFrame, "Enter the number of tickets you would like to refund:");
					if (inputStr != null) {
						try {
							int input = Integer.parseInt(inputStr);
							if (input > 0 && input <= ticketsSold) {
								ticketManager.refundTickets(event, input);
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
	
}
