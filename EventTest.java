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
    }
}
