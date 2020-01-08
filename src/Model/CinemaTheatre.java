package Model;

public class CinemaTheatre{
	private String cinemaName;
	private int theatreID;
	private int numRow, numCol;
	private int seatingCapacity = numRow * numCol;
	
	public CinemaTheatre(String cinemaName, int theatreID, int numRow, int numCol) {
		this.cinemaName = cinemaName;
		this.theatreID = theatreID;
		this.numRow = numRow;
		this.numCol = numCol;
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
}
