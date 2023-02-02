package lichsuvietnam.service.scraper;

import lichsuvietnam.model.*;

import java.util.ArrayList;


public class AllHistoricalScraper implements HistoricalScraper {
    private ArrayList<HistoricalScraper> scrapers;

    public AllHistoricalScraper() {
        this.scrapers = new ArrayList<>();
        this.scrapers.add(new NguoiKeSuHistoricalScraper());
        this.scrapers.add(new WikipediaHistoricalScraper());
    }

    @Override
    public ArrayList<HistoricalFigure> scrapeHistoricalFigure() {
        ArrayList<HistoricalFigure> historicalFigures = new ArrayList<>();

        for (HistoricalScraper scraper : scrapers) {
            historicalFigures.addAll(scraper.scrapeHistoricalFigure());
        }

        return historicalFigures;
    }

    @Override
    public ArrayList<HistoricalEvent> scrapeHistoricalEvent() {
        ArrayList<HistoricalEvent> historicalEvents = new ArrayList<>();

        for (HistoricalScraper scraper : scrapers) {
            historicalEvents.addAll(scraper.scrapeHistoricalEvent());
        }
        return historicalEvents;
    }

    @Override
    public ArrayList<HistoricalSite> scrapeHistoricalSite() {
        ArrayList<HistoricalSite> historicalSites = new ArrayList<>();
        for (HistoricalScraper scraper : scrapers){
            historicalSites.addAll(scraper.scrapeHistoricalSite());
        }
        return historicalSites;
    }

    @Override
    public ArrayList<HistoricalPeriod> scrapeHistoricalPeriod() {
        ArrayList<HistoricalPeriod> historicalPeriods = new ArrayList<>();
        for (HistoricalScraper scraper : scrapers){
            historicalPeriods.addAll(scraper.scrapeHistoricalPeriod());
        }
        return historicalPeriods;
    }

    @Override
    public ArrayList<Festival> scrapeFestival() {
        ArrayList<Festival> festivals = new ArrayList<>();
        for (HistoricalScraper scraper : scrapers){
            festivals.addAll(scraper.scrapeFestival());
        }
        return festivals;
    }
}
