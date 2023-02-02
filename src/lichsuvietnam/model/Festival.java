package lichsuvietnam.model;

import java.util.ArrayList;

public class Festival {
	private static int idCounter = 0;
	private String id;
	private String name;
	private String date;
	private String location;
	private ArrayList<String> relatedFigures = new ArrayList<>();

	private ArrayList<String> relatedFigureIds = new ArrayList<>();

	public Festival() {
		this.id = "festival-" + idCounter;
		++idCounter;
	}

	public String getId() {
		return id;
	}

	public ArrayList<String> getRelatedFigureIds() {
		return relatedFigureIds;
	}

	public ArrayList<String> getRelatedFigures() {
		return relatedFigures;
	}

	public void setRelatedFigures(ArrayList<String> relatedFigures) {
		this.relatedFigures = relatedFigures;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
