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

        SQLDAO.saveMember(newMember);
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
        } catch(ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e){
                e.printStackTrace();
            }
    }

    /**
     * Method for finding a member
     * @param ID, the member ID of the member to be found.
     */
	public Object lookUpMember(String ID) {
        String errmsg = "No such member!";
        try {
            ArrayList<Member> memArr = SQLDAO.getAllMembers();
            for (int i = 0; i < memArr.size(); i++) {
                if (memArr.get(i).getMemberID().equals(ID)) {
                    return memArr.get(i);
                } else {
                    throw new NoSuchElementException();
                }
            }
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return errmsg;
    }
    /**
     * Method for creating a unique member ID.
     * @param temp, temp is made in createMember() and is checked here to ensure that it is unique.
     */
    public void createMemberID(String temp) {
        try {
            ArrayList<Member> memArr = SQLDAO.getAllMembers();
            for (int i = 0; i < memArr.size(); i++) {
            if (memArr.size() == 0) {
                newMember.setMemberID(temp);
            }
            else if (memArr.get(i).getMemberID().equals(temp)) {                        // If there is already a member with this ID, randomize a new number.
                temp = String.valueOf(newMember.getMemberFirstName().charAt(0)) +
                        String.valueOf(newMember.getMemberLastName().charAt(0)) +
                        Integer.toString(randInt.nextInt(900)+100);
                createMemberID(temp);
            }
            else {
                newMember.setMemberID(temp);
            }
        }
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
