package lichsuvietnam.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HistoricalFigureController {
    public final static String SCENE_KEY = "historicalFigure";

    @FXML
    Button pressMe;

    @FXML
    public void initialize() {
        pressMe.setOnMouseClicked(e -> {
            System.out.println("Pressed");
        });
    }
}
