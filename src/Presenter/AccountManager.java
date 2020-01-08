package Presenter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import Model.Customer;
import Model.Order;
import Model.Staff;

public class AccountManager {
	static Scanner sc = new Scanner(System.in);
	static int choice;
	public static final String SEPARATOR = "|";
	public static final String customerFile = "datafiles/customerFile.txt";
	public static final String staffFile = "datafiles/staffFile.txt";
	
	public AccountManager() {
		
	}
	
//	Customer
	public static void createCustAccount() throws ParseException, IOException {
		System.out.println("Please complete some details to create an account.");
		String name = MenuSelection.passChoiceString("Name: ");
		String DOB = MenuSelection.passChoiceString("Date of birth (format: DD-MM-YYYY): ");
		String email = MenuSelection.passChoiceString("Email address: ");
		String password = MenuSelection.passChoiceString("Password: ");
		System.out.println("Your username will be your email address. Your account has been created.");
		int ID =  (int) (Math.random()*100000);
		
		Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(DOB);
		LocalDate current = java.time.LocalDate.now();
		String current2Str = current.toString();
		Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(current2Str);
		int age = date2.getYear() - date1.getYear();
		
		Customer cust = new Customer(ID, name, DOB, email, password, age);
		saveCustomer(customerFile, cust);
	}
	
	public static void saveCustomer(String filename, Customer c) throws IOException {
		List alw = new ArrayList();
		
		StringBuilder st =  new StringBuilder();
		st.append(c.getID());
		st.append(SEPARATOR);
		st.append(c.getName().trim());
		st.append(SEPARATOR);
		st.append(c.getDOB().toString());
		st.append(SEPARATOR);
		st.append(c.getEmailAddress().trim());
		st.append(SEPARATOR);
		st.append(c.getPassword().trim());
		st.append(SEPARATOR);
		st.append(c.getAge());
		alw.add(st.toString());

		DataManager.write(filename, alw);
	}
	
	public static ArrayList<Customer> getCustomerList() throws IOException{
		ArrayList stringArray = (ArrayList)DataManager.read(customerFile);
		ArrayList<Customer> allCustomers = new ArrayList<Customer>();
		
		for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

			int ID = Integer.parseInt(star.nextToken().trim());
			String name = star.nextToken().trim();
			String DOB = star.nextToken().trim();
			String email = star.nextToken().trim();
			String password = star.nextToken().trim();
			int age = Integer.parseInt(star.nextToken().trim());
			Customer cust = new Customer(ID, name, DOB, email, password, age);
			allCustomers.add(cust);
		}
		return allCustomers;
	}
	
	public static void readCustomerAccounts() throws IOException{
		// read String from text file
		ArrayList<Customer> stringArray = getCustomerList();
		System.out.println("");
		int i=1;
		System.out.println("The following are the customers' details.");
		for (Customer c: stringArray) {
			int ID = c.getID();
			String name = c.getName();
			String DOB = c.getDOB();
			String email = c.getEmailAddress();
			String password = c.getPassword();
			int age = c.getAge();
			
			System.out.println(i + ". "
							  +"\tID: " + ID + "\n"
							  +"\tName: " + name + "\n"
							  +"\tDate of birth: " + DOB + "\n"
							  +"\tEmail address: " + email + "\n"
							  +"\tPassword: " + password + "\n"
							  +"\tAge: " + age + "\n");
			i++;
		}
	}
	
	public static boolean verifyCustomer(String username, String password) throws IOException {
		ArrayList<Customer> alr = getCustomerList();
		
		for (Customer obj : alr) {
			if (((obj.getEmailAddress()).equalsIgnoreCase(username)) && (obj.getPassword()).equals(password))
				return true;
		}
		return false;
	}
	
	public static void viewCustomerProfile(String username) throws IOException {
		ArrayList<Customer> alr = getCustomerList();
		ArrayList<Order> ord = BookingManager.getOrders();
		
		for (Customer cust: alr) {
			if ((cust.getEmailAddress()).equalsIgnoreCase(username)) {
				int ID = cust.getID();
				String name = cust.getName();
				String DOB = cust.getDOB();
				String email = cust.getEmailAddress();
				String password = cust.getPassword();
				int age = cust.getAge();
				
				System.out.println("\nID: " + ID + "\n"
								  +"Name: " + name + "\n"
								  +"Date of birth: " + DOB + "\n"
								  +"Email address: " + email + "\n"
								  +"Password: " + password + "\n"
								  +"Age: " + age + "\n");
				
				System.out.println("Your Booking history:");
				for (Order o: ord) {
					if (o.getCustomerEmail().equalsIgnoreCase(username)) {
						System.out.println("Showtime: " + o.getShowtime() +
										   "\nSeat Number: " + o.getSeatNum() + "\n");
					}
				}
			}
		}
	}
	
//	Staff
	public static void createStaffAccount() throws ParseException, IOException {
		System.out.println("Please complete some details to create an account.");
		int staffID = (int)(Math.random()*100000);
		String name = MenuSelection.passChoiceString("Please enter the staff name.");
		String email = MenuSelection.passChoiceString("Email address: ");
		String password = MenuSelection.passChoiceString("Password: ");
		System.out.println("Your username will be your email address. Your account has been created.");
		
		Staff staff = new Staff(staffID, name, email, password);
		saveStaff(staffFile, staff);
	}
	
	public static void saveStaff(String filename, Staff stf) throws IOException {
		List alw = new ArrayList();
		
		StringBuilder st =  new StringBuilder();
		st.append(stf.getStaffID());
		st.append(SEPARATOR);
		st.append(stf.getName().trim());
		st.append(SEPARATOR);
		st.append(stf.getEmailAddress().trim());
		st.append(SEPARATOR);
		st.append(stf.getPassword().trim());
		alw.add(st.toString());

		DataManager.write(filename, alw);
	}
	
	public static ArrayList<Staff> getStaffList() throws IOException{
		ArrayList stringArray = (ArrayList)DataManager.read(staffFile);
		ArrayList<Staff> allStaff = new ArrayList<Staff>();
		
		for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

			int ID = Integer.parseInt(star.nextToken().trim());
			String name = star.nextToken().trim();
			String email = star.nextToken().trim();
			String password = star.nextToken().trim();
			Staff stf = new Staff(ID, name, email, password);
			allStaff.add(stf);
		}
		return allStaff;
	}
	
	public static boolean verifyStaff() throws IOException {
		ArrayList<Staff> alr = getStaffList();
		
		String username = MenuSelection.passChoiceString("Username: ");
		String password = MenuSelection.passChoiceString("\nPassword: ");
		for (Staff obj : alr) {
			if (((obj.getName().equalsIgnoreCase(username)) && (obj.getPassword()).equals(password)))
				return true;
		}
		return false;
	}
}
