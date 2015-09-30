package boatsNHoes;

import java.util.*;

public class MemberDataHandling {
	
	ArrayList<Member> members = new ArrayList<Member>();
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
	
	public Object changeMember() {
        Object errmsg = "There is no member with this ID.";
        Object mem;
        for (int i = 0; i <= members.size(); i++) {
            if (members.get(i).getMemberID().equals(ID)) {
                mem = members.get(i);

            } else {
                throw new NoSuchElementException();
                return errmsg;
            }
        }
	}
	
	public Object deleteMember(String ID) {
        Object errmsg = "There is no member with this ID.";
        Object mem;
        for (int i = 0; i <= members.size(); i++) {
            if (members.get(i).getMemberID().equals(ID)) {
                members.get(i)


            } else {
                throw new NoSuchElementException();
                return errmsg;
            }
        }

	}
	
	public Object lookUpMember(String ID) {
        Object errmsg = "There is no member with this ID.";
        for (int i = 0; i <= members.size(); i++) {
            if (members.get(i).getMemberID().equals(ID)) {
                return members.get(i);
            }
            else {
                throw new NoSuchElementException();
                return errmsg;
            }
        }
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
