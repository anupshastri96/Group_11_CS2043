public class Event{
	private String name, date, description;
	private double price;
	private final int EVENT_ID;
	private int tickets, soldTickets;
	private boolean availableTickets;
		
	public Event(int eventId, String name, String date, String description, double price){
		this.name = name;
		this.date = date;
		this.description = description;
		this.price = price;
		EVENT_ID = eventId;
		tickets = 0;
		soldTickets =0;
		availableTickets = false;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDate(){
		return date;
	}
	
	public String getDescription(){
		return description;
	}
	
	public double getPrice(){
		return price;
	}
	
	public int getEventId(){
		return EVENT_ID;
	}
	
	public int getTickets(){
		return tickets;
	}
	
	public int getSoldTickets(){
		return soldTickets;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public void setPrice(double price){
		this.price = price;
	}
	
	public void setTickets(int ticketsIn){
		tickets = ticketsIn;
	}
	
	public void setSoldTickets(int soldTicketsIn){
		soldTickets = soldTicketsIn;
	}
	
	public boolean checkAvailability(){
		return tickets !=0;
	}
	
	public void buyTickets(int ticketsIn){
		if(tickets == 0){
			System.out.println("Error! Event is not registered to a Stadium");
		}
		else if (tickets-soldTickets == 0){
			System.out.println("Tickets are sold out!");
		}
		else {
			if ((tickets - soldTickets) - ticketsIn >= 0){
				soldTickets += ticketsIn;
				System.out.println(ticketsIn + " tickets purchased!");
			}
			else {
				System.out.println("Error! you are tring to purchase more than the number of"  							+	"available tickets");
			}
		}
	}
	
	public void refundTickets(int ticketsIn){
		if(tickets == 0){
			System.out.println("Error! Event is not registered to a Stadium");
		}
		else if (soldTickets == 0){
			System.out.println("No tickets were sold, can't proccess refund");
		}
		else{
			if ((soldTickets - ticketsIn) >= 0){
				soldTickets -= ticketsIn;
				System.out.println(ticketsIn + " tickets refunded");
			}
			else{
				System.out.println("Error! You are trying to refund more than the number of " 										+ "tickets sold");
			}
		}
	}
	
	public String toString(){
		return "EventID = " + EVENT_ID + "\n" 
				+ name + ", " + description 
				+ "\nDate: " + date
				+ "\nPrice: $" + price;
	}
}
