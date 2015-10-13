package model;

import controller.SQLDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Daniel Hammerin & Markus Alshreydeh 2015-10-06
 * w0w0w00w
 */
public class BoatDataHandling {

	Boat boat = new Boat();
	Scanner scan = new Scanner(System.in);

	/**
	 * @param memberID, member to which boat is added.
	 * Method for adding a new boat.
	 */
	public void addNewBoat(String memberID){

		System.out.println("Set Boat Name");
		boat.setBoatName(scan.nextLine());
		System.out.println("Set Boat's Length");
		boat.setBoatLength(Integer.parseInt(scan.nextLine()));
		System.out.println("Set Boat's Type");
		boat.setBoatType(scan.nextLine());
		boat.setOwnerID(memberID);
		SQLDAO.saveBoat(memberID, boat);
		try {
			ArrayList<Member> ml = SQLDAO.getAllMembers();
			for (int i = 0; i < ml.size(); i++) {
				if (ml.get(i).getMemberID().equals(memberID)) {
					System.out.println(ml.get(i).getMemberID());
					ml.get(i).setMemberNBoats(ml.get(i).getMemberNBoats() + 1);
					SQLDAO.updateMember(ml.get(i));
					System.out.println("Boat added!");
				}
			}
		} catch(ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
			System.out.println("Database connection error.");
			e.printStackTrace();
		}
	}

	/**
	 * Method for changing boat data
	 * @param memberID, ownerID of the boat to be changed.
	 * @param boatName, name of boat to be changed.
	 */
	public void changeBoatData(String memberID, String boatName){

		try {
			ArrayList<Boat> boatList = SQLDAO.getAllBoats();
			boolean memHasBoats = false;
			boolean boatByName = false;
			for(int i = 0; i < boatList.size(); i++) {
				if(boatList.get(i).getOwnerID().equals(memberID)) {
					memHasBoats = true;
					if (boatList.get(i).getBoatName().equals(boatName)) {
						boatByName = true;
						System.out.println("Set a new Boat Name: ");
						boatList.get(i).setBoatName(scan.nextLine());
						System.out.println("Set a new Boat Length");
						boatList.get(i).setBoatLength(Integer.parseInt(scan.nextLine()));
						System.out.println("Set a new Boat Type");
						boatList.get(i).setBoatType(scan.nextLine());

						SQLDAO.updateBoat(memberID, boatList.get(i));
					}
				}
			}
			/*
			 * Depending on boolean statuses, a correct and relevant error message is displayed
			 */
			if (memHasBoats == false) {
				System.out.println("This member has no boats.");
			}
			else if (boatByName == false) {
				System.out.println("The member has no boat of that name.");
			}
			else {
				System.out.println("Boat data changed!");
			}
		}catch(ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e){
			System.out.println("Database connection error.");
			e.printStackTrace();
		}
	}

	/**
	 * Method for deleting a boat
	 * @param memberID
	 * @param boatName
	 */
	public void deleteBoat(String memberID, String boatName){
		try {
			ArrayList<Boat> boatList = SQLDAO.getAllBoats();
			ArrayList<Member> memList = SQLDAO.getAllMembers();
			boolean memCheck = false;
			boolean boatCheck = false;

			for (int i = 0; i < memList.size(); i++) {
				if (memList.get(i).getMemberID().equals(memberID)) {
					memCheck = true;
					for (int j = 0; j < boatList.size(); j++) {
						if (boatList.get(j).getOwnerID().equals(memberID) && boatList.get(j).getBoatName().equals(boatName)) {
							boatCheck = true;
							memList.get(i).setMemberNBoats(memList.get(i).getMemberNBoats() - 1);				//Remove 1 boat from number of boats.

							SQLDAO.updateMember(memList.get(i));
							SQLDAO.deleteBoat(memberID, boatList.get(j).getBoatName());
						}
					}

				}
			}
			/*
			 * Depending on boolean statuses, a correct and relevant error message is displayed
			 */
			if (memCheck == false) {
				System.out.println("There is no member with that ID.");
			}
			else if (memCheck == true && boatCheck == false) {
				System.out.println("This member has no boat with that name.");
			}
			else {
				System.out.println("Boat removed successfully!");
			}

		} catch(ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
			System.out.println("Database connection error.");
			e.printStackTrace();
		}
	}
}