package boatsNHoes;

import controller.SQLDAO;

import java.sql.SQLException;
import java.util.*;

import static controller.SQLDAO.*;

/*
 * Class for handling member data.
 * Created by Daniel Hammerin 05-10-2015
 */
public class MemberDataHandling {

    //SQLDAO dao = new SQLDAO();
    Scanner sc = new Scanner(System.in);
    Member newMember = new Member();
    Random randInt = new Random();
/**
 * Method for creating a new member.
 */
	public void createMember() {

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
        createMemberID(temp);                                               // Calls the method with temp.
        newMember.setMemberID(temp);
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
            for (int i = 0; i < memArr.size(); i++) {
                if (memArr.get(i).getMemberID().equals(ID)) {
                    System.out.println("Change member's first name to: ");
                    memArr.get(i).setMemberFirstName(sc.nextLine());
                    System.out.println("Change member's last name to: ");
                    memArr.get(i).setMemberLastName(sc.nextLine());
                    System.out.println("Change member's personal number to: ");
                    memArr.get(i).setMemberPersonalNumber(sc.nextLine());

                    SQLDAO.updateMember(memArr.get(i));
                } else {
                    System.out.println("There is no member with that ID.");
                    throw new NoSuchElementException();
                }
            }
        } catch(ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e){
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
            for (int i = 0; i < memArr.size(); i++) {
                if (memArr.get(i).getMemberID().equals(ID)) {
                    memArr.remove(i);
                    SQLDAO.deleteMember(ID);
                    System.out.println("Member deleted!");
                } else {
                    System.out.println("There is no member with that ID.");
                    throw new NoSuchElementException();
                }
            }
            for (int i = 0; i < boatList.size(); i++) {
                if (boatList.get(i).getOwnerID().equals(ID)) {
                    SQLDAO.deleteBoat(ID, boatList.get(i));
                }
            }
        } catch(ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e){
                e.printStackTrace();
            }
    }

    /**
     * Method for finding a member
     * @param ID, the member ID of the member to be found.
     */
	public void lookUpMember(String ID) {
        String errmsg = "No such member!";
        try {
            ArrayList<Member> memArr = SQLDAO.getAllMembers();
            for (int i = 0; i < memArr.size(); i++) {
                if (memArr.get(i).getMemberID().equals(ID)) {
                    System.out.println("Member first name: " + memArr.get(i).getMemberFirstName());
                    System.out.println("Member last name: " + memArr.get(i).getMemberLastName());
                    System.out.println("Member ID: " + memArr.get(i).getMemberID());
                    System.out.println("Member personal number: " + memArr.get(i).getMemberPersonalNumber());
                    System.out.println("Member number of boats: " + memArr.get(i).getMemberNBoats());
                } else {
                    System.out.println("No such member found!");
                    throw new NoSuchElementException();
                }
            }
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    /**
     * Method for creating a unique member ID.
     * @param temp, temp is made in createMember() and is checked here to ensure that it is unique.
     */
    public String createMemberID(String temp) {
        try {
            ArrayList<Member> memArr = SQLDAO.getAllMembers();
            for (int i = 0; i < memArr.size(); i++) {
            if (memArr.size() == 0) {
                return temp;
            }
            else if (memArr.get(i).getMemberID().equals(temp)) {                        // If there is already a member with this ID, randomize a new number.
                temp = String.valueOf(newMember.getMemberFirstName().charAt(0)) +
                        String.valueOf(newMember.getMemberLastName().charAt(0)) +
                        Integer.toString(randInt.nextInt(900)+100);
                createMemberID(temp);
            }
        }
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return temp;
    }

}
