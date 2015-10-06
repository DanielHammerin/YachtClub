package boatsNHoes;

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

    public static void main(String args[]) {

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
            bdh.
        }
        else if (command.equals(editBoat)) {

        }
        else if (command.equals(deleteBoat)){
            bdh.deleteBoat();
        }
    }
    protected static String getMemID() {
        Scanner scMem = new Scanner(System.in);
        System.out.println("Please enter the ID of the member you wish to change: ");
        String memID = scMem.nextLine();
        return memID.toString();
    }
}
