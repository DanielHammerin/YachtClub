package boatsNHoes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class MemberDataHandling {
	
	LinkedList<Member> members = new LinkedList<Member>();
    Scanner sc = new Scanner(System.in);
    Member newMember = new Member();
    Random randInt = new Random();

	public void createMember() {

        System.out.println("Enter the new member's first name: ");
        newMember.setMemberFirstName(sc.nextLine());
        System.out.println("Enter the new member's last name: ");
        newMember.setMemberLastName(sc.nextLine());
        System.out.println("Enter the new member's personal number: ");
        newMember.setMemberPersonalNumber(sc.nextInt());
        System.out.println("Enter the new member's number of boats: ");
        newMember.setMemberNBoats(sc.nextInt());

        String temp;
        temp = String.valueOf(newMember.getMemberFirstName().charAt(0)) +
                String.valueOf(newMember.getMemberLastName().charAt(0)) +
                Integer.toString(randInt.nextInt(900)+100);
        createMemberID(temp);
    }
	
	public void changeMember() {

	}
	
	public void deleteMember() {
		
	}
	
	public void lookUpMember() {
		
	}

    public void createMemberID(String temp) {
        for (int i = 0; i <= members.size(); i++) {
            if (members.get(i).getMemberID().equals(temp)) {
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
