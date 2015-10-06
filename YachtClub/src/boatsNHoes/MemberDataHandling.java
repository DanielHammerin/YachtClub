package boatsNHoes;

import controller.SQLDAO;

import java.sql.SQLException;
import java.util.*;

/*
 * Class for handling member data.
 * Created by Daniel Hammerin 05-10-2015
 */
public class MemberDataHandling {

    SQLDAO dao = new SQLDAO();
	ArrayList<Member> members = new ArrayList<Member>();
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
        members.add(newMember);
        dao.saveMember(newMember);
    }

    /**
     * Method for changing an already existing member.
     * @param ID, the member ID of the member to be changed.
     */
	public void changeMember(String ID) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        for (int i = 0; i <= members.size(); i++) {
            Member tempMember = members.get(i);
            if (tempMember.getMemberID().equals(ID)) {
                System.out.println("Change member's first name to: ");
                tempMember.setMemberFirstName(sc.nextLine());
                System.out.println("Change member's last name to: ");
                tempMember.setMemberLastName(sc.nextLine());
                System.out.println("Change member's personal number to: ");
                tempMember.setMemberPersonalNumber(sc.nextLine());

                dao.updateMember(tempMember);
            }
            else {
                System.out.println("There is no member with that ID.");
                throw new NoSuchElementException();
            }
        }
	}

    /**
     * Method for deleting a member
     * @param ID, the member ID of the member to be removed.
     */
	public void deleteMember(String ID) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        for (int i = 0; i <= members.size(); i++) {
            if (members.get(i).getMemberID().equals(ID)) {
                members.remove(i);
                dao.deleteMember(ID);
                System.out.println("Member deleted!");
            }
            else {
                System.out.println("There is no member with that ID.");
                throw new NoSuchElementException();
            }
        }
    }

    /**
     * Method for finding a member
     * @param ID, the member ID of the member to be found.
     */
	public Object lookUpMember(String ID) {
        Object errmsg = "There is no member with this ID.";
        for (int i = 0; i <= members.size(); i++) {
            if (members.get(i).getMemberID().equals(ID)) {
                return members.get(i);
            }
            else {
                throw new NoSuchElementException();
            }
        }
        return errmsg;
    }
    /**
     * Method for creating a unique member ID.
     * @param temp, temp is made in createMember() and is checked here to ensure that it is unique.
     */
    public void createMemberID(String temp) {
        for (int i = 0; i <= members.size(); i++) {
            if (members.size() == 0) {
                newMember.setMemberID(temp);
            }
            else if (members.get(i).getMemberID().equals(temp)) {                        // If there is already a member with this ID, randomize a new number.
                temp = String.valueOf(newMember.getMemberFirstName().charAt(0)) +
                        String.valueOf(newMember.getMemberLastName().charAt(0)) +
                        Integer.toString(randInt.nextInt(900)+100);
                createMemberID(temp);
            }
            else {
                newMember.setMemberID(temp);
            }
        }
    }

}
