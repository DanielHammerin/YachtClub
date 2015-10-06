package boatsNHoes;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Markus Alshreydeh & Daniel Hammerin 2015-10-06
 * w0w0w00w
 */
public class BoatDataHandling {
	Random rnd = new Random(); //Calling on random in order to generate random numbers for the boatID
	ArrayList<Boat> boatArr = new ArrayList<Boat>();	//the array where the boatID's will be saved
	Boat boat = new Boat();
	String boatID = null;
	Scanner scan = new Scanner(System.in);

	public void addNewBoat(String ownerID){	// this constructur method will be called upon in the Main class in
		// order to register the boats using their information
		System.out.println("Set Boat Name");
		boat.setBoatName(scan.nextLine());
		System.out.println("Set Boat's Length");
		boat.setBoatLength(Integer.parseInt(scan.nextLine()));
		System.out.println("Set Boat's Type");
		boat.setBoatType(scan.nextLine());

		boatArr.add(boat);
	}


	public void changeBoatData(String ownerID, String boatName){

		for(int i = 0; i < boatArr.size(); i++)
		{
			if(boat.getOwnerID().equals(ownerID) && boat.getBoatName().equals(boatName))
			{
				System.out.println("Set a new Boat Name: ");
				boat.setBoatName(scan.nextLine());
				System.out.println("Set a new Boat Length");
				boat.setBoatLength(Integer.parseInt(scan.nextLine()));
				System.out.println("Set a new Boat Type");
				boat.setBoatType(scan.nextLine());
				System.out.println("Boat added!");
			}
			else {
				System.out.println("ERROR, incorrect owner ID or boat name.");
				throw new NoSuchElementException();

			}
		}
	}

	public String deleteBoat(String ownerID, String boatName){
		for(int i = 0; i < boatArr.size(); i++)
		{
			if (boat.getOwnerID().equals(ownerID) && boat.getBoatName().equals(boatName))
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


