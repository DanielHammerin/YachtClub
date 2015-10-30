package controller;

import model.BoatModel;
import view.MainView;

/**
 * Created by Daniel Hammerin & Markus Alshreydeh 2015-10-06
 * w0w0w00w
 */
public class BoatDataHandling {


	BoatModel bm = new BoatModel();
	MainView view = new MainView();

	/**
	 * Method for adding a new boat.
	 */
	public void addNewBoat(String memID){
		view.printMessages("sbn");
		String boatName = view.getInput();
		view.printMessages("sbl");
		int boatLength = (Integer.parseInt(view.getInput()));
		view.printMessages("sbt");
		String boatType = view.getInput();
		String ownerID = memID;

		if (bm.addNewBoat(ownerID, boatName, boatType, boatLength) == true) {
			view.printMessages("ab");
		}
		else {
			view.printMessages("nab");
		}
	}

	/**
	 * Method for changing boat data
	 * @param memID, ownerID of the boat to be changed.
	 */
	public void changeBoatData(String memID){
		view.printMessages("snbn");
		String boatName = view.getInput();
		view.printMessages("snbn");
		int boatLength = (Integer.parseInt(view.getInput()));
		view.printMessages("snbt");
		String boatType = view.getInput();
		String ownerID = memID;

		if (bm.changeBoatData(ownerID, boatName, boatType, boatLength) == true) {
			view.printMessages("cb");
		}
		else {
			view.printMessages("ncb");
		}
	}

	/**
	 * Method for deleting a boat
	 * @param memberID
	 * @param boatName
	 */
	public void deleteBoat(String memberID, String boatName){

		if (bm.deleteBoat(memberID, boatName) == true) {
			view.printMessages("db");
		}
		else {
			view.printMessages("ndb");
		}
	}
}