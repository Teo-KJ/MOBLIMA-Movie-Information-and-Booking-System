package Model;

public class CinemaTheatre{
	private String cinemaName;
	private int theatreID;
//	private ArrayList<Showtime> showTimes;
//	private Showtime st;
	private int numRow, numCol;
	private int seatingCapacity = numRow * numCol;
//	private ArrayList<String> seatsOccupied;
//	private int numSeatsOccupied;
	
	public CinemaTheatre(String cinemaName, int theatreID, int numRow, int numCol) {
		this.cinemaName = cinemaName;
		this.theatreID = theatreID;
		this.numRow = numRow;
		this.numCol = numCol;
//		this.numSeatsOccupied = numSeatsOccupied;
//		this.seatsOccupied = seatsOccupied;
	}
	
	public int getTheatreID() {
		return theatreID;
	}
	public void setTheatreID(int theatreID) {
		this.theatreID = theatreID;
	}
	
	public String getCinemaName() {
		return cinemaName;
	}
	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
	
//	public ArrayList<Showtime> getShowtime() {
//		return showTimes;
//	}
//	public void setShowTime(Showtime st) {
//		this.st = st;
//		showTimes.add(st);
//	}
	
	public int getRow() {
		return numRow;
	}
	public void setRow(int numRow) {
		this.numRow = numRow;
	}
	
	public int getCol() {
		return numCol;
	}
	public void setCol (int numCol) {
		this.numCol = numCol;
	}
	
	public int getSeatingCapacity() {
		return seatingCapacity;
	}

//	public ArrayList<String> getSeatsOccupied() {
//		return seatsOccupied;
//	}
//	public void setSeatsOccupied(ArrayList<String> seatsOccupied) {
//		this.seatsOccupied = seatsOccupied;
//	}
//
//	public int getNumSeatsOccupied() {
//		return numSeatsOccupied;
//	}
//	public void setNumSeatsOccupied(int numSeatsOccupied) {
//		this.numSeatsOccupied = numSeatsOccupied;
//	}
	
}
