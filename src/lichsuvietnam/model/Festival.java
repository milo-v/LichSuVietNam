package lichsuvietnam.model;

import java.util.ArrayList;

public class Festival {
	private static int idCounter = 0;
	private String id;
	private String name;
	private String date;
	private String location;
	private ArrayList<String> relatedHistoricalFigureNames = new ArrayList<>();
	private ArrayList<String> relatedHistoricalFigureIds = new ArrayList<>();

	public Festival() {
		this.id = "festival-" + idCounter;
		++idCounter;
	}

	public void setRelatedHistoricalFigureNames(ArrayList<String> relatedHistoricalFigureNames) {
		this.relatedHistoricalFigureNames = relatedHistoricalFigureNames;
	}
	public ArrayList<String> getRelatedHistoricalFigureNames() {
		return relatedHistoricalFigureNames;
	}

	public String getId() {
		return id;
	}

	public ArrayList<String> getRelatedHistoricalFigureIds() {
		return relatedHistoricalFigureIds;
	}

	public void setRelatedHistoricalFigureIds(ArrayList<String> relatedHistoricalFigureIds) {
		this.relatedHistoricalFigureIds = relatedHistoricalFigureIds;
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
