package lichsuvietnam.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lichsuvietnam.model.HistoricalEvent;
import lichsuvietnam.model.HistoricalFigure;
import lichsuvietnam.service.dao.HistoricalDao;
import lichsuvietnam.service.dao.JsonHistoricalDao;

public class HistoricalEventController {
    public static final String SCENE_KEY = "historicalEvent";

    private ObservableList<HistoricalEvent> historicalEvents = FXCollections.observableArrayList();
    private FilteredList<HistoricalEvent> filteredHistoricalEvents =  new FilteredList<>(historicalEvents);

    @FXML
    private TextField searchTextField;

    @FXML
    TableView<HistoricalEvent> tableView;

    @FXML
    TableColumn<HistoricalEvent, String> nameColumn;

    @FXML
    TableColumn<HistoricalEvent, String> dateColumn;

    @FXML
    public void initialize() {
        initializeTableView();
        initializeTextField();
        getData();
    }

    public void initializeTextField() {
        searchTextField.setOnAction(e -> {
            String text = searchTextField.getText().toLowerCase();

            filteredHistoricalEvents.setPredicate(event -> {
                if (text.isBlank()) {
                    return true;
                }
                return event.getName().toLowerCase().contains(text);
            });
        });
    }

    public void getData() {
        HistoricalDao jsonDao = new JsonHistoricalDao();
        historicalEvents.setAll(jsonDao.getHistoricalEvents());
    }

    public void initializeTableView() {
        int columnSize = tableView.getColumns().size();
        float factor = 1.0f / columnSize;

        for (TableColumn<HistoricalEvent, ?> tableColumn : tableView.getColumns()) {
            tableColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(factor));
        }

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableView.setItems(filteredHistoricalEvents);
    }

}
