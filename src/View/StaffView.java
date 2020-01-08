package View;

import java.io.IOException;
import Presenter.AccountManager;

public class StaffView extends View{
	public StaffView() {
		
	}
	
	protected void starter() {				
		System.out.println("\nPlease login\n");
		
		try {
			while (!(AccountManager.verifyStaff())) {
				System.out.println("You have entered the wrong username or password. Please try again.");
				System.out.println("\nPlease login\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		intent(this, new StaffLoginDone());
	}
}