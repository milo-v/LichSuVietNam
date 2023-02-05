package lichsuvietnam.controller.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lichsuvietnam.controller.page.HistoricalFigureController;
import lichsuvietnam.model.HistoricalFigure;
import lichsuvietnam.model.HistoricalSite;
import lichsuvietnam.service.dao.HistoricalDao;
import lichsuvietnam.service.dao.JsonHistoricalDao;

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

        HistoricalDao jsonDao = new JsonHistoricalDao();

        int counter = 1;
        for (HistoricalFigure historicalFigure : jsonDao.getHistoricalFigures()) {
            if (historicalSite.getRelatedHistoricalFigureIds().contains(historicalFigure.getId())) {
                Label figure = new Label(counter + ". " + historicalFigure.getName());
                figure.setFont(new Font(14));
                relatedFiguresVBox.getChildren().add(figure);
                ++counter;
            }
        }
    }
}
