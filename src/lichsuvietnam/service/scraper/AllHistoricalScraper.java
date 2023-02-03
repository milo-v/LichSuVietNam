package lichsuvietnam.service.scraper;

import lichsuvietnam.model.*;

import java.util.ArrayList;
import java.util.HashMap;


public class AllHistoricalScraper implements HistoricalScraper {
    private HashMap<Class<? extends HistoricalScraper>, HistoricalScraper> scrapers = new HashMap<>();

    public void addHistoricalScraper(HistoricalScraper scraper) {
        if (scrapers.putIfAbsent(scraper.getClass(), scraper) != null) {
            System.out.println("Scraper's already in the map");
        }
    }
    @Override
    public ArrayList<HistoricalFigure> scrapeHistoricalFigures() {
        ArrayList<HistoricalFigure> historicalFigures = new ArrayList<>();

        for (HistoricalScraper scraper : scrapers.values()) {
            historicalFigures.addAll(scraper.scrapeHistoricalFigures());
        }

        return historicalFigures;
    }

    @Override
    public ArrayList<HistoricalEvent> scrapeHistoricalEvents() {
        ArrayList<HistoricalEvent> historicalEvents = new ArrayList<>();

        for (HistoricalScraper scraper : scrapers.values()) {
            historicalEvents.addAll(scraper.scrapeHistoricalEvents());
        }
        return historicalEvents;
    }

    @Override
    public ArrayList<HistoricalSite> scrapeHistoricalSites() {
        ArrayList<HistoricalSite> historicalSites = new ArrayList<>();
        for (HistoricalScraper scraper : scrapers.values()){
            historicalSites.addAll(scraper.scrapeHistoricalSites());
        }
        return historicalSites;
    }

    @Override
    public ArrayList<HistoricalPeriod> scrapeHistoricalPeriods() {
        ArrayList<HistoricalPeriod> historicalPeriods = new ArrayList<>();
        for (HistoricalScraper scraper : scrapers.values()){
            historicalPeriods.addAll(scraper.scrapeHistoricalPeriods());
        }
        return historicalPeriods;
    }

    @Override
    public ArrayList<Festival> scrapeFestivals() {
        ArrayList<Festival> festivals = new ArrayList<>();
        for (HistoricalScraper scraper : scrapers.values()){
            festivals.addAll(scraper.scrapeFestivals());
        }
        return festivals;
    }
}
