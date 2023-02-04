package lichsuvietnam.controller.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lichsuvietnam.controller.page.HistoricalFigureController;
import lichsuvietnam.model.HistoricalFigure;
import lichsuvietnam.model.HistoricalSite;

public class HistoricalSiteDialogController {
    @FXML
    Label nameLabel;

    @FXML
    Label locationLabel;

    @FXML
    Label designatedDateLabel;


    @FXML
    VBox relatedFiguresVBox;

    private HistoricalSite historicalSite;

    public HistoricalSiteDialogController(HistoricalSite historicalSite) {
        this.historicalSite = historicalSite;
    }
    @FXML
    public void initialize() {
        nameLabel.setText(historicalSite.getName());
        locationLabel.setText(historicalSite.getLocation());
        designatedDateLabel.setText(historicalSite.getDesignatedDate());

        for (HistoricalFigure historicalFigure : HistoricalFigureController.historicalFigures) {
            if (historicalSite.getRelatedHistoricalFigureIds().contains(historicalFigure.getId())) {
                Label figure = new Label(historicalFigure.getName());
                figure.setFont(new Font(14));
                relatedFiguresVBox.getChildren().add(figure);
            }
        }


    }
}
