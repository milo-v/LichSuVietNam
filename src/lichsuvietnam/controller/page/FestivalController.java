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
import lichsuvietnam.controller.dialog.FestivalDialogController;
import lichsuvietnam.model.Festival;
import lichsuvietnam.service.dao.HistoricalDao;
import lichsuvietnam.service.dao.JsonHistoricalDao;

import java.io.IOException;

public class FestivalController {
    public static final String SCENE_KEY = "festival";

    public static ObservableList<Festival> festivals = FXCollections.observableArrayList();
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

        for (TableColumn<Festival, ?> tableColumn : tableView.getColumns()) {
            tableColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(factor));
        }

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        tableView.setItems(filteredFestivals);

        tableView.setRowFactory(tv -> {
            TableRow<Festival> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty()) ) {
                    Festival festival = row.getItem();

                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().
                            getResource("/fxml/dialog/festival_dialog.fxml"));
                    loader.setController(new FestivalDialogController(festival));
                    Parent parent = null;
                    try {
                        parent = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/lantern.png")));
                    stage.setScene(new Scene(parent));
                    stage.setResizable(false);
                    stage.show();
                }
            });
            return row ;
        });
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
