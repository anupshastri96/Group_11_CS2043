public class Stadium{
    private String stadiumName;
    private String stadiumLocation;
    private final int seatNum;

    public Stadium(String stadiumName, String stadiumLocation, int seatNum){
        this.stadiumName = stadiumName;
        this.stadiumLocation = stadiumLocation;
        this.seatNum = seatNum;
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
}