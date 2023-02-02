package lichsuvietnam.webscraper;

import lichsuvietnam.models.*;

import java.util.ArrayList;

public interface HistoricalScraper {
    public abstract ArrayList<HistoricalFigure> scrapeHistoricalFigure();
    public abstract ArrayList<HistoricalEvent> scrapeHistoricalEvent();
    public abstract ArrayList<HistoricalSite> scrapeHistoricalSite();
    public abstract ArrayList<HistoricalPeriod> scrapeHistoricalPeriod();
    public abstract ArrayList<Festival> scrapeFestival();
}
