package lichsuvietnam.models;

import java.util.ArrayList;

public class HistoricalEvent {
	private static int idCounter = 0;
	private Integer id;

	private String name;
	private String date;

	private ArrayList<Integer> relatedHistoricalFigureIds = new ArrayList<>();

	public HistoricalEvent() {
		this.id = idCounter;
		++idCounter;
	}

	public Integer getId() {
		return id;
	}

	public ArrayList<Integer> getRelatedHistoricalFigureIds() {
		return relatedHistoricalFigureIds;
	}

	public void setRelatedHistoricalFigureIds(ArrayList<Integer> relatedHistoricalFigureIds) {
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
