import java.util.ArrayList;

public class Stadium{
    private String stadiumName;
    private String stadiumLocation;
    private final int seatNum;
    private boolean available;
    private ArrayList<Event> eventList = new ArrayList<Event>();
    private int ticketCounter;

    public Stadium(String stadiumName, String stadiumLocation, int seatNum){
        this.stadiumName = stadiumName;
        this.stadiumLocation = stadiumLocation;
        this.seatNum = seatNum;
        this.available = true;
        this.ticketCounter = 0;
    }

    public void addEvent(Event eventIn) {
        // Check for events with the same date
        boolean SameDate = false;
        for (Event prevEvent : eventList) {
            if (eventIn.getDate().equals(prevEvent.getDate())) {
                SameDate = true;
                break;
            }
        }

        if (SameDate) {
            System.out.println("Failed to add Event! There is already an Event occurring on this date!");
        } 
		else {
            eventList.add(eventIn);
			ticketCounter++;
			if (ticketCounter == seatNum)
				available = false;
            System.out.println("Event Added!");
        }
    }
	
	public void removeEvent(Event eventIn) {
		int indexToRemove = -1;
        for (int i = 0; i < eventList.size(); i++) {
			Event existingEvent = eventList.get(i);
			if (existingEvent.equals(eventIn)) {
				indexToRemove = i;
				break;
            }
        }

        if (indexToRemove != -1) {
			if (ticketCounter == seatNum)
				available = true;
			ticketCounter--;
            eventList.remove(indexToRemove);
            System.out.println("Event Removed!");
        } else {
            System.out.println("Event not found. Nothing to remove.");
        }
    }

    public String getStadiumName(){
        return stadiumName;
    }

    public String getStadiumLocation(){
        return stadiumLocation;
    }

    public int getStadiumSeatNum(){
        return seatNum;
    }

    public boolean checkAvailability(){
        return available;
    }
	
	public void setStadiumName(String nameIn){
		stadiumName = nameIn;
	}
	
	public void setStadiumLocation(String nameIn){
		stadiumLocation = nameIn;
	}
	
	public void setAvailability(boolean in){
		available = in;
	}
	
	public String toString() {
        StringBuilder sb = new StringBuilder();
		sb.append("===========================\n");
        sb.append("Event List:\n");
        for (Event event : eventList) {
            sb.append(event.toString()).append("\n");
			sb.append("===========================\n");
        }
        return sb.toString();
    }
}