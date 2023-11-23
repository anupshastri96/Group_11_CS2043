import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class EventTest {

    @Test
    public void testGetName() {
        Event event = new Event(1, "Concert", "2023-12-01", "Music event", 50.0);
        assertEquals("Concert", event.getName());
    }

    @Test
    public void testGetDate() {
        Event event = new Event(1, "Conference", "2023-11-30", "Tech conference", 100.0);
        assertEquals("2023-11-30", event.getDate());
    }

    @Test
    public void testGetDescription() {
        Event event = new Event(1, "Festival", "2023-12-15", "Cultural festival", 75.0);
        assertEquals("Cultural festival", event.getDescription());
    }

    @Test
    public void testGetPrice() {
        Event event = new Event(1, "Sports Event", "2023-12-10", "Football match", 25.0);
        assertEquals(25.0, event.getPrice());
    }

    @Test
    public void testGetEventId() {
        Event event = new Event(1, "Event", "2023-12-20", "Test event", 10.0);
        assertEquals(1, event.getEventId());
    }

    @Test
    public void testGetTickets() {
        Event event = new Event(1, "Event", "2023-12-20", "Test event", 10.0);
        event.setTickets(100);
        assertEquals(100, event.getTickets());
    }

    @Test
    public void testBuyTickets() {
        Event event = new Event(1, "Event", "2023-12-20", "Test event", 10.0);
        event.setTickets(100);
        event.buyTickets(50);
        assertEquals(50, event.getTickets());
    }

    @Test
    public void testRefundTickets() {
        Event event = new Event(1, "Event", "2023-12-20", "Test event", 10.0);
        event.setTickets(100);
        event.buyTickets(50);
        event.refundTickets(30);
        assertEquals(80, event.getTickets());
    }

    @Test
    public void testInvalidBuyTickets() {
        Event event = new Event(1, "Event", "2023-12-20", "Test event", 10.0);
        event.setTickets(100);
        event.buyTickets(120);
        assertEquals(0, event.getTickets());
    }

    @Test
    public void testInvalidRefundTickets() {
        Event event = new Event(1, "Event", "2023-12-20", "Test event", 10.0);
        event.setTickets(100);
        event.buyTickets(50);
        event.refundTickets(70);
        assertEquals(50, event.getTickets());
    }
}
