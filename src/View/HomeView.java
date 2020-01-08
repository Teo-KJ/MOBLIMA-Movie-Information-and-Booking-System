package View;

import java.util.Scanner;
import Presenter.MenuSelection;
import View.CustomerView;

public class HomeView extends View {
	public HomeView(){
		
	}
	
	public void starter() {
		Scanner sc = new Scanner(System.in);
		int choice;
		
		System.out.println("Welcome to the TKJ Moblima System\n"
						 + "Please indicate whether you are a staff or customer.\n"
						 + "1. Staff\n"
						 + "2. Customer\n"
						 + "3. Exit");
		
		while (!(sc.hasNextInt())){
			System.out.println("\nPlease enter correct option.\n"
					 + "Are you are a staff or customer?\n"
					 + "1. Staff\n"
					 + "2. Customer\n"
					 + "3. Exit");
			sc.nextInt();
		}
		
		choice = sc.nextInt();

		while (!(MenuSelection.select_choice(1, 3, choice))) {
			System.out.println("\nPlease enter correct option.\n"
					 + "Are you are a staff or customer?\n"
					 + "1. Staff\n"
					 + "2. Customer\n"
					 + "3. Exit");
			choice = sc.nextInt();
		}
		
		
		switch (choice) {
			case 1:
				intent(this, new StaffView());
				break;
			case 2:
				intent(this, new CustomerView());
				break;
			case 3:
				System.out.println("\nThank you and Goodbye!");
				System.exit(0);
				break;
		}
		
		sc.close();
	}

}
