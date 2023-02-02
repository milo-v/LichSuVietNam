package lichsuvietnam.historical.datalinker;

import lichsuvietnam.models.HistoricalEvent;
import lichsuvietnam.models.HistoricalFigure;

import java.util.ArrayList;
import java.util.Locale;

public class DataLinker {

    public static void linkHistoricalFigureToHistoricalEvent(ArrayList<HistoricalFigure> historicalFigures,
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


}
