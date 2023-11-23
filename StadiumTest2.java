public class StadiumTest2 {
    public static void main(String[] args) {
        testAddEvent();
        testRemoveEvent();
    }

    private static void testAddEvent() {
        System.out.println("Test Case: Adding Events");

        Stadium stadium = new Stadium("Example Stadium", "Example Location", 1000);

        Event event1 = new Event(1, "Concert", "January 1st, 2023", "Music Concert", 50.0);
        Event event2 = new Event(2, "Sports Event", "January 31st, 2023", "Football Match", 30.0);

        if (stadium.addEvent(event1)) {
            System.out.println("Passed: Event1 added successfully");
        }
        
        else {
            System.out.println("Failed: Unable to add Event1");
        }

        if (!stadium.addEvent(event1)) {
            System.out.println("Passed: Unable to add Event1 again (duplicate date check)");
        }
        
        else {
            System.out.println("Failed: Allowing the addition of an event with the same date");
        }

        if (stadium.addEvent(event2)) {
            System.out.println("Passed: Event2 added successfully");
        }
        
        else {
            System.out.println("Failed: Unable to add Event2");
        }

        System.out.println(stadium);

        System.out.println("Adding Events test passed!\n");
    }

    private static void testRemoveEvent() {
        System.out.println("Test Case: Removing Events");

        Stadium stadium = new Stadium("Example Stadium", "Example Location", 1000);

        Event event1 = new Event(1, "Concert", "January 1st, 2023", "Music Concert", 50.0);
        Event event2 = new Event(2, "Sports Event", "January 31st, 2023", "Football Match", 30.0);

        stadium.addEvent(event1);
        stadium.addEvent(event2);

        stadium.removeEvent(event1);

        if (event1.getTickets() == 0) {
            System.out.println("Passed: Tickets for Event1 set to 0 after removal");
        }
        
        else {
            System.out.println("Failed: Tickets for Event1 not set to 0 after removal");
        }

        if (!stadium.toString().contains("Concert")) {
            System.out.println("Passed: Event1 removed from the list");
        }
        
        else {
            System.out.println("Failed: Event1 not removed from the list");
        }

        Event nonExistingEvent = new Event(3, "Non-existing Event", "Random Date", "Random Event", 40.0);
        stadium.removeEvent(nonExistingEvent);

        System.out.println(stadium);

        System.out.println("Removing Events test passed!\n");
    }
}
