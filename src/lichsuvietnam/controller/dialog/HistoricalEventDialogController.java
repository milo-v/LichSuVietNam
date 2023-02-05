package lichsuvietnam.controller.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lichsuvietnam.controller.page.HistoricalEventController;
import lichsuvietnam.controller.page.HistoricalFigureController;
import lichsuvietnam.model.HistoricalEvent;
import lichsuvietnam.model.HistoricalFigure;
import lichsuvietnam.service.dao.HistoricalDao;
import lichsuvietnam.service.dao.JsonHistoricalDao;

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

        HistoricalDao jsonDao = new JsonHistoricalDao();

        int counter = 1;
        for (HistoricalFigure historicalFigure : jsonDao.getHistoricalFigures()) {
            if (historicalEvent.getRelatedHistoricalFigureIds().contains(historicalFigure.getId())) {
                Label figure = new Label(counter + ". " + historicalFigure.getName());
                figure.setFont(new Font(14));
                relatedFiguresVBox.getChildren().add(figure);
                ++counter;
            }
        }
    }
}
