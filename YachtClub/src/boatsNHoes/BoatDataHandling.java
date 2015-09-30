package boatsNHoes;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BoatDataHandling {
	
	Boat boat = new Boat();
	
	Random rnd = new Random();
	
	String boatID;
	
	ArrayList<String> boatArr = new ArrayList<String>();
	
	
	Scanner scan = new Scanner(System.in);
	public BoatDataHandling(){
		
		System.out.println("Set Boat Name");
		boat.setBoatName(scan.nextLine());
		System.out.println("Set Boat's Length");
		boat.setBoatLength(scan.nextLine());
		System.out.println("Set Boat's Type");
		boat.setBoatType(scan.nextLine());
		
		 
	        boatID = String.valueOf(boat.getBoatName().charAt(0)) +
	                String.valueOf(boat.getBoatType().charAt(0)) +
	                Integer.toString(rnd.nextInt(900)+100);

	}
	
	
	public ArrayList registerNewBoat(ArrayList boatArr, String boatID){
		//add the registered boat to the arraylist
		
		
		boatArr.add(boatID);
		
		return boatArr;

	}
	
	public void setBoatID(String boatID){
		this.boatID = boatID;
	}
	public String getBoatID(String boatID)
	{
		return boatID;
	}
	
	public void changeBoatData(){
		
		
	}
	
	public void deleteBoat(){}
	
	
}