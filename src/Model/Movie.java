package Model;

import java.io.Serializable;
import java.util.ArrayList;
import Enum.Classification;
import Enum.ViewStatus;
import Enum.ViewType;

public class Movie implements Serializable {
	private String name, ID;
	private ArrayList<String> cast;
	private String runtime;
	private String openingDate;
	private Classification classification;
	private ViewType viewType;
	private ViewStatus viewStatus;
	
	public Movie(String ID, String name, ArrayList<String> cast, String runtime, String openingDate,
			Classification classification, ViewType viewType, ViewStatus viewStatus) {
		this.ID = ID;
		this.name = name;
		this.cast = cast;
		this.runtime = runtime;
		this.openingDate = openingDate;
		this.classification = classification;
		this.viewType = viewType;
		this.viewStatus = viewStatus;
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}
	public String getID() {
		return ID;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setCast(ArrayList<String> cast) {
		this.cast = cast;
	}
	public ArrayList<String> getCast() {
		return cast;
	}
	
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	public String getRuntime() {
		return runtime;
	}
	
	public void setOpeningDate(String openingDate) {
		this.openingDate = openingDate;
	}
	public String getOpeningDate() {
		return openingDate;
	}
	
	public void setClassification(Classification classification) {
		this.classification = classification;
	}
	public Classification getClassification() {
		return classification;
	}
	
	public void setViewType(ViewType viewType) {
		this.viewType = viewType;
	}
	public ViewType getViewType() {
		return viewType;
	}
	
	public void setViewStatus(ViewStatus viewStatus) {
		this.viewStatus = viewStatus;
	}
	public ViewStatus getViewStatus() {
		return viewStatus;
	}
	
	public boolean equals(Object o) {
		if (o instanceof Movie) {
			Movie m = (Movie)o;
			return (getName().equals(m.getName()));
		}
		return false;
	}
}
