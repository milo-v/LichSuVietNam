package lichsuvietnam.model;

import java.util.ArrayList;

public class HistoricalFigure {
	private static int idCounter = 0;
	private String name;
	private String occupation;
	private String period;
	private String birth;
	private String death;
	private String id;

	public HistoricalFigure() {
		this.id = "figure-" + idCounter;
		++idCounter;
	}

	public String getId() {
		return id;
	}
	private String relatedHistoricalPeriodId;
	private ArrayList<String> relatedHistoricalEventIds = new ArrayList<String>();
	private ArrayList<String> relatedFestivalIds = new ArrayList<String>();

	public String getRelatedHistoricalPeriodId() {
		return relatedHistoricalPeriodId;
	}

	public void setRelatedHistoricalPeriodId(String relatedHistoricalPeriodId) {
		this.relatedHistoricalPeriodId = relatedHistoricalPeriodId;
	}

	public ArrayList<String> getRelatedHistoricalEventIds() {
		return relatedHistoricalEventIds;
	}

	public void setRelatedHistoricalEventIds(ArrayList<String> relatedHistoricalEventIds) {
		this.relatedHistoricalEventIds = relatedHistoricalEventIds;
	}

	public ArrayList<String> getRelatedFestivalIds() {
		return relatedFestivalIds;
	}

	public void setRelatedFestivalIds(ArrayList<String> relatedFestivalIds) {
		this.relatedFestivalIds = relatedFestivalIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getDeath() {
		return death;
	}

	public void setDeath(String death) {
		this.death = death;
	}
}
