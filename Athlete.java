package edu.kit.informatik;

import java.util.Comparator;

public class Athlete {
	
	private String id ;
	private String firstName;
	private String lastName;
	private String countryName;
	private int medalNumber;
	
	public Athlete(String id , String firstName , String lastName , String countryName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.countryName = countryName;
	}

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getCountryName() {
		return countryName;
	}

	public int getMedalNumber() {
		return medalNumber;
	}

	public void setMedalNumber(int medalNumber) {
		this.medalNumber = medalNumber;
	}
	
	public String toString() {
		return id + " " + firstName + " " + lastName + " " + medalNumber;
	}
	
	public static Comparator<Athlete> compareAthletes(){
		Comparator<Athlete> comp = new Comparator<Athlete>() {
			@Override
			public int compare (Athlete athlete1 , Athlete athlete2) {
				if(athlete1.getMedalNumber() != athlete2.getMedalNumber()) {
					return Integer.compare(athlete2.medalNumber, athlete1.medalNumber);
				}
				else {
					return Integer.compare(Integer.parseInt(athlete1.id), Integer.parseInt(athlete2.id));
				}
			}
		};
		return comp;
	}
	
	
	
	
	
	
	
	

}