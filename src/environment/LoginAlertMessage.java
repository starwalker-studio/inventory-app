package environment;

import javafx.scene.control.Alert;
import model.LoginUser;
import javafx.scene.control.Alert.AlertType;

import static environment.Constants.*;

public class LoginAlertMessage {

	private static Alert alert;

	public static void successLoginAlert() {
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(INFO_MESSAGE);
		alert.setHeaderText(null);
		alert.setContentText(SUCCESS_LOGIN_MESSAGE);
		alert.showAndWait();
	}

	public static void invalidUserAlert() {
		alert = new Alert(AlertType.ERROR);
		alert.setTitle(ERROR_MESSAGE);
		alert.setHeaderText(null);
		alert.setContentText(INCORRECT_USERNAME_PASSWORD_MESSAGE);
		alert.showAndWait();
	}

	public static void userExistAlert(LoginUser user) {
		alert = new Alert(AlertType.ERROR);
		alert.setTitle(ERROR_MESSAGE);
		alert.setHeaderText(null);
		alert.setContentText(user.getUsername().toUpperCase() + " is already taken!");
		alert.showAndWait();
	}

	public static void invalidPasswordAlert() {
		alert = new Alert(AlertType.ERROR);
		alert.setTitle(ERROR_MESSAGE);
		alert.setHeaderText(null);
		alert.setContentText(INVALID_PASSWORD_MESSAGE);
		alert.showAndWait();
	}
	
	public static void successAccountCreated() {
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(INFO_MESSAGE);
		alert.setHeaderText(null);
		alert.setContentText(SUCCESS_ACCOUNT_CREATED_MESSAGE);
		alert.showAndWait();
	}

}
