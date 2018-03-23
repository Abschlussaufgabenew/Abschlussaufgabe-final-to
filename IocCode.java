package edu.kit.informatik;

import java.util.Comparator;

public class IocCode {
	
	private String country;
	private int id;
	private String ioc;
	private int year;
	
	
	public IocCode(String country,int id , String ioc , int year) {
		this.country = country;
		this.id = id;
		this.ioc = ioc;
		this.year = year;
	}
	
	
	public static Comparator<IocCode> compareIocCodes(){
		Comparator<IocCode> comp = new Comparator<IocCode>() {
			@Override
			public int compare (IocCode IocCode1 , IocCode IocCode2) {
				if(IocCode1.getYear() != IocCode2.getYear()) {
					return Integer.compare(IocCode1.year, IocCode2.year);
				}
				else {
					return Integer.compare(IocCode1.id, IocCode2.id);
				}
			}
		};
		return comp;
	}
	
	
	public int getId() {
		return id;
	}
	
	public String getIoc() {
		return ioc;
	}
	
	public int getYear() {
		return year;
	}
	
	public String getCountry() {
		return country;
	}


	@Override
	public String toString() {
		return year + " " + id + " " + ioc + " " + country;
	}
	
	

}