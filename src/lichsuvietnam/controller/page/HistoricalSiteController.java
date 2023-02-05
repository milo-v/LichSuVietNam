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
import lichsuvietnam.controller.dialog.HistoricalSiteDialogController;
import lichsuvietnam.model.HistoricalSite;
import lichsuvietnam.service.dao.HistoricalDao;
import lichsuvietnam.service.dao.JsonHistoricalDao;

import java.io.IOException;

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

        tableView.setRowFactory(table -> {
            TableRow<HistoricalSite> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty()) ) {
                    HistoricalSite historicalSite = row.getItem();

                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().
                            getResource("/fxml/dialog/historical_site_dialog.fxml"));
                    loader.setController(new HistoricalSiteDialogController(historicalSite));
                    Parent parent = null;
                    try {
                        parent = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/tombs.png")));
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

            filteredHistoricalSites.setPredicate(site -> {
                if (text.isBlank()) {
                    return true;
                }
                return site.getName().toLowerCase().contains(text);
            });
        });
    }
}
