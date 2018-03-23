package edu.kit.informatik;

import java.util.Comparator;

public class SportsVenue {
	
	private int id ;
	private String countryName;
	private String location;
	private String name;
	private int openingYear;
	private int seatsNumber;
	
	
	public SportsVenue(int id , String countryName , String location , String name , int openingYear , int seatsNumber) {
		this.id = id;
		this.countryName = countryName;
		this.location = location;
		this.name = name;
		this.openingYear = openingYear;
		this.seatsNumber = seatsNumber;
	}
	
	public static Comparator<SportsVenue> compareSportsVenues(){
		Comparator<SportsVenue> comp = new Comparator<SportsVenue>() {
			@Override
			public int compare (SportsVenue sportsVenue1 , SportsVenue sportsVenue2) {
				if(sportsVenue1.getSeatsNumber() != sportsVenue2.getSeatsNumber()) {
					return Integer.compare(sportsVenue1.seatsNumber, sportsVenue2.seatsNumber);
				}
				else {
					return Integer.compare(sportsVenue1.id, sportsVenue2.id);
				}
			}
		};
		return comp;
	}
	
	
	
	
	@Override
	public String toString() {
		return id + " " + location + " " + seatsNumber;
	}

	public String getCountryname() {
		return countryName;
	}

	public int getId() {
		return id;
	}
	
	public int getSeatsNumber() {
		return seatsNumber;
	}
}