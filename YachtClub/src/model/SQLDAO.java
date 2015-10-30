package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Contains methods to interact with the data base.
 * Created by Daniel Hammerin 05-10-2015.
 */
public class SQLDAO {

    /**
     * Saves the passed member into the database.
     * @param member, the member to be saved.
     */
    public static void saveMember(Member member) {

        String query2 = "INSERT into member(firstName, lastName, memID, pnr, nBoats)"
                + " values (?, ?, ?, ?, ?)";


        try {
            Connection conn = openConnection();
            PreparedStatement preparedStmt = conn.prepareStatement(query2);

            preparedStmt.setString(1, member.getMemberFirstName());
            preparedStmt.setString(2, member.getMemberLastName());
            preparedStmt.setString(3, member.getMemberID());
            preparedStmt.setString(4, member.getMemberPersonalNumber());
            preparedStmt.setInt(5, member.getMemberNBoats());

            preparedStmt.executeUpdate();
            preparedStmt.close();

            conn.close();
        }
        catch (ClassNotFoundException| SQLException | IllegalAccessException | InstantiationException e) {
            System.out.println("Couldn't save Member because of a database connection error.");
            e.printStackTrace();
        }
    }

    /**
     * Adds a new boat to a member.
     * @param boat, the boat to be saved.
     */
    public static void saveBoat(String memberID, Boat boat) {
        String query = "insert into boat(boatName, boatType, boatLength, ownerID)" + " values (?, ?, ?, ?)";

        Connection conn = null;
        try {
            conn = openConnection();
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.setString(1, boat.getBoatName());
            preparedStmt.setString(2, boat.getBoatType());
            preparedStmt.setInt(3, boat.getBoatLength());
            preparedStmt.setString(4, boat.getOwnerID());

            preparedStmt.executeUpdate();
            preparedStmt.close();

            conn.close();
        }
        catch (ClassNotFoundException| SQLException | IllegalAccessException | InstantiationException e) {
            System.out.println("Couldn't save boat because of a database connection error.");
        }
    }
    /**
     * @param memID, the member ID of the member to be removed.
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static void deleteMember(String memID) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        String myQuery = "DELETE from member where memID='"+memID+"'";

        Connection c = openConnection();
        Statement s = c.createStatement();
        s.executeUpdate(myQuery);
        c.close();
    }

    /**
     * @param memID, owner of the boat to be removed
     * @param name, name of boat to be removed.
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static void deleteBoat(String memID, String name) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        String query= "DELETE from boat where ownerID='"+memID+"' AND boatName='"+name+"'";

        Connection c = openConnection();
        Statement s = c.createStatement();
        s.executeUpdate(query);
        c.close();
    }

    /**
     * Retrieves all members from the memberlist table.
     * @return
     */
    public static ArrayList<Member> getAllMembers() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        String myQuery = "SELECT * FROM member";

        ArrayList<Member> members = new ArrayList<>();

        Connection c = openConnection();
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery(myQuery);

        while (rs.next()) {
            Member mem = new Member();
            mem.setMemberFirstName(rs.getString("firstName"));
            mem.setMemberLastName(rs.getString("lastName"));
            mem.setMemberID(rs.getString("memID"));
            mem.setMemberPersonalNumber(rs.getString("pnr"));
            mem.setMemberNBoats(rs.getInt("nBoats"));
            members.add(mem);

        }
        c.close();
        return members;
    }

    /**
     * Retrieves all members from the memberlist table.
     * @return
     */
    public static ArrayList<Boat> getAllBoats() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        String myQuery = "SELECT * FROM boat";

        ArrayList<Boat> boats = new ArrayList<>();

        Connection c = openConnection();
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery(myQuery);

        while (rs.next()) {
            Boat b = new Boat();
            b.setBoatName(rs.getString("boatName"));
            b.setBoatType(rs.getString("boatType"));
            b.setBoatLength(rs.getInt("boatLength"));
            b.setOwnerID(rs.getString("ownerID"));
            boats.add(b);

        }
        c.close();
        return boats;
    }

    /**
     * This method retieves a member with the passed member ID.
     * @param memID
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    /*
    public static Member loadMember(String memID) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        String myQuery = "SELECT FROM member WHERE memID = '"+memID+"'" ;
        Member member = new Member();
        Connection c = openConnection();
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery(myQuery);

        while (rs.next()) {
            member.setMemberFirstName(rs.getString("firstName"));
            member.setMemberLastName(rs.getString("firstName"));
            member.setMemberID(rs.getString("memID"));
            member.setMemberPersonalNumber(rs.getString("pnr"));
            member.setMemberNBoats(rs.getInt("nBoats"));
        }
        c.close();
        return member;
    }
    */

    /**
     * This method returns the boats of a member
     * @param ownerID
     * @return a treeset containing the boats
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static ArrayList<Boat> getMemberBoats(String ownerID) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        ArrayList<Boat> boats = new ArrayList<>();
        Connection conn = openConnection();
        Boat boat = new Boat();
        String myQuery = "SELECT FROM boat WHERE ownerID = '"+ownerID+"'" ;

        ResultSet rs = conn.createStatement().executeQuery(myQuery);

        while(rs.next()){
            boat.setBoatName(rs.getString("boatName"));
            boat.setBoatType(rs.getString("boatType"));
            boat.setBoatLength(rs.getInt("boatLength"));
            boat.setOwnerID(rs.getString("ownerID"));
        }
        boats.add(boat);
        conn.close();
        return  boats;
    }


    /**
     * Checks database if there is a duplicate member.
     * @param member
     * @return
     * @throws Exception
     */
    public static boolean isThereADuplicateMember(Member member) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        ArrayList<Member> members = getAllMembers();
        for(Member m : members){
            if(Objects.equals(m.getMemberID(), member.getMemberID())) {
                System.out.println("This member already exists");
                return true;
            }
        }
        return  false;
    }

    /**
     * @param boat, boat to be chekced if it exists in DB.
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static boolean isThereADuplicateBoat(Boat boat) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        ArrayList<Boat> boatList = new ArrayList<Boat>();
        for (Boat b : boatList) {
            if (b.equals(boat)) {
                return true;
            }
        }
        return false;

    }


    /**
     * This method updates a member info
     * @param member is the new info that will be stored
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static void updateMember(Member member) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Connection conn = openConnection();
        String myQuery = "UPDATE member SET firstName = ?, lastName = ? ,memID = ?, pnr = ?, nBoats = ? WHERE memID ='"+member.getMemberID()+"'";

        PreparedStatement ps = conn.prepareStatement(myQuery);

        ps.setString(1,member.getMemberFirstName());
        ps.setString(2, member.getMemberLastName());
        ps.setString(3, member.getMemberID());
        ps.setString(4, member.getMemberPersonalNumber());
        ps.setInt(5, member.getMemberNBoats());

        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    /**
     * Updates boat information.
     * @param ownerID, The boat owners ID of the boat to be updated.
     * @param boat, The boat to be updated.
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static void updateBoat(String ownerID ,Boat boat) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Connection conn = openConnection();

        String myQuery = "UPDATE boat SET boatName = ?, boatType = ? ,boatLength = ?, ownerID = ? WHERE ownerID ='"+ownerID+"'";

        PreparedStatement ps = conn.prepareStatement(myQuery);

        ps.setString(1, boat.getBoatName());
        ps.setString(2, boat.getBoatType());
        ps.setInt(3, boat.getBoatLength());
        ps.setString(4, boat.getOwnerID());

        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    /**
     * Database connection establisher.
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static Connection openConnection() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {

        DriverManager.setLoginTimeout(5);
        Class.forName("com.mysql.jdbc.Driver").newInstance();                                               //fix jdbc.driver
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/YachtClub1","root","");
        return conn;
    }

}
