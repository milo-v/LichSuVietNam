package lichsuvietnam;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lichsuvietnam.controller.MainController;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
		loader.setController(new MainController());
		Scene scene = new Scene(loader.load());
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
}
