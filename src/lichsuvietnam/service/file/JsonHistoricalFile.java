package lichsuvietnam.service.file;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lichsuvietnam.model.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonHistoricalFile implements HistoricalFile {
    private ObjectWriter writer;
    private ObjectMapper mapper;
    public JsonHistoricalFile() {
        this.mapper = new ObjectMapper();
        this.writer = this.mapper.writer(new DefaultPrettyPrinter());
    }
    @Override
    public void writeHistoricalFigure(String path, ArrayList<HistoricalFigure> historicalFigures) {
        try {

            this.writer.writeValue(Paths.get(path ).toFile(), historicalFigures);
            System.out.println("Finished writing " + path);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in writeHistoricalFigure()");
        }
    }

    @Override
    public void writeHistoricalPeriod(String path, ArrayList<HistoricalPeriod> historicalPeriods) {
        try {
            this.writer.writeValue(Paths.get(path).toFile(), historicalPeriods);
            System.out.println("Finished writing " + path);
        } catch (IOException e) {
        	e.printStackTrace();
            System.out.println("Error in writeHistoricalPeriod()");
        }
    }

    @Override
    public void writeHistoricalEvent(String path, ArrayList<HistoricalEvent> historicalEvents) {
        try {
            this.writer.writeValue(Paths.get(path).toFile(), historicalEvents);
            System.out.println("Finished writing " + path);
        } catch (IOException e) {
            System.out.println("Error in writeHistoricalEvent()");
        }
    }

    @Override
    public void writeHistoricalSite(String path, ArrayList<HistoricalSite> historicalSites) {
        try {
            this.writer.writeValue(Paths.get(path).toFile(), historicalSites);
            System.out.println("Finished writing " + path);
        } catch (IOException e) {
            System.out.println("Error in writeHistoricalSite()");
        }
    }

    @Override
    public void writeFestival(String path, ArrayList<Festival> festivals) {
        try {
            this.writer.writeValue(Paths.get(path).toFile(), festivals);
            System.out.println("Finished writing " + path);
        } catch (IOException e) {
            System.out.println("Error in writeFestival()");
        }
    }

    @Override
    public ArrayList<HistoricalFigure> readHistoricalFigure(String path) {
        ArrayList<HistoricalFigure> historicalFigures = new ArrayList<>();

        try {
            historicalFigures = mapper.readValue(
                    Paths.get(path).toFile(),
                    new TypeReference<List<HistoricalFigure>>() {});
        } catch (IOException e) {
            System.out.println("Error in readHistoricalFigure()");
        }

        return historicalFigures;
    }

    @Override
    public ArrayList<HistoricalSite> readHistoricalSite(String path) {
        ArrayList<HistoricalSite> historicalSites = new ArrayList<>();

        try {
            historicalSites = mapper.readValue(
                    Paths.get(path).toFile(),
                    new TypeReference<List<HistoricalSite>>() {});
        } catch (IOException e) {
            System.out.println("Error in readHistoricalSite()");
        }

        return historicalSites;
    }

    @Override
    public ArrayList<HistoricalEvent> readHistoricalEvent(String path) {
        ArrayList<HistoricalEvent> historicalEvents = new ArrayList<>();

        try {
            historicalEvents = mapper.readValue(
                    Paths.get(path).toFile(),
                    new TypeReference<List<HistoricalEvent>>() {});
        } catch (IOException e) {
            System.out.println("Error in readHistoricalEvent()");
        }

        return historicalEvents;
    }

    @Override
    public ArrayList<HistoricalPeriod> readHistoricalPeriod(String path) {
        ArrayList<HistoricalPeriod> historicalPeriods = new ArrayList<>();

        try {
            historicalPeriods = mapper.readValue(
                    Paths.get(path).toFile(),
                    new TypeReference<List<HistoricalPeriod>>() {});
        } catch (IOException e) {
            System.out.println("Error in readHistoricalPeriod()");
        }

        return historicalPeriods;
    }

    @Override
    public ArrayList<Festival> readFestival(String path) {
        ArrayList<Festival> festivals = new ArrayList<>();

        try {
            festivals = mapper.readValue(
                    Paths.get(path).toFile(),
                    new TypeReference<List<Festival>>() {});
        } catch (IOException e) {
            System.out.println("Error in readFestival()");
        }

        return festivals;
    }
}
