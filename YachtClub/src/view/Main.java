package view;

import model.BoatDataHandling;
import model.MemberDataHandling;

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
    private static String exit = "exit";
    private static String allmembers = "showallmembers";
    private static int x = 1;

    public static void main(String args[]) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        while (x != 0) {
            System.out.println("=====================================================");        //Header for program.
            System.out.println("Enter any of the commands to do various things.");
            System.out.println("Cmd: 'addmember' = add new member");
            System.out.println("Cmd: 'editmember' = edit member");
            System.out.println("Cmd: 'deletemember' = delete member");
            System.out.println("Cmd: 'findmember' = look up a member");
            System.out.println("Cmd: 'showallmembers' = show all members");
            System.out.println("Cmd: 'addboat' = add new boat");
            System.out.println("Cmd: 'editboat' = edit boat data");
            System.out.println("Cmd: 'deleteboat' = delete boat");
            System.out.println("cmd: 'exit' = exit program");

            MemberDataHandling mdh = new MemberDataHandling();
            BoatDataHandling bdh = new BoatDataHandling();


            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();


            if (command.equals(addNewMember)) {
                mdh.createMember();
            } else if (command.equals(editMember)) {
                String memID = getMemID();
                mdh.changeMember(memID);
            } else if (command.equals(deleteMember)) {
                String memID = getMemID();
                mdh.deleteMember(memID);
            } else if (command.equals(lookAtMember)) {
                boolean v = false;
                v = isVerbose(v);
                String memID = getMemID();
                mdh.lookUpMember(memID, v);
            } else if (command.equals(addNewBoat)) {
                String ownerID = getMemID();
                bdh.addNewBoat(ownerID);
            } else if (command.equals(editBoat)) {
                String ownerID = getMemID();
                String boatName = getBoatName();
                bdh.changeBoatData(ownerID, boatName);
            } else if (command.equals(deleteBoat)) {
                String ownerID = getMemID();
                String boatName = getBoatName();
                bdh.deleteBoat(ownerID, boatName);
            } else if (command.equals(allmembers)) {
                boolean verbose = false;
                verbose = isVerbose(verbose);
                mdh.displayAllMembers(verbose);
            } else if (command.equals(exit)) {
                x = 0;
            }
            else {  //If there is no valid input string
                System.out.println("Operator, there is no such command. The mental health service has been notified.");
            }
        }
    }
    protected static String getMemID() { //Called so the user can chose which member to access.
        Scanner scMem = new Scanner(System.in);
        System.out.println("Please enter the ID of the member you wish to access: ");
        String memID = scMem.nextLine();
        return memID;
    }

    protected static String getBoatName() {   //called so the user can chose which boat to access.
        Scanner scBoatName = new Scanner(System.in);
        System.out.println("Please enter the name of the boat to be accessed: ");
        String boatName = scBoatName.nextLine();
        return boatName;
    }
    protected static boolean isVerbose(boolean verbose) {   //Does the user want a verbose or compact list.
        Scanner sc = new Scanner(System.in);
        System.out.println("Show compact or verbose member list?");
        System.out.println("c = compact / v = verbose");
        String choice = sc.nextLine();
        if (choice.equals("v")) {
            verbose = true;
            return verbose;
        }
        else if (choice.equals("c")){
            verbose = false;
            return verbose;
        }
        else {
            System.out.println("I'm terribly sorry, operator, but that doesn't answer my question.");
            return isVerbose(verbose);
        }

    }
}
