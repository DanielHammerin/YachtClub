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
		try {
			ArrayList<Member> ml = SQLDAO.getAllMembers();
			for (int i = 0; i < ml.size(); i++) {
				if (ml.get(i).getMemberID().equals(memberID)) {
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

	public void changeBoatData(String memberID, String boatName){

		try {
			ArrayList<Boat> boatList = SQLDAO.getAllBoats();
			for(int i = 0; i < boatList.size(); i++) {
				if(boatList.get(i).getOwnerID().equals(memberID)) {
					if(boatList.get(i).getBoatName().equals(boatName)) {
                        System.out.println("Set a new Boat Name: ");
                        boatList.get(i).setBoatName(scan.nextLine());
                        System.out.println("Set a new Boat Length");
                        boatList.get(i).setBoatLength(Integer.parseInt(scan.nextLine()));
                        System.out.println("Set a new Boat Type");
                        boatList.get(i).setBoatType(scan.nextLine());

                        SQLDAO.updateBoat(memberID, boatList.get(i));
                        System.out.println("Boat data changed!");
                    }
                    else {
                        System.out.println("The member has no boat of that name.");
                        throw new NoSuchElementException();
                    }
				}
				else {
					System.out.println("There is no member with that ID.");
					throw new NoSuchElementException();
				}
		}
		}catch(ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e){
			System.out.println("Database connection error.");
			e.printStackTrace();
		}
	}

	public void deleteBoat(String memberID, String boatName){
		try {
			ArrayList<Boat> boatList = SQLDAO.getAllBoats();
			ArrayList<Member> memList = SQLDAO.getAllMembers();
			for (int i = 0; i < boatList.size(); i++) {
				if (boatList.get(i).getOwnerID().equals(memberID)) {
					if (boatList.get(i).getBoatName().equals(boatName)) {
                        SQLDAO.deleteBoat(memberID, boatList.get(i));
                        System.out.println("Boat successfully removed!");

                        for (int j = 0; j < memList.size(); j++) {
                            if (memList.get(j).getMemberID().equals(memberID)) {
                                memList.get(j).setMemberNBoats(memList.get(j).getMemberNBoats() - 1);
                                SQLDAO.updateMember(memList.get(j));
                            }
                        }
                    }
                    else {
                        System.out.println("This member has no boat of that name.");
                        throw new NoSuchElementException();
                    }
				}
				else {
					System.out.println("There is no member with that ID.");
					throw new NoSuchElementException();
				}
			}

		} catch(ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
			System.out.println("Database connection error.");
			e.printStackTrace();
		}
	}
}