package lichsuvietnam.controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lichsuvietnam.model.HistoricalFigure;
import lichsuvietnam.model.HistoricalPeriod;
import lichsuvietnam.service.dao.HistoricalDao;
import lichsuvietnam.service.dao.JsonHistoricalDao;

public class HistoricalPeriodController {
    public static final String SCENE_KEY = "historicalPeriod";

    private ObservableList<HistoricalPeriod> historicalPeriods = FXCollections.observableArrayList();
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
