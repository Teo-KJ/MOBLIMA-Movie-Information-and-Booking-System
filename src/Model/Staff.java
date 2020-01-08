package Model;

public class Staff {
	private int staffID;
	private String name, emailAddress, password;
	
	public Staff(int staffID, String name, String emailAddress, String password) {
		this.setStaffID(staffID);
		this.setName(name);
		this.setEmailAddress(emailAddress);
		this.setPassword(password);
	}

	public int getStaffID() {
		return staffID;
	}
	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
