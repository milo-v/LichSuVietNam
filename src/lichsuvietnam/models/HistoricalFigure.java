package lichsuvietnam.models;

import java.util.ArrayList;

public class HistoricalFigure {
	private static int idCounter = 0;
	private String name;
	private String occupation;
	private String period;
	private String birth;
	private String death;

	private Integer id;

	public HistoricalFigure() {
		this.id = idCounter;
		++idCounter;
	}

	public Integer getId() {
		return id;
	}
	private Integer relatedHistoricalPeriodId = Integer.valueOf(-1);
	private ArrayList<Integer> relatedHistoricalEventIds = new ArrayList<>();
	private ArrayList<Integer> relatedFestivalIds = new ArrayList<>();

	public int getRelatedHistoricalPeriodId() {
		return relatedHistoricalPeriodId;
	}

	public void setRelatedHistoricalPeriodId(Integer relatedHistoricalPeriodId) {
		this.relatedHistoricalPeriodId = relatedHistoricalPeriodId;
	}

	public ArrayList<Integer> getRelatedHistoricalEventIds() {
		return relatedHistoricalEventIds;
	}

	public void setRelatedHistoricalEventIds(ArrayList<Integer> relatedHistoricalEventIds) {
		this.relatedHistoricalEventIds = relatedHistoricalEventIds;
	}

	public ArrayList<Integer> getRelatedFestivalIds() {
		return relatedFestivalIds;
	}

	public void setRelatedFestivalIds(ArrayList<Integer> relatedFestivalIds) {
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
