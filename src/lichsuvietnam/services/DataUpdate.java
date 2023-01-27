package lichsuvietnam.services;

import lichsuvietnam.webscraper.FestivalScraper;
import lichsuvietnam.webscraper.HistoricalEventScraper;
import lichsuvietnam.webscraper.HistoricalFigureScraper;
import lichsuvietnam.webscraper.HistoricalPeriodScraper;
import lichsuvietnam.webscraper.HistoricalSiteScraper;

public class DataUpdate {
	public void update() {
		new HistoricalPeriodScraper().getData("https://vi.wikipedia.org/wiki/L%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam", "data/historical_periods.json");
		new HistoricalSiteScraper().getData("https://vi.wikipedia.org/wiki/Danh_s%C3%A1ch_Di_t%C3%ADch_qu%E1%BB%91c_gia_Vi%E1%BB%87t_Nam", "data/historical_sites.json");
		new HistoricalFigureScraper().getData();
		new FestivalScraper().getData();
		new HistoricalEventScraper().getData();
	}
}
