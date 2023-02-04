package lichsuvietnam.controller.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lichsuvietnam.controller.page.HistoricalFigureController;
import lichsuvietnam.model.Festival;
import lichsuvietnam.model.HistoricalFigure;

public class FestivalDialogController {

    private Festival festival;
    @FXML
    private Label nameLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private VBox relatedFiguresVBox;


    public FestivalDialogController(Festival festival) {
        this.festival = festival;
    }


    @FXML
    public void initialize() {
        nameLabel.setText(festival.getName());
        locationLabel.setText(festival.getLocation());
        dateLabel.setText(festival.getDate());

        for (HistoricalFigure historicalFigure : HistoricalFigureController.historicalFigures) {
            if (festival.getRelatedHistoricalFigureIds().contains(historicalFigure.getId())) {
                Label figure = new Label(historicalFigure.getName());
                figure.setFont(new Font(14));
                relatedFiguresVBox.getChildren().add(figure);
            }
        }
    }
}
