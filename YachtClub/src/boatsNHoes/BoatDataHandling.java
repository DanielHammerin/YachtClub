package boatsNHoes;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class BoatDataHandling {
	Random rnd = new Random(); //Calling on random in order to generate random numbers for the boatID
	ArrayList<String> boatArr = new ArrayList<String>();	//the array where the boatID's will be saved
	Boat boat = new Boat();
	String boatID = null;
	Scanner scan = new Scanner(System.in);
	public void addNewBoat(){	// this constructur method will be called upon in the Main class in 
		// order to register the boats using their information
		System.out.println("Set Boat Name");
		boat.setBoatName(scan.nextLine());
		System.out.println("Set Boat's Length");
		boat.setBoatLength(scan.nextLine());
		System.out.println("Set Boat's Type");
		boat.setBoatType(scan.nextLine());

	}


	public ArrayList registerNewBoat(ArrayList boatArr, String boatID){
		//add the registered boat to the arraylist
		boatArr.add(boatID);
		return boatArr;

	}
	public Object changeBoatData(ArrayList boatArr){

		for(int i = 0; i < boatArr.size(); i++)
		{
			if(boatArr.getOwnerID().equals(boatID))
			{
				System.out.println("Set a new Boat Name: ");
				boatArr.get(i).setBoatName(scan.nextLine());
				System.out.println("Set a new Boat Length");
				boatArr.get(i).setBoatLenght(scan.nextLine());
				System.out.println("Set a new Boat Type");
				boatArr.get(i).setBoatType(scan.nextLine());
			}
		}
		String x = "Error, Invalid boat ID";
		return x;
	}
	public String deleteBoat(ArrayList boatArr){
		for(int i = 0; i < boatArr.size(); i++)
		{
			if (boatArr.get(i).getOwnerID.equals(ownerID))
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


