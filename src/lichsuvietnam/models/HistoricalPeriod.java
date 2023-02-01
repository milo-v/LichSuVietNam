package lichsuvietnam.models;

public class HistoricalPeriod {
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
