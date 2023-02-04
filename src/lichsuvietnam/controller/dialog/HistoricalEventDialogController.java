package lichsuvietnam.controller.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lichsuvietnam.controller.page.HistoricalEventController;
import lichsuvietnam.controller.page.HistoricalFigureController;
import lichsuvietnam.model.HistoricalEvent;
import lichsuvietnam.model.HistoricalFigure;

public class HistoricalEventDialogController {
    @FXML
    Label nameLabel;

    @FXML
    Label dateLabel;

    @FXML
    VBox relatedFiguresVBox;

    private HistoricalEvent historicalEvent;

    public HistoricalEventDialogController(HistoricalEvent historicalEvent) {
        this.historicalEvent = historicalEvent;
    }

    @FXML
    public void initialize() {
        nameLabel.setText(historicalEvent.getName());
        dateLabel.setText(historicalEvent.getDate());

        for (HistoricalFigure historicalFigure : HistoricalFigureController.historicalFigures) {
            if (historicalEvent.getRelatedHistoricalFigureIds().contains(historicalFigure.getId())) {
                Label figure = new Label(historicalFigure.getName());
                figure.setFont(new Font(14));
                relatedFiguresVBox.getChildren().add(figure);
            }
        }
    }
}
