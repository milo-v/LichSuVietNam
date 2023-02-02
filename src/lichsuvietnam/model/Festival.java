package lichsuvietnam.model;

public class Festival {
	private static int idCounter = 0;
	private String id;
	private String name;
	private String date;
	private String location;
	private String relatedFigure;

	private String relatedFigureId;

	public Festival() {
		this.id = "festival-" + idCounter;
		++idCounter;
	}

	public String getId() {
		return id;
	}

	public void setRelatedFigureId(String relatedFigureId) {
		this.relatedFigureId = relatedFigureId;
	}

	public String getRelatedFigureId() {
		return relatedFigureId;
	}

	public String getRelatedFigure() {
		return relatedFigure;
	}

	public void setRelatedFigure(String relatedFigure) {
		this.relatedFigure = relatedFigure;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
