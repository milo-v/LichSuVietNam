package lichsuvietnam.test.service.linker;

import lichsuvietnam.model.HistoricalEvent;
import lichsuvietnam.model.HistoricalFigure;
import lichsuvietnam.model.HistoricalPeriod;
import lichsuvietnam.service.file.JsonHistoricalFile;
import lichsuvietnam.service.linker.HistoricalLinker;
import lichsuvietnam.service.scraper.AllHistoricalScraper;

import java.util.ArrayList;

public class HistoricalLinkerTest {
    public static void main(String[] args) {
        String eventPath = "data/historical_events.json";
        String figurePath = "data/historical_figures.json";
        String periodPath = "data/historical_periods.json";

        AllHistoricalScraper scraper = new AllHistoricalScraper();

        ArrayList<HistoricalFigure> figures = scraper.scrapeHistoricalFigure();
        ArrayList<HistoricalEvent> events = scraper.scrapeHistoricalEvent();
        ArrayList<HistoricalPeriod> periods = scraper.scrapeHistoricalPeriod();

        HistoricalLinker.linkHistoricalFigureAndHistoricalEvent(figures, events);
        HistoricalLinker.linkHistoricalFigureAndHistoricalPeriod(figures, periods);

        JsonHistoricalFile jsonFile = new JsonHistoricalFile();

        jsonFile.writeHistoricalFigure(figurePath, figures);
        jsonFile.writeHistoricalEvent(eventPath, events);
        jsonFile.writeHistoricalPeriod(periodPath, periods);
    }
}
