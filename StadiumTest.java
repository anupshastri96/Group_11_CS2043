import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class StadiumTest {
    private Stadium stadium;
    private Event event1;
    private Event event2;

    @BeforeEach
    void setUp() {
        stadium = new Stadium("Stadium Test", "Location Test", 1000);
        event1 = new Event(1, "Event 1", "2023-11-25", "Description 1", 50.0);
        event2 = new Event(2, "Event 2", "2023-11-26", "Description 2", 60.0);
    }

    @Test
    void addEvent_ValidEvent_SuccessfullyAdded() {
        assertTrue(stadium.addEvent(event1));
        assertEquals(1, stadium.getEventList().size());
    }

    @Test
    void addEvent_EventWithSameDate_NotAdded() {
        stadium.addEvent(event1);
        Event eventWithSameDate = new Event(3, "Event with Same Date", "2023-11-25", "Description 3", 70.0);
        assertFalse(stadium.addEvent(eventWithSameDate));
        assertEquals(1, stadium.getEventList().size());
    }

    @Test
    void removeEvent_ExistingEvent_SuccessfullyRemoved() {
        stadium.addEvent(event1);
        stadium.addEvent(event2);
        stadium.removeEvent(event1);
        assertEquals(1, stadium.getEventList().size());
    }

    @Test
    void removeEvent_NonExistingEvent_NothingRemoved() {
        stadium.addEvent(event1);
        stadium.addEvent(event2);
        Event nonExistingEvent = new Event(3, "Non-existing Event", "2023-11-27", "Description 3", 70.0);
        stadium.removeEvent(nonExistingEvent);
        assertEquals(2, stadium.getEventList().size());
    }

    // Additional tests for other methods can be added as needed

}
