package app;

import java.net.URL;
import java.util.ResourceBundle;

import dao.LoginUserDaoImpl;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.LoginUser;

import static environment.InventoryAlertMessage.blankFieldsAlert;
import static environment.LoginAlertMessage.*;
import static environment.Constants.*;

public class LoginController implements Initializable {

	@FXML
	private Button au_loginBtn;

	@FXML
	private PasswordField au_password;

	@FXML
	private Button au_signUpBtn;

	@FXML
	private TextField au_username;

	@FXML
	private Button lg_logInBtn;

	@FXML
	private Button lg_new_accountBtn;

	@FXML
	private PasswordField lg_password;

	@FXML
	private TextField lg_username;

	@FXML
	private BorderPane login_form;

	@FXML
	private BorderPane signup_form;

	private LoginUserDaoImpl userDaoImpl;

	private LoginUser user;

	public void loginAccount() {
		user = new LoginUser(lg_username.getText(), lg_password.getText());
		try {

			if (isLoginBlankFieldsEmpty()) {
				blankFieldsAlert();
			} else {
				if (userDaoImpl.isUser(user)) {
					setInventoryScene();
					successLoginAlert();
				} else {
					invalidUserAlert();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void registerAccount() {
		user = new LoginUser(au_username.getText(), au_password.getText());
		try {
			if (isRegisterBlankFieldsEmpty()) {
				blankFieldsAlert();
			} else {
				if (userDaoImpl.userExist(user)) {
					userExistAlert(user);
				} else {
					if (passwordLength()) {
						invalidPasswordAlert();
					} else {
						userDaoImpl.createUser(user);
						successAccountCreated();
						switchToLoginForm();
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void switchForm(ActionEvent event) {
		if (event.getSource() == au_loginBtn) {
			login_form.setVisible(true);
			signup_form.setVisible(false);
		} else if (event.getSource() == lg_new_accountBtn) {
			login_form.setVisible(false);
			signup_form.setVisible(true);
		}
	}

	public void setInventoryScene() throws Exception {
		lg_logInBtn.getScene().getWindow().hide();

		Parent root = FXMLLoader.load(getClass().getResource(INVENTORY_FORM));

		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.getIcons().add(new Image(getClass().getResourceAsStream(ICON)));
		stage.setTitle(APP_NAME);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	public boolean isLoginBlankFieldsEmpty() {
		return lg_username.getText().isEmpty() || lg_password.getText().isEmpty();
	}

	public boolean isRegisterBlankFieldsEmpty() {
		return au_username.getText().isEmpty() || au_password.getText().isEmpty();
	}

	public boolean passwordLength() {
		return au_password.getText().length() < 8;
	}

	public void switchToLoginForm() {
		login_form.setVisible(true);
		signup_form.setVisible(false);
		au_username.setText("");
		au_password.setText("");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
		userDaoImpl = new LoginUserDaoImpl();
	}

}
