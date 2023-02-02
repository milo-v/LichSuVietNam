package lichsuvietnam.model;

import java.util.ArrayList;

public class HistoricalPeriod {
	private static int idCounter = 0;

	public String getId() {
		return id;
	}

	private String id;
	private String name;
	private String timeSpan;

	private ArrayList<String> relatedHistoricalFigureIds = new ArrayList<>();

	public HistoricalPeriod() {
		id = "period-" + idCounter;
		++idCounter;
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

	public String getTimeSpan() {
		return timeSpan;
	}

	public void setTimeSpan(String timeSpan) {
		this.timeSpan = timeSpan;
	}

}
