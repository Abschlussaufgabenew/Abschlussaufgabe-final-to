package edu.kit.informatik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OlympicGames {
	
	private Map<String , Admin> admins = new HashMap<>();
	private List<Admin> loggedInUsers = new LinkedList<>();
	private Map<Integer , SportsVenue> sportsVenues = new HashMap<>();
	private List<IocCode> iocCodes = new ArrayList<>();
	private List<OlympicSport> sports = new ArrayList<>();
	
	
	
	
	public void addAdmin(String firstName , String  lastName , String userName , String passWord) throws InputException{
		if(userName.length() < 4 || userName.length() > 8) {
			throw new InputException("Please choose a valid username");
		}
		else if(passWord.length() < 8 || passWord.length() > 12) {
			throw new InputException("Please choose a valid password");
		}
		else {
		Admin admin = new Admin (firstName , lastName , userName , passWord);
		if(admins.containsKey(userName)) {
			throw new InputException("Username not available ");
		}else {
			admins.put(userName, admin);
		 }
		}
	}
	
	public void loginAdmin(String userName , String passWord) throws InputException{
		if(!admins.containsKey(userName)) {
			throw new InputException("User does not exist yet");
		}
		else if (!passWord.equals(admins.get(userName).getPassWord())) {
			throw new InputException("Incorrect password");
		}
		else {
			loggedInUsers.add(admins.get(userName));
		}
	}
	
	public void logoutAdmin() throws InputException{
		if(loggedInUsers.isEmpty()) {
			throw new InputException("No logged in users");
		}else {
			loggedInUsers.clear();
		}
	}
	
	public void addSportsVenue(int id , String countryName , String location , String name , int openingYear , int seatsNumber) 
	       throws InputException{
		SportsVenue sportsVenue = new SportsVenue(id , countryName , location , name , openingYear , seatsNumber);
		if(sportsVenues.containsKey(id)) {
			throw new InputException ("Sports venue already exists");
		}
		else {
			sportsVenues.put(id, sportsVenue);
		}
		
	}
	
	public void listSportsVenues (String countryName) throws InputException{
		ArrayList<SportsVenue> sportsVenuesToList = new ArrayList<>();
		int n = 1;
		for(Map.Entry<Integer , SportsVenue> entry : sportsVenues.entrySet()) {
			if(entry.getValue().getCountryname().equals(countryName)) {
				sportsVenuesToList.add(entry.getValue());
			}
		}
		
		Collections.sort(sportsVenuesToList , SportsVenue.compareSportsVenues());
		for(SportsVenue sportsVenue : sportsVenuesToList) {
			Terminal.printLine(n + " " + sportsVenue);
			n++;
		}
		
	}
	

	public void addOlympicSport(String sportsType , String discipline) throws InputException{
		SportDiscipline sportDiscipline = new SportDiscipline(discipline);
		OlympicSport olympicSport = new OlympicSport(sportsType);
		if(sports.contains(olympicSport)) {
			for(OlympicSport sport : sports) {
				if(sport.equals(olympicSport)) {
					if(sport.getDisciplines().contains(sportDiscipline)) {
						throw new InputException("Sport discipline already added");
					}else {
						sport.addDiscipline(sportDiscipline);
					}
				}
			}
		}else {
			olympicSport.addDiscipline(sportDiscipline);
			sports.add(olympicSport);
		}
		
	}
	
	public void listOlympicSports() throws InputException{
		Collections.sort(sports , OlympicSport.compareOlympicSports());
		for(OlympicSport sport : sports) {
			Collections.sort(sport.getDisciplines() , SportDiscipline.compareSportDisciplines());
			for(SportDiscipline sportDiscipline : sport.getDisciplines()) {
				Terminal.printLine(sport + " " + sportDiscipline);
			}
		}
	}
	
	public void addIocCode(String country , int id , String ioc , int year) {
		IocCode iocCode = new IocCode(country,id,ioc,year);
		iocCodes.add(iocCode);
	}
	
	public void listIocCodes() {
		Collections.sort(iocCodes , IocCode.compareIocCodes());
		for(IocCode iocCode : iocCodes) {
			Terminal.printLine(iocCode);
		}
	}
	
	public void addAthlete(String id , String firstName , String lastName , String country , String sport , String discipline) throws InputException{
		OlympicSport olympicSport = new OlympicSport(sport);
		SportDiscipline sportDiscipline = new SportDiscipline(discipline);
		
		if(id.length() != 4) {
			throw new InputException("Invalid ID");
		}
		
		for(OlympicSport olympicSportID : sports) {
			if(olympicSportID.equals(olympicSport)) {
				for(SportDiscipline sportDisciplineID : olympicSportID.getDisciplines()) {
					if(sportDisciplineID.equals(sportDiscipline)) {
						for(Athlete athleteID : sportDisciplineID.getAthletes()) {
							if(athleteID.getId().equals(id) && !athleteID.getFirstName().equals(firstName)) {
								throw new InputException("ID unavailable");
							}
						}
					}
				}
			}
		}
		
		for(IocCode iocCode : iocCodes) {
			if(!iocCode.getCountry().equals(country)) {
				throw new InputException("Country's IOC ist not registred");
			}
		}
	
		
		if(!sports.contains(olympicSport)) {
			throw new InputException("Sport not registred");
		}
		
		Athlete athlete = new Athlete(id , firstName , lastName , country);
		for(OlympicSport olympicSport1 : sports) {
			if(olympicSport1.getSportsType().equals(sport)) {
				for(SportDiscipline sportDiscipline1 : olympicSport1.getDisciplines()) {
					if(sportDiscipline1.getDiscipline().equals(discipline)) {
						sportDiscipline1.addAthlete(id,athlete);
					}
				}
			}
		}
		
	}
	
	public void summaryAthletes(String sport , String discipline) throws InputException{
		OlympicSport testSport = new OlympicSport(sport);
		SportDiscipline testDiscipline = new SportDiscipline(discipline);
		if(!sports.contains(testSport)) {
			throw new InputException("Sport is not registred");
		}
		
		for(OlympicSport olympicSport : sports) {
			if(olympicSport.equals(testSport)) {
				for(SportDiscipline sportDiscipline : olympicSport.getDisciplines()) {
					Collections.sort(sportDiscipline.getAthletes() , Athlete.compareAthletes());
					for(Athlete athlete : sportDiscipline.getAthletes()) {
						Terminal.printLine(athlete);
					}
				}
			}
		}
	
	}
	}
	
