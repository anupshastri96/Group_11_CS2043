public class Stadium{
    private final int STADIUM_ID;
    private String stadiumName;
    private String stadiumLocation;
    private final int seatNum;

    public Stadium(int stadiumId, String stadiumName, String stadiumLocation, int seatNum){
        this.stadiumName = stadiumName;
        this.stadiumLocation = stadiumLocation;
        this.seatNum = seatNum;
        STADIUM_ID = stadiumId;
    }

    public int getStadiumId(){
        return STADIUM_ID;
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
