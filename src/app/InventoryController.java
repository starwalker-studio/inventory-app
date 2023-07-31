package app;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.ProductDaoImpl;
import environment.ExportInventoryTable;
import environment.ImportInventoryFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Product;

import static environment.InventoryAlertMessage.*;

import static environment.Constants.*;

import static environment.UserManual.openManualPDF;

public class InventoryController implements Initializable {

	@FXML
	private Button inv_addBtn;

	@FXML
	private Button inv_clearBtn;

	@FXML
	private Button inv_deleteBtn;

	@FXML
	private TextField inv_productBrand;

	@FXML
	private TextField inv_productCode;

	@FXML
	private TextField inv_productName;

	@FXML
	private TextField inv_productPrice;

	@FXML
	private Button inv_updateBtn;

	@FXML
	private TextField inv_productQuantify;

	@FXML
	private ComboBox<String> inv_productStatus;

	@FXML
	private TableView<Product> inv_tableView;

	@FXML
	private TableColumn<Product, Integer> inv_idColumn;

	@FXML
	private TableColumn<Product, String> inv_productCodeColumn;

	@FXML
	private TableColumn<Product, String> inv_productNameColumn;

	@FXML
	private TableColumn<Product, String> inv_productBrandColumn;

	@FXML
	private TableColumn<Product, String> inv_productStatusColumn;

	@FXML
	private TableColumn<Product, String> inv_productQuantifyColumn;

	@FXML
	private TableColumn<Product, String> inv_productPriceColumn;

	@FXML
	private TableColumn<Product, String> inv_productDateColumn;
	
	@FXML
    private MenuBar inv_menuBar;
	
	private ProductDaoImpl productDao;
	
	private Product product;
	
	private ObservableList<Product> productData;
	
	private ExportInventoryTable inventoryTable;
	
	private ImportInventoryFile importInvFile;
	
	private Stage stage;
	
	private Parent root;
	
	private Stage loginStage;
	
	private Scene scene;

	public void productAddBtn() {
		try {

			if (productValidation()) {
				blankFieldsAlert();
			} else {				
				if (productDao.findByProductCode(productCode())) {
					productCodeExistAlert(product);
				} else {					
					productDao.insertProduct(setProductForTable());
					productShowData();
					clearSectionsBtn();
					productSuccessAddedAlert();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void productUpdateBtn() {
		try {
			
			if (productValidation()) {
				statusFieldAlert();
			} else {
				Optional<ButtonType> option = updateProductConfirmation(productCode());
				
				if (option.get().equals(ButtonType.OK)) {					
					productDao.updateProduct(setProductForTable());				
					productShowData();
					clearSectionsBtn();
					productSuccessUpdatedAlert();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void deleteProductBtn() {
		try {
			Optional<ButtonType> option = deleteProductConfirmation(productCode());

			if (option.get().equals(ButtonType.OK)) {
				productDao.deleteProduct(productCode());
				productShowData();
				clearSectionsBtn();
				productSuccessDeletedAlert();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void saveFile() {		
		try {						
			inventoryTable.saveExcelFile(inv_tableView);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void importFile() {
		try {
			importInvFile.importScriptFile();
			productShowData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void about() {
		aboutInfoalert();
	}
	
	public void userManual() {
		openManualPDF();
	}
	
	public void logOut() {		
		try {
		root = FXMLLoader.load(getClass().getResource(LOGIN_FORM));
		stage = (Stage) inv_menuBar.getScene().getWindow();
		stage.hide();
		loginStage = new Stage();
		scene = new Scene(root);
		loginStage.setScene(scene);
		loginStage.getIcons().add(new Image(getClass().getResourceAsStream(ICON)));
		loginStage.setResizable(false);
		loginStage.setTitle(APP_NAME);
		loginStage.show();		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void exit() {
		stage = (Stage) inv_menuBar.getScene().getWindow();
		stage.close();
	}

	public void clearSectionsBtn() {
		inv_productCode.setText("");
		inv_productName.setText("");
		inv_productBrand.setText("");
		inv_productStatus.getSelectionModel().clearSelection();
		inv_productQuantify.setText("");
		inv_productPrice.setText("");
		inv_productCode.setDisable(false);
		inv_updateBtn.setDisable(true);
		inv_deleteBtn.setDisable(true);
	}

	public void productStatus() {
		List<String> statusList = new ArrayList<>();
		for (String data : PRODUCT_SATUS) {
			statusList.add(data);
		}
		ObservableList<String> statusData = FXCollections.observableArrayList(statusList);
		inv_productStatus.setItems(statusData);
	}

	public ObservableList<Product> productListData() {		
		ObservableList<Product> listData = FXCollections.observableArrayList();
		try {
			listData = productDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listData;

	}	

	public void productShowData() {
		productData = productListData();
		inv_idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		inv_productCodeColumn.setCellValueFactory(new PropertyValueFactory<>("productCode"));
		inv_productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
		inv_productBrandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
		inv_productStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		inv_productQuantifyColumn.setCellValueFactory(new PropertyValueFactory<>("quantify"));
		inv_productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		inv_productDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		inv_tableView.setItems(productData);
		inv_updateBtn.setDisable(true);
		inv_deleteBtn.setDisable(true);
	}

	public void productSelectData() {
		Product product = inv_tableView.getSelectionModel().getSelectedItem();
		int num = inv_tableView.getSelectionModel().getSelectedIndex();
		if ((num - 1) < -1) return;
		inv_productCode.setText(product.getProductCode());
		inv_productName.setText(product.getProductName());
		inv_productBrand.setText(product.getBrand());
		inv_productQuantify.setText(product.getQuantify());
		inv_productPrice.setText(product.getPrice());
		inv_productCode.setDisable(true);
		inv_updateBtn.setDisable(false);
		inv_deleteBtn.setDisable(false);
	}
	
	public boolean productValidation() {
		return inv_productCode.getText().isEmpty() 
				|| inv_productName.getText().isEmpty()
				|| inv_productBrand.getText().isEmpty()
				|| inv_productStatus.getSelectionModel().getSelectedItem() == null
				|| inv_productQuantify.getText().isEmpty() || inv_productPrice.getText().isEmpty();
	}
	
	public Product setProductForTable() {
		return product = new Product(inv_productCode.getText(), inv_productName.getText(), inv_productBrand.getText(),
				(String) inv_productStatus.getSelectionModel().getSelectedItem(), inv_productQuantify.getText(),
				inv_productPrice.getText());		
	}
	
	public Product productCode() {
		product = new Product();
		product.setProductCode(this.inv_productCode.getText());
		return product;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		importInvFile = new ImportInventoryFile();
		inventoryTable = new ExportInventoryTable();
		productDao = new ProductDaoImpl();
		productStatus();
		productShowData();
	}

}
