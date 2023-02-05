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
import lichsuvietnam.controller.dialog.HistoricalFigureDialogController;
import lichsuvietnam.model.HistoricalFigure;
import lichsuvietnam.service.dao.HistoricalDao;
import lichsuvietnam.service.dao.JsonHistoricalDao;

import java.io.IOException;

public class HistoricalFigureController {
    public final static String SCENE_KEY = "historicalFigure";

    private ObservableList<HistoricalFigure> historicalFigures = FXCollections.observableArrayList();
    private FilteredList<HistoricalFigure> filteredHistoricalFigures = new FilteredList<>(historicalFigures);
    @FXML
    private TextField searchTextField;

    @FXML
    private TableView<HistoricalFigure> tableView;

    @FXML
    private TableColumn<HistoricalFigure, String> nameColumn;

    @FXML
    private TableColumn<HistoricalFigure, String> birthColumn;

    @FXML
    private TableColumn<HistoricalFigure, String> deathColumn;

    @FXML
    private TableColumn<HistoricalFigure, String> occupationColumn;

    @FXML
    public void initialize() {
        initializeTableView();
        initializeSearchTextField();
        getData();
    }

    public void getData() {
        HistoricalDao jsonDao = new JsonHistoricalDao();
        historicalFigures.setAll(jsonDao.getHistoricalFigures());
    }

    public void initializeTableView() {
        int columnSize = tableView.getColumns().size();
        float factor = 1.0f / columnSize;

        for (TableColumn<HistoricalFigure, ?> tableColumn : tableView.getColumns()) {
            tableColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(factor));
            tableColumn.setReorderable(false);
        }

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        birthColumn.setCellValueFactory(new PropertyValueFactory<>("birth"));
        deathColumn.setCellValueFactory(new PropertyValueFactory<>("death"));
        occupationColumn.setCellValueFactory(new PropertyValueFactory<>("occupation"));

        tableView.setItems(filteredHistoricalFigures);


        tableView.setRowFactory(tv -> {
            TableRow<HistoricalFigure> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty()) ) {
                    HistoricalFigure historicalFigure = row.getItem();

                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().
                            getResource("/fxml/dialog/historical_figure_dialog.fxml"));
                    loader.setController(new HistoricalFigureDialogController(historicalFigure));
                    Parent parent = null;
                    try {
                        parent = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/cheongsam.png")));
                    stage.setScene(new Scene(parent));
                    stage.setResizable(false);
                    stage.show();
                }
            });
            return row ;
        });
    }

    public void initializeSearchTextField() {
        searchTextField.setOnAction(e -> {
            String text = searchTextField.getText().toLowerCase();

            filteredHistoricalFigures.setPredicate(figure -> {
                if (text.isBlank()) {
                    return true;
                }
                return figure.getName().toLowerCase().contains(text);
            });
        });
    }

}
