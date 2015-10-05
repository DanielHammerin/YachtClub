package boatsNHoes;

import java.util.Random;

public class Boat {
	
	private String boatName;
	private String boatType;
	private String boatLength;
	private String ownerID;
	private String boatID;
	
	BoatDataHandling data = new BoatDataHandling();
	
	Member member = new Member();
	
	public Boat(/*String boatName,String boatType, int length*/)
	{
		/*name = boatName;
		type = boatType;
		len = length;
		*/
	}
	
	
	public void setBoatName(String boatName){
		this.boatName = boatName;
	}
	
	public void setBoatType(String boatType){
		this.boatType = boatType;
	}
	
	public void setBoatLength(String boatLength){
		
		this.boatLength = boatLength;
	}
	public void setOwnerID(String ID) {
		this.ownerID = ID;
	}
	

	public String getBoatName()
	{
		return boatName;	
	}

	public String getBoatType()
	{
		return boatType;	
	}
	
	public String getBoatLength()
	{
		return boatLength;
	}

	public String getOwnerID() {
		return ownerID;
	}
	
	public String getBoatID(Random rnd, String boatID)
	{
		    boatID = String.valueOf(getBoatName().charAt(0)) +
	                String.valueOf(getBoatType().charAt(0)) +
	                Integer.toString(rnd.nextInt(900)+100);

		return boatID;
		
	}
	
}


