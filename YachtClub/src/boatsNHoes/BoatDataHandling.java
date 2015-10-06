package boatsNHoes;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class BoatDataHandling {
	
	Boat boat = new Boat();
	
	Random rnd = new Random(); 				//Calling on random in order to generate random numbers for the boatID
		
	ArrayList<String> boatArr = new ArrayList<String>();		//the array where the boatID's will be saved
	
	String boatID = null;
	
	Scanner scan = new Scanner(System.in);
	
	
	public addNewBoat(){									// this constructur method will be called upon in the Main class in
																// order to register the boats using their information
		
		System.out.println("Set Boat Name");
		boat.setBoatName(scan.nextLine());
		System.out.println("Set Boat's Length");
		boat.setBoatLength(scan.nextLine());
		System.out.println("Set Boat's Type");
		boat.setBoatType(scan.nextLine());
		
	    
	}
	

	public String getBoatID(Random rnd, String boatID)
	{
		    boatID = String.valueOf(boat.getBoatName().charAt(0)) +
	                String.valueOf(boat.getBoatType().charAt(0)) +
	                Integer.toString(rnd.nextInt(900)+100);

		return boatID;
		
	}
	

	public ArrayList registerNewBoat(ArrayList boatArr, String boatID){
		//add the registered boat to the arraylist
		
		
		boatArr.add(boatID);
		
		return boatArr;

	}
	
	public String changeBoatData(String boatID){
		
	}
		
	
	
	public String deleteBoat(){
		for(int i = 0; i < boatArr.size(); i++)
		{
			 if (boatArr.get(i).equals(boatID))
			 {
	                boatArr.remove(i);
	                String x = "the boat has been removed";
	                return x;
	            }
			 
	         
	            }

        String y = "Error, Invalid boat ID";
        return y;
		}
		
	}
	
	
