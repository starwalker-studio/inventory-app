package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import static environment.Constants.*;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource(LOGIN_FORM));
		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);
		primaryStage.setTitle(APP_NAME);
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(ICON)));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
