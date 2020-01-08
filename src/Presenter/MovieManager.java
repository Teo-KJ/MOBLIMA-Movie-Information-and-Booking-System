package Presenter;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import Enum.Classification;
import Enum.ViewStatus;
import Enum.ViewType;
import Model.Movie;

public class MovieManager {
	static Scanner sc = new Scanner(System.in);
	static int choice;
	public static final String SEPARATOR = "|";
	public static final String movieFile = "datafiles/movieFile.txt";
	public static final String pythonScript = "MovieListGenerator.py";
	public static final String showtimeFile = "datafiles/showtimeFile.txt";

	public MovieManager() {
		
	}
	
//	Movie
	public static void addMovie() throws IOException {
		String movieID = MenuSelection.passChoiceString("Please enter movie ID.");
		String movieName = MenuSelection.passChoiceString("Please enter movie name.");
		System.out.println("Please enter movie cast. How many cast are there?");
		int numOfCast = sc.nextInt();
		ArrayList<String> castList = new ArrayList<String>();
		for (int i=0; i<numOfCast; i++) {
			String castName = MenuSelection.passChoiceString("Enter the name of cast");
			castList.add(castName);
		}
		String runtime = MenuSelection.passChoiceString("Please enter the runtime of the movie?");
		String openingDate = MenuSelection.passChoiceString("Please enter the opening date in the format of DD-MM-YYYY.");
		System.out.println("Please enter the movie classification.\n"
				+ "1. G\n"
				+ "2. PG\n"
				+ "3. PG13\n"
				+ "4. NC16\n"
				+ "5. M18\n"
				+ "6. R21");
		Classification classification = null;
		choice = sc.nextInt();
		if (MenuSelection.select_choice(1, 6, choice)){
			switch(choice) {
			case 1: classification = Classification.G; break;
			case 2: classification = Classification.PG; break;
			case 3: classification = Classification.PG13; break;
			case 4: classification = Classification.NC16; break;
			case 5: classification = Classification.M18; break;
			case 6: classification = Classification.R21; break;
			}
		}
		System.out.println("Please enter the movie view type.\n"
				+ "1. Digital\n"
				+ "2. 3D");
		ViewType viewType = null;
		choice = sc.nextInt();
		if (MenuSelection.select_choice(1, 2, choice)){
			switch(choice) {
			case 1: viewType = ViewType._Digital; break;
			case 2: viewType = ViewType._3D; break;
			}
		}
		System.out.println("Please enter the movie view status.\n"
				+ "1. Now Showing\n"
				+ "2. Coming Soon\n"
				+ "3. Unavailable\n"
				+ "4. Preview");
		ViewStatus viewStatus = null;
		choice = sc.nextInt();
		if (MenuSelection.select_choice(1, 4, choice)){
			switch(choice) {
			case 1: viewStatus = ViewStatus.NowShowing; break;
			case 2: viewStatus = ViewStatus.ComingSoon; break;
			case 3: viewStatus = ViewStatus.Unavailable; break;
			case 4: viewStatus = ViewStatus.Preview; break;
			}
		}
		
		Movie movie = new Movie(movieID, movieName, castList, runtime, openingDate, classification, viewType, viewStatus);
		saveMovie(movieFile, movie);
	}
	
	public static void saveMovie(String filename, Movie m) throws IOException {
		List alw = new ArrayList();
		
		StringBuilder st =  new StringBuilder();
		st.append(m.getID().trim());
		st.append(SEPARATOR);
		st.append(m.getName().trim());
		st.append(SEPARATOR);
		st.append(m.getCast().toString());
		st.append(SEPARATOR);
		st.append(m.getRuntime());
		st.append(SEPARATOR);
		st.append(m.getOpeningDate().trim());
		st.append(SEPARATOR);
		st.append(m.getClassification().toString());
		st.append(SEPARATOR);
		st.append(m.getViewType().toString());
		st.append(SEPARATOR);
		st.append(m.getViewStatus().toString());
		alw.add(st.toString());

		DataManager.write(filename, alw);
	}
	
	public static void saveMovieFromWeb() throws IOException, InterruptedException {
		File myFile = new File(pythonScript);
        Desktop.getDesktop().open(myFile);
        DataManager.clearFile(showtimeFile);
		System.out.println("Movie extraction from web completed!");
	}
	
	public static void readMovies() throws IOException{
		// read String from text file
		ArrayList stringArray = (ArrayList)DataManager.read(movieFile);
		System.out.println("");
		
		for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

			String ID = star.nextToken().trim();
			String name = star.nextToken().trim();
			String castList = star.nextToken().trim();
			String runtime = star.nextToken().trim();
			String openingDate = star.nextToken().trim();
			String classification = star.nextToken().trim();
			String viewType = star.nextToken().trim();
			String viewStatus = star.nextToken().trim();
			System.out.println((i+1)
							  +"\tID: " + ID + "\n"
							  +"\tName: " + name + "\n"
							  +"\tRuntime: " + runtime + "\n"
							  +"\tOpening Date: " + openingDate + "\n"
							  +"\tCasts: " + castList + "\n"
							  +"\tClassification: " + classification + "\n"
							  +"\tType: " + viewType + "\n"
							  +"\tShowing Status: " + viewStatus + "\n");
		}
	}
	
	public static void removeMovie() throws IOException {
		System.out.println("Please enter the movie index that you wish to remove from the list.\n");
		readMovies();
		int movieIndex = sc.nextInt();
		
		ArrayList stringArray = (ArrayList)DataManager.read(movieFile);
		List alw = new ArrayList();
		boolean check = false;
		
		for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st, SEPARATOR);
			star.nextToken().trim();
			String name = star.nextToken().trim(); 
			
			if (movieIndex == i+1){
				System.out.println("Movie to be removed has been identified.");
				ShowtimeManager.checkShowtimeAgainstMovie(name);
				check = true;
			}
			else {
				alw.add(st);
			}
		}
		DataManager.overwrite(movieFile, alw);
		if (check == false) {
			System.out.println("There is no movie with this ID in the list.");
			return;
		}
		System.out.println("Movie has been removed.");
	}
}
