package boatsNHoes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class MemberDataHandling {
	
	LinkedList<Member> members = new LinkedList<Member>();
    ArrayList memberIdList = new ArrayList();
    Scanner sc = new Scanner(System.in);

	public void createMember() {
        Member newMember = new Member();
        Random randInt = new Random();

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

        for (int i = 0; i <= memberIdList.size(); i++) {

            if (memberIdList.get(i) == temp) {
                temp = String.valueOf(newMember.getMemberFirstName().charAt(0)) +
                        String.valueOf(newMember.getMemberLastName().charAt(0)) +
                        Integer.toString(randInt.nextInt(900)+100);
            }
            else {
                newMember.setMemberID(temp);
            }
        }
    }
	
	public void changeMember() {
		
	}
	
	public void deleteMember() {
		
	}
	
	public void lookUpMember() {
		
	}

}
