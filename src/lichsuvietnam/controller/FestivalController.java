package lichsuvietnam.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lichsuvietnam.model.Festival;
import lichsuvietnam.model.HistoricalEvent;
import lichsuvietnam.service.dao.HistoricalDao;
import lichsuvietnam.service.dao.JsonHistoricalDao;

public class FestivalController {
    public static final String SCENE_KEY = "festival";

    private ObservableList<Festival> festivals = FXCollections.observableArrayList();
    private FilteredList<Festival> filteredFestivals = new FilteredList<>(festivals);
    @FXML
    private TableView<Festival> tableView;

    @FXML
    private TableColumn<Festival, String> nameColumn;

    @FXML
    private TableColumn<Festival, String> dateColumn;

    @FXML
    private TableColumn<Festival, String> locationColumn;

    @FXML
    private TextField searchTextField;

    @FXML
    public void initialize() {
        initializeTableView();
        initializeTextField();
        getData();
    }

    public void initializeTableView() {
        int columnSize = tableView.getColumns().size();
        float factor = 1.0f / columnSize;

        System.out.println(tableView.getColumns().size());

        for (TableColumn<Festival, ?> tableColumn : tableView.getColumns()) {
            tableColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(factor));
        }

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        tableView.setItems(filteredFestivals);
    }

    public void initializeTextField() {
        searchTextField.setOnAction(e -> {
            String text = searchTextField.getText().toLowerCase();

            filteredFestivals.setPredicate(event -> {
                if (text.isBlank()) {
                    return true;
                }
                return event.getName().toLowerCase().contains(text);
            });
        });
    }

    public void getData() {
        HistoricalDao jsonDao = new JsonHistoricalDao();
        festivals.setAll(jsonDao.getFestivals());

    }
}
