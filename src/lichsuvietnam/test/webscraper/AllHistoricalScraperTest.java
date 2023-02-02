package lichsuvietnam.test.webscraper;

import lichsuvietnam.model.HistoricalEvent;
import lichsuvietnam.model.HistoricalFigure;
import lichsuvietnam.service.scraper.AllHistoricalScraper;

import java.util.ArrayList;

public class AllHistoricalScraperTest {
    public static void main(String[] args) {
        AllHistoricalScraper scraper = new AllHistoricalScraper();

        ArrayList<HistoricalEvent> events = scraper.scrapeHistoricalEvent();

        System.out.print(events);
    }
}
