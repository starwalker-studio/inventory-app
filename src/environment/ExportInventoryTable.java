package environment;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Product;

import static environment.Constants.*;

public class ExportInventoryTable {

	private Parent root;

	private Stage stage;

	private Scene scene;

	private FileChooser fileChooser;

	private File file;

	private Workbook workbook;

	public void saveExcelFile(TableView<Product> table) throws Exception {		
		createExcelFile(table);
		FileOutputStream out = new FileOutputStream(new File(file.toString()));
		workbook.write(out);
		workbook.close();
		out.close();
		openFile(file.toString());
	}

	public void openFile(String file) {
		try {
			File path = new File(file);
			Desktop.getDesktop().open(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createExcelFile(TableView<Product> table) throws Exception {
		root = FXMLLoader.load(getClass().getResource(INVENTORY_FORM_LOCATION));
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
		fileChooser = new FileChooser();
		file = fileChooser.showSaveDialog(stage);

		if (isFile(file)) {
			file = new File(file.toString() + EXCEL_FILE_EXTENSION);
			workbook = new HSSFWorkbook();
			Sheet spreadsheet = workbook.createSheet(EXCEL_FILE);

			Row row = spreadsheet.createRow(0);

			for (int j = 0; j < table.getColumns().size(); j++) {
				row.createCell(j).setCellValue(table.getColumns().get(j).getText());
			}

			for (int i = 0; i < table.getItems().size(); i++) {
				row = spreadsheet.createRow(i + 1);
				for (int j = 0; j < table.getColumns().size(); j++) {
					if (table.getColumns().get(j).getCellData(i) != null) {
						row.createCell(j).setCellValue(table.getColumns().get(j).getCellData(i).toString());
					} else {
						row.createCell(j).setCellValue("");
					}
				}
			}
		}
	}

}
