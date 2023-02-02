package lichsuvietnam.test.historical.file;

import lichsuvietnam.historical.file.JsonHistoricalFile;
import lichsuvietnam.webscraper.AllHistoricalScraper;

public class JsonHistoricalFileTest {
    public static void main(String[] args) {
        String path = "data/historical_events.json";

        JsonHistoricalFile jsonFile = new JsonHistoricalFile();
        AllHistoricalScraper scraper = new AllHistoricalScraper();
        jsonFile.writeHistoricalPeriod(path, scraper.scrapeHistoricalPeriod());


        /*
        ArrayList<HistoricalEvent> events = jsonFile.readHistoricalEvent(path);

        System.out.println(events);
         */


    }
}
