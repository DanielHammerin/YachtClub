package boatsNHoes;

public class Boat {
	
	private String boatName;
	private String boatType;
	private int boatLength;
	
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
	
	public void setBoatLength(int boatLength){
		
		this.boatLength = boatLength;
	}
	
	public String getBoatName()
	{
		return boatName;	
	}

	public String getBoatType()
	{
		return boatType;	
	}
	
	public int getBoatLength()
	{
		return boatLength;
	}
}


