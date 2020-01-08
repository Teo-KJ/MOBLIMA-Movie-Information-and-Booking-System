package Model;

import java.io.Serializable;

public class Customer implements Serializable{
	int ID;
	private String name, DOB, emailAddress, password;
	private int age;
	
	public Customer(int ID, String name, String DOB, String emailAddress, 
					String password, int age) {
		this.ID = ID;
		this.name = name;
		this.DOB = DOB;
		this.emailAddress = emailAddress;
		this.password = password;
		this.age = age;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	public int getID() {
		return ID;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setDOB(String DOB) {
		this.DOB = DOB;
	}
	public String getDOB() {
		return DOB;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	
	public boolean equals(Object o) {
		if (o instanceof Customer) {
			Customer c = (Customer)o;
			return (getName().equals(c.getName()));
		}
		return false;
	}
}
