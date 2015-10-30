package model;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Daniel on 2015-10-22.
 */
public class BoatModel {

    /**
     * @param memberID
     * @param boatName
     * @param boatType
     * @param boatLength
     */
    public boolean addNewBoat(String memberID, String boatName, String boatType, int boatLength) {
        Boat boat = new Boat();

        boat.setBoatName(boatName);
        boat.setBoatType(boatType);
        boat.setBoatLength(boatLength);
        boat.setOwnerID(memberID);

        SQLDAO.saveBoat(memberID, boat);
        try {
            ArrayList<Member> ml = SQLDAO.getAllMembers();
            for (int i = 0; i < ml.size(); i++) {
                if (ml.get(i).getMemberID().equals(memberID)) {
                    System.out.println(ml.get(i).getMemberID());
                    ml.get(i).setMemberNBoats(ml.get(i).getMemberNBoats() + 1);
                    SQLDAO.updateMember(ml.get(i));
                    return true;
                }
            }
        } catch(ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            System.out.println("Database connection error.");
            e.printStackTrace();
        }
        return false;
    }

    public boolean changeBoatData(String memberID, String boatName, String boatType, int boatLength) {

        try {
            ArrayList<Boat> boatList = SQLDAO.getAllBoats();
            boolean memHasBoats = false;
            boolean boatByName = false;
            for(int i = 0; i < boatList.size(); i++) {
                if(boatList.get(i).getOwnerID().equals(memberID)) {
                    memHasBoats = true;
                    if (boatList.get(i).getBoatName().equals(boatName)) {
                        boatByName = true;
                        boatList.get(i).setBoatName(boatName);
                        boatList.get(i).setBoatLength(boatLength);
                        boatList.get(i).setBoatType(boatType);

                        SQLDAO.updateBoat(memberID, boatList.get(i));
                        return true;
                    }
                }
            }
        }catch(ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e){
            System.out.println("Database connection error.");
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteBoat(String memberID, String boatName) {
        try {
            ArrayList<Boat> boatList = SQLDAO.getAllBoats();
            ArrayList<Member> memList = SQLDAO.getAllMembers();
            boolean memCheck = false;
            boolean boatCheck = false;

            for (int i = 0; i < memList.size(); i++) {
                if (memList.get(i).getMemberID().equals(memberID)) {
                    memCheck = true;
                    for (int j = 0; j < boatList.size(); j++) {
                        if (boatList.get(j).getOwnerID().equals(memberID) && boatList.get(j).getBoatName().equals(boatName)) {
                            boatCheck = true;
                            memList.get(i).setMemberNBoats(memList.get(i).getMemberNBoats() - 1);				//Remove 1 boat from number of boats.

                            SQLDAO.updateMember(memList.get(i));
                            SQLDAO.deleteBoat(memberID, boatList.get(j).getBoatName());
                            return true;
                        }
                    }

                }
            }
        } catch(ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            System.out.println("Database connection error.");
            e.printStackTrace();
        }
        return false;
    }
}
