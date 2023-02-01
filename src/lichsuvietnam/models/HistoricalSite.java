package lichsuvietnam.models;

public class HistoricalSite {
	private String name;
	private String designatedDate;
	private String location;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
