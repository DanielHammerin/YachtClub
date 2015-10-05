package boatsNHoes;

import java.util.ArrayList;

/**
 * This is the member class for creating objects of the type member.
 * Created by Daniel Hammerin 01-10-2015
 */
public class Member {
	
	private static String name1;
	private static String name2;
	private static String mid;
	private static int pnr;
	private static int nBoats;

	/*
	 * Constructor for the member object.
	 */
	public void member(String firstName, String lastName, String memberID, int personalNumber, int numberOfBoats) {
		Member.name1 = firstName;
		Member.name2 = lastName;
		Member.pnr = personalNumber;
		Member.mid = memberID ;
		Member.nBoats = numberOfBoats;
	}
	/**
	 * Getters and setters for the member object parameters.
	 */
	public void setMemberFirstName(String firstName) {
		Member.name1 = firstName;
	}
	public void setMemberLastName(String lastName) {
		Member.name2 = lastName;
	}
	public void setMemberPersonalNumber(int pn) {
		Member.pnr = pn;
	}
	public void setMemberID(String memID) {
		Member.mid = memID;
	}
	public void setMemberNBoats(int nBoats) {
		Member.nBoats = nBoats;
	}

	public String getMemberFirstName() {
		return name1;
	}
	public String getMemberLastName() {
		return name2;
	}
	public int getMemberPersonalNumber() {
		return pnr;
	}
	public String getMemberID() {
		return mid;
	}
	public int getMemberNBoats() {
		return nBoats;
	}
	
}
