package boatsNHoes;

import controller.SQLDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Daniel Hammerin & Markus Alshreydeh 2015-10-06
 * w0w0w00w
 */
public class BoatDataHandling {
	ArrayList<Boat> boatArr = new ArrayList<Boat>();	//the array where the boatID's will be saved


	Boat boat = new Boat();
	String boatID = null;
	Scanner scan = new Scanner(System.in);
	SQLDAO dao = new SQLDAO();

	public void addNewBoat(String ownerID){	// this constructur method will be called upon in the Main class in
		// order to register the boats using their information
		System.out.println("Set Boat Name");
		boat.setBoatName(scan.nextLine());
		System.out.println("Set Boat's Length");
		boat.setBoatLength(Integer.parseInt(scan.nextLine()));
		System.out.println("Set Boat's Type");
		boat.setBoatType(scan.nextLine());
		boat.setOwnerID(ownerID);



			try {
				ArrayList<Member> memList = dao.getAllMembers();
				for (int i = 0; i < memList.size(); i++) {
					//System.out.println(memList.get(i).getMemberID());
					if (memList.get(i).getMemberID().equals(ownerID)) {
						memList.get(i).setMemberNBoats(+1);
						boatArr.add(boat);
						dao.saveBoat(boat);
					} else {
						System.out.println("There is no member with this ID!");
						throw new NoSuchElementException();
					}
				}
				}catch(ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e){
					e.printStackTrace();
				}

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

	public void deleteBoat(String ownerID, String boatName){
		for(int i = 0; i < boatArr.size(); i++)
		{
			if (boat.getOwnerID().equals(ownerID) && boat.getBoatName().equals(boatName))
			{
				boatArr.remove(i);
				System.out.println("Boat successfully removed!");
			}


		}
		System.out.println("No such boat found...");
	}
}


