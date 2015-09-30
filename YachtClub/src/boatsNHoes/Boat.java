package boatsNHoes;

public class Boat {
	
	private String ownerID;
	private String boatName;
	private String boatType;
	private String boatLength;
	
	
	Member member = new Member();
	
	public Boat(/*String boatName,String boatType, int length*/)
	{
		/*name = boatName;
		type = boatType;
		len = length;
		*/
	}
	
	public void setOwnerID(String ownerID)
	//connect member to their boat, by connecting member's ID to his boat's ID
	{
		this.ownerID = member.getMemberID();
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
	
	public String setOwnerID()
	{
		return ownerID;
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
	
}


