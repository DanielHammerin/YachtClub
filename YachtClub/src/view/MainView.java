package view;

import controller.Controller;

import java.util.Scanner;

/**
 * Created by Daniel on 2015-10-22.
 */
public class MainView {

    public void displayHeader() {
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
        System.out.println("Cmd: 'exit' = exit program");
    }

    public String getInput() {
        Scanner sc = new Scanner(System.in);
        String entry = sc.nextLine();
        return entry;
    }

    public void printMember(String n1, String n2, String ID, String pnr, int nBoats) {
        System.out.println("==========Member data==========");
        System.out.println("Member first name: " + n1);
        System.out.println("Member last name: " + n2);
        System.out.println("Member ID: " + ID);
        System.out.println("Member personal number: " + pnr);
        System.out.println("Member number of boats: " + nBoats);
    }

    public void printMemberBoatLine(String id) {
        System.out.println("==========" + id + "boats==========");
    }

    public void printCompactMember(String n1, String n2, String ID, int nb) {
        System.out.println("==========Member data==========");
        System.out.println("Member first name: " + n1);
        System.out.println("Member last name: " + n2);
        System.out.println("Member ID: " + ID);
        System.out.println("Member number of boats: " + nb);
    }

    public void printMemberBoats(String bn, String bt, int bl) {
        System.out.println();
        System.out.println("=======================");
        System.out.println("Boat name: " + bn);
        System.out.println("Boat type: " + bt);
        System.out.println("Boat length: " + bl);
        System.out.println("=======================");
    }

    public String getMemID() { //Called so the user can chose which member to access.
        System.out.println("Please enter the ID of the member you wish to access: ");
        String memID = getInput();
        return memID;
    }

    public String getBoatName() {   //called so the user can chose which boat to access.
        System.out.println("Please enter the name of the boat to be accessed: ");
        String boatName = getInput();
        return boatName;
    }
    public boolean isVerbose(boolean verbose) {   //Does the user want a verbose or compact list.
        System.out.println("Show compact or verbose member list?");
        System.out.println("c = compact / v = verbose");
        String choice = getInput();
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
    public void printMessages(String s) { //Method for printing proper and relevant error messages.
        if (s == "ab") {
            System.out.println("Boat added!");
        }
        else if (s == "db") {
            System.out.println("Boat Deleted!");
        }
        else if (s == "cb") {
            System.out.println("Boat data changed!");
        }
        else if (s == "am") {
            System.out.println("Member Added!");
        }
        else if (s == "dm") {
            System.out.println("Member Deleted!");
        }
        else if (s == "cm") {
            System.out.println("Member data changed!");
        }
        if (s == "nab") {
            System.out.println("Boat could not be added.");
        }
        else if (s == "ndb") {
            System.out.println("Boat could no be deleted.");
        }
        else if (s == "ncb") {
            System.out.println("Boat data could not be changed.");
        }
        else if (s == "nam") {
            System.out.println("Member could not be added.");
        }
        else if (s == "ndm") {
            System.out.println("Member could no be deleted.");
        }
        else if (s == "ncm") {
            System.out.println("Member data could not be changed.");
        }
        else if (s == "nmwi") {
            System.out.println("There is no member with that ID.");
        }
        else if (s == "mus") {
            System.out.println("Member data updated successfully!");
        }
        else if (s == "nmf") {
            System.out.println("No such member found!");
        }
        else if (s == "idiot") {
            System.out.println("Operator, there is no such command. The mental health service has been notified.");
        }
        else if (s == "emfn") {
            System.out.println("Enter the new member's first name: ");
        }
        else if (s == "emln") {
            System.out.println("Enter the new member's last name: ");
        }
        else if (s == "empnr") {
            System.out.println("Enter the new member's personal number: ");
        }
        else if (s == "cmfn") {
            System.out.println("Change member's first name to: ");
        }
        else if (s == "cmln") {
            System.out.println("Change member's last name to: ");
        }
        else if (s == "cmpnr") {
            System.out.println("Change member's personal number to: ");
        }
        else if (s == "sbn") {
            System.out.println("Set Boat Name");
        }
        else if (s == "sbl") {
            System.out.println("Set Boat's Length");
        }
        else if (s == "sbt") {
            System.out.println("Set Boat's Type");
        }
        else if (s == "snbn") {
            System.out.println("Set a new Boat Name");
        }
        else if (s == "snbl") {
            System.out.println("Set a new Boat Length");
        }
        else if (s == "snbt") {
            System.out.println("Set a new Boat Type");
        }
    }


}
