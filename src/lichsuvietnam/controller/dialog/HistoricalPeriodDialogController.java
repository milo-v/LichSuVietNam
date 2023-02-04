package lichsuvietnam.controller.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lichsuvietnam.controller.page.HistoricalFigureController;
import lichsuvietnam.model.HistoricalFigure;
import lichsuvietnam.model.HistoricalPeriod;

public class HistoricalPeriodDialogController {
    @FXML
    private Label nameLabel;
    @FXML
    private Label timeSpanLabel;

    @FXML
    private VBox relatedFiguresVBox;
    private HistoricalPeriod historicalPeriod;

    public HistoricalPeriodDialogController(HistoricalPeriod historicalPeriod) {
        this.historicalPeriod = historicalPeriod;
    }

    @FXML
    public void initialize() {
        nameLabel.setText(historicalPeriod.getName());
        timeSpanLabel.setText(historicalPeriod.getTimeSpan());

        for (HistoricalFigure historicalFigure : HistoricalFigureController.historicalFigures) {
            if (historicalPeriod.getRelatedHistoricalFigureIds().contains(historicalFigure.getId())) {
                Label figure = new Label(historicalFigure.getName());
                figure.setFont(new Font(14));
                relatedFiguresVBox.getChildren().add(figure);
            }
        }
    }
}
