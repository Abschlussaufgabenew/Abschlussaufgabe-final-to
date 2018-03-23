package edu.kit.informatik;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class SportDiscipline {
	private String discipline;
	private List<Athlete> athletes = new ArrayList<>();
	private Map<String , Athlete> athletesMap = new HashMap<>();
	
	
	public SportDiscipline(String discipline) {
		this.discipline = discipline;
	}
	
	public String getDiscipline() {
		return discipline;
	}
	
	

	public static Comparator<SportDiscipline> compareSportDisciplines(){
		Comparator<SportDiscipline> comp = new Comparator<SportDiscipline>() {
			@Override
			public int compare (SportDiscipline discipline1 , SportDiscipline discipline2) {
				return discipline1.getDiscipline().compareTo(discipline2.getDiscipline());
			}
		};
		return comp;
	}
	
	
	public String toString() {
		return discipline;
	}
	
	public void addAthlete(String id ,Athlete athlete) {
		athletes.add(athlete);
		athletesMap.put(id, athlete);
	}
	
	public List<Athlete> getAthletes(){
		return athletes;
	}


}