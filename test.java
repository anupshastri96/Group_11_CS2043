import java.util.Scanner;
import java.util.ArrayList;

public class test{
	public static void main(String[] args){
		int uniqueId = 0, input, editIn;
		String name, date, desc;
		double price;
		ArrayList<Event> eventList = new ArrayList<>();
		Stadium stadium = new Stadium("Roger's Centre","Toronto",100);
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Select the following options:");
		System.out.println("(1) Create an Event");
		System.out.println("(2) Add an event to a stadium");
		System.out.println("(3) Edit an existing event");
		System.out.println("(4) Remove an event from a stadium");
		System.out.println("(5) Display all events in a stadium");
		System.out.println("(6) Display all created events");
		System.out.println("(7) Buy Tickets");
		System.out.println("(8) Refund Tickets");
		System.out.println("(9) Exit the application");
		
		System.out.print("Enter 1-9: ");
		input = scanner.nextInt();
		System.out.println("==================================\n");
		
		while (input != 9){
			scanner.nextLine(); //buffer
			if (input == 1){ //Create event
				uniqueId++;
				System.out.print("Insert the name of the event: ");
				name = scanner.nextLine();
				System.out.print("Insert the date of the event: ");
				date = scanner.nextLine();
				System.out.print("Insert a one line description for the event: ");
				desc = scanner.nextLine();
				System.out.print("Insert the price of the event's ticket: ");
				price = scanner.nextDouble();
				
				eventList.add(new Event(uniqueId, name, date, desc, price));
			}
			else if (input == 2){ //Add event to stadium
				System.out.println("Which event would you like to add?");
				System.out.print("Enter the name of the event: ");
				name = scanner.nextLine();
				boolean notAdded = true;
				for (Event event : eventList) {
					if (event.getName().equals(name)){
						stadium.addEvent(event);
						notAdded = false;
					}
				}
				if (notAdded)
					System.out.println("Event name inserted is invalid! Nothing was added");
			}
			else if (input == 3){ //Edit event
				System.out.println("Select the following to edit:");
				System.out.println("(1) Name");
				System.out.println("(2) Description");
				System.out.println("(3) Price");
				
				System.out.print("Enter 1-3: ");
				editIn = scanner.nextInt();
				if (editIn == 1){
					scanner.nextLine();
					System.out.print("Insert the name of the event you want to edit: ");
					name = scanner.nextLine();
					for (Event event : eventList) {
					if (event.getName().equals(name)){
						System.out.print("Insert the new name of the event: ");
						name = scanner.nextLine();
						event.setName(name);
						}
					}
				}
				else if (editIn == 2){
					scanner.nextLine();
					System.out.print("Insert the name of the event you want to edit: ");
					name = scanner.nextLine();
					for (Event event : eventList) {
					if (event.getName().equals(name)){
						System.out.print("Insert the new description of the event: ");
						desc = scanner.nextLine();
						event.setDescription(desc);
						}
					}
				}
				else if (editIn == 3){
					scanner.nextLine();
					System.out.print("Insert the name of the event you want to edit: ");
					name = scanner.nextLine();
					for (Event event : eventList) {
					if (event.getName().equals(name)){
						System.out.print("Insert the new price of the event: ");
						price = scanner.nextDouble();
						event.setPrice(price);
						}
					}
				}
				else {
					System.out.println("Invalid input!");
				}
			}
			else if (input == 4){ //Remove event
				System.out.print("Insert the name of the event you want to remove: ");
				name = scanner.nextLine();
				for (Event event : eventList) {
					if (event.getName().equals(name)){
						stadium.removeEvent(event);
					}
				}
			}
			else if (input == 5){ //Display events
				System.out.println("Displaying Events in the stadium:");
				System.out.println(stadium.toString());
			}
			else if (input == 6){
				System.out.println("Displaying all created events: ");
				for (Event event : eventList) {
					System.out.println(event);
				}
			}
			else if (input == 7){
				System.out.print("Insert the name of the event for the ticket you want to purchase: ");
				name = scanner.nextLine();
				for (Event event : eventList) {
				if (event.getName().equals(name)){
					System.out.println("Tickets Available: " + event.getTickets());
					System.out.println("Price per ticket: " + event.getPrice());
					System.out.print("Enter the number of tickets you would like to purchase: ");
					input = scanner.nextInt();
					event.buyTickets(input);
					}
				}
			}
			else if (input == 8){
				System.out.print("Insert the name of the event for the ticket you want to refund: ");
				name = scanner.nextLine();
				for (Event event : eventList) {
				if (event.getName().equals(name)){
					System.out.println("Tickets Available: " + event.getTickets());
					System.out.println("Price per ticket: " + event.getPrice());
					System.out.print("Enter the number of tickets you would like to refund: ");
					input = scanner.nextInt();
					event.refundTickets(input);
					}
				}
			}
				
			else { //Invalid
				System.out.println("Invalid Input");
			}
			
			System.out.println("\nSelect the following options:");
			System.out.println("(1) Create an Event");
			System.out.println("(2) Add an event to a stadium");
			System.out.println("(3) Edit an existing event");
			System.out.println("(4) Remove an event from a stadium");
			System.out.println("(5) Display all events in a stadium");
			System.out.println("(6) Display all created events");
			System.out.println("(7) Buy Tickets");
			System.out.println("(8) Refund Tickets");
			System.out.println("(9) Exit the application");
			
			System.out.print("Enter 1-9: ");
			input = scanner.nextInt();
			System.out.println("==================================\n");
		}
	}
}