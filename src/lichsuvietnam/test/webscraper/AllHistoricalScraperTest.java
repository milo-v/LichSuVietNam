package lichsuvietnam.test.webscraper;

import lichsuvietnam.models.HistoricalEvent;
import lichsuvietnam.models.HistoricalFigure;
import lichsuvietnam.webscraper.AllHistoricalScraper;

import java.util.ArrayList;

public class AllHistoricalScraperTest {
    public static void main(String[] args) {
        AllHistoricalScraper scraper = new AllHistoricalScraper();

        ArrayList<HistoricalEvent> events = scraper.scrapeHistoricalEvent();

        System.out.print(events);
    }
}
