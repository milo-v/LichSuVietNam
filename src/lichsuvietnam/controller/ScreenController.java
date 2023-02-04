package lichsuvietnam.controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lichsuvietnam.model.Festival;
import lichsuvietnam.model.HistoricalEvent;
import lichsuvietnam.model.HistoricalFigure;
import lichsuvietnam.model.HistoricalPeriod;
import lichsuvietnam.model.HistoricalSite;
import lichsuvietnam.service.dao.JsonHistoricalDao;

public class ScreenController {
	private ObservableList<HistoricalPeriod> listHistoricalPeriods = FXCollections.observableArrayList();

	private ObservableList<HistoricalEvent> listHistoricalEvents = FXCollections.observableArrayList();

	private ObservableList<HistoricalFigure> listHistoricalFigures = FXCollections.observableArrayList();

	private ObservableList<HistoricalSite> listHistoricalSites = FXCollections.observableArrayList();

	private ObservableList<Festival> listFestivals = FXCollections.observableArrayList();

	private ArrayList<HistoricalPeriod> historicalPeriods;

	private ArrayList<HistoricalEvent> historicalEvents;

	private ArrayList<HistoricalFigure> historicalFigures;

	private ArrayList<HistoricalSite> historicalSites;

	private ArrayList<Festival> festivals;

	@FXML
	private Tab tabHE;

	@FXML
	private Tab tabHF;

	@FXML
	private TableColumn<Festival, String> colNameFestival;

	@FXML
	private TableColumn<Festival, String> colLocationFestival;

	@FXML
	private TableColumn<Festival, String> colDateFestival;

	@FXML
	private TableColumn<HistoricalSite, String> colLocationHS;

	@FXML
	private TableColumn<?, ?> colMotherName;

	@FXML
	private TableColumn<HistoricalSite, String> colDateHS;

	@FXML
	private TableColumn<HistoricalPeriod, String> colTimeSpanHP;

	@FXML
	private Tab tabHP;

	@FXML
	private Tab tabFestival;

	@FXML
	private TableColumn<HistoricalFigure, String> colDateOfDeath;

	@FXML
	private TableColumn<HistoricalEvent, String> colDateHE;

	@FXML
	private TableColumn<HistoricalFigure, String> colDateOfBirth;

	@FXML
	private TableColumn<?, ?> colFirstOrganizedDate;

	@FXML
	private TableColumn<?, ?> colFatherName;

	@FXML
	private TableColumn<HistoricalSite, String> colNameHS;

	@FXML
	private TableColumn<?, ?> colNoteHE;

	@FXML
	private TableColumn<HistoricalFigure, String> colOccupation;

	@FXML
	private TableColumn<HistoricalPeriod, String> colNameHP;

	@FXML
	private Tab tabHS;

	@FXML
	private TableColumn<?, ?> colLocationHE;

	@FXML
	private TableColumn<HistoricalFigure, String> colPeriodHF;

	@FXML
	private TableColumn<?, ?> colNoteHF;

	@FXML
	private TableColumn<HistoricalEvent, String> colNameHE;

	@FXML
	private TableColumn<HistoricalFigure, String> colNameHF;

	@FXML
	private TableColumn<?, ?> colNoteHS;

	@FXML
	private TableColumn<HistoricalFigure, Button> colNoteHP;

	@FXML
	private TableColumn<?, ?> colEndDate;

	@FXML
	private TableView<Festival> tableFestival;

	@FXML
	private TableView<HistoricalSite> tableHS;

	@FXML
	private TableView<HistoricalFigure> tableHF;

	@FXML
	private TableView<HistoricalEvent> tableHE;

	@FXML
	private TableView<HistoricalPeriod> tableHP;

	@FXML
	private TableColumn<?, ?> colNoteFestival;

	@FXML
	private TextField searchFestival;

	@FXML
	private Button confirmButtonFestival;

	@FXML
	private TextField searchHS;

	@FXML
	private Button confirmButtonHS;

	@FXML
	private TextField searchHF;

	@FXML
	private Button confirmButtonHF;

	@FXML
	private TextField searchHE;

	@FXML
	private Button confirmButtonHE;

	@FXML
	private TextField searchHP;

	@FXML
	private Button confirmButtonHP;

	@FXML
	private Button updateButton;

	@FXML
	void handleClickHP(ActionEvent event) {
		if (event.getSource() == confirmButtonHP) {
			this.listHistoricalPeriods.removeAll(this.listHistoricalPeriods);
			String queryHP = searchHP.getText();

			for (int i = 0; i < this.historicalPeriods.size(); i++) {
				if (this.historicalPeriods.get(i).getName().toLowerCase().contains(queryHP.toLowerCase())) {
					this.listHistoricalPeriods.add(this.historicalPeriods.get(i));
				}
			}

			colNameHP.setCellValueFactory(new PropertyValueFactory<HistoricalPeriod, String>("name"));
			colTimeSpanHP.setCellValueFactory(new PropertyValueFactory<HistoricalPeriod, String>("timeSpan"));

			tableHP.setItems(this.listHistoricalPeriods);
		}
	}

	@FXML
	void handleClickHE(ActionEvent event) {
		if (event.getSource() == confirmButtonHE) {
			this.listHistoricalEvents.removeAll(this.listHistoricalEvents);
			String queryHE = searchHE.getText();

			for (int i = 0; i < this.historicalEvents.size(); i++) {
				if (this.historicalEvents.get(i).getName().toLowerCase().contains(queryHE.toLowerCase())) {
					this.listHistoricalEvents.add(this.historicalEvents.get(i));
				}
			}

			colNameHE.setCellValueFactory(new PropertyValueFactory<HistoricalEvent, String>("name"));
			colDateHE.setCellValueFactory(new PropertyValueFactory<HistoricalEvent, String>("date"));

			tableHE.setItems(this.listHistoricalEvents);
		}
	}

	@FXML
	void handleClickHF(ActionEvent event) {
		if (event.getSource() == confirmButtonHF) {
			this.listHistoricalFigures.removeAll(this.listHistoricalFigures);
			String queryHF = searchHF.getText();

			for (int i = 0; i < this.historicalFigures.size(); i++) {
				if (this.historicalFigures.get(i).getName().toLowerCase().contains(queryHF.toLowerCase())) {
					this.listHistoricalFigures.add(this.historicalFigures.get(i));
				}
			}

			colNameHF.setCellValueFactory(new PropertyValueFactory<HistoricalFigure, String>("name"));
			colDateOfBirth.setCellValueFactory(new PropertyValueFactory<HistoricalFigure, String>("birth"));
			colDateOfDeath.setCellValueFactory(new PropertyValueFactory<HistoricalFigure, String>("death"));
			colPeriodHF.setCellValueFactory(new PropertyValueFactory<HistoricalFigure, String>("period"));
			colOccupation.setCellValueFactory(new PropertyValueFactory<HistoricalFigure, String>("occupation"));

			tableHF.setItems(this.listHistoricalFigures);
		}
	}

	@FXML
	void handleClickHS(ActionEvent event) {
		if (event.getSource() == confirmButtonHS) {
			this.listHistoricalSites.removeAll(this.listHistoricalSites);
			String queryHS = searchHS.getText();

			for (int i = 0; i < this.historicalSites.size(); i++) {
				if (this.historicalSites.get(i).getName().toLowerCase().contains(queryHS.toLowerCase())) {
					this.listHistoricalSites.add(this.historicalSites.get(i));
				}
			}

			colNameHS.setCellValueFactory(new PropertyValueFactory<HistoricalSite, String>("name"));
			colDateHS.setCellValueFactory(new PropertyValueFactory<HistoricalSite, String>("designatedDate"));
			colLocationHS.setCellValueFactory(new PropertyValueFactory<HistoricalSite, String>("location"));

			tableHS.setItems(this.listHistoricalSites);
		}
	}

	@FXML
	void handleClickFestival(ActionEvent event) {
		if (event.getSource() == confirmButtonFestival) {
			this.listFestivals.removeAll(this.listFestivals);
			String queryFestival = searchFestival.getText();

			for (int i = 0; i < this.festivals.size(); i++) {
				if (this.festivals.get(i).getName().toLowerCase().contains(queryFestival.toLowerCase())) {
					this.listFestivals.add(this.festivals.get(i));
				}
			}

			colNameFestival.setCellValueFactory(new PropertyValueFactory<Festival, String>("name"));
			colDateFestival.setCellValueFactory(new PropertyValueFactory<Festival, String>("date"));
			colLocationFestival.setCellValueFactory(new PropertyValueFactory<Festival, String>("location"));

			tableFestival.setItems(this.listFestivals);
		}
	}

	@FXML
	void handleClickUpdate(ActionEvent event) {
		if (event.getSource() == updateButton) {
			JsonHistoricalDao scraperService = new JsonHistoricalDao();
			Alert dialog1 = new Alert(AlertType.CONFIRMATION);
			dialog1.setTitle("Lịch sử Việt Nam");
			dialog1.setHeaderText("Cập nhật dữ liệu");
			ButtonType typeOK = new ButtonType("OK", ButtonData.OK_DONE);
			ButtonType typeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
			dialog1.setContentText("Nhấn OK để bắt đầu cập nhật dữ liệu");
			// dialog1.getDialogPane().getButtonTypes().add(typeCancel);
			dialog1.showAndWait().ifPresent(response -> {
				if (response == ButtonType.OK) {
					scraperService.updateAll();
					loadDataFromJson();
					// dialog.close();
					Dialog<String> dialog2 = new Dialog<>();
					dialog2.setTitle("Lịch sử Việt Nam");
					dialog2.setContentText("Đã cập nhật xong dữ liệu");
					dialog2.getDialogPane().getButtonTypes().add(typeOK);
					dialog2.show();
				}
			});
			// if (dialog1.bu)

			// dialog.show();
		}
	}

	@FXML
	void handleRowClickHF(MouseEvent event) {
		if (event.getClickCount() == 2) {
			Dialog<String> dialog = new Dialog<>();
			HistoricalFigure figure = tableHF.getSelectionModel().getSelectedItem();
			dialog.setTitle(figure.getName());
			String content = "";
			if (figure.getRelatedHistoricalPeriodId() != null) {
				content += "Giai đoạn lịch sử liên quan:\n";
				for (int i = 0; i < this.historicalPeriods.size(); i++) {
					if (figure.getRelatedHistoricalPeriodId().equals(this.historicalPeriods.get(i).getId())) {
						content += " + " + this.historicalPeriods.get(i).getName() + "\n";
					}
				}
			}
			if (figure.getRelatedHistoricalEventIds().size() > 0) {
				content += "Sự kiện lịch sử liên quan:\n";
				for (int i = 0; i < figure.getRelatedHistoricalEventIds().size(); i++) {
					for (int j = 0; j < this.historicalEvents.size(); j++) {
						if (figure.getRelatedHistoricalEventIds().get(i).equals(this.historicalEvents.get(j).getId())) {
							content += " + " + this.historicalEvents.get(j).getName() + "\n";
						}
					}
				}
			}
			if (figure.getRelatedFestivalIds().size() > 0) {
				content += "Lễ hội liên quan:\n";
				for (int i = 0; i < figure.getRelatedFestivalIds().size(); i++) {
					for (int j = 0; j < this.festivals.size(); j++) {
						if (figure.getRelatedFestivalIds().get(i).equals(this.festivals.get(j).getId())) {
							content += " + " + this.festivals.get(j).getName() + "\n";
						}
					}
				}
			}
			if (figure.getRelatedHistoricalSiteIds().size() > 0) {
				content += "Di tích lịch sử liên quan:\n";
				for (int i = 0; i < figure.getRelatedHistoricalSiteIds().size(); i++) {
					for (int j = 0; j < this.historicalSites.size(); j++) {
						if (figure.getRelatedHistoricalSiteIds().get(i).equals(this.historicalSites.get(j).getId())) {
							content += " + " + this.historicalSites.get(j).getName() + "\n";
						}
					}
				}
			}
			content += "Hết";
			dialog.setContentText(content);
			ButtonType typeOK = new ButtonType("OK", ButtonData.OK_DONE);
			dialog.getDialogPane().getButtonTypes().add(typeOK);
			dialog.show();
		}
	}
	
	@FXML
	void handleRowClickHE(MouseEvent event) {
		if (event.getClickCount() == 2) {
			Dialog<String> dialog = new Dialog<>();
			HistoricalEvent historicalEvent = tableHE.getSelectionModel().getSelectedItem();
			dialog.setTitle(historicalEvent.getName());
			String content = "";
			
			if (historicalEvent.getRelatedHistoricalFigureIds().size() > 0) {
				content += "Nhân vật lịch sử liên quan:\n";
				for (int i = 0; i < historicalEvent.getRelatedHistoricalFigureIds().size(); i++) {
					for (int j = 0; j < this.historicalFigures.size(); j++) {
						if (historicalEvent.getRelatedHistoricalFigureIds().get(i).equals(this.historicalFigures.get(j).getId())) {
							content += " + " + this.historicalFigures.get(j).getName() + "\n";
						}
					}
				}
			}
			
			content += "Hết";
			dialog.setContentText(content);
			ButtonType typeOK = new ButtonType("OK", ButtonData.OK_DONE);
			dialog.getDialogPane().getButtonTypes().add(typeOK);
			dialog.show();
		}
	}
	
	@FXML
	void handleRowClickHS(MouseEvent event) {
		if (event.getClickCount() == 2) {
			Dialog<String> dialog = new Dialog<>();
			HistoricalSite site = tableHS.getSelectionModel().getSelectedItem();
			dialog.setTitle(site.getName());
			String content = "";
			
			if (site.getRelatedHistoricalFigureIds().size() > 0) {
				content += "Nhân vật lịch sử liên quan:\n";
				for (int i = 0; i < site.getRelatedHistoricalFigureIds().size(); i++) {
					for (int j = 0; j < this.historicalFigures.size(); j++) {
						if (site.getRelatedHistoricalFigureIds().get(i).equals(this.historicalFigures.get(j).getId())) {
							content += " + " + this.historicalFigures.get(j).getName() + "\n";
						}
					}
				}
			}
			
			content += "Hết";
			dialog.setContentText(content);
			ButtonType typeOK = new ButtonType("OK", ButtonData.OK_DONE);
			dialog.getDialogPane().getButtonTypes().add(typeOK);
			dialog.show();
		}
	}
	
	@FXML
	void handleRowClickHP(MouseEvent event) {
		if (event.getClickCount() == 2) {
			Dialog<String> dialog = new Dialog<>();
			HistoricalPeriod period = tableHP.getSelectionModel().getSelectedItem();
			dialog.setTitle(period.getName());
			String content = "";
			
			if (period.getRelatedHistoricalFigureIds().size() > 0) {
				content += "Nhân vật lịch sử liên quan:\n";
				for (int i = 0; i < period.getRelatedHistoricalFigureIds().size(); i++) {
					for (int j = 0; j < this.historicalFigures.size(); j++) {
						if (period.getRelatedHistoricalFigureIds().get(i).equals(this.historicalFigures.get(j).getId())) {
							content += " + " + this.historicalFigures.get(j).getName() + "\n";
						}
					}
				}
			}
			
			content += "Hết";
			dialog.setContentText(content);
			ButtonType typeOK = new ButtonType("OK", ButtonData.OK_DONE);
			dialog.getDialogPane().getButtonTypes().add(typeOK);
			dialog.show();
		}
	}
	
	@FXML
	void handleRowClickFestival(MouseEvent event) {
		if (event.getClickCount() == 2) {
			Dialog<String> dialog = new Dialog<>();
			Festival festival = tableFestival.getSelectionModel().getSelectedItem();
			dialog.setTitle(festival.getName());
			String content = "";
			
			if (festival.getRelatedHistoricalFigureIds().size() > 0) {
				content += "Nhân vật lịch sử liên quan:\n";
				for (int i = 0; i < festival.getRelatedHistoricalFigureIds().size(); i++) {
					for (int j = 0; j < this.historicalFigures.size(); j++) {
						if (festival.getRelatedHistoricalFigureIds().get(i).equals(this.historicalFigures.get(j).getId())) {
							content += " + " + this.historicalFigures.get(j).getName() + "\n";
						}
					}
				}
			}
			
			content += "Hết";
			dialog.setContentText(content);
			ButtonType typeOK = new ButtonType("OK", ButtonData.OK_DONE);
			dialog.getDialogPane().getButtonTypes().add(typeOK);
			dialog.show();
		}
	}

	public void initialize() {
		loadDataFromJson();
	}

	void loadDataFromJson() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			this.historicalPeriods = mapper.readValue(Paths.get("data/historical_periods.json").toFile(),
					new TypeReference<List<HistoricalPeriod>>() {
					});
			this.historicalEvents = mapper.readValue(Paths.get("data/historical_events.json").toFile(),
					new TypeReference<List<HistoricalEvent>>() {
					});
			this.historicalFigures = mapper.readValue(Paths.get("data/historical_figures.json").toFile(),
					new TypeReference<List<HistoricalFigure>>() {
					});
			this.historicalSites = mapper.readValue(Paths.get("data/historical_sites.json").toFile(),
					new TypeReference<List<HistoricalSite>>() {
					});
			this.festivals = mapper.readValue(Paths.get("data/festivals.json").toFile(),
					new TypeReference<List<Festival>>() {
					});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}