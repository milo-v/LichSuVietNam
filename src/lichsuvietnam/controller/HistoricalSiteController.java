package lichsuvietnam.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lichsuvietnam.model.HistoricalPeriod;
import lichsuvietnam.model.HistoricalSite;
import lichsuvietnam.service.dao.HistoricalDao;
import lichsuvietnam.service.dao.JsonHistoricalDao;

import java.nio.file.DirectoryStream;

public class HistoricalSiteController {
    public static final String SCENE_KEY = "historicalSite";

    private ObservableList<HistoricalSite> historicalSites = FXCollections.observableArrayList();
    private FilteredList<HistoricalSite> filteredHistoricalSites = new FilteredList<>(historicalSites);

    @FXML
    TableView<HistoricalSite> tableView;

    @FXML
    TableColumn<HistoricalSite, String> nameColumn;

    @FXML
    TableColumn<HistoricalSite, String> locationColumn;

    @FXML
    TableColumn<HistoricalSite, String> designatedDateColumn;

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
        historicalSites.setAll(jsonDao.getHistoricalSites());
    }

    private void initializeTableView() {
        int columnSize = tableView.getColumns().size();
        float factor = 1.0f / columnSize;

        for (TableColumn<HistoricalSite, ?> tableColumn : tableView.getColumns()) {
            tableColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(factor));
            tableColumn.setReorderable(false);
        }

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        designatedDateColumn.setCellValueFactory(new PropertyValueFactory<>("designatedDate"));

        tableView.setItems(filteredHistoricalSites);
    }

    private void initializeTextField() {
        searchTextField.setOnAction(e -> {
            String text = searchTextField.getText().toLowerCase();

            filteredHistoricalSites.setPredicate(site -> {
                if (text.isBlank()) {
                    return true;
                }
                return site.getName().toLowerCase().contains(text);
            });
        });
    }
}
