package boatsNHoes;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Daniel on 2015-10-06.
 */
public class Main {

    private static String addNewMember = "addmember";
    private static String editMember  = "editmember";
    private static String deleteMember = "deletemember";
    private static String lookAtMember = "findmember";
    private static String addNewBoat = "addboat";
    private static String editBoat = "editboat";
    private static String deleteBoat = "deleteboat";

    public static void main(String args[]) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        System.out.println("Enter any of the commands to do various things.");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        MemberDataHandling mdh = new MemberDataHandling();
        BoatDataHandling bdh = new BoatDataHandling();


        if (command.equals(addNewMember)) {
            mdh.createMember();
        }
        else if (command.equals(editMember)) {
            String memID = getMemID();
            mdh.changeMember(memID);
        }
        else if (command.equals(deleteMember)) {
            String memID = getMemID();
            mdh.deleteMember(memID);
        }
        else if (command.equals(lookAtMember)) {
            String memID = getMemID();
            mdh.lookUpMember(memID);
        }
        else if (command.equals(addNewBoat)) {
            String ownerID = getMemID();
            bdh.addNewBoat(ownerID);
        }
        else if (command.equals(editBoat)) {
            String ownerID = getMemID();
            String boatName = getBoatName();
            bdh.changeBoatData(ownerID, boatName);
        }
        else if (command.equals(deleteBoat)){
            String ownerID = getMemID();
            String boatName = getBoatName();
            bdh.deleteBoat(ownerID, boatName);
        }
    }
    protected static String getMemID() {
        Scanner scMem = new Scanner(System.in);
        System.out.println("Please enter the ID of the member you wish to change: ");
        String memID = scMem.nextLine();
        return memID;
    }

    protected static String getBoatName() {
        Scanner scBoatName = new Scanner(System.in);
        System.out.println("Please enter the name of the boat to be accessed: ");
        String boatName = scBoatName.nextLine();
        return boatName;
    }
}
