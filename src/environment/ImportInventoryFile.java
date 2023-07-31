package environment;

import java.io.File;
import java.io.FileReader;

import org.h2.tools.RunScript;

import dbmain.EmbedDB;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import static environment.Constants.*;

public class ImportInventoryFile {

	private Parent root;

	private Stage stage;

	private Scene scene;

	private FileChooser fileChooser;

	private File file;

	public void importScriptFile() throws Exception {
		root = FXMLLoader.load(getClass().getResource(INVENTORY_FORM_LOCATION));
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
		fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(SQL_FILE_FILTER, SQL_FILE_EXTENSION);
        fileChooser.getExtensionFilters().add(extFilter);
		file = fileChooser.showOpenDialog(stage);

		if (isFile(file)) {
			RunScript.execute(EmbedDB.connect(), new FileReader(file));
		}

	}

}
