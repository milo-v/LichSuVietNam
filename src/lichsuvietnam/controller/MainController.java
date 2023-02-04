package lichsuvietnam.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {
    @FXML
    BorderPane mainBorderPane;

    @FXML
    Button historicalFigureButton;

    @FXML
    Button historicalEventButton;

    @FXML
    Button historicalPeriodButton;

    @FXML
    Button historicalSiteButton;

    @FXML
    Button festivalButton;

    @FXML
    Button updateButton;

    @FXML
    public void initialize() {
        SceneManager.setMainBorderPane(mainBorderPane);
        initializeHistoricalFigure();
        initializeHistoricalEvent();
        initializeHistoricalPeriod();
        initializeFestival();
        initializeHistoricalSite();
        initializeButtons();
    }

    private void initializeButtons() {
        historicalFigureButton.setOnMouseClicked(e -> {
            SceneManager.setCurrentScene(HistoricalFigureController.SCENE_KEY);
        });

        historicalEventButton.setOnMouseClicked(e -> {
            SceneManager.setCurrentScene(HistoricalEventController.SCENE_KEY);
        });

        historicalSiteButton.setOnMouseClicked(e -> {
            SceneManager.setCurrentScene(HistoricalSiteController.SCENE_KEY);
        });

        historicalPeriodButton.setOnMouseClicked(e -> {
            SceneManager.setCurrentScene(HistoricalPeriodController.SCENE_KEY);
        });

        festivalButton.setOnMouseClicked(e -> {
            SceneManager.setCurrentScene(FestivalController.SCENE_KEY);
        });
    }

    private void initializeHistoricalFigure() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/historical_figure.fxml"));
        loader.setController(new HistoricalFigureController());
        try {
            SceneManager.addScene(HistoricalFigureController.SCENE_KEY, loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeHistoricalEvent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/historical_event.fxml"));
        loader.setController(new HistoricalEventController());
        try {
            SceneManager.addScene(HistoricalEventController.SCENE_KEY, loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeHistoricalPeriod() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/historical_period.fxml"));
        loader.setController(new HistoricalPeriodController());
        try {
            SceneManager.addScene(HistoricalPeriodController.SCENE_KEY, loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeHistoricalSite() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/historical_site.fxml"));
        loader.setController(new HistoricalSiteController());
        try {
            SceneManager.addScene(HistoricalSiteController.SCENE_KEY, loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeFestival() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/festival.fxml"));
        loader.setController(new FestivalController());
        try {
            SceneManager.addScene(FestivalController.SCENE_KEY, loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
