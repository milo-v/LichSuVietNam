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
import lichsuvietnam.controller.dialog.HistoricalPeriodDialogController;
import lichsuvietnam.model.HistoricalPeriod;
import lichsuvietnam.service.dao.HistoricalDao;
import lichsuvietnam.service.dao.JsonHistoricalDao;

import java.io.IOException;

public class HistoricalPeriodController {
    public static final String SCENE_KEY = "historicalPeriod";

    public static ObservableList<HistoricalPeriod> historicalPeriods = FXCollections.observableArrayList();
    private FilteredList<HistoricalPeriod> filteredHistoricalPeriods = new FilteredList<>(historicalPeriods);

    @FXML
    TableView<HistoricalPeriod> tableView;

    @FXML
    TableColumn<HistoricalPeriod, String> nameColumn;

    @FXML
    TableColumn<HistoricalPeriod, String> timeSpanColumn;

    @FXML
    TextField searchTextField;

    @FXML
    public void initialize() {
        initializeTableView();
        initializeTextField();
        getData();
    }

    public void getData() {
        HistoricalDao jsonDao = new JsonHistoricalDao();
        historicalPeriods.setAll(jsonDao.getHistoricalPeriods());
    }

    private void initializeTableView() {
        int columnSize = tableView.getColumns().size();
        float factor = 1.0f / columnSize;

        for (TableColumn<HistoricalPeriod, ?> tableColumn : tableView.getColumns()) {
            tableColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(factor));
            tableColumn.setReorderable(false);
        }

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        timeSpanColumn.setCellValueFactory(new PropertyValueFactory<>("timeSpan"));

        tableView.setItems(filteredHistoricalPeriods);

        tableView.setRowFactory(table -> {
            TableRow<HistoricalPeriod> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty()) ) {
                    HistoricalPeriod historicalPeriod = row.getItem();

                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().
                            getResource("/fxml/dialog/historical_period_dialog.fxml"));
                    loader.setController(new HistoricalPeriodDialogController(historicalPeriod));
                    Parent parent = null;
                    try {
                        parent = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/calendar.png")));
                    stage.setScene(new Scene(parent));
                    stage.setResizable(false);
                    stage.show();
                }
            });
            return row ;
        });
    }

    private void initializeTextField() {
        searchTextField.setOnAction(e -> {
            String text = searchTextField.getText().toLowerCase();

            filteredHistoricalPeriods.setPredicate(period -> {
                if (text.isBlank()) {
                    return true;
                }
                return period.getName().toLowerCase().contains(text);
            });
        });
    }
}
