package controller;

import javafx.scene.control.Alert;
import boatsNHoes.*;
import java.sql.*;
import java.util.*;

/**
 * Contains methods to interact with the data base.
 * Created by Daniel Hammerin 2015.
 */
public class SQLDAO {

    /**
     * Saves the passed member into the database.
     * @param member, the member to be saved.
     */
    public void saveMember(Member member) {

        String query2 = "insert into member(firstName, lastName, memID, pnr, nBoats)"
                + " values (?, ?, ?, ?, ?)";

        Properties properties = new Properties();
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
     * Retrieves all members from the memberlist table.
     * @return
     */
    public LinkedList<Member> getAllMembers() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        String myQuery = "SELECT * FROM members";

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
        String myQuery = "SELECT *\n" +
                "FROM member\n" +
                "WHERE memID = '"+memID+"'" ;
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
    public TreeSet<Boat> boats(Member member) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        TreeSet<Boat> boats = new TreeSet<Boat>();
        Connection conn = openConnection();

        String myQuery = "SELECT *\n" +
                "FROM boat\n" +
                "WHERE ownerID = '"+member.getMemberID()+"'" ;

        ResultSet rs = conn.createStatement().executeQuery(myQuery);

        while(rs.next()){
            Boat b = new Boat();
            b.setOwnerID(rs.getString("ownerID"));
            Boat boat = new Boat(rs.getString("boatName"),rs.getString("boatType"),rs.getString("boatLength"),rs.getString("ownerID"));
            boats.add(boat);
        }
        conn.close();
        return  boats;
    }


    /**
     * Compares the timelines in the data base looking for duplicate timelines.
     * @param dayTimeline
     * @return
     * @throws Exception
     */
    public boolean isThereADuplicateTimeline(DayTimeline dayTimeline) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        LinkedList<DayTimeline> allTimelines2 = getAllTimelines();
        for(DayTimeline dtl : allTimelines2){
            if(Objects.equals(dtl.getTitle(), dayTimeline.getTitle())){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Duplicate Timeline");
                a.setContentText("There exists an timeline called"+dtl.getTitle());
                return true;
            }
        }
        return  false;
    }

    public boolean isThereADuplicateEvent(MyEvent myEvent) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        Connection conn = openConnection();
        String myQuery;
        DayTimeline dayTimeline;

        if(myEvent instanceof  EventNT){
            EventNT eventNT = (EventNT)myEvent;
            dayTimeline = eventNT.getDayTimeline();
            myQuery= "SELECT *\n" +
                    "FROM eventnotime\n" +
                    "WHERE title = '"+myEvent.getTitle()+"'and timeline ='"+dayTimeline.getTitle()+"'" ;
        }else{
            EventTime eventTime = (EventTime)myEvent;
            dayTimeline = eventTime.getDayTimeline();
            myQuery= "SELECT *\n" +
                    "FROM eventtime\n" +
                    "WHERE title = '"+myEvent.getTitle()+"' and timeline ='"+dayTimeline.getTitle()+"'" ;
        }

        ResultSet rs = conn.createStatement().executeQuery(myQuery);
        while(rs.next()){
            String s = rs.getString("title");
            if(Objects.equals(s, myEvent.getTitle())){
                return true;
            }
        }
        return  false;
    }

    /**
     * Deletes a member from the database.
     * @param memID, the member ID of the member to be removed
     */
    public void deleteMember(String memID) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        String myQuery = "DELETE from member where memID='"+memID+"'";

        Connection c = openConnection();
        Statement s = c.createStatement();
        s.executeUpdate(myQuery);
        c.close();
    }

    public void removeBoat(String memID, Boat boat) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

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
     * @param ownerID
     * @param boat
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
        ps.setString(3, boat.getBoatLength());
        ps.setString(4, boat.getOwnerID());

        ps.executeUpdate();
        ps.close();
        conn.close();
    }


    public Connection openConnection() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {

        DriverManager.setLoginTimeout(5);
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/YachtClubDB","root","");;
        return conn;
    }

}

