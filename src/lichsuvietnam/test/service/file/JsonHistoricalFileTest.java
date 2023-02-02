package lichsuvietnam.test.service.file;

import lichsuvietnam.service.file.JsonHistoricalFile;
import lichsuvietnam.service.scraper.AllHistoricalScraper;

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
