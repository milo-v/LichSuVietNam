package lichsuvietnam.controller;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lichsuvietnam.model.HistoricalFigure;
import lichsuvietnam.service.dao.HistoricalDao;
import lichsuvietnam.service.dao.JsonHistoricalDao;

import java.nio.file.DirectoryStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
