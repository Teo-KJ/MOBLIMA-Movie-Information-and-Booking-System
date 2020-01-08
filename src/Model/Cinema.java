package Model;

import java.util.ArrayList;

public class Cinema {
	private String cinemaName;
	private double basePrice;
	private int numTheatres;
	private ArrayList<CinemaTheatre> ct;
	
	public Cinema(String cinemaName, double basePrice, int numTheatres, ArrayList<CinemaTheatre> ct) {
		this.cinemaName = cinemaName;
		this.basePrice = basePrice;
		this.numTheatres = numTheatres;
		this.ct = ct;
	}

	public String getCinemaName() {
		return cinemaName;
	}
	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
	
	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public ArrayList<CinemaTheatre> getCinemaTheatre() {
		return ct;
	}
	public void setCinemaTheatre(ArrayList<CinemaTheatre> ct) {
		this.ct = ct;
	}
	
	public int getNumTheatre() {
		return numTheatres;
	}
}