package Model;

public class Order {
	private int theatreID, orderNum;
	private String showtime, seatNum, custEmail, movieName;
	private double price;
	
	public Order(int orderNum, String custEmail, int theatreID, String movieName, String showtime, String seatNum, double price) {
		this.orderNum = orderNum;
		this.custEmail = custEmail;
		this.theatreID = theatreID;
		this.setMovieName(movieName);
		this.showtime = showtime;
		this.seatNum = seatNum;
		this.price = price;
	}
	
	public int getTheatreID() {
		return theatreID;
	}
	public void setTheatreID(int theatreID) {
		this.theatreID = theatreID;
	}

	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public String getShowtime() {
		return showtime;
	}
	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}

	public String getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}

	public String getCustomerEmail() {
		return custEmail;
	}
	public void setCustomerEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
}
