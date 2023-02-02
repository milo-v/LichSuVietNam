package lichsuvietnam.model;

import java.util.ArrayList;

public class HistoricalEvent {
	private static int idCounter = 0;
	private String id;

	private String name;
	private String date;

	private ArrayList<String> relatedHistoricalFigureIds = new ArrayList<String>();

	public HistoricalEvent() {
		this.id = "event-" + idCounter;
		++idCounter;
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
}
