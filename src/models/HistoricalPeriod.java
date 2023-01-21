package models;

public class HistoricalPeriod {
	public HistoricalPeriod() {
	}

	public HistoricalPeriod(String name, String timespan) {
		this.name = name;
		this.timespan = timespan;
	}

	private String name;
	private String timespan;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTimespan() {
		return timespan;
	}

	public void setTimespan(String timespan) {
		this.timespan = timespan;
	}

}
