package lichsuvietnam.service.linker;

import lichsuvietnam.model.*;

import java.util.ArrayList;

public class HistoricalLinker {

    public static void linkHistoricalFigureAndHistoricalEvent(ArrayList<HistoricalFigure> historicalFigures,
                                                              ArrayList<HistoricalEvent> historicalEvents) {
        for (HistoricalEvent historicalEvent : historicalEvents) {
            String eventName = historicalEvent.getName().toLowerCase();
            for (HistoricalFigure historicalFigure : historicalFigures) {
                if (eventName.contains(historicalFigure.getName().toLowerCase())) {
                    historicalFigure.getRelatedHistoricalEventIds().add(historicalEvent.getId());
                    historicalEvent.getRelatedHistoricalFigureIds().add(historicalFigure.getId());
                }
            }
        }
    }

    public static void linkHistoricalFigureAndHistoricalPeriod(ArrayList<HistoricalFigure> historicalFigures,
                                                               ArrayList<HistoricalPeriod> historicalPeriods) {
        for (HistoricalPeriod historicalPeriod : historicalPeriods) {
            for (HistoricalFigure historicalFigure : historicalFigures) {
                if (historicalPeriod.getName().equals(historicalFigure.getPeriod())
                || historicalPeriod.getName().contains(historicalFigure.getName())) {
                    historicalFigure.setRelatedHistoricalPeriodId(historicalPeriod.getId());
                    historicalPeriod.getRelatedHistoricalFigureIds().add(historicalFigure.getId());
                }
            }
        }
    }

    public static void linkHistoricalFigureAndFestival(ArrayList<HistoricalFigure> historicalFigures,
                                                       ArrayList<Festival> festivals) {
        for (Festival festival : festivals) {
            for (HistoricalFigure historicalFigure : historicalFigures) {
                if (festival.getRelatedFigure().equals(historicalFigure.getName())) {
                    festival.setRelatedFigureId(historicalFigure.getId());
                    historicalFigure.getRelatedFestivalIds().add(festival.getId());
                }
            }
        }
    }



}
