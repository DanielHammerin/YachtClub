package boatsNHoes;

import java.util.ArrayList;
import java.util.Scanner;

public class BoatDataHandling {
	
	Boat boat = new Boat();
	
	ArrayList<String> boatArr = new ArrayList<String>();
	
	Scanner scan = new Scanner(System.in);
	public BoatDataHandling(){
		
		System.out.println("Set Boat Name");
		boat.setBoatName(scan.nextLine());
		System.out.println("Set Boat's Length");
		boat.setBoatLength(scan.nextLine());
		
	}
	
	public void registerNewBoat(){
		


	}
	
	public void changeBoatData(){
		
		
	}
	
	public void deleteBoat(){}
	
	
}