package lichsuvietnam.models;

public class HistoricalPeriod {
	private String name;
	private String timespan;

	public HistoricalPeriod() {
	}

	public HistoricalPeriod(String name, String timespan) {
		this.name = name;
		this.timespan = timespan;
	}

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
