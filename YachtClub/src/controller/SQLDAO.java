package controller;

import javafx.scene.control.Alert;
import boatsNHoes.*;
import java.sql.*;
import java.util.*;

/**
 * Contains methods to interact with the data base.
 * Created by Daniel Hammerin 05-10-2015.
 */
public class SQLDAO {

    /**
     * Saves the passed member into the database.
     * @param member, the member to be saved.
     */
    public void saveMember(Member member) {

        String query2 = "insert into member(firstName, lastName, memID, pnr, nBoats)"
                + " values (?, ?, ?, ?, ?)";

        Connection conn = null;
        try {
            conn = openConnection();
            PreparedStatement preparedStmt = conn.prepareStatement(query2);

            preparedStmt.setString(1, member.getMemberFirstName());
            preparedStmt.setString(2, member.getMemberLastName());
            preparedStmt.setString(3, member.getMemberID());
            preparedStmt.setInt(4, member.getMemberPersonalNumber());
            preparedStmt.setInt(5, member.getMemberNBoats());

            preparedStmt.executeUpdate();
            preparedStmt.close();

            conn.close();
        }
        catch (ClassNotFoundException| SQLException | IllegalAccessException | InstantiationException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Connection Error");
            alert.setHeaderText("Error!");
            alert.setContentText("The Timeline was not saved due to a Database Connection Error");
            alert.showAndWait();

            e.printStackTrace();
        }
    }

    /**
     * Adds a new boat to a member.
     * @param member, the member ID of the owner.
     * @param boat, the boat to be saved.
     */
    public void saveBoat(Member member, Boat boat) {
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

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Connection Error");
            alert.setHeaderText("Error!");
            alert.setContentText("The Timeline was not saved due to a Database Connection Error");
            alert.showAndWait();

            e.printStackTrace();
        }
    }

    /**
     * Retrieves all members from the memberlist table.
     * @return
     */
    public LinkedList<Member> getAllMembers() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        String myQuery = "SELECT FROM members";

        LinkedList<Member> members = new LinkedList<Member>();

        Connection c = openConnection();
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery(myQuery);

        while (rs.next()) {
            Member mem = new Member();
            mem.setMemberFirstName(rs.getString("firstName"));
            mem.setMemberLastName(rs.getString("lastName"));
            mem.setMemberID(rs.getString("memID"));
            mem.setMemberPersonalNumber(rs.getInt("pnr"));
            members.add(mem);

        }
        c.close();
        return members;
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
    public Member loadMember(String memID) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        String myQuery = "SELECT FROM member WHERE memID = '"+memID+"'" ;
        Member member = new Member();
        Connection c = openConnection();
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery(myQuery);

        while (rs.next()) {
            member.setMemberFirstName(rs.getString("firstName"));
            member.setMemberLastName(rs.getString("firstName"));
            member.setMemberID(rs.getString("memID"));
            member.setMemberPersonalNumber(rs.getInt("pnr"));
            member.setMemberNBoats(rs.getInt("nBoats"));
        }
        c.close();
        return member;
    }

    /**
     * This method returns the boats of a member
     * @param member
     * @return a treeset containing the boats
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public TreeSet<Boat> getMemberBoats(Member member) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        TreeSet<Boat> boats = new TreeSet<Boat>();
        Connection conn = openConnection();
        Boat boat = new Boat();
        String myQuery = "SELECT FROM boat WHERE ownerID = '"+member.getMemberID()+"'" ;

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
    public boolean isThereADuplicateMember(Member member) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        LinkedList<Member> members = getAllMembers();
        for(Member m : members){
            if(Objects.equals(m.getMemberID(), member.getMemberID())) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Duplicate Member");
                a.setContentText("There already exists a member with the ID: "+m.getMemberID());
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
    public boolean isThereADuplicateBoat(Boat boat) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        ArrayList<Boat> boatList = new ArrayList<Boat>();
        for (Boat b : boatList) {
            if (b.equals(boat)) {
                return true;
            }
        }
        return false;

    }

    /**
     *
     * @param memID, the member ID of the member to be removed.
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void deleteMember(String memID) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        String myQuery = "DELETE from member where memID='"+memID+"'";

        Connection c = openConnection();
        Statement s = c.createStatement();
        s.executeUpdate(myQuery);
        c.close();
    }

    public void deleteBoat(String memID, Boat boat) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        String query;
        if(boat instanceof  Boat){
            query= "DELETE from boat where ownerID='"+memID+"'";
        }
        else {
            throw new NoSuchElementException();
        }
        Connection c = openConnection();
        Statement s = c.createStatement();
        s.executeUpdate(query);
        c.close();
    }

    /**
     * This method updates a member info
     * @param member is the new info that will be stored
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void updateMember(Member member) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Connection conn = openConnection();
        String myQuery = "UPDATE member SET firstName = ?, lastName = ? ,memID = ?, pnr = ?, nBoats = ? WHERE memID ='"+member.getMemberID()+"'";

        PreparedStatement ps = conn.prepareStatement(myQuery);

        ps.setString(1,member.getMemberFirstName());
        ps.setString(2, member.getMemberLastName());
        ps.setString(3, member.getMemberID());
        ps.setInt(4, member.getMemberPersonalNumber());
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
    public void updateBoat(String ownerID ,Boat boat) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
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
    public Connection openConnection() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {

        DriverManager.setLoginTimeout(5);
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/YachtClubDB","root","");;
        return conn;
    }

}

