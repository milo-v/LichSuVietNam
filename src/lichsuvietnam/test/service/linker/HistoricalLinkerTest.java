package lichsuvietnam.test.service.linker;

import lichsuvietnam.model.HistoricalEvent;
import lichsuvietnam.model.HistoricalFigure;
import lichsuvietnam.model.HistoricalPeriod;
import lichsuvietnam.service.dao.JsonHistoricalDao;
import lichsuvietnam.service.linker.HistoricalLinker;
import lichsuvietnam.service.scraper.AllHistoricalScraper;

import java.util.ArrayList;

public class HistoricalLinkerTest {
    public static void main(String[] args) {
        AllHistoricalScraper scraper = new AllHistoricalScraper();

        ArrayList<HistoricalFigure> figures = scraper.scrapeHistoricalFigure();
        ArrayList<HistoricalEvent> events = scraper.scrapeHistoricalEvent();
        ArrayList<HistoricalPeriod> periods = scraper.scrapeHistoricalPeriod();

        HistoricalLinker.linkHistoricalFigureAndHistoricalEvent(figures, events);
        HistoricalLinker.linkHistoricalFigureAndHistoricalPeriod(figures, periods);

        JsonHistoricalDao jsonFile = new JsonHistoricalDao();

        jsonFile.saveHistoricalFigures(figures);
        jsonFile.saveHistoricalEvents(events);
        jsonFile.saveHistoricalPeriods(periods);
    }
}
