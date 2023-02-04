package lichsuvietnam.controller.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lichsuvietnam.controller.page.FestivalController;
import lichsuvietnam.controller.page.HistoricalEventController;
import lichsuvietnam.controller.page.HistoricalPeriodController;
import lichsuvietnam.controller.page.HistoricalSiteController;
import lichsuvietnam.model.*;

public class HistoricalFigureDialogController {
    private HistoricalFigure historicalFigure;

    @FXML
    Label nameLabel;

    @FXML
    Label birthLabel;

    @FXML
    Label deathLabel;

    @FXML
    Label occupationLabel;

    @FXML
    Label periodLabel;


    @FXML
    VBox relatedSitesVBox;

    @FXML
    VBox relatedEventsVBox;

    @FXML
    VBox relatedFestivalsVBox;

    public HistoricalFigureDialogController(HistoricalFigure historicalFigure) {
        this.historicalFigure = historicalFigure;
    }

    @FXML
    public void initialize() {
        nameLabel.setText(historicalFigure.getName());
        birthLabel.setText(historicalFigure.getBirth());
        deathLabel.setText(historicalFigure.getDeath());
        occupationLabel.setText(historicalFigure.getOccupation());

        String periodName = null;
        for (HistoricalPeriod historicalPeriod : HistoricalPeriodController.historicalPeriods) {
            if (historicalPeriod.getId().equals(historicalFigure.getRelatedHistoricalPeriodId())) {
                periodName = historicalPeriod.getName();
                break;
            }
        }

        periodLabel.setText(periodName);

        int counter = 1;
        for (HistoricalEvent historicalEvent : HistoricalEventController.historicalEvents) {
            if (historicalFigure.getRelatedHistoricalEventIds().contains(historicalEvent.getId())) {
                Label event = new Label(counter + ". " + historicalEvent.getName());
                event.setFont(new Font(14));
                relatedEventsVBox.getChildren().add(event);
                ++counter;
            }
        }

        counter = 1;
        for (HistoricalSite historicalSite : HistoricalSiteController.historicalSites) {
            if (historicalFigure.getRelatedHistoricalSiteIds().contains(historicalSite.getId())) {
                Label site = new Label(counter + ". " + historicalSite.getName());
                site.setFont(new Font(14));
                relatedSitesVBox.getChildren().add(site);
                ++counter;
            }
        }

        counter = 1;
        for (Festival festival : FestivalController.festivals) {
            if (historicalFigure.getRelatedFestivalIds().contains(festival.getId())) {
                Label fest = new Label(counter + ". " + festival.getName());
                fest.setFont(new Font(14));
                relatedFestivalsVBox.getChildren().add(fest);
                ++counter;
            }
        }
    }
}
