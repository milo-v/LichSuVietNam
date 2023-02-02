package lichsuvietnam.test.historical.datalinker;

import lichsuvietnam.historical.datalinker.DataLinker;
import lichsuvietnam.historical.file.JsonHistoricalFile;
import lichsuvietnam.models.HistoricalEvent;
import lichsuvietnam.models.HistoricalFigure;
import lichsuvietnam.webscraper.AllHistoricalScraper;

import java.util.ArrayList;

public class DataLinkerTest {
    public static void main(String[] args) {
        String eventPath = "data/historical_events.json";
        String figurePath = "data/historical_figures.json";

        AllHistoricalScraper scraper = new AllHistoricalScraper();

        ArrayList<HistoricalFigure> figures = scraper.scrapeHistoricalFigure();
        ArrayList<HistoricalEvent> events = scraper.scrapeHistoricalEvent();

        DataLinker.linkHistoricalFigureToHistoricalEvent(figures, events);

        JsonHistoricalFile jsonFile = new JsonHistoricalFile();

        jsonFile.writeHistoricalFigure(figurePath, figures);
        jsonFile.writeHistoricalEvent(eventPath, events);
    }
}
