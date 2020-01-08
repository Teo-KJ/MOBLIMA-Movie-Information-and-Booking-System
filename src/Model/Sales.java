package Model;

import java.util.Comparator;

public class Sales {

	private String movieName;
	private double movieSales;

	public Sales (String movieName, double movieSales) {
		this.setMovieName(movieName);
	    this.setMovieSales(movieSales);
	}
	
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public double getMovieSales() {
		return movieSales;
	}
	public void setMovieSales(double movieSales) {
		this.movieSales = movieSales;
	}

	public static Comparator<Sales> movieSalesComparator = new Comparator<Sales>() {

		public int compare(Sales m1, Sales m2) {
			Double total_sales1 = m1.getMovieSales();
			Double total_sales2 = m2.getMovieSales();
		   return total_sales2.compareTo(total_sales1);
		}};
}