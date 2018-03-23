package edu.kit.informatik;

public class Admin {
	private String firstName;
	private String lastName;
	private String userName;
	private String passWord;
	
	
	public Admin(String firstName , String lastName , String userName , String passWord) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.passWord = passWord;
		this.userName = userName;
	}
	
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassWord() {
		return passWord;
	}

}