package boatsNHoes;

import controller.SQLDAO;

import java.sql.SQLException;
import java.util.*;

/*
 * Class for handling member data.
 * Created by Daniel Hammerin 05-10-2015
 */
public class MemberDataHandling {

    //SQLDAO dao = new SQLDAO();
    Scanner sc = new Scanner(System.in);

    Random randInt = new Random();
/**
 * Method for creating a new member.
 */
	public void createMember() {
        Member newMember = new Member();
        System.out.println("Enter the new member's first name: ");
        newMember.setMemberFirstName(sc.nextLine());
        System.out.println("Enter the new member's last name: ");
        newMember.setMemberLastName(sc.nextLine());
        System.out.println("Enter the new member's personal number: ");
        newMember.setMemberPersonalNumber((sc.nextLine()));

        /**
         * Below is the method for creating a member ID
         */
        String temp;
        temp = String.valueOf(newMember.getMemberFirstName().charAt(0)) +
                String.valueOf(newMember.getMemberLastName().charAt(0)) +
                Integer.toString(randInt.nextInt(900)+100);
        Member tempMem = new Member(newMember.getMemberFirstName(), newMember.getMemberLastName(), newMember.getMemberID(), newMember.getMemberPersonalNumber(), newMember.getMemberNBoats());
        String tempID = createMemberID(temp, tempMem);// Calls the method with temp.
        newMember.setMemberID(tempID);
        newMember.setMemberNBoats(0);
        SQLDAO.saveMember(newMember);

        System.out.println("New member data: ");
        System.out.println("Member first name: " + newMember.getMemberFirstName());
        System.out.println("Member last name: " + newMember.getMemberLastName());
        System.out.println("Member ID: " + newMember.getMemberID());
        System.out.println("Member personal number: " + newMember.getMemberPersonalNumber());
        System.out.println("Member number of boats: " + newMember.getMemberNBoats());
    }

    /**
     * Method for changing an already existing member.
     * @param ID, the member ID of the member to be changed.
     */
	public void changeMember(String ID) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        try {
            ArrayList<Member> memArr = SQLDAO.getAllMembers();
            boolean memCheck = false;

            for (int i = 0; i < memArr.size(); i++) {
                if (memArr.get(i).getMemberID().equals(ID)) {
                    memCheck = true;
                    System.out.println("Change member's first name to: ");
                    memArr.get(i).setMemberFirstName(sc.nextLine());
                    System.out.println("Change member's last name to: ");
                    memArr.get(i).setMemberLastName(sc.nextLine());
                    System.out.println("Change member's personal number to: ");
                    memArr.get(i).setMemberPersonalNumber(sc.nextLine());

                    SQLDAO.updateMember(memArr.get(i));

                }

            }
            /**
             * Depending on boolean statuses, a correct and relevant error message is displayed
             */
            if (memCheck == false) {
                System.out.println("There is no member with that ID.");
            }
            else {
                System.out.println("Member data updated successfully!");
            }
        } catch(ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e){
            System.out.println("Database connection error.");
            e.printStackTrace();
        }
	}

    /**
     * Method for deleting a member
     * @param ID, the member ID of the member to be removed.
     */
	public void deleteMember(String ID) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        try {
            ArrayList<Member> memArr = SQLDAO.getAllMembers();
            ArrayList<Boat> boatList = SQLDAO.getAllBoats();
            boolean memCheck = false;

            for (int i = 0; i < memArr.size(); i++) {
                if (memArr.get(i).getMemberID().equals(ID)) {
                    SQLDAO.deleteMember(ID);
                    memCheck = true;
                }
            }
            for (int i = 0; i < boatList.size(); i++) {
                if (boatList.get(i).getOwnerID().equals(ID)) {
                    SQLDAO.deleteBoat(ID, boatList.get(i).getBoatName());
                }
            }
            /**
             * Depending on boolean statuses, a correct and relevant error message is displayed
             */
            if (memCheck == false) {
                System.out.println("There is no member with that ID.");
            }
            else {
                System.out.println("Member deleted!");
            }

        } catch(ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e){
            System.out.println("Database connection error.");
            e.printStackTrace();
            }
    }

    /**
     * Method for finding a member
     * @param ID, the member ID of the member to be found.
     * @param v, the boolean for verbose or compact member data.
     */
	public void lookUpMember(String ID, boolean v) {
        try {
            ArrayList<Member> memArr = SQLDAO.getAllMembers();
            ArrayList<Boat> boatList = SQLDAO.getAllBoats();
            boolean memCheck = false;

            if (v == true) {
                for (int i = 0; i < memArr.size(); i++) {
                    if (memArr.get(i).getMemberID().equals(ID)) {
                        memCheck = true;
                        System.out.println("=====Member data=====");
                        System.out.println("Member first name: " + memArr.get(i).getMemberFirstName());
                        System.out.println("Member last name: " + memArr.get(i).getMemberLastName());
                        System.out.println("Member ID: " + memArr.get(i).getMemberID());
                        System.out.println("Member personal number: " + memArr.get(i).getMemberPersonalNumber());
                        System.out.println("Member number of boats: " + memArr.get(i).getMemberNBoats());
                        for (int j = 0; j < boatList.size(); j++) {
                            if (boatList.get(j).getOwnerID().equals(memArr.get(i).getMemberID())) {
                                System.out.println();
                                System.out.println("=======================");
                                System.out.println("Boat name: " + boatList.get(j).getBoatName());
                                System.out.println("Boat type: " + boatList.get(j).getBoatType());
                                System.out.println("Boat length: " + boatList.get(j).getBoatLength());
                                System.out.println("=======================");
                            }
                        }
                    }
                }
            }
            else if (v == false) {
                for (int i = 0; i < memArr.size(); i++) {
                    if (memArr.get(i).getMemberID().equals(ID)) {
                        memCheck = true;
                        System.out.println("=====Member data=====");
                        System.out.println("Member first name: " + memArr.get(i).getMemberFirstName());
                        System.out.println("Member last name: " + memArr.get(i).getMemberLastName());
                        System.out.println("Member ID: " + memArr.get(i).getMemberID());
                        System.out.println("Member number of boats: " + memArr.get(i).getMemberNBoats());
                    }
                }
            }
            /**
             * Depending on boolean statuses, a correct and relevant error message is displayed
             */
            if (memCheck == false) {
                System.out.println("No such member found!");
            }
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            System.out.println("Database connection error.");
            e.printStackTrace();
        }
    }

    /**
     * @param v, does the user want a verbose or compact list?
     * Method for displaying all members and information.
     */
    public void displayAllMembers(boolean v) {
        try {
            ArrayList<Member> memArr = SQLDAO.getAllMembers();
            ArrayList<Boat> boatList = SQLDAO.getAllBoats();

            if (v == true) {                                                                //If verbose is requested, print member data and corresponding boat data.
                for (int i = 0; i < memArr.size(); i++) {
                    System.out.println();
                    System.out.println("==========Member data==========");
                    System.out.println("Member ID: " + memArr.get(i).getMemberID());
                    System.out.println("Member first name: " + memArr.get(i).getMemberFirstName());
                    System.out.println("Member last name: " + memArr.get(i).getMemberLastName());
                    System.out.println("Member personal number: " + memArr.get(i).getMemberPersonalNumber());
                    System.out.println("Member number of boats: " + memArr.get(i).getMemberNBoats());
                    System.out.println("==========" + memArr.get(i).getMemberID() + "boats==========");
                    for (int j = 0; j < boatList.size(); j++) {
                        if (boatList.get(j).getOwnerID().equals(memArr.get(i).getMemberID())) {
                            System.out.println();
                            System.out.println("=======================");
                            System.out.println("Boat name: " + boatList.get(j).getBoatName());
                            System.out.println("Boat type: " + boatList.get(j).getBoatType());
                            System.out.println("Boat length: " + boatList.get(j).getBoatLength());
                            System.out.println("=======================");
                        }
                    }
                }
            } else if (v == false) {                                                                    //Else, just member data.
                for (int i = 0; i < memArr.size(); i++) {
                    System.out.println("=====Member data=====");
                    System.out.println("Member ID: " + memArr.get(i).getMemberID());
                    System.out.println("Member first name: " + memArr.get(i).getMemberFirstName());
                    System.out.println("Member last name: " + memArr.get(i).getMemberLastName());
                    System.out.println("Member number of boats: " + memArr.get(i).getMemberNBoats());
                }
            }
        }
        catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            System.out.println("Database connection error.");
            e.printStackTrace();
        }
    }
    /**
     * Method for creating a unique member ID.
     * @param temp, temp is made in createMember() and is checked here to ensure that it is unique.
     */
    public String createMemberID(String temp, Member newMember) {
        try {
            ArrayList<Member> memArr = SQLDAO.getAllMembers();                          //Get all members from database
            for (int i = 0; i < memArr.size(); i++) {
            if (memArr.size() == 0) {                                                   //If the member list is empty
                return temp;
            }
            else if (memArr.get(i).getMemberID().equals(temp)) {                        // If there is already a member with this ID, randomize a new number.

                char firstC = newMember.getMemberFirstName().charAt(0);
                char secondC = newMember.getMemberLastName().charAt(0);
                int rndm = randInt.nextInt(900)+100;
                System.out.println(i + " firstC: " + firstC + " secondC: " + secondC + " rndm: " + rndm);
                temp = firstC+secondC+Integer.toString(rndm);
                createMemberID(temp, newMember);
            }
        }
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            System.out.println("Database connection error.");
            e.printStackTrace();
        }
        return temp;
    }

}
