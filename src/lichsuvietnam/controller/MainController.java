package lichsuvietnam.controller;

import javafx.concurrent.Task;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lichsuvietnam.controller.page.*;
import lichsuvietnam.service.dao.HistoricalDao;
import lichsuvietnam.service.dao.JsonHistoricalDao;

import java.io.IOException;

public class MainController {
    private HistoricalDao jsonDao = new JsonHistoricalDao();

    private final HistoricalFigureController FIGURE_CONTROLLER = new HistoricalFigureController();
    private final HistoricalSiteController SITE_CONTROLLER = new HistoricalSiteController();
    private final HistoricalPeriodController PERIOD_CONTROLLER = new HistoricalPeriodController();
    private final HistoricalEventController EVENT_CONTROLLER = new HistoricalEventController();
    private final FestivalController FESTIVAL_CONTROLLER = new FestivalController();

    @FXML
    BorderPane mainBorderPane;

    @FXML
    RadioButton historicalFigureButton;

    @FXML
    RadioButton historicalEventButton;

    @FXML
    RadioButton historicalPeriodButton;

    @FXML
    RadioButton historicalSiteButton;

    @FXML
    RadioButton festivalButton;

    @FXML
    RadioButton homeButton;
    @FXML
    Button updateButton;

    @FXML
    public void initialize() {
        SceneManager.setMainBorderPane(mainBorderPane);
        initializeHome();
        initializeHistoricalFigure();
        initializeHistoricalEvent();
        initializeHistoricalPeriod();
        initializeFestival();
        initializeHistoricalSite();
        initializeButtons();


        SceneManager.setCurrentScene(HomeController.SCENE_KEY);
    }

    public void initializeHome() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/page/home.fxml"));
        loader.setController(new HomeController());
        try {
            SceneManager.addScene(HomeController.SCENE_KEY, loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        homeButton.setOnMouseClicked(e -> {
            SceneManager.setCurrentScene(HomeController.SCENE_KEY);
        });

        updateButton.setOnMouseClicked(e -> {
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    HistoricalDao jsonDao = new JsonHistoricalDao();
                    jsonDao.updateAll();

                    return null;
                }
            };

            VBox dialog = new VBox();
            dialog.setPadding(new Insets(20));
            dialog.setPrefHeight(Region.USE_COMPUTED_SIZE);
            dialog.setPrefWidth(Region.USE_COMPUTED_SIZE);
            dialog.setSpacing(20);

            Label label = new Label("Scraping data...");
            label.setFont(new Font(16));
            ProgressBar progressBar = new ProgressBar();
            progressBar.progressProperty().bind(task.progressProperty());
            progressBar.setPrefWidth(200);
            dialog.getChildren().add(label);
            dialog.getChildren().add(progressBar);

            Stage progressDialog = new Stage();
            progressDialog.setScene(new Scene(dialog));
            progressDialog.setResizable(false);
            progressDialog.setTitle("Scraper");
            progressDialog.initModality(Modality.APPLICATION_MODAL);
            progressDialog.setOnCloseRequest(event -> event.consume());
            progressDialog.getIcons().add(new Image(getClass().getResourceAsStream("/img/scrape.png")));
            progressDialog.show();

            task.setOnSucceeded(ex -> {
                progressDialog.close();

                FIGURE_CONTROLLER.getData();
                SITE_CONTROLLER.getData();
                PERIOD_CONTROLLER.getData();
                EVENT_CONTROLLER.getData();
                FESTIVAL_CONTROLLER.getData();
            });

            new Thread(task).start();
        });
    }

    private void initializeHistoricalFigure() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/page/historical_figure.fxml"));
        loader.setController(FIGURE_CONTROLLER);
        try {
            SceneManager.addScene(HistoricalFigureController.SCENE_KEY, loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeHistoricalEvent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/page/historical_event.fxml"));
        loader.setController(EVENT_CONTROLLER);
        try {
            SceneManager.addScene(HistoricalEventController.SCENE_KEY, loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeHistoricalPeriod() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/page/historical_period.fxml"));
        loader.setController(PERIOD_CONTROLLER);
        try {
            SceneManager.addScene(HistoricalPeriodController.SCENE_KEY, loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeHistoricalSite() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/page/historical_site.fxml"));
        loader.setController(SITE_CONTROLLER);
        try {
            SceneManager.addScene(HistoricalSiteController.SCENE_KEY, loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeFestival() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/page/festival.fxml"));
        loader.setController(FESTIVAL_CONTROLLER);
        try {
            SceneManager.addScene(FestivalController.SCENE_KEY, loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
