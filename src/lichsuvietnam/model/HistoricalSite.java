package lichsuvietnam.model;

import java.util.ArrayList;

public class HistoricalSite {
	private static int idCounter = 0;
	private String id;
	private String name;
	private String designatedDate;
	private String location;

	private ArrayList<String> relatedHistoricalFigureIds = new ArrayList<>();
	public HistoricalSite() {
		this.id = "site-" + idCounter;
		++idCounter;
	}

	public String getId() {
		return id;
	}
	public ArrayList<String> getRelatedHistoricalFigureIds() {
		return relatedHistoricalFigureIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignatedDate() {
		return designatedDate;
	}

	public void setDesignatedDate(String designatedDate) {
		this.designatedDate = designatedDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
