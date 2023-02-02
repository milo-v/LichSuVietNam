package lichsuvietnam.historicalfile;

import lichsuvietnam.models.*;

import java.util.ArrayList;

public interface HistoricalFile {
    public abstract void writeHistoricalFigure(String path, ArrayList<HistoricalFigure> historicalFigures);
    public abstract void writeHistoricalPeriod(String path, ArrayList<HistoricalPeriod> historicalPeriods);
    public abstract void writeHistoricalEvent(String path, ArrayList<HistoricalEvent> historicalEvents);
    public abstract void writeHistoricalSite(String path, ArrayList<HistoricalSite> historicalSites);
    public abstract void writeFestival(String path, ArrayList<Festival> festivals);

    public abstract ArrayList<HistoricalFigure> readHistoricalFigure(String path);
    public abstract ArrayList<HistoricalSite> readHistoricalSite(String path);

    public abstract ArrayList<HistoricalEvent> readHistoricalEvent(String path);

    public abstract ArrayList<HistoricalPeriod> readHistoricalPeriod(String path);

    public abstract ArrayList<Festival> readFestival(String path);
}
