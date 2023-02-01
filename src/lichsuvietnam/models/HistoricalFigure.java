package lichsuvietnam.models;

public class HistoricalFigure {
	private String id;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String name;
	private String occupation;
	private String period;
	private String birth;
	private String death;
	private String relatedPeriodId;

	public String getRelatedPeriodId() {
		return relatedPeriodId;
	}

	public void setRelatedPeriodId(String relatedPeriodId) {
		this.relatedPeriodId = relatedPeriodId;
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
