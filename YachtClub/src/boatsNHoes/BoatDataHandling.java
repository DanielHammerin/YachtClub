package boatsNHoes;

import controller.SQLDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by Daniel Hammerin & Markus Alshreydeh 2015-10-06
 * w0w0w00w
 */
public class BoatDataHandling {

	Boat boat = new Boat();
	Scanner scan = new Scanner(System.in);

	public void addNewBoat(String memberID){

		System.out.println("Set Boat Name");
		boat.setBoatName(scan.nextLine());
		System.out.println("Set Boat's Length");
		boat.setBoatLength(Integer.parseInt(scan.nextLine()));
		System.out.println("Set Boat's Type");
		boat.setBoatType(scan.nextLine());
		boat.setOwnerID(memberID);
		SQLDAO.saveBoat(memberID, boat);

	}

	public void changeBoatData(String memberID, String boatName){

		try {
			ArrayList<Boat> boatList = SQLDAO.getAllBoats();
			for(int i = 0; i < boatList.size(); i++) {
				if(boatList.get(i).getOwnerID().equals(memberID) && boatList.get(i).getBoatName().equals(boatName)) {
					System.out.println("Set a new Boat Name: ");
					boatList.get(i).setBoatName(scan.nextLine());
					System.out.println("Set a new Boat Length");
					boatList.get(i).setBoatLength(Integer.parseInt(scan.nextLine()));
					System.out.println("Set a new Boat Type");
					boatList.get(i).setBoatType(scan.nextLine());
					SQLDAO.updateBoat(memberID, boatList.get(i));
					System.out.println("Boat added!");
				}
				else {
					System.out.println("ERROR, incorrect owner ID or boat name.");
					throw new NoSuchElementException();
				}
		}
		}catch(ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e){
			e.printStackTrace();
		}
	}

	public void deleteBoat(String memberID, String boatName){
		try {
			ArrayList<Boat> boatList = SQLDAO.getAllBoats();
			ArrayList<Member> memList = SQLDAO.getAllMembers();
			for (int i = 0; i < memList.size(); i++) {
				if (memList.get(i).equals(memberID)) {
					memList.get(i).setMemberNBoats(-1);
				}
				else {
					System.out.println("You are trying to delete a boat from a member that doesn't exist.");
				}
			}
			for (int i = 0; i < boatList.size(); i++) {
				if (boatList.get(i).getOwnerID().equals(memberID) && boatList.get(i).getBoatName().equals(boatName)) {
					boatList.remove(i);
					System.out.println("Boat successfully removed!");
				}
			}

		} catch(ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
			System.out.println("No such boat found...");
			e.printStackTrace();
		}
	}
}