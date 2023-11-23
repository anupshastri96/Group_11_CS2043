public class EventTest{
    public static void main (String[] args){
    
        Event test1 = new Event(1, "name", "date", "description", 99.99);
        
        if (test1.getEventId() == 1){
        	System.out.println("Test1: Event Id is correct");
        }
        else{
        	System.out.println("Test1: Event Id is incorrect");
        }
        
        if (test1.getName().equals("name")){
            System.out.println("Test1: Name is correct");
        }
        else{
            System.out.println("Test1: Name is incorrect");
        }
        
        if (test1.getDate().equals("date")){
            System.out.println("Test1: Date is correct");
        }
        else{
            System.out.println("Test1: Date is incorrect");
        }
        
        if (test1.getDescription().equals("description")){
            System.out.println("Test1: Description is correct");
        }
        else{
            System.out.println("Test1: Description is incorrect");
        }
        
        if (test1.getPrice() == 99.99){
            System.out.println("Test1: Price is correct");
        }
        else{
            System.out.println("Test1: Price is incorrect");
        }  
		
		if (test1.getTickets() == 0){
			System.out.println("Test1: Tickets are correct");
        }
        else{
            System.out.println("Test1: Tickets are incorrect");
        } 
		
		test1.setName("name2");
		
		if (test1.getName().equals("name2")){
        	System.out.println("Test1: setName is correct");
        }
        else{
        	System.out.println("Test1: setName is incorrect");
        }
		
		test1.setDate("Date2");
		
		if (test1.getDate().equals("Date2")){
        	System.out.println("Test1: setDate is correct");
        }
        else{
        	System.out.println("Test1: setDate is incorrect");
        }
		
		test1.setDescription("description2");
		
		if (test1.getDescription().equals("description2")){
        	System.out.println("Test1: setDescription is correct");
        }
        else{
        	System.out.println("Test1: setDescription is incorrect");
        }
		
		test1.setPrice(39.99);
		
		if (test1.getPrice() == 39.99){
        	System.out.println("Test1: setPrice is correct");
        }
        else{
        	System.out.println("Test1: setPrice is incorrect");
        }
		
		test1.buyTickets(1); //buying tickets with no available tickets
		
		test1.refundTickets(1); //refunding tickets with no available tickets
		
		test1.setTickets(100);
		
		if (test1.getTickets() == 100){
			System.out.println("Test1: setTickets is correct");
        }
        else{
            System.out.println("Test1: setTickets is incorrect");
        } 
		
		test1.buyTickets(1); //buying tickets (should work)
		
		test1.refundTickets(1); //refunding tickets (should work)
		
		test1.buyTickets(101); //attemting to buy more tickets thana available
		
		test1.refundTickets(101); //when no tickets have been sold
		
		test1.buyTickets(60); //buying 60 tickets (should work)
		
		test1.refundTickets(61); //attemting to refund tickets more than bought amount
		
		System.out.println(test1.toString());
		
		
		
		
		
		
    }
}
