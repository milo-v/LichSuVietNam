package lichsuvietnam.services;

import lichsuvietnam.webscraper.FestivalScraper;
import lichsuvietnam.webscraper.HistoricalEventScraper;
import lichsuvietnam.webscraper.HistoricalFigureScraper;
import lichsuvietnam.webscraper.HistoricalPeriodScraper;
import lichsuvietnam.webscraper.HistoricalSiteScraper;

public class DataUpdate {
	public void update() {
		new HistoricalPeriodScraper().getData();
		new HistoricalSiteScraper().getData();
		new HistoricalFigureScraper().getData();
		new FestivalScraper().getData();
		new HistoricalEventScraper().getData();
	}
}
