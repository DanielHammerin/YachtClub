package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Markus Alshraydeh 2015-10-01
 */
public class Boat {
	
	private String boatName;
	private String boatType;
	private int boatLength;
	private String ownerID;
	
	public Boat(String name,String type, int length, String owner) {
		boatName = name;
		boatType = type;
		boatLength = length;
		ownerID = owner;
	}
	public Boat () { }


	public void setBoatName(String boatName){
		this.boatName = boatName;
	}
	
	public void setBoatType(String boatType){
		this.boatType = boatType;
	}
	
	public void setBoatLength(int boatLength){
		
		this.boatLength = boatLength;
	}
	public void setOwnerID(String ID) {
		this.ownerID = ID;
	}
	

	public String getBoatName()
	{
		return boatName;	
	}

	public String getBoatType()
	{
		return boatType;	
	}
	
	public int getBoatLength()
	{
		return boatLength;
	}

	public String getOwnerID() {
		return ownerID;
	}


}


