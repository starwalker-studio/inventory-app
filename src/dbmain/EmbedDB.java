package dbmain;

//import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;

import org.h2.tools.RunScript;

import static environment.Constants.*;

public class EmbedDB {

	private static final InputStream USER_SCRIPT = EmbedDB.class.getResourceAsStream(CREATE_USER_TABLE_SCRIPT);

	private static final InputStream PRODUCT_SCRIPT = EmbedDB.class.getResourceAsStream(CREATE_USER_TABLE_SCRIPT);

	public static Connection connect() {
		try {
			Class.forName(H2_DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			/*
			 * RunScript.execute(conn, new FileReader(CREATE_USER_TABLE_SCRIPT));
			 * RunScript.execute(conn, new FileReader(CREATE_PRODUCT_TABLE_SCRIPT));
			 */
			RunScript.execute(conn, new InputStreamReader(USER_SCRIPT));
			RunScript.execute(conn, new InputStreamReader(PRODUCT_SCRIPT));
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
