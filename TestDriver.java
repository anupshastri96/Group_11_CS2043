public class TestDriver{
	public static void main(String[] args){
		Event drake = new Event(1, "Drake Concert", "October 5th","4th World Tour", 125.00);
		Event bts = new Event(2, "BTS Concert", "October 6th","Map of the Soul", 325.00);
		Event tSwift = new Event(3, "Taylor Swift Concert", "November 7th","Red World Tour", 225.00);
		Event newJeans = new Event(4, "NewJeans Concert", "December 25th","Bunnies Everywhere", 165.00);
		Event blackpink = new Event(5, "BlackPink Concert", "October 5th","Coachella", 525.00);
		
		Stadium rogers = new Stadium("Roger's Centre","Toronto",100);
		
		rogers.addEvent(drake);
		rogers.addEvent(bts);
		rogers.addEvent(tSwift);
		rogers.addEvent(newJeans);
		rogers.addEvent(blackpink);
		
		System.out.println(rogers.toString());
	}
}
