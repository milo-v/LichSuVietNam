package lichsuvietnam.controller.page;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lichsuvietnam.controller.dialog.HistoricalEventDialogController;
import lichsuvietnam.model.HistoricalEvent;
import lichsuvietnam.service.dao.HistoricalDao;
import lichsuvietnam.service.dao.JsonHistoricalDao;

import java.io.IOException;

public class HistoricalEventController {
    public static final String SCENE_KEY = "historicalEvent";
    public static ObservableList<HistoricalEvent> historicalEvents = FXCollections.observableArrayList();
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

        tableView.setRowFactory(table -> {
            TableRow<HistoricalEvent> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty()) ) {
                    HistoricalEvent historicalEvent = row.getItem();

                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().
                            getResource("/fxml/dialog/historical_event_dialog.fxml"));
                    loader.setController(new HistoricalEventDialogController(historicalEvent));
                    Parent parent = null;
                    try {
                        parent = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/history.png")));
                    stage.setScene(new Scene(parent));
                    stage.setResizable(false);
                    stage.show();
                }
            });
            return row ;
        });
    }

}
