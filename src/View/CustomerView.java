package View;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import Presenter.AccountManager;
import Presenter.BookingManager;
import Presenter.CinemaManager;
import Presenter.MenuSelection;

public class CustomerView extends View {
	public CustomerView() {	
		
	}
	
	protected void starter() {
		try {
			menu();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void menu() throws IOException, ParseException {
		Scanner sc = new Scanner(System.in);
		int choice;
		
		System.out.println("\nWelcome Customer!\n"
					+ "What would you like to do?\n"
					+ "1. Find available movies\n"
					+ "2. Find top movies by sales\n"
					+ "3. Find cinemas\n"
					+ "4. Book cinema ticket\n"
					+ "5. Sign in\n"
					+ "6. Create an account\n"
					+ "7. Exit");
		
		while (!(sc.hasNextInt())){
			System.out.println("\nWelcome Customer!\n"
					+ "What would you like to do?\n"
					+ "1. Find available movies\n"
					+ "2. Find top movies by sales\n"
					+ "3. Find cinemas\n"
					+ "4. Book cinema ticket\n"
					+ "5. Sign in\n"
					+ "6. Create an account\n"
					+ "7. Exit");
			sc.nextInt();
		}
		choice = sc.nextInt();
		
		while (!(MenuSelection.select_choice(1, 7, choice))) {
			System.out.println("\nWelcome Customer!\n"
					+ "What would you like to do?\n"
					+ "1. Find available movies\n"
					+ "2. Find top movies by sales\n"
					+ "3. Find cinemas\n"
					+ "4. Book cinema ticket\n"
					+ "5. Sign in\n"
					+ "6. Create an account\n"
					+ "7. Exit");
			choice = sc.nextInt();
		}
		
		switch(choice) {
		case 1:
			System.out.println("Please select the movie that you would like to check the showtimes.");
			BookingManager.viewShowtimesFromMovie();
			menu();
			break;
		case 2:
			BookingManager.getTopMoviesBySales();
			menu();
			break;
		case 3:
			CinemaManager.readCinemasCustomer();
			menu();
			break;
		case 4:
			System.out.println("Please select the movie that you would like to purchase tickets for.");
			BookingManager.bookTix();
			menu();
			break;
		case 5:
			String username = MenuSelection.passChoiceString("Username: ");
			String password = MenuSelection.passChoiceString("\nPassword: ");
			if (AccountManager.verifyCustomer(username, password)) AccountManager.viewCustomerProfile(username);
			else System.out.println("\nWrong credentials :(");
			
			String goBack = MenuSelection.passChoiceString("Press 'A' to return.");
			if (goBack.equalsIgnoreCase("A")) menu();
			break;
		case 6:
			AccountManager.createCustAccount();
			menu();
			break;
		case 7:
			System.out.println("");
			intent(this, new HomeView());
			break;
		}
		sc.close();
	}

}
