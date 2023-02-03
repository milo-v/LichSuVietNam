package lichsuvietnam.test.service.scraper;

import lichsuvietnam.model.HistoricalEvent;
import lichsuvietnam.service.dao.HistoricalDao;
import lichsuvietnam.service.dao.JsonHistoricalDao;
import lichsuvietnam.service.scraper.AllHistoricalScraper;
import lichsuvietnam.service.scraper.HistoricalScraper;
import lichsuvietnam.service.scraper.NguoiKeSuHistoricalScraper;
import lichsuvietnam.service.scraper.WikipediaHistoricalScraper;

import java.util.ArrayList;

public class AllHistoricalScraperTest {
    public static void main(String[] args) {
        AllHistoricalScraper scraper = new AllHistoricalScraper();
        scraper.addHistoricalScraper(new WikipediaHistoricalScraper());
        scraper.addHistoricalScraper(new NguoiKeSuHistoricalScraper());

        ArrayList<HistoricalEvent> events = scraper.scrapeHistoricalEvents();
        HistoricalDao dao = new JsonHistoricalDao();
        dao.saveHistoricalEvents(events);
    }
}
