package lichsuvietnam.service.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lichsuvietnam.model.*;
import lichsuvietnam.service.linker.HistoricalLinker;
import lichsuvietnam.service.scraper.AllHistoricalScraper;
import lichsuvietnam.service.scraper.NguoiKeSuHistoricalScraper;
import lichsuvietnam.service.scraper.WikipediaHistoricalScraper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonHistoricalDao implements HistoricalDao {
    private static String FIGURE_PATH = "data/historical_figures.json";
    private static String EVENT_PATH = "data/historical_events.json";
    private static String PERIOD_PATH = "data/historical_periods.json";
    private static String SITE_PATH = "data/historical_sites.json";
    private static String FESTIVAL_PATH = "data/festivals.json";

    @Override
    public void saveHistoricalFigures(ArrayList<HistoricalFigure> historicalFigures) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(Paths.get(FIGURE_PATH).toFile(), historicalFigures);
            System.out.println("Finished writing " + FIGURE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveHistoricalPeriods(ArrayList<HistoricalPeriod> historicalPeriods) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(Paths.get(PERIOD_PATH).toFile(), historicalPeriods);
            System.out.println("Finished writing " + PERIOD_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveHistoricalEvents(ArrayList<HistoricalEvent> historicalEvents) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(Paths.get(EVENT_PATH).toFile(), historicalEvents);
            System.out.println("Finished writing " + EVENT_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveHistoricalSites(ArrayList<HistoricalSite> historicalSites) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(Paths.get(SITE_PATH).toFile(), historicalSites);
            System.out.println("Finished writing " + SITE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveFestivals(ArrayList<Festival> festivals) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(Paths.get(FESTIVAL_PATH).toFile(), festivals);
            System.out.println("Finished writing " + FESTIVAL_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<HistoricalFigure> getHistoricalFigures(String path) {
        ArrayList<HistoricalFigure> historicalFigures = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            historicalFigures = mapper.readValue(
                    Paths.get(path).toFile(),
                    new TypeReference<List<HistoricalFigure>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return historicalFigures;
    }

    @Override
    public ArrayList<HistoricalSite> getHistoricalSites(String path) {
        ArrayList<HistoricalSite> historicalSites = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            historicalSites = mapper.readValue(
                    Paths.get(path).toFile(),
                    new TypeReference<List<HistoricalSite>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return historicalSites;
    }

    @Override
    public ArrayList<HistoricalEvent> getHistoricalEvents(String path) {
        ArrayList<HistoricalEvent> historicalEvents = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            historicalEvents = mapper.readValue(
                    Paths.get(path).toFile(),
                    new TypeReference<List<HistoricalEvent>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return historicalEvents;
    }

    @Override
    public ArrayList<HistoricalPeriod> getHistoricalPeriods(String path) {
        ArrayList<HistoricalPeriod> historicalPeriods = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            historicalPeriods = mapper.readValue(
                    Paths.get(path).toFile(),
                    new TypeReference<List<HistoricalPeriod>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return historicalPeriods;
    }

    @Override
    public ArrayList<Festival> getFestivals(String path) {
        ArrayList<Festival> historicalFestivals = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            historicalFestivals = mapper.readValue(
                    Paths.get(path).toFile(),
                    new TypeReference<List<Festival>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return historicalFestivals;
    }

    @Override
    public void updateAll() {
        AllHistoricalScraper scraper = new AllHistoricalScraper();

        scraper.addHistoricalScraper(new WikipediaHistoricalScraper());
        scraper.addHistoricalScraper(new NguoiKeSuHistoricalScraper());

        ArrayList<HistoricalFigure> figures = scraper.scrapeHistoricalFigures();
        ArrayList<HistoricalEvent> events = scraper.scrapeHistoricalEvents();
        ArrayList<HistoricalPeriod> periods = scraper.scrapeHistoricalPeriods();
        ArrayList<HistoricalSite> sites = scraper.scrapeHistoricalSites();
        ArrayList<Festival> festivals = scraper.scrapeFestivals();

        HistoricalLinker linker = new HistoricalLinker();
        linker.linkHistoricalFigureAndHistoricalEvent(figures, events);
        linker.linkHistoricalFigureAndHistoricalPeriod(figures, periods);
        linker.linkHistoricalFigureAndFestival(figures, festivals);

        this.saveHistoricalFigures(figures);
        this.saveHistoricalEvents(events);
        this.saveHistoricalPeriods(periods);
        this.saveHistoricalSites(sites);
        this.saveFestivals(festivals);
    }
}
