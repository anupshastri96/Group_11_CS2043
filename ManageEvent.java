import java.util.ArrayList;

public class ManageEvent{
	private ArrayList<Event> eventList = new ArrayList<Event>();
	public boolean addEvent(Event eventIn, Stadium stadiumIn) {
        // Check for events with the same date
        boolean SameId = false;
        for (Event prevEvent : eventList) {
            if (eventIn.getEventId() == prevEvent.getEventId()) {
                SameId = true;
                break;
            }
        }

        if (SameId) {
			return false;
        } 
		else {
            eventList.add(eventIn);
			eventIn.setTickets(stadiumIn.getStadiumSeatNum());
			return true;
        }
    }
	
	public void removeEvent(Event eventIn) {
		int indexToRemove = -1;
        for (int i = 0; i < eventList.size(); i++) {
			Event existingEvent = eventList.get(i);
			if (existingEvent.getEventId() == eventIn.getEventId()) {
				eventIn.setTickets(0);
				eventIn.setSoldTickets(0);
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