package boatsNHoes;

public class Boat {
	
	private String boatName;
	private String boatType;
	private String boatLength;
	private String ownerID;
	
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
	
}


