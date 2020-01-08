package Model;

public class Showtime {
	private int CT_ID;
	private String movieName;
	private String time;
			
	public Showtime(int CT_ID, String movieName, String time) {
		this.CT_ID = CT_ID;
		this.movieName = movieName;
		this.time = time;
	}
	
	public void setMovie(String movieName) {
		this.movieName = movieName;
	}
	public String getMovie() {
		return movieName;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	public String getTime() {
		return time;
	}
	
	public void setCinemaTheatre(int CT_ID) {
		this.CT_ID = CT_ID;
	}
	public int getCinemaTheatre() {
		return CT_ID;
	}
}
