package boatsNHoes;

import java.util.ArrayList;

/**
 * This is the member class for creating objects of the type member.
 * Created by Daniel Hammerin 01-10-2015
 */
public class Member {
	
	private String name1;
	private String name2;
	private String mid;
	private String pnr;
	private int nBoats;

	/*
	 * Constructor for the member object.
	 */
	public Member(String firstName, String lastName, String memberID, String personalNumber, int numberOfBoats) {
		this.name1 = firstName;
		this.name2 = lastName;
		this.pnr = personalNumber;
		this.mid = memberID ;
		this.nBoats = numberOfBoats;
	}
    public Member() {}

	/**
	 * Getters and setters for the member object parameters.
	 */
	public void setMemberFirstName(String firstName) {
		this.name1 = firstName;
	}
	public void setMemberLastName(String lastName) {
        this.name2 = lastName;
	}
	public void setMemberPersonalNumber(String pn) {
        this.pnr = pn;
	}
	public void setMemberID(String memID) {
        this.mid = memID;
	}
	public void setMemberNBoats(int nBoats) {
        this.nBoats = nBoats;
	}

	public String getMemberFirstName() {
		return this.name1;
	}
	public String getMemberLastName() {
		return this.name2;
	}
	public String getMemberPersonalNumber() {
		return this.pnr;
	}
	public String getMemberID() {
		return this.mid;
	}
    public int getMemberNBoats() {
		return this.nBoats;
	}
	
}
