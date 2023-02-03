package lichsuvietnam.service.scraper;

import lichsuvietnam.model.*;

import java.util.ArrayList;

public interface HistoricalScraper {
    public abstract ArrayList<HistoricalFigure> scrapeHistoricalFigures();
    public abstract ArrayList<HistoricalEvent> scrapeHistoricalEvents();
    public abstract ArrayList<HistoricalSite> scrapeHistoricalSites();
    public abstract ArrayList<HistoricalPeriod> scrapeHistoricalPeriods();
    public abstract ArrayList<Festival> scrapeFestivals();
}
