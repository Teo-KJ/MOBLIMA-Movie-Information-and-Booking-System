package View;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import Presenter.AccountManager;
import Presenter.BookingManager;
import Presenter.CinemaManager;
import Presenter.MenuSelection;
import Presenter.MovieManager;
import Presenter.ShowtimeManager;

public class StaffLoginDone extends View{
	public StaffLoginDone() {
		
	}
	
	@Override
	protected void starter() {
		try {
			menu();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	protected void menu() throws IOException, InterruptedException, ParseException {
		int choice;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nWelcome Staff!\n"
				 + "What would you like to do?\n"
				 + "1. View current movies\n"
				 + "2. View cinemas\n"
				 + "3. View Showtimes\n"
				 + "4. Add movies\n"
				 + "5. Add cinemas\n"
				 + "6. Add showtimes\n"
				 + "7. Remove movies\n"
				 + "8. Remove cinemas\n"
				 + "9. Remove showtimes\n"
				 + "10. Check customers' profiles\n"
				 + "11. Initialise the movies from Web source\n"
				 + "12. Create new staff account\n"
				 + "13. View top sales in current movies\n"
				 + "0. Logout and Exit\n");
		
		while (!(sc.hasNextInt())){
			System.out.println("\nWelcome Staff!\n"
					 + "What would you like to do?\n"
					 + "1. View current movies\n"
					 + "2. View cinemas\n"
					 + "3. View Showtimes\n"
					 + "4. Add movies\n"
					 + "5. Add cinemas\n"
					 + "6. Add showtimes\n"
					 + "7. Remove movies\n"
					 + "8. Remove cinemas\n"
					 + "9. Remove showtimes\n"
					 + "10. Check customers' profiles\n"
					 + "11. Initialise the movies from Web source\n"
					 + "12. Create new staff account\n"
					 + "13. View top sales in current movies\n"
					 + "0. Logout and Exit\n");
			sc.nextInt();
		}
		choice = sc.nextInt();
		
		while (!(MenuSelection.select_choice(0, 13, choice))) {
			System.out.println("\nWelcome Staff!\n"
					 + "What would you like to do?\n"
					 + "1. View current movies\n"
					 + "2. View cinemas\n"
					 + "3. View Showtimes\n"
					 + "4. Add movies\n"
					 + "5. Add cinemas\n"
					 + "6. Add showtimes\n"
					 + "7. Remove movies\n"
					 + "8. Remove cinemas\n"
					 + "9. Remove showtimes\n"
					 + "10. Check customers' profiles\n"
					 + "11. Initialise the movies from Web source\n"
					 + "12. Create new staff account\n"
					 + "13. View top sales in current movies\n"
					 + "0. Logout and Exit\n");
			choice = sc.nextInt();
		}
		
		switch(choice) {
		case 1:
			MovieManager.readMovies();
			menu();
			break;
		case 2:
			CinemaManager.readCinemas();
			menu();
			break;
		case 3:
			ShowtimeManager.readShowtimes();
			menu();
			break;
		case 4:
			MovieManager.addMovie();
			menu();
			break;
		case 5:
			CinemaManager.addCinema();
			menu();
			break;
		case 6:
			ShowtimeManager.addShowtime();
			menu();
			break;
		case 7:
			MovieManager.removeMovie();
			menu();
			break;
		case 8:
			CinemaManager.removeCinema();
			menu();
			break;
		case 9:
			ShowtimeManager.removeShowtimes();
			menu();
			break;
		case 10:
			AccountManager.readCustomerAccounts();
			menu();
			break;
		case 11:
			MovieManager.saveMovieFromWeb();
			menu();
			break;
		case 12:
			AccountManager.createStaffAccount();
			menu();
			break;
		case 13:
			BookingManager.getTopMoviesBySales();
			menu();
			break;
		case 0:
			System.out.println("");
			intent(this, new HomeView());
			break;
		}
		sc.close();
	}
}
