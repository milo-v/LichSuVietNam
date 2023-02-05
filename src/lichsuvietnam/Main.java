package lichsuvietnam;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lichsuvietnam.controller.MainController;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
		loader.setController(new MainController());
		Scene scene = new Scene(loader.load());
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/history-book.png")));
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.setTitle("Lịch sử Việt Nam");
		stage.setOnCloseRequest(e -> Platform.exit());
		stage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
}
