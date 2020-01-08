package Presenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import Model.Cinema;
import Model.CinemaTheatre;

public class CinemaManager {
	static Scanner sc = new Scanner(System.in);
	static int choice;
	public static final String SEPARATOR = "|";
	public static final String cinemaFile = "datafiles/cinemaFile.txt";
	
	public CinemaManager() {
		
	}
	
//	Cinema
	public static void addCinema() throws IOException {
		String cinemaName = MenuSelection.passChoiceString("Please enter cinema name.");
		System.out.println("Please enter the base price to charge at the cinema.");
		double price = sc.nextDouble();
		System.out.println("How many theatres are there?");
		int numTheatre = sc.nextInt();
		ArrayList<CinemaTheatre> allTheatres = new ArrayList<CinemaTheatre>();
		
		for (int j=0; j<numTheatre; j++) {
			int ID = (int) (Math.random()*100000);
			System.out.println("Please enter the number of rows at the cinema.");
			int row = sc.nextInt();
			System.out.println("Please enter the number of columns at the cinema.");
			int col = sc.nextInt();
			CinemaTheatre ct = new CinemaTheatre(cinemaName, ID, row, col);
			allTheatres.add(ct);
		}
		
		Cinema cinema = new Cinema(cinemaName, price, numTheatre, allTheatres);
		saveCinema(cinemaFile, cinema, allTheatres);
	}
	
	public static void saveCinema(String filename, Cinema cinema, ArrayList<CinemaTheatre> ct) throws IOException {
		List alw = new ArrayList();
		
		StringBuilder st =  new StringBuilder();
		st.append(cinema.getCinemaName().toString());
		st.append(SEPARATOR);
		st.append(cinema.getBasePrice());
		st.append(SEPARATOR);
		st.append(cinema.getNumTheatre());
		st.append(SEPARATOR);
		for (CinemaTheatre c: ct) {
			st.append(c.getTheatreID());
			st.append(SEPARATOR);
			st.append(c.getCol());
			st.append(SEPARATOR);
			st.append(c.getRow());
			st.append(SEPARATOR);
		}
		alw.add(st.toString());
		DataManager.write(filename, alw);
	}
	
	public static void readCinemas() throws IOException{
		ArrayList<Cinema> stringArray = getCinemasList();
		int i=1;
		System.out.println("");
		
		for (Cinema c: stringArray) {
			System.out.println(i + ". " + c.getCinemaName() + ", Number of theatres: " + c.getNumTheatre());
			ArrayList<CinemaTheatre> ct = c.getCinemaTheatre();
			for (CinemaTheatre cinThe: ct) {
				System.out.println("Theatre ID: " + cinThe.getTheatreID());
			}
			i++;
			System.out.println(" ");
		}
	}
	
	public static ArrayList<Cinema> getCinemasList() throws IOException {
		ArrayList stringArray = (ArrayList)DataManager.read(cinemaFile);
		ArrayList<Cinema> allCinemas = new ArrayList<Cinema>();
		
		for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);
			String name = star.nextToken().trim();
			double price = Double.parseDouble(star.nextToken().trim());
			int numTheatres = Integer.parseInt(star.nextToken().trim());
			
			ArrayList<CinemaTheatre> allTheatres = new ArrayList<CinemaTheatre>();
			for (int j=0; j<numTheatres; j++) {
				int ID = Integer.parseInt(star.nextToken().trim());
				int row = Integer.parseInt(star.nextToken().trim());
				int col = Integer.parseInt(star.nextToken().trim());
				CinemaTheatre ct = new CinemaTheatre(name, ID, row, col);
				allTheatres.add(ct);
			}
			Cinema c = new Cinema(name, price, numTheatres, allTheatres);
			allCinemas.add(c);
		}
		return allCinemas;
	}
	
	public static void readCinemasCustomer() throws IOException{
		ArrayList<Cinema> stringArray = getCinemasList();
		int i=1;
		System.out.println("");
		
		for (Cinema c: stringArray) {
			System.out.println(i + ". " + c.getCinemaName());
			i++;
		}
	}
	
	public static void removeCinema() throws IOException {
		System.out.println("Please enter the index for the cinema that you wish to remove from the list.\n");
		readCinemas();
		int index = sc.nextInt(), i = 0;
		
		ArrayList stringArray = (ArrayList)DataManager.read(cinemaFile);
		ArrayList<Cinema> cinemaArray = getCinemasList();
		List alw = new ArrayList();
		boolean check = false;
		
		for (Cinema c: cinemaArray) {
			String st = (String)stringArray.get(i);
			
			if (index == i+1){
				System.out.println("Cinema to be removed has been identified.");
				for (CinemaTheatre ct: c.getCinemaTheatre()) {
					ShowtimeManager.checkShowtimeAgainstCinema(ct.getTheatreID());
				}
				check = true;
			}
			else {
				alw.add(st);
			}
			i++;
		}
		
		DataManager.overwrite(cinemaFile, alw);
		
		if (check == false) {
			System.out.println("There is no such cinema.");
			return;
		}
		System.out.println("Cinema has been removed.");
	}
}
