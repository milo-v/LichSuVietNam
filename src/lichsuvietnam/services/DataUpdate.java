package lichsuvietnam.services;

import lichsuvietnam.webscraper.HistoricalPeriodScraper;

public class DataUpdate {
	public void update() {
		new HistoricalPeriodScraper().getData("https://vi.wikipedia.org/wiki/L%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam", "data/historical_period.json");
	}
}
