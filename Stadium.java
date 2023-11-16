import java.util.ArrayList;

public class Stadium{
    private String stadiumName;
    private String stadiumLocation;
    private final int seatNum;
    private ArrayList<Event> eventList = new ArrayList<Event>();

    public Stadium(String stadiumName, String stadiumLocation, int seatNum){
        this.stadiumName = stadiumName;
        this.stadiumLocation = stadiumLocation;
        this.seatNum = seatNum;
    }

    public boolean addEvent(Event eventIn) {
        // Check for events with the same date
        boolean SameDate = false;
        for (Event prevEvent : eventList) {
            if (eventIn.getDate().equals(prevEvent.getDate())) {
                SameDate = true;
                break;
            }
        }

        if (SameDate) {
			return false;
        } 
		else {
            eventList.add(eventIn);
			eventIn.setTickets(seatNum);
			return true;
        }
    }
	
	public void removeEvent(Event eventIn) {
		int indexToRemove = -1;
        for (int i = 0; i < eventList.size(); i++) {
			Event existingEvent = eventList.get(i);
			if (existingEvent.getName().equals(eventIn.getName())) {
				eventIn.setTickets(0);
				indexToRemove = i;
				break;
            }
        }

        if (indexToRemove != -1) {
            eventList.remove(indexToRemove);
            System.out.println("Event " + eventIn.getName() + " Removed!");
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
	
	public void setStadiumName(String nameIn){
		stadiumName = nameIn;
	}
	
	public void setStadiumLocation(String nameIn){
		stadiumLocation = nameIn;
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