public class ManageTickets{
	public void buyTickets(Event e, int amount){
		if (e.checkAvailability()){
			e.setTickets(e.getTickets()-amount);
			e.setSoldTickets(e.getSoldTickets()+amount);
			System.out.println(amount + " Tickets Bought! Cost: $" + amount*e.getPrice());		
		}
		else{
			System.out.println("No Tickets are available for this event!");
		}
	}
	
	public void refundTickets(Event e, int amount){
		if (e.getSoldTickets()-amount >= 0){
			e.setTickets(e.getTickets()+amount);
			e.setSoldTickets(e.getSoldTickets()-amount);
			System.out.println(amount + " Tickets Refunded! Cost: $" +amount*e.getPrice());		
		}
		else{
			System.out.println("Error! Trying to refund more tickets than sold tickets!");
		}
	}
}
