package boatsNHoes;

public class Member {
	
	private static String name1;
	private static String name2;
	private static String mid;
	private static int pn;
	private static int nBoats;
	
	public void member(String firstName, String lastName, int personalNumber, String memberID, int numberOfBoats) {
		Member.name1 = firstName;
		Member.name2 = lastName;
		Member.pn = personalNumber;
		Member.mid = memberID ;
		Member.nBoats = numberOfBoats;
	}
	
	public void setMemberFirstName(String firstName) {
		Member.name1 = firstName;
	}
	public void setMemberLastName(String lastName) {
		Member.name2 = lastName;
	}
	public void setMemberPersonalNumber(int pn) {
		Member.pn = pn;
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
		return pn;
	}
	public String getMemberID() {
		return mid;
	}
	public int getMemberNBoats() {
		return nBoats;
	}
	
}
