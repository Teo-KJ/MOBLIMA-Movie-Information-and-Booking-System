package Presenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import Model.Cinema;
import Model.CinemaTheatre;
import Model.Customer;
import Model.Order;
import Model.Sales;
import Model.Showtime;

public class BookingManager {
	static Scanner sc = new Scanner(System.in);
	public static final String SEPARATOR = "|";
	public static final String movieFile = "datafiles/movieFile.txt";
	public static final String orderFile = "datafiles/orderFile.txt";
	public static final String salesFile = "datafiles/salesFile.txt";
	
	public BookingManager() {
		
	}
	
	public static void viewShowtimesFromMovie() throws IOException {
		ArrayList movieArray = (ArrayList)DataManager.read(movieFile);
		ArrayList<Showtime> showTimeArray = ShowtimeManager.getShowtimesList();
		ArrayList<Cinema> cinemaArray = CinemaManager.getCinemasList();
		MovieManager.readMovies();
		int choice = sc.nextInt();
		boolean check;
		int j=1;
		
		if (!(MenuSelection.select_choice(1, movieArray.size(), choice))) {
			System.out.println("Please enter the valid movie from the list.");
		}
		
		for (int i=0 ; i<movieArray.size() ; i++) {
			check = false;
			if (choice == i+1) {
				String movie_st = (String)movieArray.get(i);
				StringTokenizer movie_star = new StringTokenizer(movie_st , SEPARATOR);
				movie_star.nextToken().trim();
				String name = movie_star.nextToken().trim();
				System.out.print("Movie: " + name);
				System.out.println("\nShowtimes");
				
				for (Showtime s: showTimeArray) {
					if (name.equalsIgnoreCase(s.getMovie())) {
						for (Cinema c: cinemaArray) {
							
							for (CinemaTheatre ct: c.getCinemaTheatre()) {
								if (s.getCinemaTheatre() == ct.getTheatreID()) {
									System.out.println(j + ". " + ct.getCinemaName() + ": " + s.getTime());
									check = true;
									j++;
								}
							}
						}
					}
					
				}
				if (!check) {
					System.out.println("\nNo showtimes available.");
				}
			}
		}
	}
	
	public static Showtime viewShowtimesForBooking() throws IOException {
		ArrayList movieArray = (ArrayList)DataManager.read(movieFile);
		ArrayList<Showtime> showTimeArray = ShowtimeManager.getShowtimesList();
		ArrayList<Cinema> cinemaArray = CinemaManager.getCinemasList();
		MovieManager.readMovies();
		int choice = sc.nextInt();
		boolean check;
		int j=1;
		ArrayList<Showtime> showtime = new ArrayList<Showtime>();
		Showtime show = null;
		
		if (!(MenuSelection.select_choice(1, movieArray.size(), choice))) {
			System.out.println("Please enter the valid movie from the list.");
		}
		
		for (int i=0 ; i<movieArray.size() ; i++) {
			check = false;
			if (choice == i+1) {
				String movie_st = (String)movieArray.get(i);
				StringTokenizer movie_star = new StringTokenizer(movie_st , SEPARATOR);
				movie_star.nextToken().trim();
				String name = movie_star.nextToken().trim();
				System.out.print("Movie: " + name);
				System.out.println("\nShowtimes");
				
				for (Showtime s: showTimeArray) {
					if (name.equalsIgnoreCase(s.getMovie())) {
						for (Cinema c: cinemaArray) {
							
							for (CinemaTheatre ct: c.getCinemaTheatre()) {
								if (s.getCinemaTheatre() == ct.getTheatreID()) {
									System.out.println(j + ". " + ct.getCinemaName() + ": " + s.getTime());
									check = true;
									showtime.add(s);
									j++;
								}
								
							}
						}
					}
				}
				if (!check) {
					System.out.println("\nNo showtimes available.");
					return null;
				}
			}
		}
		
		System.out.println("Please select the showtime");
		int showtimeChoice = sc.nextInt();
		int i = 1;
		
		for (Showtime st: showtime) {
			if (i==showtimeChoice)
				show=st;
			i++;
		}
		return show;
}
	
	public static void bookTix() throws IOException {
		Showtime movieChoice = viewShowtimesForBooking();
		
		if (movieChoice == null) return;
		
		int j=1;
		ArrayList<Cinema> cinemaArray = CinemaManager.getCinemasList();
		
		for (Cinema c: cinemaArray) {
			for (CinemaTheatre ct: c.getCinemaTheatre()) {
				if (ct.getTheatreID() == movieChoice.getCinemaTheatre()) {
					System.out.println("The cinema layout is as follows.\n");
					printSeats(ct, movieChoice.getTime(), c.getBasePrice(), movieChoice.getMovie());
				}
			}
		}
	}
	
	public static void printSeats(CinemaTheatre ct, String showtime, double cinemaPrice, String movieName) throws IOException {
		int row = ct.getRow();
		int col = ct.getCol();
		int i, j, k;
		String seat = "[ ]";
//		String occupiedSeat = "[X]";
		char ch = 'A';
		System.out.println("\n\t Screen is here");
		System.out.print("  ");
		
		for (k=1; k<=col; k++) {
			if (k<10) System.out.print(" " + k + "  ");
			else System.out.print("" + k + "  ");
		}
		System.out.println("  ");
		
		for (i=0; i<row; i++) {
			System.out.print(ch + " ");
			for (j=0; j<col; j++) {
				System.out.print(seat + " ");
//				if ((j == i) )
			}
			System.out.println("");
			ch++;
		}
		
		String choice = MenuSelection.passChoiceString("Would you like to proceed? Y/N");
		if (choice.equalsIgnoreCase("y")) {
			chooseSeats(ct, showtime, cinemaPrice, movieName);
		}
	}
	
	public static void chooseSeats(CinemaTheatre ct, String showtime, double cinemaPrice, String movieName) throws IOException {
		String username = MenuSelection.passChoiceString("Username: ");
		String password = MenuSelection.passChoiceString("\nPassword: ");
		int age = 0;
		ArrayList<Customer> customer = AccountManager.getCustomerList();
		
		for (Customer c: customer) {
			if (c.getEmailAddress().equalsIgnoreCase(username))
				age = c.getAge();
		}
		
		if (!AccountManager.verifyCustomer(username, password)) {
			System.out.println("\nWrong credentials :(");
			return;
		}
		
		System.out.println("How many seats do you wish to purchase?");
		int numSeats = sc.nextInt();
		
		System.out.println("Please choose the available seats in the following format.\n"
					   	 + "RowColumn, i.e. A5 or B2");
//		ArrayList<String> seats = new ArrayList<String>();
		for (int i=0; i<numSeats; i++) {
			int orderNum = (int)(Math.random()*100000);
			String selectedSeat = MenuSelection.passChoiceString("Enter the seat number: ");
//			int row = selectedSeat.charAt(0) - 40;
//			int col = selectedSeat.charAt(1);
//			seats.add(selectedSeat);
			
//			allow for student and senior citizen price
			if (age>67) cinemaPrice = 8;
			else if ((age>0) && (age<17)) cinemaPrice = 7;
			
			Order newOrder = new Order(orderNum, username, ct.getTheatreID(), movieName, showtime, selectedSeat, cinemaPrice);
			saveOrder(orderFile, newOrder);
		}
//		ct.setSeatsOccupied(seats);
		System.out.println("Order successful!");
	}

	public static void saveOrder(String filename, Order ord) throws IOException {
		List alw = new ArrayList();
		
		StringBuilder st =  new StringBuilder();
		st.append(ord.getOrderNum());
		st.append(SEPARATOR);
		st.append(ord.getCustomerEmail());
		st.append(SEPARATOR);
		st.append(ord.getTheatreID());
		st.append(SEPARATOR);
		st.append(ord.getMovieName());
		st.append(SEPARATOR);
		st.append(ord.getShowtime());
		st.append(SEPARATOR);
		st.append(ord.getSeatNum());
		st.append(SEPARATOR);
		st.append(ord.getPrice());
		alw.add(st.toString());
		DataManager.write(filename, alw);
	}
	
	public static ArrayList<Order> getOrders() throws IOException{
		ArrayList stringArray = (ArrayList)DataManager.read(orderFile);
		ArrayList<Order> allOrders = new ArrayList<Order>();
		
		for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);
			int OrderID = Integer.parseInt(star.nextToken().trim());
			String customerEmail = star.nextToken().trim();
			int theatreID = Integer.parseInt(star.nextToken().trim());
			String movieName = star.nextToken().trim();
			String showtime = star.nextToken().trim();
			String seatNum = star.nextToken().trim();
			double cinemaPrice = Double.parseDouble(star.nextToken().trim());
			
			Order ord = new Order(OrderID, customerEmail, theatreID, movieName, showtime, seatNum, cinemaPrice);
			allOrders.add(ord);
		}
		return allOrders;
	}
	
	public static void getTopMoviesBySales() throws IOException {
		ArrayList<Order> orderArray = BookingManager.getOrders();
		ArrayList<Showtime> showtimeArray = ShowtimeManager.getShowtimesList();
		ArrayList<Sales> movieOnSale = new ArrayList<Sales>();
		int totalSales, i=1;
		
		for (Showtime s: showtimeArray) {
			totalSales = 0;
			for (Order ord: orderArray) {
				if (ord.getMovieName().equalsIgnoreCase(s.getMovie())) {
					totalSales+=ord.getPrice();
				}
			}
			Sales sale = new Sales(s.getMovie(), totalSales);
			movieOnSale.add(sale);
		}
		
		Collections.sort(movieOnSale, Model.Sales.movieSalesComparator);
		
		List<Sales> noRepeat = new ArrayList<Sales>();

		for (Sales s : movieOnSale) {
		    boolean isFound = false;
		    for (Sales e : noRepeat) {
		        if (e.getMovieName().equals(s.getMovieName()) || (e.equals(s))) {
		            isFound = true;        
		            break;
		        }
		    }
		    if (!isFound) noRepeat.add(s);
		}
		
		System.out.println("\nHere are the top movie sales.");
		for (Sales sal: noRepeat) {
			System.out.println(i+ ".\t" + sal.getMovieName() + ":  $" + sal.getMovieSales() + "0");
			i++;
		}
	}
}
