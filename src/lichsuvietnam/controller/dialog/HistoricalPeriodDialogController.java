package lichsuvietnam.controller.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lichsuvietnam.controller.page.HistoricalFigureController;
import lichsuvietnam.model.HistoricalFigure;
import lichsuvietnam.model.HistoricalPeriod;
import lichsuvietnam.service.dao.HistoricalDao;
import lichsuvietnam.service.dao.JsonHistoricalDao;

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

        HistoricalDao jsonDao = new JsonHistoricalDao();

        int counter = 1;
        for (HistoricalFigure historicalFigure : jsonDao.getHistoricalFigures()) {
            if (historicalPeriod.getRelatedHistoricalFigureIds().contains(historicalFigure.getId())) {
                Label figure = new Label(counter + ". " + historicalFigure.getName());
                figure.setFont(new Font(14));
                relatedFiguresVBox.getChildren().add(figure);
                ++counter;
            }
        }
    }
}
