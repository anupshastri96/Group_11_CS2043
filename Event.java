public class Event{
	private String name, date, description;
	private double price;
	private final int EVENT_ID;
		
	public Event(int eventId, String name, String date, String description, double price){
		this.name = name;
		this.date = date;
		this.description = description;
		this.price = price;
		EVENT_ID = eventId;
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
	
	public String toString(){
		return "EventID = " + EVENT_ID + "\n" + name + ", " + description + "\nDate: " + date;
	}
}
