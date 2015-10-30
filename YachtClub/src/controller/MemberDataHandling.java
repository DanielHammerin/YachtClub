package controller;


import model.Boat;
import model.Member;
import model.MemberModel;
import model.SQLDAO;
import view.MainView;

import java.sql.SQLException;
import java.util.*;

/**
 * Class for handling member data.
 * Created by Daniel Hammerin 05-10-2015
 */
public class MemberDataHandling {

    MemberModel mm = new MemberModel();
    MainView view = new MainView();
    Random randInt = new Random();
/**
 * Method for creating a new member.
 */
	public void createMember() {
        Member newMember = new Member();
        view.printMessages("emfn");
        newMember.setMemberFirstName(view.getInput());
        view.printMessages("emln");
        newMember.setMemberLastName(view.getInput());
        view.printMessages("empnr");
        newMember.setMemberPersonalNumber(view.getInput());

        //Create the unique member ID and check it so ensure its originality.
        String temp;
        temp = String.valueOf(newMember.getMemberFirstName().charAt(0)) +
                String.valueOf(newMember.getMemberLastName().charAt(0)) +
                Integer.toString(randInt.nextInt(900)+100);
        Member tempMem = new Member(newMember.getMemberFirstName(), newMember.getMemberLastName(), newMember.getMemberID(), newMember.getMemberPersonalNumber(), newMember.getMemberNBoats());

        String tempID = createMemberID(temp, tempMem);// Calls the method with temp.
        newMember.setMemberID(tempID);
        newMember.setMemberNBoats(0);

        mm.saveMember(newMember); // Store in DB
        view.printMessages("am");
        view.printMember(newMember.getMemberFirstName(), newMember.getMemberLastName(), newMember.getMemberID(), newMember.getMemberPersonalNumber(), newMember.getMemberNBoats());
    }

    /**
     * Method for changing an already existing member.
     * @param ID, the member ID of the member to be changed.
     */
	public void changeMember(String ID)  {
        try {
            ArrayList<Member> memArr = SQLDAO.getAllMembers();
            boolean memCheck = false;

            for (int i = 0; i < memArr.size(); i++) {
                if (memArr.get(i).getMemberID().equals(ID)) {
                    memCheck = true;
                    view.printMessages("cmfn");
                    memArr.get(i).setMemberFirstName(view.getInput());
                    view.printMessages("cmln");
                    memArr.get(i).setMemberLastName(view.getInput());
                    view.printMessages("cmpnr");
                    memArr.get(i).setMemberPersonalNumber(view.getInput());

                    mm.changeMember(memArr.get(i));         //Store in DB
                }
            }
            /*
             * Depending on boolean statuses, a correct and relevant error message is displayed
             */
            if (memCheck == false) {
                view.printMessages("nmwi");
            }
            else {
                view.printMessages("mus");
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
	public void deleteMember(String ID) {
        try {
            ArrayList<Member> memArr = SQLDAO.getAllMembers();
            ArrayList<Boat> boatList = SQLDAO.getAllBoats();
            boolean memCheck = false;

            for (int i = 0; i < memArr.size(); i++) {
                if (memArr.get(i).getMemberID().equals(ID)) {
                    mm.deleteMember(memArr.get(i).getMemberID());
                    memCheck = true;
                }
            }
            for (int i = 0; i < boatList.size(); i++) {
                if (boatList.get(i).getOwnerID().equals(ID)) {
                    SQLDAO.deleteBoat(ID, boatList.get(i).getBoatName());
                }
            }
            /*
             * Depending on boolean statuses, a correct and relevant error message is displayed
             */
            if (memCheck == false) {
                view.printMessages("nmwi");
            }
            else {
                view.printMessages("dm");
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
                        String n1 = memArr.get(i).getMemberFirstName();
                        String n2 = memArr.get(i).getMemberLastName();
                        String memID = memArr.get(i).getMemberID();
                        String pnr = memArr.get(i).getMemberPersonalNumber();
                        int nb = memArr.get(i).getMemberNBoats();
                        view.printMember(n1, n2, memID, pnr, nb);

                        for (int j = 0; j < boatList.size(); j++) {
                            if (boatList.get(j).getOwnerID().equals(memArr.get(i).getMemberID())) {
                                String bn = boatList.get(j).getBoatName();
                                String bt = boatList.get(j).getBoatType();
                                int bl = boatList.get(j).getBoatLength();
                                view.printMemberBoats(bn, bt, bl);
                            }
                        }
                    }
                }
            }
            else if (v == false) {
                for (int i = 0; i < memArr.size(); i++) {
                    if (memArr.get(i).getMemberID().equals(ID)) {
                        memCheck = true;
                        String n1 = memArr.get(i).getMemberFirstName();
                        String n2 = memArr.get(i).getMemberLastName();
                        String memID = memArr.get(i).getMemberID();
                        int nb = memArr.get(i).getMemberNBoats();
                        view.printCompactMember(n1, n2, memID, nb);
                    }
                }
            }
            /*
             * Depending on boolean statuses, a correct and relevant error message is displayed
             */
            if (memCheck == false) {
                view.printMessages("nmf");
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
                    String n1 = memArr.get(i).getMemberFirstName();
                    String n2 = memArr.get(i).getMemberLastName();
                    String memID = memArr.get(i).getMemberID();
                    String pnr = memArr.get(i).getMemberPersonalNumber();
                    int nb = memArr.get(i).getMemberNBoats();
                    view.printMember(n1, n2, memID, pnr, nb);

                    String id = memArr.get(i).getMemberID();
                    view.printMemberBoatLine(id);

                    for (int j = 0; j < boatList.size(); j++) {
                        if (boatList.get(j).getOwnerID().equals(memArr.get(i).getMemberID())) {
                            String bn = boatList.get(j).getBoatName();
                            String bt = boatList.get(j).getBoatType();
                            int bl = boatList.get(j).getBoatLength();
                            view.printMemberBoats(bn, bt, bl);
                        }
                    }
                }
            } else if (v == false) {                                                                    //Else, just member data.
                for (int i = 0; i < memArr.size(); i++) {
                    String n1 = memArr.get(i).getMemberFirstName();
                    String n2 = memArr.get(i).getMemberLastName();
                    String memID = memArr.get(i).getMemberID();
                    int nb = memArr.get(i).getMemberNBoats();
                    view.printCompactMember(n1, n2, memID, nb);
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
