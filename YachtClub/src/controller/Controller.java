package controller;

import view.MainView;

import java.sql.SQLException;

/**
 * Created by Daniel on 2015-10-22.
 */
public class Controller {
    private static String addNewMember = "addmember";
    private static String editMember  = "editmember";
    private static String deleteMember = "deletemember";
    private static String lookAtMember = "findmember";
    private static String addNewBoat = "addboat";
    private static String editBoat = "editboat";
    private static String deleteBoat = "deleteboat";
    private static String exit = "exit";
    private static String allmembers = "showallmembers";

    MainView view = new MainView();
    MemberDataHandling mdh = new MemberDataHandling();
    BoatDataHandling bdh = new BoatDataHandling();

    public boolean commandControl() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {



        while (true) {
            view.displayHeader();
            String cmd = view.getInput();

            if (cmd.equals(addNewMember)) {
                mdh.createMember();
            }
            else if (cmd.equals(editMember)) {
                String memID = view.getMemID();
                mdh.changeMember(memID);
            }
            else if (cmd.equals(deleteMember)) {
                String memID = view.getMemID();
                mdh.deleteMember(memID);
            }
            else if (cmd.equals(lookAtMember)) {
                boolean v = false;
                v = view.isVerbose(v);
                String memID = view.getMemID();
                mdh.lookUpMember(memID, v);
            }
            else if (cmd.equals(addNewBoat)) {
                String ownerID = view.getMemID();
                bdh.addNewBoat(ownerID);
            }
            else if (cmd.equals(editBoat)) {
                String ownerID = view.getMemID();
                bdh.changeBoatData(ownerID);
            }
            else if (cmd.equals(deleteBoat)) {
                String ownerID = view.getMemID();
                String boatName = view.getBoatName();
                bdh.deleteBoat(ownerID, boatName);
            }
            else if (cmd.equals(allmembers)) {
                boolean verbose = false;
                verbose = view.isVerbose(verbose);
                mdh.displayAllMembers(verbose);
            }
            else if (cmd.equals(exit)) {
                return false;
            }
            else {  //If there is no valid input string
                view.printMessages("idiot");
            }
        }
    }
}
