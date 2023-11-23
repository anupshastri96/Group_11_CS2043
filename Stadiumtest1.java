public class Stadiumtest1{
    public static void main(String[] args){
        Stadium stadium = new Stadium("Stadium Name", "Fredericton", 100);
        String outData;
        Boolean outDataTwo;
        int outDataThree;


        outData = stadium.getStadiumName();
        if(outData.equals(stadium.getStadiumName())){
            System.out.println("Test 1: Stadium Name is Correct");
        }
        else{
             System.out.println("Test 1: Stadium Name is NOT Correct");
        }
        stadium.setStadiumName("New Name");
        outData = stadium.getStadiumName();
        if(outData.equals(stadium.getStadiumName())){
            System.out.println("Test 1: Stadium Name is Correct");
        }
        else{
             System.out.println("Test 1: Stadium Name is NOT Correct");
        }

        outData = stadium.getStadiumLocation();
        if(outData.equals(stadium.getStadiumLocation())){
            System.out.println("Test 2: Stadium Location is Correct");
        }
        else{
             System.out.println("Test 2: Stadium Location is NOT Correct");
        }
        stadium.setStadiumLocation("Moncton");
        outData = stadium.getStadiumLocation();
        if(outData.equals(stadium.getStadiumLocation())){
            System.out.println("Test 2: Stadium Location is Correct");
        }
        else{
             System.out.println("Test 2: Stadium Location is NOT Correct");
        }

        outDataTwo = stadium.checkAvailability();
        if(outDataTwo){
            System.out.println("Test 3: Seats are available!");
        }
        else{
            System.out.println("Test 3: Seats are NOT available!");
        }
        stadium.setAvailability(false);
        if(outDataTwo){
            System.out.println("Test 3: Seats are available!");
        }
        else{
            System.out.println("Test 3: Seats are NOT available!");
        }

        outDataThree = stadium.getStadiumSeatNum();
        if(outDataThree == stadium.getStadiumSeatNum()){
            System.out.println("Test 4: Stadium Location is Correct");
        }
        else{
             System.out.println("Test 4: Stadium Location is NOT Correct");
        }

        System.out.println(stadium.toString());

    }
}