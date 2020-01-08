package Presenter;

import java.util.Scanner;

public class MenuSelection {
	static Scanner sc = new Scanner(System.in);
	
	public static boolean select_choice(int first, int last, int choice) {
		if ((choice >= first) && (choice <= last)) {
			return true;
		}
		else return false;
	}
	
	public static String passChoiceString(String... string) {
        for(String s: string) System.out.println(s);
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
        return choice;
    }
}