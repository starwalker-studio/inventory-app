package environment;

import java.io.File;

public class Constants {

	public static final String SELECT_DATA = "SELECT * FROM `product`";

	public static final String INSERT_DATA = "INSERT INTO `product` (`product_code`, `product_name`, `product_brand`, `product_status`, "
			+ "`product_date`, `product_quantify`, `product_price`) VALUES (?,?,?,?,?,?,?)";

	public static final String SEARCH_BY_PRODUCT_CODE = "SELECT `product_code` FROM `product` WHERE `product_code` = ?";

	public static final String UPDATE_PRODUCT = "UPDATE `product` SET `product_name` = ?"
			+ ", `product_brand` = ?, `product_status` = ?, `product_date` = ?, `product_quantify` = ?"
			+ ", `product_price` = ? WHERE `product_code` = ?";

	public static final String DELETE_PRODUCT = "DELETE FROM `product` WHERE `product_code` = ?";

	public static final String[] PRODUCT_SATUS = { "Full Stock", "Running Low", "Out of Stock" };

	public static final String URL = "jdbc:h2:~/db_wavestore";

	public static final String USER = "user";

	public static final String PASSWORD = "1234";

	public static final String H2_DRIVER = "org.h2.Driver";
	
	public static final String CREATE_USER_TABLE_SCRIPT = "/scripts/user_table.sql";
	
	public static final String CREATE_PRODUCT_TABLE_SCRIPT = "/scripts/product_table.sql";

	public static final String INVENTORY_FORM_LOCATION = "/app/inventoryForm.fxml";

	public static final String SQL_FILE_FILTER = "SQL Text files (*.sql)";

	public static final String SQL_FILE_EXTENSION = "*.sql";

	public static final String EXCEL_FILE_EXTENSION = ".xls";

	public static final String EXCEL_FILE = "inventory";

	public static final String[] INVENTORY_COLUMNS_TABLE = { "id", "product_code", "product_name", "product_brand",
			"product_status", "product_quantify", "product_price", "product_date" };

	public static final String DATE_PATTERN = "MM-dd-yyyy HH:mm:ss";

	public static boolean isFile(File file) {
		return file != null;
	}

	public static final String SELECT_WHERE_USER_AND_PASSWORD = "SELECT `user_name`, `user_password` FROM `user` WHERE `user_name` = ? and `user_password` = ?";

	public static final String SELECT_WHERE_USER = "SELECT `user_name` FROM `user` WHERE `user_name` = ?";

	public static final String INSERT_NEW_USER = "INSERT INTO `user` (`user_name`,`user_password`) VALUES (?,?)";

	/* ALERT MESSAGES */

	public static final String ERROR_MESSAGE = "Error Message";

	public static final String INFO_MESSAGE = "Information Message";

	public static final String CONFIRM_MESSAGE = "Confirmation Message";

	public static final String BLANK_FIELDS = "Please fill all blank fields!";

	public static final String SELECT_STATUS_OR_UPDATE_FIELD_MESSAGE = "Please select a status or update a field";

	public static final String SUCCESS_ADDED_MESSAGE = "Successfully Added!";

	public static final String SUCCESS_UPDATE_MESSAGE = "Successfully Updated!";

	public static final String SUCCESS_DELETE_MESSAGE = "Successfully Deleted!";

	public static final String SUCCESS_LOGIN_MESSAGE = "Successfully Login";

	public static final String SUCCESS_ACCOUNT_CREATED_MESSAGE = "Successfully created an account!";

	public static final String UPDATE_THIS_PRODUCT_MESSAGE = "Do you want to UPDATE this product: ";

	public static final String DELETE_THIS_PRODUCT_MESSAGE = "Do you want to DELETE this product: ";

	public static final String INCORRECT_USERNAME_PASSWORD_MESSAGE = "Incorrect Username/Password";

	public static final String INVALID_PASSWORD_MESSAGE = "Invalid Password, at least 8 characters!";

	public static final String ABOUT_MESSAGE = "This program is for demo and portfolio purposes in the hope that it will be useful."
			+ "\n \n This program is for product inventory with the purpose of an easier tool for employees. \n";

	public static final String COPYRIGHT = "Copyright 2023 Starwalker Code";

	public static final String LOGO = "/img/logoWord.png";
	
	public static final String ICON = "/img/favicon.png";

	public static final String APP_NAME = "Wave Music Store Inventory App V.01";
	
	public static final String LOGIN_FORM = "loginForm.fxml";
	
	public static final String INVENTORY_FORM = "inventoryForm.fxml";
	
	public static final String USER_MANUAL = "/manual/USER-MANUAL.pdf";

}
