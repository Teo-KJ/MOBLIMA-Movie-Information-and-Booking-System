package Presenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import Model.Cinema;
import Model.CinemaTheatre;
import Model.Showtime;

public class ShowtimeManager {
	static Scanner sc = new Scanner(System.in);
	static int choice;
	public static final String SEPARATOR = "|";
	public static final String cinemaFile = "datafiles/cinemaFile.txt";
	public static final String showtimeFile = "datafiles/showtimeFile.txt";
	public static final String movieFile = "datafiles/movieFile.txt";
	
	public ShowtimeManager() {
		
	}
	
//	Showtimes
	public static void addShowtime() throws IOException {
		CinemaManager.readCinemas();
		System.out.println("Please enter the cinema theatre ID.");
		int cinemaTheatre = sc.nextInt();
		boolean check = true, checkCinemaExist = false;
		ArrayList<Cinema> cinemaArray = CinemaManager.getCinemasList();
		
		for (Cinema c: cinemaArray) {
			ArrayList<CinemaTheatre> allTheatres = c.getCinemaTheatre();
			for (CinemaTheatre all: allTheatres) {
				if (cinemaTheatre == all.getTheatreID()) {
					System.out.println("Cinema theatre identified!");
					checkCinemaExist = true;
					break;
				}
			}
		}
		
		if (!checkCinemaExist) {
			System.out.println("This cinema does not exists in the system. Please enter correct cinema theatre ID.");
			return;
		}
		
		MovieManager.readMovies();
		System.out.println("Please enter movie index.");
		int movieIndex = sc.nextInt();
		String movieName = null;
		
		ArrayList movieArray = (ArrayList)DataManager.read(movieFile);
		for (int i = 0 ; i < movieArray.size() ; i++) {
			String st = (String)movieArray.get(i);
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);
			star.nextToken().trim();
			String name = star.nextToken().trim();
			if (movieIndex == i+1) {
				System.out.println("Movie identified!");
				movieName = name;
				check = true;
				break;
			}
			else {
				check = false;
			}
		}
		
		String time = MenuSelection.passChoiceString("Please enter the show time (in format of HH:MM).");
		
		if (check) {
			Showtime st = new Showtime(cinemaTheatre, movieName, time);
			saveShowtime(showtimeFile, st);
			System.out.println("Successfully added!");
		}
		else return;
	}
	
	public static void saveShowtime(String filename, Showtime show) throws IOException {
		List alw = new ArrayList();
		
		StringBuilder st =  new StringBuilder();
		st.append(show.getCinemaTheatre());
		st.append(SEPARATOR);
		st.append(show.getMovie());
		st.append(SEPARATOR);
		st.append(show.getTime());
		alw.add(st.toString());
		DataManager.write(filename, alw);
	}
	
	public static void readShowtimes() throws IOException{
		ArrayList<Showtime> stringArray = getShowtimesList();
		int i=1;
		System.out.println("");
		
		for (Showtime st: stringArray) {
			System.out.println(i + ".\tMovie: " + st.getMovie() + "\n\tCinema Theatre: " + st.getCinemaTheatre() + "\n\tTime: " + st.getTime());
			i++;
		}
	}
	
	public static ArrayList<Showtime> getShowtimesList() throws IOException{
		ArrayList stringArray = (ArrayList)DataManager.read(showtimeFile);
		ArrayList<Showtime> allShowtimes = new ArrayList<Showtime>();
		
		for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);
			int cinemaTheatre = Integer.parseInt(star.nextToken().trim());
			String movie = star.nextToken().trim();
			String time = star.nextToken().trim();
			Showtime show = new Showtime(cinemaTheatre, movie, time);
			allShowtimes.add(show);
		}
		return allShowtimes;
	}
	
	public static void removeShowtimes() throws IOException{
		System.out.println("Please enter the index that you wish to remove from the list.\n");
		readShowtimes();
		int showtimeIndex = sc.nextInt();
		
		ArrayList stringArray = (ArrayList)DataManager.read(showtimeFile);
		List alw = new ArrayList();
		boolean check = false;
		
		for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			
			if (showtimeIndex == i+1){
				System.out.println("Showtime to be removed has been identified.");
				check = true;
			}
			else {
				alw.add(st);
			}
		}
		DataManager.overwrite(showtimeFile, alw);
		if (check == false) {
			System.out.println("There is no showtime with this index in the list.");
			return;
		}
		System.out.println("Showtime has been removed.");
	}
	
//	Perform checks to ensure the show time is removed as well if the movie or cinema
//	has been removed
	public static void checkShowtimeAgainstMovie(String Name) throws IOException {
		ArrayList showtimeArray = (ArrayList)DataManager.read(showtimeFile);
		List alw = new ArrayList();
		
		for (int i = 0 ; i < showtimeArray.size() ; i++) {
			String st = (String)showtimeArray.get(i);
			StringTokenizer star = new StringTokenizer(st, SEPARATOR);
			star.nextToken().trim();
			String movieName = star.nextToken().trim(); 
			if (! ( Name.equalsIgnoreCase(movieName) ) ) alw.add(st);
		}
		DataManager.overwrite(showtimeFile, alw);
	}
	
	public static void checkShowtimeAgainstCinema(int theatreID) throws IOException {
		ArrayList showtimeArray = (ArrayList)DataManager.read(showtimeFile);
		List alw = new ArrayList();
		
		for (int i = 0 ; i < showtimeArray.size() ; i++) {
			String st = (String)showtimeArray.get(i);
			StringTokenizer star = new StringTokenizer(st, SEPARATOR);
			int id = Integer.parseInt(star.nextToken().trim());
			if (!(theatreID == id) ) alw.add(st);
		}
		DataManager.overwrite(showtimeFile, alw);
	}
}
