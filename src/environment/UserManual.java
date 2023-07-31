package environment;

import java.awt.Desktop;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static environment.Constants.*;

public class UserManual {

	private static final InputStream USER_MANUAL_LOCATION = UserManual.class.getResourceAsStream(USER_MANUAL);
	
	private static Path path;
	
	private static FileOutputStream out;
	
	public static void openManualPDF() {
		try {
			path = Files.createTempFile("temp", ".pdf");
			out = new FileOutputStream(path.toFile());
			byte[] buffer = new byte[1024]; 
	        int len; 
	        while ((len = USER_MANUAL_LOCATION.read(buffer)) != -1) { 
	            out.write(buffer, 0, len); 
	        }
			Desktop.getDesktop().open(path.toFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
}
