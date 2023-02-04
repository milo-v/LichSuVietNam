package lichsuvietnam.service.dao;

import lichsuvietnam.model.*;

import java.util.ArrayList;

public interface HistoricalDao {
    public abstract void saveHistoricalFigures(ArrayList<HistoricalFigure> historicalFigures);
    public abstract void saveHistoricalPeriods(ArrayList<HistoricalPeriod> historicalPeriods);
    public abstract void saveHistoricalEvents(ArrayList<HistoricalEvent> historicalEvents);
    public abstract void saveHistoricalSites(ArrayList<HistoricalSite> historicalSites);
    public abstract void saveFestivals(ArrayList<Festival> festivals);
    public abstract ArrayList<HistoricalFigure> getHistoricalFigures();
    public abstract ArrayList<HistoricalSite> getHistoricalSites();
    public abstract ArrayList<HistoricalEvent> getHistoricalEvents();
    public abstract ArrayList<HistoricalPeriod> getHistoricalPeriods();
    public abstract ArrayList<Festival> getFestivals();
    public abstract void updateAll();
}
