package environment;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import model.Product;

import static environment.Constants.*;

public class InventoryAlertMessage {
	
	private static Alert alert;
	
	public static void blankFieldsAlert() {
		alert = new Alert(AlertType.ERROR);
		alert.setTitle(ERROR_MESSAGE);
		alert.setHeaderText(null);
		alert.setContentText(BLANK_FIELDS);
		alert.showAndWait();
	}
	
	public static void statusFieldAlert() {
		alert = new Alert(AlertType.ERROR);
		alert.setTitle(ERROR_MESSAGE);
		alert.setHeaderText(null);
		alert.setContentText(SELECT_STATUS_OR_UPDATE_FIELD_MESSAGE);
		alert.showAndWait();
	}
	
	public static void productCodeExistAlert(Product product) {
		alert = new Alert(AlertType.ERROR);
		alert.setTitle(ERROR_MESSAGE);
		alert.setHeaderText(null);
		alert.setContentText("Product Code: " + product.getProductCode() + " already exists!");
		alert.showAndWait();
	}
	
	public static void productSuccessAddedAlert() {
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(INFO_MESSAGE);
		alert.setHeaderText(null);
		alert.setContentText(SUCCESS_ADDED_MESSAGE);
		alert.showAndWait();
	}
	
	public static void productSuccessUpdatedAlert() {
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(INFO_MESSAGE);
		alert.setHeaderText(null);
		alert.setContentText(SUCCESS_UPDATE_MESSAGE);
		alert.showAndWait();
	}
	
	public static void productSuccessDeletedAlert() {
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(INFO_MESSAGE);
		alert.setHeaderText(null);
		alert.setContentText(SUCCESS_DELETE_MESSAGE);
		alert.showAndWait();
	}
	
	public static Optional<ButtonType> updateProductConfirmation(Product product) {
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(CONFIRM_MESSAGE);
		alert.setHeaderText(null);
		alert.setContentText(UPDATE_THIS_PRODUCT_MESSAGE + product.getProductCode() + " ?");
		return alert.showAndWait();
	}
	
	public static Optional<ButtonType> deleteProductConfirmation(Product product) {
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(CONFIRM_MESSAGE);
		alert.setHeaderText(null);
		alert.setContentText(DELETE_THIS_PRODUCT_MESSAGE + product.getProductCode() + " ?");
		return alert.showAndWait();
	}
	
	public static void aboutInfoalert() {
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(APP_NAME);
		alert.setHeaderText(COPYRIGHT);
		ImageView icon = new ImageView(LOGO);
	    icon.setFitHeight(58);
	    icon.setFitWidth(98);
	    alert.setContentText(ABOUT_MESSAGE);
	    alert.getDialogPane().setGraphic(icon);
	    alert.show();
	}

}
